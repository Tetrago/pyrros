package tetrago.pyrros.common.data;

public class Distance
{
    private final long mMeters;

    private Distance(long meters)
    {
        mMeters = meters;
    }

    public static Distance meters(long d)
    {
        return new Distance(d);
    }

    public static Distance kilometers(long d)
    {
        return new Distance(d * 1_000);
    }

    public static Distance megameters(long d)
    {
        return new Distance(d * 1_000_000);
    }

    public static Distance gigameters(long d)
    {
        return new Distance(d * 1_000_000_000);
    }

    public long asMeters()
    {
        return mMeters;
    }

    public long asKilometers()
    {
        return mMeters / 1_000;
    }

    public long asMegameters()
    {
        return mMeters / 1_000_000;
    }

    public long asGigameters()
    {
        return mMeters / 1_000_000_000;
    }
}
