package libgdx.game;

public class Enemy extends PlayerPerson{
    public Enemy(int healthPoints, int conversant, int lassitude) {
        super(healthPoints, conversant, lassitude);
        player = false;
    }

}