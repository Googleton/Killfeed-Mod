package com.gugu42.killfeed.gui;

import net.minecraft.util.DamageSource;

public class KillfeedItem
{
	enum KillType
	{
		SWORD, BOW, VOID, EXPLOSION
	}
	
	public String killerName;
	public int killerColor;
	
	public String victimName;
	public int victimColor;
	
	public KillType deathType;
	public int duration;
	public long timeCreated;
	
	public DamageSource source;
	
	public KillfeedItem()
	{
		this.timeCreated = System.currentTimeMillis();
	}
	
	public static KillType getKillTypeFromDamageSource(String type)
	{
		switch(type)
		{
		case "player":
			return KillType.SWORD;
		case "arrow":
			return KillType.BOW;
		case "thrown":
			return KillType.VOID;
		case "explosion.player":
			return KillType.EXPLOSION;
		default:
			return KillType.SWORD;
		}
		
	}
	
}