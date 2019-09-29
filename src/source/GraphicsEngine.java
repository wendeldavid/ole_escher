//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.awt.Graphics;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import jlib.JGraphicsEngine;
import jlib.JGraphicsEngine.Data;

public class GraphicsEngine extends JGraphicsEngine {
    public GraphicsEngine() {
    }

    public boolean Initialize(int var1, int var2, int var3) {
        super.Initialize(var1, var2, var3);
        return true;
    }

    int CheckEdge(int var1, int var2, int var3, int var4) {
        boolean var5 = false;
        boolean var6 = false;
        boolean var7 = false;
        boolean var8 = false;
        boolean var9 = false;
        boolean var10 = false;
        if (var1 == 3) {
            return 2;
        } else {
            int var11 = this.GetID(var3 - 1, var4);
            int var12 = this.GetID(var3, var4 - 1);
            int var13 = this.GetID(var3 - 1, var4 - 1);
            if (var1 != var11) {
                return 1;
            } else if (var1 != var12) {
                return 1;
            } else if (var1 != var13) {
                return 1;
            } else {
                int var14 = this.GetIDSub(var3 - 1, var4);
                int var15 = this.GetIDSub(var3, var4 - 1);
                int var16 = this.GetIDSub(var3 - 1, var4 - 1);
                if (var2 != var14) {
                    return 2;
                } else if (var2 != var15) {
                    return 2;
                } else {
                    return var2 != var16 ? 2 : 0;
                }
            }
        }
    }

    int GetID(int var1, int var2) {
        Data var3 = this.m_Data[var1 + var2 * this.m_iWidth];
        return var3.m_iID;
    }

    int GetIDSub(int var1, int var2) {
        Data var3 = this.m_Data[var1 + var2 * this.m_iWidth];
        return var3.m_iIDSub;
    }

    public void DrawEdge() {
        int var14 = this.m_iHeight * this.m_iWidth;
        DataBuffer var15 = this.m_OffScreenImage.getRaster().getDataBuffer();
        byte[] var16 = ((DataBufferByte)var15).getData();

        for(int var13 = 0; var13 < var14; ++var13) {
            var16[var13] = -1;
        }

        for(int var4 = 0; var4 < this.m_iSubY; ++var4) {
            int var9 = var4 * this.m_iSubX;

            for(int var3 = 0; var3 < this.m_iSubX; ++var3) {
                if (this.m_SubSpace[var3 + var9]) {
                    int var5 = var3 * this.m_iSubdividedSize;
                    int var6 = var4 * this.m_iSubdividedSize;
                    int var7 = (var3 + 1) * this.m_iSubdividedSize;
                    int var8 = (var4 + 1) * this.m_iSubdividedSize;
                    if (var5 < 1) {
                        var5 = 1;
                    }

                    if (var6 < 1) {
                        var6 = 1;
                    }

                    if (var7 >= this.m_iWidth) {
                        var7 = this.m_iWidth - 1;
                    }

                    if (var8 >= this.m_iHeight) {
                        var8 = this.m_iHeight - 1;
                    }

                    for(int var2 = var6; var2 < var8; ++var2) {
                        int var10 = var2 * this.m_iWidth;

                        for(int var1 = var5; var1 < var7; ++var1) {
                            Data var12 = this.m_Data[var1 + var10];
                            int var11 = this.CheckEdge(var12.m_iID, var12.m_iIDSub, var1, var2);
                            if (var11 == 2) {
                                var16[var1 + var10] = 0;
                            } else if (var11 == 1) {
                                var16[var1 + var10] = 0;
                                var16[var1 - 1 + var2 * this.m_iWidth] = 0;
                                var16[var1 + (var2 - 1) * this.m_iWidth] = 0;
                                var16[var1 - 1 + (var2 - 1) * this.m_iWidth] = 0;
                            }
                        }
                    }
                }
            }
        }

    }

    public void Render(Graphics var1, ImageObserver var2) {
        var1.drawImage(this.m_OffScreenImage, 0, 0, var2);
    }
}
