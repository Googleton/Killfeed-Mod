package com.gugu42.killfeed;

public class Utils {
	
	public static int mapRange(int old_min, int old_max, int new_min, int new_max, int old_value){
		return new_min + ((old_value - old_min)*(new_max - new_min))/(old_max - old_min);
	}
}
