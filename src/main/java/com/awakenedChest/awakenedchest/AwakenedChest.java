package com.awakenedChest.awakenedchest;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.Sys;


@Mod(modid = AwakenedChest.MODID, version = AwakenedChest.VERSION,useMetadata = true)
public class AwakenedChest
{
    public static final String MODID = "awakenedchest";
    public static final String VERSION = "1.0";

    //Variable Declaration
    AwakenedChestBlock awakenedChestBlock = new AwakenedChestBlock(Material.IRON);
    //--------------------

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event){

        awakenedChestBlock.setCreativeTab(Vars.awakenedChestTab);

        Vars.blockRegistry.register(awakenedChestBlock);
        Vars.itemRegistry.register(new AwakenedItemBlock(awakenedChestBlock,null, null));

    }//void PreInit: Use for registration

    @EventHandler
    public void Init(FMLInitializationEvent event){



    }//void init: Runs at the beginning of the game launch
}
