package melonystudios.library.config.custom;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DescriptedSliderOption extends SliderPercentageOption {
    private final ITextComponent tooltip;

    public DescriptedSliderOption(String translation, double minValue, double maxValue, float steps, Function<GameSettings, Double> getter, BiConsumer<GameSettings, Double> setter, BiFunction<GameSettings, SliderPercentageOption, ITextComponent> toString, ITextComponent tooltip) {
        super(translation, minValue, maxValue, steps, getter, setter, toString);
        this.tooltip = tooltip;
    }

    @Override
    @Nonnull
    public Widget createButton(GameSettings settings, int x, int y, int width) {
        this.setTooltip(Minecraft.getInstance().font.split(this.tooltip, 200));
        return super.createButton(settings, x, y, width);
    }
}
