package libgdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;

public class Starter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture deck_img;
	Texture card_img;
	float x,y;
	float directionX;
	float directionY;

	float currX, currY;



	MouseAdapter mouseAdapter1 = new MouseAdapter();

	private BitmapFont FontRed1;

	public class MouseAdapter extends InputAdapter {

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			currX = screenX;
			currY = Math.abs(1080 - screenY);
			return super.touchDown(screenX, screenY, pointer, button);
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
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		deck_img = new Texture("card_back.png");
		card_img = new Texture ("card_hearts_A.png");

		OrthographicCamera cam;
		float cameraWidth = 1920;
		float cameraHeight = 1080;
		cam = new OrthographicCamera(cameraWidth, cameraHeight);
		cam.position.set(1920, 1080,0);
		cam.update();

		batch = new SpriteBatch();

		FontRed1 = new BitmapFont();
		FontRed1.setColor(Color.RED); //Красный

		x = 0;
		y = 0;
	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, x, y);
		/*batch.draw(deck_img, 1700, 100);
		batch.draw(card_img, 1500, 100);
		batch.draw(card_img, 1300, 100);
		batch.draw(card_img, 1100, 100);
		batch.draw(card_img, 900, 100);
		batch.draw(card_img, 700, 100);*/
		Gdx.input.setInputProcessor(mouseAdapter1);
		FontRed1.draw(batch, "x = " + currX + " y = " + currY, currX, currY);

		batch.end();
		if (x >= 1920 || y >= 1080){
			directionX = -16f;
			directionY = -9f;
		}
		if (x <= 0 || y <= 0){
			directionX = 16f;
			directionY = 9f;
		}
		x += directionX;
		y += directionY;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
