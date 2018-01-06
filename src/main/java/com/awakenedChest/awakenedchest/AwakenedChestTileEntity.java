package com.awakenedChest.awakenedchest;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class AwakenedChestTileEntity extends TileEntity implements ICapabilityProvider {

    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    IItemHandler itemHandler = new ItemStackHandler(6);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return true;

        }//If capability is item handler

        return false;

    }//boolean hasCapabilty()

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return (T)itemHandler;

        }//If capability is item handler

            return super.getCapability(capability,facing);

    }//getCapability()
}
