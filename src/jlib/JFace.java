//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JFace {
    public JVec3 n = new JVec3();
    public JVec3[] p = new JVec3[3];
    float area;
    float d;
    static JVec3 vTemp = new JVec3();
    static JVec3 vTemp1 = new JVec3();
    static JVec3 vTemp2 = new JVec3();

    public JFace() {
        this.p[0] = null;
        this.p[1] = null;
        this.p[2] = null;
    }

    public JFace(JVec3 var1, JVec3 var2, JVec3 var3) {
        this.p[0] = var1;
        this.p[1] = var2;
        this.p[2] = var3;
    }

    public void CalcNormal() {
        JVec3 var1 = this.p[0];
        JVec3 var2 = this.p[1];
        JVec3 var3 = this.p[2];
        float var4 = var1.x - var2.x;
        float var5 = var1.y - var2.y;
        float var6 = var1.z - var2.z;
        float var7 = var3.x - var2.x;
        float var8 = var3.y - var2.y;
        float var9 = var3.z - var2.z;
        float var10000 = var3.x - var1.x;
        var10000 = var3.y - var1.y;
        var10000 = var3.z - var1.z;
        this.n.x = var5 * var9 - var6 * var8;
        this.n.y = var6 * var7 - var4 * var9;
        this.n.z = var4 * var8 - var5 * var7;
        float var13 = (float)Math.sqrt((double)(this.n.x * this.n.x + this.n.y * this.n.y + this.n.z * this.n.z));
        if (var13 != 0.0F) {
            JVec3 var14 = this.n;
            var14.x /= var13;
            var14 = this.n;
            var14.y /= var13;
            var14 = this.n;
            var14.z /= var13;
        }

    }

    public void CalcD() {
        this.d = this.p[0].x * this.n.x + this.p[0].y * this.n.y + this.p[0].z * this.n.z;
        this.d *= -1.0F;
    }

    public void CalcAreaCoordinate() {
        vTemp.Set(this.p[0]);
        vTemp.Minus(this.p[1]);
        JVec3 var1 = vTemp;
        vTemp1.Set(this.p[1]);
        vTemp1.Minus(this.p[2]);
        JVec3 var2 = vTemp1;
        vTemp2.Set(var1);
        vTemp2.CrossProduct(var2);
        this.area = vTemp2.Magnitude();
    }

    public float CalcArea(JVec3 var1) {
        return var1.x * this.n.x + var1.y * this.n.y + var1.z * this.n.z + this.d;
    }

    public float GetDistancePointToFace(JVec3 var1) {
        vTemp.x = this.p[0].x - var1.x;
        vTemp.y = this.p[0].y - var1.y;
        vTemp.z = this.p[0].z - var1.z;
        return vTemp.DotProduct(this.n);
    }

    public boolean GetCrossPointLineToFace(JVec3 var1, JVec3 var2, JVec3 var3) {
        float var4 = this.n.DotProduct(var2);
        if (var4 == 0.0F) {
            return false;
        } else {
            float var5 = this.GetDistancePointToFace(var3) / var4;
            var1.x = var2.x * var5 + var3.x;
            var1.y = var2.y * var5 + var3.y;
            var1.z = var2.z * var5 + var3.z;
            return true;
        }
    }

    public boolean GetCrossPointStripToFace(JVec3 var1, JVec3 var2, JVec3 var3) {
        JVec3 var4 = vTemp;
        var4.x = var3.x - var2.x;
        var4.y = var3.y - var2.y;
        var4.z = var3.z - var2.z;
        float var5 = this.n.DotProduct(var4);
        if (var5 == 0.0F) {
            return false;
        } else {
            float var6 = this.GetDistancePointToFace(var2) / var5;
            var1.x = var4.x * var6 + var2.x;
            var1.y = var4.y * var6 + var2.y;
            var1.z = var4.z * var6 + var2.z;
            if (var6 < 0.0F) {
                return false;
            } else {
                return var6 <= 1.0F;
            }
        }
    }

    public boolean PtInFace(JVec3 var1) {
        for(int var2 = 0; var2 < 3; ++var2) {
            vTemp1.Set(var1);
            vTemp1.Minus(this.p[var2]);
            vTemp.Set(vTemp1);
            vTemp2.Set(this.p[var2 + 1 == 3 ? 0 : var2 + 1]);
            vTemp2.Set(this.p[var2]);
            vTemp1.CrossProduct(vTemp2);
            vTemp1.Multiply(this.n);
            if (vTemp1.x < -1.0E-4F || vTemp1.y < -1.0E-4F || vTemp1.z < -1.0E-4F) {
                return false;
            }
        }

        return true;
    }
}
