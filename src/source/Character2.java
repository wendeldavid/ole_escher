//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.net.URL;
import java.util.Vector;
import jlib.J3DObjectDXF;
import jlib.JGraphicsEngine;
import jlib.JMatrix;

public class Character2 {
    Vector m_vecJ3DObjectDXF;
    JMatrix m_matLocal = new JMatrix();
    int m_iAnimeSize = 16;

    public Character2() {
    }

    public boolean Initialize(URL var1, Class var2) {
        this.m_vecJ3DObjectDXF = new Vector();

        for(int var5 = 0; var5 <= this.m_iAnimeSize; ++var5) {
            J3DObjectDXF var3 = new J3DObjectDXF();
            String var4 = "data/character" + var5 + ".dxf";
            var3.Initialize(var1, var2, var4);
            this.m_vecJ3DObjectDXF.addElement(var3);
        }

        return true;
    }

    public void Render(JGraphicsEngine var1, JMatrix var2, int var3, Object var4, int var5) {
        this.m_matLocal.Set(var2);
        this.m_matLocal.Multiply(var1.m_matWorld);
        ((J3DObjectDXF)this.m_vecJ3DObjectDXF.get(var5)).Render(var1, this.m_matLocal, var3, var4);
    }
}
