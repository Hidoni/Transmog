package com.hidoni.transmog.data;

import com.hidoni.transmog.Constants;
import com.hidoni.transmog.registry.ModBlocks;
import com.hidoni.transmog.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput recipeOutput) {
        super(lookupProvider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        this.shaped(RecipeCategory.MISC, ModBlocks.TRANSMOGRIFICATION_TABLE.get())
                .pattern(" X ")
                .pattern("YZY")
                .pattern("ZZZ")
                .define('X', ModItems.VOID_FRAGMENT.get())
                .define('Y', Items.GLASS)
                .define('Z', Items.AMETHYST_BLOCK)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(output);
        this.shaped(RecipeCategory.MISC, ModItems.VOID_FRAGMENT.get())
                .pattern(" Z ")
                .pattern("XYX")
                .pattern(" X ")
                .define('X', Items.OBSIDIAN)
                .define('Y', Items.AMETHYST_SHARD)
                .define('Z', Items.ENDER_PEARL)
                .unlockedBy("has_item", has(Items.ENDER_PEARL))
                .save(output);
    }

    public static class Runner extends RecipeProvider.Runner {
        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return Constants.MOD_NAME + ModRecipeProvider.class.getSimpleName();
        }
    }
}
