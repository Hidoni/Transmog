package com.hidoni.transmog.data;

import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.TRANSMOGRIFICATION_TABLE.get())
                .pattern(" X ")
                .pattern("YZY")
                .pattern("ZZZ")
                .define('X', ModItems.VOID_FRAGMENT.get())
                .define('Y', Items.GLASS)
                .define('Z', Items.AMETHYST_BLOCK)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(consumer, ModBlocks.TRANSMOGRIFICATION_TABLE.getResourceLocation());
        ShapedRecipeBuilder.shaped(ModItems.VOID_FRAGMENT.get())
                .pattern(" Z ")
                .pattern("XYX")
                .pattern(" X ")
                .define('X', Items.OBSIDIAN)
                .define('Y', Items.AMETHYST_SHARD)
                .define('Z', Items.ENDER_PEARL)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(consumer, ModItems.VOID_FRAGMENT.getResourceLocation());
    }
}
