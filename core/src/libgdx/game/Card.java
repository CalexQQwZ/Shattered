package libgdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont fontHp;
    private BitmapFont fontMana;
    private BitmapFont fontDamage;
    private BitmapFont fontCooldown;
    public Card(String name, boolean player ,int healthPoints, int damage, int manaCost, int modificatorId, int sigilId, int effectId, Texture texture) {
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
        image = texture;
        setWidth(64);
        setHeight(72);
        setTouchable(Touchable.enabled);
        bitmapCreate();
    }
    public Card(Card oldCard){
        super();
        this.player = oldCard.player;
        this.healthPoints = oldCard.healthPoints;
        this.damage = oldCard.damage;
        this.manaCost = oldCard.manaCost;
        this.modificatorId = oldCard.modificatorId;
        this.sigilId = oldCard.sigilId;
        this.effectId = oldCard.effectId;
        onCooldown = true;
        fromHand = false;
        alive = true;
        target = false;
        inHand = false;
        onTable = false;
        turnCounter = 0;
        image = oldCard.image;
        setWidth(64);
        setHeight(72);
        setTouchable(Touchable.enabled);
        bitmapCreate();
    }
    public void bitmapCreate(){
        fontHp = new BitmapFont();
        fontMana = new BitmapFont();
        fontDamage = new BitmapFont();
        fontCooldown = new BitmapFont();

        fontHp.setColor(Color.GREEN);
        fontMana.setColor(Color.BLUE);
        fontDamage.setColor(Color.RED);
        fontCooldown.setColor(Color.CORAL);

        fontHp.getData().setScale(1);
        fontMana.getData().setScale(1);
        fontDamage.getData().setScale(1);
        fontCooldown.getData().setScale(1);
    }
    public void setImage(Texture image){
        this.image = image;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        fontHp.draw(batch, Integer.toString(healthPoints) ,getX()+74,getY()+10);
        fontMana.draw(batch, Integer.toString(manaCost) ,getX()+74,getY()+30 );
        fontDamage.draw(batch, Integer.toString(damage),getX()+74,getY()+50);
        fontCooldown.draw(batch, String.valueOf(onCooldown),getX()+74,getY()+70);
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
    public void setPlayer(boolean player){
        this.player = player;
    }
}