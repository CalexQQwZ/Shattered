package libgdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Table extends Actor{
    Deck deck;
    Player player;
    Enemy enemy;
    PlayerPerson currentPlayer;
    private ArrayList<Card> enemyDeck = new ArrayList<>();
    private ArrayList<Card> enemyHand = new ArrayList<>();
    private ArrayList<Card> enemyTable = new ArrayList<>();
    private ArrayList<Card> playerDeck = new ArrayList<>();
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> playerTable = new ArrayList<>();
    private final StageNew stage;
    private final GameLoop gameLoop;
    private Vector2 tempCoords = new Vector2();
    private float distX, distY, posX, posY;
    Actor target;
    Card targetCardOld, targetCardNew;
    Button endTurnButton;
    PlayerPerson attackedPerson;
    DialogButton dialogButton;
    Texture image;
    Random random;
    int retakeCards;
    int turncounter;
    public Table(StageNew stage, GameLoop gameLoop, Player player, Enemy enemy, Deck deck) {
        this.player = player;
        this.enemy = enemy;
        currentPlayer = player;
        this.stage = stage;
        this.gameLoop = gameLoop;
        this.deck = deck;
        setTouchable(Touchable.disabled);
        image = new Texture("nustol.jpg");
        random = new Random();
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
    }
    public void setCoordinateHandPlayer(Card card) {
        float x = 710;
        while (x <= 1110) {
            if (stage.hit(x, 180, true) == null) {
                card.setPosition(x, 180);
            }
            x += 100;
        }
    }
    public void setCoordinateHandEnemy(Card card){
            float x = 710;
            while (x <= 1110){
                if (stage.hit(x,800,true) == null){
                    card.setPosition(x,800);
                }
                x += 100;
            }
    }
    public void setCoordinateTablePlayer(Card card){
        float x = 400;
        while (x <= 1420) {
            if (stage.hit(x, 280, true) == null) {
                card.setPosition(x, 280);
            }
            x += 224;
        }
    }
    public void setCoordinateTableEnemy(Card card){
        float x = 400;
        while (x <= 1420) {
            if (stage.hit(x, 700, true) == null) {
                card.setPosition(x, 700);
            }
            x += 224;
        }
    }
    public void createDeck(){
        for (int i = 0; i < 10; i++){
            Card card1 = new Card(deck.getCardFromPlayerDeck());
            Card card2 = new Card(deck.getCardFromEnemyDeck());
            if(playerDeck.size() < 10){
                card1.setName("card");
                card2.setName("card");
                playerDeck.add(card1);
                enemyDeck.add(card2);
                turncounter = 1;
            }
        }
        takeCardFromDeck();takeCardFromDeck();takeCardFromDeck();takeCardFromDeck();
    }
    public boolean isCardDrawable(Card card){
            return( (currentPlayer.getConversant() - card.getManaCost()) >=0);
    }
    public boolean isCardAttack(Card card) {
            return (!card.isOnCooldown());
    }
    public boolean isCardsAttackable(Card card) {
        boolean provocation = false;
        if(card.isOnTable()){
            if (!currentPlayer.isPlayer()) {
                for (Card cardI : playerTable) {
                    if (cardI.getTarget()) {
                        provocation = true;
                    }
                }
                return !provocation;
            }
            else {
                for (Card cardI : enemyTable) {
                    if (cardI.getTarget()) {
                        provocation = true;
                    }
                }
                return !provocation;
            }
        }
        else{
            return false;
        }
    }
    public void takeCardFromDeck(){
        if (!currentPlayer.isPlayer()) {
            if (enemyHand.size() < 5) {
                if (enemyDeck.isEmpty()) {
                    Card randomCard1 = new Card(deck.getCardFromEnemyDeck());
                    enemyHand.add(randomCard1);
                    randomCard1.setName("card");
                    randomCard1.takeInHand();
                    currentPlayer.setLassitude(currentPlayer.getLassitude() + 1);
                    setCoordinateHandEnemy(randomCard1);
                    stage.addActor(randomCard1);
                } else {
                    Card newCard1 = enemyDeck.remove(enemyDeck.size() - 1);
                    enemyHand.add(newCard1);
                    newCard1.takeInHand();
                    setCoordinateHandEnemy(newCard1);
                    stage.addActor(newCard1);
                }
            }
        }
        else{
            if(playerHand.size() < 5){
                if (playerDeck.isEmpty()){
                    Card randomCard2 = new Card(deck.getCardFromPlayerDeck());
                    playerHand.add(randomCard2);
                    randomCard2.setName("card");
                    randomCard2.takeInHand();
                    currentPlayer.setLassitude(currentPlayer.getLassitude()+1);
                    setCoordinateHandPlayer(randomCard2);
                    stage.addActor(randomCard2);
                }
                else{
                    Card newCard2 = playerDeck.remove(playerDeck.size()-1);
                    playerHand.add(newCard2);
                    newCard2.takeInHand();
                    setCoordinateHandPlayer(newCard2);
                    stage.addActor(newCard2);
                }
            }
        }
    }
    public void checkDiedAll(){
        Iterator<Card> iterator1 = enemyTable.iterator();
        Iterator<Card> iterator2 = playerTable.iterator();
        Card cardI;
        while (iterator1.hasNext()) {
            cardI = iterator1.next();
            if (!cardI.isAlive()) {
                cardI.remove();
                iterator1.remove();
            }
        }
        while (iterator2.hasNext()) {
            cardI = iterator2.next();
            if (!cardI.isAlive()) {
                cardI.remove();
                iterator2.remove();
            }
        }
    }
    public void attackCard(Card attackingCard, Card attackedCard) {
        attackedCard.setHealthPoints(attackedCard.getHealthPoints() - attackingCard.getDamage());
        attackingCard.setHealthPoints(attackingCard.getHealthPoints() - attackedCard.getDamage());
        attackingCard.setOnCooldown(true);
    }
    public void attackPlayer(Card attackingCard, PlayerPerson attackedPerson){
        attackedPerson.setHealthPoints(attackedPerson.getHealthPoints() - attackingCard.getDamage());
        attackingCard.setOnCooldown(true);
    }
    public void switchCurrentPlayer(){
        if(currentPlayer.isPlayer()){
            currentPlayer = enemy;
        }
        else{
            currentPlayer = player;
        }
    }
    public void endTurn(){
        turncounter += 1;
        switchCurrentPlayer();
        if(!currentPlayer.isPlayer() && !enemyTable.isEmpty()){
            for (Card cardI:enemyTable) {
                cardI.setOnCooldown(false);
                cardI.incTurnCounter();
            }
        }
        if(currentPlayer.isPlayer() && !playerTable.isEmpty()){
            for (Card cardI : playerTable) {
                cardI.setOnCooldown(false);
                cardI.incTurnCounter();
            }
        }
        checkDiedAll();
        if(currentPlayer.getConversant() <=7){
            currentPlayer.setConversant(currentPlayer.getConversant() + 3);
        }
        else{
            currentPlayer.setConversant(10);
        }
        takeCardFromDeck();
    }
    public void touchDownHandler(int screenX, int screenY){
        stage.screenToStageCoordinates(tempCoords.set(screenX, screenY));
        target = stage.hit(tempCoords.x, tempCoords.y, true);
        targetCardOld = null;
        targetCardNew = null;
        endTurnButton = null;
        attackedPerson = null;
        dialogButton = null;
        if(target != null && target.getName().equals("card") ) {
            targetCardOld = (Card) target;
            if (targetCardOld.isPlayer() == currentPlayer.isPlayer()) {
                posX = targetCardOld.getX();
                posY = targetCardOld.getY();
                targetCardOld.setWidth(86);
                targetCardOld.setHeight(86);
                distX = tempCoords.x - targetCardOld.getX();
                distY = tempCoords.y - targetCardOld.getY();
            }
            else {
                targetCardOld = null;
            }
        }
        else if(target != null && target.getName().equals("button") ) {
            endTurnButton = (Button) target;
            if (endTurnButton.isPlayer() == currentPlayer.isPlayer()) {
                endTurnButton.setWidth(110);
                endTurnButton.setHeight(110);
            } else {
                endTurnButton = null;
            }
        } else if (target != null && target.getName().equals("dialogbutton") ) {
            dialogButton = (DialogButton) target;
            dialogButtonHandler();
        }
    }
    public void touchUpHandler(int screenX, int screenY) {
        if (targetCardOld != null && targetCardOld.getName().equals("card") && retakeCards < 4 && currentPlayer.isPlayer() && turncounter<2 ){
            newRandomCard();
        }
        if (targetCardOld != null && targetCardOld.getName().equals("card")) {
            targetCardOld.setWidth(64);
            targetCardOld.setHeight(72);
            targetCardOld.setX(posX);
            targetCardOld.setY(posY);
            target = stage.hit(tempCoords.x, tempCoords.y, false);
            if (target != null && target.getName().equals("card")) {
                targetCardNew = (Card) target;
                attackCardUnderMouse();
            } else if (target != null && target.getName().equals("table")) {
                playOnTableUnderMouse(screenX, screenY);
            } else if (target != null && (target.getName().equals("player") || target.getName().equals("enemy"))) {
                attackedPerson = (PlayerPerson) target;
                attackPlayerUnderMouse();
            }
        } else if (endTurnButton != null && endTurnButton.getName().equals("button") && (endTurnButton.isPlayer() == currentPlayer.isPlayer())) {
            endTurnButton.setWidth(100);
            endTurnButton.setHeight(100);
            endTurn();
        }
    }
    public void touchDraggedHandler(int screenX, int screenY) {
        stage.screenToStageCoordinates(tempCoords.set(screenX, screenY));
        if (targetCardOld != null && targetCardOld.getName().equals("card") ) {
            targetCardOld.setX(tempCoords.x - distX);
            targetCardOld.setY(tempCoords.y - distY);
        }
    }
    public void attackCardUnderMouse() {
        if (targetCardNew.getName().equals("card") && targetCardOld.isOnTable() && isCardAttack(targetCardOld) && isCardsAttackable(targetCardNew) &&
                (targetCardOld.isPlayer() != targetCardNew.isPlayer()) ) {
            attackCard(targetCardOld, targetCardNew);
            checkDiedAll();
        }
    }
    public void attackPlayerUnderMouse() {
        if (attackedPerson.getName().equals("player") && !targetCardOld.isPlayer() && targetCardOld.isOnTable() && isCardAttack(targetCardOld)) {
            attackPlayer(targetCardOld,player);
        } else if (attackedPerson.getName().equals("enemy") && targetCardOld.isPlayer() && targetCardOld.isOnTable() && isCardAttack(targetCardOld)) {
            attackPlayer(targetCardOld,enemy);
        }
        if(attackedPerson.getHealthPoints() <= 0){
            endMatch();
        }
    }
    public void playOnTableUnderMouse(int screenX, int screenY) {
        if (target.getName().equals("table") && screenX > 300 && screenX < 1500 && screenY > 280 && screenY < 800 && targetCardOld.isInHand() && isCardDrawable(targetCardOld)) {
            if (targetCardOld.isPlayer() && playerTable.size() < 5) {
                setCoordinateTablePlayer(targetCardOld);
                playerTable.add(targetCardOld);
                playerHand.remove(targetCardOld);
                targetCardOld.playOnTable();
                targetCardOld.setFromHand(true);
                currentPlayer.setConversant(currentPlayer.getConversant() - targetCardOld.getManaCost());
            } else if (!targetCardOld.isPlayer() && enemyTable.size() < 5) {
                setCoordinateTableEnemy(targetCardOld);
                enemyTable.add(targetCardOld);
                enemyHand.remove(targetCardOld);
                targetCardOld.playOnTable();
                targetCardOld.setFromHand(true);
                currentPlayer.setConversant(currentPlayer.getConversant() - targetCardOld.getManaCost());
            }
        }
    }
    public void newRandomCard(){
        targetCardOld.remove();
        playerHand.remove(targetCardOld);
        takeCardFromDeck();
        retakeCards += 1;
    }
    public void dialogButtonHandler(){
        Texture texture;
        switch (dialogButton.getId()){
            case 0 :
                gameLoop.answerTheQuestions1();
                break;
            case 1 :
                texture = new Texture("you_1.jpg");
                player.setImage(texture);
                gameLoop.answerTheQuestions2();
                player.setPlayerId(1);
                break;
            case 2 :
                texture = new Texture("you_2.jpg");
                player.setImage(texture);
                gameLoop.answerTheQuestions2();
                player.setPlayerId(2);
                break;
            case 3 :
                texture = new Texture("you_3.jpg");
                player.setImage(texture);
                gameLoop.answerTheQuestions2();
                player.setPlayerId(3);
                break;
            case 4 :
                texture = new Texture("you_4.jpg");
                player.setImage(texture);
                gameLoop.answerTheQuestions2();
                player.setPlayerId(4);
                break;
            case 5 :
                texture = new Texture("your_enemy_1.jpg");
                enemy.setImage(texture);
                gameLoop.startBeforeMatch();
                player.setPlayerId(5);
                break;
            case 6 :
                texture = new Texture("your_enemy_2.jpg");
                enemy.setImage(texture);
                gameLoop.startBeforeMatch();
                player.setPlayerId(6);
                break;
            case 7 :
                gameLoop.createBeforeMatch();
                break;
            case 8 :
                deck.addCardPlayer(random.nextInt(4));
                gameLoop.startBeforeMatch();
                break;
        }
    }
    public void endMatch(){
        Button back = new Button(currentPlayer.isPlayer());
        back.setTouchable(Touchable.disabled);
        back.setBounds(0,0,1920,1080);

        DialogButton dialogButton7 = new DialogButton("Start new match", 7);
        dialogButton7.setBounds(900,540, 330,80);
        dialogButton7.setName("dialogbutton");

        stage.clear();
        stage.addActor(back);
        stage.addActor(dialogButton7);


        if (player.getHealthPoints() <= 0){
            Texture loseImage = new Texture("lose.jpg");
            back.setImage(loseImage);
        }
        else if (enemy.getHealthPoints() <= 0){
            Texture winImage = new Texture("win.jpg");
            back.setImage(winImage);
            DialogButton dialogButton8 = new DialogButton("Add random card to deck", 8);
            dialogButton8.getFontMessage().getData().setScale(1);
            dialogButton8.setBounds(900,340, 330,80);
            dialogButton8.setName("dialogbutton");
            stage.addActor(dialogButton8);
        }
        else if(player.getLassitude() > 0){
            Texture madImage = new Texture("madness.jpg");
            back.setImage(madImage);
        }
        else{
            Texture loseImage = new Texture("lose.jpg");
            back.setImage(loseImage);
            System.out.println("Run out of time,so you lose");
        }
    }
}
