//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JGraphicsEngine;
import jlib.JVec3;

public class Sight {
    private int m_iLength;
    private int m_iFigureSize;
    private Figure[] m_Figure;

    public Sight() {
    }

    int GetLength() {
        return this.m_iLength;
    }

    int GetFigureSize() {
        return this.m_iFigureSize;
    }

    Figure GetFigure(int var1) {
        return this.m_Figure[var1];
    }

    public boolean Initialize(int var1) {
        this.m_iLength = var1;
        this.m_Figure = new Figure[this.m_iLength * 2 * this.m_iLength * 2];
        return this.m_Figure != null;
    }

    int Setup(JGraphicsEngine var1, JVec3 var2) {
        this.m_iFigureSize = 0;
        int var8 = (int)var2.x - this.m_iLength;
        int var9 = (int)var2.y - this.m_iLength;
        int var10 = (int)var2.x + this.m_iLength;
        int var11 = (int)var2.y + this.m_iLength;
        if (var8 < 0) {
            var8 = 0;
        }

        if (var9 < 0) {
            var9 = 0;
        }

        if (var10 >= var1.m_iWidth) {
            var10 = var1.m_iWidth - 1;
        }

        if (var11 >= var1.m_iHeight) {
            var11 = var1.m_iHeight - 1;
        }

        for(int var7 = var9; var7 < var11; ++var7) {
            for(int var6 = var8; var6 < var10; ++var6) {
                Object var3 = null;
                int var5 = var1.GetDataID(var6, var7);
                switch(var5) {
                    case 1:
                        FaceDoubleBlock var4 = (FaceDoubleBlock)var1.GetDataObject(var6, var7);
                        var3 = (FigureBlock)var4.m_Figure;
                    case 2:
                    default:
                        break;
                    case 3:
                    case 4:
                        var3 = (Figure)var1.GetDataObject(var6, var7);
                }

                if (var3 != null) {
                    this.Attach((Figure)var3);
                }
            }
        }

        return this.m_iFigureSize;
    }

    private void Attach(Figure var1) {
        int var3 = this.m_iFigureSize;

        for(int var2 = 0; var2 < var3; ++var2) {
            if (this.GetFigure(var2) == var1) {
                return;
            }
        }

        this.m_Figure[var3] = var1;
        var1.m_bAlive = false;
        ++this.m_iFigureSize;
    }
}
