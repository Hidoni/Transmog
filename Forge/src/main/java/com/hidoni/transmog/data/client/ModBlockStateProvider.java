package com.hidoni.transmog.data.client;

import com.hidoni.transmog.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        getVariantBuilder(ModBlocks.TRANSMOGRIFICATION_TABLE.get()).forAllStates((blockState -> ConfiguredModel.builder().modelFile(models()
                .withExistingParent(ModBlocks.TRANSMOGRIFICATION_TABLE.getResourceLocation().getPath(), mcLoc("block/block"))
                .element()
                .from(0, 0, 0)
                .to(16, 8, 16)
                .allFaces(((direction, faceBuilder) -> faceBuilder
                        .texture(directionToTextureLower(direction))
                        .cullface(direction == Direction.UP ? null : direction)
                        .end())
                ).end()
                .element()
                .from(0, 8, 0)
                .to(16, 12, 16)
                .allFaces(((direction, faceBuilder) -> faceBuilder
                        .texture(directionToTextureUpper(direction))
                        .cullface(direction == Direction.UP ? null : direction)
                        .end())
                ).end()
                .element()
                .from(15, 12, 15)
                .to(1, 8, 1)
                .face(Direction.EAST)
                .end()
                .face(Direction.SOUTH)
                .end()
                .face(Direction.NORTH)
                .end()
                .face(Direction.WEST)
                .end()
                .faces(((direction, faceBuilder) -> faceBuilder
                        .texture(directionToTextureUpper(direction))
                        .end())
                )
                .end()
                .texture("bottom", modLoc("block/transmogrification_table_bottom"))
                .texture("side", modLoc("block/transmogrification_table_side"))
                .texture("top", modLoc("block/transmogrification_table_top"))
                .texture("particle", modLoc("block/transmogrification_table_side"))
                .renderType("cutout")
        ).build()));
    }

    private String directionToTextureLower(Direction dir) {
        return switch (dir) {
            case UP, DOWN -> "#bottom";
            default -> "#side";
        };
    }

    private String directionToTextureUpper(Direction dir) {
        return switch (dir) {
            case UP, DOWN -> "#top";
            default -> "#side";
        };
    }
}
