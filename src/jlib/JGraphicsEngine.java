//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jlib;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class JGraphicsEngine {
    public int m_iWidth = 0;
    public int m_iHeight = 0;
    public JMatrix m_matWorld;
    public JMatrix m_matView;
    public JMatrix m_matProjection;
    public JMatrix m_matScreen;
    public JMatrix m_matTotal;
    public JMatrix m_matVPS;
    public JVec3 m_vCameraDirection;
    public JVec3 m_vCameraFrom;
    public JVec3 m_vCameraAt;
    public JVec3 m_vWorldUp;
    public JVec3 m_vLightDirection;
    public JVec3 m_vCameraNormal;
    public JVec3 m_vCameraNormalReset;
    public BufferedImage m_OffScreenImage;
    public Graphics m_OffScreenGraphics;
    public boolean m_bVisibleAll;
    public boolean m_bVisiblePart;
    public boolean m_bEnableDepth = true;
    public boolean m_bWriting = true;
    public boolean m_bUseMinZ = false;
    public boolean m_bWorking = false;
    public boolean m_bCrossTest = false;
    public float m_fMinZ = 999999.0F;
    protected Data[] m_Data;
    protected Data[] m_DataWorking;
    protected int m_iSubdividedSize = 24;
    protected int m_iSubX;
    protected int m_iSubY;
    protected boolean[] m_SubSpace;
    static float[] fDX = new float[3];
    static float[] fDy = new float[3];
    static float[] fDz = new float[3];
    static JVec3 vTemp = new JVec3();
    static JVec3 vTemp2 = new JVec3();
    static JVec4 v4Temp = new JVec4();
    static JMatrix matTemp = new JMatrix();

    public JGraphicsEngine() {
    }

    public Data GetData(int var1, int var2) {
        return this.m_Data[var1 + var2 * this.m_iWidth];
    }

    public int GetDataID(int var1, int var2) {
        Data var3 = this.m_Data[var1 + var2 * this.m_iWidth];
        return var3.m_Data == null ? -1 : var3.m_iID;
    }

    public int GetDataIDSub(int var1, int var2) {
        Data var3 = this.m_Data[var1 + var2 * this.m_iWidth];
        return var3 == null ? -1 : var3.m_iIDSub;
    }

    public Object GetDataObject(int var1, int var2) {
        Data var3 = this.m_Data[var1 + var2 * this.m_iWidth];
        return var3 == null ? null : var3.m_Data;
    }

    public boolean Initialize(int var1, int var2, int var3) {
        this.m_iWidth = var1;
        this.m_iHeight = var2;
        this.m_iSubdividedSize = var3;
        this.m_iSubX = this.m_iWidth / this.m_iSubdividedSize + 1;
        this.m_iSubY = this.m_iHeight / this.m_iSubdividedSize + 1;
        this.m_OffScreenImage = new BufferedImage(this.m_iWidth, this.m_iHeight, 10);
        this.m_OffScreenGraphics = this.m_OffScreenImage.getGraphics();
        this.m_SubSpace = new boolean[this.m_iSubX * this.m_iSubY];
        this.m_Data = new Data[var1 * var2];
        this.m_DataWorking = new Data[var1 * var2];

        for(int var4 = 0; var4 < this.m_iHeight; ++var4) {
            for(int var5 = 0; var5 < this.m_iWidth; ++var5) {
                this.m_Data[var5 + var4 * this.m_iWidth] = new Data();
                this.m_Data[var5 + var4 * this.m_iWidth].Reset();
                this.m_DataWorking[var5 + var4 * this.m_iWidth] = new Data();
                this.m_DataWorking[var5 + var4 * this.m_iWidth].Reset();
            }
        }

        this.m_matWorld = new JMatrix();
        this.m_matWorld.Identity();
        this.m_vCameraFrom = new JVec3(0.0F, 0.0F, -10.0F);
        this.m_vCameraAt = new JVec3(0.0F, 0.0F, 0.0F);
        this.m_vWorldUp = new JVec3(0.0F, 1.0F, 0.0F);
        this.m_vCameraDirection = new JVec3();
        this.m_matView = new JMatrix();
        this.m_matView.SetViewMatrix(this.m_vCameraFrom, this.m_vCameraAt, this.m_vWorldUp);
        this.m_vCameraDirection.Set(this.m_vCameraFrom);
        this.m_vCameraDirection.Minus(this.m_vCameraAt);
        this.m_vCameraDirection.Normalize();
        this.m_matProjection = new JMatrix();
        this.m_matProjection.SetScale(new JVec3((float)this.m_iHeight / (float)this.m_iWidth, 1.0F, 1.0F));
        this.m_matScreen = new JMatrix();
        this.m_matScreen.SetScreenMatrix(0, 0, this.m_iWidth, this.m_iHeight, 0.0F, 1.0F);
        this.m_matTotal = new JMatrix();
        this.m_matTotal.Set(this.m_matWorld);
        this.m_matTotal.Multiply(this.m_matView);
        this.m_matTotal.Multiply(this.m_matProjection);
        this.m_matTotal.Multiply(this.m_matScreen);
        this.m_matVPS = new JMatrix();
        this.m_vLightDirection = new JVec3(-0.2F, -1.0F, -6.0F);
        this.m_vLightDirection.Normalize();
        this.m_vCameraNormal = new JVec3(0.0F, 0.0F, -1.0F);
        this.m_vCameraNormalReset = new JVec3(0.0F, 0.0F, -1.0F);
        return true;
    }

    public void ReadyMinZ() {
        this.m_fMinZ = 999999.0F;
    }

    public void CameraNormal(JMatrix var1) {
        this.m_vCameraNormal.x = 0.0F;
        this.m_vCameraNormal.y = 0.0F;
        this.m_vCameraNormal.z = -1.0F;
        this.m_vCameraNormal.MatrixMultiply(var1);
        this.m_vCameraNormal.Normalize();
    }

    public void CameraNormalReset() {
        this.m_vCameraNormal.Set(this.m_vCameraNormalReset);
    }

    public void Update() {
        this.m_vCameraNormal.x = 0.0F;
        this.m_vCameraNormal.y = 0.0F;
        this.m_vCameraNormal.z = -1.0F;
        this.m_vCameraNormal.MatrixMultiply(this.m_matWorld);
        this.m_vCameraNormal.Normalize();
        this.m_vCameraNormalReset.Set(this.m_vCameraNormal);
        this.m_matTotal.Set(this.m_matWorld);
        this.m_matTotal.Multiply(this.m_matView);
        this.m_matTotal.Multiply(this.m_matProjection);
        this.m_matTotal.Multiply(this.m_matScreen);
        this.m_matVPS.Set(this.m_matView);
        this.m_matVPS.Multiply(this.m_matProjection);
        this.m_matVPS.Multiply(this.m_matScreen);
    }

    public void Render(Graphics var1, ImageObserver var2) {
        var1.drawImage(this.m_OffScreenImage, 0, 0, var2);
    }

    public void Clear() {
        int var1 = this.m_iWidth * this.m_iHeight;

        int var2;
        for(var2 = 0; var2 < var1; ++var2) {
            this.m_Data[var2].Reset();
        }

        var1 = this.m_iSubX * this.m_iSubY;

        for(var2 = 0; var2 < var1; ++var2) {
            this.m_SubSpace[var2] = false;
        }

    }

    public void ClearWorking() {
        int var1 = this.m_iWidth * this.m_iHeight;

        for(int var2 = 0; var2 < var1; ++var2) {
            this.m_DataWorking[var2].Reset();
        }

    }

    void CopyWorkingFromData() {
        int var3 = this.m_iWidth * this.m_iHeight;

        for(int var4 = 0; var4 < var3; ++var4) {
            Data var1 = this.m_Data[var4];
            Data var2 = this.m_DataWorking[var4];
            var1.m_Data = var2.m_Data;
            var1.m_fZ = var2.m_fZ;
            var1.m_iID = var2.m_iID;
            var1.m_iIDSub = var2.m_iIDSub;
        }

    }

    void SetSubSpace(int var1, int var2) {
        this.m_SubSpace[var1 / this.m_iSubdividedSize + var2 / this.m_iSubdividedSize * this.m_iSubX] = true;
        this._SetSubSpace(var1 + 1, var2);
        this._SetSubSpace(var1 + 1, var2 + 1);
        this._SetSubSpace(var1, var2 + 1);
    }

    void _SetSubSpace(int var1, int var2) {
        if (var1 < this.m_iWidth && var2 < this.m_iHeight) {
            this.m_SubSpace[var1 / this.m_iSubdividedSize + var2 / this.m_iSubdividedSize * this.m_iSubX] = true;
        }
    }

    public boolean ScreenToWorld(JFace var1, int var2, int var3, JVec3 var4) {
        JMatrix var5 = matTemp;
        var5.Set(this.m_matTotal);
        var5.Invert();
        JVec4 var6 = v4Temp;
        var6.x = (float)var2;
        var6.y = (float)var3;
        var6.z = 0.0F;
        var6.w = 1.0F;
        var6.Multiply(var5);
        vTemp.x = var6.x;
        vTemp.y = var6.y;
        vTemp.z = var6.z;
        vTemp2.x = 0.0F;
        vTemp2.y = 0.0F;
        vTemp2.z = -1.0F;
        vTemp2.MatrixMultiply(this.m_matWorld);
        return var1.GetCrossPointLineToFace(var4, vTemp2, vTemp);
    }

    public boolean ScreenToWorldGrid(JFace var1, int var2, int var3, JVec3 var4, float var5) {
        JMatrix var6 = matTemp;
        var6.Set(this.m_matTotal);
        var6.Invert();
        JVec4 var7 = v4Temp;
        var7.x = (float)var2;
        var7.y = (float)var3;
        var7.z = 0.0F;
        var7.w = 1.0F;
        var7.Multiply(var6);
        vTemp.x = var7.x;
        vTemp.y = var7.y;
        vTemp.z = var7.z;
        vTemp2.x = 0.0F;
        vTemp2.y = 0.0F;
        vTemp2.z = -1.0F;
        vTemp2.MatrixMultiply(this.m_matWorld);
        if (var1.GetCrossPointLineToFace(var4, vTemp2, vTemp)) {
            var4.x = (float)((int)(var4.x / var5)) * var5;
            var4.y = (float)((int)(var4.y / var5)) * var5;
            var4.z = (float)((int)(var4.z / var5)) * var5;
            return true;
        } else {
            return false;
        }
    }

    public void ZBuffer(JVec3[] var1, int var2, int var3, Object var4) {
        float var11 = 0.0F;
        float var12 = 0.0F;
        float var13 = 0.0F;
        float var14 = 0.0F;

        for(int var6 = 0; var6 < 3; ++var6) {
            int var7;
            if (var6 == 2) {
                var7 = 0;
            } else {
                var7 = var6 + 1;
            }

            fDX[var6] = var1[var7].x - var1[var6].x;
            fDy[var6] = var1[var7].y - var1[var6].y;
            fDz[var6] = var1[var7].z - var1[var6].z;
        }

        float var22 = var1[0].x;
        float var23 = var1[0].x;
        float var24 = var1[0].y;
        float var25 = var1[0].y;

        int var5;
        for(var5 = 1; var5 < 3; ++var5) {
            if (var1[var5].x > var22) {
                var22 = var1[var5].x;
            }

            if (var1[var5].x < var23) {
                var23 = var1[var5].x;
            }

            if (var1[var5].y > var24) {
                var24 = var1[var5].y;
            }

            if (var1[var5].y < var25) {
                var25 = var1[var5].y;
            }
        }

        int var27 = (int)(var24 + 0.5F);
        int var9 = (int)(var25 + 0.5F);
        if (var9 < 0) {
            var9 = 0;
        }

        if (var27 > this.m_iHeight) {
            var27 = this.m_iHeight;
        }

        for(; var9 <= var27; ++var9) {
            var11 = var22;
            var12 = var23;

            for(var5 = 0; var5 < 3; ++var5) {
                if (Math.abs(fDy[var5]) > 0.0F) {
                    float var15 = ((float)var9 - var1[var5].y) / fDy[var5];
                    if (var15 >= 0.0F && var15 <= 1.0F) {
                        float var16 = var15 * fDX[var5] + var1[var5].x;
                        float var17 = var15 * fDz[var5] + var1[var5].z;
                        if (var16 <= var11) {
                            var11 = var16;
                            var13 = var17;
                        }

                        if (var16 >= var12) {
                            var12 = var16;
                            var14 = var17;
                        }
                    }
                }
            }

            float var18 = var12 - var11;
            float var19 = var14 - var13;
            float var20;
            if (var18 >= 1.0F) {
                var20 = var19 / var18;
            } else {
                var20 = 0.0F;
            }

            int var8 = (int)(var11 + 0.5F);
            int var28 = (int)var12;
            if (var8 < 0) {
                var8 = 0;
            }

            if (var28 > this.m_iWidth) {
                var28 = this.m_iWidth;
            }

            for(int var10 = var9 * this.m_iWidth + var8; var8 <= var28; ++var8) {
                float var21 = var13 + ((float)var8 - var11) * var20;
                if (var21 < 1.0E-5F) {
                    if (var20 > 0.0F) {
                        break;
                    }
                } else if (var21 > 1000.0F) {
                    if (var20 < 0.0F) {
                        break;
                    }
                } else if (var8 < this.m_iWidth && var9 < this.m_iHeight) {
                    Data var26;
                    if (this.m_bWorking) {
                        var26 = this.m_DataWorking[var10];
                    } else {
                        var26 = this.m_Data[var10];
                    }

                    if (this.m_bCrossTest) {
                        if (var26.m_Data != null) {
                            this.m_bVisiblePart = true;
                            return;
                        }
                    } else if (this.m_bEnableDepth) {
                        if (var21 < var26.m_fZ) {
                            if (this.m_bWriting) {
                                var26.m_iID = var2;
                                var26.m_iIDSub = var3;
                                var26.m_Data = var4;
                                var26.m_fZ = var21;
                                this.SetSubSpace(var8, var9);
                            }

                            if (this.m_bUseMinZ && var26.m_fZ < this.m_fMinZ) {
                                this.m_fMinZ = var26.m_fZ;
                            }

                            this.m_bVisiblePart = true;
                        } else if (var26.m_iID != var2) {
                            this.m_bVisibleAll = false;
                        }
                    } else {
                        if (this.m_bWriting) {
                            var26.m_iID = var2;
                            var26.m_iIDSub = var3;
                            var26.m_Data = var4;
                            var26.m_fZ = 999999.0F;
                            this.SetSubSpace(var8, var9);
                        }

                        if (this.m_bUseMinZ && var26.m_fZ < this.m_fMinZ) {
                            this.m_fMinZ = var26.m_fZ;
                        }

                        this.m_bVisiblePart = true;
                    }

                    ++var10;
                }
            }
        }

    }

    public class Data {
        public float m_fZ;
        public int m_iID;
        public int m_iIDSub;
        public Object m_Data;

        public Data() {
        }

        public void Reset() {
            this.m_fZ = 999999.0F;
            this.m_iID = 0;
            this.m_iIDSub = 0;
            this.m_Data = null;
        }
    }
}
