package melonystudios.library.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

public class MLKeys {
    private static final long MINECRAFT_WINDOW = Minecraft.getInstance().getWindow().getWindow();

    @OnlyIn(Dist.CLIENT)
    public static boolean isShiftDown() {
        return InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
}
