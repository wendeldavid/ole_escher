//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JVec4 {
    public float x;
    public float y;
    public float z;
    public float w;
    static JVec4 vTemp = new JVec4();

    public JVec4() {
        this.x = this.y = this.z = 0.0F;
        this.w = 1.0F;
    }

    public JVec4(float var1, float var2, float var3) {
        this.x = var1;
        this.y = var2;
        this.z = var3;
        this.w = 1.0F;
    }

    public JVec4(JVec3 var1) {
        this.x = var1.x;
        this.y = var1.y;
        this.z = var1.z;
        this.w = 1.0F;
    }

    public void Set(float var1, float var2, float var3) {
        this.x = var1;
        this.y = var2;
        this.z = var3;
        this.w = 1.0F;
    }

    public void Set(JVec3 var1) {
        this.x = var1.x;
        this.y = var1.y;
        this.z = var1.z;
        this.w = 1.0F;
    }

    public void Plus(JVec3 var1) {
        this.x += var1.x;
        this.y += var1.y;
        this.z += var1.z;
    }

    public void Minus(JVec3 var1) {
        this.x -= var1.x;
        this.y -= var1.y;
        this.z -= var1.z;
    }

    public void Multiply(JMatrix var1) {
        vTemp.x = this.x * var1._11 + this.y * var1._21 + this.z * var1._31 + this.w * var1._41;
        vTemp.y = this.x * var1._12 + this.y * var1._22 + this.z * var1._32 + this.w * var1._42;
        vTemp.z = this.x * var1._13 + this.y * var1._23 + this.z * var1._33 + this.w * var1._43;
        vTemp.w = this.x * var1._14 + this.y * var1._24 + this.z * var1._34 + this.w * var1._44;
        this.x = vTemp.x;
        this.y = vTemp.y;
        this.z = vTemp.z;
        this.w = vTemp.w;
    }

    public void Slerp(JVec4 var1, JVec4 var2, float var3) {
        float var4 = var1.x * var2.x + var1.y * var2.y + var1.z * var2.z + var1.w * var2.w;
        if (var4 < 0.0F) {
            var4 = -var4;
            var2.x = -var2.x;
            var2.y = -var2.y;
            var2.z = -var2.z;
            var2.w = -var2.w;
        }

        float var5 = 1.0F - var3;
        if (1.0F - var4 > 0.001F) {
            float var6 = (float)Math.acos((double)var4);
            var5 = (float)(Math.sin((double)(var6 * var5)) / Math.sin((double)var6));
            var3 = (float)(Math.sin((double)(var6 * var3)) / Math.sin((double)var6));
        }

        this.x = var5 * var1.x + var3 * var2.x;
        this.y = var5 * var1.y + var3 * var2.y;
        this.z = var5 * var1.z + var3 * var2.z;
        this.w = var5 * var1.w + var3 * var2.w;
    }

    public boolean SetMatrix(JMatrix var1) {
        float var2 = (float)Math.sqrt((double)(var1._11 + var1._22 + var1._33 + var1._44));
        if (var2 < 1.0E-5F) {
            return false;
        } else {
            this.x = (var1._23 - var1._32) / (2.0F * var2);
            this.y = (var1._31 - var1._13) / (2.0F * var2);
            this.z = (var1._12 - var1._21) / (2.0F * var2);
            this.w = 0.5F * var2;
            return true;
        }
    }

    public void GetAngle(JVec3 var1, float var2) {
        var2 = (float)Math.acos((double)this.w) * 2.0F;
        var1.x = this.x / (float)Math.sin((double)(var2 / 2.0F));
        var1.y = this.y / (float)Math.sin((double)(var2 / 2.0F));
        var1.z = this.z / (float)Math.sin((double)(var2 / 2.0F));
    }

    public void SetAngle(JVec3 var1, float var2) {
        this.x = (float)Math.sin((double)(var2 / 2.0F)) * var1.x;
        this.y = (float)Math.sin((double)(var2 / 2.0F)) * var1.y;
        this.z = (float)Math.sin((double)(var2 / 2.0F)) * var1.z;
        this.w = (float)Math.cos((double)(var2 / 2.0F));
    }
}
