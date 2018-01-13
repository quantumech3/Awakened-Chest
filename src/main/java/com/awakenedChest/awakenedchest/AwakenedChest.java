package com.awakenedChest.awakenedchest;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.ChestRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.Sys;

import java.awt.*;


@Mod(modid = AwakenedChest.MODID, version = AwakenedChest.VERSION,useMetadata = true)
public class AwakenedChest
{
    public static final String MODID = "awakenedchest";
    public static final String VERSION = "1.0";

    //Variable Declaration
    AwakenedChestBlock awakenedChestBlock = new AwakenedChestBlock(Material.IRON);
    public static TextureAtlasSprite upgradeSlotTexture,containerSlotTexture,inventorySlotTexture;
    //--------------------

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event){


        //Set inital blocks creative tab, iterations with inventory dont have a creative tab set
        awakenedChestBlock.setCreativeTab(Vars.awakenedChestTab);

        //Registering
        NetworkRegistry.INSTANCE.registerGuiHandler(this.MODID,new AwakenedChestGuiHandler());

        GameRegistry.registerTileEntity(AwakenedChestTileEntity.class,"awakenedChestEntity");
        Vars.blockRegistry.register(awakenedChestBlock);
        Vars.itemRegistry.register(new AwakenedItemBlock(awakenedChestBlock,null, null));


        ClientRegistry.bindTileEntitySpecialRenderer(AwakenedChestTileEntity.class,new AwakenedChestRenderer());
        //-----------

            MinecraftForge.EVENT_BUS.register(new EventHandler());

    }//void PreInit: Use for registration

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){



    }//void init: Runs at the beginning of the game launch


    public class EventHandler{

        @SubscribeEvent
        public void OnStartup(TextureStitchEvent.Pre event){

            upgradeSlotTexture = event.getMap().registerSprite(new ResourceLocation(MODID+":upgradeslot"));
            containerSlotTexture = event.getMap().registerSprite(new ResourceLocation(MODID+":containerslot"));
            inventorySlotTexture = event.getMap().registerSprite(new ResourceLocation(MODID+":inventoryslot"));
        }//void RegisterSlotTextures(): Set Slot textures

    }//class Events
}
