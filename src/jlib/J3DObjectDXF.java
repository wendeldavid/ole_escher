//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import jlib.J3DObject.Group;

public class J3DObjectDXF extends J3DObject {
    public J3DObjectDXF() {
    }

    public boolean Initialize(String var1) {
        super.Initialize(var1);

        try {
            BufferedReader var2 = new BufferedReader(new FileReader(var1));
            this.Initialize(var2);
        } catch (Exception var3) {
        }

        return true;
    }

    public boolean Initialize(Class var1, String var2) {
        super.Initialize(var2);
        URL var3 = var1.getResource(var2);

        try {
            InputStream var4 = null;
            var4 = var3.openStream();
            BufferedReader var5 = new BufferedReader(new InputStreamReader(var4));
            this.Initialize(var5);
            return true;
        } catch (IOException var6) {
            return this.Initialize(var2);
        }
    }

    public boolean Initialize(URL var1, Class var2, String var3) {
        return this.Initialize(var2, var3);
    }

    boolean Initialize(BufferedReader var1) {
        while(true) {
            try {
                String var2;
                if ((var2 = var1.readLine()) != null) {
                    if (var2.equals("3DFACE")) {
                        JVertex var4 = new JVertex();
                        JFace var5 = new JFace();
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        Group var3 = this.GetGroup(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.x = Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.z = -Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.y = Float.valueOf(var2);
                        var5.p[0] = var3.GetExistedPoint(var4);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.x = Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.z = -Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.y = Float.valueOf(var2);
                        var5.p[1] = var3.GetExistedPoint(var4);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.x = Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.z = -Float.valueOf(var2);
                        var2 = var1.readLine();
                        var2 = var1.readLine();
                        var4.y = Float.valueOf(var2);
                        var5.p[2] = var3.GetExistedPoint(var4);
                        var5.CalcNormal();
                        var5.CalcD();
                        var5.CalcAreaCoordinate();
                        var3.m_vecFace.add(var5);
                    }
                    continue;
                }
            } catch (Exception var6) {
            }

            return true;
        }
    }
}
