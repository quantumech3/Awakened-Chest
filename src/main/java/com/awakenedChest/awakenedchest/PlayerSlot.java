package com.awakenedChest.awakenedchest;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class PlayerSlot extends Slot {


    public PlayerSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override public TextureAtlasSprite getBackgroundSprite() { return AwakenedChest.inventorySlotTexture; }
}
