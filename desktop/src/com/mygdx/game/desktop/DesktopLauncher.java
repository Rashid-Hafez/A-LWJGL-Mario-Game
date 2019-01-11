package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.MyGdxGame;

/**
 * 
 * Will eventually move configuration files to be in another class to enable dynamic configuration
 * in game... maybe i wont... too lazy
 * 
 * Bowser's kingdom has been held captive by King Boo
 * Need's Princess Peach to save him
 * Peach becomes possessed
 * 
 * @author Rashid Hafez
 *
 *@version preAlpha
 *
 *@TODO Remake map add the coin room down the pipe.
 *add stuff
 *
 *@references: Brent tutorials on LWJGL
 *
 */
public class DesktopLauncher {
	
	private static LwjglApplicationConfiguration cfg;
	
	public static void main (String[] arg) {		
		cfg = new LwjglApplicationConfiguration();
        cfg.title = "Mario The Lost Tale";
        cfg.resizable = false;
        cfg.width = 1200;
        cfg.height = 600;
        cfg.allowSoftwareMode = true;
        cfg.initialBackgroundColor = Color.BLACK;
        cfg.vSyncEnabled = true;
        new LwjglApplication(new MyGdxGame(), cfg);
	}
	
	public LwjglApplicationConfiguration getCfg(){
		return cfg;
	}
}
