package com.awakenedChest.awakenedchest;

import com.mojang.realmsclient.client.Request;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.Sys;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AwakenedChestTileEntity extends TileEntity implements ICapabilityProvider, ITickable {

    public int amountOfInventorySlots = 0;
    public int amountOfUpgradeSlots = 3;
    public int amountOfContainerSlots = 3;
    BlockPos playerPos;
    float rotationYaw;
    int rotationAngle = 999;
    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    ItemStackHandler inventory = new ItemStackHandler(amountOfContainerSlots + amountOfUpgradeSlots);

    public ItemStack[] GetFromUpgradeSlots() {

        ItemStack[] output = new ItemStack[amountOfUpgradeSlots];

        for (int i = 0; i < amountOfUpgradeSlots; i++) {

            output[i] = inventory.getStackInSlot(i);

        }//append all items from upgrade slots into output

        return output;
    }//GetFromUpgradeSlots(): This gets all items from upgrade slots

    public ItemStack[] GetFromContainerSlots() {

        ItemStack[] output = new ItemStack[amountOfUpgradeSlots];

        for (int i = amountOfUpgradeSlots; i < amountOfUpgradeSlots + amountOfContainerSlots; i++) {

            output[i - amountOfUpgradeSlots] = inventory.getStackInSlot(i);

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
        return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), n);
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
        compound.setInteger("rotation", Math.round(rotationYaw));

        return compound;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }//GetCapability()

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }//bool HasCapability(): set whether it can take stuff from hoppers


    @Override
    public void update() {
        int _amountOfInventorySlots = amountOfInventorySlots;
        //Reset amountOfInventorySlots so it doesnt keep on going up
        amountOfInventorySlots = 0;
        ItemStack buffer[];
        ItemStack[] containerItems = GetFromContainerSlots();

        for (ItemStack i : containerItems) {

            if (i.getItem() instanceof ItemBlock) {

                ItemBlock itemBlock = (ItemBlock) i.getItem();
                Block block = itemBlock.getBlock();

                    //Switch through all blocks
                    if (block.getClass() == BlockChest.class) amountOfInventorySlots += 27;

                    if (block.getClass() == AwakenedChestBlock.class) amountOfInventorySlots += 27;

                    if (block.getClass() == BlockEnderChest.class) amountOfInventorySlots += 100;
                    //-------------------------

                }//If: if the item from the stack is a itemBlock, switch through everything

            }//For: iterate though every container slot



            buffer = new ItemStack[amountOfInventorySlots + amountOfUpgradeSlots + amountOfContainerSlots];
        if(buffer.length <= inventory.getSlots()){

            for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = inventory.getStackInSlot(i);
            }//for: Set buffer for backup

        }//if amount of inventory slots is not smaller then it was before
        else{

            for (int i = 0; i < inventory.getSlots(); i++) {
                buffer[i] = inventory.getStackInSlot(i);
            }//for: Set buffer for backup

        }//if the amount of inventory slots decreased

        if(_amountOfInventorySlots < amountOfInventorySlots){

            int beginning = amountOfContainerSlots + amountOfUpgradeSlots + amountOfInventorySlots - 1;
            int ending = amountOfContainerSlots + amountOfUpgradeSlots + _amountOfInventorySlots - 1;

            for(int i = beginning; i <= ending; i++ ){

                InventoryHelper.spawnItemStack(Minecraft.getMinecraft().world, pos.getX(),pos.getY(),pos.getZ(), buffer[i]);
                buffer[i] = ItemStack.EMPTY;

            }//go through each slot that isnt there anymore and drop them

        }//If the amount of inventory slots decrease

        inventory.setSize(amountOfContainerSlots+amountOfUpgradeSlots+amountOfInventorySlots);

        if(buffer.length >= inventory.getSlots()){
            for(int i = 0; i < inventory.getSlots(); i++){

                System.out.println(i);
                inventory.setStackInSlot(i,buffer[i]);

            }//for: Set buffer for backup
        }//If the amount of inventory slots increased
        if(buffer.length <= inventory.getSlots()){
            for(int i = 0; i < 2; i++){

                inventory.setStackInSlot(i,buffer[i]);

            }//for: Set buffer for backup
        }//If the amount of inventory slots increased




    }//void Update

}//class AwakenedChestTileEntity


