//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import jlib.JEvent;
import jlib.JGraphicsEngine;

public class Icon {
    JEvent m_Event;
    Applet m_Applet;
    Image m_ImageOn;
    Image m_ImageOff;
    public Rectangle m_Rect = new Rectangle();

    public Icon() {
    }

    public boolean Initialize(Applet var1, URL var2, Class var3, String var4, String var5, JEvent var6) {
        Toolkit var7 = var1.getToolkit();
        Object var8 = null;
        URL var9 = var3.getResource(var4);
        URL var10 = var3.getResource(var5);

        try {
            this.m_ImageOn = var7.createImage((ImageProducer)var9.getContent());
            this.m_ImageOff = var7.createImage((ImageProducer)var10.getContent());
        } catch (IOException var12) {
        }

        this.m_Applet = var1;
        this.m_Event = var6;
        this.m_Rect.width = 36;
        this.m_Rect.height = 36;
        return true;
    }

    public JEvent GetEvent() {
        return this.m_Event;
    }

    public void SetPosition(int var1, int var2) {
        this.m_Rect.x = var1;
        this.m_Rect.y = var2;
    }

    public Image GetImageOn() {
        return this.m_ImageOn;
    }

    public Image GetImageOff() {
        return this.m_ImageOff;
    }

    public void Update() {
    }

    public void RenderOn(JGraphicsEngine var1) {
        var1.m_OffScreenGraphics.drawImage(this.m_ImageOn, this.m_Rect.x, this.m_Rect.y, this.m_Applet);
    }

    public void RenderOff(JGraphicsEngine var1) {
        var1.m_OffScreenGraphics.drawImage(this.m_ImageOff, this.m_Rect.x, this.m_Rect.y, this.m_Applet);
    }
}
