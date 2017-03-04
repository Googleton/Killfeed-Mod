package com.gugu42.killfeed.gui;

import org.lwjgl.opengl.GL11;

import com.gugu42.killfeed.Utils;
import com.gugu42.killfeed.gui.KillfeedItem.KillType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIKillfeed extends Gui {

	public GUIKillfeed(Minecraft mc, KillfeedItem data, int index)
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
			//Format :               killer name                 ICON          victim Name
		
		int offsetY = 5 + (index * 19);
		int timeDelta = (int) (System.currentTimeMillis() - data.timeCreated);
		int alpha = 0x00;
		alpha = Utils.mapRange(0, 3000, 0, 255, timeDelta);
		alpha *= 16777216;
		
		int offsetX = 4 + getTextLengthInPixel(data.killerName) + 18 + getTextLengthInPixel(data.victimName);
		
		drawRect(width - offsetX, offsetY, width - 2, offsetY + 18, -(0x800000 | alpha));
		drawRect(width - offsetX + 2, offsetY + 2, width - 4, offsetY + 16, -(0xEEEEEE | alpha));
		drawString(mc.fontRenderer, data.killerName, width - offsetX + 4, offsetY + 5, 0xFFFFFF);
		drawString(mc.fontRenderer, data.victimName, width - getTextLengthInPixel(data.victimName) - 4, offsetY + 5, 0xFFFFFF);
		
		int texX = 0;
		int texY = 0;
		
		if(data.deathType == KillType.BOW)
			texX = 16;
		if(data.deathType == KillType.VOID)
			texX = 32;
		if(data.deathType == KillType.EXPLOSION)
		{
			texX = 48;
			texY = 2;
		}
		
		
		mc.getTextureManager().bindTexture(new ResourceLocation("killfeed", "textures/gui/icons.png"));
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		this.drawTexturedModalRect(width - getTextLengthInPixel(data.victimName) - 20 , offsetY + 1, texX, texY, 16, 16);
		GlStateManager.popMatrix();
	}
	
	public static int getTextLengthInPixel(String text)
	{
		return text.length() * 6;
	}
	
}
