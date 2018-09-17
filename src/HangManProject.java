import java.util.Scanner;

/**
 * Test the Hangman
 */

public class HangManProject {

    static int count = 1;
    static int totalMistake = 0;

    public static void main( String[] args)
    {
        Scanner scan = new Scanner( System.in);

        while (true) {

            showWelcome();

            String category = scan.nextLine();
            SecretWord word = new SecretWord(category);

            System.out.println("Your category: " + category);
            System.out.println("Use only the small one letter.\b");

            KeyboardInput input = new KeyboardInput();

            //enter new letter
            while (totalMistake < 10 && !word.isSolved()) {
                System.out.println(word);
                char c = input.getNewLetter();
                if (!word.update(c))
                    totalMistake = totalMistake + 1;
            }

            //check for the word is solved
            if (word.isSolved()) {
                System.out.println("Great, you done it.\n");
                System.out.println("You have " + count++ + " score.");
                System.out.println((10 - totalMistake) + " mistakes left.");

                System.out.println("Do you want play again Y/N: ");

                String YN = scan.nextLine();

                if (YN.equalsIgnoreCase("N")) {
                    System.out.println("Game over!!!");
                    return;
                } else {
                    continue;
                }
            } else {
                System.out.println("Sorry, too many errors.");
                return;
            }
        }
    }

    /**
     * show the first istruction
     */

    private static void showWelcome()
    {
        System.out.println( "Welcome to Hangman Game:)!!" );
        System.out.println();
        System.out.println("Choose the category: \n" +
                "1.Football teams \n" +
                "2.Books \n" +
                "3.Programming principles");
        System.out.println("The system wait your choice: ");
    }
}

