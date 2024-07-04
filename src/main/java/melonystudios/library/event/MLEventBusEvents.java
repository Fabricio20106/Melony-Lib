package melonystudios.library.event;

import melonystudios.library.MelonyLib;
import melonystudios.library.data.tag.MLBlockTagsProvider;
import melonystudios.library.data.tag.MLItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MelonyLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MLEventBusEvents {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        MLBlockTagsProvider mlBlockStateProvider = new MLBlockTagsProvider(generator, fileHelper);
        generator.addProvider(mlBlockStateProvider);
        generator.addProvider(new MLItemTagsProvider(generator, mlBlockStateProvider, fileHelper));
    }
}
