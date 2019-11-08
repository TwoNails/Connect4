// package Connect4_V3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class GameManager {

    public Pawn[][] board = new Pawn[7][6];

    public int currentTurn=0;

    public int      currentPawnNorthEastNeighbours, currentPawnEastNeighbours, currentPawnSouthEastNeighbours,
                    currentPawnSouthNeighbours, currentPawnSouthWestNeighbours, currentPawnWestNeighbours, currentPawnNorthWestNeighbours;

    /**
     * This function resets the value of the Neighbours of the last Pawn played, so that we can 
     * 
     */
    public void resetCurrentpawnNeighbours(){
        currentPawnNorthEastNeighbours = 0;
        currentPawnEastNeighbours = 0;
        currentPawnSouthEastNeighbours = 0;
        currentPawnSouthNeighbours = 0;
        currentPawnSouthWestNeighbours = 0;
        currentPawnWestNeighbours = 0;
        currentPawnNorthWestNeighbours = 0;
    }

    /**
     * This function displays the board, looping through it and making 
     * use of \t and \n to build a String that will properly represent it. 
     */
    public void displayBoard(){
        String str = "        ";

        for(int j=0 ; j<board[0].length ; j++){
            for(int i=0 ; i<board.length ; i++){     

                if(board[i][j]==null){
                    str+="[ ] ";
                }else{
                    if(board[i][j].player1==true){
                        str+="[X] ";
                    }
                    if(board[i][j].player1==false){
                        str+="[O] ";
                    }
                }          
            }
            str+="\n        ";
        }
        System.out.println(str);
    }

    public GameManager(boolean singlePlayer){
        boolean endRound = false;                    // We will use this boolean to quit the Main loop eventually

        List<Pawn> allPawns = new ArrayList<Pawn>();    // I want ti instanciate my Pawns every turn without needing to name each of them individually, and before knowing at which
                                                    // index they'll end up int the board. This line not be perfectly clean code but for now it allows me to do that.
        Scanner sc = new Scanner(System.in);

        displayBoard();                        // After some playtests, being able to see the board, even empty, at the start, is appreciated

        while(!endRound){  
            currentTurn++;
            resetCurrentpawnNeighbours();
            System.out.println("-----------------------------------------------------------------");

            if(!singlePlayer){

                // BEHAVIOR FOR MULTIPLAYER
                if(currentTurn%2==1){
                    System.out.println("It's your turn to play, " + MainConnect4.player1name);
                }else{
                    System.out.println("It's your turn to play, " + MainConnect4.player2name);
                }
                System.out.println("In which row would you like to drop your Pawn ?");
                try {
                    allPawns.add(new Pawn(sc.nextInt()-1, this));     // we are calling Pawn's constructor, which can potentially provoque some errors
                } catch (IndexOutOfBoundsException e) {         // the constructor will interact with an array and can potentially send an error if the int entered by the player
                                                // does not match any of the array's columns. 
                currentTurn--;                  //The constructor is then skipped, and we decrement currentTurn to allow the same player to play another move, hopefully a valid one.
                    System.out.println("Please enter a number between 1 and 7");
                } catch (InputMismatchException e) {            // the constructor is expecting an int and will send an error otherwise
                    currentTurn--;
                    System.out.println("Please enter a number between 1 and 7");
                } 
            }
            else{

                if(currentTurn%2==1){
                    System.out.println("It's your turn to play, " + MainConnect4.player1name);
                    System.out.println("In which row would you like to drop your Pawn ?");
                    try {
                        allPawns.add(new Pawn(sc.nextInt()-1, this));    
                    } catch (IndexOutOfBoundsException e) {         
                        currentTurn--;                             
                        System.out.println("Please enter a number between 1 and 7");
                    } catch (InputMismatchException e) {            
                        currentTurn--;
                        System.out.println("Please enter a number between 1 and 7");
                    }
                }else{
                    System.out.println("It's the turn of the computer. ");
                    AIscripts scriptsForThisBoardState = new AIscripts(board);

                    int errorMove = 0;          
                    int nextMove = 0;

                    nextMove = scriptsForThisBoardState.winningMove();
                    
                    if(nextMove == -1) {
                        nextMove = scriptsForThisBoardState.emergencyMove();
                    }
                    if(nextMove == -1) {
                        nextMove = scriptsForThisBoardState.buildingLineMove();
                    }
                    if(nextMove == -1) {
                        nextMove = scriptsForThisBoardState.randomMove();
                    }
                    try {
                        allPawns.add(new Pawn(nextMove, this));
                    } catch (IndexOutOfBoundsException e) {         // this is meant to give the AI an alternative move if she's stuck choosing one that can't be played because the column is full.
                        errorMove++; 
                        System.out.println("catched an error");       
                        allPawns.add(new Pawn((nextMove+errorMove%7), this)); 
                                                 
                    }
                }

            }
            
            displayBoard();

            if(currentPawnSouthNeighbours>2                                                 // if there's at least 3 Pawns under the one we just played
            || currentPawnNorthEastNeighbours + currentPawnSouthWestNeighbours>2            // or when adding the number of Pawns on opposite sides in the three other possible axis
            || currentPawnEastNeighbours + currentPawnWestNeighbours>2
            || currentPawnSouthEastNeighbours + currentPawnNorthWestNeighbours>2)
            {
                endRound=true;                                                              // then we break out of the loop
            }
        }

        System.out.println("Game Over !");                                          // and we announce the winner
        //sc.close();
        if(currentTurn%2==1){
            System.out.println("And the winner is : " + MainConnect4.player1name + " !        Scroll up to see the final board state.");
            MainConnect4.winsP1++;
        }else{
            System.out.println("And the winner is : " + MainConnect4.player2name + " !        Scroll up to see the final board state.");
            MainConnect4.winsP2++;
        }
    }
}