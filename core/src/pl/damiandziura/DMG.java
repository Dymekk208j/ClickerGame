package pl.damiandziura;


import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class DMG extends Label {
    int a;
    private float time = 1.0f;
    private float alpha = 1.0f;
    private int pos_X;
    private int pos_Y;
    boolean showing = false;
    float r = 0;

    public DMG(LabelStyle labelStyle)
    {
        super("", labelStyle);


    }

    public boolean Update(float DT)
    {
        if(time > 0 && alpha > 0)
        {
            time -= DT;
            alpha -= 0.02f;
            showing = true;
            this.setColor(r, 0,0, alpha);
        }else
        {
            showing = false;
        }
        return showing;
    }

    public void setNotification(String DMG_message, int x, int y, boolean crit)
    {
        String mes = DMG_message;
        if(crit)
        {
            r = 1;
            this.setFontScale(4f);
            mes = mes + " !";

        }else
        {
            r = 0;
            this.setFontScale(2f);
        }
        this.setText(mes);
        this.setX((float)x);
        this.setY((float)y);
        alpha = 1.0f;
        time = 1.0f;
        showing = true;
    }

    public boolean isShowing() {
        return showing;
    }
}
