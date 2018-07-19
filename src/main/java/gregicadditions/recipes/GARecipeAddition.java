package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.CokeOvenRecipe;
import gregicadditions.machines.GATileEntities;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials.*;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.stream.Collectors;

public class GARecipeAddition {

    private static final MaterialStack[] solderingList = {
            new MaterialStack(Materials.Tin, 2L),
            new MaterialStack(Materials.SolderingAlloy, 1L),
            new MaterialStack(Materials.Lead, 4L)
    };

    private static final MaterialStack[] sawLubricants = {
            new MaterialStack(Materials.Lubricant, 1L),
            new MaterialStack(Materials.DistilledWater, 3L),
            new MaterialStack(Materials.Water, 4L)
    };

    public static void postInit() {
        //GTNH Bricks
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(), GAMetaItems.COKE_BRICK.getStackForm());
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(new ItemStack(Items.CLAY_BALL), new ItemStack(Blocks.SAND, 2)).outputs(GAMetaItems.COKE_BRICK.getStackForm()).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2).inputs(new ItemStack(Items.CLAY_BALL)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(new ItemStack(Items.BRICK)).buildAndRegister();
        ModHandler.addShapelessRecipe("acacia_form", GAMetaItems.ACACIA_FORM.getStackForm(), MetaItems.PLANK_ACACIA.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("birch_form", GAMetaItems.BIRCH_FORM.getStackForm(), MetaItems.PLANK_BIRCH.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("darkoak_form", GAMetaItems.DARK_OAK_FORM.getStackForm(), MetaItems.PLANK_DARKOAK.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("jungle_form", GAMetaItems.JUNGLE_FORM.getStackForm(), MetaItems.PLANK_JUNGLE.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("oak_form", GAMetaItems.OAK_FORM.getStackForm(), MetaItems.PLANK_OAK.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("spruce_form", GAMetaItems.SPRUCE_FORM.getStackForm(), MetaItems.PLANK_SPRUCE.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("clay_brick", GAMetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.CLAY_BALL), "formWood");
        ModHandler.addShapedRecipe("eight_clay_brick", GAMetaItems.COMPRESSED_CLAY.getStackForm(8), "BBB", "BFB", "BBB", 'B', new ItemStack(Items.CLAY_BALL), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_brick", GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(3), "BBB", "SFS", "SSS", 'B', new ItemStack(Items.CLAY_BALL), 'S', new ItemStack(Blocks.SAND), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_bricks",GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.COKE_OVEN_BRICKS),"BB","BB",'B',GAMetaItems.COKE_BRICK.getStackForm());
        ModHandler.addShapedRecipe("coke_oven",GATileEntities.COKE_OVEN.getStackForm(),"hRS","PBR","dRS",'R',OreDictUnifier.get(OrePrefix.stick,Materials.Iron),'S',OreDictUnifier.get(OrePrefix.screw,Materials.Iron),'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron),'B',GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.COKE_OVEN_BRICKS));

        //GT5U Old Primitive Brick Processing
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_primitive_bricks"));
        ModHandler.addShapedRecipe("fireclay_bricks", MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS),"BB","BB",'B',GAMetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(),GAMetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.addShapelessRecipe("fireclay",OreDictUnifier.get(OrePrefix.dust,GAMaterials.Fireclay,2),OreDictUnifier.get(OrePrefix.dust,GAMaterials.Brick),OreDictUnifier.get(OrePrefix.dust,Materials.Clay));
        RecipeMaps.MIXER_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Brick),OreDictUnifier.get(OrePrefix.dust,Materials.Clay)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Fireclay,2)).duration(200).EUt(8).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Fireclay)).outputs(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm()).duration(400).EUt(2).buildAndRegister();
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BRICK)).outputs(OreDictUnifier.get(OrePrefix.dustSmall,GAMaterials.Brick)).duration(16).EUt(10).buildAndRegister();

        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL),MetaItems.RUBBER_DROP.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));
        ModHandler.addShapelessRecipe("harder_bone_meal",new ItemStack(Items.DYE, 3, 15),new ItemStack(Items.BONE),ToolDictNames.craftingToolMortar);
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(16).EUt(10).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(300).EUt(2).buildAndRegister();
        ModHandler.addShapedRecipe("fireclay_bricks", MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS), "BB", "BB", 'B', GAMetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());

        //GT6 Bending
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
        ModHandler.addShapedRecipe("bucket",new ItemStack(Items.BUCKET),"ChC"," P ",'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron),'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).inputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron,2),OreDictUnifier.get(OrePrefix.plate,Materials.Iron)).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).inputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.WroughtIron,2),OreDictUnifier.get(OrePrefix.plate,Materials.WroughtIron)).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
        ModHandler.addShapedRecipe("iron_helmet",new ItemStack(Items.IRON_HELMET),"PPP","ChC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
        ModHandler.addShapedRecipe("iron_chestplate",new ItemStack(Items.IRON_CHESTPLATE),"PhP","CPC","CPC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
        ModHandler.addShapedRecipe("iron_leggings",new ItemStack(Items.IRON_LEGGINGS),"PCP","ChC","C C",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
        ModHandler.addShapedRecipe("iron_boots",new ItemStack(Items.IRON_BOOTS),"P P","ChC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Iron),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Iron));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
        ModHandler.addShapedRecipe("golden_helmet",new ItemStack(Items.GOLDEN_HELMET),"PPP","ChC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Gold),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Gold));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
        ModHandler.addShapedRecipe("golden_chestplate",new ItemStack(Items.GOLDEN_CHESTPLATE),"PhP","CPC","CPC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Gold),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Gold));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
        ModHandler.addShapedRecipe("golden_leggings",new ItemStack(Items.GOLDEN_LEGGINGS),"PCP","ChC","C C",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Gold),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Gold));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
        ModHandler.addShapedRecipe("golden_boots",new ItemStack(Items.GOLDEN_BOOTS),"P P","ChC",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Gold),'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),Materials.Gold));
        ModHandler.addShapedRecipe("chain_helmet",new ItemStack(Items.CHAINMAIL_HELMET),"RRR","RhR",'R',OreDictUnifier.get(OrePrefix.ring,Materials.Iron));
        ModHandler.addShapedRecipe("chain_chestplate",new ItemStack(Items.CHAINMAIL_CHESTPLATE),"RhR","RRR","RRR",'R',OreDictUnifier.get(OrePrefix.ring,Materials.Iron));
        ModHandler.addShapedRecipe("chain_leggings",new ItemStack(Items.CHAINMAIL_LEGGINGS),"RRR","RhR","R R",'R',OreDictUnifier.get(OrePrefix.ring,Materials.Iron));
        ModHandler.addShapedRecipe("chain_boots",new ItemStack(Items.CHAINMAIL_BOOTS),"R R","RhR",'R',OreDictUnifier.get(OrePrefix.ring,Materials.Iron));
        for(Material m : IngotMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.ring, m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.ring,m));
                ModHandler.addShapedRecipe("ring_" + m.toString(), OreDictUnifier.get(OrePrefix.ring, m), "hS", " C", 'S', OreDictUnifier.get(OrePrefix.stick, m), 'C', "craftingToolBendingCylinderSmall");
            }
            if (!OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m).isEmpty()) {
                ModHandler.addShapedRecipe("curved_plate_" + m.toString(), OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), "h", "P", "C", 'P', OreDictUnifier.get(OrePrefix.plate, m), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("flatten_plate_"+m.toString(), OreDictUnifier.get(OrePrefix.plate,m),"h","C",'C',OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),m));
                RecipeMaps.BENDER_RECIPES.recipeBuilder().EUt(24).duration((int)m.getMass()).inputs(OreDictUnifier.get(OrePrefix.plate,m)).circuitMeta(0).outputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(OrePrefix.rotor, m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.rotor,m));
                ModHandler.addShapedRecipe("rotor_" + m.toString(), OreDictUnifier.get(OrePrefix.rotor, m), "ChC", "SRf", "CdC", 'C', OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), 'S', OreDictUnifier.get(OrePrefix.screw,m),'R',OreDictUnifier.get(OrePrefix.ring,m));
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24).inputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"),m,4),OreDictUnifier.get(OrePrefix.ring,m)).fluidInputs(Materials.SolderingAlloy.getFluid(32)).outputs(OreDictUnifier.get(OrePrefix.rotor,m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(OrePrefix.foil,m).isEmpty()) {
                ModHandler.addShapedRecipe("foil_"+m.toString(), OreDictUnifier.get(OrePrefix.foil,m,2), "hPC",'P',OreDictUnifier.get(OrePrefix.plate,m),'C',"craftingToolBendingCylinder");
                GARecipeMaps.CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int)m.getMass()).inputs(OreDictUnifier.get(OrePrefix.plate,m)).outputs(OreDictUnifier.get(OrePrefix.foil,m,4)).buildAndRegister();
            }

            //GT6 Plate Recipe
            if (m instanceof IngotMaterial && !OreDictUnifier.get(OrePrefix.plate,m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.plate,m));
                ModHandler.addShapedRecipe("ingot_double_"+m.toString(),OreDictUnifier.get(OrePrefix.valueOf("ingotDouble"),m),"h","I","I",'I',OreDictUnifier.get(OrePrefix.ingot,m));
                ModHandler.addShapedRecipe("plate_"+m.toString(),OreDictUnifier.get(OrePrefix.plate,m),"h","I",'I',OreDictUnifier.get(OrePrefix.valueOf("ingotDouble"),m));
            }

            //GT5U Block Recipes
            if (m instanceof IngotMaterial && !m.hasFlag(DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES)) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.block, m));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_" + m.toString()));
                RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(OreDictUnifier.get(OrePrefix.ingot, m, 9)).outputs(OreDictUnifier.get(OrePrefix.block, m)).buildAndRegister();
            }
        }
        for (Material m : GemMaterial.MATERIAL_REGISTRY) {
            if (m instanceof GemMaterial && !m.hasFlag(DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES)) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.block, m));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_" + m.toString()));
                if (m!= Materials.NetherQuartz) {
                    RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(OreDictUnifier.get(OrePrefix.gem, m, 9)).outputs(OreDictUnifier.get(OrePrefix.block, m)).buildAndRegister();
                    RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(OrePrefix.block, m)).outputs(OreDictUnifier.get(OrePrefix.gem, m, 9)).buildAndRegister();
                }
            }
        }
        for (Material m : DustMaterial.MATERIAL_REGISTRY) {
            if (m instanceof DustMaterial && OreDictUnifier.get(OrePrefix.ingot,m).isEmpty() && OreDictUnifier.get(OrePrefix.gem,m).isEmpty() && !m.hasFlag(DustMaterial.MatFlags.EXCLUDE_BLOCK_CRAFTING_RECIPES)) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.block, m));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_"+m.toString()));
                if (m!=Materials.Bone)
                    RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(OreDictUnifier.get(OrePrefix.dust, m, 9)).outputs(OreDictUnifier.get(OrePrefix.block, m)).buildAndRegister();
            }
        }
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(OrePrefix.block, Materials.NetherQuartz)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 4)).buildAndRegister();
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_ingot_from_block"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:gold_ingot_from_block"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:redstone"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:diamond"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:emerald"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:coal"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:lapis_lazuli"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(new ItemStack(Items.DYE, 9,15)).outputs(OreDictUnifier.get(OrePrefix.block, Materials.Bone)).buildAndRegister();

        //Wood To Pulp
        ModHandler.addShapelessRecipe("log_to_pulp", OreDictUnifier.get(OrePrefix.dust, Materials.Wood, 1), "logWood", ToolDictNames.craftingToolMortar);

        //GT5U Slabs Are Made With A Saw
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:stone_slab"));
        ModHandler.addShapedRecipe("stone_slab",new ItemStack(Blocks.STONE_SLAB,2),"sS",'S',new ItemStack(Blocks.STONE));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:sandstone_slab"));
        ModHandler.addShapedRecipe("sandstone_slab",new ItemStack(Blocks.STONE_SLAB,2,1),"sS",'S',new ItemStack(Blocks.SANDSTONE));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:cobblestone_slab"));
        ModHandler.addShapedRecipe("cobblestone_slab",new ItemStack(Blocks.STONE_SLAB,2,3),"sC",'C',new ItemStack(Blocks.COBBLESTONE));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:brick_slab"));
        ModHandler.addShapedRecipe("brick_slab",new ItemStack(Blocks.STONE_SLAB,2,4),"sB",'B',new ItemStack(Blocks.BRICK_BLOCK));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:stone_brick_slab"));
        ModHandler.addShapedRecipe("stone_brick_slab",new ItemStack(Blocks.STONE_SLAB,2,5),"sB",'B',new ItemStack(Blocks.STONEBRICK));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:nether_brick_slab"));
        ModHandler.addShapedRecipe("nether_brick_slab",new ItemStack(Blocks.STONE_SLAB,2,6),"sB",'B',new ItemStack(Blocks.NETHER_BRICK));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:quartz_slab"));
        ModHandler.addShapedRecipe("quartz_slab",new ItemStack(Blocks.STONE_SLAB,2,7),"sQ",'Q',new ItemStack(Blocks.QUARTZ_BLOCK));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:oak_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:spruce_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:birch_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:jungle_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:acacia_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:dark_oak_wooden_slab"));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:red_sandstone_slab"));
        ModHandler.addShapedRecipe("red_sandstone_slab",new ItemStack(Blocks.STONE_SLAB2,2),"sS",'S',new ItemStack(Blocks.RED_SANDSTONE));
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:purpur_slab"));
        ModHandler.addShapedRecipe("purpur",new ItemStack(Blocks.PURPUR_SLAB,2),"sP",'P',new ItemStack(Blocks.PURPUR_BLOCK));

        List<ItemStack> allWoodLogs =  OreDictionary.getOres("logWood").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        //Coke Oven Recipes
        GARecipeMaps.COKE_OVEN_RECIPES.add(new CokeOvenRecipe(CountableIngredient.from(OreDictUnifier.get(OrePrefix.gem,Materials.Coal)), OreDictUnifier.get(OrePrefix.gem,GAMaterials.Coke), Materials.Creosote.getFluid(500), 1800));
        for (ItemStack stack : allWoodLogs) {
            ItemStack smeltingOutput = ModHandler.getSmeltingOutput(stack);
            if (!smeltingOutput.isEmpty() && smeltingOutput.getItem() == Items.COAL && smeltingOutput.getMetadata() == 1) {
                int coalAmount = smeltingOutput.getCount();
                ModHandler.removeFurnaceSmelting(stack);
                GARecipeMaps.COKE_OVEN_RECIPES.add(new CokeOvenRecipe(CountableIngredient.from(stack), new ItemStack(Items.COAL, coalAmount, 1), Materials.Creosote.getFluid(500), 1800));

                //Pyrolise Oven Recipes
                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(0)
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(Materials.Creosote.getFluid(4000 * coalAmount))
                        .duration(440)
                        .EUt(64)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(1)
                        .fluidInputs(Materials.Nitrogen.getFluid(400))
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(Materials.Creosote.getFluid(4000 * coalAmount))
                        .duration(200)
                        .EUt(96)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(2)
                        .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ash, 4 * coalAmount))
                        .fluidOutputs(Materials.OilHeavy.getFluid(200 * coalAmount))
                        .duration(280)
                        .EUt(192)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(3)
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodVinegar.getFluid(3000 * coalAmount))
                        .duration(640)
                        .EUt(64)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(4)
                        .fluidInputs(Materials.Nitrogen.getFluid(400))
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodVinegar.getFluid(3000 * coalAmount))
                        .duration(320)
                        .EUt(96)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(5)
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodGas.getFluid(1500 * coalAmount))
                        .duration(640)
                        .EUt(64)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(6)
                        .fluidInputs(Materials.Nitrogen.getFluid(400))
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodGas.getFluid(1500 * coalAmount))
                        .duration(320)
                        .EUt(96)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(7)
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodTar.getFluid(1500 * coalAmount))
                        .duration(640)
                        .EUt(64)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(8)
                        .fluidInputs(Materials.Nitrogen.getFluid(400))
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.WoodTar.getFluid(1500 * coalAmount))
                        .duration(320)
                        .EUt(96)
                        .buildAndRegister();

                RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copyAmount(16, stack))
                        .circuitMeta(9)
                        .fluidInputs(Materials.Nitrogen.getFluid(400))
                        .outputs(new ItemStack(Items.COAL, 20 * coalAmount, 1))
                        .fluidOutputs(GAMaterials.CharcoalByproducts.getFluid(4000 * coalAmount))
                        .duration(320)
                        .EUt(96)
                        .buildAndRegister();
            }
        }
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GAMetaItems.BIO_CHAFF.getStackForm())
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(Materials.Biomass.getFluid(1500))
                .duration(100)
                .EUt(10)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(GAMetaItems.BIO_CHAFF.getStackForm())
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(1500))
                .fluidOutputs(GAMaterials.FermentedBiomass.getFluid(1500))
                .duration(200)
                .EUt(10)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.SUGAR,23))
                .circuitMeta(1)
                .outputs(OreDictUnifier.get(OrePrefix.dust,Materials.Charcoal,12))
                .fluidOutputs(Materials.Water.getFluid(1500))
                .duration(640)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.SUGAR,23))
                .circuitMeta(2)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(OreDictUnifier.get(OrePrefix.dust,Materials.Charcoal,12))
                .fluidOutputs(Materials.Water.getFluid(1500))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        //Cracker Recipes
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.LightFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.HeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.CrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.CrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CRACKING_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Chemical Reactor Cracking
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.LightFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.HeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000),GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.CrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.CrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000),GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Distillation Recipes
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).fluidInputs(GAMaterials.HydroCrackedEthane.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(200)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(GAMaterials.SteamCrackedEthane.getFluid(500)).fluidOutputs(Materials.Methane.getFluid(750)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).fluidInputs(GAMaterials.HydroCrackedEthylene.getFluid(100)).fluidOutputs(GAMaterials.Ethane.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedPropene.getFluid(100)).fluidOutputs(GAMaterials.Propane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedPropene.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedPropene.getFluid(100)).fluidOutputs(GAMaterials.Ethylene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).fluidInputs(GAMaterials.SteamCrackedPropene.getFluid(500)).fluidOutputs(Materials.Methane.getFluid(750)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedPropane.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedPropane.getFluid(100)).fluidOutputs(GAMaterials.Ethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(120).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedPropane.getFluid(500)).fluidOutputs(Materials.Methane.getFluid(875)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedPropane.getFluid(500)).fluidOutputs(GAMaterials.Ethylene.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(100)).fluidOutputs(GAMaterials.Butane.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(100)).fluidOutputs(GAMaterials.Propane.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(100)).fluidOutputs(Materials.Naphtha.getFluid(80)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(200)).fluidOutputs(Materials.Methane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(5).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(200)).fluidOutputs(GAMaterials.Ethane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(1).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.HeavyFuel.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(2).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Butadiene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(4).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.Naphtha.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(5).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Benzene.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(6).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(7).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.Toluene.getFluid(30)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(8).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Butene.getFluid(65)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(9).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Propene.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(10).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Propane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(11).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).fluidOutputs(GAMaterials.Ethane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedButane.getFluid(75)).fluidOutputs(GAMaterials.Propane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedButane.getFluid(75)).fluidOutputs(Materials.Methane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedButane.getFluid(75)).fluidOutputs(GAMaterials.Ethane.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedButane.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(200)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedButane.getFluid(100)).fluidOutputs(GAMaterials.Ethane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.SteamCrackedButane.getFluid(100)).fluidOutputs(GAMaterials.Ethylene.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.SteamCrackedButane.getFluid(100)).fluidOutputs(GAMaterials.Propane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedNaphtha.getFluid(100)).fluidOutputs(GAMaterials.Propane.getFluid(30)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedNaphtha.getFluid(100)).fluidOutputs(GAMaterials.Ethane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedNaphtha.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.HydroCrackedNaphtha.getFluid(100)).fluidOutputs(GAMaterials.Butane.getFluid(80)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(Materials.HeavyFuel.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Butene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Propane.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(5).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Butadiene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(6).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Propene.getFluid(300)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(7).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(8).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(Materials.Toluene.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(9).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Benzene.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(10).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Ethane.getFluid(65)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(11).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).fluidOutputs(Materials.LightFuel.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(100)).fluidOutputs(Materials.LightFuel.getFluid(60)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(100)).fluidOutputs(Materials.Naphtha.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(100)).fluidOutputs(GAMaterials.Butane.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(100)).fluidOutputs(GAMaterials.Propane.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(5).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(200)).fluidOutputs(Materials.Methane.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(48).EUt(30).circuitMeta(6).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(200)).fluidOutputs(GAMaterials.Ethane.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(1).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(2).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Propane.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.Toluene.getFluid(80)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(4).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Propene.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(5).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Benzene.getFluid(400)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(6).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.Naphtha.getFluid(125)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(7).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.LightFuel.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(8).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(9).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Ethane.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(10).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Butene.getFluid(80)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(11).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.Butadiene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedGas.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(140)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedGas.getFluid(100)).fluidOutputs(Materials.Helium.getFluid(2)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedGas.getFluid(100)).fluidOutputs(Materials.Hydrogen.getFluid(134)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(192).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).fluidOutputs(GAMaterials.Ethylene.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(192).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).fluidOutputs(GAMaterials.Ethane.getFluid(6)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(192).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).fluidOutputs(Materials.Methane.getFluid(914)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(192).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).fluidOutputs(GAMaterials.Propene.getFluid(6)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(192).EUt(30).circuitMeta(5).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).fluidOutputs(Materials.Helium.getFluid(16)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(18).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(75)).fluidOutputs(GAMaterials.Butane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(18).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(75)).fluidOutputs(Materials.Methane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(18).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(75)).fluidOutputs(GAMaterials.Ethane.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(18).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(75)).fluidOutputs(GAMaterials.Ethylene.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(18).EUt(30).circuitMeta(5).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(75)).fluidOutputs(GAMaterials.Propene.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(19).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedButene.getFluid(80)).fluidOutputs(GAMaterials.Ethylene.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(120).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedButene.getFluid(500)).fluidOutputs(Materials.Methane.getFluid(750)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.SteamCrackedButene.getFluid(1000)).fluidOutputs(GAMaterials.Propene.getFluid(120)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.HydroCrackedButadiene.getFluid(75)).fluidOutputs(GAMaterials.Ethylene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(180).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.HydroCrackedButadiene.getFluid(75)).fluidOutputs(GAMaterials.Butene.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(96).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.SteamCrackedButadiene.getFluid(400)).fluidOutputs(GAMaterials.Ethylene.getFluid(75)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(96).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.SteamCrackedButadiene.getFluid(400)).fluidOutputs(Materials.Methane.getFluid(450)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(240).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.SteamCrackedButadiene.getFluid(1000)).fluidOutputs(GAMaterials.Propene.getFluid(125)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(16).circuitMeta(1).fluidInputs(GAMaterials.DilutedHydrochloricAcid.getFluid(40)).fluidOutputs(GAMaterials.HypochlorousAcid.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(24).EUt(16).circuitMeta(2).fluidInputs(GAMaterials.DilutedHydrochloricAcid.getFluid(40)).fluidOutputs(Materials.Water.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(30).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.DilutedSulfuricAcid.getFluid(75)).fluidOutputs(Materials.SulfuricAcid.getFluid(50)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(30).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.DilutedSulfuricAcid.getFluid(75)).fluidOutputs(Materials.Water.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(1).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(Materials.Water.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(2).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(GAMaterials.Methanol.getFluid(60)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(3).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(GAMaterials.MethylAcetate.getFluid(2)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(4).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(GAMaterials.AceticAcid.getFluid(20)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(5).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(GAMaterials.Acetone.getFluid(10)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(6).fluidInputs(GAMaterials.WoodVinegar.getFluid(200)).fluidOutputs(Materials.Ethanol.getFluid(2)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.WoodGas.getFluid(200)).fluidOutputs(Materials.CarbonDioxide.getFluid(98)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.WoodGas.getFluid(200)).fluidOutputs(Materials.Hydrogen.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(3).fluidInputs(GAMaterials.WoodGas.getFluid(200)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(68)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(4).fluidInputs(GAMaterials.WoodGas.getFluid(200)).fluidOutputs(GAMaterials.Ethylene.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(5).fluidInputs(GAMaterials.WoodTar.getFluid(200)).fluidOutputs(GAMaterials.Phenol.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(6).fluidInputs(GAMaterials.WoodTar.getFluid(200)).fluidOutputs(GAMaterials.Benzene.getFluid(70)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(7).fluidInputs(GAMaterials.WoodTar.getFluid(200)).fluidOutputs(Materials.Creosote.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(64).circuitMeta(8).fluidInputs(GAMaterials.WoodTar.getFluid(200)).fluidOutputs(Materials.Toluene.getFluid(15)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(80).EUt(64).circuitMeta(1).fluidInputs(GAMaterials.CharcoalByproducts.getFluid(1000)).fluidOutputs(GAMaterials.WoodTar.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(80).EUt(64).circuitMeta(2).fluidInputs(GAMaterials.CharcoalByproducts.getFluid(1000)).fluidOutputs(GAMaterials.WoodVinegar.getFluid(500)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(80).EUt(64).circuitMeta(3).fluidInputs(GAMaterials.CharcoalByproducts.getFluid(1000)).fluidOutputs(GAMaterials.WoodGas.getFluid(250)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(160).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.CalciumAcetate.getFluid(1000)).fluidOutputs(GAMaterials.Acetone.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(160).EUt(30).circuitMeta(2).fluidInputs(GAMaterials.CalciumAcetate.getFluid(1000)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(GAMaterials.FishOil.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(8).circuitMeta(1).fluidInputs(GAMaterials.FermentedBiomass.getFluid(10)).fluidOutputs(GAMaterials.BioGas.getFluid(18)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(2).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(25)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(3).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(600)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(4).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(Materials.CarbonDioxide.getFluid(400)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(5).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(Materials.Ethanol.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(6).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(GAMaterials.Ammonia.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(7).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(GAMaterials.Methanol.getFluid(150)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(1400).EUt(8).circuitMeta(8).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(375)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(160).circuitMeta(1).fluidInputs(GAMaterials.Acetone.getFluid(100)).fluidOutputs(Materials.Methane.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(160).circuitMeta(2).fluidInputs(GAMaterials.Acetone.getFluid(100)).fluidOutputs(GAMaterials.Ethenone.getFluid(100)).buildAndRegister();

        //Fluid Heater Recipes
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).fluidInputs(GAMaterials.Acetone.getFluid(100)).fluidOutputs(GAMaterials.Ethenone.getFluid(100)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).fluidInputs(GAMaterials.CalciumAcetate.getFluid(200)).fluidOutputs(GAMaterials.Acetone.getFluid(200)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(24).fluidInputs(GAMaterials.RawGrowthMedium.getFluid(500)).fluidOutputs(GAMaterials.SterilizedGrowthMedium.getFluid(500)).buildAndRegister();

        //Fermenter Recipe
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(150).EUt(2).fluidInputs(Materials.Biomass.getFluid(100)).fluidOutputs(GAMaterials.FermentedBiomass.getFluid(100)).buildAndRegister();

        //Fish Oil Recipes
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH)).fluidOutputs(GAMaterials.FishOil.getFluid(40));
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH,1,1)).fluidOutputs(GAMaterials.FishOil.getFluid(60));
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH,1,2)).fluidOutputs(GAMaterials.FishOil.getFluid(70));
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH,1,3)).fluidOutputs(GAMaterials.FishOil.getFluid(30));

        List<ItemStack> allWoodLeaves =  OreDictionary.getOres("treeLeaves").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        //Biomass Process
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(129).EUt(4).inputs(GAMetaItems.BIO_CHAFF.getStackForm()).fluidInputs(Materials.Water.getFluid(750)).fluidOutputs(Materials.Biomass.getFluid(750)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(129).EUt(4).inputs(GAMetaItems.BIO_CHAFF.getStackForm()).fluidInputs(Materials.DistilledWater.getFluid(750)).fluidOutputs(Materials.Biomass.getFluid(750)).buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GAMetaItems.BIO_CHAFF.getStackForm()).outputs(new ItemStack(Blocks.DIRT)).buildAndRegister();

        for (ItemStack stack : allWoodLeaves)
            RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GTUtility.copyAmount(8, stack)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT_SEEDS,16)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.BEETROOT_SEEDS,16)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.MELON_SEEDS,16)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.PUMPKIN_SEEDS,16)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.POTATO,8)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.CARROT,8)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.CACTUS,8)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.DEADBUSH,8)).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(MetaItems.PLANT_BALL.getStackForm()).outputs(GAMetaItems.BIO_CHAFF.getStackForm()).buildAndRegister();

        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().duration(16).EUt(4).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Wood)).chancedOutput(MetaItems.PLANT_BALL.getStackForm(),100).fluidOutputs(Materials.Creosote.getFluid(5)).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT,8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();

        //Circuit Rabbit Hole - Layer 1
        for (MaterialStack stack : solderingList) {
            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;

            ModHandler.addShapedRecipe("vacuum_tube_1", GAMetaItems.VACUUM_TUBE.getStackForm(), "PTP", "WWW", 'P', new ItemStack(Items.PAPER), 'T', GAMetaItems.GLASS_TUBE.getStackForm(), 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper));
            ModHandler.addShapedRecipe("vacuum_tube_2", GAMetaItems.VACUUM_TUBE.getStackForm(), "PTP", "WWW", 'P', new ItemStack(Items.PAPER), 'T', GAMetaItems.GLASS_TUBE.getStackForm(), 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(8).inputs(GAMetaItems.GLASS_TUBE.getStackForm(), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper, 2), new ItemStack(Items.PAPER, 2)).outputs(GAMetaItems.VACUUM_TUBE.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(8).inputs(GAMetaItems.GLASS_TUBE.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2), new ItemStack(Items.PAPER, 2)).outputs(GAMetaItems.VACUUM_TUBE.getStackForm()).buildAndRegister();
            ModHandler.addShapedRecipe("basic_circuit", GAMetaItems.BASIC_CIRCUIT.getStackForm(), "RPR", "VBV", "CCC", 'R', GAMetaItems.RESISTOR.getStackForm(), 'P', OreDictUnifier.get(OrePrefix.plate, Materials.Steel), 'V', GAMetaItems.VACUUM_TUBE.getStackForm(), 'B', GAMetaItems.COATED_BOARD.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.RedAlloy));
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(),GAMetaItems.LOGIC_CIRCUIT.getStackForm(),GAMetaItems.RESISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_BASIC.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(),GAMetaItems.LOGIC_CIRCUIT.getStackForm(),GAMetaItems.SMD_RESISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_BASIC.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.CPU.getStackForm(4),GAMetaItems.RESISTOR.getStackForm(4),GAMetaItems.CAPACITOR.getStackForm(4),GAMetaItems.TRANSISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Copper,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.CPU.getStackForm(4),GAMetaItems.SMD_RESISTOR.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.SMD_TRANSISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Copper,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.SOC.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Copper,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            ModHandler.addShapedRecipe("good_circuit",MetaItems.CIRCUIT_GOOD.getStackForm(),"PCR","CDC","RCP",'P',OreDictUnifier.get(OrePrefix.plate,Materials.Steel),'C',GAMetaItems.BASIC_CIRCUIT.getStackForm(),'R',OreDictUnifier.get(OrePrefix.cableGtSingle,Materials.RedAlloy),'D',GAMetaItems.DIODE.getStackForm());
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(),MetaItems.CIRCUIT_BASIC.getStackForm(3),GAMetaItems.RESISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(),MetaItems.CIRCUIT_BASIC.getStackForm(3),GAMetaItems.SMD_RESISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.CPU.getStackForm(),GAMetaItems.RESISTOR.getStackForm(2),GAMetaItems.CAPACITOR.getStackForm(2),GAMetaItems.TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.CPU.getStackForm(),GAMetaItems.SMD_RESISTOR.getStackForm(2),GAMetaItems.SMD_CAPACITOR.getStackForm(2),GAMetaItems.SMD_TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),GAMetaItems.SOC.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(GAMetaItems.GOOD_CIRCUIT.getStackForm(2),GAMetaItems.LOGIC_CIRCUIT.getStackForm(3),GAMetaItems.RAM.getStackForm(),GAMetaItems.TRANSISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(GAMetaItems.GOOD_CIRCUIT.getStackForm(2),GAMetaItems.LOGIC_CIRCUIT.getStackForm(3),GAMetaItems.RAM.getStackForm(),GAMetaItems.SMD_TRANSISTOR.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),MetaItems.CIRCUIT_ADVANCED.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(80).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),MetaItems.CIRCUIT_ADVANCED.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(),GAMetaItems.NANOCPU.getStackForm(),GAMetaItems.SMD_RESISTOR.getStackForm(2),GAMetaItems.SMD_CAPACITOR.getStackForm(2),GAMetaItems.SMD_TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.NANOPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(),GAMetaItems.SOC.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.NANOPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2),GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm(3),GAMetaItems.DIODE.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_DATA.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2),GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm(3),GAMetaItems.SMD_DIODE.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_DATA.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(),GAMetaItems.NANOPROCESSOR.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.NANO_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(),GAMetaItems.QBIT_CPU.getStackForm(),GAMetaItems.NANOCPU.getStackForm(),GAMetaItems.SMD_CAPACITOR.getStackForm(2),GAMetaItems.SMD_TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.QUANTUMPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(38400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(),GAMetaItems.ASOC.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.QUANTUMPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(OrePrefix.frameGt,Materials.Aluminium),MetaItems.CIRCUIT_DATA.getStackForm(4),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.CAPACITOR.getStackForm(24),GAMetaItems.RAM.getStackForm(16),OreDictUnifier.get(OrePrefix.wireGtSingle,Materials.AnnealedCopper,12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(OrePrefix.frameGt,Materials.Aluminium),MetaItems.CIRCUIT_DATA.getStackForm(4),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(24),GAMetaItems.RAM.getStackForm(16),OreDictUnifier.get(OrePrefix.wireGtSingle,Materials.AnnealedCopper,12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(2),GAMetaItems.NANO_ASSEMBLY.getStackForm(3),GAMetaItems.SMD_DIODE.getStackForm(4),GAMetaItems.NOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Electrum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.NANO_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(),GAMetaItems.QUANTUMPROCESSOR.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_ELITE.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(),GAMetaItems.CRYSTAL_CPU.getStackForm(),GAMetaItems.NANOCPU.getStackForm(),GAMetaItems.SMD_CAPACITOR.getStackForm(2),GAMetaItems.SMD_TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.NiobiumTitanium,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.CRYSTALPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(153600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(),GAMetaItems.CRYSTAL_SOC.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.NiobiumTitanium,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.CRYSTALPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(1920).inputs(OreDictUnifier.get(OrePrefix.frameGt,Materials.Aluminium),GAMetaItems.NANO_COMPUTER.getStackForm(4),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(24),GAMetaItems.RAM.getStackForm(16),OreDictUnifier.get(OrePrefix.wireGtSingle,Materials.AnnealedCopper,12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.NANO_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(2),MetaItems.CIRCUIT_ELITE.getStackForm(3),GAMetaItems.SMD_DIODE.getStackForm(4),GAMetaItems.NOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.QUANTUM_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(),GAMetaItems.CRYSTALPROCESSOR.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.NiobiumTitanium,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_MASTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(38400).inputs(MetaItems.CIRCUIT_BOARD_ELITE.getStackForm(),GAMetaItems.CRYSTAL_CPU.getStackForm(),GAMetaItems.NANOCPU.getStackForm(),GAMetaItems.SMD_CAPACITOR.getStackForm(2),GAMetaItems.SMD_TRANSISTOR.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine,Materials.YttriumBariumCuprate,2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.WETWAREPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(7680).inputs(OreDictUnifier.get(OrePrefix.frameGt,Materials.Aluminium),GAMetaItems.QUANTUM_COMPUTER.getStackForm(4),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(24),GAMetaItems.RAM.getStackForm(16),OreDictUnifier.get(OrePrefix.wireGtSingle,Materials.AnnealedCopper,12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.QUANTUM_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(2),MetaItems.CIRCUIT_MASTER.getStackForm(3),GAMetaItems.SMD_DIODE.getStackForm(4),GAMetaItems.NOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.NiobiumTitanium,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.CRYSTAL_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.WETWARE_BOARD.getStackForm(),GAMetaItems.WETWAREPROCESSOR.getStackForm(2),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.YttriumBariumCuprate,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.WETWARE_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(30720).inputs(OreDictUnifier.get(OrePrefix.frameGt,Materials.Aluminium),GAMetaItems.CRYSTAL_COMPUTER.getStackForm(4),GAMetaItems.SMALL_COIL.getStackForm(4),GAMetaItems.SMD_CAPACITOR.getStackForm(24),GAMetaItems.RAM.getStackForm(16),OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor,12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.CRYSTAL_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.WETWARE_BOARD.getStackForm(2),GAMetaItems.WETWARE_ASSEMBLY.getStackForm(3),GAMetaItems.SMD_DIODE.getStackForm(4),GAMetaItems.NOR.getStackForm(4),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.YttriumBariumCuprate,6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.WETWARE_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(512).EUt(1024).inputs(GAMetaItems.FIBER_BOARD.getStackForm(),GAMetaItems.PIC.getStackForm(4),MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_MASTER.getStackForm(18),GAMetaItems.NANOCPU.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,16)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1024).EUt(4096).inputs(GAMetaItems.FIBER_BOARD.getStackForm(),GAMetaItems.HPIC.getStackForm(4),MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8),GAMetaItems.QBIT_CPU.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,16),OreDictUnifier.get(OrePrefix.plate,Materials.Europium,4)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(),MetaItems.CIRCUIT_ADVANCED.getStackForm(),GAMetaItems.NAND.getStackForm(32),GAMetaItems.RAM.getStackForm(4),OreDictUnifier.get(OrePrefix.wireFine,Materials.RedAlloy,8),OreDictUnifier.get(OrePrefix.plate,Materials.Plastic,4)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_STICK.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(1200).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(),GAMetaItems.NANOPROCESSOR.getStackForm(),GAMetaItems.RAM.getStackForm(4),GAMetaItems.NOR.getStackForm(32),GAMetaItems.NAND.getStackForm(64),OreDictUnifier.get(OrePrefix.wireFine,Materials.Platinum,32)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_ORB.getStackForm()).buildAndRegister();
        }
        //Circuit Rabbit Hole - Layer 2
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(160).EUt(8).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Glass)).notConsumable(MetaItems.SHAPE_MOLD_BALL).outputs(GAMetaItems.GLASS_TUBE.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("resistor_1", GAMetaItems.RESISTOR.getStackForm(3), " P ", "WCW", " P ", 'P', new ItemStack(Items.PAPER), 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper), 'C', OreDictUnifier.get(OrePrefix.dust, Materials.Coal));
        ModHandler.addShapedRecipe("resistor_2", GAMetaItems.RESISTOR.getStackForm(3), " P ", "WCW", " P ", 'P', new ItemStack(Items.PAPER), 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'C', OreDictUnifier.get(OrePrefix.dust, Materials.Coal));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(6).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Coal), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 4)).outputs(GAMetaItems.RESISTOR.getStackForm(12)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Plastic), OreDictUnifier.get(OrePrefix.foil, Materials.Aluminium, 2)).outputs(GAMetaItems.CAPACITOR.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(24).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Silicon), OreDictUnifier.get(OrePrefix.foil, Materials.Tin, 6)).fluidInputs(Materials.Plastic.getFluid(144)).outputs(GAMetaItems.TRANSISTOR.getStackForm(8)).buildAndRegister();
        ModHandler.addShapedRecipe("diode_1", GAMetaItems.DIODE.getStackForm(), "DG ", "TWT", "DG ", 'D', OreDictUnifier.get(OrePrefix.dye, Color.Black), 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Tin), 'W', GAMetaItems.SILICON_WAFER.getStackForm());
        ModHandler.addShapedRecipe("diode_2", GAMetaItems.DIODE.getStackForm(), "DG ", "TWT", "DG ", 'D', OreDictUnifier.get(OrePrefix.dye, Color.Black), 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireFine, Materials.Tin), 'W', GAMetaItems.SILICON_WAFER.getStackForm());
        ModHandler.addShapedRecipe("diode_3", GAMetaItems.DIODE.getStackForm(4), "DG ", "TWT", "DG ", 'D', OreDictUnifier.get(OrePrefix.dye, Color.Black), 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Tin), 'W', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gallium));
        ModHandler.addShapedRecipe("diode_4", GAMetaItems.DIODE.getStackForm(4), "DG ", "TWT", "DG ", 'D', OreDictUnifier.get(OrePrefix.dye, Color.Black), 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireFine, Materials.Tin), 'W', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gallium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(48).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 4), OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gallium)).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.DIODE.getStackForm(16)).buildAndRegister();
        ModHandler.addShapedRecipe("small_coil_1", GAMetaItems.SMALL_COIL.getStackForm(2), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'B', OreDictUnifier.get(OrePrefix.bolt, Materials.Steel));
        ModHandler.addShapedRecipe("small_coil_2", GAMetaItems.SMALL_COIL.getStackForm(2), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), 'B', OreDictUnifier.get(OrePrefix.bolt, Materials.Steel));
        ModHandler.addShapedRecipe("small_coil_3", GAMetaItems.SMALL_COIL.getStackForm(4), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'B', OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite));
        ModHandler.addShapedRecipe("small_coil_4", GAMetaItems.SMALL_COIL.getStackForm(4), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), 'B', OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), OreDictUnifier.get(OrePrefix.bolt, Materials.Steel)).outputs(GAMetaItems.SMALL_COIL.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), OreDictUnifier.get(OrePrefix.bolt, Materials.Steel)).outputs(GAMetaItems.SMALL_COIL.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite)).outputs(GAMetaItems.SMALL_COIL.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite)).outputs(GAMetaItems.SMALL_COIL.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 4)).fluidInputs(Materials.Plastic.getFluid(144)).outputs(GAMetaItems.SMD_RESISTOR.getStackForm(24)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120).inputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gallium), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 4)).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.SMD_DIODE.getStackForm(32)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Gallium), OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 6)).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.SMD_TRANSISTOR.getStackForm(32)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(120).inputs(OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyvinylChloride, 4), OreDictUnifier.get(OrePrefix.foil, Materials.Aluminium)).fluidInputs(Materials.Plastic.getFluid(36)).outputs(GAMetaItems.SMD_CAPACITOR.getStackForm(16));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120).inputs(OreDictUnifier.get(OrePrefix.foil, GAMaterials.SiliconeRubber, 4), OreDictUnifier.get(OrePrefix.foil, Materials.Aluminium)).fluidInputs(Materials.Plastic.getFluid(36)).outputs(GAMetaItems.SMD_CAPACITOR.getStackForm(16));
        ModHandler.addShapedRecipe("coated_board", GAMetaItems.COATED_BOARD.getStackForm(3), " R ", "PPP", " R ", 'R', MetaItems.RUBBER_DROP.getStackForm(), 'P', OreDictUnifier.get(OrePrefix.plate, Materials.Wood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(8).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Wood, 8), MetaItems.RUBBER_DROP.getStackForm()).fluidInputs(Materials.Glue.getFluid(100)).outputs(GAMetaItems.COATED_BOARD.getStackForm(8)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(30).EUt(8).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Wood)).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(Materials.Glue.getFluid(100)).outputs(GAMetaItems.PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(30).EUt(8).inputs(OreDictUnifier.get(OrePrefix.dust, Materials.Wood)).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(GAMaterials.BisphenolA.getFluid(100)).outputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Plastic), OreDictUnifier.get(OrePrefix.foil, Materials.Copper)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(OreDictUnifier.get(OrePrefix.plate, GAMaterials.PolyvinylChloride), OreDictUnifier.get(OrePrefix.foil, Materials.Copper)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Polytetrafluoroethylene), OreDictUnifier.get(OrePrefix.foil, Materials.Copper)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(10).inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Epoxid), OreDictUnifier.get(OrePrefix.foil, Materials.Copper)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.EPOXY_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(10).inputs(OreDictUnifier.get(OrePrefix.plate, GAMaterials.ReinforcedEpoxyResin), OreDictUnifier.get(OrePrefix.foil, Materials.Copper)).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.FIBER_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 16)).fluidInputs(Materials.SulfuricAcid.getFluid(250)).outputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), GAMetaItems.PETRI_DISH.getStackForm(), GAMetaItems.GOOD_CIRCUIT.getStackForm()).fluidInputs(GAMaterials.SterilizedGrowthMedium.getFluid(250)).outputs(GAMetaItems.WETWARE_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(40000).inputs(GAMetaItems.CRYSTAL_CPU.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens, Color.Blue)).outputs(GAMetaItems.CRYSTAL_SOC.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(40000).inputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens, Color.Lime)).outputs(GAMetaItems.CRYSTAL_CPU.getStackForm()).buildAndRegister();

        //Cutting Machine Recipes
        for (MaterialStack stack : sawLubricants) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.ASOC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.ASOC.getStackForm(6)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.SOC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.SOC.getStackForm(6)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.RAM_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.RAM.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.QBIT_CPU.getStackForm(5)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.PIC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.PIC.getStackForm(4)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.HPIC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.HPIC.getStackForm(2)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.NOR_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.NOR.getStackForm(16)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.NAND_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.NAND.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.CPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.CPU.getStackForm(8)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm()).fluidInputs(material.getFluid(22)).outputs(GAMetaItems.LOGIC_CIRCUIT.getStackForm(8)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600).EUt(48).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22)).outputs(GAMetaItems.NANOCPU.getStackForm(7)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.SILICON_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.SILICON_WAFER.getStackForm(16)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(400).EUt(64).inputs(GAMetaItems.GLOWSTONE_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(800).EUt(384).inputs(GAMetaItems.NAQUADAH_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.NAQUADAH_WAFER.getStackForm(64)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Air.getFluid(7000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Oxygen.getFluid(7000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(4320)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(600).EUt(120).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.FerriteMixture)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.ingot,GAMaterials.NickelZincFerrite)).blastFurnaceTemp(1500).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Air.getFluid(7000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Oxygen.getFluid(7000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Polydimethylsiloxane,9),OreDictUnifier.get(OrePrefix.dust,Materials.Sulfur)).fluidOutputs(GAMaterials.SiliconeRubber.getFluid(1296)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Silicon)).fluidInputs(Materials.Epichlorhydrin.getFluid(144)).fluidOutputs(GAMaterials.SiliconeRubber.getFluid(144)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(8).fluidInputs(GAMaterials.PolyvinylAcetate.getFluid(1000),GAMaterials.Acetone.getFluid(1500)).fluidOutputs(Materials.Glue.getFluid(2500)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(8).fluidInputs(GAMaterials.PolyvinylAcetate.getFluid(1000),GAMaterials.MethylAcetate.getFluid(1500)).fluidOutputs(Materials.Glue.getFluid(2500)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(5).inputs(MetaItems.RUBBER_DROP.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dust,Materials.RawRubber,3)).chancedOutput(MetaItems.PLANT_BALL.getStackForm(),1000).fluidOutputs(Materials.Glue.getFluid(100)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.HydrochloricAcid.getFluid(1000),GAMaterials.Acetone.getFluid(1000),GAMaterials.Phenol.getFluid(2)).fluidInputs(GAMaterials.BisphenolA.getFluid(1000),Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(8).fluidInputs(GAMaterials.SulfurTrioxide.getFluid(100),Materials.Water.getFluid(1000)).fluidOutputs(Materials.SulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(30).fluidInputs(GAMaterials.NickelSulfateSolution.getFluid(9000)).outputs(OreDictUnifier.get(OrePrefix.dust,Materials.Nickel)).fluidOutputs(Materials.Oxygen.getFluid(1000),Materials.SulfuricAcid.getFluid(8000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(30).fluidInputs(GAMaterials.BlueVitriolSolution.getFluid(9000)).outputs(OreDictUnifier.get(OrePrefix.dust,Materials.Copper)).fluidOutputs(Materials.Oxygen.getFluid(1000),Materials.SulfuricAcid.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Air.getFluid(7000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Oxygen.getFluid(7000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidInputs(Materials.Epichlorhydrin.getFluid(1000),GAMaterials.BisphenolA.getFluid(1000)).fluidOutputs(Materials.Epoxid.getFluid(1000),GAMaterials.SaltWater.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(Materials.Naphtha.getFluid(3000),Materials.NitrogenDioxide.getFluid(1000),Materials.Epichlorhydrin.getFluid(144)).fluidOutputs(Materials.Epoxid.getFluid(288)).buildAndRegister();
        ModHandler.addShapelessRecipe("reinforce_expoy_resin_1",OreDictUnifier.get(OrePrefix.dust,GAMaterials.ReinforcedEpoxyResin),OreDictUnifier.get(OrePrefix.dust,Materials.Epoxid),GAMetaItems.GLASS_FIBER.getStackForm());
        ModHandler.addShapelessRecipe("reinforce_expoy_resin_2",OreDictUnifier.get(OrePrefix.dust,GAMaterials.ReinforcedEpoxyResin),OreDictUnifier.get(OrePrefix.dust,Materials.Epoxid),GAMetaItems.RAW_CARBON_FIBERS.getStackForm());
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(16).inputs(GAMetaItems.GLASS_FIBER.getStackForm()).fluidInputs(Materials.Epoxid.getFluid(144)).outputs(OreDictUnifier.get(OrePrefix.plate,GAMaterials.ReinforcedEpoxyResin)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(16).inputs(GAMetaItems.RAW_CARBON_FIBERS.getStackForm()).fluidInputs(Materials.Epoxid.getFluid(144)).outputs(OreDictUnifier.get(OrePrefix.plate,GAMaterials.ReinforcedEpoxyResin)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(GAMaterials.Polystyrene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Materials.Polytetrafluoroethylene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(),OreDictUnifier.get(OrePrefix.plate,Materials.Emerald)).fluidInputs(Materials.Helium.getFluid(1000)).outputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(),OreDictUnifier.get(OrePrefix.plate,Materials.Olivine)).fluidInputs(Materials.Helium.getFluid(1000)).outputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(20).inputs(OreDictUnifier.get(OrePrefix.log,Materials.Wood)).chancedOutput(MetaItems.RUBBER_DROP.getStackForm(),5000).chancedOutput(MetaItems.PLANT_BALL.getStackForm(),3750).chancedOutput(OreDictUnifier.get(OrePrefix.dust,Materials.Carbon),2500).chancedOutput(OreDictUnifier.get(OrePrefix.dust,Materials.Wood),2500).fluidOutputs(Materials.Methane.getFluid(60)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Red)).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Red)).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Red)).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Materials.GreenSapphire)).outputs(GAMetaItems.RAM_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Materials.GreenSapphire)).outputs(GAMetaItems.RAM_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Materials.GreenSapphire)).outputs(GAMetaItems.RAM_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.EnderPearl)).outputs(GAMetaItems.NAND_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.EnderPearl)).outputs(GAMetaItems.NAND_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.EnderEye)).outputs(GAMetaItems.NOR_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.EnderEye)).outputs(GAMetaItems.NOR_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.Glass)).outputs(GAMetaItems.CPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.Glass)).outputs(GAMetaItems.CPU_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.Glass)).outputs(GAMetaItems.CPU_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens,Materials.GarnetYellow)).outputs(GAMetaItems.SOC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Orange)).outputs(GAMetaItems.ASOC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Blue)).outputs(GAMetaItems.PIC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.craftingLens,Color.Blue)).outputs(GAMetaItems.PIC_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(1920).inputs(GAMetaItems.PIC_WAFER.getStackForm(),OreDictUnifier.get(OrePrefix.dust,GAMaterials.IndiumGalliumPhosphide,2)).fluidInputs(Materials.RedAlloy.getFluid(288)).outputs(GAMetaItems.HPIC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(GAMetaItems.CPU_WAFER.getStackForm(),GAMetaItems.RAW_CARBON_FIBERS.getStackForm(16)).fluidInputs(Materials.Glowstone.getFluid(576)).outputs(GAMetaItems.NANOCPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm(),MetaItems.QUANTUM_EYE.getStackForm(2)).fluidInputs(GAMaterials.GalliumArsenide.getFluid(288)).outputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm(),OreDictUnifier.get(OrePrefix.dust,GAMaterials.IndiumGalliumPhosphide)).fluidInputs(Materials.Radon.getFluid(50)).outputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Silicon,32),OreDictUnifier.get(OrePrefix.dustTiny,Materials.Gallium)).outputs(GAMetaItems.SILICON_BOULE.getStackForm()).blastFurnaceTemp(1784).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(12000).EUt(480).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Silicon,64),OreDictUnifier.get(OrePrefix.dust,Materials.Glowstone,8)).fluidInputs(Materials.Nitrogen.getFluid(8000)).outputs(GAMetaItems.GLOWSTONE_BOULE.getStackForm()).blastFurnaceTemp(2484).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(1500).EUt(1920).inputs(OreDictUnifier.get(OrePrefix.block,Materials.Silicon,15),OreDictUnifier.get(OrePrefix.ingot,Materials.Naquadah)).fluidInputs(Materials.Argon.getFluid(8000)).outputs(GAMetaItems.NAQUADAH_BOULE.getStackForm()).blastFurnaceTemp(5400).buildAndRegister();
        ModHandler.addShapelessRecipe("ferrite_mixture",OreDictUnifier.get(OrePrefix.dust,GAMaterials.FerriteMixture,6),OreDictUnifier.get(OrePrefix.dust,Materials.Nickel),OreDictUnifier.get(OrePrefix.dust,Materials.Zinc),OreDictUnifier.get(OrePrefix.dust,Materials.Iron,4));
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).fluidInputs(GAMaterials.Dimethyldichlorosilane.getFluid(1000),Materials.Water.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Polydimethylsiloxane,3)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Silicon)).fluidInputs(GAMaterials.HydrochloricAcid.getFluid(2000),GAMaterials.Methanol.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Polydimethylsiloxane,3)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Silicon)).fluidInputs(Materials.Water.getFluid(1000),Materials.Chlorine.getFluid(4000),Materials.Methane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Polydimethylsiloxane,3)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(2000),GAMaterials.DilutedHydrochloricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).fluidInputs(Materials.Chlorine.getFluid(1000),Materials.Hydrogen.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Salt,2)).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumBisulfate)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(IntCircuitIngredient.getIntegratedCircuit(1)).fluidInputs(Materials.Chlorine.getFluid(6000),Materials.Methane.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(3000),GAMaterials.Chloroform.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(IntCircuitIngredient.getIntegratedCircuit(2)).fluidInputs(Materials.Chlorine.getFluid(2000),Materials.Methane.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000),GAMaterials.Chloromethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Chlorine.getFluid(2000),GAMaterials.Benzene.getFluid(500)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000),GAMaterials.Dichlorobenzene.getFluid(500)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.Propene.getFluid(1000),Materials.Chlorine.getFluid(2000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000),GAMaterials.AllylChloride.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Chlorine.getFluid(2000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.VinylChloride.getFluid(1000),GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).fluidInputs(GAMaterials.SulfurDioxide.getFluid(1000),Materials.Oxygen.getFluid(1000)).fluidOutputs(GAMaterials.SulfurTrioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Sulfur)).fluidInputs(Materials.Oxygen.getFluid(3000)).fluidOutputs(GAMaterials.SulfurTrioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(256).fluidInputs(GAMaterials.Chloroform.getFluid(1000),GAMaterials.HydrofluoricAcid.getFluid(2000)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(3000),GAMaterials.Tetrafluoroethylene.getFluid(500)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(720).EUt(30).fluidInputs(GAMaterials.SaltWater.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidOutputs(Materials.Chlorine.getFluid(1000),Materials.Hydrogen.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidInputs(Materials.Water.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidOutputs(Materials.Hydrogen.getFluid(1000)).buildAndRegister();
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().duration(160).EUt(96).inputs(OreDictUnifier.get(OrePrefix.ingot,GAMaterials.BorosilicateGlass)).notConsumable(MetaItems.SHAPE_EXTRUDER_WIRE.getStackForm()).outputs(GAMetaItems.GLASS_FIBER.getStackForm(8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Styrine.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Styrine.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Air.getFluid(7000), GAMaterials.Styrine.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).fluidInputs(Materials.Oxygen.getFluid(7000), GAMaterials.Styrine.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(4320)).buildAndRegister();
        ModHandler.addShapelessRecipe("infinite_raw_crystal_chips",GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(9),GAMetaItems.CRYSTAL_CPU.getStackForm());
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(OrePrefix.gemExquisite,Materials.Emerald)).fluidInputs(Materials.Europium.getFluid(16)).chancedOutput(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(),1000).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Carbon)).fluidInputs(Materials.Palladium.getFluid(1)).chancedOutput(GAMetaItems.RAW_CARBON_FIBERS.getStackForm(2),9000).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Carbon)).fluidInputs(Materials.Lutetium.getFluid(1)).chancedOutput(GAMetaItems.RAW_CARBON_FIBERS.getStackForm(2),3333).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR,4),OreDictUnifier.get(OrePrefix.dust,GAMaterials.Meat),OreDictUnifier.get(OrePrefix.dustTiny,Materials.Salt)).fluidInputs(Materials.DistilledWater.getFluid(4000)).fluidOutputs(GAMaterials.RawGrowthMedium.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000),GAMaterials.AceticAcid.getFluid(1000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000),GAMaterials.VinylAcetate.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000),GAMaterials.HydrochloricAcid.getFluid(1000),GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000),GAMaterials.VinylChloride.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000),GAMaterials.Cumene.getFluid(1000)).fluidOutputs(GAMaterials.Phenol.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Wood,4)).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(new ItemStack(Items.COAL,1,1)).fluidOutputs(GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).inputs(new ItemStack(Items.SUGAR,4)).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(new ItemStack(Items.COAL,1,1)).fluidOutputs(GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30).fluidInputs(Materials.NitrationMixture.getFluid(3000),GAMaterials.Glycerol.getFluid(1000)).fluidOutputs(Materials.Glyceryl.getFluid(1000),GAMaterials.DilutedSulfuricAcid.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000),GAMaterials.AceticAcid.getFluid(1000)).fluidOutputs(GAMaterials.Ethenone.getFluid(1000),GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000),Materials.Ethanol.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(1000),GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Calcite)).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000),Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.Quicklime)).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000),Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Calcium)).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000),Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(GAMaterials.Methanol.getFluid(1000),GAMaterials.AceticAcid.getFluid(1000)).fluidOutputs(GAMaterials.MethylAcetate.getFluid(1000),Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).fluidInputs(GAMaterials.Glycerol.getFluid(1000),GAMaterials.HydrochloricAcid.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(2000),Materials.Epichlorhydrin.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidInputs(GAMaterials.AllylChloride.getFluid(1000),GAMaterials.HypochlorousAcid.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000),Materials.Epichlorhydrin.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidInputs(Materials.Water.getFluid(1000),Materials.Chlorine.getFluid(4000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000),Materials.Epichlorhydrin.getFluid(1000),GAMaterials.HydrochloricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust,GAMaterials.SodiumHydroxide)).fluidInputs(GAMaterials.HypochlorousAcid.getFluid(1000),Materials.Chlorine.getFluid(2000),GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000),Materials.Epichlorhydrin.getFluid(1000),GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).inputs(OreDictUnifier.get(OrePrefix.dust,Materials.Sulfur)).fluidInputs(Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricLightFuel.getFluid(12000),Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000),Materials.LightFuel.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricHeavyFuel.getFluid(12000),Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000),Materials.HeavyFuel.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricNaphtha.getFluid(12000),Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000),Materials.Naphtha.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricGas.getFluid(12000),Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000),Materials.Gas.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.NaturalGas.getFluid(12000),Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000),Materials.Gas.getFluid(12000)).buildAndRegister();
    }
}
