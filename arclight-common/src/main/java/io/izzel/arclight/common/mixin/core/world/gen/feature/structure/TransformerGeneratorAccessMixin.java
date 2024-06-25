package io.izzel.arclight.common.mixin.core.world.gen.feature.structure;

import io.izzel.arclight.common.bridge.core.world.IWorldWriterBridge;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v.util.TransformerGeneratorAccess;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TransformerGeneratorAccess.class)
public abstract class TransformerGeneratorAccessMixin implements IWorldWriterBridge {

  // @formatter:off
  @Shadow
  public abstract boolean addFreshEntity(Entity entityIn);
  // @formatter:on

  private CreatureSpawnEvent.SpawnReason spawnReason;

  public boolean addFreshEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
    return this.addFreshEntity(entity);
  }

  @Override
  public boolean bridge$addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
    return addFreshEntity(entity, reason);
  }

  @Override
  public void bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason reason) {
    this.spawnReason = reason;
  }

  @Override
  public CreatureSpawnEvent.SpawnReason bridge$getAddEntityReason() {
    return spawnReason;
  }
}