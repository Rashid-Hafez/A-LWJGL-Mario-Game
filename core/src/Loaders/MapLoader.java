package Loaders;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Physics.GamePhysics;

public class MapLoader {
	
	//coins 16 16 margin 1 spacing 1
	//normal blocks 16 16 0 0 
	//item sheet 16 17 3 1
	
	private TiledMap map;
	private Viewport view;
	private OrthogonalTiledMapRenderer OTMR;
	private String level;
	private OrthographicCamera cam;
	private MyGdxGame game; 
	private World world;
	private GamePhysics physics;
	
	//TODO class description
	public MapLoader(MyGdxGame game, OrthographicCamera camera, World world){
		
		this.world = world;
		this.cam = camera;
		this.game = game;
		
		//100 pixels per meter for box2D physics
		view = new FillViewport(game.width/100,game.height/100,cam);
		view.setWorldHeight(300/100);
		view.setWorldWidth(900/100);
		cam.position.set(view.getWorldWidth()/3f, view.getWorldHeight()/2f,0);
	}
	
	/**
	 * Set own camera parameters, overrides the default parameters
	 */
	public void setCamPos(int x, int y, int z){
		cam.position.set(x, y,z);
	}
	
	/**
	 * Returns the Orthographic Camera for the current level
	 * @return
	 */
	public OrthographicCamera getCam(){
		return cam;
	}
	
	/**
	 * Get the renderer... must call OTMR.render() inside render method at Delta time in the screen classes
	 * @return OrthogonalTiledMapRenderer
	 */
	public OrthogonalTiledMapRenderer OTMR(){
		return OTMR;
	}
	
	/**
	 * Get the viewport for this map/level
	 * @return
	 */
	public Viewport getView(){
		return view;
	}
	public void LoadMap(String file){
		map = game.ASSLOAD.manager.get(file);
		OTMR = new OrthogonalTiledMapRenderer(map, 1/100f);
		OTMR.setView(cam);
		physics = new GamePhysics(world);
		physics.CreateBodies(map);
	
	}
	public void dispose(){
		OTMR.dispose();
		physics.dispose();
		
	}
	
	public void render(float delta){
		cam.update();
		OTMR.setView(cam);
		OTMR.render();
		physics.render(delta, cam);
	}
	
	public void resize(int width, int height){
		view.update(width, height);
		view.setScreenSize(width, height);
		cam.update();
		view.getCamera().update();
	}
}
