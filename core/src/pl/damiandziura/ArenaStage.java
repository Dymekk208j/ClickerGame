package pl.damiandziura;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static pl.damiandziura.ClickerGame.DEFAULT_HEIGHT;
import static pl.damiandziura.ClickerGame.DEFAULT_WIDTH;


public class ArenaStage extends Image
{
    public ArenaStage(String texture_patch)
    {
        super(new Texture(texture_patch));
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setPosition(0,0);

    }
}
