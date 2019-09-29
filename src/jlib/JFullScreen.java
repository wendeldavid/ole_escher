//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import javax.swing.JFrame;

public class JFullScreen {
    GraphicsDevice m_GraphicsDevice;
    DisplayMode m_DisplayMode;

    public JFullScreen() {
    }

    public boolean Do(JFrame var1, int var2, int var3, int var4) {
        GraphicsEnvironment var5 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.m_GraphicsDevice = var5.getDefaultScreenDevice();
        GraphicsConfiguration var6 = this.m_GraphicsDevice.getDefaultConfiguration();
        this.m_DisplayMode = this.m_GraphicsDevice.getDisplayMode();
        DisplayMode var7 = new DisplayMode(640, 480, 32, 0);

        try {
            this.m_GraphicsDevice.setFullScreenWindow(var1);
            this.m_GraphicsDevice.setDisplayMode(var7);
            return true;
        } catch (Exception var9) {
            this.m_GraphicsDevice.setFullScreenWindow((Window)null);
            return false;
        }
    }
}
