package com.awakenedChest.awakenedchest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import javax.annotation.Nullable;

public class AwakenedChestBlock extends BlockContainer{

    BlockPos _pos;

    public AwakenedChestBlock(Material materialIn) {

        super(materialIn);

        this.setRegistryName(Vars.AwakenedChest_REGNAME);
        //TODO make AwakenedItemBlock display name correctly
        this.setUnlocalizedName("Awakened Chest");

    }//Constructor

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {


        if(!worldIn.isRemote) {
            playerIn.openGui(AwakenedChest.MODID, AwakenedChestGuiHandler.MOD_TILE_ENTITY_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }//If it this is a client, open a gui

        return false;
    }//boolean OnBlockActivated()

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {

        _pos = pos;

        return false;
    }

    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
        return 1;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        //add inventory TileEntity to AwakenedChestBlock

        //This is done because you cant load player position at launch when there is no player in the world
        AwakenedChestTileEntity tileEntity = new AwakenedChestTileEntity();

        tileEntity.rotationYaw = Minecraft.getMinecraft().player.rotationYaw;
        tileEntity.playerPos = Minecraft.getMinecraft().player.getPosition();
        //-------------------------------------------------------------------------------------------------

        return tileEntity;

    }//createNewTileEntity

}//Awakened Chest Block
