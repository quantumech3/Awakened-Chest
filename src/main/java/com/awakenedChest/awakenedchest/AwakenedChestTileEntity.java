package com.awakenedChest.awakenedchest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.Sys;
import scala.collection.parallel.ParIterableLike;

import javax.annotation.Nullable;

public class AwakenedChestTileEntity extends TileEntity implements ICapabilityProvider, ITickable {

    public int amountOfInventorySlots = 0;
    public int _amountOfInventorySlots = 0;
    public int amountOfUpgradeSlots = 3;
    public int amountOfContainerSlots = 3;
    BlockPos playerPos;
    float rotationYaw;
    int rotationAngle = 999;
    //The item handlers size is 6 because initally there are 3 container slots and 3 upgrade slots
    ItemStackHandler inventory = new ItemStackHandler(amountOfContainerSlots + amountOfUpgradeSlots);

    public AwakenedChestTileEntity() {
        inventory.setSize(100000);
    }//Constructor

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
            return false;
        }
        return super.hasCapability(capability, facing);
    }//bool HasCapability(): set whether it can take stuff from hoppers

    public int GetAmountOfInventorySlots(){

        ItemStack[] containerItems = GetFromContainerSlots();
        int output = 0;

        for (ItemStack i : containerItems) {

            if (i.getItem() instanceof ItemBlock) {


                ItemBlock itemBlock = (ItemBlock) i.getItem();
                Block block = itemBlock.getBlock();

                //Switch through all blocks
                if (block.getClass() == BlockChest.class) output += 27;

                if (block.getClass() == AwakenedChestBlock.class) output += 27;

                if (block.getClass() == BlockEnderChest.class) output += 100;
                //-------------------------

            }//If: if the item from the stack is a itemBlock, switch through everything
        }//For: iterate though every container slot

        return output;

    }

    public ItemStack[] InitializeBufferWith(ItemStack[] buffer, ItemStack item){

        for(int i = 0; i < buffer.length; i++){
            buffer[i] = item;
        }

        return buffer;

    }

    public ItemStack[] BackupInventory(){

        ItemStack[] buffer = new ItemStack[10000];

        buffer = InitializeBufferWith(buffer,ItemStack.EMPTY);

        if(buffer.length <= inventory.getSlots()){

            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = inventory.getStackInSlot(i);
            }//for: Set buffer for backup

        }//if amount of inventory slots is not smaller then it was before


        return buffer;

    }//BackupInventory()


    public void DropItem(ItemStack stack, BlockPos pos){

        if(!getWorld().isRemote) {
            EntityItem entityItem = new EntityItem(getWorld(), pos.getX(), pos.getY(), pos.getZ(), stack);
            getWorld().spawnEntity(entityItem);
        }

    }

    public void DropAnyExessItems(int _amountOfInventorySlots){

            int beginning = amountOfContainerSlots + amountOfUpgradeSlots + amountOfInventorySlots - 1;
            int ending = amountOfContainerSlots + amountOfUpgradeSlots + _amountOfInventorySlots - 1;

            for(int i = beginning; i <= ending; i++ ){

                DropItem(inventory.getStackInSlot(i),pos);
                inventory.setStackInSlot(i,ItemStack.EMPTY);

            }//go through each slot that isnt there anymore and drop them



    }

    public void RecoverFromBackup(){



    }//RecoverFromBackup():

    public boolean IsContainer(Block block){


        if(block instanceof BlockContainer){
            return true;
        }

        return false;
    }//IsContainer()

    public boolean IsItemBlock(Item item){

        if(item instanceof ItemBlock){
            return true;
        }
        return false;
    }

    @Override
    public void update() {

        _amountOfInventorySlots = amountOfInventorySlots;
        amountOfInventorySlots = GetAmountOfInventorySlots();

        if(_amountOfInventorySlots > amountOfInventorySlots){
            DropAnyExessItems(_amountOfInventorySlots);
        }



    }//void Update


}//class AwakenedChestTileEntity


