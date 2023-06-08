package libgdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class DialogButton extends Actor {
    private BitmapFont fontMessage;
    private int id;
    private String message;
    Texture image;
    public DialogButton(String message, int id) {
        this.message = message;
        this.id = id;
        setTouchable(Touchable.enabled);
        fontMessage = new BitmapFont();
        fontMessage.setColor(Color.WHITE);
        fontMessage.getData().setScale(3);
        image = new Texture("dialogbutton.jpg");
    }
    public int getId(){
        return id;
    }
    public BitmapFont getFontMessage(){
        return fontMessage;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        fontMessage.draw(batch, message,getX()+5,getY()+50);
    }
}
