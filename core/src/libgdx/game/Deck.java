package libgdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class Deck {
    private ArrayList<Card> deckAllCards = new ArrayList<>();
    private ArrayList<Card> deckPlayerPickedCards = new ArrayList<>();
    private ArrayList<Card> deckEnemyPickedCards = new ArrayList<>();
    Texture i,we,it,trans,Chmonya1,Chmonya2,Chmonya3,Chmonya4;
    public void createDeck(){
        i = new Texture("I.jpg");
        we = new Texture("We.jpg");
        it = new Texture ("It.jpg");
        trans = new Texture("Glazoedi.jpg");
        Chmonya1 = new Texture("nurgl.jpg");
        Chmonya2 = new Texture("slaanesh.jpg");
        Chmonya3 = new Texture ("tzeench.jpg");
        Chmonya4 = new Texture("ultramarine.jpg");
        Card card = new Card("I", true, 3,3,1,0,0,0, i);
        deckAllCards.add(card);
        card = new Card("We",true, 4,4,1,0,0,0, we);
        deckAllCards.add(card);
        card = new Card("It", true, 5,5,1,0,0,0, it);
        deckAllCards.add(card);
        card = new Card("Transcendences", true, 5,7,5,0,0,0, trans);
        deckAllCards.add(card);
        card = new Card("Chmonya1", true, 12,6,8,0,0,0, Chmonya1);
        deckAllCards.add(card);
        card = new Card("Chmonya2",true, 2,9,10,0,0,0, Chmonya2);
        deckAllCards.add(card);
        card = new Card("Chmonya3", true, 5,6,1,0,0,0, Chmonya3);
        deckAllCards.add(card);
        card = new Card("Chmonya4", true, 100,100,8,0,0,0, Chmonya4);
        deckAllCards.add(card);
    }
    public void addCardPlayer(int id){
        Card card = new Card(deckAllCards.get(id % 4));
        deckPlayerPickedCards.add( card );
        card = new Card(deckAllCards.get(4 + id % 4));
        deckPlayerPickedCards.add( card );
    }
    public void addCardEnemy(int id){
        Card card = new Card(deckAllCards.get(id % 4));
        card.setPlayer(false);
        deckEnemyPickedCards.add( card );
        card = new Card(deckAllCards.get(4 + id % 4) );
        card.setPlayer(false);
        deckEnemyPickedCards.add( card );
    }
    public Card getCardFromPlayerDeck() {
        return deckPlayerPickedCards.get( random.nextInt(deckPlayerPickedCards.size()) );
    }
    public Card getCardFromEnemyDeck() {
        return deckEnemyPickedCards.get( random.nextInt(deckEnemyPickedCards.size()) );
    }
}
