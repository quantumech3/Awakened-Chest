package com.awakenedChest.awakenedchest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.Sys;

//TODO make chest be place at a rotation relative to how you place it
public class AwakenedChestRenderer extends TileEntitySpecialRenderer {



    double GetDistance(double x, double y, double z){
        return Math.sqrt(
                Math.pow(x,2)+
                        Math.pow(y,2)+
                        Math.pow(z,2)
        );
    }//Get Distance(): Pythagoram therum :D

    double GetDistanceFromPlayer(AwakenedChestTileEntity tileEntBlockPos){

        double playerX = Math.abs(tileEntBlockPos.playerPos.getX());
        double playerY = Math.abs(tileEntBlockPos.playerPos.getY());
        double playerZ = Math.abs(tileEntBlockPos.playerPos.getZ());

        double blockX = Math.abs(tileEntBlockPos.getPos().getX());
        double blockY = Math.abs(tileEntBlockPos.getPos().getY());
        double blockZ = Math.abs(tileEntBlockPos.getPos().getZ());



        //fixme might need to reverse the subtraction
        return GetDistance(
                playerX - blockX,
                playerY - blockY,
                playerZ - blockZ
        );

    }//GetDistanceFromPlayer()

    ModelChest modelChest = new ModelChest();

    ResourceLocation texture = new ResourceLocation(AwakenedChest.MODID+":awakenedchest.png");

    public void renderAwakenedChest(AwakenedChestTileEntity tileEntity,double x,double y, double z){

        bindTexture(texture);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x,y,z);



        //Makes the model right side up
        GlStateManager.translate(1,1,0);
        GlStateManager.rotate(180,0,0,1);
        //-----------------------------

        GlStateManager.scale(1,1,1);
        GlStateManager.pushMatrix();
        //Rotate depending on how the player places it

        GlStateManager.rotate(-Minecraft.getMinecraft().player.rotationYaw,0,1,0);
        //--------------------------------------------
        modelChest.renderAll();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }//Rendering code goes here

    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderAwakenedChest((AwakenedChestTileEntity)te,x,y,z);
    }
}//class AwakenedChestRenderer




