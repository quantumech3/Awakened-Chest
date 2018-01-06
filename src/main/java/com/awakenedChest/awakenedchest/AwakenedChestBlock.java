package com.awakenedChest.awakenedchest;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AwakenedChestBlock extends BlockContainer{

    public AwakenedChestBlock(Material materialIn) {

        super(materialIn);

        this.setRegistryName(Vars.AwakenedChest_REGNAME);
        //TODO make AwakenedItemBlock display name correctly
        this.setUnlocalizedName("Awakened Chest");

    }//Constructor

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {return false;}


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        //add inventory TileEntity to AwakenedChestBlock
        return new AwakenedChestTileEntity();

    }//createNewTileEntity

}//Awakened Chest Block
