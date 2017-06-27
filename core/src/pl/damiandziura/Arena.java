package pl.damiandziura;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static pl.damiandziura.ClickerGame.AMOUNT_OF_UNIQUE_STAGES;
import static pl.damiandziura.ClickerGame.DEFAULT_HEIGHT;
import static pl.damiandziura.ClickerGame.DEFAULT_WIDTH;

public class Arena
{
    private ArenaStage arenaStage[];
    private int arenaNumber;
    private int level = 1;
    private int defeatedMonsters = 0;
    private int maxUnlockedArena = 1;

    public Arena(int level, Stage stage)
    {
        this.level = level-1;
        arenaStage = new ArenaStage[AMOUNT_OF_UNIQUE_STAGES];
        String bufor_patch;

        for (int a = 0; a < AMOUNT_OF_UNIQUE_STAGES; a++)
        {
            bufor_patch = "GFX\\Locations\\" + Integer.toString(a+1) + ".png";
            arenaStage[a] = new ArenaStage(bufor_patch);
            arenaStage[a].setVisible(false);
            stage.addActor(arenaStage[a]);
        }
       generateArena();
    }

    public void generateArena()
    {
        level++;
        if(level > maxUnlockedArena) maxUnlockedArena = level;

        defeatedMonsters = 0;

        if(level/10 <= AMOUNT_OF_UNIQUE_STAGES)
        {
            arenaNumber = (level/10);
            System.out.printf("arenaNumber" + Integer.toString(arenaNumber));
        }
        else
        {
            arenaNumber = MathUtils.random(0, AMOUNT_OF_UNIQUE_STAGES-1);
        }

        arenaStage[arenaNumber].setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        arenaStage[arenaNumber].setPosition(0,0);

        for (int a = 0; a < AMOUNT_OF_UNIQUE_STAGES; a++)
        {
            if(a != arenaNumber)
            {
                arenaStage[a].setVisible(false);
            }else
            {
                arenaStage[a].setVisible(true);
            }

        }

    }

    public void loadArena(int level)
    {
        this.level = level;
        defeatedMonsters = 0;

        if(level <= AMOUNT_OF_UNIQUE_STAGES)
        {
            arenaNumber = level-1;
            System.out.printf("arenaNumber" + Integer.toString(arenaNumber));
        }
        else
        {
            arenaNumber = MathUtils.random(0, AMOUNT_OF_UNIQUE_STAGES-1);
        }

        arenaStage[arenaNumber].setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        arenaStage[arenaNumber].setPosition(0,0);

        for (int a = 0; a < AMOUNT_OF_UNIQUE_STAGES; a++)
        {
            if(a != arenaNumber)
            {
                arenaStage[a].setVisible(false);
            }else
            {
                arenaStage[a].setVisible(true);
            }

        }

    }

    public void addDefeatedMonster()
    {
        defeatedMonsters++;
    }

    public void resetArena()
    {
        defeatedMonsters = 0;
    }

    /*
     * GETTERS AND SETTERS
     */
    public boolean isCompleted()
    {
        if(defeatedMonsters >= 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getDefeatedMonsters() {
        return defeatedMonsters;
    }

    public ArenaStage getArenaStage() {
        return arenaStage[arenaNumber];
    }

    public int getMaxUnlockedArena() {
        return maxUnlockedArena;
    }

    public void setMaxUnlockedArena(int maxUnlockedArena) {
        this.maxUnlockedArena = maxUnlockedArena;
    }
}
