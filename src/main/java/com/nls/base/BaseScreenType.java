package com.nls.base;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.NonNull;

public enum BaseScreenType {
    STRETCH;

    public @NonNull Viewport getViewport(float width, float height, @NonNull Camera camera) {
        return switch (this) {
            case STRETCH -> new StretchViewport(width, height, camera);
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }
}
