//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JEventManager;
import jlib.JResourceManager;

public class EventManager extends JEventManager {
    static final int ID_EVENT_MAIN_BLOCK = 0;
    static final int ID_EVENT_MAIN_CAST = 1;
    static final int ID_EVENT_MAIN_DELETE = 2;
    static final int ID_EVENT_MAIN_HOLE = 3;
    static final int ID_EVENT_MAIN_ROTATE = 4;
    static final int ID_EVENT_MAIN_CLEAR1 = 5;

    public EventManager() {
    }

    public boolean Initialize(JResourceManager var1) {
        super.Initialize(var1);
        this.m_vecEvent.addElement(this.m_EventCurrent = new EventMainCast(var1));
        this.m_vecEvent.addElement(new EventMainBlock(var1));
        this.m_vecEvent.addElement(new EventMainHole(var1));
        this.m_vecEvent.addElement(new EventMainDelete(var1));
        this.m_vecEvent.addElement(new EventMainRotate(var1));
        this.m_vecEvent.addElement(new EventMainClear1(var1));
        return true;
    }
}
