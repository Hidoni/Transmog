package com.hidoni.transmog.platform.services;

import com.hidoni.transmog.registry.RegistryProvider;
import net.minecraft.resources.ResourceKey;

public interface IRegistryHelper {
    <T> RegistryProvider<T> getRegistry(ResourceKey<? extends net.minecraft.core.Registry<T>> resourceKey);
}
