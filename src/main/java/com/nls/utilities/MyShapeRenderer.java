package com.nls.utilities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nls.game.board.Checker;
import com.nls.game.board.Square;
import lombok.NonNull;

public class MyShapeRenderer extends ShapeRenderer {
    private enum MyShapeType { Rect, Circle }

    public MyShapeRenderer() {
    }

    public MyShapeRenderer(int maxVertices) {
        super(maxVertices);
    }

    public MyShapeRenderer(int maxVertices, ShaderProgram defaultShader) {
        super(maxVertices, defaultShader);
    }

    public void drawRect(@NonNull Square square) { drawRect(square, null); }

    private void draw(@NonNull Actor actor, Batch batch, MyShapeType type) {
        boolean isBatched = batch != null && batch.isDrawing();
        if ( isBatched ) { batch.end(); }
        setProjectionMatrix(actor.getStage().getCamera().combined);
        begin(ShapeRenderer.ShapeType.Filled);
        setColor(actor.getColor());
        switch ( type ) {
            case Rect -> rect(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
            case Circle -> {
                circle(actor.getX(), actor.getY(), actor.getWidth() * actor.getScaleX());
            }
        }
        end();
        if ( isBatched ) { batch.begin(); }
    }

    public void drawRect(@NonNull Square square, Batch batch) {
        draw(square, batch, MyShapeType.Rect);
    }

    public void drawCircle(@NonNull Checker checker, Batch batch) {
        draw(checker, batch, MyShapeType.Circle);
    }
}
