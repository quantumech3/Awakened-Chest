package com.awakenedChest.awakenedchest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class AwakenedChestGuiHandler implements IGuiHandler {

    public static final int MOD_TILE_ENTITY_GUI = 0;


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new AwakenedChestContainer(player.inventory,(AwakenedChestTileEntity)world.getTileEntity(new BlockPos(x,y,z)),0);
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new AwakenedChestGUI(player.inventory,(AwakenedChestTileEntity)world.getTileEntity(new BlockPos(x,y,z)));
    }

}//class AwakenedChestGuiHandler
