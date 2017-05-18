package me.tobystrong.spinner.proxy;

import me.tobystrong.spinner.FidgetSpinner;
import me.tobystrong.spinner.ModelUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobystrong on 10/05/2017.
 */
public class ClientProxy implements IProxy
{

    public ModelResourceLocation[] spinnerModels;

    @Override
    public void registerRenderers()
    {
        spinnerModels = new ModelResourceLocation[2];
        spinnerModels[0] = new ModelResourceLocation("fidgetspinner:fidget_spinner", "inventory");
        spinnerModels[1] = new ModelResourceLocation("fidgetspinner:fidget_spinner_anim", "inventory");

        ModelBakery.addVariantName(FidgetSpinner.instance.fidget_spinner, "fidgetspinner:fidget_spinner", "fidgetspinner:fidget_spinner_anim");
        ModelLoader.setCustomModelResourceLocation(FidgetSpinner.instance.fidget_spinner, 0, spinnerModels[0]);


        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(FidgetSpinner.instance.fidget_spinner, new ItemMeshDefinition() {
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("fidgetspinner:fidget_spinner", "inventory");
            }
        });

        /*for(int i = 0; i < 16; i++)
        {
            ModelUtil.registerItem(FidgetSpinner.instance.fidget_spinner, i, "fidgetspinner:fidget_spinner");
        }*/
    }

    @Override
    public Object[] getSpinnerModels()
    {
        return spinnerModels;
    }
}
