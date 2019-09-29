//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class JResourceManager {
    public JResourceManager() {
    }

    public static Cursor CreateNullCursor() {
        BufferedImage var0 = new BufferedImage(16, 16, 6);
        Graphics2D var1 = var0.createGraphics();
        var1.setColor(new Color(0, 0, 0, 0));
        var1.fillRect(0, 0, 16, 16);
        var1.dispose();
        return Toolkit.getDefaultToolkit().createCustomCursor(var0, new Point(0, 0), "null_cursor");
    }
}
