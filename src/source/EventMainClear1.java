//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.awt.Color;
import jlib.JResourceManager;

public class EventMainClear1 extends EventMain {
    static final int MAX_COUNT = 30;
    protected int m_iCount;

    public EventMainClear1(JResourceManager var1) {
        super(5, var1);
    }

    public void UpdateDataClick() {
    }

    public void UpdateDataDrag() {
    }

    public void Reset() {
        this.m_iCount = 0;
    }

    public void Update() {
        ResourceManager var1 = (ResourceManager)this.m_ResourceManager;
        if (this.m_iCount == 0) {
            var1.m_FigureBlockManager.ResetBlockFace();
        }

        ++this.m_iCount;
        if (this.m_iCount > 30) {
            var1.m_FigureCastManager.DeleteAll();
            var1.m_FigureBlockManager.DeleteAll();
            if (!var1.m_bApplet) {
                System.exit(0);
                return;
            }

            Icon var2 = var1.m_IconManager.GetIcon(0);
            var1.m_IconManager.SetCurrentIcon(var2);
            var1.m_EventManager.SetCurrentEvent(var2.GetEvent());
        }

    }

    public void Render() {
        ResourceManager var3 = (ResourceManager)this.m_ResourceManager;
        int var4 = var3.m_GraphicsEngine.m_iWidth;
        int var5 = var3.m_GraphicsEngine.m_iHeight;
        float var6 = 1.0F - (float)this.m_iCount / 30.0F;
        if (!var3.m_bLoaded) {
            var3.m_GraphicsEngine.Clear();
            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.white);
            var3.m_GraphicsEngine.m_OffScreenGraphics.fillRect(0, 0, var4, var5);
            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.black);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawString("Now Loading...", 24, 24);
        } else {
            var3.m_GraphicsEngine.Clear();
            var3.m_FigureBlockManager.Render(var3.m_GraphicsEngine, var6);
            var3.m_FigureCastManager.Render(var3.m_GraphicsEngine, var6);
            var3.m_GraphicsEngine.DrawEdge();
            var3.m_IconManager.Render(var3.m_GraphicsEngine);
            if (var3.m_bApplet && var3.m_bMouseEnter) {
                var3.m_GraphicsEngine.m_OffScreenGraphics.drawImage(var3.m_ImageCursor, var3.m_iCursorX, var3.m_iCursorY, var3.m_Applet);
            }

            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.lightGray);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(0, 0, var4 - 1, 0);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(var4 - 1, 0, var4 - 1, var5 - 1);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(var4 - 1, var5 - 1, 0, var5 - 1);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(0, var5 - 1, 0, 0);
        }
    }
}
