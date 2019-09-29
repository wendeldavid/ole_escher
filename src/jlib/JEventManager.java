//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.util.Vector;

public class JEventManager {
    protected Vector m_vecEvent;
    protected JResourceManager m_ResourceManager;
    protected JEvent m_EventCurrent = null;

    public JEventManager() {
    }

    public boolean Initialize(JResourceManager var1) {
        this.m_ResourceManager = var1;
        this.m_vecEvent = new Vector();
        return true;
    }

    public Vector GetEvents() {
        return this.m_vecEvent;
    }

    public JEvent GetCurrentEvent() {
        return this.m_EventCurrent;
    }

    public void SetCurrentEvent(int var1) {
        this.m_EventCurrent = (JEvent)this.m_vecEvent.get(var1);
    }

    public void SetCurrentEvent(JEvent var1) {
        this.m_EventCurrent = var1;
    }

    public void Update() {
        this.m_EventCurrent.Update();
    }

    public void Render() {
        this.m_EventCurrent.Render();
    }
}
