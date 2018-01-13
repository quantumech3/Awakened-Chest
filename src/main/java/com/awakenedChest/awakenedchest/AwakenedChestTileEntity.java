package com.awakenedChest.awakenedchest;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AwakenedChestTileEntity extends TileEntity implements ICapabilityProvider {


    int amountOfUpgradeSlots = 3;
    int amountOfContainerSlots = 3;
    BlockPos playerPos;
    float rotationYaw;
    int rotationAngle = 999;
    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    ItemStackHandler inventory = new ItemStackHandler(amountOfContainerSlots+amountOfUpgradeSlots){

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }//Stack size
    };

    public ItemStack[] GetFromUpgradeSlots(){

        ItemStack[] output = new ItemStack[amountOfUpgradeSlots];

        for(int i = 0; i<amountOfUpgradeSlots; i++){

            output[i] = inventory.getStackInSlot(i);

        }//append all items from upgrade slots into output

        return output;
    }//GetFromUpgradeSlots(): This gets all items from upgrade slots

    public ItemStack[] GetFromContainerSlots(){

        ItemStack[] output = new ItemStack[amountOfUpgradeSlots];

        for(int i = amountOfUpgradeSlots; i<amountOfUpgradeSlots+amountOfContainerSlots; i++){

            output[i] = inventory.getStackInSlot(i);

        }//append all items from container slots into output

        return output;
    }//GetFromUpgradeSlots(): This gets all items from container slots

    @Override
    public void readFromNBT(NBTTagCompound compound) {

        rotationYaw = compound.getInteger("rotation");
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));

        super.readFromNBT(compound);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound n = new NBTTagCompound();
        this.writeToNBT(n);
        return new SPacketUpdateTileEntity(this.pos,getBlockMetadata(),n);
    }//Needed server stuff

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }//Needed server stuff

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }//Needed server stuff

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("rotation",Math.round(rotationYaw));

        return compound;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
         return (T)inventory;
        }
        return super.getCapability(capability,facing);
    }//GetCapability()

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return true;
        }
        return super.hasCapability(capability,facing);
    }//bool HasCapability(): set whether it can take stuff from hoppers


}//class AwakenedChestTileEntity
