package libgdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Table {
    Player player;
    Enemy enemy;
    PlayerPerson currentPlayer;
    public ArrayList<Card> enemyDeck = new ArrayList<>();
    public ArrayList<Card> enemyHand = new ArrayList<>();
    public ArrayList<Card> enemyTable = new ArrayList<>();
    public ArrayList<Card> playerDeck = new ArrayList<>();
    public ArrayList<Card> playerHand = new ArrayList<>();
    public ArrayList<Card> playerTable = new ArrayList<>();
    private final StageNew stage;
    private final Vector2 tempCoords = new Vector2();
    private float distX, distY, posX, posY;
    Actor target;
    Card targetCardOld, targetCardNew;
    Button endTurnButton;
    PlayerPerson attackedPerson;
    public Table(StageNew stage, Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        currentPlayer = player;
        this.stage = stage;
    }
    public void setCoordinateHandPlayer(Card card) {
        float x = 400;
        while (x <= 1420) {
            if (stage.hit(x, 180, true) == null) {
                card.setPosition(x, 180);
            }
            x += 224;
        }
    } // broken a little bit
    public void setCoordinateHandEnemy(Card card){
            float x = 400;
            while (x <= 1420){
                if (stage.hit(x,900,true) == null){
                    card.setPosition(x,900);
                }
                x += 224;
            }
    } // broken a little bit
    public void setCoordinateTablePlayer(Card card){
        float x = 400;
        while (x <= 1420) {
            if (stage.hit(x, 280, true) == null) {
                card.setPosition(x, 280);
            }
            x += 224;
        }
    } // broken a little bit
    public void setCoordinateTableEnemy(Card card){
        float x = 400;
        while (x <= 1420) {
            if (stage.hit(x, 800, true) == null) {
                card.setPosition(x, 800);
            }
            x += 224;
        }
    } // broken a little bit
    public void createDeck(){
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            Card card1 = new Card("playerCard" + i, true, random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
            Card card2 = new Card("enemyCard" + i, false, random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
            if(playerDeck.size() < 5){
                card1.setName("card");
                card2.setName("card");
                playerDeck.add(card1);
                enemyDeck.add(card2);
            }
        }
    } // enough for now i guess
    public void startBeforeMatch(Player player, Enemy enemy){
        player.setStart();
        enemy.setStart();
        createDeck();
    } // not need to be updated
    public boolean isCardDrawable(Card card){
            return( (currentPlayer.getConversant() - card.getManaCost()) >=0);
    } // updated
    public boolean isCardAttack(Card card) {
            return (!card.isOnCooldown());
    }// updated
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
                    Random random = new Random();
                    Card randomCard1 = new Card("enemyCard" + 111, false, random.nextInt(5) + 1, random.nextInt(5) + 1, random.nextInt(5) + 1, 0, 0, 0);
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
                    Random random = new Random();
                    Card randomCard2 = new Card("playerCard" + 111, true, random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
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
    } //// надо мощно подумоть но походу все правильно
    public void attackCard(Card attackingCard, Card attackedCard) {
            /*if(attackedId == 100){
                player.setHealthPoints( player.getHealthPoints() - enemyTable.get(attackingId).getDamage() );
            }
            else{*/
        attackedCard.setHealthPoints(attackedCard.getHealthPoints() - attackingCard.getDamage());
        attackingCard.setHealthPoints(attackingCard.getHealthPoints() - attackedCard.getDamage());
        attackingCard.setOnCooldown(true);
            /*if(attackedId == 100){
                enemy.setHealthPoints( enemy.getHealthPoints() - playerTable.get(attackingId).getDamage() );
            }
            else{*/
    }
    public void attackPlayer(Card attackingCard, PlayerPerson attackedPerson){
        attackedPerson.setHealthPoints(attackedPerson.getHealthPoints() - attackingCard.getDamage());
        attackingCard.setOnCooldown(true);
    }
    public void switchCurrentPlayer(){
        if(currentPlayer.isPlayer()){
            currentPlayer = enemy;
            System.out.println("End of the player turn");
        }
        else{
            currentPlayer = player;
            System.out.println("End of the enemy turn");
        }
    }
    public void endTurn(){
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
        if(currentPlayer.getConversant() <=9){
            currentPlayer.setConversant(currentPlayer.getConversant() + 1);
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
        if(target != null && target.getName() == "card") {
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
        else if(target != null && target.getName() == "button") {
            endTurnButton = (Button) target;
            if (endTurnButton.isPlayer() == currentPlayer.isPlayer()) {
                endTurnButton.setWidth(110);
                endTurnButton.setHeight(110);
            } else {
                endTurnButton = null;
            }
        }
    }
    public void touchUpHandler(int screenX, int screenY){
        if (targetCardOld != null && targetCardOld.getName() == "card") {
            targetCardOld.setWidth(64);
            targetCardOld.setHeight(64);
            targetCardOld.setX(posX);
            targetCardOld.setY(posY);
            target = stage.hit(tempCoords.x, tempCoords.y, false);
            if(target != null && target.getName() == "card"){
                targetCardNew = (Card) target;
                attackCardUnderMouse();
            }
            else if(target != null && target.getName() == "table") {
                targetCardNew = (Card) target;
                playOnTableUnderMouse(screenX, screenY);
            }
            else if(target != null && (target.getName() == "player" || target.getName() == "enemy")){
                attackedPerson = (PlayerPerson) target;
                attackPlayerUnderMouse();
            }
        }
        else if (endTurnButton != null && endTurnButton.getName() == "button") {
            if(endTurnButton.isPlayer() == currentPlayer.isPlayer()) {
                endTurnButton.setWidth(100);
                endTurnButton.setHeight(100);
                endTurn();
            }
        }
    }
    public void touchDraggedHandler(int screenX, int screenY) {
        stage.screenToStageCoordinates(tempCoords.set(screenX, screenY));
        if (targetCardOld != null && targetCardOld.getName() == "card") {
            targetCardOld.setX(tempCoords.x - distX);
            targetCardOld.setY(tempCoords.y - distY);
        }
    }
    public void attackCardUnderMouse() {
        if (targetCardNew.getName() == "card") {
            if (targetCardOld.isOnTable() && isCardAttack(targetCardOld) && isCardsAttackable(targetCardNew)) {
                attackCard(targetCardOld,targetCardNew);
                checkDiedAll();
            }
        }
    }
    public void attackPlayerUnderMouse() {
        if (attackedPerson.getName() == "player" && !targetCardOld.isPlayer() && targetCardOld.isOnTable() && isCardAttack(targetCardOld)) {
            System.out.println("Player hp =" + player.getHealthPoints());
            attackPlayer(targetCardOld,player);
            System.out.println("Player hp =" + player.getHealthPoints());
        } else if (attackedPerson.getName() == "enemy" && targetCardOld.isPlayer() && targetCardOld.isOnTable() && isCardAttack(targetCardOld)) {
            System.out.println("Enemy hp =" + enemy.getHealthPoints());
            attackPlayer(targetCardOld,enemy);
            System.out.println("Enemy hp =" + enemy.getHealthPoints());
        }
    }
    public void playOnTableUnderMouse(int screenX, int screenY) {
        if (targetCardNew.getName() == "table" && screenX > 300 && screenX < 1500 && screenY > 280 && screenY < 800) {
            if (targetCardOld.isInHand() && isCardDrawable(targetCardOld)) {
                if (targetCardOld.isPlayer() && playerTable.size() < 5) {
                    setCoordinateTablePlayer(targetCardOld);
                    playerTable.add(targetCardOld);
                    playerHand.remove(targetCardOld);
                    targetCardOld.playOnTable();
                    targetCardOld.setFromHand(true);
                    currentPlayer.setConversant(currentPlayer.getConversant() - targetCardOld.getManaCost());
                } else if(!targetCardOld.isPlayer() && enemyTable.size() < 5){
                    setCoordinateTableEnemy(targetCardOld);
                    enemyTable.add(targetCardOld);
                    enemyHand.remove(targetCardOld);
                    targetCardOld.playOnTable();
                    targetCardOld.setFromHand(true);
                    currentPlayer.setConversant(currentPlayer.getConversant() - targetCardOld.getManaCost());
                }
            }
        }
    }
}
