package pl.damiandziura;

import com.badlogic.gdx.math.MathUtils;

public class Player
{
    private String name;
    private int MinClickDmg = 1;
    private int MaxClickDmg = 10;
    private int PassiveDmg = 5;
    private int Gold = 0;

    public Player(String name) {
        this.name = name;
    }

    public int getClickDmg() {

        return MathUtils.random(MinClickDmg, MaxClickDmg);
    }

     public void addClickDmg(int value)
    {

        MinClickDmg += value;
        MaxClickDmg += value;
    }
    public void addPassiveDmg(int value)
    {
        PassiveDmg += value;
    }


    /*
    SETTERS AND GETTERS
     */

    public int getPassiveDmg() {
        return PassiveDmg;
    }

    public void setPassiveDmg(int passiveDmg) {
        PassiveDmg = passiveDmg;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMinClickDmg() {
        return MinClickDmg;
    }

    public void setMinClickDmg(int minClickDmg) {
        MinClickDmg = minClickDmg;
    }

    public int getMaxClickDmg() {
        return MaxClickDmg;
    }

    public void setMaxClickDmg(int maxClickDmg) {
        MaxClickDmg = maxClickDmg;
    }

    public void addGold(int amount)
    {
        Gold += amount;
    }
    public int getGold() {
        return Gold;
    }

    public void setGold(int gold) {
        Gold = gold;
    }
}
