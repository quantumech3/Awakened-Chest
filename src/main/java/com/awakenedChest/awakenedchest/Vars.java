package com.awakenedChest.awakenedchest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Vars {
    static CreativeTabs awakenedChestTab = new CreativeTabs("awakenedChestTab") {
        @Override
        public ItemStack getTabIconItem() {

            //replace texture to the creative tab
            return new ItemStack(Items.DIAMOND);

        }//ItemStack getTabIconItem(): sets the tab icon

        public String getTranslatedTabLabel(){ return "Awakened Chests"; }

    };

    //Constants----------------------------
    static String AwakenedChest_REGNAME = "awakenedChest";
    //-------------------------------------

    //Registries---------------------------
    static IForgeRegistry<Block> blockRegistry = GameRegistry.findRegistry(Block.class);
    static IForgeRegistry<Item> itemRegistry = GameRegistry.findRegistry(Item.class);
    //-------------------------------------

}//Variable Declaration
