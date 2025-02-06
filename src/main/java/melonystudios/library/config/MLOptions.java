package melonystudios.library.config;

import melonystudios.library.config.custom.DescriptedSliderOption;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.ResourceLoadProgressGui;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class MLOptions {
    public static final BooleanOption MONOCHROME_LOGO = new BooleanOption("options.accessibility.monochrome_logo", new TranslationTextComponent(
            "options.accessibility.monochrome_logo.tooltip"), settings -> MLConfigs.COMMON_CONFIGS.monochromeMojangLogo.get(), (settings, newValue) -> {
        MLConfigs.COMMON_CONFIGS.monochromeMojangLogo.set(newValue);

        int redBackground = ColorHelper.PackedColor.color(255, 239, 50, 61);
        int blackBackground = ColorHelper.PackedColor.color(255, 0, 0, 0); // 255, 0, 0, 0
        ResourceLoadProgressGui.BRAND_BACKGROUND = MLConfigs.COMMON_CONFIGS.monochromeMojangLogo.get() ? blackBackground : redBackground;
        ResourceLoadProgressGui.BRAND_BACKGROUND_NO_ALPHA = MLConfigs.COMMON_CONFIGS.monochromeMojangLogo.get() ? blackBackground : redBackground & 16777215;
    });

    /*public static final BooleanOption HIGH_CONTRAST = new BooleanOption("options.accessibility.high_contrast", new TranslationTextComponent(
            "options.accessibility.high_contrast.tooltip"), settings -> MLConfigs.COMMON_CONFIGS.highContrast.get(), (settings, newValue) -> {
        MLConfigs.COMMON_CONFIGS.highContrast.set(newValue);
        ResourcePackList packRepository = Minecraft.getInstance().getResourcePackRepository();
        boolean highContrastEnabled = packRepository.getSelectedIds().contains("high_contrast");
        if (!highContrastEnabled && newValue) {
            if (packRepository.isAvailable("high_contrast")) this.updateResourcePacks(packRepository);
        } else if (highContrastEnabled && !newValue && packRepository.)
    });*/

    public static final BooleanOption HIGH_CONTRAST_BLOCK_OUTLINES = new BooleanOption("options.accessibility.high_contrast_block_outline", new TranslationTextComponent(
            "options.accessibility.high_contrast_block_outline.tooltip"), settings -> MLConfigs.COMMON_CONFIGS.highContrastBlockOutlines.get(), (settings, newValue) ->
            MLConfigs.COMMON_CONFIGS.highContrastBlockOutlines.set(newValue));

    public static final BooleanOption HIDE_SPLASH_TEXTS = new BooleanOption("options.accessibility.hide_splash_texts", new TranslationTextComponent(
            "options.accessibility.hide_splash_texts.tooltip"), settings -> MLConfigs.COMMON_CONFIGS.hideSplashTexts.get(), (settings, newValue) ->
            MLConfigs.COMMON_CONFIGS.hideSplashTexts.set(newValue));

    public static final SliderPercentageOption DAMAGE_TILT = new DescriptedSliderOption("options.accessibility.damage_tilt", 0D, 1D, 0F, settings ->
            MLConfigs.COMMON_CONFIGS.damageTilt.get(), (settings, newValue) -> MLConfigs.COMMON_CONFIGS.damageTilt.set(MathHelper.clamp(newValue, 0, 1)), (settings, option) -> {
        double percentage = option.toPct(option.get(settings));
        TranslationTextComponent captionComponent = new TranslationTextComponent("options.accessibility.damage_tilt");
        return (percentage == 0 ? DialogTexts.optionStatus(captionComponent, false) : percentValueLabel(percentage, captionComponent));
    }, new TranslationTextComponent("options.accessibility.damage_tilt.tooltip"));

    private static ITextComponent percentValueLabel(double percentage, ITextComponent captionComponent) {
        return new TranslationTextComponent("options.percent_value", captionComponent, (int) (percentage * 100.0));
    }
}
