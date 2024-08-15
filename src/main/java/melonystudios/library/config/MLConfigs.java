package melonystudios.library.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MLConfigs {
    public static final MLCommonConfigs COMMON_CONFIGS;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<MLCommonConfigs, ForgeConfigSpec> commonConfigPair = new ForgeConfigSpec.Builder().configure(MLCommonConfigs::new);

        COMMON_CONFIGS = commonConfigPair.getLeft();
        COMMON_SPEC = commonConfigPair.getRight();
    }
}
