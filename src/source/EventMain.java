//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.awt.Color;
import jlib.JEvent;
import jlib.JMath;
import jlib.JResourceManager;

public class EventMain extends JEvent {
    public EventMain(int var1, JResourceManager var2) {
        super(var1, var2);
    }

    public void UpdateDataClick() {
    }

    public void UpdateDataDrag() {
    }

    public void Update() {
        ResourceManager var3 = (ResourceManager)this.m_ResourceManager;
        if (var3.m_bLoaded) {
            if (var3.m_bClick && var3.m_bKeyButton) {
                if (var3.m_IconManager.Update(var3.m_iCursorX, var3.m_iCursorY)) {
                    var3.m_bKeyButton = false;
                }

                var3.m_bClick = false;
            } else if (var3.m_bClick && !var3.m_bKeyButton) {
                if (!var3.m_IconManager.Update(var3.m_iCursorX, var3.m_iCursorY)) {
                    this.UpdateDataClick();
                    var3.m_bDragReady = true;
                }

                var3.m_iCursorXOld = var3.m_iCursorX;
                var3.m_iCursorYOld = var3.m_iCursorY;
                var3.m_bUpdateInterface = false;
                var3.m_bClick = false;
            }

            if (var3.m_bUpdateInterface && var3.m_bDrag && var3.m_bDragReady && (var3.m_iCursorX != var3.m_iCursorXOld || var3.m_iCursorY != var3.m_iCursorYOld)) {
                this.UpdateDataDrag();
            }

            var3.m_fRotateXOld = var3.m_fRotateX;
            var3.m_fRotateYOld = var3.m_fRotateY;
            if (var3.m_bUpdateInterface) {
                if (var3.m_bKeyButton && var3.m_bDrag && (var3.m_iCursorX != var3.m_iCursorXOld || var3.m_iCursorY != var3.m_iCursorYOld)) {
                    var3.m_fRotateY -= (float)(var3.m_iCursorX - var3.m_iCursorXOld) * 1.0F;
                    var3.m_fRotateX -= (float)(var3.m_iCursorY - var3.m_iCursorYOld) * 1.0F;
                    if (var3.m_fRotateX < -90.0F) {
                        var3.m_fRotateX = -90.0F;
                    }

                    if (var3.m_fRotateX > 90.0F) {
                        var3.m_fRotateX = 90.0F;
                    }

                    if (var3.m_fRotateY < 0.0F) {
                        var3.m_fRotateY += 360.0F;
                    }

                    if (var3.m_fRotateY > 360.0F) {
                        var3.m_fRotateY -= 360.0F;
                    }
                }

                var3.m_iCursorXOld = var3.m_iCursorX;
                var3.m_iCursorYOld = var3.m_iCursorY;
                var3.m_bUpdateInterface = false;
            }

            var3.m_matRotateX.SetRotateX(JMath.Radian(var3.m_fRotateX));
            var3.m_matRotateY.SetRotateY(JMath.Radian(var3.m_fRotateY));
            var3.m_GraphicsEngine.m_matWorld.Identity();
            var3.m_GraphicsEngine.m_matWorld.SetScale(var3.m_vScale);
            var3.m_GraphicsEngine.m_matWorld.Multiply(var3.m_matRotateY);
            var3.m_GraphicsEngine.m_matWorld.Multiply(var3.m_matRotateX);
            var3.m_GraphicsEngine.Update();
            var3.m_FaceDoubleView.Update(var3.m_GraphicsEngine, var3.m_vCenter);
            var3.m_FaceDoubleViewY.Update(var3.m_GraphicsEngine, var3.m_vCenter);
            var3.m_FigureBlockManager.Update(var3.m_GraphicsEngine);
            var3.m_FigureCastManager.Update(var3.m_GraphicsEngine);
            var3.m_FigureCastManager.CheckDelete();
        }
    }

    public void Render() {
        ResourceManager var3 = (ResourceManager)this.m_ResourceManager;
        int var4 = var3.m_GraphicsEngine.m_iWidth;
        int var5 = var3.m_GraphicsEngine.m_iHeight;
        if (!var3.m_bLoaded) {
            var3.m_GraphicsEngine.Clear();
            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.white);
            var3.m_GraphicsEngine.m_OffScreenGraphics.fillRect(0, 0, var4, var5);
            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.black);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawString("Now Loading...", 24, 24);
        } else {
            var3.m_GraphicsEngine.Clear();
            var3.m_FigureBlockManager.Render(var3.m_GraphicsEngine);
            var3.m_FigureCastManager.Render(var3.m_GraphicsEngine);
            var3.m_GraphicsEngine.DrawEdge();
            var3.m_IconManager.Render(var3.m_GraphicsEngine);
            if (var3.m_bMouseEnter) {
                var3.m_GraphicsEngine.m_OffScreenGraphics.drawImage(var3.m_ImageCursor, var3.m_iCursorX, var3.m_iCursorY, var3.m_Applet);
            }

            var3.m_GraphicsEngine.m_OffScreenGraphics.setColor(Color.lightGray);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(0, 0, var4 - 1, 0);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(var4 - 1, 0, var4 - 1, var5 - 1);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(var4 - 1, var5 - 1, 0, var5 - 1);
            var3.m_GraphicsEngine.m_OffScreenGraphics.drawLine(0, var5 - 1, 0, 0);
        }
    }
}
