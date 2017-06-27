package pl.damiandziura;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static pl.damiandziura.ClickerGame.AMOUNT_OF_UNIQUE_MONSTERS;
import static pl.damiandziura.ClickerGame.AMOUNT_OF_UNIQUE_STAGES;

public class Enemy
{
    private Monster monster[];
    private Boss boss[];
    private int monsterNumber;
    private int bossNumber;
    private int Max_hp;
    private int HP;
    private boolean alive = true;
    private int stageLevel;
    private boolean bossFight = false;
    public Enemy(int stageLevel, Stage stage)
    {
        this.stageLevel = stageLevel;
        monster = new Monster[AMOUNT_OF_UNIQUE_MONSTERS];
        boss = new Boss[AMOUNT_OF_UNIQUE_STAGES];

        for (int a = 0; a < AMOUNT_OF_UNIQUE_MONSTERS; a++)
        {
            String bufor_patch = "GFX\\Monsters\\" + Integer.toString(a+1) + ".png";
            String bufor_name = "TestowyPotwor nr " + Integer.toString(a);//TODO zmienic na pobieranie z bazy danych
            monster[a] = new Monster(bufor_patch, bufor_name);
            monster[a].setVisible(false);
            stage.addActor(monster[a]);

        }
        for (int a = 0; a < AMOUNT_OF_UNIQUE_STAGES; a++)
        {
            String bufor_patch = "GFX\\Bosses\\" + Integer.toString(a+1) + ".png";
            String bufor_name = "TestowyBoss nr " + Integer.toString(a);//TODO zmienic na pobieranie z bazy danych
            boss[a] = new Boss(bufor_patch, bufor_name);
            boss[a].setVisible(false);
            stage.addActor(boss[a]);

        }
        generateEnemy(stageLevel);
    }

    public void generateEnemy(int stageLevel)
    {
        this.stageLevel = stageLevel;
        if(stageLevel%10 == 0) bossFight = true;
        if(bossFight)
        {
            generateBoss();  
        }else
        {
            generateMonster();
        }
    }

    private void generateBoss() {
        if(stageLevel/10 <= AMOUNT_OF_UNIQUE_STAGES)
        {
            bossNumber = (int)(stageLevel/10)-1;
        }
        else
        {
            bossNumber = MathUtils.random(0, AMOUNT_OF_UNIQUE_STAGES-1);
        }


        setInvisibleAll();
        boss[bossNumber].setVisible(true);
        boss[bossNumber].resetTime();
        this.Max_hp = 1000;
        this.HP = this.Max_hp;
        alive = true;
        bossFight = true;
    }

    public void resetBoss()
    {
        setInvisibleAll();
        boss[bossNumber].setVisible(true);
        boss[bossNumber].resetTime();
        this.Max_hp = 1000;
        this.HP = this.Max_hp;
        alive = true;
        bossFight = true;
    }

    private void generateMonster() {
        this.stageLevel = stageLevel;
        monsterNumber = MathUtils.random(0, 27);

        setInvisibleAll();;
        monster[monsterNumber].setVisible(true);

        this.Max_hp = 100;
        this.HP = this.Max_hp;
        alive = true;
        bossFight = false;
    }

    private void setInvisibleAll()
    {
        for (int a = 0; a < AMOUNT_OF_UNIQUE_STAGES; a++)
        {
            boss[a].setVisible(false);
        }
        for (int a = 0; a < AMOUNT_OF_UNIQUE_MONSTERS; a++)
        {
            monster[a].setVisible(false);
        }
    }

    public void hurt(int dmg)
    {
        HP -= dmg;
        if(HP <= 0) {
            alive = false;
        }
    }

    public float getLeftTime()
    {
        return boss[bossNumber].getLefTime();
    }

    public void updateTime(float DT)
    {
        boss[bossNumber].updateTime(DT);
    }

    public void animation()
    {
        monster[monsterNumber].Animaton();
    }

    public boolean isAlive()
    {
        return alive;
    }

    /**
     * GETTERS AND SETTERS
     */

    public String getName()
    {
        if(bossFight)
        {
            return boss[bossNumber].getName();
        }else
        {
            return monster[monsterNumber].getName();
        }
    }

    public int getMax_hp() {
        return Max_hp;
    }

    public void setMax_hp(int max_hp) {
        Max_hp = max_hp;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getWIDTH()
    {
        if(bossFight)
        {
            return boss[bossNumber].getWIDTH();
        }else
        {
            return monster[monsterNumber].getWIDTH();
        }
    }

    public int getPosition_X() {
        if(bossFight)
        {
            return boss[bossNumber].getPosition_X();
        }else
        {
            return monster[monsterNumber].getPosition_X();
        }
    }

    public int getPosition_Y() {
        if(bossFight)
        {
            return boss[bossNumber].getPosition_Y();
        }else
        {
            return monster[monsterNumber].getPosition_Y();
        }
    }

    public int getHEIGHT() {
        if(bossFight)
        {
            return boss[bossNumber].getHEIGHT();
        }else
        {
            return monster[monsterNumber].getHEIGHT();
        }
    }

    public void setBossFight(boolean bossFight) {
        this.bossFight = bossFight;
    }

    public boolean isBossFight() {
        return bossFight;
    }

    public void resetArena()
    {
        HP = Max_hp;
        alive = true;
        boss[bossNumber].resetTime();
    }
}
