//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JGraphicsEngine;
import jlib.JMatrix;
import jlib.JVec3;

public class Figure {
    public static final int ID_NULL = 0;
    public static final int ID_BLOCK = 1;
    public static final int ID_CAST = 2;
    public static final int ID_HOLE = 3;
    public static final int ID_FAN = 4;
    public static final int ID_STAIRS = 5;
    public int m_iID;
    protected JVec3 m_vPosition = new JVec3();
    protected float m_fAngle = 0.0F;
    public float m_fScale = 1.0F;
    public boolean m_bAlive = true;
    public ResourceManager m_RM;
    public Figure m_FigureSub = null;
    public JVec3 m_vPositionOnScreen = new JVec3();

    public Figure(int var1) {
        this.m_iID = var1;
    }

    public boolean Initialize(ResourceManager var1) {
        this.m_RM = var1;
        return true;
    }

    public JVec3 GetPosition() {
        return this.m_vPosition;
    }

    public void SetPosition(float var1, float var2, float var3) {
        this.m_vPosition.x = var1;
        this.m_vPosition.y = var2;
        this.m_vPosition.z = var3;
    }

    public void SetScale(float var1) {
        this.m_fScale = var1;
    }

    public boolean IsOn(JVec3 var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        return false;
    }

    public boolean SetOn(JVec3 var1, JMatrix var2, boolean var3) {
        var1.y = this.m_vPosition.y + 0.5F;
        return true;
    }

    static int GetDirectionFromAngle(float var0) {
        boolean var1 = false;
        byte var2;
        if (var0 > 45.0F && var0 <= 135.0F) {
            var2 = 0;
        } else if (var0 > 135.0F && var0 <= 225.0F) {
            var2 = 1;
        } else if (var0 > 225.0F && var0 <= 315.0F) {
            var2 = 2;
        } else {
            var2 = 3;
        }

        return var2;
    }

    static float GetAngleFromDirection(int var0) {
        float var1 = 0.0F;
        switch(var0) {
            case 0:
                var1 = 90.0F;
                break;
            case 1:
                var1 = 180.0F;
                break;
            case 2:
                var1 = 270.0F;
                break;
            case 3:
                var1 = 0.0F;
        }

        return var1;
    }

    public void Update(JGraphicsEngine var1) {
    }

    public void Render(JGraphicsEngine var1) {
    }

    public void Render(JGraphicsEngine var1, float var2) {
    }

    public void RenderWorking(JGraphicsEngine var1) {
    }
}
