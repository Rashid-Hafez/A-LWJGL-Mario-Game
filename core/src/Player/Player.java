package Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Sprites.Bros;

//ON MAIN MENU, IF MARIO CLICKED THEN
//private Player PLAYER = NEW MARIO();

//MARIO EXTENDS PLAYER.. PLAYER FUNCTIONS == WHEN RIGHT CLICKED MOVE RIGHT
//MARIO FUNCTIONS ARE HOW FAST HE MOVES AND THE SPRITE SHEET HE LOOKS LIKE

public class Player extends Sprite{
	private Bros bro;
	private World world;
	private Body body;
	
	public Player(World world){
		this.world = world;
		
		//if something... then:
		bro = new Bros();
		bro.type = bro.type.Mario;
		
		createChar(bro);
		
		//ELSE
		/*
		 * bro.type = bro.type.Luigi
		 * createChar(bro);
		 */
	}
	
	//CREATES THE FIXTURE
	private void createChar(Bros bro){ //mario or luigi
		
		BodyDef def = new BodyDef();
		def.position.set(100/100f,100/100f);
		def.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(def);
		
		//Fixtures
		FixtureDef fdef = new FixtureDef();
		CircleShape radius = new CircleShape();
		radius.setRadius((10/100f)*bro.type.getSize());
		fdef.shape=radius;
		body.createFixture(fdef);
		
	}
	
	//setup struct for states, can do if state get config for velocity.
	private enum playerState{
		IsStanding(0,0), InAir(1,0), CrouchState(2,0), WaterState(3,0.556647f),
		Right(4,0), Left(5,0);
		private int state;
		private float configs;
		
		private playerState(int state, float yo){
			this.state = state;
			configs = yo;
		}
		
		public int getState(){
			return state;
		}
		
		public float getConfigs(){
			return configs;
		}
		
	};
	
	public playerState playState = Player.playerState.IsStanding;
	
	public void Actions(float delta){
		//If Up
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
		body.applyForce(new Vector2(0,119.289f), body.getWorldCenter(), true);
		}
		//if down
		
		//if left
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && body.getLinearVelocity().x >= -1.569f){
			body.applyForce(new Vector2(-1.885f,0), body.getWorldCenter(), true);
		}
		
		//if right
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x <= 1.569f){
			body.applyForce(new Vector2(1.885f,0), body.getWorldCenter(), true);
		}

		//TODO: IF SPACE PRESSED THEN SHOOT
		
		//TODO: if InAir && Gdx.input DownKey.IsPressed GROUNDPOUND
		/**
		 * TODO: Make ENUM States: InAir State, WaterState, CrouchState
		 * TODO: While(player.State = player.State.inAir){
		 * TODO: if (Gdx.Input.down.JustPressed){
		 * TODO: body.applyForce(new Vector2(0,-50.5f);
		 * }
		 */
	}
	
	public Body getBody(){
		return body;
	}
}
