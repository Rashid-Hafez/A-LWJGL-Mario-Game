/**
In order to not use full path for resources in the current directory, you need to right click on DESKTOP
project, then go to buildpath, then source folder. then add ASSETS to source folder.

now we can just do "/GUI/ui-orange" instead of /Users/Rashid/Desktop/.../assets/gui..
**/
package Loaders;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.assets.loaders.SkinLoader; //might use this later
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin; //might use this later

public class AssetLoader {
	//Assets
//	public static final String p= "/Users/rashid/Desktop/testPort/core/assets/";
	
//	File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
	
	public String paths = AssetLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"assets/";
	
	private final String [] pack = 
		{"GUI/ui-blue.atlas","GUI/ui-orange.atlas","GUI/ui-red.atlas","GUI/ui-green.atlas","GUI/ui-white.atlas",
				"GUI/HUD.txt","Mario!/marioWalk.txt","Luigi!/luigiWalk.txt"};
	private final String [] img = {"GUI/coinHUD.png","bgImage.png"};
	private final String [] map = {"Maps/Level1.tmx","Maps/Level2.tmx"};
	public final  AssetManager manager;
	private int x;
	
	//Mini Constructor
	public AssetLoader(){
	manager  = new AssetManager();
	System.out.println(paths);
	}
	
	//load
	public void load()
{
		for (String s : pack){
			x++;
			System.out.println("Loading Textures "+x+" of "+pack.length);
			manager.load(s, TextureAtlas.class);
		}
		
		x=0;
		
		System.out.println("Loading Static Textures");
		for (String s : img){
			manager.load(s,Texture.class);
		}
		
		System.out.println("Loading Maps");
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		for (String s : map){
			x++;
			System.out.println("Loading Map "+x+"of "+map.length);
			manager.load(s, TiledMap.class);
		}
}
	
public void dispose()
{
    manager.dispose();
}
}
