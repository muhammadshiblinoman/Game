package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String [] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();
        String word = null;
        if(players.equals("1")){
            Scanner scanner = new Scanner(new File("D:\\java_project\\Array\\Cheapter8\\src\\file.txt"));

            List<String> words = new ArrayList<>();

            while(scanner.hasNext()){
                words.add(scanner.nextLine());
            }
            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        }
        else{
            System.out.println("Player 1, please enter your word:");
            word = keyboard.nextLine();
            System.out.println("\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }


        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        int worngCount = 0;
        while(true){

            printHangedMan(worngCount);

            if(worngCount >= 6){
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }

            printWordState(word,playerGuesses);
            if(!getPlayerGuess(keyboard , word , playerGuesses)){
                worngCount++;
            }

            if(printWordState(word , playerGuesses)){
                System.out.println("You win!");
                break;
            }
            System.out.println("Please enter your guess for the word");
            if(keyboard.nextLine().equals(word)){
                System.out.println("You win!");
                break;
            }
            else{
                System.out.println("Nop! Try again.");
            }
        }


    }
    private static boolean printWordState(String word , List<Character> playerGuesses){
        int correntCount = 0;
        for(int i = 0; i < word.length(); i++){
            if(playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correntCount++;
            } else{
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correntCount);
    }

    private static boolean getPlayerGuess(Scanner keyboard , String word , List<Character> playerGuesses){
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        printWordState(word,playerGuesses);

        return word.contains(letterGuess);
    }

    public static void printHangedMan(Integer worngCount){
        System.out.println(" --------- ");
        System.out.println(" |       |");
        if(worngCount >= 1){
            System.out.println(" O");
        }
        if(worngCount >= 2){
            System.out.print("\\ ");
            if(worngCount >= 3){
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }
        if(worngCount >= 4){
            System.out.println(" |");
        }
        if(worngCount >= 5){
            System.out.print("/ ");
            if(worngCount >= 6){
                System.out.println("\\ ");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

}
