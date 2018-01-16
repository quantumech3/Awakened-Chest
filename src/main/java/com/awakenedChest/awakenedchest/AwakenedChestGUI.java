package com.awakenedChest.awakenedchest;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class AwakenedChestGUI extends GuiContainer {

    IInventory playerInventory;
    AwakenedChestTileEntity awakenedChestTileEntity;
    AwakenedChestContainer container;
    int page = 0;
    final int BACK_BUTTON = 0;
    final int FOWARD_BUTTON = 1;

    //add white background to gui
    //fixme make gui not be weird the first time you open it

    //These are here to be accessed by the container
    public static int SIZE_X,SIZE_Y;

    public AwakenedChestGUI(IInventory playerinv, AwakenedChestTileEntity tileEntity) {
        super(new AwakenedChestContainer(playerinv,tileEntity,0));

        playerInventory = playerinv;
        awakenedChestTileEntity = tileEntity;



        //Set dimensions before these
        SIZE_X = xSize; SIZE_Y = ySize;
        //---------------------------

    }//Constructor

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {



    }


    @Override
    public void initGui()
    {
        super.initGui();


        int buttonWidth = 8;
        int buttonHeight = 8;

        int button1x = mc.currentScreen.width/2 - xSize/2 + 16*4 + 4;
        int button1y = mc.currentScreen.height/2 - ySize/2 + (16*11);

        int button2x = mc.currentScreen.width/2 - xSize/2 + 16*4 + 4;
        int button2y = mc.currentScreen.height/2 - ySize/2 + (16*11);

        //Button 1: page back
        addButton(
                    new GuiButton(BACK_BUTTON,
                    button1x -(buttonWidth/2),
                    button1y-(buttonWidth/2)-3,
                    buttonWidth,buttonHeight,
                    "<"
                    )
        );

        //Button 2: page foward
        addButton(
                new GuiButton(FOWARD_BUTTON,
                        button2x + (buttonWidth/2),
                        button2y-(buttonWidth/2)-3,
                        buttonWidth,buttonHeight,
                        ">"
                )
        );

    }
    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(!awakenedChestTileEntity.getWorld().isRemote) {
            if (button.id == BACK_BUTTON) {
                page = Math.max(0, page - 1);
                inventorySlots = new AwakenedChestContainer(playerInventory, awakenedChestTileEntity, page);
            }
            if (button.id == FOWARD_BUTTON) {
                page++;
                inventorySlots = new AwakenedChestContainer(playerInventory, awakenedChestTileEntity, page);
            }

            System.out.println(page);
        }
    }//actionPerformed

}//class AwakenedChestGUI
