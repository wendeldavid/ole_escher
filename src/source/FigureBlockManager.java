//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.util.Vector;
import jlib.JFace;
import jlib.JGraphicsEngine;
import jlib.JVec3;

public class FigureBlockManager extends Vector {
    public ResourceManager m_RM;
    JVec3 vTemp = new JVec3();
    static final float BLOCK_SIZE = 1.0F;
    static JVec3[] vPLink = new JVec3[6];
    static int[] iLink = new int[6];

    public FigureBlockManager() {
    }

    public boolean Initialize(ResourceManager var1) {
        this.m_RM = var1;

        for(int var2 = 0; var2 < 6; ++var2) {
            vPLink[var2] = new JVec3();
        }

        iLink[0] = 1;
        iLink[1] = 0;
        iLink[2] = 3;
        iLink[3] = 2;
        iLink[4] = 5;
        iLink[5] = 4;
        return true;
    }

    public void Add(JGraphicsEngine var1, int var2, int var3) {
        int var4 = var1.GetDataID(var2, var3);
        if (var4 == -1) {
            FigureBlock var5 = new FigureBlock();
            var5.Initialize(this.m_RM);
            if (var1.ScreenToWorldGrid(this.m_RM.m_FaceDoubleView.face[0], var2, var3, this.vTemp, 1.0F)) {
                var5.SetPosition(this.vTemp.x, this.vTemp.y, this.vTemp.z);
                if (this.m_RM.m_GridSpaceManager.Attatch(var5, var5.GetPosition()) != null) {
                    this.ChainLink(var5);
                    this.addElement(var5);
                    this.m_RM.m_SoundAdd.play();
                    this.m_RM.m_FaceDoubleXZ.Update(var1, var5.m_vPosition);
                }
            }
        } else if (var4 == 1) {
            FaceDoubleBlock var9 = (FaceDoubleBlock)var1.GetDataObject(var2, var3);
            JFace var6 = var9.face[0];
            FigureBlock var7 = new FigureBlock();
            var7.Initialize(this.m_RM);
            this.vTemp.Set(var9.m_Figure.GetPosition());
            var7.SetPosition(this.vTemp.x - var6.n.x * 1.0F, this.vTemp.y - var6.n.y * 1.0F, this.vTemp.z - var6.n.z * 1.0F);
            if (this.m_RM.m_GridSpaceManager.Attatch(var7, var7.GetPosition()) != null) {
                this.ChainLink(var7);
                this.addElement(var7);
                this.m_RM.m_SoundAdd.play();
                this.m_RM.m_FaceDoubleXZ.Update(var1, var7.m_vPosition);
            }
        }

    }

    public void Add(JVec3 var1) {
        GridSpace var2 = this.m_RM.m_GridSpaceManager.GetSpace(var1);
        if (var2 != null) {
            if (var2.m_Figure == null) {
                FigureBlock var3 = new FigureBlock();
                var3.Initialize(this.m_RM);
                var3.SetPosition(var1.x, var1.y, var1.z);
                if (this.m_RM.m_GridSpaceManager.Attatch(var3, var3.GetPosition()) != null) {
                    this.ChainLink(var3);
                    this.addElement(var3);
                    this.m_RM.m_SoundAdd.play();
                }

            }
        }
    }

    public void Delete(FigureBlock var1) {
        for(int var2 = 0; var2 < 6; ++var2) {
            if (var1.m_Face[var2].m_FaceLink != null) {
                var1.m_Face[var2].m_FaceLink.m_bActive = true;
                var1.m_Face[var2].m_FaceLink.m_FaceLink = null;
            }
        }

        this.m_RM.m_GridSpaceManager.Release(var1.GetPosition());
        var1.m_FigureSub = null;
        this.removeElement(var1);
        var1 = null;
    }

    public void DeleteAll() {
        for(int var1 = 0; var1 < this.size(); ++var1) {
            FigureBlock var2 = (FigureBlock)this.get(var1);
            this.Delete(var2);
            --var1;
        }

    }

    public void ResetBlockFace() {
        int var2 = this.size();

        for(int var1 = 0; var1 < var2; ++var1) {
            FigureBlock var3 = (FigureBlock)this.get(var1);
            var3.ResetFace();
        }

    }

    void ChainLink(FigureBlock var1) {
        int var3 = this.size();
        FigureBlock var4 = null;
        JVec3 var6 = var1.GetPosition();
        float var7 = 1.0F;
        vPLink[0].Set(var6.x, var6.y - var7, var6.z);
        vPLink[1].Set(var6.x, var6.y + var7, var6.z);
        vPLink[2].Set(var6.x, var6.y, var6.z - var7);
        vPLink[3].Set(var6.x, var6.y, var6.z + var7);
        vPLink[4].Set(var6.x - var7, var6.y, var6.z);
        vPLink[5].Set(var6.x + var7, var6.y, var6.z);

        for(int var2 = 0; var2 < 6; ++var2) {
            GridSpace var8 = this.m_RM.m_GridSpaceManager.GetSpace(vPLink[var2]);
            if (var8 != null) {
                Figure var5 = var8.m_Figure;
                if (var5 != null && var5.m_iID == 1) {
                    var4 = (FigureBlock)var8.m_Figure;
                    if (var4 != null) {
                        var1.m_Face[var2].m_bActive = false;
                        var1.m_Face[var2].m_FaceLink = var4.m_Face[iLink[var2]];
                        var4.m_Face[iLink[var2]].m_bActive = false;
                        var4.m_Face[iLink[var2]].m_FaceLink = var1.m_Face[var2];
                    }
                }
            }
        }

    }

    public void Update(JGraphicsEngine var1) {
        int var3 = this.size();

        for(int var2 = 0; var2 < var3; ++var2) {
            ((FigureBlock)this.get(var2)).Update(var1);
        }

    }

    public void Render(JGraphicsEngine var1) {
        int var3 = this.size();

        for(int var2 = 0; var2 < var3; ++var2) {
            ((FigureBlock)this.get(var2)).Render(var1);
        }

    }

    public void Render(JGraphicsEngine var1, float var2) {
        int var4 = this.size();

        for(int var3 = 0; var3 < var4; ++var3) {
            ((FigureBlock)this.get(var3)).Render(var1, var2);
        }

    }
}
