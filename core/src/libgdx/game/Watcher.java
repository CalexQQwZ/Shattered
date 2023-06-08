package libgdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Watcher extends Actor {
    private Texture image;
    private BitmapFont fontMessage;
    private String message;
    public Watcher() {
        setTouchable(Touchable.enabled);
        image = new Texture("dialog.jpg");
        fontMessage = new BitmapFont();
        fontMessage.setColor(Color.TEAL);
        fontMessage.getData().setScale(3);
        this.message = "";
    }

    public void setFontMessage(String message) {
        this.message = message ;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        fontMessage.draw(batch, message ,880,580);
    }
}
