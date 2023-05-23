package libgdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Card extends Actor {
    private int healthPoints;
    private int damage;
    private int manaCost;
    private int turnCounter;
    private boolean player; // 0 - Enemy. 1 - Player
    private boolean onCooldown;
    private boolean fromHand;
    private boolean alive ;
    private boolean target;
    private boolean inHand;
    private boolean onTable;
    private int modificatorId;
    private int sigilId;
    private int effectId;
    Texture image;
    public Card(String name, boolean player ,int healthPoints, int damage, int manaCost, int modificatorId, int sigilId, int effectId) {
        super();
        this.player = player;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.manaCost = manaCost;
        this.modificatorId = modificatorId;
        this.sigilId = sigilId;
        this.effectId = effectId;
        onCooldown = true;
        fromHand = false;
        alive = true;
        target = false;
        inHand = false;
        onTable = false;
        turnCounter = 0;
        image = new Texture("card_hearts_A.png");
        setWidth(64);
        setHeight(64);
        setTouchable(Touchable.enabled);
    }
    public void setImage(Texture image){
        this.image = image;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
    }
    public boolean isOnCooldown() {
        return onCooldown;
    }
    public void setOnCooldown(boolean onCooldown) {
        this.onCooldown = onCooldown;
    }
    public boolean isFromHand() {
        return fromHand;
    }
    public void setFromHand(boolean fromHand) {
        this.fromHand = fromHand;
    }
    public boolean isAlive() {
        if (this.healthPoints <= 0){
            this.alive = false;
        }
        return alive;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getManaCost() {
        return manaCost;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
    public int getDamage() {
        return damage;
    }
    public int getTurnCounter() {
        return turnCounter;
    }
    public void incTurnCounter() {
        this.turnCounter += 1;
    }
    public boolean getTarget() {
        return target;
    }
    public void setTarget(boolean target) {
        this.target = target;
    }
    public int getModificatorId() {
        return modificatorId;
    }
    public void takeInHand(){
        onTable = false;
        inHand = true;
    }
    public void playOnTable(){
        onTable = true;
        inHand = false;
    }
    public boolean isInHand(){
        return inHand;
    }
    public boolean isOnTable(){
        return onTable;
    }
    public boolean isPlayer(){
        return player;
    }
}