package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Loaders.MapLoader;
import Player.Player;
import Scenes.HUD;
import Sprites.Bros;

import com.badlogic.gdx.Gdx;

//SMALL MARIO SHOULD BE 1.5 TILES HIGH AND
//BIG MARIO SHOULD BE 2.5 HIGH
//JUMPS 2.5 TILES HIGH

public class Play implements Screen {
	
	//core variables
	MyGdxGame game;
	private Play play;
	private HUD hud;
	protected Viewport view;
	private boolean firstTime =true;
	private MapLoader LoadMap;
	public World world;
	private OrthographicCamera cam;
	
	//player variables
	private Player user;
	
	/**
	 * Takes the core game.java class as a param to render play world. Creates a world and passes it to the LoadMap engine and the Physics engine
	 * If this is the first time playing,
	 * the first level will load, and be accessible after the short movie.
	 * @param game
	 * @param OrthographicCamera
	 */
	public Play (MyGdxGame game, OrthographicCamera cam){
		this.cam = cam;
		this.game = game;
		hud = new HUD(game);
		System.out.println("called");
		
		//Create World first, then pass this world to load map, Load map will pass it to gamePhysics
		if(firstTime){
			world = new World(new Vector2(0,-3), true); //1
			LoadMap = new MapLoader(game,cam, world); //2 
			LoadMap.LoadMap("Maps/Level2.tmx"); //3
			user = new Player(world);  //4 
		}
		//Else... load from save file... same order:
		/*
		 * world = new world
		 * LoadMap
		 * LoadMap
		 * Player
		 * ... this is how it is, otherwise later i could change the code
		 * to call the physics class from here... idk, i prefer it this way.
		 */
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		//clear screen
		Gdx.gl.glClearColor(1, (float) 10.000300005, 50, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	//render screen
	LoadMap.render(delta); //TODO: Send world instance to GamePhysics and MapLoader. GG
	hud.getStage().act(delta);
	hud.getStage().draw();
	world.step(1/60f, 6, 2);
	
	user.Actions(delta);
	cam.position.x = user.getBody().getPosition().x;
	
	}

	@Override
	public void resize(int width, int height) {
		LoadMap.resize(width, height);
		//hud.stage.getViewport().update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		LoadMap.dispose();
		world.dispose();
		hud.dispose();
		game.dispose();
	}

}
