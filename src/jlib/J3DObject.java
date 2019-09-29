//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.util.Vector;

public class J3DObject {
    Vector m_vecGroup;
    int m_iGroupID = 0;

    public J3DObject() {
    }

    public boolean Initialize(String var1) {
        this.m_vecGroup = new Vector();
        return true;
    }

    public void Render(JGraphicsEngine var1, JMatrix var2, int var3, int var4, Object var5) {
        for(int var6 = 0; var6 < this.m_vecGroup.size(); ++var6) {
            Group var7 = (Group)this.m_vecGroup.get(var6);
            if (var7.m_bRoot) {
                var7.Render(var1, var2, var3, var4, var5);
            }
        }

        var1.CameraNormalReset();
    }

    public void Render(JGraphicsEngine var1, JMatrix var2, int var3, Object var4) {
        for(int var5 = 0; var5 < this.m_vecGroup.size(); ++var5) {
            Group var6 = (Group)this.m_vecGroup.get(var5);
            if (var6.m_bRoot) {
                var6.Render(var1, var2, var3, var6.m_iID, var4);
            }
        }

        var1.CameraNormalReset();
    }

    public Group GetGroup(String var1) {
        for(int var2 = 0; var2 < this.m_vecGroup.size(); ++var2) {
            Group var3 = (Group)this.m_vecGroup.get(var2);
            if (var3.m_strName.equals(var1)) {
                return var3;
            }
        }

        Group var4 = new Group();
        var4.Initialize(this);
        var4.m_iID = this.m_iGroupID;
        var4.m_strName = new String(var1);
        this.m_vecGroup.add(var4);
        ++this.m_iGroupID;
        return var4;
    }

    public Group GetGroup(int var1) {
        for(int var2 = 0; var2 < this.m_vecGroup.size(); ++var2) {
            Group var3 = (Group)this.m_vecGroup.get(var2);
            if (var3.m_iID == var1) {
                return var3;
            }
        }

        return null;
    }

    public class Group {
        int m_iID = -1;
        public Vector m_vecVertex;
        public Vector m_vecFace;
        public String m_strName;
        boolean m_bRoot = true;
        JVec3 m_vPivot;
        JMatrix m_matLocal;
        Vector m_vecPosition;
        Vector m_vecRotate;
        Vector m_vecScale;
        Vector m_vecGroupChild;
        J3DObject m_Parent3DObject;
        JVec4[] v4V = new JVec4[3];
        JVec3[] vV = new JVec3[3];
        JMatrix matTemp = new JMatrix();
        JMatrix matTemp2 = new JMatrix();

        public Group() {
        }

        public boolean Initialize(J3DObject var1) {
            this.m_Parent3DObject = var1;
            this.m_vecVertex = new Vector();
            this.m_vecFace = new Vector();
            this.m_vecPosition = new Vector();
            this.m_vecRotate = new Vector();
            this.m_vecScale = new Vector();
            this.m_vecGroupChild = new Vector();
            this.m_matLocal = new JMatrix();
            this.m_matLocal.Identity();

            for(int var2 = 0; var2 < 3; ++var2) {
                this.v4V[var2] = new JVec4();
                this.vV[var2] = new JVec3();
            }

            return true;
        }

        public void RenderPolygon(JGraphicsEngine var1, JMatrix var2, int var3, int var4, Object var5, JFace var6) {
            if (var6.n.DotProduct(var1.m_vCameraNormal) <= 0.0F) {
                for(int var7 = 0; var7 < 3; ++var7) {
                    JVec4 var8 = this.v4V[var7];
                    var8.x = var6.p[var7].x;
                    var8.y = var6.p[var7].y;
                    var8.z = var6.p[var7].z;
                    var8.w = 1.0F;
                    var8.Multiply(var2);
                    var8.x /= this.v4V[var7].w;
                    var8.y /= this.v4V[var7].w;
                    var8.z /= this.v4V[var7].w;
                }

                this.vV[0].x = this.v4V[0].x;
                this.vV[0].y = this.v4V[0].y;
                this.vV[0].z = this.v4V[0].z;
                this.vV[1].x = this.v4V[1].x;
                this.vV[1].y = this.v4V[1].y;
                this.vV[1].z = this.v4V[1].z;
                this.vV[2].x = this.v4V[2].x;
                this.vV[2].y = this.v4V[2].y;
                this.vV[2].z = this.v4V[2].z;
                var1.ZBuffer(this.vV, var3, var4, var5);
            }
        }

        public void Render(JGraphicsEngine var1, JMatrix var2, int var3, int var4, Object var5) {
            this.matTemp.Set(var2);
            this.matTemp.Multiply(this.m_matLocal);
            JMatrix var6 = this.matTemp;
            var1.CameraNormal(var6);

            int var7;
            for(var7 = 0; var7 < this.m_vecFace.size(); ++var7) {
                this.matTemp2.Set(var6);
                this.matTemp2.Multiply(var1.m_matVPS);
                JMatrix var8 = this.matTemp2;
                this.RenderPolygon(var1, var8, var3, var4, var5, (JFace)this.m_vecFace.get(var7));
            }

            for(var7 = 0; var7 < this.m_vecGroupChild.size(); ++var7) {
                ((Group)this.m_vecGroupChild.get(var7)).Render(var1, var6, var3, var4, var5);
            }

        }

        public JVertex GetExistedPoint(JVertex var1) {
            JVertex var2;
            for(int var3 = 0; var3 < this.m_vecVertex.size(); ++var3) {
                var2 = (JVertex)this.m_vecVertex.get(var3);
                if (var1.x == var2.x && var1.y == var2.y && var1.z == var2.z) {
                    return var2;
                }
            }

            var2 = new JVertex();
            if (var2 == null) {
                return null;
            } else {
                this.m_vecVertex.add(var2);
                var2.x = var1.x;
                var2.y = var1.y;
                var2.z = var1.z;
                return var2;
            }
        }
    }
}
