//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

public class JFaceDoubleViewY extends JFaceDoubleView {
    public JFaceDoubleViewY() {
    }

    public void Update(JGraphicsEngine var1, JVec3 var2) {
        this.m_vCamera[0].Set(1.0F, 0.0F, 0.0F);
        this.m_vCamera[1].Set(1.0F, 0.0F, 0.0F);
        this.m_vCamera[2].Set(-1.0F, 0.0F, 0.0F);
        this.m_vCamera[3].Set(-1.0F, 0.0F, 0.0F);
        this.m_matTranlate.Set(var1.m_matView);
        this.m_matTranlate.Invert();
        this.m_matWorldInv.Set(var1.m_matWorld);
        this.m_matWorldInv.Invert();
        this.m_matTranlate.Multiply(this.m_matWorldInv);

        for(int var3 = 0; var3 < 4; ++var3) {
            this.m_v4Temp.x = this.m_vCamera[var3].x;
            this.m_v4Temp.y = this.m_vCamera[var3].y;
            this.m_v4Temp.z = this.m_vCamera[var3].z;
            this.m_v4Temp.w = 1.0F;
            this.m_matTranlate._41 = var2.x;
            this.m_matTranlate._42 = var2.y;
            this.m_matTranlate._43 = var2.z;
            this.m_v4Temp.Multiply(this.m_matTranlate);
            this.p[var3].x = this.m_v4Temp.x / this.m_v4Temp.w;
            this.p[var3].y = this.m_v4Temp.y / this.m_v4Temp.w;
            this.p[var3].z = this.m_v4Temp.z / this.m_v4Temp.w;
        }

        this.p[0].y -= 100.0F;
        this.p[1].y += 100.0F;
        this.p[2].y += 100.0F;
        this.p[3].y -= 100.0F;
        this.CalcNormal();
        this.CalcD();
        this.CalcAreaCoordinate();
    }
}
