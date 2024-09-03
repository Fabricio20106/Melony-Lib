package melonystudios.library.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MLCommonConfigs {
    public final ForgeConfigSpec.BooleanValue show121OnDebugMenu;
    public final ForgeConfigSpec.DoubleValue defaultCloudHeight;

    public MLCommonConfigs(ForgeConfigSpec.Builder builder) {
        this.show121OnDebugMenu = builder.comment("Whether to display \"Minecraft 1.21\" on the debug menu (F3 menu).").define("show121OnDebugMenu", false);
        this.defaultCloudHeight = builder.comment("What the default cloud height in dimensions with cloud height Y-128 should be. Defaults to 128 in vanilla and 192 in Melony Lib.").defineInRange("defaultCloudHeight", 192D, 0, 320);
    }
}
