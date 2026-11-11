package com.enderdevelopment.frontierwarfare;

import com.enderdevelopment.frontierwarfare.item.FieldRifleItem;
import com.enderdevelopment.frontierwarfare.item.FragmentationBombItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class FrontierWarfare implements ModInitializer {
    public static final String MOD_ID = "frontierwarfare";
    public static final Item CARTRIDGE = register("cartridge", new Item(new Item.Properties()));
    public static final Item FIELD_RIFLE = register("field_rifle", new FieldRifleItem(new Item.Properties().durability(384)));
    public static final Item FRAGMENTATION_BOMB = register("fragmentation_bomb", new FragmentationBombItem(new Item.Properties().stacksTo(16)));
    public static final CreativeModeTab TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "frontier_warfare"), CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.frontierwarfare"))
                    .icon(() -> new ItemStack(FIELD_RIFLE))
                    .displayItems((context, entries) -> {
                        entries.accept(FIELD_RIFLE);
                        entries.accept(CARTRIDGE);
                        entries.accept(FRAGMENTATION_BOMB);
                    }).build());

    private static Item register(String name, Item item) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    @Override
    public void onInitialize() {
    }
}
