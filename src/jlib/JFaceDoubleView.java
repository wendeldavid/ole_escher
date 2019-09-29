//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.awt.Color;

public class JFaceDoubleView extends JFaceDouble {
    JVec3[] m_vCamera = new JVec3[4];
    JMatrix m_matTranlate = new JMatrix();
    JMatrix m_matWorldInv = new JMatrix();
    JVec4 m_v4Temp = new JVec4();
    JVec4[] m_vV4 = new JVec4[4];

    public JFaceDoubleView() {
        for(int var1 = 0; var1 < 4; ++var1) {
            this.m_vCamera[var1] = new JVec3();
            this.m_vV4[var1] = new JVec4();
        }

    }

    public void Update(JGraphicsEngine var1, JVec3 var2) {
        this.m_vCamera[0].Set(1.0F, 1.0F, 0.0F);
        this.m_vCamera[1].Set(1.0F, -1.0F, 0.0F);
        this.m_vCamera[2].Set(-1.0F, -1.0F, 0.0F);
        this.m_vCamera[3].Set(-1.0F, 1.0F, 0.0F);
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

        this.CalcNormal();
        this.CalcD();
        this.CalcAreaCoordinate();
    }

    public void Render(JGraphicsEngine var1) {
        for(int var2 = 0; var2 < 4; ++var2) {
            this.m_vV4[var2].Set(this.p[var2]);
            this.m_vV4[var2].Multiply(var1.m_matTotal);
        }

        var1.m_OffScreenGraphics.setColor(Color.black);
        var1.m_OffScreenGraphics.drawLine((int)this.m_vV4[0].x, (int)this.m_vV4[0].y, (int)this.m_vV4[1].x, (int)this.m_vV4[1].y);
        var1.m_OffScreenGraphics.drawLine((int)this.m_vV4[1].x, (int)this.m_vV4[1].y, (int)this.m_vV4[2].x, (int)this.m_vV4[2].y);
        var1.m_OffScreenGraphics.drawLine((int)this.m_vV4[2].x, (int)this.m_vV4[2].y, (int)this.m_vV4[3].x, (int)this.m_vV4[3].y);
        var1.m_OffScreenGraphics.drawLine((int)this.m_vV4[3].x, (int)this.m_vV4[3].y, (int)this.m_vV4[0].x, (int)this.m_vV4[0].y);
    }
}
