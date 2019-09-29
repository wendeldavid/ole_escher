//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import jlib.JFullScreen;
import source.ResourceManager;

public class OLE extends Applet implements Runnable {
    static int m_iWidth = 480;
    static int m_iHeight = 360;
    Thread m_Thread;
    Graphics m_CurrentGraphics;
    ResourceManager m_ResourceManager;

    public OLE() {
    }

    public static void main(String[] args) {
        m_iWidth = 640;
        m_iHeight = 480;
        JFrame frame = new JFrame("OLE Coordinatae System");
        frame.setUndecorated(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent var1) {
                System.exit(0);
            }
        });
        URL url = null;

        try {
            url = new URL("file:///./");
        } catch (Exception e) {
            return;
        }

        OLE ole = new OLE();
        ole.Initialize(url, false);
        frame.add(ole);
        JFullScreen fullScreen = new JFullScreen();
        if (fullScreen.Do(frame, m_iWidth, m_iHeight, 32)) {
            ole.start();
        } else {
            frame.dispose();
            frame.setUndecorated(false);
            frame.setVisible(true);
            frame.setVisible(false);
            frame.setSize(m_iWidth + frame.getInsets().left + frame.getInsets().right, m_iHeight + frame.getInsets().top + frame.getInsets().bottom);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.show();
            ole.start();
        }

    }

    public void init() {
        this.Initialize(this.getDocumentBase(), true);
    }

    public void Initialize(URL url, boolean useParameters) {
        if (useParameters) {
            String widt = this.getParameter("width");
            m_iWidth = Integer.parseInt(widt);
            String height = this.getParameter("height");
            m_iHeight = Integer.parseInt(height);
        }

        this.m_ResourceManager = new ResourceManager();
        this.m_ResourceManager.Initialize(this, url, this.getClass(), m_iWidth, m_iHeight, useParameters);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case 32:
                        OLE.this.m_ResourceManager.m_bKeyButtonOld = OLE.this.m_ResourceManager.m_bKeyButton;
                        OLE.this.m_ResourceManager.m_bKeyButton = true;
                        OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                    default:
                }
            }

            public void keyReleased(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case 32:
                        OLE.this.m_ResourceManager.m_bKeyButtonOld = OLE.this.m_ResourceManager.m_bKeyButton;
                        OLE.this.m_ResourceManager.m_bKeyButton = false;
                        OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                    default:
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_iCursorXOld = OLE.this.m_ResourceManager.m_iCursorX;
                OLE.this.m_ResourceManager.m_iCursorYOld = OLE.this.m_ResourceManager.m_iCursorY;
                OLE.this.m_ResourceManager.m_iCursorX = evt.getX();
                OLE.this.m_ResourceManager.m_iCursorY = evt.getY();
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
            }

            public void mouseDragged(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_iCursorXOld = OLE.this.m_ResourceManager.m_iCursorX;
                OLE.this.m_ResourceManager.m_iCursorYOld = OLE.this.m_ResourceManager.m_iCursorY;
                OLE.this.m_ResourceManager.m_iCursorX = evt.getX();
                OLE.this.m_ResourceManager.m_iCursorY = evt.getY();
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_iCursorXOld = OLE.this.m_ResourceManager.m_iCursorX;
                OLE.this.m_ResourceManager.m_iCursorYOld = OLE.this.m_ResourceManager.m_iCursorY;
                OLE.this.m_ResourceManager.m_iCursorX = evt.getX();
                OLE.this.m_ResourceManager.m_iCursorY = evt.getY();
                OLE.this.m_ResourceManager.m_bMouseButtonOld = OLE.this.m_ResourceManager.m_bMouseButton;
                OLE.this.m_ResourceManager.m_bMouseButton = true;
                OLE.this.m_ResourceManager.m_bClickOld = OLE.this.m_ResourceManager.m_bClick;
                OLE.this.m_ResourceManager.m_bClick = true;
                OLE.this.m_ResourceManager.m_bDragOld = OLE.this.m_ResourceManager.m_bDrag;
                OLE.this.m_ResourceManager.m_bDrag = true;
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                if (SwingUtilities.isRightMouseButton(evt)) {
                    OLE.this.m_ResourceManager.m_bKeyButtonOld = OLE.this.m_ResourceManager.m_bKeyButton;
                    OLE.this.m_ResourceManager.m_bKeyButton = true;
                    OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                }

            }

            public void mouseReleased(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_iCursorXOld = OLE.this.m_ResourceManager.m_iCursorX;
                OLE.this.m_ResourceManager.m_iCursorYOld = OLE.this.m_ResourceManager.m_iCursorY;
                OLE.this.m_ResourceManager.m_iCursorX = evt.getX();
                OLE.this.m_ResourceManager.m_iCursorY = evt.getY();
                OLE.this.m_ResourceManager.m_bMouseButtonOld = OLE.this.m_ResourceManager.m_bMouseButton;
                OLE.this.m_ResourceManager.m_bMouseButton = false;
                OLE.this.m_ResourceManager.m_bClickOld = OLE.this.m_ResourceManager.m_bClick;
                OLE.this.m_ResourceManager.m_bClick = false;
                OLE.this.m_ResourceManager.m_bDragOld = OLE.this.m_ResourceManager.m_bDrag;
                OLE.this.m_ResourceManager.m_bDrag = false;
                OLE.this.m_ResourceManager.m_bDragReady = false;
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                if (SwingUtilities.isRightMouseButton(evt)) {
                    OLE.this.m_ResourceManager.m_bKeyButtonOld = OLE.this.m_ResourceManager.m_bKeyButton;
                    OLE.this.m_ResourceManager.m_bKeyButton = false;
                    OLE.this.m_ResourceManager.m_bUpdateInterface = true;
                }

            }

            public void mouseEntered(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_bMouseEnter = true;
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
            }

            public void mouseExited(MouseEvent evt) {
                OLE.this.m_ResourceManager.m_bMouseEnter = false;
                OLE.this.m_ResourceManager.m_bUpdateInterface = true;
            }
        });
    }

    public void start() {
        this.m_CurrentGraphics = this.getGraphics();
        if (this.m_Thread == null) {
            this.m_Thread = new Thread(this);
            this.m_Thread.start();
        }

    }

    public void update(Graphics graphics) {
    }

    public void paint(Graphics graphics) {
    }

    public void run() {
        try {
            while(true) {
                this.m_ResourceManager.Do(this.m_CurrentGraphics, this);
                Thread var10000 = this.m_Thread;
                Thread.sleep(5L);
            }
        } catch (Exception e) {
        }
    }
}
