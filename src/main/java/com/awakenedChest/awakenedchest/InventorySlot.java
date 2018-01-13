package com.awakenedChest.awakenedchest;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class InventorySlot extends SlotItemHandler{

    public int stackSize = 64;

    public InventorySlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        setBackgroundName(AwakenedChest.MODID + ":slottexture.png");
    }//Constructor



    @Override public int getSlotStackLimit() {return stackSize;}

    @Override public int getItemStackLimit(@Nonnull ItemStack stack) {return stackSize;}

    @Override public TextureAtlasSprite getBackgroundSprite() { return AwakenedChest.inventorySlotTexture; }

}//class UpgradeSlot
