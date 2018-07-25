import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Movies {
    private ArrayList<String> moviesList;
    private ArrayList<Character> letterAnswer;
    String chosenMovie;
    String convertLetter;
    int numGuess = 11;
    char answer;


    Movies() throws FileNotFoundException {
        try {
            File file = new File("src/movies");
            Scanner fileScanner = new Scanner(file);
            moviesList = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String movie = fileScanner.nextLine();
                moviesList.add(movie);
            }

            Random randomNum = new Random();
            int pickRandomMovie = randomNum.nextInt(moviesList.size());
            chosenMovie = moviesList.get(pickRandomMovie);
            System.out.println(chosenMovie + " <=Chosen randomly");
            convertLetter = chosenMovie.replaceAll("[a-z]", "*");
            System.out.println(convertLetter);
            findMatch();

        } catch (FileNotFoundException exception) {
            System.out.println("File has not found");
        }


    }

    public void findMatch() {
        while (!hasWon(convertLetter) && numGuess > 1) {
            numGuess--;
            System.out.println("Input your answer:");
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("You have " + numGuess + " tries");
            letterAnswer = new ArrayList<>();

            answer = inputScanner.next().toLowerCase().charAt(0);
            if (answer >= '0' && answer <= '9') {
                System.out.println("This is not a letter");
            }
            if (letterAnswer.contains(answer)) {
                System.out.println("You have already guessed this letter");
            } else {
                letterAnswer.add(answer);
            }

            StringBuilder result = new StringBuilder(convertLetter);
            //System.out.println(answer);

            int compareIndex = convertLetter.indexOf(answer);

            //System.out.println(compareIndex);



            for (int i = 0; i < chosenMovie.length(); i++){
                if (chosenMovie.charAt(i) == answer){
                    result.setCharAt(i,answer);
                    convertLetter = result.toString();
                    //System.out.println(convertLetter);
                }


            }
            if (hasWon(convertLetter)){
                System.out.println("Congratulations!!! You have won the game ");
                System.out.println("The movie was " +chosenMovie);
            }
            if (numGuess == 1){
                System.out.println("You lose, try again");
                System.out.println("The movie was " +chosenMovie);
            }
            else {
                System.out.println("You guessed");
                System.out.println(convertLetter);
            }


        }

    }
    private boolean hasWon(String convertLetter ) {
        return !convertLetter.contains("*");
    }

}





