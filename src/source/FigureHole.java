//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.J3DObjectDXF;
import jlib.JGraphicsEngine;
import jlib.JMatrix;
import jlib.JVec3;

public class FigureHole extends Figure {
    final float HOLE_RANGE = 0.15F;
    final float FAN_RANGE = 0.2F;
    public Figure m_FigureParent = null;
    private JMatrix m_matLocal;
    private J3DObjectDXF m_ModelHole;
    static JVec3 vTemp = new JVec3();
    static JVec3 vTemp2 = new JVec3();
    static JVec3 vTemp3 = new JVec3();
    static JMatrix m_matTranslate = new JMatrix();

    public FigureHole() {
        super(3);
    }

    public boolean Initialize(ResourceManager var1, Figure var2) {
        super.Initialize(var1);
        this.m_FigureParent = var2;
        this.m_ModelHole = var1.m_ModelHole;
        this.m_matLocal = new JMatrix();
        this.m_matLocal.Identity();
        this.m_vPosition.Set(this.m_FigureParent.m_vPosition);
        JVec3 var10000 = this.m_vPosition;
        var10000.y += 0.5F;
        return true;
    }

    void SetFan(boolean var1) {
        if (var1) {
            this.m_iID = 4;
        } else {
            this.m_iID = 3;
        }

    }

    boolean IsFan() {
        return this.m_iID == 4;
    }

    public boolean IsOn(JVec3 var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        JVec3 var5 = vTemp;
        JVec3 var6 = vTemp2;
        var5.Set(var1.x, 0.0F, var1.z);
        var6.Set(var2.x, 0.0F, var2.z);
        vTemp3.Set(var5);
        vTemp3.Set(var6);
        float var7 = vTemp3.Magnitude();
        float var8;
        if (this.m_iID == 3) {
            var8 = 0.15F;
        } else {
            var8 = 0.2F;
        }

        return var7 > var8 || !this.IsVisible();
    }

    boolean IsVisible() {
        GraphicsEngine var1 = this.m_RM.m_GraphicsEngine;
        var1.m_bVisiblePart = false;
        var1.m_bWriting = false;
        vTemp.Set(this.m_vPosition);
        JVec3 var10000 = vTemp;
        var10000.y += 0.05F;
        this.m_matLocal.SetTranslate(vTemp);
        this.m_matLocal.Multiply(var1.m_matWorld);
        this.m_ModelHole.Render(var1, this.m_matLocal, this.m_iID, this);
        var1.m_bWriting = true;
        return var1.m_bVisiblePart;
    }

    public boolean SetOn(JVec3 var1, JMatrix var2, boolean var3) {
        return true;
    }

    public void Update(JGraphicsEngine var1) {
    }

    public void Render(JGraphicsEngine var1) {
        this.m_matLocal.SetTranslate(this.m_vPosition);
        this.m_matLocal.Multiply(var1.m_matWorld);
        this.m_ModelHole.Render(var1, this.m_matLocal, this.m_iID, this);
    }

    public void Render(JGraphicsEngine var1, float var2) {
        m_matTranslate.SetTranslate(this.m_vPosition);
        this.m_matLocal.SetScale(var2);
        this.m_matLocal.Multiply(m_matTranslate);
        this.m_matLocal.Multiply(var1.m_matWorld);
        this.m_ModelHole.Render(var1, this.m_matLocal, this.m_iID, this);
    }
}
