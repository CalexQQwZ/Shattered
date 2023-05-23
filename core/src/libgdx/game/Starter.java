package libgdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.ScreenUtils;

public class Starter extends ApplicationAdapter {
	StageNew stage;
	GameLoop gameLoop;
	Card playingTable;
	Button button1, button2;
	SpriteBatch batch;
	Texture img, deck_img, card_img;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		deck_img = new Texture("card_back.png");
		card_img = new Texture ("card_hearts_A.png");

		stage = new StageNew();
		gameLoop = new GameLoop(stage);
		playingTable = new Card("table",true,0,0,0,0,0,0);
		playingTable.setBounds(200,160,1520,760);
		playingTable.setImage(img);
		playingTable.setName("table");
		playingTable.setTouchable(Touchable.disabled);
		button1 = new Button(false);
		button1.setBounds(1800,860,100,100);
		button1.setName("button");
		button2 = new Button(true);
		button2.setBounds(1800,160,100,100);
		button2.setName("button");
		stage.addActor(playingTable);
		stage.addActor(button1);
		stage.addActor(button2);

		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();

		gameLoop.startMatch();
	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		stage.act(30);
		//System.out.println(gameLoop.player.conversant);
		//System.out.println(gameLoop.enemy.conversant);
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
		img.dispose();
	}
}
