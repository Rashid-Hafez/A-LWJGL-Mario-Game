package Scenes;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Screens.Menu;
import Screens.Play;

public class MenuGui implements ApplicationListener {
	
	private Menu main;
	
	public Stage stage;
	private Skin skin;
	private Skin mar;
	private Skin lui;
	
	private TextureAtlas atlas;
	private TextureAtlas marioButt;
	private TextureAtlas luigiButt;
	
	private Viewport fixed;
	private static final String menu = "SUPER MARIO:";
	private static final String end = "THE LOST TALE";
	
	private Label menuLabel;
	private Label endMenu;
	private TextButton playLabel;
	private TextButton loadLabel;
	private TextButton playMARIO;
	private TextButton playLUIGI;
	
	private Table table;
	private Window mWin;
	private Window lWin;
	private Table Mchar;
	private Table Lchar;
	
	private int var;
	MyGdxGame game;
	
	//Background
	private Texture bg;
	
	//smart way to render animations from this class NOT the menu class...
	//pass the menu.class parameter to this method.
	public MenuGui(MyGdxGame game, Menu men){
		var = 0;
		this.game = game;
		this.main = men;
		fixed = new FitViewport(game.width,game.height, new OrthographicCamera());
		stage = new Stage(fixed);
		
		//Background Image begin:
		bg= game.ASSLOAD.manager.get("bgImage.png");
		Image image = new Image(bg);
		image.setScale(0.39f);
		image.setX(350);
		//stage.addActor(image);
		
		
		//Menu Begin
		//font bin
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SuperMario256.ttf"));
		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		FreeTypeFontParameter parameter3 = new FreeTypeFontParameter();
		FreeTypeFontParameter parameter4 = new FreeTypeFontParameter();
		
		parameter1.size = 20;
		parameter1.shadowColor = Color.LIGHT_GRAY;
		parameter1.shadowOffsetX = 2;
		parameter1.shadowOffsetY = 3;
		parameter1.spaceX = 2;
		parameter1.color = Color.BLACK;

		parameter2.size = 40;
		parameter2.spaceX = 4;
		parameter2.color = Color.RED;
		parameter2.borderColor = Color.ROYAL;
		parameter2.borderWidth = 1.5f;
		parameter2.gamma = 10;
		parameter2.shadowColor = Color.BLACK;
		parameter2.shadowOffsetX = 6;
		parameter2.shadowOffsetY = 4;
		
		parameter4.size = 40;
		parameter4.borderColor = Color.BLACK;
		parameter4.borderWidth = 1.5f;
		parameter4.shadowColor = Color.BLACK;
		parameter4.spaceX = 2;
		parameter4.spaceY = 2;
		parameter4.gamma = 30;
		parameter4.shadowOffsetX = 6;
		parameter4.shadowOffsetY = 4;
		parameter4.color = Color.GOLDENROD;
		
		parameter3.size = 18;
		parameter3.shadowColor = Color.BLACK;
		parameter3.shadowOffsetX = 2;
		parameter3.shadowOffsetY = 2;
		
		
		BitmapFont buttonFONT = generator.generateFont(parameter1);
		BitmapFont titleFont1 = generator.generateFont(parameter2);
		BitmapFont charFont = generator.generateFont(parameter3);
		BitmapFont titleFont2 = generator.generateFont(parameter4);
		
		generator.dispose();
		//end freetype generation
		
        skin = new Skin();
        atlas = game.ASSLOAD.manager.get("GUI/ui-white.atlas");
        
        skin.addRegions(atlas);
        
        //Self explanatory... MENU
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = buttonFONT;
        textButtonStyle.up = skin.getDrawable("button_04");
        textButtonStyle.down = skin.getDrawable("button_03");
        playLabel = new TextButton("Play", textButtonStyle);
        loadLabel = new TextButton("Load", textButtonStyle);
       
        LabelStyle ls = new LabelStyle();
        ls.font = titleFont1;
        
        LabelStyle lastLabel = new LabelStyle();
        lastLabel.font = titleFont2;
        
		menuLabel = new Label(menu,ls);
		endMenu = new Label(end,lastLabel);

		table = new Table();
		table.setFillParent(true);
		table.center();
		table.defaults().width(250);
		table.defaults().height(50);
		table.add(menuLabel).expandX().padRight(250);
		table.row();
		table.add(endMenu).expandX().align(Align.center).padBottom(50).padRight(100);
		table.row();
		table.add(playLabel).expandX().pad(10);
		table.row();
		table.add(loadLabel).expandX().pad(10);
		//end of design
			
		stage.addActor(table);
		//MENU END
		
	//********************************************************************************\\
		//"CHOOSE A GAME!" begin
		mar = new Skin();
		lui = new Skin();
		marioButt = game.ASSLOAD.manager.get("GUI/ui-red.atlas");
		luigiButt = game.ASSLOAD.manager.get("GUI/ui-green.atlas");
		mar.addRegions(marioButt);
		lui.addRegions(luigiButt);
		
		//style windows
		WindowStyle wm = new WindowStyle();
		wm.background = mar.getDrawable("window_01");
		wm.titleFont = charFont;
		mWin = new Window("", wm);
		
		WindowStyle wl = new WindowStyle();
		wl.background = lui.getDrawable("window_01");
		wl.titleFont = charFont;
		lWin = new Window("",wl);
		
		//style buttons
		TextButtonStyle m = new TextButtonStyle();
		m.font=charFont;
		m.up= mar.getDrawable("button_04");
		m.down= mar.getDrawable("button_03");
		TextButtonStyle l = new TextButtonStyle();
		l.font = charFont;
		l.up = lui.getDrawable("button_04");
		l.down = lui.getDrawable("button_03");
		
		playMARIO = new TextButton("MARIO",m);
		playLUIGI = new TextButton("LUIGI",l);
		
		playMARIO.addListener(new ClickListener() {

		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		    	main.mario = true;
		    }
		});
		
		playLUIGI.addListener(new ClickListener() {

		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("LUIGI");
		    }
		});		
		
		
		/**************************************************
		 * ************************************************
		 * Add to CHARACTER TABLES|||||||||||||||||||||||||||||||||||
		 * ************************************************
		 * *************************************************
		 */
			Mchar = new Table();
		Mchar.setFillParent(true);
		Mchar.left().setX(50);
		Mchar.setY(-50);
		Mchar.add(mWin).fill().width(300).height(250).top();
		Mchar.row();
		Mchar.add(playMARIO).padTop(10);
		/*
		 * 
		 * Luigi Table
		 * 
		 */
		Lchar = new Table();
		Lchar.setFillParent(true);
		Lchar.right().setX(-50);
		Lchar.setY(-50);
		Lchar.add(lWin).fill().width(300).height(250).top();
		Lchar.row();
		Lchar.add(playLUIGI).padTop(10);
		
		stage.addActor(Mchar);
		stage.addActor(Lchar);
		Lchar.setVisible(false);
		Mchar.setVisible(false);
		
//****************************************************************************//		
		//ANIMATIONS
		
		
		playLabel.addListener(new ClickListener() {

		    @Override
		    public void clicked(InputEvent event, float x, float y) {
		        System.out.println("Clicked");
		        var++;
		        if (var%2==1){
		        	System.out.println("SHOULD SHOW");
		        	render();
		        choose();
		        }
		        if(var%2==0){
		        	goAway();
		        }
		    }

		});	
		
	}
	
	//public Animation<TextureRegion> getMarioMove(){
		//return (mario);
	//}
	
	//I wanna access the textButton methods
	public TextButton getPlay(){
		return playLabel;
	}
	public TextButton getLoad(){
		return loadLabel;
	}
	
	public TextButton getLuigi(){
		return playLUIGI;
	}
	
	public TextButton getMario(){
		return playMARIO;
	}
	
	private void choose(){
		Mchar.setVisible(true);
		Lchar.setVisible(true);
		main.var = true;

		}
		
		
		/*while(var%2==1){
			for(int i=2; i<8;i++){
				Mchar.removeActor(img);
				img = new Image(mSkin.getDrawable("mario"+i));
				Mchar.add(img);
			}
			*/
	
	private void goAway(){
		Mchar.setVisible(false);
		Lchar.setVisible(false);
		main.var=false;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
