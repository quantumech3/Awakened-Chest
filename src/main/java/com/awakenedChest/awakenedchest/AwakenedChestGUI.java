package com.awakenedChest.awakenedchest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.Sys;

public class AwakenedChestGUI extends GuiContainer {

    IInventory playerInventory;
    AwakenedChestTileEntity awakenedChestTileEntity;

    //add white background to gui
    //fixme make gui not be weird the first time you open it

    //These are here to be accessed by the container
    public static int SIZE_X,SIZE_Y;

    public AwakenedChestGUI(IInventory playerinv, AwakenedChestTileEntity tileEntity) {
        super(new AwakenedChestContainer(playerinv,tileEntity));

        playerInventory = playerinv;
        awakenedChestTileEntity = tileEntity;

        //Set dimensions before these
        SIZE_X = xSize; SIZE_Y = ySize;
        //---------------------------

    }//Constructor

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {



    }



}//class AwakenedChestGUI
