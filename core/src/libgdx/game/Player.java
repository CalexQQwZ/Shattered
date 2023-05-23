package libgdx.game;

public class Player extends PlayerPerson{

    private int comprehension;//Понимание;
    public Player(int healthPoints, int conversant, int lassitude, int comprehension) {
        super(healthPoints, conversant, lassitude);
        player = true;
        this.comprehension = comprehension;
    }

    public void setStart(){
        this.healthPoints = 20;
        this.conversant = 4;
        this.comprehension = 0;
        this.lassitude = 0;
    }
}