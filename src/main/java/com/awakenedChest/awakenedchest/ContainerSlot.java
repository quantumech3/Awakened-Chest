package com.awakenedChest.awakenedchest;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerSlot extends SlotItemHandler{
    ItemStack itemStack;
    boolean isEnabled = true;
    //todo make discrimination between container blocks and other stuff

    public ContainerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        setBackgroundName(AwakenedChest.MODID + ":slottexture.png");

        ItemStack itemStack = itemHandler.getStackInSlot(index);
        if(itemStack != ItemStack.EMPTY){
            isEnabled = false;
        }

    }//Constructor


    @Override public int getSlotStackLimit() {return 1;}

    @Override public int getItemStackLimit(@Nonnull ItemStack stack) {return 1;}

    @Override
    public boolean isEnabled() {

        return isEnabled;
    }

    @Override public TextureAtlasSprite getBackgroundSprite() { return AwakenedChest.containerSlotTexture; }

}//class UpgradeSlot
