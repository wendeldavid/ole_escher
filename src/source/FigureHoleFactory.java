//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JGraphicsEngine;
import jlib.JVec3;

public class FigureHoleFactory {
    public ResourceManager m_RM;
    static JVec3 vTemp = new JVec3();

    public FigureHoleFactory() {
    }

    public boolean Initialize(ResourceManager var1) {
        this.m_RM = var1;
        return true;
    }

    FigureHole Add(FigureBlock var1) {
        FigureHole var2 = new FigureHole();
        if (var2 == null) {
            return null;
        } else if (!var2.Initialize(this.m_RM, var1)) {
            return null;
        } else {
            var1.m_FigureSub = var2;
            JVec3 var3 = vTemp;
            var3.Set(var1.m_vPosition);
            var3.y += 0.5F;
            var2.m_vPosition.Set(var3);
            JVec3 var4 = vTemp;
            var4.Set(var1.m_vPosition);
            --var4.y;
            GridSpace var5 = this.m_RM.m_GridSpaceManager.GetSpace(var4);
            if (var5 == null) {
                return null;
            } else {
                Figure var6 = var5.m_Figure;
                if (var6 != null && var6.m_iID == 1 && var6.m_FigureSub == null) {
                    this.Add((FigureBlock)var6);
                }

                return var2;
            }
        }
    }

    public FigureHole Add(JGraphicsEngine var1, int var2, int var3) {
        int var4 = var1.GetDataID(var2, var3);
        if (var4 == 1) {
            FaceDoubleBlock var5 = (FaceDoubleBlock)var1.GetDataObject(var2, var3);
            FigureBlock var6 = (FigureBlock)var5.m_Figure;
            if (var6.m_FigureSub == null && var5.m_iDirection == 1) {
                FigureHole var7 = this.Add(var6);
                this.m_RM.m_SoundAdd.play();
                return var7;
            }
        } else {
            FigureHole var8;
            if (var4 == 3) {
                var8 = (FigureHole)var1.GetDataObject(var2, var3);
                var8.SetFan(true);
            } else if (var4 == 4) {
                var8 = (FigureHole)var1.GetDataObject(var2, var3);
                var8.SetFan(false);
            }
        }

        return null;
    }

    public boolean Delete(FigureHole var1) {
        var1.m_FigureParent.m_FigureSub = null;
        var1 = null;
        return false;
    }
}
