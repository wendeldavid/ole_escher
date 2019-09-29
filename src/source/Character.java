//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.net.URL;
import java.util.Vector;
import jlib.J3DObjectDXF;
import jlib.JGraphicsEngine;
import jlib.JMath;
import jlib.JMatrix;
import jlib.JVec3;

public class Character {
    Group m_GroupBody;
    JMatrix m_matTemp = new JMatrix();

    public Character() {
    }

    public boolean Initialize(URL url, Class clazz) {
        this.m_GroupBody = new Group();
        JMatrix matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, 0.2F, 0.0F));
        this.m_GroupBody.Initialize(url, clazz, "data/body.dxf", matrix, 0);
        this.m_GroupBody.Setup();
        Group group1 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, 0.6F, 0.0F));
        group1.Initialize(url, clazz, "data/head.dxf", matrix, 1);
        this.m_GroupBody.AddChild(group1);
        group1.Setup();
        Group group2 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.3F, 0.45F, 0.0F));
        group2.Initialize(url, clazz, "data/arm_l1.dxf", matrix, 2);
        this.m_GroupBody.AddChild(group2);
        group2.AddRotatekey(0, 0.0F);
        group2.AddRotatekey(15, 15.0F);
        group2.AddRotatekey(30, 0.0F);
        group2.AddRotatekey(45, -15.0F);
        group2.AddRotatekey(60, 0.0F);
        group2.Setup();
        Group group3 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, -0.4F, 0.0F));
        group3.Initialize(url, clazz, "data/arm_l1.dxf", matrix, 3);
        group2.AddChild(group3);
        group3.Setup();
        Group group4 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(-0.3F, 0.45F, 0.0F));
        group4.Initialize(url, clazz, "data/arm_r1.dxf", matrix, 4);
        this.m_GroupBody.AddChild(group4);
        group4.AddRotatekey(0, 0.0F);
        group4.AddRotatekey(15, -15.0F);
        group4.AddRotatekey(30, 0.0F);
        group4.AddRotatekey(45, 15.0F);
        group4.AddRotatekey(60, 0.0F);
        group4.Setup();
        Group group5 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, -0.4F, 0.0F));
        group5.Initialize(url, clazz, "data/arm_r1.dxf", matrix, 5);
        group4.AddChild(group5);
        group5.Setup();
        Group group6 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.1F, -0.09F, 0.0F));
        group6.Initialize(url, clazz, "data/arm_l1.dxf", matrix, 6);
        this.m_GroupBody.AddChild(group6);
        group6.AddRotatekey(0, 0.0F);
        group6.AddRotatekey(15, -15.0F);
        group6.AddRotatekey(30, 0.0F);
        group6.AddRotatekey(45, 15.0F);
        group6.AddRotatekey(60, 0.0F);
        group6.Setup();
        Group group7 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, -0.4F, 0.0F));
        group7.Initialize(url, clazz, "data/arm_l1.dxf", matrix, 7);
        group6.AddChild(group7);
        group7.Setup();
        Group group8 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(-0.1F, -0.09F, 0.0F));
        group8.Initialize(url, clazz, "data/arm_r1.dxf", matrix, 8);
        this.m_GroupBody.AddChild(group8);
        group8.Setup();
        group8.AddRotatekey(0, 0.0F);
        group8.AddRotatekey(15, 15.0F);
        group8.AddRotatekey(30, 0.0F);
        group8.AddRotatekey(45, -15.0F);
        group8.AddRotatekey(60, 0.0F);
        group8.Setup();
        Group group9 = new Group();
        matrix = new JMatrix();
        matrix.SetTranslate(new JVec3(0.0F, -0.4F, 0.0F));
        group9.Initialize(url, clazz, "data/arm_r1.dxf", matrix, 9);
        group8.AddChild(group9);
        group9.Setup();
        return true;
    }

    public void Update(int var1) {
        this.m_GroupBody.Update(var1);
    }

    public void Render(JGraphicsEngine var1, JMatrix var2, int var3, Object var4) {
        this.m_matTemp.Set(var2);
        this.m_matTemp.Multiply(var1.m_matWorld);
        JMatrix var5 = this.m_matTemp;
        this.m_GroupBody.Render(var1, var5, var3, var4);
    }

    public class Group {
        int m_iID = 0;
        J3DObjectDXF m_3DObject;
        JMatrix m_matBase;
        JMatrix m_matLocal;
        JMatrix m_matRotate;
        Vector m_vecChildGroup;
        Vector m_vecRotateKey;
        Vector m_vecRotate;
        int m_iMaxTime = 0;
        JMatrix m_matTotal;

        public Group() {
        }

        public boolean Initialize(URL var1, Class var2, String var3, JMatrix var4, int var5) {
            this.m_vecChildGroup = new Vector();
            this.m_vecRotateKey = new Vector();
            this.m_vecRotate = new Vector();
            this.m_matLocal = new JMatrix();
            this.m_matRotate = new JMatrix();
            this.m_3DObject = new J3DObjectDXF();
            this.m_matTotal = new JMatrix();
            if (!this.m_3DObject.Initialize(var1, var2, var3)) {
                return false;
            } else {
                this.m_matBase = var4;
                this.m_iID = var5;
                return true;
            }
        }

        public void Setup() {
            int var1 = 0;
            if (this.m_vecRotateKey.size() > 0) {
                this.m_iMaxTime = ((RotateKey)this.m_vecRotateKey.get(this.m_vecRotateKey.size() - 1)).m_iTime - 1;
            } else {
                this.m_iMaxTime = 0;
            }

            for(int var6 = 0; var6 < this.m_vecRotateKey.size() - 1; ++var6) {
                int var2 = ((RotateKey)this.m_vecRotateKey.get(var6)).m_iTime;
                int var3 = ((RotateKey)this.m_vecRotateKey.get(var6 + 1)).m_iTime;
                float var4 = ((RotateKey)this.m_vecRotateKey.get(var6)).m_fAngle;
                float var5 = ((RotateKey)this.m_vecRotateKey.get(var6 + 1)).m_fAngle - var4;
                var5 /= (float)(var3 - var2);

                for(int var7 = 0; var7 < var3 - var2; ++var7) {
                    RotateKey var8 = Character.this.new RotateKey();
                    var8.m_iTime = var1;
                    var8.m_fAngle = var4 + var5 * (float)var7;
                    this.m_vecRotate.add(var8);
                    ++var1;
                }
            }

        }

        public void AddRotatekey(int var1, float var2) {
            RotateKey var3 = Character.this.new RotateKey();
            var3.m_iTime = var1;
            var3.m_fAngle = var2;
            this.m_vecRotateKey.add(var3);
        }

        public void AddChild(Group var1) {
            this.m_vecChildGroup.add(var1);
        }

        public void Update(int var1) {
            int var2 = var1;
            if (var1 > this.m_iMaxTime) {
                var2 = this.m_iMaxTime;
            }

            if (this.m_vecRotate.size() != 0) {
                this.m_matRotate.SetRotateX(JMath.Radian(((RotateKey)this.m_vecRotate.get(var2)).m_fAngle));
                this.m_matRotate.Multiply(this.m_matBase);
                this.m_matLocal = this.m_matRotate;
            } else {
                this.m_matLocal = this.m_matBase;
            }

            for(int var3 = 0; var3 < this.m_vecChildGroup.size(); ++var3) {
                ((Group)this.m_vecChildGroup.get(var3)).Update(var1);
            }

        }

        public void Render(JGraphicsEngine var1, JMatrix var2, int var3, Object var4) {
            this.m_matTotal.Set(this.m_matLocal);
            this.m_matTotal.Multiply(var2);
            this.m_3DObject.Render(var1, this.m_matTotal, var3, this.m_iID, var4);

            for(int var5 = 0; var5 < this.m_vecChildGroup.size(); ++var5) {
                ((Group)this.m_vecChildGroup.get(var5)).Render(var1, this.m_matTotal, var3, var4);
            }

        }
    }

    public class RotateKey {
        int m_iTime;
        float m_fAngle;

        public RotateKey() {
        }
    }
}
