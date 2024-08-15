package melonystudios.library.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MLCommonConfigs {
    public final ForgeConfigSpec.BooleanValue show121OnDebugMenu;

    public MLCommonConfigs(ForgeConfigSpec.Builder builder) {
        this.show121OnDebugMenu = builder.comment("Whether to display \"Minecraft 1.21\" on the debug menu (F3 menu).").define("show121OnDebugMenu", false);
    }
}
