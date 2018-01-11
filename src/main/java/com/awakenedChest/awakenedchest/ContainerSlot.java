package com.awakenedChest.awakenedchest;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerSlot extends SlotItemHandler{

    public ContainerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

    }//Constructor




    @Override public int getSlotStackLimit() {return 1;}

    @Override public int getItemStackLimit(@Nonnull ItemStack stack) {return 1;}

}//class UpgradeSlot
