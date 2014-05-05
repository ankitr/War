public class Main {
	public static void main(String args[]) {
		// Checking that we're in the right universe.
		assert 1 + 1 == 2;
		
		// Create the deck we're planning to use.
		Deck deck = new Deck();
		
		// Here are the player hands.
		Queue<Card> firstHand = new Queue<Card>(), secondHand = new Queue<Card>();
		
		deal(deck, firstHand, secondHand);
		
		playWar(firstHand, secondHand);
	}
	
	private static void deal(Deck deck, Queue<Card> firstHand, Queue<Card> secondHand) {
		for (int i = 0; i < 52; i++) {
			// Half the cards in each hand.
			if (i % 2 == 0)
				firstHand.put(deck.getCard());
			else
				secondHand.put(deck.getCard());
		}
	}
	
	private static void playWar(Queue<Card> firstHand, Queue<Card> secondHand) {
		System.out.println("Welcome to War.");
		while (!(firstHand.isEmpty()) && !(secondHand.isEmpty())) {
			playRound(firstHand, secondHand);
			System.out.println("Player 0 has " + firstHand.size() + ", Player 1 has " + secondHand.size());
		}
		if ((firstHand.size() == 0) && (secondHand.size() > 0)) {
			System.out.println("And the winner is... Player 1!");
		}
		else if ((firstHand.size() > 0) && (secondHand.size() == 0)) {
			System.out.println("And the winner is... Player 0!");
		}
		else {
			System.out.println("And it's a tie!");
		}
	}
	
	private static void playRound(Queue<Card> firstHand, Queue<Card> secondHand) {
		// Create our play stacks with a card each in them.
		Stack<Card> firstPlayed = new Stack<Card>();
		Stack<Card> secondPlayed = new Stack<Card>();
		firstPlayed.put(firstHand.get());
		secondPlayed.put(secondHand.get());
		
		playRound(firstHand, secondHand, firstPlayed, secondPlayed);
	}
	
	private static void playRound(Queue <Card> firstHand, Queue<Card> secondHand, Stack<Card> firstPlayed, Stack<Card> secondPlayed) {
		// Get the two compared cards out.
		Card firstCard = firstPlayed.peek();
		Card secondCard = secondPlayed.peek();
		
		// Print the round information.
		System.out.println(firstCard + " versus " + secondCard);
		
		if (firstCard.compareTo(secondCard) > 0) {
			while (firstPlayed.size() > 0)
				firstHand.put(firstPlayed.get());
			while (secondPlayed.size() > 0)
				firstHand.put(secondPlayed.get());
		}
		else if (firstCard.compareTo(secondCard) < 0) {
			while (secondPlayed.size() > 0)
				secondHand.put(secondPlayed.get());
			while (firstPlayed.size() > 0)
				secondHand.put(firstPlayed.get());
		}
		else {
			if (firstHand.isEmpty() && secondHand.isEmpty())
				// It's a tie.
				return;
			
			// This. Means. WAR!!!
			System.out.println("WAR!");
			for (int i = 0; i < 4; i++) {
				// Fill up the first hand.
				if (firstHand.isEmpty())
					break;
				firstPlayed.put(firstHand.get());
			}
			for (int i = 0; i < 4; i++) {
				// Fill up the second hand.
				if (secondHand.isEmpty())
					break;
				secondPlayed.put(secondHand.get());
			}
			
			// Play the round.
			playRound(firstHand, secondHand, firstPlayed, secondPlayed);
		}
	}
}
