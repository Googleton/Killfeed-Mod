package com.gugu42.killfeed;

import java.util.ArrayList;
import java.util.Iterator;

import com.gugu42.killfeed.gui.GUIKillfeed;
import com.gugu42.killfeed.gui.KillfeedItem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.tools.nsc.io.SourceReader;

public class KillfeedEventHandler {

		private ArrayList<KillfeedItem> killfeedItems = new ArrayList<KillfeedItem>();
	
		/*
		 * Handles the render of killfeed's HUD GUI.
		 */
	    @SubscribeEvent
	    public void onRenderGui(RenderGameOverlayEvent.Post event)
	    {
	    	if (event.getType() != ElementType.EXPERIENCE) return;
	    	
	    	
	    	KillfeedItem kfItem = new KillfeedItem();
	    	
	    	kfItem.victimName = "Creeper";
			kfItem.killerName = "Player999";
			
			kfItem.deathType = KillfeedItem.getKillTypeFromDamageSource("arrow");
	    	new GUIKillfeed(Minecraft.getMinecraft(), kfItem, 0);
	    	kfItem.deathType = KillfeedItem.getKillTypeFromDamageSource("sword");
	    	new GUIKillfeed(Minecraft.getMinecraft(), kfItem, 1);
	    	kfItem.deathType = KillfeedItem.getKillTypeFromDamageSource("thrown");
	    	new GUIKillfeed(Minecraft.getMinecraft(), kfItem, 2);
	    	kfItem.deathType = KillfeedItem.getKillTypeFromDamageSource("explosion.player");
	    	new GUIKillfeed(Minecraft.getMinecraft(), kfItem, 3);
	    	new GUIKillfeed(Minecraft.getMinecraft(), kfItem, 4);
	    	
	    	int idx = 0;
	    	for (Iterator<KillfeedItem> iterator = killfeedItems.iterator(); iterator.hasNext(); idx++) {
	    	    KillfeedItem item = iterator.next();
	    	    
	    	    long timeDelta = System.currentTimeMillis() - item.timeCreated;
	    	    
	    	    if (timeDelta >= 3000) {
	    	        iterator.remove();
	    	        continue;
	    	    }
	    	    
	    	    new GUIKillfeed(Minecraft.getMinecraft(), item, idx);
	    	}
	    }
	    
	    /*
	     * Handles the data of kills
	     */
	    @SubscribeEvent
	    public void onEntityDies(LivingDeathEvent event)
	    {
	    	//We only display PvP
	    	if(event.getEntity() != null && event.getEntity() instanceof EntityPlayer)
	    	{
	    		DamageSource source = event.getSource();
	    		//We only display PvP
	    		if(source.getEntity() != null && source.getEntity() instanceof EntityPlayer)
	    		{
	    			KillfeedItem data = new KillfeedItem();
	    			data.deathType = KillfeedItem.getKillTypeFromDamageSource(source.damageType);
	    			data.victimName = event.getEntity().getName();
	    			data.killerName = source.getEntity().getName();
	    			killfeedItems.add(0, data);
	    		}
	    	}
	    }
}


