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
        String str = "      ";

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
        boolean endRound = false;                    // Ce boolean contrôlera la sortie de la boucle principale

        List<Pawn> allPawns = new ArrayList<Pawn>();    // Je veux pouvoir instancier des pions à chaque tour sans avoir besoin de les nommer, et avant de savoir ou 
                                                    //  ils atterriront dans le tableau. Ce n'est pas très élégant mais cette liste est juste là pour me le permettre
        Scanner sc = new Scanner(System.in);

        displayBoard();                        // Après playtests, pouvoir voir le tableau, bien que vide, dès le début est apprécié des joueurs

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
                    allPawns.add(new Pawn(sc.nextInt()-1, this));     // on appelle le constructeur de Pawn, ce qui peut potentiellement soulever des erreurs.
                } catch (IndexOutOfBoundsException e) {         // le constructeur va interagir avec un tableau et peut renvoyer une erreur si l'int entré par le joueur
                    currentTurn--;                              // ne correspond pas à l'une des colonnes de ce tableau
                    System.out.println("Please enter a number between 1 and 7");
                } catch (InputMismatchException e) {            // le constructeur attend un int et peut renvoyer une erreur dans le cas contraire
                    currentTurn--;
                    System.out.println("Please enter a number between 1 and 7");
                } 
            }
            else{

                if(currentTurn%2==1){
                    System.out.println("It's your turn to play, " + MainConnect4.player1name);
                    System.out.println("In which row would you like to drop your Pawn ?");
                    try {
                        allPawns.add(new Pawn(sc.nextInt()-1, this));     // on appelle le constructeur de Pawn, ce qui peut potentiellement soulever des erreurs.
                    } catch (IndexOutOfBoundsException e) {         // le constructeur va interagir avec un tableau et peut renvoyer une erreur si l'int entré par le joueur
                        currentTurn--;                              // ne correspond pas à l'une des colonnes de ce tableau
                        System.out.println("Please enter a number between 1 and 7");
                    } catch (InputMismatchException e) {            // le constructeur attend un int et peut renvoyer une erreur dans le cas contraire
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
                    } catch (IndexOutOfBoundsException e) {
                        errorMove++; 
                        System.out.println("catched an error");       
                        allPawns.add(new Pawn((nextMove+errorMove%7), this)); 
                                                 
                    }
                }

            }
            
            displayBoard();

            if(currentPawnSouthNeighbours>2                                                 // s'il y a au moins trois pions sous le pion qui vient d'être joué
            || currentPawnNorthEastNeighbours + currentPawnSouthWestNeighbours>2       // ou répartis de par et d'autre sur l'un des trois autres axes possibles
            || currentPawnEastNeighbours + currentPawnWestNeighbours>2
            || currentPawnSouthEastNeighbours + currentPawnNorthWestNeighbours>2)
            {
                endRound=true;                                                                    // alors on provoque la sortie de boucle
            }
        }

        System.out.println("Game Over !");                                          // on annonce la fin de partie et le vainqueur
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