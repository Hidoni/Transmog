package com.hidoni.transmog;

public class MathUtils {
    public static float angleWithinBounds(float rotation) {
        return (float) Math.IEEEremainder(rotation, Math.PI * 2F);
    }
}
