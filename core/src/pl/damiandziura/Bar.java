package pl.damiandziura;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * Created by Dymek on 26.06.2017.
 */

public class Bar extends Image {
    private int value;
    private int max_value;
    private int HEIGHT;
    private int WIDTH;
    private int POSITION_X;
    private int POSITION_Y;

    public Bar(int WIDTH, int HEIGHT, int max_value, int value, int POSITION_X, int POSITION_Y, String dot_path)
    {
        super(new Texture(dot_path));
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.value = value;
        this.max_value = max_value;
        this.POSITION_X = POSITION_X;
        this.POSITION_Y = POSITION_Y;
        update(value, max_value);
    }


    public void update(int value, int max_value)
    {
        float valuMin = value;
        float valuMax = max_value;
        this.max_value = max_value;
        this.value = value;
        float buffor_width = WIDTH * (valuMin/valuMax);
        setSize(buffor_width, HEIGHT);
        setPosition(POSITION_X, POSITION_Y);
    }

    public void update(float value, float max_value)
    {
        this.max_value = (int)max_value;
        this.value = (int)value;
        float buffor_width = WIDTH * (value/max_value);
        setSize(buffor_width, HEIGHT);
        setPosition(POSITION_X, POSITION_Y);
    }


    /**
     * SETTERS AND GETTERS
     */
    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public float getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getHIGHT() {
        return HEIGHT;
    }

    public void setHIGHT(int HIGHT) {
        this.HEIGHT= HIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getPOSITION_X() {
        return POSITION_X;
    }

    public void setPOSITION_X(int POSITION_X) {
        this.POSITION_X = POSITION_X;
    }

    public int getPOSITION_Y() {
        return POSITION_Y;
    }

    public void setPOSITION_Y(int POSITION_Y) {
        this.POSITION_Y = POSITION_Y;
    }


}
