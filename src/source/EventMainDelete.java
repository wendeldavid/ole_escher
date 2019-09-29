//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JResourceManager;

public class EventMainDelete extends EventMain {
    public EventMainDelete(JResourceManager var1) {
        super(2, var1);
    }

    public void UpdateDataClick() {
        boolean var1 = false;
        ResourceManager var2 = (ResourceManager)this.m_ResourceManager;
        int var3 = var2.m_GraphicsEngine.GetDataID(var2.m_iCursorX, var2.m_iCursorY);
        if (var3 == 2) {
            FigureCast var4 = (FigureCast)var2.m_GraphicsEngine.GetDataObject(var2.m_iCursorX, var2.m_iCursorY);
            var2.m_FigureCastManager.Delete(var4);
            var2.m_SoundDelete.play();
            var1 = true;
        } else if (var3 == 1) {
            FaceDoubleBlock var7 = (FaceDoubleBlock)var2.m_GraphicsEngine.GetDataObject(var2.m_iCursorX, var2.m_iCursorY);
            FigureBlock var5 = (FigureBlock)var7.m_Figure;
            var2.m_FigureBlockManager.Delete(var5);
            var2.m_SoundDelete.play();
            var1 = true;
        } else if (var3 == 3 || var3 == 4) {
            FigureHole var8 = (FigureHole)var2.m_GraphicsEngine.GetDataObject(var2.m_iCursorX, var2.m_iCursorY);
            var2.m_FigureHoleFactory.Delete(var8);
            var2.m_SoundDelete.play();
            var1 = true;
        }

        if (var1) {
            int var10 = var2.m_FigureCastManager.size();

            for(int var9 = 0; var9 < var10; ++var9) {
                FigureCast var6 = (FigureCast)var2.m_FigureCastManager.get(var9);
                var6.ReleaseMyGoal();
            }
        }

    }
}
