package libgdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Starter extends ApplicationAdapter {
	StageNew stage;
	GameLoop gameLoop;
	@Override
	public void create () {
		stage = new StageNew();
		gameLoop = new GameLoop(stage);
		gameLoop.createDeck();
		gameLoop.createBeforeMatch();
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
