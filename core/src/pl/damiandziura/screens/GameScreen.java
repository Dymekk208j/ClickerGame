package pl.damiandziura.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.damiandziura.Arena;
import pl.damiandziura.Bar;
import pl.damiandziura.ClickerGame;
import pl.damiandziura.Coin;
import pl.damiandziura.DMG;
import pl.damiandziura.Enemy;
import pl.damiandziura.GUI;
import pl.damiandziura.Player;

import static pl.damiandziura.ClickerGame.CRIT_CHANCE_PRECENT;
import static pl.damiandziura.ClickerGame.DEFAULT_HEIGHT;
import static pl.damiandziura.ClickerGame.DEFAULT_WIDTH;


public class GameScreen extends AbstractScreen
{
    private static final String NAME = "Dymek";
    private static final int AMOUT_OF_NOTIFICATIONS = 10;
    private static final int AMOUT_OF_COINS= 100;

    private Coin coin[];

    private GUI LvlSwitchBackground;
    private GUI LvlSwitchNormal[];
    private GUI LvlSwitchBoss[];
    private Button jumpButton[];
    private Label labelJumpButton[];


    private Bar hpBar;
    private Bar timeBar;

    private Arena arena;

    private Enemy enemy;

    private Button monsterButton;

    private Player player;

    private Label InformationLabel;
    private Label MonsterLabel;
    private Label TimeLeftLabel;

    private float TimerPassiveDmg = 0;
    private float TimerTest = 0;


    private DMG dmgArrayList[];

    public GameScreen(ClickerGame game) {
        super(game);
        init();

    }


    private void init()
    {
        initStage();
        initEnemy();
        initBars();
        initMonsterButton();
        initPlayer();
        initLabels();
        initGUI();
        initCoins();


    }

    private void initCoins() {
        coin = new Coin[AMOUT_OF_COINS];
        Texture tex = new Texture("GFX\\GUI\\CoinPlaceHolder.png");
        for(int a = 0; a < AMOUT_OF_COINS; a++)
        {

            coin[a] = new Coin(tex, this.stage, 0 , 0, this.player);
            coin[a].trunOff();

        }
    }

    private void initGUI()
    {
        Label.LabelStyle labelStyleGUI = new Label.LabelStyle();
        labelStyleGUI.font = new BitmapFont();
        Color color = new Color();
        color.set(1,0,1,1);

        labelStyleGUI.fontColor = color;

        labelJumpButton = new Label[5];
        jumpButton = new Button[5];
        LvlSwitchNormal = new GUI[5];
        LvlSwitchBoss = new GUI[5];

        LvlSwitchBackground = new GUI("GFX\\GUI\\lvlSwitch0.png");
        LvlSwitchBackground.setSize(500, 150);
        float PosX = DEFAULT_WIDTH-126;
        float PosY = DEFAULT_HEIGHT- 100;
        LvlSwitchBackground.rotateBy(270);
        LvlSwitchBackground.setPosition(PosX, PosY);

        stage.addActor(LvlSwitchBackground);

        for(int a = 0; a < 5; a++)
        {
            int x = 0;
            if(a == 0 || a == 4) x = 32;
            if(a == 1 || a == 3) x = 16;

            jumpButton[a] = new Button(new Button.ButtonStyle());
            jumpButton[a].setWidth(64);
            jumpButton[a].setHeight(64);
            PosX = DEFAULT_WIDTH-104+x;
            PosY = (DEFAULT_HEIGHT/2) + ((85*5)/2) - jumpButton[a].getHeight() - (a*85);

            jumpButton[a].setX(PosX);
            jumpButton[a].setY(PosY);
            jumpButton[a].setDebug(true);

            LvlSwitchNormal[a] = new GUI("GFX\\GUI\\lvlSwitch2.png");
            LvlSwitchNormal[a].setSize(64, 64);
            LvlSwitchNormal[a].setPosition(jumpButton[a].getX(), jumpButton[a].getY() );
            LvlSwitchNormal[a].setVisible(false);

            LvlSwitchBoss[a] = new GUI("GFX\\GUI\\lvlSwitch1.png");
            LvlSwitchBoss[a].setSize(64, 64);
            LvlSwitchBoss[a].setPosition(jumpButton[a].getX(), jumpButton[a].getY() );
            LvlSwitchBoss[a].setVisible(false);

            labelJumpButton[a] = new Label( "", labelStyleGUI);
            labelJumpButton[a].setX(jumpButton[a].getX() + jumpButton[a].getWidth()/2 - labelJumpButton[a].getWidth()/2);
            labelJumpButton[a].setY(jumpButton[a].getY() + jumpButton[a].getHeight()/2 - labelJumpButton[a].getHeight()/2);
            labelJumpButton[a].setVisible(false);


            stage.addActor(LvlSwitchBoss[a]);
            stage.addActor(LvlSwitchNormal[a]);
            stage.addActor(labelJumpButton[a]);
            stage.addActor(jumpButton[a]);
        }

        initButtonClickActions();
        labelJumpButton[2].setVisible(true);
    }

    private void initButtonClickActions() {
        jumpButton[0].addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(arena.getLevel()-2 > 0)
                {
                    enemy.resetArena();
                    arena.loadArena(arena.getLevel()-2);
                    if(arena.getLevel()%10 ==0){
                        enemy.setBossFight(true);
                    }else{
                        enemy.setBossFight(false);
                    }
                    enemy.generateEnemy(arena.getLevel());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        jumpButton[1].addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(arena.getLevel()-1 > 0)
                {
                    enemy.resetArena();
                    arena.loadArena(arena.getLevel()-1);
                    if(arena.getLevel()%10 ==0){
                        enemy.setBossFight(true);
                    }else{
                        enemy.setBossFight(false);
                    }
                    enemy.generateEnemy(arena.getLevel());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        jumpButton[2].addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                enemy.resetArena();
                if(!enemy.isBossFight())
                {
                    arena.resetArena();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        jumpButton[3].addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(arena.getLevel()+1 <= arena.getMaxUnlockedArena())
                {
                    System.out.println((arena.getLevel()+1) + ">=" +arena.getMaxUnlockedArena());
                    enemy.resetArena();
                    arena.loadArena(arena.getLevel()+1);
                    if(arena.getLevel()%10 ==0){
                        enemy.setBossFight(true);
                    }else{
                        enemy.setBossFight(false);
                    }
                    enemy.generateEnemy(arena.getLevel());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        jumpButton[4].addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(arena.getLevel()+2 <= arena.getMaxUnlockedArena())
                {
                    enemy.resetArena();
                    arena.loadArena(arena.getLevel()+2);
                    if(arena.getLevel()%10 ==0){
                        enemy.setBossFight(true);
                    }else{
                        enemy.setBossFight(false);
                    }
                    enemy.generateEnemy(arena.getLevel());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        Color color = new Color();
        color.set(1,0,0,1);


        labelStyle.fontColor = color;
        InformationLabel = new Label(player.getName(), labelStyle);
        InformationLabel.setX(0);
        InformationLabel.setY(680);
        stage.addActor(InformationLabel);

        String buffor = Integer.toString(enemy.getHP()) + " / " + Integer.toString(enemy.getMax_hp());
        MonsterLabel = new Label(buffor, labelStyle);
        float posX = hpBar.getX() + hpBar.getWIDTH()/2 - MonsterLabel.getWidth()/2;
        float posY = hpBar.getY() + +hpBar.getHeight()/2 - MonsterLabel.getHeight()/2;
        MonsterLabel.setX(posX);
        MonsterLabel.setY(posY);
        stage.addActor(MonsterLabel);

        initDMGLabels();

        Label.LabelStyle labelStyle2 = new Label.LabelStyle();
        labelStyle2.font = new BitmapFont();

        Color color2 = new Color();
        color2.set(0,0,0,1);

        //String buffor = Integer.toString(enemy.getHP()) + " / " + Integer.toString(enemy.getMax_hp());
        TimeLeftLabel = new Label("20 s", labelStyle2);
        posX = enemy.getPosition_X() + timeBar.getWidth()/2 - TimeLeftLabel.getWidth()/2;
        posY = enemy.getPosition_Y() + 600 + (TimeLeftLabel.getWidth()/2);
        TimeLeftLabel.setColor(color2);
        TimeLeftLabel.setX(posX);
        TimeLeftLabel.setY(posY);
        stage.addActor(TimeLeftLabel);
        TimeLeftLabel.setVisible(false);

        /*
         pos_X = enemy.getPosition_X();
        pos_Y = enemy.getPosition_Y() + 610;
        barWidth = enemy.getWIDTH();
        barHeight = 30;
        value = enemy.getHP();
        max_value = enemy.getMax_hp();

        timeBar = new Bar(barWidth, barHeight, 100, 100, pos_X, pos_Y, "timeDot.png");
        stage.addActor(timeBar);
        timeBar.setVisible(false);
         */


    }

    private void initDMGLabels()
    {
        dmgArrayList = new DMG[AMOUT_OF_NOTIFICATIONS];

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        for(int a = 0; a < AMOUT_OF_NOTIFICATIONS; a++)
        {
            dmgArrayList[a] = new DMG(labelStyle);
            stage.addActor(dmgArrayList[a]);
        }
    }

    private void initPlayer() {
        player = new Player(NAME);


    }

    private void initBars() {
        int pos_X = enemy.getPosition_X();
        int pos_Y = enemy.getPosition_Y() - 40;
        int barWidth = enemy.getWIDTH();
        int barHeight = 30;
        int value = enemy.getHP();
        int max_value = enemy.getMax_hp();

        hpBar = new Bar(barWidth, barHeight, value, max_value, pos_X, pos_Y, "GFX\\GUI\\reddot.png");
        stage.addActor(hpBar);

        pos_X = enemy.getPosition_X();
        pos_Y = enemy.getPosition_Y() + 610;
        barWidth = enemy.getWIDTH();
        barHeight = 30;
        value = enemy.getHP();
        max_value = enemy.getMax_hp();

        timeBar = new Bar(barWidth, barHeight, 100, 100, pos_X, pos_Y, "GFX\\GUI\\timeDot.png");
        stage.addActor(timeBar);
        timeBar.setVisible(false);
    }

    private void initStage() {
        arena = new Arena(1, this.stage);
    }


    private void initEnemy()
    {
        enemy = new Enemy(1, this.stage);
    }

    private void initMonsterButton()
    {
        monsterButton = new Button(new Button.ButtonStyle());
        monsterButton.setWidth(enemy.getWIDTH());
        monsterButton.setHeight(enemy.getHEIGHT());
        monsterButton.setX(enemy.getPosition_X());
        monsterButton.setY(enemy.getPosition_Y());
        monsterButton.setDebug(true);
        stage.addActor(monsterButton);

        monsterButton.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                hitMonsterByClick();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void hitMonsterByClick()
    {
        if(enemy.isAlive())
        {
            int a = 0;
            boolean var = false;
            boolean CRIT = false;
            int ClickDMG = player.getClickDmg();
            if (MathUtils.random(0, 100) <= CRIT_CHANCE_PRECENT) {
                CRIT = true;
                ClickDMG *= 2;
            }
            do {
                if (dmgArrayList[a].isShowing() == false) {
                    int posX = MathUtils.random(enemy.getPosition_X(), enemy.getPosition_X() + enemy.getWIDTH());
                    int posY = MathUtils.random(enemy.getPosition_Y(), enemy.getPosition_Y() + enemy.getHEIGHT());

                    dmgArrayList[a].setNotification(Integer.toString(ClickDMG), posX, posY, CRIT);
                    var = true;
                }
                a++;
            } while (a < AMOUT_OF_NOTIFICATIONS && var == false);

            if (a >= AMOUT_OF_NOTIFICATIONS && var == false) {
                int posX = MathUtils.random(enemy.getPosition_X(), enemy.getPosition_X() + enemy.getWIDTH());
                int posY = MathUtils.random(enemy.getPosition_Y(), enemy.getPosition_Y() + enemy.getHEIGHT());
                dmgArrayList[MathUtils.random(0, 9)].setNotification(Integer.toString(ClickDMG), posX, posY, CRIT);
            }
            enemy.hurt(ClickDMG);
            enemy.isAlive();//zeby uaktualnic pole ktore mowi o tym czy zyje czy nie;
            enemy.animation();
            if(!enemy.isAlive()) defeatedEnemy();
        }
        else
        {
            defeatedEnemy();
        }
    }

    private void defeatedEnemy() {
        if(arena.isCompleted())
        {
            arena.generateArena();
            if(arena.getLevel()%10 == 0)
            {
                timeBar.setVisible(true);

            }

        }

        else
        {
            arena.addDefeatedMonster();

            if(enemy.isBossFight())
            {
                enemy.setBossFight(false);
                timeBar.setVisible(false);
                arena.generateArena();
            }



        }
        enemy.generateEnemy(arena.getLevel());

        playGoldRain();


    }

    private void playGoldRain()
    {
        int goldReward = 500;
        int amoutOfCoins = MathUtils.random(1, 15);

        for(int a = 0; a < amoutOfCoins; a++)
        {
            boolean b = false;
            int counter = 0;
            do{
                counter++;

                if(!coin[a].isInUse())
                {
                    coin[a].use(goldReward/amoutOfCoins, 500, 500);
                    coin[a].animation(enemy.getPosition_X(), enemy.getPosition_Y(), enemy.getWIDTH());
                    b = true;
                    counter= amoutOfCoins+1;
                }

                if(counter >= amoutOfCoins && b == false)
                {
                    int rand = MathUtils.random(0, AMOUT_OF_COINS-1);
                    coin[rand].trunOff();
                    coin[rand].use(goldReward/amoutOfCoins, 500, 500);
                    coin[rand].animation(enemy.getPosition_X(), enemy.getPosition_Y(), enemy.getWIDTH());
                    b = true;
                    counter= amoutOfCoins+1;
                }

            }while (b != true && counter < amoutOfCoins);
        }

        //player.addGold(goldReward);

    }

    private void hitMonsterPassive()
    {
        if(enemy.isAlive())
        {
            enemy.hurt(player.getPassiveDmg()/20);
            enemy.isAlive();//zeby uaktualnic pole ktore mowi o tym czy zyje czy nie;
            if(!enemy.isAlive()) defeatedEnemy();
        }
        else
        {
            defeatedEnemy();
        }
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        batch.begin();

        stage.draw();
        batch.end();
    }

    private void update(float delta)
    {
        stage.act();
        updateBars();
        updateDMGNotifications(delta);
        if(enemy.isBossFight())
        {
            enemy.updateTime(delta);
            if(enemy.getLeftTime() <=0)
            {
                enemy.resetBoss();
            }
        }
        TimerPassiveDmg += delta;
        if(TimerPassiveDmg >= 0.05)
        {
            TimerPassiveDmg = 0;
            hitMonsterPassive();
        }

        TimerTest += delta;
        if(TimerTest >= 0.2)
        {
            for(int a = 0; a < AMOUT_OF_COINS; a++)
            {
                coin[a].check();
            }
            TimerTest = 0;
          //
        }
        InformationLabel.setText("Level: " + Integer.toString(arena.getLevel()) + "    Defeated monsters: " + Integer.toString(arena.getDefeatedMonsters()) + "    Enemy name: " + enemy.getName() + "    Gold: " + Integer.toString(player.getGold()));
        //raz na sekunde hitMonsterPassive();

        updatesGUI();
    }

    private void updateDMGNotifications(float delta) {
        for(int a = 0; a < AMOUT_OF_NOTIFICATIONS; a++)
        {
            dmgArrayList[a].Update(delta);

        }
    }

    private void updateBars() {
        hpBar.update(enemy.getHP(), enemy.getMax_hp());
        String buffor = Integer.toString(enemy.getHP()) + " / " + Integer.toString(enemy.getMax_hp());
        MonsterLabel.setText(buffor);

        if(enemy.isBossFight())
        {
            timeBar.setVisible(true);
            TimeLeftLabel.setVisible(true);
        }else
        {
            timeBar.setVisible(false);
            TimeLeftLabel.setVisible(false);
        }
        if(timeBar.isVisible())
        {
            String test = String.format("%.2f", enemy.getLeftTime()) + " sec";
            timeBar.update(enemy.getLeftTime(), 10.0f);
            TimeLeftLabel.setText(test);
    }


    }

    private void updatesGUI() {
        String bufor;

        if (arena.getLevel() - 2 > 0)
        {
            bufor = Integer.toString(arena.getLevel()-2);
            labelJumpButton[0].setText(bufor);
            labelJumpButton[0].setX(jumpButton[0].getX() + jumpButton[0].getWidth()/2 - labelJumpButton[0].getWidth()/2 - (bufor.length() *4));
            labelJumpButton[0].setY(jumpButton[0].getY() + jumpButton[0].getHeight()/2 - labelJumpButton[0].getHeight()/2);
            labelJumpButton[0].setVisible(true);

            if((arena.getLevel()-2)%10 == 0)
            {
                LvlSwitchNormal[0].setVisible(false);
                LvlSwitchBoss[0].setVisible(true);
            }
            else
            {
                LvlSwitchNormal[0].setVisible(true);
                LvlSwitchBoss[0].setVisible(false);
            }
        }
        else
        {
            labelJumpButton[0].setVisible(false);
            LvlSwitchNormal[0].setVisible(false);
            LvlSwitchBoss[0].setVisible(false);
        }

        if (arena.getLevel() - 1 > 0)
        {
            bufor = Integer.toString(arena.getLevel()-1);
            labelJumpButton[1].setText(bufor);
            labelJumpButton[1].setX(jumpButton[1].getX() + jumpButton[1].getWidth()/2 - labelJumpButton[1].getWidth()/2 - (bufor.length() *4));
            labelJumpButton[1].setY(jumpButton[1].getY() + jumpButton[1].getHeight()/2 - labelJumpButton[1].getHeight()/2);
            labelJumpButton[1].setVisible(true);
            
            if((arena.getLevel()-1) % 10 == 0)
            {
                LvlSwitchNormal[1].setVisible(false);
                LvlSwitchBoss[1].setVisible(true);
            }
            else
            {
                LvlSwitchNormal[1].setVisible(true);
                LvlSwitchBoss[1].setVisible(false);
            }
        }
        else
        {
            labelJumpButton[1].setVisible(false);
            LvlSwitchNormal[1].setVisible(false);
            LvlSwitchBoss[1].setVisible(false);
        }

        if (arena.getMaxUnlockedArena() >= arena.getLevel()+1)
        {
            bufor = Integer.toString(arena.getLevel()+1);
            labelJumpButton[3].setText(bufor);
            labelJumpButton[3].setX(jumpButton[3].getX() + jumpButton[3].getWidth()/2 - labelJumpButton[3].getWidth()/2 - (bufor.length() *4));
            labelJumpButton[3].setY(jumpButton[3].getY() + jumpButton[3].getHeight()/2 - labelJumpButton[3].getHeight()/2);
            labelJumpButton[3].setVisible(true);
            
            if((arena.getLevel()+1) % 10 == 0)
            {
                LvlSwitchNormal[3].setVisible(false);
                LvlSwitchBoss[3].setVisible(true);
            }
            else
            {
                LvlSwitchNormal[3].setVisible(true);
                LvlSwitchBoss[3].setVisible(false);
            }
        }
        else
        {
            labelJumpButton[3].setVisible(false);
            LvlSwitchNormal[3].setVisible(false);
            LvlSwitchBoss[3].setVisible(false);
        }

        if (arena.getMaxUnlockedArena() >= arena.getLevel()+2)
        {
            labelJumpButton[4].setVisible(true);
            bufor = Integer.toString(arena.getLevel()+2);
            labelJumpButton[4].setText(bufor);
            labelJumpButton[4].setX(jumpButton[4].getX() + jumpButton[4].getWidth()/2 - labelJumpButton[4].getWidth()/2 - (bufor.length() *4));
            labelJumpButton[4].setY(jumpButton[4].getY() + jumpButton[4].getHeight()/2 - labelJumpButton[4].getHeight()/2);

            if((arena.getLevel()+2) % 10 == 0)
            {
                LvlSwitchNormal[4].setVisible(false);
                LvlSwitchBoss[4].setVisible(true);
            }
            else
            {
                LvlSwitchNormal[4].setVisible(true);
                LvlSwitchBoss[4].setVisible(false);
            }
        }
        else
        {
            labelJumpButton[4].setVisible(false);
            LvlSwitchNormal[4].setVisible(false);
            LvlSwitchBoss[4].setVisible(false);
        }

        if(arena.getLevel()%10 == 0)
        {
            LvlSwitchNormal[2].setVisible(false);
            LvlSwitchBoss[2].setVisible(true);
        }
        else
        {
            LvlSwitchNormal[2].setVisible(true);
            LvlSwitchBoss[2].setVisible(false);
        }

        bufor = Integer.toString(arena.getLevel());
        labelJumpButton[2].setText(bufor);
        labelJumpButton[2].setX(jumpButton[2].getX() + jumpButton[2].getWidth()/2 - labelJumpButton[2].getWidth()/2 - (bufor.length() *4));
        labelJumpButton[2].setY(jumpButton[2].getY() + jumpButton[2].getHeight()/2 - labelJumpButton[2].getHeight()/2);

    }






}
