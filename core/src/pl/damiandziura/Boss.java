package pl.damiandziura;

/**
 * Created by Dymek on 27.06.2017.
 */

public class Boss extends Monster
{
    float lefTime = 10f;

    public Boss(String texture_path, String Name)
    {
        super(texture_path, Name);
    }

    public float getLefTime()
    {
        return lefTime;
    }

    public void updateTime(float dt)
    {
        lefTime -= dt;
        if(lefTime < 0) lefTime=0;
    }

    public void resetTime()
    {
        lefTime = 10f;
    }
}
