//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.util.Vector;
import jlib.JFace;
import jlib.JGraphicsEngine;
import jlib.JVec3;

public class FigureCastManager extends Vector {
    public ResourceManager m_RM;
    JVec3 vTemp = new JVec3();

    public FigureCastManager() {
    }

    public boolean Initialize(ResourceManager var1) {
        this.m_RM = var1;
        return true;
    }

    public void Add(JGraphicsEngine var1, int var2, int var3) {
        int var4 = var1.GetDataID(var2, var3);
        if (var4 == -1) {
            FigureCast var5 = new FigureCast();
            if (var1.ScreenToWorld(this.m_RM.m_FaceDoubleView.face[0], var2, var3, this.vTemp)) {
                var5.Initialize(this.m_RM, this.vTemp);
                this.addElement(var5);
                this.m_RM.m_SoundAdd.play();
            }
        } else if (var4 == 1) {
            int var8 = var1.GetDataIDSub(var2, var3);
            JFace var6 = ((FaceDoubleBlock)var1.GetDataObject(var2, var3)).face[0];
            if (var8 == 1) {
                FigureCast var7 = new FigureCast();
                if (var1.ScreenToWorld(var6, var2, var3, this.vTemp)) {
                    var7.Initialize(this.m_RM, this.vTemp);
                    this.addElement(var7);
                    this.m_RM.m_SoundAdd.play();
                }
            }
        }

    }

    public void Delete(FigureCast var1) {
        this.removeElement(var1);
        var1 = null;
    }

    public void DeleteAll() {
        this.removeAllElements();
    }

    public void CheckDelete() {
        for(int var1 = 0; var1 < this.size(); ++var1) {
            FigureCast var2 = (FigureCast)this.get(var1);
            if (var2.IsDelete()) {
                this.Delete(var2);
                --var1;
            }
        }

    }

    public void Update(JGraphicsEngine var1) {
        int var3 = this.size();

        for(int var2 = 0; var2 < var3; ++var2) {
            ((FigureCast)this.get(var2)).Update(var1);
        }

    }

    public void Render(JGraphicsEngine var1) {
        int var3 = this.size();

        for(int var2 = 0; var2 < var3; ++var2) {
            ((FigureCast)this.get(var2)).Render(var1);
        }

    }

    public void Render(JGraphicsEngine var1, float var2) {
        int var4 = this.size();

        for(int var3 = 0; var3 < var4; ++var3) {
            ((FigureCast)this.get(var3)).Render(var1, var2);
        }

    }
}
