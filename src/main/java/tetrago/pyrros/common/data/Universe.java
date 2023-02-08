package tetrago.pyrros.common.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import tetrago.pyrros.common.capability.ModCapabilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Universe extends SavedData
{
    private final List<Planet> mPlanets = new ArrayList<>();

    public static Universe getInstance(Level level)
    {
        if(level.isClientSide())
        {
            return level.getCapability(ModCapabilities.UNIVERSE)
                    .orElseThrow(() -> new IllegalStateException("Level missing universe capability"))
                    .getUniverse();
        }

        DimensionDataStorage storage = ((ServerLevel)level).getServer().overworld().getDataStorage();;
        return storage.computeIfAbsent(Universe::new, () -> new Universe(level.getRandom()), "universe");
    }

    public Universe(Random random)
    {
        int count = random.nextInt(5, 15);
        for(int i = 0; i < count; ++i)
        {
            mPlanets.add(new Planet(random));
        }
    }

    public Universe(CompoundTag nbt)
    {
        mPlanets.addAll(nbt.getList("planets", Tag.TAG_COMPOUND).stream().map(tag -> (CompoundTag)tag).map(tag ->
                new Planet(tag.getString("name"), Distance.meters(tag.getLong("distance")))).toList());
    }

    @Override
    public CompoundTag save(CompoundTag nbt)
    {
        ListTag planets = new ListTag();
        planets.addAll(mPlanets.stream().map(planet -> {
            CompoundTag tag = new CompoundTag();
            tag.putString("name", planet.getName());
            tag.putLong("distance", planet.getDistance().asMeters());

            return tag;
        }).toList());

        nbt.put("planets", planets);

        return nbt;
    }

    public List<Planet> getPlanets()
    {
        return Collections.unmodifiableList(mPlanets);
    }
}
