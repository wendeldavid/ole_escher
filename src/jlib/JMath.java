//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JMath {
    public static final float PIE = 3.141592F;
    public static final float FAR = 999999.0F;
    public static final float EPSILON = 1.0E-5F;

    public JMath() {
    }

    public static float Radian(float var0) {
        return var0 * 3.141592F / 180.0F;
    }

    public static float GetAngle(float var0, float var1) {
        float var2 = (float)Math.acos((double)var0 / Math.sqrt((double)(var0 * var0 + var1 * var1)));
        var2 = var2 / 3.141592F * 180.0F;
        if (var1 < 0.0F) {
            var2 = 360.0F - var2;
        }

        return var2;
    }
}
