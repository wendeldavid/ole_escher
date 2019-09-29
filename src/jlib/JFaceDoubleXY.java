//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JFaceDoubleXY extends JFaceDouble {
    public JFaceDoubleXY() {
    }

    public void Update(JGraphicsEngine var1, JVec3 var2) {
        for(int var3 = 0; var3 < 4; ++var3) {
            this.p[var3].Set(var2);
        }

        JVec3 var10000 = this.p[0];
        var10000.x += 100.0F;
        var10000 = this.p[0];
        var10000.y += 100.0F;
        var10000 = this.p[1];
        var10000.x -= 100.0F;
        var10000 = this.p[1];
        var10000.y += 100.0F;
        var10000 = this.p[2];
        var10000.x -= 100.0F;
        var10000 = this.p[2];
        var10000.y -= 100.0F;
        var10000 = this.p[3];
        var10000.x += 100.0F;
        var10000 = this.p[3];
        var10000.y -= 100.0F;
        this.CalcNormal();
        this.CalcD();
        this.CalcAreaCoordinate();
    }
}
