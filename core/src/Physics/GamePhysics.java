package Physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GamePhysics {

	private World world;
	private Box2DDebugRenderer debug;
	
	/**
	 * Takes a world and renders the collision and physics for all the objects
	 * 
	 * @param world
	 */
	public GamePhysics(World world){
		this.world = world;
		debug = new Box2DDebugRenderer();
	}
	
	public void CreateBodies(TiledMap map){
		
		BodyDef bdef = new BodyDef();
		PolygonShape poly = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		//Iterate through Ground Objects rendered on the map (layer 3)
		for(MapObject Object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) Object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/100f, (rect.getY()+rect.getHeight()/2)/100f);
			
			body = world.createBody(bdef);
			poly.setAsBox((rect.getWidth()/2)/100f, (rect.getHeight()/2)/100f);
			fdef.shape = poly;
			body.createFixture(fdef);
		}
		
		//Iterate through Question Blocks on the Map (layer 4)
		for(MapObject Object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) Object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/100f, (rect.getY()+rect.getHeight()/2)/100f);
			
			body = world.createBody(bdef);
			poly.setAsBox((rect.getWidth()/2)/100f, (rect.getHeight()/2)/100f);
			fdef.shape = poly;
			body.createFixture(fdef);
		}
		
		//Iterate through bricks on Map (layer5)
		for(MapObject Object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) Object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/100f, (rect.getY()+rect.getHeight()/2)/100f);
			
			body = world.createBody(bdef);
			poly.setAsBox((rect.getWidth()/2)/100f, (rect.getHeight()/2)/100f);
			fdef.shape=poly;
			body.createFixture(fdef);
		}
		
		//Iterate through coins on map (layer 6)
		for(MapObject Object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) Object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/100f, (rect.getY()+rect.getHeight()/2)/100f);
			
			body = world.createBody(bdef);
			poly.setAsBox((rect.getWidth()/2)/100f, (rect.getHeight()/2)/100f);
			fdef.shape=poly;
			body.createFixture(fdef);
		}
		
		//Iterate through pipes on map (layer 7)
		for(MapObject Object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) Object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/100f, (rect.getY()+rect.getHeight()/2)/100f);
			
			body = world.createBody(bdef);
			poly.setAsBox((rect.getWidth()/2)/100f, (rect.getHeight()/2)/100f);
			fdef.shape=poly;
			body.createFixture(fdef);
		}
	}
	
	public void render(float delta, OrthographicCamera cam){
		debug.render(world, cam.combined);
		world.step(1/60f, 6, 2);
		
	}
	
	public void dispose(){
		debug.dispose();
	}
}
