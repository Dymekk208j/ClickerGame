package pl.damiandziura;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static pl.damiandziura.ClickerGame.DEFAULT_HEIGHT;
import static pl.damiandziura.ClickerGame.DEFAULT_WIDTH;

public class Coin extends Image
{
    private Player player;
    private int value = 0;
    private float leftTime = 5f;
    private float alpha = 1f;
    private Stage stage;
    private Button button;
    private boolean inUse = false;

    public Coin(Texture texture, Stage stage, int x, int y, Player player)
    {
        super(texture);
        this.setPosition(x, y);
        this.stage = stage;
        this.setSize(32,32);
        this.player = player;
        button = new Button(new Button.ButtonStyle());
        button.setWidth(this.getWidth());
        button.setHeight(this.getHeight());
        button.setX(this.getX());
        button.setY(this.getY());
        button.setDebug(true);

        stage.addActor(this);
        stage.addActor(button);

        addListener();

    }

    private void addListener() {
        button.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                trunOff();
            }



        });

    }

    public void trunOff() {
        this.setVisible(false);
        button.setVisible(false);
        inUse = false;
        player.addGold(value);
    }

    public void trunOn() {
        this.setVisible(true);
        button.setVisible(true);
        inUse = true;
    }


    /*

     */
    public void check()
    {
        if(inUse)
        {
            Color color = this.getColor();
            alpha -= 0.02f;
            if (alpha <= 0)
            {
                alpha = 1f;
                trunOff();
            }
            color.a = alpha;
            this.setColor(color);
        }
    }

    public void animation(int enemy_x, int enemy_y, int enemy_width)
    {
        int up = MathUtils.random(50, 200);
        inUse = true;
        int od = (int)((this.getX()-enemy_x)*(-1));
        int DO = (int)((enemy_x+enemy_width)-this.getX()-this.getWidth());
        int xMoveAmmount = MathUtils.random(od, DO);

        od = (int)(this.getY()-(enemy_y+100)+32);
        DO = (int)(this.getY()-(enemy_y));
        int yMoveAmmount = MathUtils.random(od, DO);

        float moveActionTime = 1.5f;
        Action moveAction = Actions.sequence(
                Actions.moveBy(xMoveAmmount/10, up, 0.5f, Interpolation.circleOut),
                Actions.moveBy(xMoveAmmount, -yMoveAmmount-up, moveActionTime, Interpolation.bounceOut)

        );

        this.addAction(moveAction);
        button.setPosition(this.getX()+xMoveAmmount+(xMoveAmmount/10), this.getY()-yMoveAmmount);

    }
    public int getValue() {
        return value;
    }


    public boolean isInUse() {
        return inUse;
    }

    public void use(int value, int x, int y)
    {
        alpha =1f;
        this.setPosition(x, y);
        button.setPosition(x, y);
        this.value = value;
        trunOn();
    }
}
