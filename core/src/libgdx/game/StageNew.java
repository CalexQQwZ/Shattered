package libgdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageNew extends Stage {
    public StageNew() {
        super();
    }
    Table table;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        table.touchDownHandler(screenX,screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        table.touchUpHandler(screenX,screenY);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        table.touchDraggedHandler(screenX,screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }
    public void setTable(Table table) {
        this.table = table;
    }
}
