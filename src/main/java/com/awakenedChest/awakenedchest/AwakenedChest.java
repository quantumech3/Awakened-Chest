package com.awakenedChest.awakenedchest;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.Sys;

@Mod(modid = AwakenedChest.MODID, version = AwakenedChest.VERSION,useMetadata = true)
public class AwakenedChest
{
    public static final String MODID = "awakenedchest";
    public static final String VERSION = "1.0";

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event){



    }//void PreInit: Runs before the game finishes launching

    @EventHandler
    public void Init(FMLInitializationEvent event){

        

    }//void init: Runs at the beginning of the game launch
}
