package com.awakenedChest.awakenedchest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class AwakenedChestGUI extends GuiContainer {

    //These are here to be accessed by the container
    public static int SIZE_X,SIZE_Y;

    public AwakenedChestGUI(IInventory playerinv, AwakenedChestTileEntity tileEntity) {
        super(new AwakenedChestContainer(playerinv,tileEntity));



        //Set dimensions before these
        SIZE_X = xSize; SIZE_Y = ySize;
        //---------------------------

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}//class AwakenedChestGUI
