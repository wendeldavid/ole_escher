//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import jlib.J3DObjectDXF;
import jlib.JEvent;
import jlib.JFaceDoubleView;
import jlib.JFaceDoubleViewY;
import jlib.JFaceDoubleXY;
import jlib.JFaceDoubleXZ;
import jlib.JFaceDoubleZY;
import jlib.JMatrix;
import jlib.JResourceManager;
import jlib.JVec3;

public class ResourceManager extends JResourceManager {
    public boolean m_bApplet = true;
    public GraphicsEngine m_GraphicsEngine;
    public EventManager m_EventManager;
    public JFaceDoubleView m_FaceDoubleView;
    public JFaceDoubleViewY m_FaceDoubleViewY;
    public JFaceDoubleXZ m_FaceDoubleXZ;
    public JFaceDoubleXY m_FaceDoubleXY;
    public JFaceDoubleZY m_FaceDoubleZY;
    public Sight m_Sight;
    public int m_iCursorX = 0;
    public int m_iCursorY = 0;
    public int m_iCursorXOld = 0;
    public int m_iCursorYOld = 0;
    public boolean m_bMouseButton = false;
    public boolean m_bMouseButtonOld = false;
    public boolean m_bKeyButton = false;
    public boolean m_bKeyButtonOld = false;
    public boolean m_bClick = false;
    public boolean m_bClickOld = false;
    public boolean m_bDrag = false;
    public boolean m_bDragOld = false;
    public boolean m_bMouseEnter = false;
    public boolean m_bDragReady = false;
    public boolean m_bUpdateInterface = false;
    private long m_lTimeCur = System.currentTimeMillis();
    private long m_lTimeOld;
    private int m_iCount;
    private String m_strFPS;
    public float m_fRotateX;
    public float m_fRotateY;
    public float m_fRotateXOld;
    public float m_fRotateYOld;
    public JVec3 m_vScale;
    public JMatrix m_matRotateX;
    public JMatrix m_matRotateY;
    public JVec3 m_vCenter;
    public Applet m_Applet;
    public MediaTracker m_MediaTracker;
    final int MEDIA_TRACKER_GROUP_ID;
    public boolean m_bLoaded;
    public FigureCastManager m_FigureCastManager;
    public FigureBlockManager m_FigureBlockManager;
    public FigureHoleFactory m_FigureHoleFactory;
    public GridSpaceManager m_GridSpaceManager;
    public Character2 m_ModelCharacter;
    public J3DObjectDXF m_ModelHole;
    public IconManager m_IconManager;
    public AudioClip m_SoundAdd;
    public AudioClip m_SoundSelected;
    public AudioClip m_SoundDelete;
    public Image m_ImageCursor;

    public ResourceManager() {
        this.m_lTimeOld = this.m_lTimeCur;
        this.m_iCount = 0;
        this.m_strFPS = "";
        this.m_fRotateX = -30.0F;
        this.m_fRotateY = 30.0F;
        this.m_fRotateXOld = -30.0F;
        this.m_fRotateYOld = 30.0F;
        this.m_vScale = new JVec3(0.1F, 0.1F, 0.1F);
        this.m_matRotateX = new JMatrix();
        this.m_matRotateY = new JMatrix();
        this.m_vCenter = new JVec3(0.0F, 0.0F, 0.0F);
        this.MEDIA_TRACKER_GROUP_ID = 0;
        this.m_bLoaded = false;
    }

    public boolean Initialize(Applet var1, URL var2, Class var3, int var4, int var5, boolean var6) {
        var1.setCursor(CreateNullCursor());
        this.m_bApplet = var6;
        this.m_Applet = var1;
        this.m_MediaTracker = new MediaTracker(var1);
        this.m_GraphicsEngine = new GraphicsEngine();
        if (var6) {
            this.m_GraphicsEngine.Initialize(var4, var5, var4 / 12);
        } else {
            this.m_GraphicsEngine.Initialize(var4, var5, var4 / 20);
        }

        this.m_FaceDoubleView = new JFaceDoubleView();
        this.m_FaceDoubleViewY = new JFaceDoubleViewY();
        this.m_FaceDoubleXZ = new JFaceDoubleXZ();
        this.m_FaceDoubleXY = new JFaceDoubleXY();
        this.m_FaceDoubleZY = new JFaceDoubleZY();
        this.m_EventManager = new EventManager();
        this.m_EventManager.Initialize(this);
        this.m_ModelCharacter = new Character2();
        this.m_ModelCharacter.Initialize(var2, var3);
        this.m_ModelHole = new J3DObjectDXF();
        this.m_ModelHole.Initialize(var2, var3, "data/hole.dxf");
        this.m_FigureCastManager = new FigureCastManager();
        this.m_FigureCastManager.Initialize(this);
        this.m_FigureBlockManager = new FigureBlockManager();
        this.m_FigureBlockManager.Initialize(this);
        this.m_FigureHoleFactory = new FigureHoleFactory();
        this.m_FigureHoleFactory.Initialize(this);
        this.m_GridSpaceManager = new GridSpaceManager();
        this.m_GridSpaceManager.Initialize(this);
        this.m_Sight = new Sight();
        this.m_Sight.Initialize(32);
        this.m_SoundAdd = Applet.newAudioClip(var3.getResource("/data/add.au"));
        this.m_SoundSelected = Applet.newAudioClip(var3.getResource("/data/selected.au"));
        this.m_SoundDelete = Applet.newAudioClip(var3.getResource("/data/delete.au"));
        this.m_IconManager = new IconManager();
        this.m_IconManager.Initialize(this, var1, var2, var3, var4, var5);
        Icon var7 = this.m_IconManager.Add("data/icon_cast_on.png", "data/icon_cast_off.png", (JEvent)this.m_EventManager.GetEvents().get(0));
        this.m_IconManager.SetCurrentIcon(var7);
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        var7 = this.m_IconManager.Add("data/icon_block_on.png", "data/icon_block_off.png", (JEvent)this.m_EventManager.GetEvents().get(1));
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        var7 = this.m_IconManager.Add("data/icon_hole_on.png", "data/icon_hole_off.png", (JEvent)this.m_EventManager.GetEvents().get(2));
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        var7 = this.m_IconManager.Add("data/icon_delete_on.png", "data/icon_delete_off.png", (JEvent)this.m_EventManager.GetEvents().get(3));
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        var7 = this.m_IconManager.Add("data/icon_rotate_on.png", "data/icon_rotate_off.png", (JEvent)this.m_EventManager.GetEvents().get(4));
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        var7 = this.m_IconManager.Add2("data/icon_clear_on.png", "data/icon_clear_off.png", (JEvent)this.m_EventManager.GetEvents().get(5));
        this.m_MediaTracker.addImage(var7.GetImageOn(), 0);
        this.m_MediaTracker.addImage(var7.GetImageOff(), 0);
        URL var8 = var3.getResource("data/cursor.png");

        try {
            this.m_ImageCursor = var1.getToolkit().createImage((ImageProducer)var8.getContent());
            this.m_MediaTracker.addImage(this.m_ImageCursor, 0);
        } catch (IOException var11) {
        }

        while(!this.m_bLoaded) {
            try {
                this.m_MediaTracker.waitForID(0);
                if (!this.m_MediaTracker.isErrorID(0)) {
                    this.m_bLoaded = true;
                }
            } catch (InterruptedException var10) {
            }
        }

        return true;
    }

    public void Do(Graphics var1, ImageObserver var2) {
        if (this.m_bLoaded) {
            this.m_lTimeCur = System.currentTimeMillis();
            long var3 = this.m_lTimeCur - this.m_lTimeOld;
            if (var3 > 33L) {
                this.m_EventManager.Update();
                this.m_EventManager.Render();
                this.m_GraphicsEngine.Render(var1, var2);
                this.m_lTimeOld = this.m_lTimeCur;
            }
        }

    }
}
