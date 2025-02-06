package melonystudios.library.mixin.gui;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import melonystudios.library.config.MLConfigs;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.lighting.WorldLightManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(DebugOverlayGui.class)
public abstract class MLDebugOverlayGUIMixin {
    @Shadow
    @Final
    private Minecraft minecraft;
    @Shadow
    @Nullable
    protected abstract ServerWorld getServerLevel();
    @Shadow
    @Final
    private static Map<Heightmap.Type, String> HEIGHTMAP_NAMES;
    @Shadow
    @Nullable
    protected abstract Chunk getServerChunk();
    @Shadow
    protected abstract Chunk getClientChunk();
    @Shadow
    @Nullable
    protected abstract String getServerChunkStats();
    @Shadow
    protected abstract World getLevel();
    @Shadow
    public abstract void clearChunkCache();
    @Shadow
    @Nullable
    private ChunkPos lastPos;

    @Inject(method = "getGameInformation", at = @At("HEAD"), cancellable = true)
    private void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
        String serverInformation = getServerInformation();
        BlockPos cameraPos = this.minecraft.getCameraEntity().blockPosition();
        String show121 = MLConfigs.COMMON_CONFIGS.show121OnDebugMenu.get() ? "1.21.4" : SharedConstants.getCurrentVersion().getName();

        if (this.minecraft.showOnlyReducedInfo()) {
            cir.setReturnValue(Lists.newArrayList("Minecraft " + show121 + " (" + this.minecraft.getLaunchedVersion() + "/" + ClientBrandRetriever.getClientModName() + ")", this.minecraft.fpsString, serverInformation, this.minecraft.levelRenderer.getChunkStatistics(), this.minecraft.levelRenderer.getEntityStatistics(), "P: " + this.minecraft.particleEngine.countParticles() + ". T: " + this.minecraft.level.getEntityCount(), this.minecraft.level.gatherChunkSourceStats(), "", String.format("Chunk-relative: %d %d %d", cameraPos.getX() & 15, cameraPos.getY() & 15, cameraPos.getZ() & 15)));
        } else {
            Entity cameraEntity = this.minecraft.getCameraEntity();
            Direction facingDirection = cameraEntity.getDirection();
            String towards;

            switch (facingDirection) {
                case NORTH:
                    towards = "Towards negative Z";
                    break;
                case SOUTH:
                    towards = "Towards positive Z";
                    break;
                case WEST:
                    towards = "Towards negative X";
                    break;
                case EAST:
                    towards = "Towards positive X";
                    break;
                default:
                    towards = "Invalid";
            }

            ChunkPos chunkPos = new ChunkPos(cameraPos);
            if (!Objects.equals(this.lastPos, chunkPos)) {
                this.lastPos = chunkPos;
                this.clearChunkCache();
            }

            World world = this.getLevel();
            LongSet forcedChunks = world instanceof ServerWorld ? ((ServerWorld) world).getForcedChunks() : LongSets.EMPTY_SET;
            List<String> gameInfo = Lists.newArrayList("Minecraft " + show121 + " (" + this.minecraft.getLaunchedVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" +
                    this.minecraft.getVersionType()) + ")", this.minecraft.fpsString, serverInformation, this.minecraft.levelRenderer.getChunkStatistics(), this.minecraft.levelRenderer.getEntityStatistics(), "P: " + this.minecraft.particleEngine.countParticles() + ". T: " +
                    this.minecraft.level.getEntityCount(), this.minecraft.level.gatherChunkSourceStats());
            String serverChunkStats = this.getServerChunkStats();
            if (serverChunkStats != null) gameInfo.add(serverChunkStats);

            gameInfo.add(this.minecraft.level.dimension().location() + " FC: " + forcedChunks.size());
            gameInfo.add("");
            gameInfo.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.minecraft.getCameraEntity().getX(), this.minecraft.getCameraEntity().getY(), this.minecraft.getCameraEntity().getZ()));
            gameInfo.add(String.format("Block: %d %d %d", cameraPos.getX(), cameraPos.getY(), cameraPos.getZ()));
            gameInfo.add(String.format("Chunk: %d %d %d in %d %d %d", cameraPos.getX() & 15, cameraPos.getY() & 15, cameraPos.getZ() & 15, cameraPos.getX() >> 4, cameraPos.getY() >> 4, cameraPos.getZ() >> 4));
            gameInfo.add(String.format(Locale.ROOT, "Facing: %s (%s) (%.1f / %.1f)", facingDirection, towards, MathHelper.wrapDegrees(cameraEntity.yRot), MathHelper.wrapDegrees(cameraEntity.xRot)));
            if (this.minecraft.level != null) {
                if (this.minecraft.level.hasChunkAt(cameraPos)) {
                    Chunk clientChunk = this.getClientChunk();
                    if (clientChunk.isEmpty()) {
                        gameInfo.add("Waiting for chunk...");
                    } else {
                        int rawBrightness = this.minecraft.level.getChunkSource().getLightEngine().getRawBrightness(cameraPos, 0);
                        int skyLight = this.minecraft.level.getBrightness(LightType.SKY, cameraPos);
                        int blockLight = this.minecraft.level.getBrightness(LightType.BLOCK, cameraPos);
                        gameInfo.add("Client Light: " + rawBrightness + " (" + skyLight + " sky, " + blockLight + " block)");

                        Chunk serverChunk = this.getServerChunk();
                        if (serverChunk != null) {
                            WorldLightManager lightEngine = world.getChunkSource().getLightEngine();
                            gameInfo.add("Server Light: (" + lightEngine.getLayerListener(LightType.SKY).getLightValue(cameraPos) + " sky, " + lightEngine.getLayerListener(LightType.BLOCK).getLightValue(cameraPos) + " block)");
                        } else {
                            gameInfo.add("Server Light: (?? sky, ?? block)");
                        }

                        StringBuilder heightmap = new StringBuilder("CH");

                        for (Heightmap.Type type : Heightmap.Type.values()) {
                            if (type.sendToClient())
                                heightmap.append(" ").append(HEIGHTMAP_NAMES.get(type)).append(": ").append(clientChunk.getHeight(type, cameraPos.getX(), cameraPos.getZ()));
                        }

                        gameInfo.add(heightmap.toString());
                        heightmap.setLength(0);
                        heightmap.append("SH");

                        for (Heightmap.Type type : Heightmap.Type.values()) {
                            if (type.keepAfterWorldgen()) {
                                heightmap.append(" ").append(HEIGHTMAP_NAMES.get(type)).append(": ");
                                if (serverChunk != null) {
                                    heightmap.append(serverChunk.getHeight(type, cameraPos.getX(), cameraPos.getZ()));
                                } else {
                                    heightmap.append("??");
                                }
                            }
                        }

                        gameInfo.add(heightmap.toString());
                        if (cameraPos.getY() >= 0 && cameraPos.getY() < 256) {
                            gameInfo.add("Biome: " + this.minecraft.level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(this.minecraft.level.getBiome(cameraPos)));

                            long inhabitedTime = 0L;
                            float moonBrightness = 0;
                            if (serverChunk != null) {
                                moonBrightness = world.getMoonBrightness();
                                inhabitedTime = serverChunk.getInhabitedTime();
                            }

                            DifficultyInstance instance = new DifficultyInstance(world.getDifficulty(), world.getDayTime(), inhabitedTime, moonBrightness);
                            gameInfo.add(String.format(Locale.ROOT, "Local Difficulty: %.2f // %.2f (Day %d)", instance.getEffectiveDifficulty(), instance.getSpecialMultiplier(), this.minecraft.level.getDayTime() / 24000L));
                        }
                    }
                } else {
                    gameInfo.add("Outside of world...");
                }
            } else {
                gameInfo.add("Outside of world...");
            }

            ServerWorld serverWorld = this.getServerLevel();
            if (serverWorld != null) {
                if (cameraPos.getY() >= 0 && cameraPos.getY() < 256) {
                    Biome biome = serverWorld.getBiome(cameraPos);
                    DecimalFormat format = Util.make(new DecimalFormat("0.000"), format1 -> format1.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
                    gameInfo.add("NoiseRouter C: \"" + biome.getBiomeCategory().getSerializedName() + "\" P: \"" + biome.getPrecipitation().getSerializedName() + "\" D: " + biome.getDepth() + " S: " + biome.getScale());
                    gameInfo.add("Biome builder BT: " + biome.getBaseTemperature() + " T: " + format.format(biome.getTemperature(cameraPos)) + " H: " + biome.getDownfall());
                }

                WorldEntitySpawner.EntityDensityManager densityManager = serverWorld.getChunkSource().getLastSpawnState();
                if (densityManager != null) {
                    Object2IntMap<EntityClassification> categoryCounts = densityManager.getMobCategoryCounts();
                    int spawnableChunkCount = densityManager.getSpawnableChunkCount();
                    gameInfo.add("SC: " + spawnableChunkCount + ", " + Stream.of(EntityClassification.values()).map(classification -> Character.toUpperCase(classification.getName().charAt(0)) + ": " + categoryCounts.getInt(classification)).collect(Collectors.joining(", ")));
                } else {
                    gameInfo.add("SC: N/A");
                }
            }

            ShaderGroup currentShader = this.minecraft.gameRenderer.currentEffect();
            if (currentShader != null) gameInfo.add("Shader: " + currentShader.getName());

            gameInfo.add(this.minecraft.getSoundManager().getDebugString() + String.format(" (Mood %d%%)", Math.round(this.minecraft.player.getCurrentMood() * 100F)));
            cir.setReturnValue(gameInfo);
        }
    }

    @Unique
    private String getServerInformation() {
        IntegratedServer integratedServer = this.minecraft.getSingleplayerServer();
        NetworkManager networkManager = this.minecraft.getConnection().getConnection();
        float averageSentPackets = networkManager.getAverageSentPackets();
        float averageReceivedPackets = networkManager.getAverageReceivedPackets();
        String format;

        if (integratedServer != null) {
            format = String.format("Integrated server @ %.0f ms ticks, %.0f tx, %.0f rx", integratedServer.getAverageTickTime(), averageSentPackets, averageReceivedPackets);
        } else {
            format = String.format("\"%s\" server, %.0f tx, %.0f rx", this.minecraft.player.getServerBrand(), averageSentPackets, averageReceivedPackets);
        }
        return format;
    }
}
