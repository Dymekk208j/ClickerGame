package pl.damiandziura;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class GUI extends Image {
    public GUI(String texture_patch)
    {
        super(new Texture(texture_patch));

    }
}
