package libgdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
public class Button extends Actor {
    Texture image;
    boolean player;
    public Button(boolean player) {
        image = new Texture("card_back.png");
        setTouchable(Touchable.enabled);
        this.player = player;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
    }
    public boolean isPlayer(){
        return player;
    }
    public void setImage(Texture texture){
        this.image = texture;
    }
}
