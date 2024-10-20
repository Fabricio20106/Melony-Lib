package melonystudios.library.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MLCommonConfigs {
    public final ForgeConfigSpec.BooleanValue show121OnDebugMenu;
    public final ForgeConfigSpec.DoubleValue defaultCloudHeight;
    public final ForgeConfigSpec.BooleanValue highContrast;
    public final ForgeConfigSpec.BooleanValue highContrastBlockOutlines;
    public final ForgeConfigSpec.BooleanValue monochromeMojangLogo;
    public final ForgeConfigSpec.BooleanValue hideSplashTexts;
    public final ForgeConfigSpec.DoubleValue damageTilt;

    public MLCommonConfigs(ForgeConfigSpec.Builder builder) {
        this.show121OnDebugMenu = builder.comment("Whether to display \"Minecraft 1.21.1\" on the debug menu (F3 menu).").define("show121OnDebugMenu", false);
        this.defaultCloudHeight = builder.comment("What the default cloud height in dimensions with cloud height Y-128 should be. Defaults to 128 in vanilla and 192 in Melony Lib.").defineInRange("defaultCloudHeight", 192D, 0, 320);

        builder.push("vanillaOptions");
        this.highContrast = builder.comment("Enhances the contrast of UI elements.").translation("options.accessibility.high_contrast").define("highContrast", false);
        this.highContrastBlockOutlines = builder.comment("Enhances the block outline contrast of the targeted block.").translation("options.accessibility.high_contrast").define("highContrastBlockOutlines", false);
        this.monochromeMojangLogo = builder.comment("Hides the yellow splash text in the main menu.").translation("options.accessibility.monochrome_logo").define("hideSplashTexts", false);
        this.hideSplashTexts = builder.comment("Changes the Mojang Studios loading screen color from red to black.").translation("options.accessibility.hide_splash_texts").define("monochromeMojangLogo", false);
        this.damageTilt = builder.comment("The amount of camera shake caused by being hurt.").translation("options.accessibility.damage_tilt").defineInRange("damageTilt", 1D, 0, 1);
        builder.pop();
    }
}
