package com.awakenedChest.awakenedchest;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.Sys;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import scala.collection.parallel.ParIterableLike;
import sun.plugin.com.Utils;

import javax.annotation.Nullable;
import java.util.logging.Logger;

public class AwakenedChestTileEntity extends TileEntityChest implements ICapabilityProvider {


    BlockPos playerPos;
    float rotationYaw;
    int rotationAngle = 999;



    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    IItemHandler itemHandler = new ItemStackHandler(6);

    @Override
    public void readFromNBT(NBTTagCompound compound) {



        rotationYaw = compound.getInteger("rotation");

        super.readFromNBT(compound);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound n = new NBTTagCompound();
        this.writeToNBT(n);
        return new SPacketUpdateTileEntity(this.pos,getBlockMetadata(),n);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        compound.setInteger("rotation",Math.round(rotationYaw));



        return compound;
    }

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

}//class AwakenedChestTileEntity
