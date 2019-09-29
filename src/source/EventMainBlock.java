//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JResourceManager;
import jlib.JVec3;

public class EventMainBlock extends EventMain {
    JVec3 m_vStartPos = new JVec3();
    JVec3 m_vEndPos = new JVec3();
    JVec3 m_vTemp = new JVec3();
    JVec3 m_vTemp2 = new JVec3();
    JVec3 m_vTemp3 = new JVec3();

    public EventMainBlock(JResourceManager var1) {
        super(0, var1);
    }

    public void UpdateDataClick() {
        ResourceManager var1 = (ResourceManager)this.m_ResourceManager;
        var1.m_FigureBlockManager.Add(var1.m_GraphicsEngine, var1.m_iCursorX, var1.m_iCursorY);
        var1.m_GraphicsEngine.ScreenToWorldGrid(var1.m_FaceDoubleXZ.face[0], var1.m_iCursorX, var1.m_iCursorY, this.m_vStartPos, 1.0F);
    }

    public void UpdateDataDrag() {
    }
}
