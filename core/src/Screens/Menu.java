package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Loaders.MapLoader;
import Scenes.HUD;
import Scenes.MenuGui;
import Screens.Play;

public class Menu implements Screen {
	
	@SuppressWarnings("unused")
	private Play play;
	private MenuGui menuGUI;
	private OrthographicCamera cam;
	protected MyGdxGame game;
	protected Viewport view;

	//Map shit
	private TiledMap map;
	private OrthogonalTiledMapRenderer OTMR;
	
	//pro animation shit:
	private TextureAtlas marioAtlas;
	private Animation<TextureRegion> marioAnimation;
	private float accurateTime;
	public boolean var;
	public boolean mario =false;
	public boolean luigi =false;
	private boolean quit =false;
	private Animation<TextureRegion> luigiAnimation;
	private TextureAtlas luigiAtlas;
	private MapLoader Maploader;

	public Menu(MyGdxGame myGdxGame) {
		
		//Essentials
		//TODO Maploader.view.setWorldHeight(800);
		this.game = myGdxGame;
		cam = new OrthographicCamera();
		view = new FitViewport(game.width/100,game.height/100,cam);
		view.setWorldHeight(800);
		view.setWorldWidth(1700);
		cam.viewportHeight = game.height;
		cam.viewportWidth = game.width;
		
		//MenuGUI
		menuGUI = new MenuGui(myGdxGame, this);
		
		//TODO Loading map stuff// Move this shit into another class
		
		//TODO MapLoader = new Maploader("Maps/Level1.tmx");
		
		map=myGdxGame.ASSLOAD.manager.get("Maps/Level1.tmx");
		OTMR = new OrthogonalTiledMapRenderer(map,3.37f);
		cam.position.set(view.getWorldWidth()/2, view.getWorldHeight()/2,0);
		//animations
		marioAtlas = game.ASSLOAD.manager.get("Mario!/marioWalk.txt");
        marioAnimation = new Animation<TextureRegion>(0.18f, marioAtlas.findRegions("mario"));
        
        luigiAtlas = game.ASSLOAD.manager.get("Luigi!/luigiWalk.txt");
        luigiAnimation = new Animation<TextureRegion>(0.18f,luigiAtlas.findRegions("luigi"));
	}
	
	//Calculates and renders data at runtime deltaTime
	public void render(float delta) {
		//default
		Gdx.gl.glClearColor(1, (float) 10.000300005, 50, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//TODO: Move this shit
		//Maploader.getCam.update();
		//Maploader.OTMR.setView(Maploader.getCam);
		//Maploader.OTMR.render();
		cam.update();
		OTMR.setView(cam);
		OTMR.render();
		
		//cool looking traversing map
		//TODO change to Maploader
		//Maploader.getCam.position.x += 1;
		cam.position.x += 1;
		
		menuGUI.stage.act(delta);
		//hud.stage.act(delta);
		menuGUI.stage.draw();
		//hud.stage.draw();
		Gdx.input.setInputProcessor(menuGUI.stage);
		accurateTime +=Gdx.graphics.getDeltaTime();

		//DONT DRAW ANYTHING ELSE INSIDE. AND ALWAYS DISPOSE YOUR STUFF **AFTER BATCH END** took me 2 hours
		//FFS!
		if(var){
		TextureRegion currentFrame = marioAnimation.getKeyFrame(accurateTime, true);
		game.batch.begin();
		game.batch.draw(currentFrame, 157f, 200);
		TextureRegion currentLui = luigiAnimation.getKeyFrame(accurateTime,true);
		game.batch.draw(currentLui, 963, 190);
		game.batch.end();
		}
		if(mario){
			dispose();
			game.setScreen((Screen)new Play(game,cam));
		}
		//TODO play as Luigi
		if(luigi){
			dispose();
			game.setScreen((Screen) new Play(game,cam));
		}
	}
	@Override
	public void resize(int width, int height) {
		//must always do this
		/*TODO
		 * Maploader.getCam.update();
		 * Maploader.view.update(width,height);
		 * Maploader.view.setScreenSize(width,height);
		 */
		view.update(width, height);
		view.setScreenSize(width, height);
		cam.update();
		view.getCamera().update();
		menuGUI.stage.getViewport().update(width, height);
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		//nothing... maybe minimize
	}

	@Override
	//Function to call the next scene
	public void dispose() {
		menuGUI.stage.dispose();
		OTMR.dispose();
		map.dispose();
		
		if(quit){
		this.game.dispose();
		System.exit(0);
		}		
	}

	@Override
	public void show() {
		
	}

}
