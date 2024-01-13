package com.hidoni.transmog.data;

import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TRANSMOGRIFICATION_TABLE.get())
                .pattern(" X ")
                .pattern("YZY")
                .pattern("ZZZ")
                .define('X', ModItems.VOID_FRAGMENT.get())
                .define('Y', Items.GLASS)
                .define('Z', Items.AMETHYST_BLOCK)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(output, ModBlocks.TRANSMOGRIFICATION_TABLE.getResourceLocation());
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOID_FRAGMENT.get())
                .pattern(" Z ")
                .pattern("XYX")
                .pattern(" X ")
                .define('X', Items.OBSIDIAN)
                .define('Y', Items.AMETHYST_SHARD)
                .define('Z', Items.ENDER_PEARL)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(output, ModItems.VOID_FRAGMENT.getResourceLocation());
    }
}
