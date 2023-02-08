package tetrago.pyrros.common.data;

import java.util.Random;

public class Planet
{
    private final String mName;
    private final Distance mDistance;

    public Planet(String name, Distance distance)
    {
        mName = name;
        mDistance = distance;
    }

    public Planet(Random random)
    {
        mName = "RandomPlanetName";
        mDistance = Distance.gigameters(random.nextInt(5, 700));
    }

    public String getName()
    {
        return mName;
    }

    public Distance getDistance()
    {
        return mDistance;
    }
}
