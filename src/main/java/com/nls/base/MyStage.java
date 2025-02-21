package com.nls.base;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.NonNull;

public class MyStage extends Stage {
    private final Vector2 stageCoordinates = new Vector2();

    public MyStage() { super(); }

    public MyStage(Viewport viewport) { super(viewport); }

    public MyStage(Viewport viewport, Batch batch) { super(viewport, batch); }

    public float getStageCoordinatesX() { return getStageCoordinates().x; }

    public float getStageCoordinatesY() { return getStageCoordinates().y; }

    public final @NonNull Vector2 getStageCoordinates() { return stageCoordinates; }

    public final @NonNull Vector2 updateStageCoordinates(float screenX, float screenY) {
        stageCoordinates.set(screenX, screenY);
        this.screenToStageCoordinates(stageCoordinates);
        return getStageCoordinates();
    }
}
