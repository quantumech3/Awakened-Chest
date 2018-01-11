package com.awakenedChest.awakenedchest;

import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


//TODO make upgrade slot only allow upgrades
public class UpgradeSlot extends SlotItemHandler{

    public UpgradeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

    }//Constructor




    @Override public int getSlotStackLimit() {return 1;}

    @Override public int getItemStackLimit(@Nonnull ItemStack stack) {return 1;}

    @Nullable
    @Override
    public String getSlotTexture() {
        //fixme make UpgradeSlot have a texture and have container slot have a texture
        return "resources/assets/awakenedchest/slottexture.png";
    }

}//class UpgradeSlot
