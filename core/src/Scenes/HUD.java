package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Sprites.Bros;

public class HUD {
	private Stage stage;
	private Viewport fixed;
	BitmapFont font;
	
	private Texture imgc;
	private Label characterLabel;
	private Label timeLabel;
	private static final String time = "TIME";
	private String characterName;
	private Image scores;
	
	private Table table;
	
	//IF PLAYER.getCharacter == MARIO. MAKE HUD FOR MARIO//
	//ELSE MAKE HUD FOR LUIGI//
	
	public HUD (MyGdxGame game){
		
		//Change this from static to dynamic allocation
		characterName = "MARIO";
		fixed = new FitViewport(game.width,game.height, new OrthographicCamera());
		stage = new Stage(fixed);
		
		//Font gen
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SuperMario256.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 16;
		parameter.color = Color.RED;
		parameter.shadowColor = Color.BLACK;
		parameter.shadowOffsetX = 2;
		parameter.shadowOffsetY = 2;
		BitmapFont font12 = generator.generateFont(parameter);
		generator.dispose();
      
        //HUD graphics
       LabelStyle ls = new LabelStyle();
       ls.font = font12;
       characterLabel = new Label(characterName,ls);
       timeLabel = new Label(time,ls);
       imgc = game.ASSLOAD.manager.get("GUI/coinHUD.png");
       scores = new Image(imgc);
       
       //table
       table = new Table();
       table.setFillParent(true);
       
       table.top();
       table.defaults().width(30);
       table.defaults().height(25);
       
       table.add(characterLabel).expandX().padLeft(50);
       table.add(timeLabel).expandX();
       table.add(scores).expandX().padRight(50);
       table.row();
       
       stage.addActor(table);
       
       //Stage
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void dispose(){
		stage.dispose();
	}
}
