//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JMatrix {
    public float _11;
    public float _12;
    public float _13;
    public float _14;
    public float _21;
    public float _22;
    public float _23;
    public float _24;
    public float _31;
    public float _32;
    public float _33;
    public float _34;
    public float _41;
    public float _42;
    public float _43;
    public float _44;
    static JVec3 vTemp = new JVec3();
    static JVec3 vTemp2 = new JVec3();
    static JVec3 vTemp3 = new JVec3();
    static JMatrix matTemp = new JMatrix();

    public JMatrix() {
    }

    public JMatrix(JMatrix var1) {
        this._11 = var1._11;
        this._12 = var1._12;
        this._13 = var1._13;
        this._14 = var1._14;
        this._21 = var1._21;
        this._22 = var1._22;
        this._23 = var1._23;
        this._24 = var1._24;
        this._31 = var1._31;
        this._32 = var1._32;
        this._33 = var1._33;
        this._34 = var1._34;
        this._41 = var1._41;
        this._42 = var1._42;
        this._43 = var1._43;
        this._44 = var1._44;
    }

    public void Zero() {
        this._11 = this._12 = this._13 = this._14 = this._21 = this._22 = this._23 = this._24 = 0.0F;
        this._31 = this._32 = this._33 = this._34 = this._41 = this._42 = this._43 = this._44 = 0.0F;
    }

    public void Identity() {
        this._12 = this._13 = this._14 = this._21 = this._23 = this._24 = 0.0F;
        this._31 = this._32 = this._34 = this._41 = this._42 = this._43 = 0.0F;
        this._11 = this._22 = this._33 = this._44 = 1.0F;
    }

    public JMatrix Multiply(JMatrix var1) {
        JMatrix var2 = matTemp;
        var2._11 = this._11 * var1._11 + this._12 * var1._21 + this._13 * var1._31 + this._14 * var1._41;
        var2._12 = this._11 * var1._12 + this._12 * var1._22 + this._13 * var1._32 + this._14 * var1._42;
        var2._13 = this._11 * var1._13 + this._12 * var1._23 + this._13 * var1._33 + this._14 * var1._43;
        var2._14 = this._11 * var1._14 + this._12 * var1._24 + this._13 * var1._34 + this._14 * var1._44;
        var2._21 = this._21 * var1._11 + this._22 * var1._21 + this._23 * var1._31 + this._24 * var1._41;
        var2._22 = this._21 * var1._12 + this._22 * var1._22 + this._23 * var1._32 + this._24 * var1._42;
        var2._23 = this._21 * var1._13 + this._22 * var1._23 + this._23 * var1._33 + this._24 * var1._43;
        var2._24 = this._21 * var1._14 + this._22 * var1._24 + this._23 * var1._34 + this._24 * var1._44;
        var2._31 = this._31 * var1._11 + this._32 * var1._21 + this._33 * var1._31 + this._34 * var1._41;
        var2._32 = this._31 * var1._12 + this._32 * var1._22 + this._33 * var1._32 + this._34 * var1._42;
        var2._33 = this._31 * var1._13 + this._32 * var1._23 + this._33 * var1._33 + this._34 * var1._43;
        var2._34 = this._31 * var1._14 + this._32 * var1._24 + this._33 * var1._34 + this._34 * var1._44;
        var2._41 = this._41 * var1._11 + this._42 * var1._21 + this._43 * var1._31 + this._44 * var1._41;
        var2._42 = this._41 * var1._12 + this._42 * var1._22 + this._43 * var1._32 + this._44 * var1._42;
        var2._43 = this._41 * var1._13 + this._42 * var1._23 + this._43 * var1._33 + this._44 * var1._43;
        var2._44 = this._41 * var1._14 + this._42 * var1._24 + this._43 * var1._34 + this._44 * var1._44;
        this._11 = var2._11;
        this._12 = var2._12;
        this._13 = var2._13;
        this._14 = var2._14;
        this._21 = var2._21;
        this._22 = var2._22;
        this._23 = var2._23;
        this._24 = var2._24;
        this._31 = var2._31;
        this._32 = var2._32;
        this._33 = var2._33;
        this._34 = var2._34;
        this._41 = var2._41;
        this._42 = var2._42;
        this._43 = var2._43;
        this._44 = var2._44;
        return this;
    }

    public void Set(JVec3 var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        this.Identity();
        this._11 = var1.x;
        this._12 = var1.y;
        this._13 = var1.z;
        this._21 = var2.x;
        this._22 = var2.y;
        this._23 = var2.z;
        this._31 = var3.x;
        this._32 = var3.y;
        this._33 = var3.z;
        this._41 = var4.x;
        this._42 = var4.y;
        this._43 = var4.z;
    }

    public void Set(JMatrix var1) {
        this._11 = var1._11;
        this._12 = var1._12;
        this._13 = var1._13;
        this._14 = var1._14;
        this._21 = var1._21;
        this._22 = var1._22;
        this._23 = var1._23;
        this._24 = var1._24;
        this._31 = var1._31;
        this._32 = var1._32;
        this._33 = var1._33;
        this._34 = var1._34;
        this._41 = var1._41;
        this._42 = var1._42;
        this._43 = var1._43;
        this._44 = var1._44;
    }

    public void SetTranslate(JVec3 var1) {
        this.Identity();
        this._41 = var1.x;
        this._42 = var1.y;
        this._43 = var1.z;
    }

    public void SetScale(float var1) {
        this.Identity();
        this._11 = var1;
        this._22 = var1;
        this._33 = var1;
    }

    public void SetScale(JVec3 var1) {
        this.Identity();
        this._11 = var1.x;
        this._22 = var1.y;
        this._33 = var1.z;
    }

    public void SetShear(JVec3 var1) {
        this.Identity();
        this._24 = var1.x;
        this._14 = var1.y;
        this._13 = var1.z;
    }

    public void SetRotateX(float var1) {
        this.Identity();
        this._22 = (float)Math.cos((double)var1);
        this._23 = (float)Math.sin((double)var1);
        this._32 = -((float)Math.sin((double)var1));
        this._33 = (float)Math.cos((double)var1);
    }

    public void SetRotateY(float var1) {
        this.Identity();
        this._11 = (float)Math.cos((double)var1);
        this._13 = -((float)Math.sin((double)var1));
        this._31 = (float)Math.sin((double)var1);
        this._33 = (float)Math.cos((double)var1);
    }

    public void SetRotateZ(float var1) {
        this.Identity();
        this._11 = (float)Math.cos((double)var1);
        this._12 = (float)Math.sin((double)var1);
        this._21 = -((float)Math.sin((double)var1));
        this._22 = (float)Math.cos((double)var1);
    }

    public void SetRotationAxis(JVec3 var1, float var2) {
        float var3 = (float)Math.cos((double)var2);
        float var4 = (float)Math.sin((double)var2);
        var1.Normalize();
        this._11 = var1.x * var1.x * (1.0F - var3) + var3;
        this._12 = var1.x * var1.y * (1.0F - var3) - var1.z * var4;
        this._13 = var1.x * var1.z * (1.0F - var3) + var1.y * var4;
        this._21 = var1.y * var1.x * (1.0F - var3) + var1.z * var4;
        this._22 = var1.y * var1.y * (1.0F - var3) + var3;
        this._23 = var1.y * var1.z * (1.0F - var3) - var1.x * var4;
        this._31 = var1.z * var1.x * (1.0F - var3) - var1.y * var4;
        this._32 = var1.z * var1.y * (1.0F - var3) + var1.x * var4;
        this._33 = var1.z * var1.z * (1.0F - var3) + var3;
        this._14 = this._24 = this._34 = 0.0F;
        this._41 = this._42 = this._43 = 0.0F;
        this._44 = 1.0F;
    }

    public void Invert() {
        JMatrix var1 = matTemp;
        float var2 = 1.0F / (this._11 * (this._22 * this._33 - this._23 * this._32) - this._12 * (this._21 * this._33 - this._23 * this._31) + this._13 * (this._21 * this._32 - this._22 * this._31));
        var1._11 = var2 * (this._22 * this._33 - this._23 * this._32);
        var1._12 = -var2 * (this._12 * this._33 - this._13 * this._32);
        var1._13 = var2 * (this._12 * this._23 - this._13 * this._22);
        var1._14 = 0.0F;
        var1._21 = -var2 * (this._21 * this._33 - this._23 * this._31);
        var1._22 = var2 * (this._11 * this._33 - this._13 * this._31);
        var1._23 = -var2 * (this._11 * this._23 - this._13 * this._21);
        var1._24 = 0.0F;
        var1._31 = var2 * (this._21 * this._32 - this._22 * this._31);
        var1._32 = -var2 * (this._11 * this._32 - this._12 * this._31);
        var1._33 = var2 * (this._11 * this._22 - this._12 * this._21);
        var1._34 = 0.0F;
        var1._41 = -(this._41 * var1._11 + this._42 * var1._21 + this._43 * var1._31);
        var1._42 = -(this._41 * var1._12 + this._42 * var1._22 + this._43 * var1._32);
        var1._43 = -(this._41 * var1._13 + this._42 * var1._23 + this._43 * var1._33);
        var1._44 = 1.0F;
        this._11 = var1._11;
        this._12 = var1._12;
        this._13 = var1._13;
        this._14 = var1._14;
        this._21 = var1._21;
        this._22 = var1._22;
        this._23 = var1._23;
        this._24 = var1._24;
        this._31 = var1._31;
        this._32 = var1._32;
        this._33 = var1._33;
        this._34 = var1._34;
        this._41 = var1._41;
        this._42 = var1._42;
        this._43 = var1._43;
        this._44 = var1._44;
    }

    public void SetTranspose(JMatrix var1) {
        this._11 = var1._11;
        this._12 = var1._21;
        this._13 = var1._31;
        this._21 = var1._12;
        this._22 = var1._22;
        this._23 = var1._32;
        this._31 = var1._13;
        this._32 = var1._23;
        this._33 = var1._33;
        this._14 = this._24 = this._34 = 0.0F;
        this._44 = 1.0F;
    }

    public boolean MatrixMultiply(JVec3 var1, JVec3 var2) {
        float var3 = var2.x * this._11 + var2.y * this._21 + var2.z * this._31 + this._41;
        float var4 = var2.x * this._12 + var2.y * this._22 + var2.z * this._32 + this._42;
        float var5 = var2.x * this._13 + var2.y * this._23 + var2.z * this._33 + this._43;
        float var6 = var2.x * this._14 + var2.y * this._24 + var2.z * this._34 + this._44;
        if (Math.abs(var6) < 1.0E-5F) {
            return false;
        } else {
            var1.x = var3 / var6;
            var1.y = var4 / var6;
            var1.z = var5 / var6;
            return true;
        }
    }

    public boolean SetViewMatrix(JVec3 var1, JVec3 var2, JVec3 var3) {
        vTemp3.x = var2.x - var1.x;
        vTemp3.y = var2.y - var1.y;
        vTemp3.z = var2.z - var1.z;
        JVec3 var4 = vTemp3;
        float var5 = var4.Magnitude();
        if (var5 < 1.0E-6F) {
            return false;
        } else {
            var4.Divide(var5);
            float var6 = var3.DotProduct(var4);
            vTemp.Set(var4);
            vTemp.Multiply(var6);
            vTemp2.Set(var3);
            vTemp2.Minus(vTemp);
            JVec3 var7 = vTemp2;
            if (1.0E-6F > (var5 = var7.Magnitude())) {
                var7.x = 0.0F;
                var7.x = 1.0F;
                var7.x = 0.0F;
                vTemp.Set(var4);
                vTemp.Multiply(var4.y);
                vTemp2.Set(var7);
                vTemp2.Minus(vTemp);
                var7 = vTemp2;
                if (1.0E-6F > (var5 = var7.Magnitude())) {
                    var7.x = 0.0F;
                    var7.x = 1.0F;
                    var7.x = 0.0F;
                    vTemp.Set(var4);
                    vTemp.Multiply(var4.z);
                    vTemp2.Set(var7);
                    vTemp2.Minus(vTemp);
                    var7 = vTemp2;
                    if (1.0E-6F > (var5 = var7.Magnitude())) {
                        return false;
                    }
                }
            }

            var7.Divide(var5);
            vTemp.Set(var7);
            vTemp.CrossProduct(var4);
            this.Identity();
            this._11 = vTemp.x;
            this._12 = var7.x;
            this._13 = var4.x;
            this._21 = vTemp.y;
            this._22 = var7.y;
            this._23 = var4.y;
            this._31 = vTemp.z;
            this._32 = var7.z;
            this._33 = var4.z;
            this._41 = -var1.DotProduct(vTemp);
            this._42 = -var1.DotProduct(var7);
            this._43 = -var1.DotProduct(var4);
            return true;
        }
    }

    public boolean SetProjection(float var1, float var2, float var3, float var4) {
        if (Math.abs(var4 - var3) < 0.01F) {
            return false;
        } else if (Math.abs(Math.sin((double)(var1 * 0.5F))) < 0.009999999776482582D) {
            return false;
        } else {
            float var5 = var2 * (float)(Math.cos((double)(var1 * 0.5F)) / Math.sin((double)(var1 * 0.5F)));
            float var6 = 1.0F * (float)(Math.cos((double)(var1 * 0.5F)) / Math.sin((double)(var1 * 0.5F)));
            float var7 = var4 / (var4 - var3);
            this.Zero();
            this._11 = var5;
            this._22 = var6;
            this._33 = var7;
            this._34 = 1.0F;
            this._43 = -var7 * var3;
            return true;
        }
    }

    public void SetFrustumMatrix(float var1, float var2, float var3, float var4, float var5, float var6) {
        float var7 = var3 - var1;
        float var8 = var2 - var4;
        float var9 = var6 - var5;
        this.Zero();
        this._11 = 2.0F * var5 / var7;
        this._22 = 2.0F * var5 / var8;
        this._33 = -(var6 + var5) / var9;
        this._31 = (var3 + var1) / var7;
        this._32 = (var2 + var4) / var8;
        this._34 = -1.0F;
        this._41 = 0.0F;
        this._42 = 0.0F;
        this._44 = -2.0F * var5 * var6 / var9;
    }

    public void SetOrthoMatrix(float var1, float var2, float var3, float var4, float var5, float var6) {
        float var7 = var3 - var1;
        float var8 = var2 - var4;
        float var9 = var6 - var5;
        this.Identity();
        this._11 = 2.0F / var7;
        this._22 = 2.0F / var8;
        this._33 = -2.0F / var9;
        this._41 = (var3 + var1) / var7;
        this._42 = (var2 + var4) / var8;
        this._43 = (var6 + var5) / var9;
    }

    public void SetScreenMatrix(int var1, int var2, int var3, int var4, float var5, float var6) {
        this.Identity();
        this._11 = (float)(var3 / 2);
        this._22 = (float)(-var4 / 2);
        this._33 = var6 - var5;
        this._41 = (float)(var1 + var3 / 2);
        this._42 = (float)(var2 + var4 / 2);
        this._43 = var5;
    }

    public void SetLookAtLH(JVec3 var1, JVec3 var2, JVec3 var3) {
        vTemp3.Set(var2);
        vTemp3.Minus(var1);
        JVec3 var4 = vTemp3;
        var4.Normalize();
        vTemp.Set(var3);
        vTemp.CrossProduct(var4);
        JVec3 var5 = vTemp;
        vTemp2.Set(var4);
        vTemp2.CrossProduct(var5);
        JVec3 var6 = vTemp2;
        this.Identity();
        this._11 = var5.x;
        this._12 = var6.x;
        this._13 = var4.x;
        this._21 = var5.y;
        this._22 = var6.y;
        this._23 = var4.y;
        this._31 = var5.z;
        this._32 = var6.z;
        this._33 = var4.z;
        this._41 = -var5.DotProduct(var1);
        this._42 = -var6.DotProduct(var1);
        this._43 = -var4.DotProduct(var1);
    }

    public void SetLookAtRH(JVec3 var1, JVec3 var2, JVec3 var3) {
        vTemp3.Set(var2);
        vTemp3.Minus(var1);
        JVec3 var4 = vTemp3;
        var4.Normalize();
        vTemp.Set(var3);
        vTemp.CrossProduct(var4);
        JVec3 var5 = vTemp;
        vTemp2.Set(var4);
        vTemp2.CrossProduct(var5);
        JVec3 var6 = vTemp2;
        this.Identity();
        this._11 = var5.x;
        this._12 = var6.x;
        this._13 = var4.x;
        this._21 = var5.y;
        this._22 = var6.y;
        this._23 = var4.y;
        this._31 = var5.z;
        this._32 = var6.z;
        this._33 = var4.z;
        this._41 = -var5.DotProduct(var1);
        this._42 = -var6.DotProduct(var1);
        this._43 = -var4.DotProduct(var1);
    }
}
