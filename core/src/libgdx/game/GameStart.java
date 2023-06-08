package libgdx.game;

import com.badlogic.gdx.utils.Timer;

public class GameStart {
    StageNew stage;
    Player player;
    Enemy enemy;
    Table table;
    Deck deck;
    Button button1;
    Button button2;
    DialogButton dialogButton0;
    DialogButton dialogButton1;
    DialogButton dialogButton2;
    DialogButton dialogButton3;
    DialogButton dialogButton4;
    DialogButton dialogButton5;
    DialogButton dialogButton6;
    Watcher watcher;
    TouchHandler touchHandler;
    //GameLogic gameLogic;
    int comprehension;
    public GameStart(StageNew stage) {
        this.stage = stage;
    }
    public void createDeck(){
        deck = new Deck();
        deck.createDeck();
    }
    public void createBeforeMatch(){
        player = new Player(30,4,0,comprehension);
        enemy = new Enemy(30,4,0);
        table = new Table(stage, this, player, enemy, deck);
        touchHandler = new TouchHandler(stage, table);
        button1 = new Button(false);
        button2 = new Button(true);
        //gameLogic = new GameLogic();

        stage.setTouchHandler(touchHandler);

        table.setName("table");
        player.setName("player");
        enemy.setName("enemy");
        button1.setName("button");
        button2.setName("button");

        table.setBounds(200,160,1520,760);
        player.setBounds(910,0,80,100);
        enemy.setBounds(910,980,80,100);
        button1.setBounds(1800,860,100,100);
        button2.setBounds(1800,160,100,100);
        //
        dialogButton0 = new DialogButton("Start",0);
        dialogButton1 = new DialogButton("I'm teacher of Java",1);
        dialogButton2 = new DialogButton("I'm Java game developer",2);
        dialogButton3 = new DialogButton("I'm Genshin Player",3);
        dialogButton4 = new DialogButton("Meow",4);
        dialogButton5 = new DialogButton("You wanna fight against Russian",5);
        dialogButton6 = new DialogButton("You wanna fight against BinouralniyBegemot",6);

        dialogButton0.setName("dialogbutton");
        dialogButton1.setName("dialogbutton");
        dialogButton2.setName("dialogbutton");
        dialogButton3.setName("dialogbutton");
        dialogButton4.setName("dialogbutton");
        dialogButton5.setName("dialogbutton");
        dialogButton6.setName("dialogbutton");

        dialogButton0.setBounds(900,540, 110,80);
        dialogButton1.setBounds(800,900, 500,80);
        dialogButton2.setBounds(800,700, 500,80);
        dialogButton3.setBounds(800,500, 500,80);
        dialogButton4.setBounds(800,300, 500,80);
        dialogButton5.setBounds(600,800, 900,80);
        dialogButton6.setBounds(600,600, 900,80);
        //
        watcher = new Watcher();
        watcher.setBounds(1600,20,100,100);
        watcher.setName("watcher");

        stage.clear();
        stage.addActor(dialogButton0);

    }
    public void answerTheQuestions1(){
        stage.clear();
        stage.addActor(dialogButton1);
        stage.addActor(dialogButton2);
        stage.addActor(dialogButton3);
        stage.addActor(dialogButton4);
    }
    public void answerTheQuestions2(){
        stage.clear();
        stage.addActor(dialogButton5);
        stage.addActor(dialogButton6);
    }
    public void startBeforeMatch(){
        stage.clear();
        stage.addActor(table);
        stage.addActor(player);
        stage.addActor(enemy);
        stage.addActor(button1);
        stage.addActor(button2);
        //
        stage.addActor(watcher);
        //
        deck.addCardPlayer(player.getPlayerId());
        deck.addCardEnemy(enemy.getPlayerId());
        table.createDeck();

        Timer timer = new Timer();
        Timer.Task timerTask = new Timer.Task() {
            @Override
            public void run() {
                table.endMatch();
            }
        };
        timer.scheduleTask(timerTask, 300);


    }
}