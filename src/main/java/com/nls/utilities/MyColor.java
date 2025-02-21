package com.nls.utilities;

import com.badlogic.gdx.graphics.Color;
import lombok.NonNull;

public abstract class MyColor {
    public static final @NonNull Color woodColor1 = generateColor(39, 24, 16);
    public static final @NonNull Color woodColor2 = generateColor(79, 32, 15);
    public static final @NonNull Color woodColor3 = generateColor(149, 69, 32);
    public static final @NonNull Color woodColor4 = generateColor(199, 108, 63);
    public static final @NonNull Color woodColor5 = generateColor(189, 148, 118);

    private static @NonNull Color generateColor(int r, int g, int b) {
        return new Color(r / 255f, g / 255f, b / 255f, 1f);
    }
}
