import java.util.HashSet;
import java.util.Iterator;

public class Deck { // This is slow--can you think of a better way? (There are
    // many...)
    HashSet<Card> myCards = new HashSet<Card>();
    char[] suits = { 'S', 'H', 'C', 'D' };
    char[] ranks = { 'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4',
            '3', '2' };
    Iterator<Card> cardGetter; // This is used in a really ugly way

    public Deck() {
        init();
    }

    private void init() {
        for (int suit = 0; suit < suits.length; suit++)
            for (int rank = 0; rank < ranks.length; rank++)
                myCards.add(new Card(ranks[rank], suits[suit]));
        cardGetter = myCards.iterator();
    }

    // Note why we need to adhere to abstractions. The shuffle method
    // doesn't really shuffle at all upon inspection. However, in conjunction
    // with getCard(), the behavior of shuffling and getting cards from the
    // deck is perfectly reasonable.
    //
    // While I would not recommend writing shuffle() this way in general,
    // the point is that the Deck abstraction should be treated like a black
    // box, not an open book.
    //
    // Part of the point of this is to illustrate how an iterator works (see
    // the getCard() method).

    public void shuffle() {
        myCards.clear();
        init();
    }

    public Card getCard() {
        Card c;
        cardGetter = myCards.iterator(); // Start from beginning each time

        int next = (int) (Math.random() * size()) + 1;
        c = cardGetter.next();
        for (int i = 1; i < next; i++) {
            c = cardGetter.next();
        }
        cardGetter.remove(); // Note that if a card is dealt,
        return c;            // it is taken out of the deck
    }

    public boolean isEmpty() {
        return myCards.isEmpty();
    }

    public int size() {
        return myCards.size();
    }
}