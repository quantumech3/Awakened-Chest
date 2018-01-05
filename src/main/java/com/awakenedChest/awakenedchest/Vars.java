package com.awakenedChest.awakenedchest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Vars {
    static CreativeTabs awakenedChestTab = new CreativeTabs("awakenedChestTab") {
        @Override
        public ItemStack getTabIconItem() {

            //add a texture to the creative tab
            return null;
        }
    };

    //Constants----------------------------
    static String AwakenedChest_REGNAME = "awakenedChest";
    static String PLAYER_NAME = Minecraft.getMinecraft().player.getName();
    //-------------------------------------

    //Registries---------------------------
    static IForgeRegistry<Block> blockRegistry = GameRegistry.findRegistry(Block.class);
    static IForgeRegistry<Item> itemRegistry = GameRegistry.findRegistry(Item.class);
    //-------------------------------------

    //Blocks-------------------------------
    static AwakenedChestBlock awakenedChestBlock = new AwakenedChestBlock(Material.IRON);
    //-------------------------------------





}//Variable Declaration
