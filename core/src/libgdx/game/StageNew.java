package libgdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageNew extends Stage {
    public StageNew() {
        super();
    }
    TouchHandler touchHandler;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchHandler.touchDownHandler(screenX,screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchHandler.touchUpHandler(screenX,screenY);
        return super.touchUp(screenX, screenY, pointer, button);
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchHandler.touchDraggedHandler(screenX,screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }
    public void setTouchHandler(TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }
}
