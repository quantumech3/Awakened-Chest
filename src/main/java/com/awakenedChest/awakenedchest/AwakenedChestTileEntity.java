package com.awakenedChest.awakenedchest;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AwakenedChestTileEntity extends TileEntityChest implements ICapabilityProvider, ISidedInventory {

    public boolean allowHoppers = false;
    BlockPos playerPos;
    float rotationYaw;
    int rotationAngle = 999;
    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    ItemStackHandler inventory = new ItemStackHandler(6){

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }//Stack size
    };

    @Override
    public int getSizeInventory() {
        return 6;
    }

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
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }//int[] getSlotsForFace()

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) { return allowHoppers; }//Allow hoppers is false right now

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) { return (T)inventory; }

}//class AwakenedChestTileEntity
