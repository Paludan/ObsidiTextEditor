package com.obsidiskrivemaskine;

import com.obsidiskrivemaskine.GUI.CustomCow;
import com.obsidiskrivemaskine.block.KurtSkriveMaskineBlock;
import com.obsidiskrivemaskine.Handler.ObsidiGuiHandler;
import com.obsidiskrivemaskine.Proxy.ObsidiServerProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ObsidiSkriveMaskineMod.MODID, version = ObsidiSkriveMaskineMod.VERSION)
public class ObsidiSkriveMaskineMod
{
    public static final String MODID = "obsidiskrivemaskine";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static ObsidiSkriveMaskineMod INSTANCE;

    public static Block obsidiblock;

    @SidedProxy(clientSide = "com.obsidiskrivemaskine.Proxy.ObsidiClientProxy", serverSide = "com.obsidiskrivemaskine.Proxy.ObsidiServerProxy")
    public static ObsidiServerProxy PROXY;

    public static int kurtguiid;

    @EventHandler
    public void preinit (FMLPreInitializationEvent event)
    {
        obsidiblock = new KurtSkriveMaskineBlock().setUnlocalizedName("ObsidiSkriveMaskineBlock").setCreativeTab(CreativeTabs.tabRedstone);
        kurtguiid = 1;
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        registerBlocks();
        registerHandlers();

        CustomCow.RegisterEntity();
    }

    @EventHandler
    public void postinit (FMLPostInitializationEvent event)
    {
        PROXY.registerModels();
    }

    public void registerBlocks ()
    {
        GameRegistry.registerBlock(obsidiblock, "ObsidiSkriveMaskineBlock");
    }

    public void registerHandlers ()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new ObsidiGuiHandler());
    }
}
