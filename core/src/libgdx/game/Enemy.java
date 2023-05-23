package libgdx.game;

public class Enemy extends PlayerPerson{
    public Enemy(int healthPoints, int conversant, int lassitude) {
        super(healthPoints, conversant, lassitude);
        player = false;
    }

    public void setStart() {
        this.healthPoints = 20;
        this.conversant = 4;
        this.lassitude = 0;
    }
}