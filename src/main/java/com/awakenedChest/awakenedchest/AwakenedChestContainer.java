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

    int amountOfUpgradeSlots = 3;
    int amountOfContainerSlots = 3;
    IItemHandler inventory;
    AwakenedChestTileEntity entity;

    public int page = 0;

    final int MAX_X = AwakenedChestGUI.SIZE_X;
    final int MAX_Y = AwakenedChestGUI.SIZE_Y;
    final int SLOT_WIDTH = 16;
    final int INVENTORY_WIDTH = 9;
    final int GAP_BETWEEN_UPGRADEANDCONTAINER_SLOTS = SLOT_WIDTH*2;
    final int SLOTS_ON_PAGE = 100;

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

        for (int i = 9; i < 36; i++) {

            addSlotToContainer(
                    new PlayerSlot(
                            playerInv,
                            i,

                            (i % 9) * SLOT_WIDTH,
                            (((int) Math.floor(i / 9)) * SLOT_WIDTH) + MAX_Y - (SLOT_WIDTH * 5) + (SLOT_WIDTH * 5)
                    )
            );

        }//For: make inventory slots

    }

    public void RenderPlayerBar(IInventory playerInv){

        for (int i = 0; i < 9; i++) {

            addSlotToContainer(
                    new PlayerSlot(
                            playerInv,
                            i,

                            (i % 9) * SLOT_WIDTH,
                            (((int) Math.floor(i / 9)) * SLOT_WIDTH) + MAX_Y + (SLOT_WIDTH * 5)
                    )
            );

        }//For: make inventory slots

    }//RenderPlayerBar()

    public void RenderStorageInventory(AwakenedChestTileEntity tileEntity) {
        if (tileEntity.amountOfInventorySlots > 0) {

            for (int i = 0; i < tileEntity.amountOfInventorySlots - amountOfContainerSlots - amountOfUpgradeSlots; i++) {

                addSlotToContainer(
                        new InventorySlot(
                                inventory,

                                //limit the container slots that are being displayed to the ones in the chest
                                //fixme -3 might not be nessasary
                                Math.min(amountOfUpgradeSlots + amountOfContainerSlots + (page*SLOTS_ON_PAGE) + i,
                                        amountOfUpgradeSlots + amountOfContainerSlots + tileEntity.amountOfInventorySlots-3),

                                (i % INVENTORY_WIDTH) * SLOT_WIDTH,
                                ((int) Math.floor(i / INVENTORY_WIDTH)) * SLOT_WIDTH - (SLOT_WIDTH)
                        )
                );

            }//For: make inventory slots
        }
    }

    public AwakenedChestContainer(IInventory playerInv, AwakenedChestTileEntity tileEntity, int _page) {

        page = _page;

        amountOfUpgradeSlots = tileEntity.amountOfUpgradeSlots;
        amountOfContainerSlots = tileEntity.amountOfContainerSlots;
        inventory = tileEntity.inventory;
        entity = tileEntity;

        RenderPlayerInventory(playerInv);
        RenderPlayerBar(playerInv);

        RenderUpgradeSlots();

        RenderContainerSlots();

        RenderStorageInventory(entity);


    }//Constructor

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        //add shiftclick inventory capabilitys
        return ItemStack.EMPTY;

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {return true;}



}//class AwakenedChestContainer
