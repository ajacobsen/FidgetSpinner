package me.tobystrong.spinner.item;

import me.tobystrong.spinner.FidgetSpinner;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by tobystrong on 10/05/2017.
 */
public class ItemSpinner extends Item
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

    public ItemSpinner()
    {
        super();
        setRegistryName("fidget_spinner");
        setUnlocalizedName("fidget_spinner");
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
        if(player.isUsingItem() && stack.hasTagCompound() && stack.getTagCompound().hasKey("spin") && stack.getTagCompound().getBoolean("spin"))
        {
            return (ModelResourceLocation) FidgetSpinner.proxy.getSpinnerModels()[1];
        }

        return (ModelResourceLocation) FidgetSpinner.proxy.getSpinnerModels()[0];
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stackIn, World worldIn, EntityPlayer playerIn)
    {
        if(!stackIn.hasTagCompound())
        {
            return stackIn;
        }

        stackIn.getTagCompound().setBoolean("spin", false);

        playerIn.playSound("random.click", 1f, 1f);
        return stackIn;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int tintIndex)
    {
        return colors[stack.getMetadata() < 16 ? stack.getMetadata() : 0];
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for(int i = 0; i < 16; i++)
        {
            subItems.add(new ItemStack(FidgetSpinner.instance.fidget_spinner, 1, i));
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stackIn, World worldIn, EntityPlayer playerIn)
    {
        if(!stackIn.hasTagCompound())
        {
            stackIn.setTagCompound(new NBTTagCompound());
        }

        stackIn.getTagCompound().setBoolean("spin", true);
        playerIn.setItemInUse(stackIn, getMaxItemUseDuration(stackIn));

        playerIn.playSound("random.click", 1f, 1f);
        return stackIn;
    }
}
