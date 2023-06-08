package libgdx.game;

public class Player extends PlayerPerson{

    private int comprehension;//Понимание;
    public Player(int healthPoints, int conversant, int lassitude, int comprehension) {
        super(healthPoints, conversant, lassitude);
        player = true;
        this.comprehension = comprehension;
    }


    public int getComprehension(){
        return comprehension;
    }
}