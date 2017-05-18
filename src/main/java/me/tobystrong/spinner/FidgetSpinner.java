package me.tobystrong.spinner;

import me.tobystrong.spinner.item.ItemSpinner;
import me.tobystrong.spinner.proxy.IProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by tobystrong on 10/05/2017.
 */
@Mod(modid = "fidgetspinner", name = "Fidget Spinner", version = "1")
public class FidgetSpinner
{
    @Mod.Instance("fidgetspinner")
    public static FidgetSpinner instance;

    @SidedProxy(modId = "fidgetspinner", clientSide = "me.tobystrong.spinner.proxy.ClientProxy", serverSide = "me.tobystrong.spinner.proxy.ServerProxy")
    public static IProxy proxy;

    public ItemSpinner fidget_spinner;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent pre)
    {
        fidget_spinner = new ItemSpinner();
        GameRegistry.registerItem(fidget_spinner, "fidget_spinner");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent init)
    {
        proxy.registerRenderers();


        for(int i = 0; i < 16; i++)
        {
            CraftingManager.getInstance().addRecipe(new ItemStack(FidgetSpinner.instance.fidget_spinner, 1, i), " S ", "SWS", " S ", 'W', new ItemStack(Blocks.wool, 1, i), 'S', new ItemStack(Items.stick, 1));
        }
    }
}
