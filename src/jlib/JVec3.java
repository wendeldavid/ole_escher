//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JVec3 {
    public float x;
    public float y;
    public float z;
    static JVec3 vTemp = new JVec3();

    public JVec3() {
        this.x = this.y = this.z = 0.0F;
    }

    public JVec3(float var1, float var2, float var3) {
        this.x = var1;
        this.y = var2;
        this.z = var3;
    }

    public void Set(float var1, float var2, float var3) {
        this.x = var1;
        this.y = var2;
        this.z = var3;
    }

    public void Set(JVec3 var1) {
        this.x = var1.x;
        this.y = var1.y;
        this.z = var1.z;
    }

    public boolean Normalize() {
        float var1 = (float)Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
        if (var1 == 0.0F) {
            return false;
        } else {
            this.x /= var1;
            this.y /= var1;
            this.z /= var1;
            return true;
        }
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

    public void Multiply(float var1) {
        this.x *= var1;
        this.y *= var1;
        this.z *= var1;
    }

    public void Multiply(JVec3 var1) {
        this.x *= var1.x;
        this.y *= var1.y;
        this.z *= var1.z;
    }

    public void Divide(float var1) {
        this.x /= var1;
        this.y /= var1;
        this.z /= var1;
    }

    public void Divide(JVec3 var1) {
        this.x /= var1.x;
        this.y /= var1.y;
        this.z /= var1.z;
    }

    public float SquareMagnitude() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public float Magnitude() {
        return (float)Math.sqrt((double)this.SquareMagnitude());
    }

    public float DotProduct(JVec3 var1) {
        return this.x * var1.x + this.y * var1.y + this.z * var1.z;
    }

    public void CrossProduct(JVec3 var1) {
        vTemp.x = this.y * var1.z - this.z * var1.y;
        vTemp.y = this.z * var1.x - this.x * var1.z;
        vTemp.z = this.x * var1.y - this.y * var1.x;
        this.x = vTemp.x;
        this.y = vTemp.y;
        this.z = vTemp.z;
    }

    public void MatrixMultiply(JMatrix var1) {
        vTemp.x = var1._11 * this.x + var1._12 * this.y + var1._13 * this.z;
        vTemp.y = var1._21 * this.x + var1._22 * this.y + var1._23 * this.z;
        vTemp.z = var1._31 * this.x + var1._32 * this.y + var1._33 * this.z;
        this.x = vTemp.x;
        this.y = vTemp.y;
        this.z = vTemp.z;
    }
}
