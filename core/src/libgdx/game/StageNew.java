package libgdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageNew extends Stage {
    public StageNew() {
        super();
    }

    private Vector2 tempCoords = new Vector2();
    private float distX, distY;
    Actor target;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenToStageCoordinates(tempCoords.set(screenX, screenY));
        target = hit(tempCoords.x, tempCoords.y, true);
        target.setWidth(86);
        target.setHeight(86);
        distX = tempCoords.x - target.getX();
        distY = tempCoords.y - target.getY();
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        target.setWidth(64);
        target.setHeight(64);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenToStageCoordinates(tempCoords.set(screenX, screenY));
        if (target != null){
            target.setX(tempCoords.x - distX);
            target.setY(tempCoords.y - distY);
        }
        return super.touchDragged(screenX, screenY, pointer);
    }
}
