//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JFaceDouble {
    public JFace[] face = new JFace[2];
    public JVec3[] p = new JVec3[4];

    public JFaceDouble() {
        this.p[0] = new JVec3();
        this.p[1] = new JVec3();
        this.p[2] = new JVec3();
        this.p[3] = new JVec3();
        this.face[0] = new JFace(this.p[0], this.p[1], this.p[2]);
        this.face[1] = new JFace(this.p[0], this.p[2], this.p[3]);
    }

    public void CalcNormal() {
        this.face[0].CalcNormal();
        this.face[1].CalcNormal();
    }

    public void CalcD() {
        this.face[0].CalcD();
        this.face[1].CalcD();
    }

    public void CalcAreaCoordinate() {
        this.face[0].CalcAreaCoordinate();
        this.face[1].CalcAreaCoordinate();
    }

    public float CalcArea(int var1, JVec3 var2) {
        return this.face[var1].CalcArea(var2);
    }

    public float GetDistancePointToFace(int var1, JVec3 var2) {
        return this.face[var1].GetDistancePointToFace(var2);
    }

    public boolean GetCrossPointLineToFace(int var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        return this.face[var1].GetCrossPointLineToFace(var2, var3, var4);
    }

    public boolean GetCrossPointStripToFace(int var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        return this.face[var1].GetCrossPointStripToFace(var2, var3, var4);
    }

    public boolean PtInFace(JVec3 var1) {
        for(int var2 = 0; var2 < 2; ++var2) {
            if (this.face[var2].PtInFace(var1)) {
                return true;
            }
        }

        return false;
    }
}
