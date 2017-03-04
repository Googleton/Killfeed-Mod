package com.gugu42.killfeed;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = KillfeedMod.MODID, version = KillfeedMod.VERSION)
public class KillfeedMod
{
    public static final String MODID = "killfeed";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new KillfeedEventHandler());
    }
}
