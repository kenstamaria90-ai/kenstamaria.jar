import java.util.Random;
import java.util.Scanner;

public class GuessingGamee {
    public String playerName;
    public int secretNumber;
    public int attemptsUsed;
    public final int maxAttempts = 10;
    public Scanner scanner = new Scanner(System.in);


    public void displayWelcome() {
        System.out.println("========================================");
        System.out.println("WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.println("Hello, " + playerName + "!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println("Let's begin!");
        System.out.println("========================================");
    }

    
    public int generateSecretNumber() {
        Random rand = new Random();
        secretNumber = rand.nextInt(100) + 1; // 1 to 100
        return secretNumber;
    }

   
    public int getUserGuess(int attempt) {
        int guess;
        while (true) {
            System.out.println("--- Attempt #" + attempt + " ---");
            System.out.print("Enter your guess (1-100): ");
            guess = scanner.nextInt();
            if (guess >= 1 && guess <= 100) {
                break;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            }
        }
        return guess;
    }

    
    public boolean giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
            return false;
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
            return false;
        } else {
            System.out.println("Congratulations " + playerName + "!");
            System.out.println("You guessed the number " + secretNumber + " in " + attemptsUsed + " attempts!");
            return true;
        }
    }

   
    public void playGame() {
        generateSecretNumber();
        attemptsUsed = 0;
        boolean success = false;

        while (attemptsUsed < maxAttempts) {
            attemptsUsed++;
            int guess = getUserGuess(attemptsUsed);
            if (giveHint(guess)) {
                success = true;
                break;
            }
        }

        if (!success) {
            System.out.println("GAME OVER!");
            System.out.println("You've used all " + maxAttempts + " attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
            System.out.println("Better luck next time, " + playerName + "!");
        }

        displayStats(success);
    }

    
    public void displayStats(boolean success) {
        System.out.println("========================================");
        System.out.println("GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attemptsUsed);

        if (success) {
            if (attemptsUsed == 1) {
                System.out.println("Rating: Perfect!");
            } else if (attemptsUsed <= 3) {
                System.out.println("Rating: Excellent!");
            } else if (attemptsUsed <= 6) {
                System.out.println("Rating: Good job!");
            } else {
                System.out.println("Rating: Nice try!");
            }
        } else {
            System.out.println("Rating: Better luck next time!");
        }
        System.out.println("========================================");
    }

    
    public boolean askPlayAgain() {
        System.out.print("Would you like to play again, " + playerName + "? (Y/N): ");
        char choice = scanner.next().toLowerCase().charAt(0);
        return choice == 'y';
    }

    // Start game
    public void startGame() {
        do {
            displayWelcome();
            playGame();
        } while (askPlayAgain());
        System.out.println("========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    
    public static void main(String[] args) {
        GuessingGamee game = new GuessingGamee();
        game.startGame();
    }
}