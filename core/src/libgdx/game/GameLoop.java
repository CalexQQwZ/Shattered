package libgdx.game;

public class GameLoop {
    StageNew stage;
    Player player;
    Enemy enemy;
    Table table;
    public GameLoop(StageNew stage) {
        this.stage = stage;
    }

    public void startMatch(){
        player = new Player(30,4,0,0);
        enemy = new Enemy(30,4,0);
        player.setBounds(910,0,100,100);
        enemy.setBounds(910,980,100,100);
        player.setName("player");
        enemy.setName("enemy");
        stage.addActor(player);
        stage.addActor(enemy);
        GameLogic gameLogic = new GameLogic();
        table = new Table(stage, player, enemy);
        table.startBeforeMatch(player, enemy);
        stage.setTable(table);
    }
    public boolean checkEndMatch(){
        return player.getHealthPoints() <= 0 || enemy.getHealthPoints() <= 0;
    }
}