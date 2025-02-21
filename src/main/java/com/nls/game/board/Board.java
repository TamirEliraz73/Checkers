package com.nls.game.board;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nls.base.MyStage;
import com.nls.base.SquareNotFoundException;
import com.nls.base.Vector2R;
import com.nls.service.BoardListener;
import com.nls.service.BoardService;
import com.nls.service.GameService;
import lombok.NonNull;

import java.util.HashMap;

public class Board extends Group implements BoardListener {
    private final Array<Square> squaresArray;
    private final HashMap<Vector2R, Square> squaresMap;

    public Board(Viewport viewport) {
        this.setStage(new MyStage(viewport));
        squaresArray = new Array<>();
        squaresMap = new HashMap<>();
        getStage().addActor(this);

        BoardService.getInstance(this); // init
        SquareFactory factory = SquareFactory.getInstance(true);
        while ( factory.hasNext() ) {
            Square square = factory.next();
            addActor(square);
            squaresArray.add(square);
            squaresMap.put(square.getPositionInBoard(), square);
        }

        GameService.getInstance(); // init
    }

    public @NonNull Square getSquare(int row, int col) {
        Square square = squaresMap.get(new Vector2R(row, col));
        if ( square == null ) {
            throw new SquareNotFoundException("Cannot find square with values row=" + row + ", col=" + col);
        }
        return square;
    }
    public boolean hasSquare(int row, int col) {
        return squaresMap.containsKey(new Vector2R(row, col));
    }
    public @NonNull Array<Square> getSquares() {
        if ( squaresArray.size != getStage().getActors().size ) {
            squaresArray.clear();
            squaresArray.clear();
            for ( Actor actor : getStage().getActors() ) {
                if ( actor instanceof Square ) {
                    squaresArray.add(( Square ) actor);
                    squaresMap.put(new Vector2R((( Square ) actor).row(), (( Square ) actor).col()), ( Square ) actor);
                }
            }
        }
        return squaresArray;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void update(float dt) {
        this.getStage().act(dt);
        this.getStage().draw();
    }
}