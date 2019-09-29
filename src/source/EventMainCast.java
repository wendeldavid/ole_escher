//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JResourceManager;

public class EventMainCast extends EventMain {
    public EventMainCast(JResourceManager var1) {
        super(1, var1);
    }

    public void UpdateDataClick() {
        ResourceManager var1 = (ResourceManager)this.m_ResourceManager;
        var1.m_FigureCastManager.Add(var1.m_GraphicsEngine, var1.m_iCursorX, var1.m_iCursorY);
    }
}
