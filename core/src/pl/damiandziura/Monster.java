package pl.damiandziura;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static pl.damiandziura.ClickerGame.DEFAULT_HEIGHT;
import static pl.damiandziura.ClickerGame.DEFAULT_WIDTH;


public class Monster extends Image
{
    private int CENTER_POINT_X = 600;
    private int CENTER_POINT_Y = 600;
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;
    private final static int Position_X = (DEFAULT_WIDTH/2) - (WIDTH/2);
    private final static int Position_Y = (DEFAULT_HEIGHT /2) - (HEIGHT /2);
    private String name = "";

    public Monster( String texture_path, String Name)
    {
        super(new Texture(texture_path));
        this.setOrigin(WIDTH/2, HEIGHT /2);
        this.setSize(WIDTH, HEIGHT);
        this.setPosition(Position_X, Position_Y);
        this.name = Name;

    }


    public void Animaton() {

        int xMoveAmmount = MathUtils.random(-10, 10);
        int yMoveAmmount = 10;
        float moveActionTime = 0.30f;
        Action moveAction = Actions.sequence(
                Actions.moveBy(xMoveAmmount, yMoveAmmount, moveActionTime, Interpolation.circleOut),
                Actions.moveBy(-xMoveAmmount, -yMoveAmmount, moveActionTime, Interpolation.circle)
        );

        int xGrowAmmount = MathUtils.random(-10, 0);
        int yGrowAmmount = xGrowAmmount;
        float growActionTime = 0.2f;
        Action growAction =  Actions.sequence(
                Actions.sizeBy(xGrowAmmount, yGrowAmmount, growActionTime, Interpolation.circleOut),
                Actions.sizeBy(-xGrowAmmount, -yGrowAmmount, growActionTime, Interpolation.circle)
        );

        this.addAction(moveAction);
        this.addAction(growAction);

    }

    public int getWIDTH()
    {
        return WIDTH;
    }

    public int getPosition_X() {
        return Position_X;
    }

    public int getPosition_Y() {
        return Position_Y;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public String getName() {
        return name;
    }
}
