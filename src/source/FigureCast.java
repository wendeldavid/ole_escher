//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package source;

import jlib.JGraphicsEngine;
import jlib.JMath;
import jlib.JMatrix;
import jlib.JVec3;
import jlib.JVec4;

public class FigureCast extends Figure {
    final float MOVE_SPEED = 0.1F;
    final float DROP_SPEED = -0.25F;
    final float DEEP_AREA = -31.0F;
    final int BOTTOM_SEARCH = 32;
    final float JUMP_SPEED = 1.0F;
    final float Z_SHIFT = 0.01F;
    final float CAPACITY_LENGTH = 2.0F;
    final int STATE_NORMAL = 0;
    final int STATE_DROP = 1;
    final int STATE_JUMP = 2;
    private int m_iTime = 0;
    private Character2 m_ModelCharacter;
    private MySpace[][][] m_MySpace;
    private Goal m_Goal;
    private JVec3 m_vVelocity;
    private int m_iState = 0;
    private boolean m_bDelete = false;
    private boolean m_bReVisible = false;
    private boolean m_bDrawForced = false;
    private FigureBlock[] m_BlockVirtual;
    static JMatrix m_matLocal = new JMatrix();
    static JMatrix m_matTranslate = new JMatrix();
    static JMatrix m_matRotate = new JMatrix();
    static JMatrix m_matScale = new JMatrix();
    static JVec3 m_vTemp = new JVec3();
    static JVec3 m_vTemp2 = new JVec3();
    static JVec3 m_vTemp3 = new JVec3();
    static JVec3 m_vTemp4 = new JVec3();
    static JVec3 m_vTemp5 = new JVec3();
    static JVec4 m_v4Temp = new JVec4();
    static JMatrix m_matTemp = new JMatrix();
    static MySpace[] m_MySpaceTemp = new MySpace[4];
    static Figure[] m_FigureTemp = new Figure[4];
    static int[] m_iDirectionObjectTemp = new int[4];
    static JVec3[] m_vDirection = new JVec3[]{new JVec3(), new JVec3(), new JVec3(), new JVec3()};
    static JVec3[] m_vPositionAround = new JVec3[]{new JVec3(), new JVec3(), new JVec3(), new JVec3()};
    static JVec3[] m_vFaceDirection = new JVec3[]{new JVec3(-1.0F, 0.0F, 0.0F), new JVec3(0.0F, 0.0F, 1.0F), new JVec3(1.0F, 0.0F, 0.0F), new JVec3(0.0F, 0.0F, -1.0F)};

    public FigureCast() {
        super(2);
    }

    public boolean Initialize(ResourceManager var1, JVec3 var2) {
        super.Initialize(var1);
        this.m_ModelCharacter = var1.m_ModelCharacter;
        this.m_vPosition.Set(var2);
        this.m_Goal = new Goal();
        this.m_vVelocity = new JVec3();
        this.m_vVelocity.x = 0.0F;
        this.m_vVelocity.y = 0.0F;
        this.m_vVelocity.z = 0.0F;
        this.m_MySpace = new MySpace[5][4][5];

        int var3;
        int var4;
        int var5;
        for(var4 = 0; var4 < 4; ++var4) {
            for(var3 = 0; var3 < 5; ++var3) {
                for(var5 = 0; var5 < 5; ++var5) {
                    this.m_MySpace[var3][var4][var5] = new MySpace();
                }
            }
        }

        this.m_BlockVirtual = new FigureBlock[5];

        for(var3 = 0; var3 < 5; ++var3) {
            this.m_BlockVirtual[var3] = new FigureBlock();
            this.m_BlockVirtual[var3].Initialize(var1);
            this.m_BlockVirtual[var3].SetPosition(0.0F, 0.0F, 0.0F);
        }

        for(var3 = -2; var3 <= 2; ++var3) {
            for(var5 = -2; var5 <= 2; ++var5) {
                for(var4 = -1; var4 <= 2; ++var4) {
                    MySpace var6 = this.GetMySpace(var3, var4, var5);
                    var6.m_Figure = null;
                    var6.m_iX = var3;
                    var6.m_iY = var4;
                    var6.m_iZ = var5;
                    var6.m_bMine = false;
                    m_vTemp.x = var2.x + (float)var3;
                    m_vTemp.y = var2.y + (float)var4 + 1.0E-5F;
                    m_vTemp.z = var2.z + (float)var5;
                    var6.m_Figure = var1.m_GridSpaceManager.GetFigure(m_vTemp);
                }
            }
        }

        if (!this.SetGoalNear()) {
            this.m_vVelocity.y = -0.25F;
        }

        return true;
    }

    void ReleaseMyGoal() {
        this.m_Goal.m_bMine = false;
    }

    MySpace GetMySpace(int var1, int var2, int var3) {
        return this.m_MySpace[var1 + 2][var2 + 1][var3 + 2];
    }

    MySpace GetMySpace(Figure var1) {
        for(int var2 = -2; var2 <= 2; ++var2) {
            for(int var4 = -2; var4 <= 2; ++var4) {
                for(int var3 = -1; var3 <= 2; ++var3) {
                    if (this.GetMySpace(var2, var3, var4).m_Figure == var1) {
                        return this.GetMySpace(var2, var3, var4);
                    }
                }
            }
        }

        return null;
    }

    MySpace GetMySpaceSmall(Figure var1) {
        for(int var2 = -1; var2 <= 1; ++var2) {
            for(int var4 = -1; var4 <= 1; ++var4) {
                for(int var3 = -1; var3 <= 2; ++var3) {
                    if (this.GetMySpace(var2, var3, var4).m_Figure == var1) {
                        return this.GetMySpace(var2, var3, var4);
                    }
                }
            }
        }

        return null;
    }

    public boolean IsDelete() {
        return this.m_bDelete;
    }

    boolean SetGoalNear() {
        this.m_Goal.m_vTarget.Set(this.m_vPosition);
        Figure var1 = this.GetMySpace(0, 0, 0).m_Figure;
        if (var1 != null) {
            this.m_Goal.Set(var1, var1.m_vPosition, false);
            this.m_iState = 0;
        } else {
            var1 = this.GetMySpace(0, -1, 0).m_Figure;
            if (var1 == null) {
                this.m_Goal.Empty();
                this.m_iState = 1;
                return false;
            }

            this.m_Goal.Set(var1, var1.m_vPosition, false);
            this.m_iState = 0;
        }

        this.m_vVelocity.Set(this.m_Goal.m_vTarget);
        this.m_vVelocity.Minus(this.m_vPosition);
        this.m_vVelocity.Normalize();
        this.m_vVelocity.y = 0.0F;
        JVec3 var10000 = this.m_vVelocity;
        var10000.x *= 0.1F;
        var10000 = this.m_vVelocity;
        var10000.y *= 0.1F;
        var10000 = this.m_vVelocity;
        var10000.z *= 0.1F;
        return true;
    }

    Figure IsDrop(JVec3 var1) {
        JVec3 var2 = m_vTemp;
        JVec3 var3 = m_vTemp2;
        var2.Set(var1);
        var2 = this.m_RM.m_GridSpaceManager.NormalizePosition(var2);
        var3.Set(var2);
        var2.y += -0.25F;

        int var5;
        Figure var7;
        for(var5 = 0; var5 >= -1; --var5) {
            m_vTemp4.Set(0.0F, (float)var5, 0.0F);
            m_vTemp5.Set(0.0F, 0.0F, 0.0F);
            var7 = this.GetMySpace(0, var5, 0).m_Figure;
            if (var7 != null && var7.IsOn(m_vTemp4, var2, var3, m_vTemp5)) {
                return var7;
            }
        }

        for(var5 = 0; var5 >= -1; --var5) {
            for(int var4 = -1; var4 <= 1; ++var4) {
                for(int var6 = -1; var6 <= 1; ++var6) {
                    if (var4 != 0 || var6 != 0) {
                        var7 = this.GetMySpace(var4, var5, var6).m_Figure;
                        m_vTemp3.Set((float)var4, (float)var5, (float)var6);
                        m_vTemp4.Set((float)var4, (float)var5, (float)var6);
                        if (var7 != null && var7.IsOn(m_vTemp3, var2, var3, m_vTemp4)) {
                            return var7;
                        }
                    }
                }
            }
        }

        return null;
    }

    public void Update(JGraphicsEngine var1) {
        ++this.m_iTime;
        if (this.m_iTime > 15) {
            this.m_iTime = 0;
        }

        var1.m_bVisibleAll = true;
        var1.m_bVisiblePart = false;
        var1.m_bWriting = false;
        this.Render(var1);
        var1.m_bWriting = true;
        boolean var2 = var1.m_bVisibleAll;
        boolean var3 = var1.m_bVisiblePart;
        if (this.m_Goal.m_bMine && (this.m_RM.m_fRotateX != this.m_RM.m_fRotateXOld || this.m_RM.m_fRotateY != this.m_RM.m_fRotateYOld)) {
            this.m_Goal.m_bMine = false;
        }

        if (var2 && !this.m_bReVisible) {
            this.m_bReVisible = true;
        }

        JVec3 var10000;
        if (this.m_iState == 2) {
            var10000 = this.m_vVelocity;
            var10000.y -= 0.05F;
            if (this.m_vVelocity.y < 0.0F) {
                this.m_iState = 1;
                this.m_Goal.Set((Figure)null, this.m_vPosition, false);
            }
        }

        if (this.m_iState != 2 && this.IsJump(this.m_vPosition)) {
            this.m_bReVisible = false;
            this.m_iState = 2;
            this.m_vVelocity.y = 1.0F;
        }

        if (this.m_iState != 2 && var2) {
            this.m_bDrawForced = false;
        }

        if (this.m_iState != 2 && !this.m_Goal.m_bMine) {
            if (this.m_iState != 1) {
                this.m_vVelocity.Set(0.0F, 0.0F, 0.0F);
            }

            Figure var4 = this.IsDrop(this.m_vPosition);
            if (var4 == null && !this.m_Goal.m_bMine) {
                if (this.m_iState != 1) {
                    this.m_bReVisible = false;
                }

                if (this.m_iState == 0) {
                    this.m_vVelocity.y = -0.25F;
                }

                var10000 = this.m_vVelocity;
                var10000.y -= 0.01F;
                if (this.m_vVelocity.y < -0.25F) {
                    this.m_vVelocity.y = -0.25F;
                }

                this.m_iState = 1;
                this.m_Goal.Empty();
            } else {
                if (var4 != null) {
                    boolean var5 = false;
                    if (this.m_iState == 1) {
                        var5 = true;
                    }

                    var4.SetOn(this.m_vPosition, this.m_RM.m_GraphicsEngine.m_matTotal, var5);
                    if (var5) {
                        this.m_Goal.Set(var4, var4.m_vPosition, false);
                    }
                }

                this.m_iState = 0;
                this.m_vVelocity.Set(this.m_Goal.m_vTarget);
                this.m_vVelocity.Minus(this.m_vPosition);
                this.m_vVelocity.Normalize();
                this.m_vVelocity.y = 0.0F;
                var10000 = this.m_vVelocity;
                var10000.x *= 0.1F;
                var10000 = this.m_vVelocity;
                var10000.y *= 0.1F;
                var10000 = this.m_vVelocity;
                var10000.z *= 0.1F;
            }
        }

        if ((this.m_iState == 1 || this.m_iState == 2) && this.IsPassInAir(GetDirectionFromAngle(this.m_fAngle)) != null) {
            this.m_iState = 1;
            this.m_vVelocity.x = 0.0F;
            this.m_vVelocity.y = -0.25F;
            this.m_vVelocity.z = 0.0F;
            this.m_bDrawForced = false;
        }

        this.m_vPosition.Plus(this.m_vVelocity);
        JVec4 var9 = m_v4Temp;
        var9.Set(this.m_vPosition);
        var9.Multiply(this.m_RM.m_GraphicsEngine.m_matTotal);
        this.m_vPositionOnScreen.x = var9.x;
        this.m_vPositionOnScreen.y = var9.y;
        this.m_vPositionOnScreen.z = var9.z;
        this.AI(var2, var3);

        for(int var10 = -1; var10 <= 1; ++var10) {
            for(int var6 = -1; var6 <= 1; ++var6) {
                for(int var7 = -1; var7 <= 1; ++var7) {
                    m_vTemp3.Set(this.m_vPosition);
                    var10000 = m_vTemp3;
                    var10000.x += (float)var6;
                    var10000 = m_vTemp3;
                    var10000.y += (float)var10;
                    var10000 = m_vTemp3;
                    var10000.z += (float)var7;
                    GridSpace var8 = this.m_RM.m_GridSpaceManager.GetSpace(m_vTemp3);
                    if (var8 == null) {
                        this.m_bDelete = true;
                        return;
                    }
                }
            }
        }

    }

    public void AI(boolean var1, boolean var2) {
        for(int var4 = -2; var4 <= 2; ++var4) {
            for(int var5 = -2; var5 <= 2; ++var5) {
                for(int var6 = -1; var6 <= 2; ++var6) {
                    MySpace var3 = this.GetMySpace(var4, var6, var5);
                    m_vTemp.x = this.m_vPosition.x + (float)var4;
                    m_vTemp.y = this.m_vPosition.y + (float)var6 + 1.0E-5F;
                    m_vTemp.z = this.m_vPosition.z + (float)var5;
                    var3.m_Figure = this.m_RM.m_GridSpaceManager.GetFigure(m_vTemp);
                    var3.m_bMine = false;
                }
            }
        }

        switch(this.m_iState) {
            case 0:
                this.Move(var1, var2);
                break;
            case 1:
                this.Drop();
                this.InterpretationDrop(var1, var2);
                break;
            case 2:
                this.Jump();
                this.InterpretationJump(var1, var2);
        }

        if (this.m_vPosition.y < -31.0F) {
            this.m_bDelete = true;
        }

    }

    public void Move(boolean var1, boolean var2) {
        this.InterpretationMove(var1, var2);
        if (this.m_iState == 0 && this.m_vVelocity.Magnitude() != 0.0F) {
            this.m_fAngle = JMath.GetAngle(this.m_vVelocity.x, this.m_vVelocity.z);
            this.m_fAngle = 360.0F - this.m_fAngle - 90.0F;
            if (this.m_fAngle > 360.0F) {
                this.m_fAngle -= 360.0F;
            }

            if (this.m_fAngle < 0.0F) {
                this.m_fAngle += 360.0F;
            }
        }

        m_vTemp.Set(this.m_Goal.m_vTarget);
        m_vTemp.Minus(this.m_vPosition);
        m_vTemp.y = 0.0F;
        float var3 = m_vTemp.Magnitude();
        if (var3 <= 0.1F) {
            this.InterpretationGoal(var1, var2);
            this.SetGoal();
        }

    }

    public void Drop() {
        if (this.SetGoalNear()) {
            m_vTemp.Set(this.m_Goal.m_vTarget);
            m_vTemp.Minus(this.m_vPosition);
            m_vTemp.y = 0.0F;
            float var1 = m_vTemp.Magnitude();
            if (var1 <= 0.1F) {
                this.m_vVelocity.x = 0.0F;
                this.m_vVelocity.y = 0.0F;
                this.m_vVelocity.z = 0.0F;
                this.SetGoal();
                this.m_iState = 0;
            }
        }

    }

    public void Jump() {
    }

    boolean IsJump(JVec3 var1) {
        JVec3 var3 = m_vTemp;
        JVec3 var4 = m_vTemp2;
        var3.Set(var1);
        var3 = this.m_RM.m_GridSpaceManager.NormalizePosition(var3);
        var4.Set(var1);
        Figure var2 = this.GetMySpace(0, -1, 0).m_Figure;
        m_vTemp4.Set(0.0F, -1.0F, 0.0F);
        m_vTemp5.Set(0.0F, 0.0F, 0.0F);
        return var2 != null && var2.m_FigureSub != null && var2.m_FigureSub.m_iID == 4 && !var2.m_FigureSub.IsOn(m_vTemp4, var3, var4, m_vTemp5);
    }

    MySpace IsPass(int var1) {
        Figure var2 = this.GetMySpace(0, -1, 0).m_Figure;
        Figure var3 = this.GetMySpace(0, 0, 0).m_Figure;
        m_iDirectionObjectTemp[0] = 255;
        m_iDirectionObjectTemp[1] = 255;
        m_iDirectionObjectTemp[2] = 255;

        for(int var4 = -1; var4 <= 2; ++var4) {
            int var5 = var4 + 1;
            switch(var1) {
                case 0:
                    m_MySpaceTemp[var5] = this.GetMySpace(-1, var4, 0);
                    m_FigureTemp[var5] = m_MySpaceTemp[var5].m_Figure;
                    break;
                case 1:
                    m_MySpaceTemp[var5] = this.GetMySpace(0, var4, 1);
                    m_FigureTemp[var5] = m_MySpaceTemp[var5].m_Figure;
                    break;
                case 2:
                    m_MySpaceTemp[var5] = this.GetMySpace(1, var4, 0);
                    m_FigureTemp[var5] = m_MySpaceTemp[var5].m_Figure;
                    break;
                case 3:
                    m_MySpaceTemp[var5] = this.GetMySpace(0, var4, -1);
                    m_FigureTemp[var5] = m_MySpaceTemp[var5].m_Figure;
            }

            if (m_FigureTemp[var5] != null) {
                m_iDirectionObjectTemp[var5] = GetDirectionFromAngle(m_FigureTemp[var5].m_fAngle);
            }
        }

        if (m_FigureTemp[0] != null && m_FigureTemp[1] == null && m_FigureTemp[2] == null) {
            return m_MySpaceTemp[0];
        } else {
            return null;
        }
    }

    Figure IsPassInAir(int var1) {
        float var2 = 0.0F;
        float var3 = 0.0F;
        float var4 = 0.0F;
        switch(var1) {
            case 0:
                var2 = -1.0F;
                var4 = 0.0F;
                break;
            case 1:
                var2 = 0.0F;
                var4 = 1.0F;
                break;
            case 2:
                var2 = 1.0F;
                var4 = 0.0F;
                break;
            case 3:
                var2 = 0.0F;
                var4 = -1.0F;
        }

        for(int var5 = 1; var5 <= 2; ++var5) {
            m_vTemp.Set(this.m_vPosition);
            JVec3 var10000 = m_vTemp;
            var10000.x += var2;
            var10000 = m_vTemp;
            var10000.y -= 0.5F;
            var10000 = m_vTemp;
            var10000.z += var4;
            var10000 = m_vTemp;
            var10000.y += 1.0F * (float)var5;
            GridSpace var6 = this.m_RM.m_GridSpaceManager.GetSpace(m_vTemp);
            if (var6 == null) {
                this.m_bDelete = true;
                return null;
            }

            Figure var7 = var6.m_Figure;
            if (var7 != null && var7.m_iID == 1) {
                return var7;
            }
        }

        return null;
    }

    void SetGoal() {
        int var2 = GetDirectionFromAngle(this.m_fAngle);
        boolean var4 = false;
        MySpace var5 = null;

        for(int var1 = 0; var1 < 4; ++var1) {
            if ((var5 = this.IsPass((var2 + 3) % 4)) != null) {
                var2 = (var2 + 3) % 4;
                break;
            }

            if ((var5 = this.IsPass(var2)) != null) {
                break;
            }

            var2 = (var2 + 1) % 4;
        }

        if (var5 == null) {
            this.m_Goal.m_vTarget.Set(this.m_vPosition);
            this.m_fAngle = GetAngleFromDirection(var2);
        } else {
            if (var5 != null && var5.m_Figure != null) {
                JVec3 var9 = m_vTemp;
                var9.Set(this.m_vPosition);
                this.m_RM.m_GridSpaceManager.RasterizatePosition(var9);
                int var6 = (int)var9.x;
                int var7 = (int)var9.y;
                int var8 = (int)var9.z;
                var9.Set(this.m_vPosition);
                this.m_RM.m_GridSpaceManager.NormalizePosition(var9);
                JVec3 var10 = m_vTemp3;
                var10.Set((float)(var6 + var5.m_iX), (float)(var7 + var5.m_iY), (float)(var8 + var5.m_iZ));
                this.m_Goal.Set(var5.m_Figure, var10, var5.m_bMine);
                this.m_fAngle = GetAngleFromDirection(var2);
                this.m_vVelocity.Set(this.m_Goal.m_vTarget);
                this.m_vVelocity.Minus(this.m_vPosition);
                this.m_vVelocity.Normalize();
                this.m_vVelocity.y = 0.0F;
                JVec3 var10000 = this.m_vVelocity;
                var10000.x *= 0.1F;
                var10000 = this.m_vVelocity;
                var10000.y *= 0.1F;
                var10000 = this.m_vVelocity;
                var10000.z *= 0.1F;
            }

        }
    }

    void InterpretationGoal(boolean var1, boolean var2) {
        Figure var3 = this.GetMySpace(0, 0, 0).m_Figure;
        if (var3 == null) {
            var3 = this.GetMySpace(0, -1, 0).m_Figure;
        }

        if (var3 == null || var3.m_FigureSub == null) {
            if (var2 && var3 != null) {
                this.m_RM.m_Sight.Setup(this.m_RM.m_GraphicsEngine, this.m_vPositionOnScreen);
                this.m_RM.m_FaceDoubleViewY.Update(this.m_RM.m_GraphicsEngine, this.m_vPosition);
                int var7 = this.m_RM.m_Sight.GetFigureSize();

                int var4;
                int var5;
                int var6;
                for(var4 = -2; var4 <= 2; ++var4) {
                    for(var6 = -2; var6 <= 2; ++var6) {
                        for(var5 = -1; var5 <= 2; ++var5) {
                            MySpace var8 = this.GetMySpace(var4, var5, var6);
                            m_vTemp.x = this.m_vPosition.x + (float)var4;
                            m_vTemp.y = this.m_vPosition.y + (float)var5 + 1.0E-5F;
                            m_vTemp.z = this.m_vPosition.z + (float)var6;
                            var8.m_Figure = this.m_RM.m_GridSpaceManager.GetFigure(m_vTemp);
                            var8.m_bMine = false;
                        }
                    }
                }

                if (var3.m_iID == 1) {
                    JVec3 var9 = m_vTemp;
                    var9.Set(var3.m_vPosition);
                    GraphicsEngine var12 = this.m_RM.m_GraphicsEngine;
                    Figure var10 = this.GetMySpace(-1, -1, 0).m_Figure;
                    Figure var11 = this.GetMySpace(-2, -1, 0).m_Figure;
                    boolean var13;
                    if (var10 == null && var11 != null && var11.m_iID == 1) {
                        var12.m_bVisiblePart = false;
                        var12.m_bWriting = false;
                        this.m_BlockVirtual[0].SetPosition(var9.x - 1.0F, var9.y, var9.z);
                        this.m_BlockVirtual[0].Render(var12);
                        var12.m_bWriting = true;
                        var13 = var12.m_bVisiblePart;
                        if (!var13) {
                            this.GetMySpace(-1, -1, 0).m_bMine = true;
                            this.GetMySpace(-1, 0, 0).m_bMine = true;
                            this.GetMySpace(-1, 1, 0).m_bMine = true;
                            this.GetMySpace(-1, 2, 0).m_bMine = true;
                            this.m_BlockVirtual[0].SetPosition(var9.x - 2.0F, var9.y, var9.z);
                            this.GetMySpace(-1, -1, 0).m_Figure = this.m_BlockVirtual[0];
                            m_vTemp2.x = var9.x - 2.0F;
                            m_vTemp2.y = var9.y + 0.5F;
                            m_vTemp2.z = var9.z;
                            this.m_Goal.Set(this.m_BlockVirtual[0], m_vTemp2, true);
                            return;
                        }
                    }

                    var10 = this.GetMySpace(0, -1, 1).m_Figure;
                    var11 = this.GetMySpace(0, -1, 2).m_Figure;
                    if (var10 == null && var11 != null && var11.m_iID == 1) {
                        var12.m_bVisiblePart = false;
                        var12.m_bWriting = false;
                        this.m_BlockVirtual[1].SetPosition(var9.x, var9.y, var9.z + 1.0F);
                        this.m_BlockVirtual[1].Render(var12);
                        var12.m_bWriting = true;
                        var13 = var12.m_bVisiblePart;
                        if (!var13) {
                            this.GetMySpace(0, -1, 1).m_bMine = true;
                            this.GetMySpace(0, 0, 1).m_bMine = true;
                            this.GetMySpace(0, 1, 1).m_bMine = true;
                            this.GetMySpace(0, 2, 1).m_bMine = true;
                            this.m_BlockVirtual[1].SetPosition(var9.x, var9.y, var9.z + 2.0F);
                            this.GetMySpace(0, -1, 1).m_Figure = this.m_BlockVirtual[1];
                            m_vTemp2.x = var9.x;
                            m_vTemp2.y = var9.y + 0.5F;
                            m_vTemp2.z = var9.z + 2.0F;
                            this.m_Goal.Set(this.m_BlockVirtual[1], m_vTemp2, true);
                            return;
                        }
                    }

                    var10 = this.GetMySpace(1, -1, 0).m_Figure;
                    var11 = this.GetMySpace(2, -1, 0).m_Figure;
                    if (var10 == null && var11 != null && var11.m_iID == 1) {
                        var12.m_bVisiblePart = false;
                        var12.m_bWriting = false;
                        this.m_BlockVirtual[2].SetPosition(var9.x + 1.0F, var9.y, var9.z);
                        this.m_BlockVirtual[2].Render(var12);
                        var12.m_bWriting = true;
                        var13 = var12.m_bVisiblePart;
                        if (!var13) {
                            this.GetMySpace(1, -1, 0).m_bMine = true;
                            this.GetMySpace(1, 0, 0).m_bMine = true;
                            this.GetMySpace(1, 1, 0).m_bMine = true;
                            this.GetMySpace(1, 2, 0).m_bMine = true;
                            this.m_BlockVirtual[2].SetPosition(var9.x + 2.0F, var9.y, var9.z);
                            this.GetMySpace(1, -1, 0).m_Figure = this.m_BlockVirtual[2];
                            m_vTemp2.x = var9.x + 2.0F;
                            m_vTemp2.y = var9.y + 0.5F;
                            m_vTemp2.z = var9.z;
                            this.m_Goal.Set(this.m_BlockVirtual[2], m_vTemp2, true);
                            return;
                        }
                    }

                    var10 = this.GetMySpace(0, -1, -1).m_Figure;
                    var11 = this.GetMySpace(0, -1, -2).m_Figure;
                    if (var10 == null && var11 != null && var11.m_iID == 1) {
                        var12.m_bVisiblePart = false;
                        var12.m_bWriting = false;
                        this.m_BlockVirtual[3].SetPosition(var9.x, var9.y, var9.z - 1.0F);
                        this.m_BlockVirtual[3].Render(var12);
                        var12.m_bWriting = true;
                        var13 = var12.m_bVisiblePart;
                        if (!var13) {
                            this.GetMySpace(0, -1, -1).m_bMine = true;
                            this.GetMySpace(0, 0, -1).m_bMine = true;
                            this.GetMySpace(0, 1, -1).m_bMine = true;
                            this.GetMySpace(0, 2, -1).m_bMine = true;
                            this.m_BlockVirtual[3].SetPosition(var9.x, var9.y, var9.z - 2.0F);
                            this.GetMySpace(0, -1, -1).m_Figure = this.m_BlockVirtual[3];
                            m_vTemp2.x = var9.x;
                            m_vTemp2.y = var9.y + 0.5F;
                            m_vTemp2.z = var9.z - 2.0F;
                            this.m_Goal.Set(this.m_BlockVirtual[3], m_vTemp2, true);
                            return;
                        }
                    }
                }

                Figure var16;
                JVec4 var17;
                int var23;
                if (this.m_RM.m_fRotateX == -90.0F) {
                    for(var4 = -1; var4 <= 1; ++var4) {
                        for(var6 = -1; var6 <= 1; ++var6) {
                            for(var5 = 0; var5 <= 2; ++var5) {
                                this.GetMySpace(var4, var5, var6).m_Figure = null;
                            }
                        }
                    }

                    var17 = m_v4Temp;
                    byte var18 = -1;
                    if (var3.m_iID == 5) {
                        m_vDirection[0].Set(-1.0F, (float)(var18 + 0) + 1.0E-5F, 0.0F);
                        m_vDirection[1].Set(0.0F, (float)(var18 + 0) + 1.0E-5F, 1.0F);
                        m_vDirection[2].Set(1.0F, (float)(var18 + 0) + 1.0E-5F, 0.0F);
                        m_vDirection[3].Set(0.0F, (float)(var18 + 0) + 1.0E-5F, -1.0F);
                    } else {
                        m_vDirection[0].Set(-1.0F, (float)(var18 + 1) + 1.0E-5F, 0.0F);
                        m_vDirection[1].Set(0.0F, (float)(var18 + 1) + 1.0E-5F, 1.0F);
                        m_vDirection[2].Set(1.0F, (float)(var18 + 1) + 1.0E-5F, 0.0F);
                        m_vDirection[3].Set(0.0F, (float)(var18 + 1) + 1.0E-5F, -1.0F);
                    }

                    int var19;
                    for(var19 = 0; var19 < 4; ++var19) {
                        var17.Set(this.m_Goal.m_vTarget);
                        var17.Plus(m_vDirection[var19]);
                        var17.y -= 0.5F;
                        m_vTemp2.x = var17.x;
                        m_vTemp2.y = var17.y;
                        m_vTemp2.z = var17.z;
                        var17.Multiply(this.m_RM.m_GraphicsEngine.m_matTotal);
                        m_vPositionAround[var19].x = var17.x;
                        m_vPositionAround[var19].y = var17.y;
                    }

                    label183:
                    for(var19 = 0; var19 < 4; ++var19) {
                        for(var23 = 0; var23 < var7; ++var23) {
                            var16 = this.m_RM.m_Sight.GetFigure(var23);
                            if (var16.m_iID != 0) {
                                JVec3 var14 = m_vTemp3;
                                var14.x = var16.m_vPositionOnScreen.x - m_vPositionAround[var19].x;
                                var14.y = var16.m_vPositionOnScreen.y - m_vPositionAround[var19].y;
                                var14.z = 0.0F;
                                if (var14.Magnitude() < 2.0F) {
                                    switch(var19) {
                                        case 0:
                                            if (this.GetMySpace(-1, var18, 0).m_Figure != var16) {
                                                this.GetMySpace(-1, var18, 0).m_bMine = true;
                                                this.GetMySpace(-1, var18, 0).m_Figure = var16;
                                            }
                                            continue label183;
                                        case 1:
                                            if (this.GetMySpace(0, var18, 1).m_Figure != var16) {
                                                this.GetMySpace(0, var18, 1).m_bMine = true;
                                                this.GetMySpace(0, var18, 1).m_Figure = var16;
                                            }
                                            continue label183;
                                        case 2:
                                            if (this.GetMySpace(1, var18, 0).m_Figure != var16) {
                                                this.GetMySpace(1, var18, 0).m_bMine = true;
                                                this.GetMySpace(1, var18, 0).m_Figure = var16;
                                            }
                                            continue label183;
                                        case 3:
                                            if (this.GetMySpace(0, var18, -1).m_Figure != var16) {
                                                this.GetMySpace(0, var18, -1).m_bMine = true;
                                                this.GetMySpace(0, var18, -1).m_Figure = var16;
                                            }
                                        default:
                                            continue label183;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    var17 = m_v4Temp;
                    byte var21 = -1;
                    if (var3.m_iID == 5) {
                        m_vDirection[0].Set(-1.0F, (float)(var21 + 0) + 1.0E-5F, 0.0F);
                        m_vDirection[1].Set(0.0F, (float)(var21 + 0) + 1.0E-5F, 1.0F);
                        m_vDirection[2].Set(1.0F, (float)(var21 + 0) + 1.0E-5F, 0.0F);
                        m_vDirection[3].Set(0.0F, (float)(var21 + 0) + 1.0E-5F, -1.0F);
                    } else {
                        m_vDirection[0].Set(-1.0F, (float)(var21 + 1) + 1.0E-5F, 0.0F);
                        m_vDirection[1].Set(0.0F, (float)(var21 + 1) + 1.0E-5F, 1.0F);
                        m_vDirection[2].Set(1.0F, (float)(var21 + 1) + 1.0E-5F, 0.0F);
                        m_vDirection[3].Set(0.0F, (float)(var21 + 1) + 1.0E-5F, -1.0F);
                    }

                    for(var23 = 0; var23 < 4; ++var23) {
                        var17.Set(this.m_Goal.m_vTarget);
                        var17.Plus(m_vDirection[var23]);
                        var17.y -= 0.5F;
                        m_vTemp2.x = var17.x;
                        m_vTemp2.y = var17.y;
                        m_vTemp2.z = var17.z;
                        var17.Multiply(this.m_RM.m_GraphicsEngine.m_matTotal);
                        m_vPositionAround[var23].x = var17.x;
                        m_vPositionAround[var23].y = var17.y;
                    }

                    label163:
                    for(var23 = 0; var23 < 4; ++var23) {
                        for(int var22 = 0; var22 < var7; ++var22) {
                            var16 = this.m_RM.m_Sight.GetFigure(var22);
                            if (var16.m_iID != 0) {
                                JVec3 var15 = m_vTemp3;
                                var15.x = var16.m_vPositionOnScreen.x - m_vPositionAround[var23].x;
                                var15.y = var16.m_vPositionOnScreen.y - m_vPositionAround[var23].y;
                                var15.z = 0.0F;
                                if (var15.Magnitude() < 2.0F && this.GetMySpaceSmall(var16) == null) {
                                    var15.x = this.m_Goal.m_vTarget.x - var16.m_vPosition.x;
                                    var15.y = this.m_Goal.m_vTarget.y - var16.m_vPosition.y;
                                    var15.z = this.m_Goal.m_vTarget.z - var16.m_vPosition.z;
                                    var15.Normalize();
                                    MySpace var20;
                                    switch(var23) {
                                        case 0:
                                            var20 = this.GetMySpace(-1, var21, 0);
                                            if (var20.m_Figure != var16 && m_vFaceDirection[var23].DotProduct(var15) < 0.0F) {
                                                var20.m_bMine = true;
                                                var20.m_Figure = var16;
                                            }
                                            continue label163;
                                        case 1:
                                            var20 = this.GetMySpace(0, var21, 1);
                                            if (var20.m_Figure != var16 && m_vFaceDirection[var23].DotProduct(var15) < 0.0F) {
                                                var20.m_bMine = true;
                                                var20.m_Figure = var16;
                                            }
                                            continue label163;
                                        case 2:
                                            var20 = this.GetMySpace(1, var21, 0);
                                            if (var20.m_Figure != var16 && m_vFaceDirection[var23].DotProduct(var15) < 0.0F) {
                                                var20.m_bMine = true;
                                                var20.m_Figure = var16;
                                            }
                                            continue label163;
                                        case 3:
                                            var20 = this.GetMySpace(0, var21, -1);
                                            if (var20.m_Figure != var16 && m_vFaceDirection[var23].DotProduct(var15) < 0.0F) {
                                                var20.m_bMine = true;
                                                var20.m_Figure = var16;
                                            }
                                        default:
                                            continue label163;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    void InterpretationMove(boolean var1, boolean var2) {
        if (this.m_Goal.m_bMine) {
            if (this.m_Goal.m_Figure != null && this.m_Goal.m_FigureStart != null) {
                Figure var3 = this.m_Goal.m_Figure;
                Figure var4 = this.m_Goal.m_FigureStart;
                Figure var5;
                Figure var6;
                if (this.m_RM.m_fRotateX < 0.0F) {
                    if (var3.m_vPositionOnScreen.z <= var4.m_vPositionOnScreen.z) {
                        var5 = var3;
                        var6 = var4;
                    } else {
                        var5 = var4;
                        var6 = var3;
                    }
                } else if (var3.m_vPositionOnScreen.z < var4.m_vPositionOnScreen.z) {
                    var5 = var4;
                    var6 = var3;
                } else {
                    var5 = var3;
                    var6 = var4;
                }

                this.m_RM.m_GraphicsEngine.ClearWorking();
                this.m_RM.m_GraphicsEngine.m_bWorking = true;
                var5.RenderWorking(this.m_RM.m_GraphicsEngine);
                this.m_RM.m_GraphicsEngine.m_bWorking = false;
                this.m_RM.m_GraphicsEngine.m_bWorking = true;
                this.m_RM.m_GraphicsEngine.m_bCrossTest = true;
                this.m_RM.m_GraphicsEngine.m_bVisibleAll = true;
                this.m_RM.m_GraphicsEngine.m_bVisiblePart = false;
                this.Render(this.m_RM.m_GraphicsEngine);
                boolean var7 = this.m_RM.m_GraphicsEngine.m_bVisiblePart;
                this.m_RM.m_GraphicsEngine.m_bCrossTest = false;
                this.m_RM.m_GraphicsEngine.m_bWorking = false;
                boolean var8 = false;
                if (this.m_iState == 1) {
                    var8 = true;
                }

                if (var7) {
                    var5.SetOn(this.m_vPosition, this.m_RM.m_GraphicsEngine.m_matTotal, var8);
                    if (var5 == var3) {
                        this.m_Goal.Set(var3, var3.m_vPosition, true);
                    }
                } else {
                    var6.SetOn(this.m_vPosition, this.m_RM.m_GraphicsEngine.m_matTotal, var8);
                    if (var6 == var3) {
                        this.m_Goal.Set(var3, var3.m_vPosition, true);
                    }
                }

            }
        }
    }

    void InterpretationDrop(boolean var1, boolean var2) {
        if (var2) {
            if (this.m_iState == 1 && this.m_bReVisible) {
                int var3 = (int)this.m_vPositionOnScreen.x;
                int var5 = (int)this.m_vPositionOnScreen.y;
                int var6 = var5 + 32;
                Figure var7 = null;
                Figure var8 = null;
                int var9 = 100;
                GraphicsEngine var10 = this.m_RM.m_GraphicsEngine;
                if (var3 < 0 || var3 >= this.m_RM.m_GraphicsEngine.m_iWidth) {
                    return;
                }

                if (var5 < 0) {
                    var5 = 0;
                }

                if (var5 >= this.m_RM.m_GraphicsEngine.m_iHeight) {
                    var5 = this.m_RM.m_GraphicsEngine.m_iHeight;
                }

                if (var6 < 0) {
                    var6 = 0;
                }

                if (var6 >= this.m_RM.m_GraphicsEngine.m_iHeight) {
                    var6 = this.m_RM.m_GraphicsEngine.m_iHeight;
                }

                for(int var4 = var5; var4 < var6; ++var4) {
                    int var11 = var10.GetID(var3, var4);
                    if (var11 == 1) {
                        var7 = ((FaceDoubleBlock)var10.GetDataObject(var3, var4)).m_Figure;
                        int var12 = (int)Math.abs(var7.m_vPositionOnScreen.x - this.m_vPositionOnScreen.x);
                        if (var12 < var9) {
                            var9 = var12;
                            var8 = var7;
                        }
                    }
                }

                if (var8 != null) {
                    JVec3 var19 = m_vTemp;
                    JVec3 var13 = m_vTemp3;
                    JVec3 var14 = null;
                    var13.Set(0.0F, 0.0F, -1.0F);
                    var13.MatrixMultiply(var10.m_matTotal);
                    this.m_RM.m_FaceDoubleViewY.Update(var10, var8.m_vPosition);
                    if (this.m_RM.m_FaceDoubleViewY.face[0].GetCrossPointLineToFace(var19, var13, this.m_vPosition)) {
                        var14 = var19;
                    }

                    if (var14 == null) {
                        return;
                    }

                    JVec3 var16 = m_vTemp3;
                    var16.Set(var14);

                    for(var3 = -1; var3 <= 1; ++var3) {
                        for(int var17 = -1; var17 <= 1; ++var17) {
                            for(int var18 = 0; var18 <= 2; ++var18) {
                                var16.Set(var14);
                                var16.x += (float)var3;
                                var16.y += (float)var18;
                                var16.z += (float)var17;
                                GridSpace var15 = this.m_RM.m_GridSpaceManager.GetSpace(var16);
                                if (var15 == null) {
                                    this.m_bDelete = true;
                                    return;
                                }

                                var7 = var15.m_Figure;
                                if (var7 != null) {
                                    return;
                                }
                            }
                        }
                    }

                    m_vTemp2.Set(this.m_vPosition);
                    var10.m_bVisibleAll = true;
                    var10.m_bVisiblePart = false;
                    var10.m_bWriting = false;
                    this.m_vPosition.Set(var14);
                    this.Render(var10);
                    var10.m_bWriting = true;
                    if (var1 && !var10.m_bVisibleAll || !var1 && var10.m_bVisibleAll) {
                        this.m_vPosition.Set(m_vTemp2);
                    }
                }
            }

        }
    }

    void InterpretationJump(boolean var1, boolean var2) {
        if (var2) {
            if (this.m_bReVisible) {
                this.m_bDrawForced = true;
            }

            GraphicsEngine var3 = this.m_RM.m_GraphicsEngine;
            var3.m_bWriting = false;
            var3.m_bUseMinZ = true;
            var3.ReadyMinZ();
            this.Render(var3);
            if (var3.m_fMinZ != 999999.0F) {
                JMatrix var4 = m_matTemp;
                var4.Set(var3.m_matTotal);
                var4.Invert();
                JVec4 var5 = m_v4Temp;
                var5.x = this.m_vPositionOnScreen.x;
                var5.y = this.m_vPositionOnScreen.y;
                var5.z = var3.m_fMinZ - 0.01F;
                var5.w = 1.0F;
                var5.Multiply(var4);
                this.m_vPosition.Set(var5.x, var5.y, var5.z);
            }

            var3.m_bWriting = true;
            var3.m_bUseMinZ = false;
        }
    }

    public void Render(JGraphicsEngine var1) {
        m_matRotate.SetRotateY(JMath.Radian(this.m_fAngle));
        m_matTranslate.SetTranslate(this.m_vPosition);
        m_matRotate.Multiply(m_matTranslate);
        m_matLocal.Set(m_matRotate);
        if (this.m_bDrawForced) {
            var1.m_bEnableDepth = false;
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
            var1.m_bEnableDepth = true;
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
        } else {
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
        }

    }

    public void Render(JGraphicsEngine var1, float var2) {
        m_matScale.SetScale(var2);
        m_matRotate.SetRotateY(JMath.Radian(this.m_fAngle));
        m_matTranslate.SetTranslate(this.m_vPosition);
        m_matRotate.Multiply(m_matTranslate);
        m_matScale.Multiply(m_matRotate);
        m_matLocal.Set(m_matScale);
        if (this.m_bDrawForced) {
            var1.m_bEnableDepth = false;
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
            var1.m_bEnableDepth = true;
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
        } else {
            this.m_ModelCharacter.Render(var1, m_matLocal, this.m_iID, this, this.m_iTime);
        }

    }

    class MySpace {
        public Figure m_Figure;
        public int m_iX;
        public int m_iY;
        public int m_iZ;
        public boolean m_bMine;

        MySpace() {
        }
    }

    public class Goal {
        public JVec3 m_vTarget = new JVec3();
        public boolean m_bMine = false;
        public Figure m_Figure = null;
        public Figure m_FigureStart = null;

        public Goal() {
            this.m_vTarget.x = 0.0F;
            this.m_vTarget.y = 0.0F;
            this.m_vTarget.z = 0.0F;
        }

        void Empty() {
            this.m_Figure = null;
            this.m_FigureStart = null;
            this.m_bMine = false;
        }

        void Set(JVec3 var1) {
            this.m_vTarget.Set(var1);
            this.m_bMine = false;
        }

        void Set(Figure var1, JVec3 var2, boolean var3) {
            this.m_FigureStart = this.m_Figure;
            this.m_Figure = var1;
            this.m_vTarget.Set(var2);
            JVec3 var10000 = this.m_vTarget;
            var10000.y += 0.5F;
            this.m_bMine = var3;
        }
    }
}
