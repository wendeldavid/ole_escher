//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.awt.Color;
import jlib.JFaceDouble;
import jlib.JGraphicsEngine;
import jlib.JMatrix;
import jlib.JVec3;
import jlib.JVec4;

public class FaceDoubleBlock extends JFaceDouble {
    public int m_iID = 0;
    public int m_iDirection = 0;
    public Figure m_Figure;
    public boolean m_bActive = true;
    public FaceDoubleBlock m_FaceLink = null;
    static JVec4 vV41 = new JVec4();
    static JVec4 vV42 = new JVec4();
    static JVec4 vV43 = new JVec4();
    static JVec4 vV44 = new JVec4();
    JVec3[] vV = new JVec3[3];

    public FaceDoubleBlock() {
    }

    boolean Initialize(int var1, int var2, Figure var3) {
        this.m_iID = var1;
        this.m_iDirection = var2;
        this.m_Figure = var3;

        for(int var4 = 0; var4 < 3; ++var4) {
            this.vV[var4] = new JVec3();
        }

        return true;
    }

    public void Update() {
    }

    public void Render(JGraphicsEngine var1) {
        if (this.m_bActive) {
            if (this.face[0].n.DotProduct(var1.m_vCameraNormal) <= 0.0F) {
                var1.m_OffScreenGraphics.setColor(Color.black);
                JMatrix var2 = var1.m_matTotal;
                vV41.x = this.p[0].x;
                vV41.y = this.p[0].y;
                vV41.z = this.p[0].z;
                vV41.w = 1.0F;
                vV42.x = this.p[1].x;
                vV42.y = this.p[1].y;
                vV42.z = this.p[1].z;
                vV42.w = 1.0F;
                vV43.x = this.p[2].x;
                vV43.y = this.p[2].y;
                vV43.z = this.p[2].z;
                vV43.w = 1.0F;
                vV44.x = this.p[3].x;
                vV44.y = this.p[3].y;
                vV44.z = this.p[3].z;
                vV44.w = 1.0F;
                vV41.Multiply(var2);
                vV42.Multiply(var2);
                vV43.Multiply(var2);
                vV44.Multiply(var2);
                this.vV[0].x = vV41.x;
                this.vV[0].y = vV41.y;
                this.vV[0].z = vV41.z;
                this.vV[1].x = vV42.x;
                this.vV[1].y = vV42.y;
                this.vV[1].z = vV42.z;
                this.vV[2].x = vV43.x;
                this.vV[2].y = vV43.y;
                this.vV[2].z = vV43.z;
                var1.ZBuffer(this.vV, this.m_iID, this.m_iDirection, this);
                this.vV[0].x = vV41.x;
                this.vV[0].y = vV41.y;
                this.vV[0].z = vV41.z;
                this.vV[1].x = vV43.x;
                this.vV[1].y = vV43.y;
                this.vV[1].z = vV43.z;
                this.vV[2].x = vV44.x;
                this.vV[2].y = vV44.y;
                this.vV[2].z = vV44.z;
                var1.ZBuffer(this.vV, this.m_iID, this.m_iDirection, this);
            }
        }
    }

    public void RenderWorking(JGraphicsEngine var1) {
        if (this.m_bActive) {
            var1.m_OffScreenGraphics.setColor(Color.black);
            JMatrix var2 = var1.m_matTotal;
            vV41.x = this.p[0].x;
            vV41.y = this.p[0].y;
            vV41.z = this.p[0].z;
            vV41.w = 1.0F;
            vV42.x = this.p[1].x;
            vV42.y = this.p[1].y;
            vV42.z = this.p[1].z;
            vV42.w = 1.0F;
            vV43.x = this.p[2].x;
            vV43.y = this.p[2].y;
            vV43.z = this.p[2].z;
            vV43.w = 1.0F;
            vV44.x = this.p[3].x;
            vV44.y = this.p[3].y;
            vV44.z = this.p[3].z;
            vV44.w = 1.0F;
            vV41.Multiply(var2);
            vV42.Multiply(var2);
            vV43.Multiply(var2);
            vV44.Multiply(var2);
            this.vV[0].x = vV41.x;
            this.vV[0].y = vV41.y;
            this.vV[0].z = vV41.z;
            this.vV[1].x = vV42.x;
            this.vV[1].y = vV42.y;
            this.vV[1].z = vV42.z;
            this.vV[2].x = vV43.x;
            this.vV[2].y = vV43.y;
            this.vV[2].z = vV43.z;
            var1.ZBuffer(this.vV, this.m_iID, this.m_iDirection, this);
            this.vV[0].x = vV41.x;
            this.vV[0].y = vV41.y;
            this.vV[0].z = vV41.z;
            this.vV[1].x = vV43.x;
            this.vV[1].y = vV43.y;
            this.vV[1].z = vV43.z;
            this.vV[2].x = vV44.x;
            this.vV[2].y = vV44.y;
            this.vV[2].z = vV44.z;
            var1.ZBuffer(this.vV, this.m_iID, this.m_iDirection, this);
        }
    }
}
