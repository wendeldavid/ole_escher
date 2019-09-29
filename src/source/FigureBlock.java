//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JGraphicsEngine;
import jlib.JMatrix;
import jlib.JVec3;
import jlib.JVec4;

public class FigureBlock extends Figure {
    final float DROP_RANGE = 0.25F;
    private JMatrix m_matLocal;
    public FaceDoubleBlock[] m_Face;
    private JVec3[] m_vVertex;
    static JVec3 vTemp = new JVec3();
    static JVec3 vTemp2 = new JVec3();
    static JVec4 v4Temp = new JVec4();

    public FigureBlock() {
        super(1);
    }

    public boolean Initialize(ResourceManager var1) {
        super.Initialize(var1);
        this.m_matLocal = new JMatrix();
        this.m_matLocal.Identity();
        this.m_Face = new FaceDoubleBlock[6];

        int var2;
        for(var2 = 0; var2 < 6; ++var2) {
            this.m_Face[var2] = new FaceDoubleBlock();
            this.m_Face[var2].Initialize(this.m_iID, var2, this);
        }

        this.m_vVertex = new JVec3[8];

        for(var2 = 0; var2 < 8; ++var2) {
            this.m_vVertex[var2] = new JVec3();
        }

        this.m_vPosition.x = 0.0F;
        this.m_vPosition.y = 0.0F;
        this.m_vPosition.z = 0.0F;
        this.SetPosition(this.m_vPosition.x, this.m_vPosition.y, this.m_vPosition.z);
        return true;
    }

    public void SetPosition(float var1, float var2, float var3) {
        this.m_vPosition.x = var1;
        this.m_vPosition.y = var2;
        this.m_vPosition.z = var3;
        this.m_matLocal.SetTranslate(this.m_vPosition);
        float var4 = 0.5F * this.m_fScale;

        int var5;
        for(var5 = 0; var5 < 8; ++var5) {
            this.m_vVertex[var5].Set(this.m_vPosition);
        }

        JVec3 var10000 = this.m_vVertex[0];
        var10000.x -= var4;
        var10000 = this.m_vVertex[0];
        var10000.y -= var4;
        var10000 = this.m_vVertex[0];
        var10000.z -= var4;
        var10000 = this.m_vVertex[1];
        var10000.x += var4;
        var10000 = this.m_vVertex[1];
        var10000.y -= var4;
        var10000 = this.m_vVertex[1];
        var10000.z -= var4;
        var10000 = this.m_vVertex[2];
        var10000.x += var4;
        var10000 = this.m_vVertex[2];
        var10000.y -= var4;
        var10000 = this.m_vVertex[2];
        var10000.z += var4;
        var10000 = this.m_vVertex[3];
        var10000.x -= var4;
        var10000 = this.m_vVertex[3];
        var10000.y -= var4;
        var10000 = this.m_vVertex[3];
        var10000.z += var4;
        var10000 = this.m_vVertex[4];
        var10000.x -= var4;
        var10000 = this.m_vVertex[4];
        var10000.y += var4;
        var10000 = this.m_vVertex[4];
        var10000.z -= var4;
        var10000 = this.m_vVertex[5];
        var10000.x += var4;
        var10000 = this.m_vVertex[5];
        var10000.y += var4;
        var10000 = this.m_vVertex[5];
        var10000.z -= var4;
        var10000 = this.m_vVertex[6];
        var10000.x += var4;
        var10000 = this.m_vVertex[6];
        var10000.y += var4;
        var10000 = this.m_vVertex[6];
        var10000.z += var4;
        var10000 = this.m_vVertex[7];
        var10000.x -= var4;
        var10000 = this.m_vVertex[7];
        var10000.y += var4;
        var10000 = this.m_vVertex[7];
        var10000.z += var4;
        this.m_Face[0].p[0].Set(this.m_vVertex[0]);
        this.m_Face[0].p[1].Set(this.m_vVertex[1]);
        this.m_Face[0].p[2].Set(this.m_vVertex[2]);
        this.m_Face[0].p[3].Set(this.m_vVertex[3]);
        this.m_Face[1].p[0].Set(this.m_vVertex[7]);
        this.m_Face[1].p[1].Set(this.m_vVertex[6]);
        this.m_Face[1].p[2].Set(this.m_vVertex[5]);
        this.m_Face[1].p[3].Set(this.m_vVertex[4]);
        this.m_Face[2].p[0].Set(this.m_vVertex[4]);
        this.m_Face[2].p[1].Set(this.m_vVertex[5]);
        this.m_Face[2].p[2].Set(this.m_vVertex[1]);
        this.m_Face[2].p[3].Set(this.m_vVertex[0]);
        this.m_Face[3].p[0].Set(this.m_vVertex[6]);
        this.m_Face[3].p[1].Set(this.m_vVertex[7]);
        this.m_Face[3].p[2].Set(this.m_vVertex[3]);
        this.m_Face[3].p[3].Set(this.m_vVertex[2]);
        this.m_Face[4].p[0].Set(this.m_vVertex[0]);
        this.m_Face[4].p[1].Set(this.m_vVertex[3]);
        this.m_Face[4].p[2].Set(this.m_vVertex[7]);
        this.m_Face[4].p[3].Set(this.m_vVertex[4]);
        this.m_Face[5].p[0].Set(this.m_vVertex[5]);
        this.m_Face[5].p[1].Set(this.m_vVertex[6]);
        this.m_Face[5].p[2].Set(this.m_vVertex[2]);
        this.m_Face[5].p[3].Set(this.m_vVertex[1]);

        for(var5 = 0; var5 < 6; ++var5) {
            this.m_Face[var5].CalcNormal();
            this.m_Face[var5].CalcD();
            this.m_Face[var5].CalcAreaCoordinate();
        }

    }

    public void SetScale(float var1) {
        super.SetScale(var1);
        this.SetPosition(this.m_vPosition.x, this.m_vPosition.y, this.m_vPosition.z);
    }

    public boolean IsOn(JVec3 var1, JVec3 var2, JVec3 var3, JVec3 var4) {
        if (this.m_FigureSub != null && !this.m_FigureSub.IsOn(var1, var2, var3, var4)) {
            return false;
        } else {
            float var6 = var1.x - 0.5F;
            float var7 = var1.z - 0.5F;
            float var8 = var1.y + 0.5F;
            float var9 = var1.x + 0.5F;
            float var10 = var1.z + 0.5F;
            float var11 = var2.x - 0.25F;
            float var12 = var2.z - 0.25F;
            float var13 = var2.x - 0.25F;
            float var14 = var2.z + 0.25F;
            float var15 = var2.x + 0.25F;
            float var16 = var2.z + 0.25F;
            float var17 = var2.x + 0.25F;
            float var18 = var2.z - 0.25F;
            if (var11 >= var6 && var11 <= var9 && var8 >= var2.y - 0.1F && var8 <= var3.y + 0.1F && var12 >= var7 && var12 <= var10) {
                return true;
            } else if (var13 >= var6 && var13 <= var9 && var8 >= var2.y - 0.1F && var8 <= var3.y + 0.1F && var14 >= var7 && var14 <= var10) {
                return true;
            } else if (var15 >= var6 && var15 <= var9 && var8 >= var2.y - 0.1F && var8 <= var3.y + 0.1F && var16 >= var7 && var16 <= var10) {
                return true;
            } else {
                return var17 >= var6 && var17 <= var9 && var8 >= var2.y - 0.1F && var8 <= var3.y + 0.1F && var18 >= var7 && var18 <= var10;
            }
        }
    }

    public boolean SetOn(JVec3 var1, JMatrix var2, boolean var3) {
        JVec3 var4 = vTemp;
        vTemp2.Set(0.0F, 0.0F, -1.0F);
        vTemp2.MatrixMultiply(var2);
        if (this.m_Face[1].face[0].GetCrossPointLineToFace(var4, vTemp2, var1) && var4 != null) {
            var4.y = this.m_vPosition.y + 0.5F;
            var1.Set(var4);
            return true;
        } else {
            return false;
        }
    }

    public void ResetFace() {
        for(int var1 = 0; var1 < 6; ++var1) {
            this.m_Face[var1].m_bActive = true;
        }

    }

    public void Update(JGraphicsEngine var1) {
        JVec4 var2 = v4Temp;
        var2.Set(this.m_vPosition);
        var2.Multiply(this.m_RM.m_GraphicsEngine.m_matTotal);
        this.m_vPositionOnScreen.x = var2.x;
        this.m_vPositionOnScreen.y = var2.y;
        this.m_vPositionOnScreen.z = var2.z;
    }

    public void Render(JGraphicsEngine var1) {
        for(int var2 = 0; var2 < 6; ++var2) {
            this.m_Face[var2].Render(var1);
        }

        if (this.m_FigureSub != null) {
            this.m_FigureSub.Render(var1);
        }

    }

    public void Render(JGraphicsEngine var1, float var2) {
        this.SetScale(var2);

        for(int var3 = 0; var3 < 6; ++var3) {
            this.m_Face[var3].Render(var1);
        }

        if (this.m_FigureSub != null) {
            this.m_FigureSub.Render(var1, var2);
        }

    }

    public void RenderWorking(JGraphicsEngine var1) {
        this.m_Face[1].RenderWorking(var1);
    }
}
