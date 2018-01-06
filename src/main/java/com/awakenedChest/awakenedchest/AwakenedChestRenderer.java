package com.awakenedChest.awakenedchest;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

//TODO make chest be place at a rotation relative to how you place it
public class AwakenedChestRenderer extends TileEntitySpecialRenderer {

    ModelChest modelChest = new ModelChest();

    ResourceLocation texture = new ResourceLocation(AwakenedChest.MODID+":awakenedchest.png");

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        System.out.println("Rendering");
        bindTexture(texture);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x+1,y+1,z);
        GlStateManager.rotate(180,0,0,1);
        GlStateManager.scale(1,1,1);
        modelChest.renderAll();
        GlStateManager.popMatrix();
    }
}//class AwakenedChestRenderer




