package libgdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class PlayerPerson extends Actor {
    protected int healthPoints;
    protected int conversant;//Осознание;
    protected int lassitude;//Усталость;
    protected boolean player; // 0 - Enemy. 1 - Player
    Texture image;

    public PlayerPerson(int healthPoints, int conversant, int lassitude) {
        this.healthPoints = healthPoints;
        this.conversant = conversant;
        this.lassitude = lassitude;
        image = new Texture("card_back.png");
        setTouchable(Touchable.disabled);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getConversant() {
        return conversant;
    }
    public void setConversant(int conversant) {
        this.conversant = conversant;
    }
    public int getLassitude() {
        return lassitude;
    }
    public void setLassitude(int lassitude) {
        this.lassitude = lassitude;
    }
    public void setPlayer(boolean player){
        this.player = player;
    }
    public boolean isPlayer(){
        return player;
    }
}
