import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class MovieClass {

    private ArrayList<String> films;
    private Scanner scanner;
    private String randomFilm;
    private String guessString;
    private int NUM_GUESSES = 11;
    private static char userInput;
    private StringBuilder result;
    private ArrayList<Character> letterArray = new ArrayList<>();


    MovieClass() throws Exception {
        try {
            File file = new File("src/movies");
            scanner = new Scanner(file);
            films = new ArrayList<>();
            while (scanner.hasNextLine()) {
                films.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File missing!");
        }
        int randomIndex = (int) (Math.random() * films.size());
        randomFilm = films.get(randomIndex).toLowerCase();

        guessString = randomFilm.replaceAll("[a-zA-Z]", "*").trim();
        System.out.println(randomFilm);
        System.out.println(guessString);

        FindMatch();
    }

    private void FindMatch() {
        while (!HasWon(guessString) && NUM_GUESSES > 1) {
            NUM_GUESSES--;
            System.out.println("Enter one letter, please: ");
            System.out.println("You have " + NUM_GUESSES + " guesses left: ");

            scanner = new Scanner(System.in);
            userInput = scanner.next().toLowerCase().charAt(0);

            //check if user input is a digit or not
            if (userInput >= '0' && userInput <= '9') {
                System.out.println("This is not a letter");
            }

            if (HasTheChar(userInput)) {
                result = new StringBuilder(guessString);

                //check if the ArrayList contains user input already,
                //so does not add to the guessingString.
                if (letterArray.contains(userInput)) {
                    System.out.println("You already guessed this letter");
                } else {
                    //add all the user input to an ArrayList.
                    letterArray.add(userInput);
                }

                for (int j = 0; j < randomFilm.length(); j++) {

                    //if letter in the film at 'j' position
                    //equals to the user input.
                    if (randomFilm.charAt(j) == userInput) {

                        //set the input to the particular index in guessString.
                        result.setCharAt(j, userInput);
                        guessString = result.toString();
                    }
                }

                if (HasWon(guessString))
                    System.out.println("Congratulations, you won! " + "The movie is: " + randomFilm);
                else if (NUM_GUESSES == 1)
                    System.out.println("You loose, " + "the movie was: " + randomFilm);
                else
                    System.out.println("You are guessing: " + guessString);

            } else {
                System.out.println("The movie does not contain : " + userInput);
            }
        }
    }

    /**
     * @param userInput check the character that user entered;
     * @return the index of the user input in the film to guess
     * if it exists.
     **/
    private boolean HasTheChar(char userInput) {
        return randomFilm.indexOf(userInput) > -1;
    }

    /**
     * @param guessingString we check the string that user guessing;
     * @return the condition to win: the string that does not contain "*".
     */
    private boolean HasWon(String guessingString) {
        return !guessingString.contains("*");
    }
}