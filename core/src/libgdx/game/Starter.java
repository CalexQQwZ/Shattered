package libgdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Starter extends ApplicationAdapter {
	StageNew stage;
	GameStart gameStart;
	@Override
	public void create () {
		stage = new StageNew();
		gameStart = new GameStart(stage);
		gameStart.createDeck();
		gameStart.createBeforeMatch();
		Gdx.input.setInputProcessor(stage);
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		stage.draw();
	}
	@Override
	public void dispose () {
		stage.dispose();
	}
}
