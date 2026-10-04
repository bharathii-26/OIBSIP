package numberguessing;

import java.util.*;
public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        ArrayList<String> scoreHistory = new ArrayList<>();

        boolean playAgain = true;
        int round = 1;

        while (playAgain) {

            // Difficulty selection
            System.out.println("\n==============================");
            System.out.println("     NUMBER GUESSING GAME");
            System.out.println("==============================");
            System.out.println("1. Easy   (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard   (1-200, 5 attempts)");

            int difficulty;

            while (true) {
                System.out.print("Choose difficulty (1-3): ");

                if (scanner.hasNextInt()) {
                    difficulty = scanner.nextInt();

                    if (difficulty >= 1 && difficulty <= 3) {
                        break;
                    }
                } else {
                    scanner.next();
                }

                System.out.println("Please enter 1, 2, or 3.");
            }

            int maxNumber;
            int maxAttempts;
            String level;

            if (difficulty == 1) {
                maxNumber = 50;
                maxAttempts = 10;
                level = "Easy";
            } else if (difficulty == 2) {
                maxNumber = 100;
                maxAttempts = 7;
                level = "Medium";
            } else {
                maxNumber = 200;
                maxAttempts = 5;
                level = "Hard";
            }

            // Computer selects random number
            int secretNumber = random.nextInt(maxNumber) + 1;

            int attempts = 0;
            boolean guessed = false;

            System.out.println("\nRound " + round);
            System.out.println("Difficulty: " + level);
            System.out.println("Guess a number between 1 and " + maxNumber);
            System.out.println("You have " + maxAttempts + " attempts.");

            // Guessing loop
            while (attempts < maxAttempts) {

                System.out.print("\nEnter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                if (guess < 1 || guess > maxNumber) {
                    System.out.println(
                            "Please enter a number between 1 and " + maxNumber
                    );
                    continue;
                }

                attempts++;

                if (guess > secretNumber) {
                    System.out.println("Too High!");
                } 
                else if (guess < secretNumber) {
                    System.out.println("Too Low!");
                } 
                else {
                    System.out.println("Correct!");
                    System.out.println(
                            "You guessed it in " + attempts + " attempts."
                    );

                    guessed = true;
                    break;
                }

                System.out.println(
                        "Attempts remaining: " + (maxAttempts - attempts)
                );
            }

            // Round result
            if (!guessed) {
                System.out.println("\nYou Lost!");
                System.out.println(
                        "The correct number was: " + secretNumber
                );
            }

            // Store score
            if (guessed) {
                scoreHistory.add(
                        "Round " + round + " (" + level + ") — guessed in "
                                + attempts + " attempts"
                );
            } else {
                scoreHistory.add(
                        "Round " + round + " (" + level + ") — Not guessed"
                );
            }

            // Play again
            System.out.print("\nPlay Again? (yes/no): ");
            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("yes")) {
                playAgain = false;
            }

            round++;
        }

        // Score summary
        System.out.println("\n==============================");
        System.out.println("       SCORE SUMMARY");
        System.out.println("==============================");

        for (String score : scoreHistory) {
            System.out.println(score);
        }

        System.out.println("\nThanks for playing!");
        scanner.close();
    }
}