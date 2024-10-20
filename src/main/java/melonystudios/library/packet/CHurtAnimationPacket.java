package melonystudios.library.packet;

import melonystudios.library.util.LibUtils;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;

public class CHurtAnimationPacket implements IPacket<IClientPlayNetHandler> {
    public int entityID;
    public float yaw;

    public CHurtAnimationPacket(int entityID, float yaw) {
        this.entityID = entityID;
        this.yaw = yaw;
    }

    public CHurtAnimationPacket(LivingEntity livEntity) {
        this(livEntity.getId(), livEntity.hurtDir);
    }

    public CHurtAnimationPacket(PacketBuffer buffer) {
        this(buffer.readVarInt(), buffer.readFloat());
    }

    @Override
    public void read(PacketBuffer buffer) {
        this.entityID = buffer.readVarInt();
        this.yaw = buffer.readFloat();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeVarInt(this.entityID);
        buffer.writeFloat(this.yaw);
    }

    @Override
    public void handle(IClientPlayNetHandler playHandler) {
        LibUtils.handleHurtAnimation(this, playHandler);
    }
}
