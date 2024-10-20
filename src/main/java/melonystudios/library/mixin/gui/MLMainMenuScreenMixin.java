package melonystudios.library.mixin.gui;

import melonystudios.library.config.MLConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(MainMenuScreen.class)
public class MLMainMenuScreenMixin {
    @Shadow
    @Nullable
    private String splash;

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (MLConfigs.COMMON_CONFIGS.hideSplashTexts.get()) {
            this.splash = null;
        } else {
            if (this.splash == null) this.splash = Minecraft.getInstance().getSplashManager().getSplash();
        }
    }
}
