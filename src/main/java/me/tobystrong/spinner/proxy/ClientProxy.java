package me.tobystrong.spinner.proxy;

import me.tobystrong.spinner.FidgetSpinner;
import me.tobystrong.spinner.ModelUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * Created by tobystrong on 10/05/2017.
 */
public class ClientProxy implements IProxy
{
    private static final int[] colors = new int[]{
        0xE9ECEC,
        0xF07613,
        0xBD44B3,
        0x3AAFD9,
        0xF8C627,
        0x70B919,
        0xED8DAC,
        0x555d60,
        0x8E8E86,
        0x158991,
        0x792AAC,
        0x35399D,
        0x724728,
        0x546D1B,
        0xA12722,
        0x3E4447
    };

    @Override
    public void registerRenderers()
    {
        for(int i = 0; i < 16; i++)
        {
            ModelUtil.registerItem(FidgetSpinner.instance.fidget_spinner, i, "fidgetspinner:fidget_spinner");
            CraftingManager.getInstance().addRecipe(new ItemStack(FidgetSpinner.instance.fidget_spinner, 1, i), " S ", "SWS", " S ", 'W', new ItemStack(Blocks.WOOL, 1, i), 'S', new ItemStack(Items.STICK, 1));
        }

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return colors[stack.getMetadata() < 16 ? stack.getMetadata() : 0];
            }
        }, FidgetSpinner.instance.fidget_spinner);
    }
}
