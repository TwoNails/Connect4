package Connect4_V3;

import java.util.InputMismatchException;
import java.util.Scanner;

class MainConnect4 {

    public static String player1name = "Joueur 1";
    public static String player2name = "Joueur 2";
    public static int winsP1;
    public static int winsP2;

    public static void main(String[] args) { 

        System.out.println("\n     ULTIMATE CONNECT 4 - IA EDITION      \n===========================================");
        System.out.println("\n  [ ]   [ ]   [ ]   [ ]   [ ]   [ ]   [ ]\n  [ ]   [O]   [ ]   [ ]   [O]   [ ]   [O]\n  [O]   [ ]   [O]   [ ]   [O]   [ ]   [O]");
        System.out.println(  "  [O]   [ ]   [ ]   [ ]   [O]   [O]   [O]\n  [O]   [ ]   [O]   [ ]   [ ]   [ ]   [O]\n  [ ]   [O]   [ ]   [ ]   [ ]   [ ]   [O]\n");

        GameManager currentGameManager;
        int nbrGames=0;

        while(true){
            System.out.println("New Game :\n    Multiplayer (Press 1)   Single player (press 2)");

            //boolean keepScore = true;
            winsP1 = 0;
            winsP2 = 0;

            Scanner sc = new Scanner(System.in);

            Boolean keepPlaying = true;
            
            Boolean modeSelectedYet = false;
            Boolean singlePlayer = false;

            while(modeSelectedYet == false){
                int gameType = 0;
                try{ 
                    gameType = sc.nextInt();  
                }catch (InputMismatchException e){
                    sc.nextLine();                      // allow us to clear the scanner's buffer. Without it we had an infinite loop where it wouldn't let us type a different input I assume
                }
                
				if(gameType == 1){
                    System.out.println("Player 1, please enter your name :");
                    player1name = sc.next();
                    System.out.println("Player 2, please enter your name :");
                    player2name = sc.next();
                    singlePlayer = false;
                    modeSelectedYet = true;                                            // might be optionnal 
                }else if(gameType == 2){
                    System.out.println("Player 1, please enter your name :");
                    player1name = sc.next();
                    player2name = "Computer";
                    singlePlayer = true;
                    modeSelectedYet = true;
                }else{
                    System.out.println(" Please type either 1 or 2 to select a game mode");
                }
            }

            while(keepPlaying){
                if(singlePlayer){
                    currentGameManager = new GameManager(true);
                }else{
                    currentGameManager = new GameManager(false);
                }
                
                System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~ SCORE ~~~~~~~~~~~~~~~~~~~~~~~~\n\n" + MainConnect4.player1name + " : " + MainConnect4.winsP1 +"                     " + MainConnect4.player2name + " : " + MainConnect4.winsP2 + "\n\n\n\n\n\n\n");
                String anyKey = "";
                while(anyKey.equals("")){
                    System.out.println("Press any key to continue");
                    anyKey = sc.next();
                }
                System.out.println("Would you like to keep playing against the same opponent ?");
                System.out.println("     - Keep Playing (Press 1)\n     - Reset scores / Change opponent / Switch game mode (press 2)\n     - Exit (press 3)");
                Boolean followUpChosenYet = false;
                
                while(!followUpChosenYet){
                    int followUpChoice = 0;
                    try{ 
                        followUpChoice = sc.nextInt();  
                    }catch (InputMismatchException e){
                        sc.nextLine();                 
                    }
                    
                    if(followUpChoice == 1){
                        followUpChosenYet=true;
                        nbrGames++;
                        System.out.println("Starting Game " + nbrGames + " !");
                    }else if(followUpChoice == 2){
                        followUpChosenYet=true;
                        keepPlaying=false;
                    }else if(followUpChoice == 3){
                        System.out.println("Thanks for Playing ! The program will now close");
                        System.exit(0);
                    }else{
                        System.out.println(" Please type 1, 2 or 3");
                    }
                }
            }
        }   
    }
}