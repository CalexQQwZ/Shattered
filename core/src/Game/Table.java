package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Table {
    Player player;
    Enemy enemy;
    PlayerPerson currentPlayer;
    private ArrayList<Card> enemyDeck = new ArrayList<Card>();
    private ArrayList<Card> enemyHand = new ArrayList<Card>();
    private ArrayList<Card> enemyTable = new ArrayList<Card>();
    private ArrayList<Card> playerDeck = new ArrayList<Card>();
    private ArrayList<Card> playerHand = new ArrayList<Card>();
    private ArrayList<Card> playerTable = new ArrayList<Card>();

    public Table(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        currentPlayer = player;
    }
    public void createDeck(){
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            String cardName = "goodName" + Integer.toString(i);
            Card card1 = new Card("playerCard" + Integer.toString(i), random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
            Card card2 = new Card("enemyCard" + Integer.toString(i), random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
            if(playerDeck.size() < 3){
                playerDeck.add(card1);
                enemyDeck.add(card2);
            }
        }
    }
    public void startBeforeMatch(Player player, Enemy enemy){
        player.setStart();
        enemy.setStart();
        createDeck();
    }
    public boolean isCardDrawable(int cardId){
        if (currentPlayer.getId() == 0) {
            return(currentPlayer.getConversant() >= enemyHand.get(cardId).getManaCost());
        }
        else{
            return(currentPlayer.getConversant() >= playerHand.get(cardId).getManaCost());
        }

    }
    public boolean isCardAttack(int cardId) {
        if (currentPlayer.getId() == 0){
            return (enemyTable.get(cardId).isAttack());
        }
        else{
            return (playerTable.get(cardId).isAttack());
        }

    }
    public boolean isCardsAttackable(int cardId) {
        boolean target = false;
        if (currentPlayer.getId() == 0) {
            for (Card cardI : playerTable) {
                if (cardI.getTarget()) {
                    target = true;
                }
                if(cardId != 100){
                    if (cardI == playerTable.get(cardId)) {
                        return (true);
                    }
                }
            }
            if (!target) {
                return (true);
            }
            else {
                return (false);
            }
        }
        else {
            for (Card cardI : enemyTable) {
                if (cardI.getTarget()) {
                    target = true;
                }
                if(cardId != 100){
                    if (cardI == enemyTable.get(cardId)) {
                        return (true);
                    }
                }
            }
            if (!target) {
                return (true);
            } else {
                return (false);
            }
        }
    }
    public void takeCardFromDeck(){
        if (currentPlayer.getId() == 0){
            if (enemyDeck.isEmpty()){
                Random random = new Random();
                Card randomCard1 = new Card("enemyCard" + Integer.toString(111), random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
                enemyHand.add(randomCard1);
            }
            else{
                enemyHand.add(enemyDeck.remove(enemyDeck.size()-1));
                currentPlayer.setLassitude(currentPlayer.getLassitude()+1);
            }
        }
        else{
            if (playerDeck.isEmpty()){
                Random random = new Random();
                Card randomCard2 = new Card("playerCard" + Integer.toString(111), random.nextInt(5)+1, random.nextInt(5)+1,random.nextInt(5)+1,0,0,0);
                playerHand.add(randomCard2);
            }
            else{
                playerHand.add(playerDeck.remove(playerDeck.size()-1));
                currentPlayer.setLassitude(currentPlayer.getLassitude()+1);
            }
        }
    }
    public void playCardFromHand(int cardId){
        if(cardId == -1){
            return;
        }
        if (currentPlayer.getId() == 0){
            enemyHand.get(cardId).setFromHand(true);
            enemyTable.add(enemyHand.remove(cardId));
        }
        else{
            playerHand.get(cardId).setFromHand(true);
            playerTable.add(playerHand.remove(cardId));
        }
    }
    public int pickPlayableCard(){
        int i;
        Scanner console = new Scanner(System.in);
        if (currentPlayer.getId() == 0){
            if(enemyHand.isEmpty()){
                System.out.println("You can't your hand is empty");
                return (-1);
            }
            else{
                System.out.println("Input playable num between 0 and ");
                System.out.println(enemyHand.size()-1);
            }
        }
        else{
            if(playerHand.isEmpty()){
                System.out.println("You can't your hand is empty");
                return (-1);
            }
            else{
                System.out.println("Input playable num between 0 and ");
                System.out.println(playerHand.size()-1);
            }
        }
        i = console.nextInt();
        while(!isCardDrawable(i)){
            System.out.println("Wrong num input again");
            i = console.nextInt();
        }
        return (i);
    }
    public int pickAttackingCard(){
        int i;
        Scanner console = new Scanner(System.in);
        if (currentPlayer.getId() == 0){
            if (enemyTable.isEmpty()){
                System.out.println("You don't have card on table");
                return(-1);
            }
            else{
                System.out.println("Input attacking num between 0 and ");
                System.out.println(enemyTable.size()-1);
            }
        }
        else{
            if (playerTable.isEmpty()){
                System.out.println("You don't have card on table");
                return(-1);
            }
            else{
                System.out.println("Input attacking num between 0 and ");
                System.out.println(playerTable.size()-1);
            }
        }
        i = console.nextInt();
        while(!isCardAttack(i)){
            System.out.println("Wrong num input again");
            i = console.nextInt();
        }
        return (i);
    }
    public int pickAttackedCard(){
        int i;
        Scanner console = new Scanner(System.in);
        if (currentPlayer.getId() == 0){
            if (playerTable.isEmpty()){
                if (isCardsAttackable(100)){
                    System.out.println("You can attack only a Person");
                    return (100);
                }
                else{
                    System.out.println("Nobody to be attacked");
                    return (-1);
                }
            }
            else{
                System.out.println("Input attacked num between 0 and ");
                System.out.println(playerTable.size()-1);
            }
        }
        else{
            if (enemyTable.isEmpty()){
                if (isCardsAttackable(100)) {
                    System.out.println("You can attack only a Person");
                    return (100);
                }
                else{
                    System.out.println("Nobody to be attacked");
                    return (-1);
                }
            }
            else{
                System.out.println("Input attacked num between 0 and ");
                System.out.println(enemyTable.size()-1);
            }
        }
        System.out.println(" or 100 to attack person");
        i = console.nextInt();
        while (!isCardsAttackable(i)){
            System.out.println("Wrong num input again");
            i = console.nextInt();
        }
        return (i);
    }
    public void checkDiedAll(){
        if (!enemyTable.isEmpty()) {
            for (Card cardI : enemyTable) {
                if (!cardI.isAlive()) {
                    enemyTable.remove(cardI);
                }
            }
        }
        if (!playerTable.isEmpty()) {
            for (Card cardI : playerTable) {
                if (cardI.isAlive()) {
                    playerTable.remove(cardI);
                }
            }
        }
    }
    public void attack(int attackingId, int attackedId){
        if(attackedId == -1 || attackingId == -1){
            return;
        }
        if (currentPlayer.getId() == 0){
            if(attackedId == 100){
                player.setHealthPoints( player.getHealthPoints() - enemyTable.get(attackingId).getDamage() );
            }
            else{
                playerTable.get(attackedId).setHealthPoints(playerTable.get(attackedId).getHealthPoints() - enemyTable.get(attackingId).getDamage());
            }
            enemyTable.get(attackingId).setOnCooldown(true);
        }
        else {
            if(attackedId == 100){
                enemy.setHealthPoints( enemy.getHealthPoints() - playerTable.get(attackingId).getDamage() );
            }
            else{
                enemyTable.get(attackedId).setHealthPoints(enemyTable.get(attackedId).getHealthPoints() - playerTable.get(attackingId).getDamage());
            }
            playerTable.get(attackingId).setOnCooldown(true);
        }
    }
    public void turn(){
        boolean endOfTurn = false;
        int action;
        takeCardFromDeck();
        while(!endOfTurn){
            Scanner console = new Scanner(System.in);
            System.out.println("Input num between 0 and 2 (0 to end turn. 1 to play card from hand. 2 to attack card)");
            action = console.nextInt();
            if(action == 0){
                endOfTurn = true;
            }
            if(action == 1){
                playCardFromHand(pickPlayableCard());
            }
            if(action == 2){
                attack(pickAttackingCard(),pickAttackedCard());
                checkDiedAll();
            }
        }
        checkDiedAll();
        if(currentPlayer.getId() == 0 && !enemyTable.isEmpty()){
            for (Card cardI:enemyTable) {
                cardI.setOnCooldown(false);
                cardI.incTurnCounter();
            }
        }
        if(currentPlayer.getId() == 1 && !playerTable.isEmpty()){
            for (Card cardI : playerTable) {
                cardI.setOnCooldown(false);
                cardI.incTurnCounter();
            }
        }
        currentPlayer.setConversant(currentPlayer.getConversant() + 3);
        if(currentPlayer.getId() == 0){
            currentPlayer = player;
        }
        else{
            currentPlayer = enemy;
        }
    }
}
