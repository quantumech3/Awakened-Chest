package com.awakenedChest.awakenedchest;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class AwakenedItemBlock extends ItemBlock {

    private IItemHandler _inventory;

    @Nullable
    public AwakenedItemBlock(Block block, IItemHandler inventory, String regName) {

        super(block);

        if(inventory != null) {
            _inventory = inventory;
        }//Null guard for _inventory


        this.setRegistryName("awakenedChestItem" + regName);
        //TODO make AwakenedItemBlock have name display correctly
        this.setUnlocalizedName("Awakened Chest");

    }//Constructor

}
