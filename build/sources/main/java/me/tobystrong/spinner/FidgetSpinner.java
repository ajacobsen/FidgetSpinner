package me.tobystrong.spinner;

import me.tobystrong.spinner.item.ItemSpinner;
import me.tobystrong.spinner.proxy.IProxy;
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
        GameRegistry.register(fidget_spinner);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent init)
    {
        proxy.registerRenderers();
    }
}
