package libgdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class Starter extends ApplicationAdapter {
	StageNew stage;
	Group group;
	Card card1;
	Card card2;
	Card card3;
	Card card4;
	Card card5;
	SpriteBatch batch;
	Texture img;
	Texture deck_img;
	Texture card_img;
/*	public class MouseAdapter extends InputAdapter {

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			Actor hitCard = stage.hit(screenX,screenY,false);
			if (hitCard != null) {
				System.out.println("we hit");
				return true;
			}
			else{
				System.out.println("we missed");
				return false;
			}
			//return super.touchDown(screenX, screenY, pointer, button);
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {

			return super.touchUp(screenX, screenY, pointer, button);
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			return super.touchDragged(screenX, screenY, pointer);
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return super.mouseMoved(screenX, screenY);
		}
	}*/


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		deck_img = new Texture("card_back.png");
		card_img = new Texture ("card_hearts_A.png");

		stage = new StageNew();
		group = new Group();
		card1 = new Card(card_img);
		card2 = new Card(card_img);
		card3 = new Card(card_img);
		card4 = new Card(card_img);
		card5 = new Card(card_img);
		card1.setPosition(400,250);
		card2.setPosition(600,250);
		card3.setPosition(800,250);
		card4.setPosition(1000,250);
		card5.setPosition(1200,250);
		group.addActor(card1);
		group.addActor(card2);
		group.addActor(card3);
		group.addActor(card4);
		group.addActor(card5);
		stage.addActor(group);
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();

	}


	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
		img.dispose();
	}
}
