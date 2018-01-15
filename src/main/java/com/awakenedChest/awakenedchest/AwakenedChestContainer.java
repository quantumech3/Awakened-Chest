package com.awakenedChest.awakenedchest;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.Sys;

public class AwakenedChestContainer extends Container{

    int page = 0;
    int amountOfUpgradeSlots = 3;
    int amountOfContainerSlots = 3;
    IItemHandler inventory;
    AwakenedChestTileEntity entity;

    final int MAX_X = AwakenedChestGUI.SIZE_X;
    final int MAX_Y = AwakenedChestGUI.SIZE_Y;
    final int SLOT_WIDTH = 16;
    final int INVENTORY_WIDTH = 20;
    final int GAP_BETWEEN_UPGRADEANDCONTAINER_SLOTS = SLOT_WIDTH*2;

    public void RenderContainerSlots(){
        //Container slots are the next 3 slots
        for(int i = amountOfUpgradeSlots; i < amountOfUpgradeSlots+amountOfContainerSlots; i++){

            addSlotToContainer(new ContainerSlot(inventory,i,MAX_X, -GAP_BETWEEN_UPGRADEANDCONTAINER_SLOTS +MAX_Y-(SLOT_WIDTH*i)));

        }//For: Make container slots
    }//RenderContainerSlots()

    public void RenderUpgradeSlots(){
        //Upgrade slots are the first 3 slots
        for(int i = 0;i<amountOfUpgradeSlots;i++){

            addSlotToContainer(new UpgradeSlot(inventory,i,MAX_X,MAX_Y-(SLOT_WIDTH*i)));

        }//For: make upgrade slots
    }//RenderUpgradeSlots()

    public void RenderPlayerInventory(IInventory playerInv){

        for (int i = 0; i < 36; i++) {

            addSlotToContainer(
                    new PlayerSlot(
                            playerInv,
                            i,

                            (i % 9) * SLOT_WIDTH,
                            (((int) Math.floor(i / 9)) * SLOT_WIDTH) + MAX_Y
                    )
            );

        }//For: make inventory slots

    }

    public AwakenedChestContainer(IInventory playerInv, AwakenedChestTileEntity tileEntity) {

        inventory = tileEntity.inventory;
        entity = tileEntity;

        RenderUpgradeSlots();

        RenderContainerSlots();

        if(tileEntity.amountOfInventorySlots > 0) {

            for (int i = 0; i < tileEntity.amountOfInventorySlots-amountOfContainerSlots-amountOfUpgradeSlots; i++) {

                    addSlotToContainer(
                            new InventorySlot(
                                    inventory,
                                    amountOfUpgradeSlots + amountOfContainerSlots + i,

                                    (i % INVENTORY_WIDTH) * SLOT_WIDTH,
                                    ((int) Math.floor(i / INVENTORY_WIDTH)) * SLOT_WIDTH
                            )
                    );

            }//For: make inventory slots

        }//Make sure that inventory slots exist at all

        RenderPlayerInventory(playerInv);

    }//Constructor

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        //add shiftclick inventory capabilitys
        return ItemStack.EMPTY;

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {return true;}

}//class AwakenedChestContainer
