//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JResourceManager;
import jlib.JVec3;

public class GridSpaceManager {
    public JResourceManager m_RM;
    public GridSpace[] m_GridSpace;
    public static final int GRID_SIZE = 64;

    GridSpaceManager() {
    }

    boolean Initialize(JResourceManager var1) {
        this.m_RM = var1;
        int var2 = 262144;
        this.m_GridSpace = new GridSpace[var2];

        int var3;
        for(var3 = 0; var3 < var2; ++var3) {
            this.m_GridSpace[var3] = new GridSpace();
        }

        for(var3 = 0; var3 < 64; ++var3) {
            for(int var4 = 0; var4 < 64; ++var4) {
                for(int var5 = 0; var5 < 64; ++var5) {
                    GridSpace var6 = this.GetSpace(var5, var4, var3);
                    var6.m_iX = var5;
                    var6.m_iY = var4;
                    var6.m_iZ = var3;
                }
            }
        }

        return true;
    }

    GridSpace GetSpace(JVec3 var1) {
        float var2 = 32.5F;
        float var3 = var1.x + var2;
        float var4 = var1.y + var2;
        float var5 = var1.z + var2;
        int var6 = (int)var3;
        int var7 = (int)var4;
        int var8 = (int)var5;
        return this.GetSpace(var6, var7, var8);
    }

    GridSpace GetSpace(int var1, int var2, int var3) {
        if (var1 < 0) {
            return null;
        } else if (var2 < 0) {
            return null;
        } else if (var3 < 0) {
            return null;
        } else if (var1 >= 64) {
            return null;
        } else if (var2 >= 64) {
            return null;
        } else {
            return var3 >= 64 ? null : this.m_GridSpace[var3 + var2 * 64 + var1 * 64 * 64];
        }
    }

    JVec3 NormalizePosition(JVec3 var1) {
        float var2 = 32.5F;
        float var3 = var1.x + var2;
        float var4 = var1.y + var2;
        float var5 = var1.z + var2;
        int var6 = (int)var3;
        int var7 = (int)var4;
        int var8 = (int)var5;
        var1.x = var3 - (float)var6 - 0.5F;
        var1.y = var4 - (float)var7 - 0.5F;
        var1.z = var5 - (float)var8 - 0.5F;
        return var1;
    }

    void RasterizatePosition(JVec3 var1) {
        float var2 = 32.5F;
        float var3 = var1.x + var2;
        float var4 = var1.y + var2;
        float var5 = var1.z + var2;
        byte var6 = 32;
        var1.x = (float)((int)var3 - var6);
        var1.y = (float)((int)var4 - var6);
        var1.z = (float)((int)var5 - var6);
    }

    Figure GetFigure(JVec3 var1) {
        GridSpace var2 = this.GetSpace(var1);
        return var2 != null ? var2.m_Figure : null;
    }

    Figure GetFigure(int var1, int var2, int var3) {
        GridSpace var4 = this.GetSpace(var1, var2, var3);
        return var4 != null ? var4.m_Figure : null;
    }

    GridSpace Attatch(Figure var1, JVec3 var2) {
        GridSpace var3 = this.GetSpace(var2);
        if (var3 != null) {
            var3.m_Figure = var1;
        }

        return var3;
    }

    void Release(JVec3 var1) {
        GridSpace var2 = this.GetSpace(var1);
        if (var2 != null) {
            var2.m_Figure = null;
        }

    }
}
