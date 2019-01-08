package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Loaders.AssetLoader;
import Screens.Menu;

public class MyGdxGame extends Game{
	public SpriteBatch batch;
	public int PPM = 1*100;
	public final int height = 600;
	public final int width = 1200;
	public AssetLoader ASSLOAD; //Load the asses
	@Override
	public void create () {
		batch = new SpriteBatch();
		ASSLOAD = new AssetLoader();
		ASSLOAD.load();
		ASSLOAD.manager.finishLoading();
		setScreen((Screen) new Menu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();

	}
}
