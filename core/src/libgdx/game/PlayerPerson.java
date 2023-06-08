package libgdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class PlayerPerson extends Actor {
    protected int healthPoints;
    protected int conversant;//Осознание;
    protected int lassitude;//Усталость;
    protected boolean player; // 0 - Enemy. 1 - Player
    protected int playerId;
    private Texture image;
    private BitmapFont fontHp;
    private BitmapFont fontMana;
    public PlayerPerson(int healthPoints, int conversant, int lassitude) {
        this.healthPoints = healthPoints;
        this.conversant = conversant;
        this.lassitude = lassitude;
        setTouchable(Touchable.disabled);
        fontHp = new BitmapFont();
        fontMana = new BitmapFont();
        fontHp.setColor(Color.GREEN);
        fontMana.setColor(Color.BLUE);
        fontHp.getData().setScale(1);
        fontMana.getData().setScale(1);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        fontHp.draw(batch, Integer.toString(healthPoints) ,getX()+90,getY()+50);
        fontMana.draw(batch, Integer.toString(conversant) ,getX()+90,getY()+30 );
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
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public void setPlayer(boolean player){
        this.player = player;
    }
    public boolean isPlayer(){
        return player;
    }
    public void setImage(Texture texture) {
        image = texture;
    }
}
