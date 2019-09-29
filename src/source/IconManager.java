//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.applet.Applet;
import java.net.URL;
import java.util.Vector;
import jlib.JEvent;
import jlib.JGraphicsEngine;

public class IconManager extends Vector {
    public ResourceManager m_RM;
    protected Applet m_App;
    protected URL m_BaseURL;
    protected Class m_BaseClass;
    protected int m_iWidth;
    protected int m_iHeight;
    protected Icon m_IconCurrent = null;
    private int m_iSpace = 4;

    public IconManager() {
    }

    public boolean Initialize(ResourceManager var1, Applet var2, URL var3, Class var4, int var5, int var6) {
        this.m_RM = var1;
        this.m_App = var2;
        this.m_BaseURL = var3;
        this.m_BaseClass = var4;
        this.m_iWidth = var5;
        this.m_iHeight = var6;
        return true;
    }

    public Icon GetIcon(int var1) {
        return (Icon)this.get(var1);
    }

    public void SetCurrentIcon(Icon var1) {
        this.m_IconCurrent = var1;
    }

    public Icon Add(String var1, String var2, JEvent var3) {
        Icon var4 = new Icon();
        var4.Initialize(this.m_App, this.m_BaseURL, this.m_BaseClass, var1, var2, var3);
        var4.SetPosition(this.m_iWidth - var4.m_Rect.width - this.m_iSpace, (var4.m_Rect.height + this.m_iSpace) * this.size() + this.m_iSpace);
        this.addElement(var4);
        return var4;
    }

    public Icon Add2(String var1, String var2, JEvent var3) {
        Icon var4 = new Icon();
        var4.Initialize(this.m_App, this.m_BaseURL, this.m_BaseClass, var1, var2, var3);
        var4.SetPosition(this.m_iSpace, this.m_iHeight - (var4.m_Rect.height + this.m_iSpace));
        this.addElement(var4);
        return var4;
    }

    public boolean Update(int var1, int var2) {
        int var4 = this.size();

        for(int var3 = 0; var3 < var4; ++var3) {
            Icon var5 = (Icon)this.get(var3);
            if (var5.m_Rect.contains(var1, var2)) {
                var5.GetEvent().Reset();
                this.m_RM.m_EventManager.SetCurrentEvent(var5.GetEvent());
                this.m_IconCurrent = var5;
                this.m_RM.m_SoundSelected.play();
                return true;
            }
        }

        return false;
    }

    public void Render(JGraphicsEngine var1) {
        int var3 = this.size();

        for(int var2 = 0; var2 < var3; ++var2) {
            Icon var4 = (Icon)this.get(var2);
            var4.Update();
            if (this.m_IconCurrent == var4) {
                var4.RenderOn(var1);
            } else {
                var4.RenderOff(var1);
            }
        }

    }
}
