package Connect4_V3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AIscripts {

    private Pawn[][] boardStateToAnalyse;

    private List<Integer> losingMoves = new ArrayList<Integer>();           // OPTIONNAL, WILL DO IF I HAVE SOME TIME LEFT : makes a list of terrible moves that will allow 
                                                                            // the opponent to win the following turn. This list is obtained by testing emergencyMove on an hypothetical
                                                                            // next turn board state after a given
    
    private HashMap<Integer, List<Integer>> parametersForBuildLineMove = new HashMap<Integer, List<Integer>>(); // Hopefully with that I'll be able to reduce the nbr of lines of code

    public AIscripts(Pawn[][] boardToAnalyse) {
        this.boardStateToAnalyse = boardToAnalyse;

        for (int i = 0; i < 7; i++) {
            parametersForBuildLineMove.put(i, new ArrayList<Integer>());
        }
        // parametersForBuildLineMove.get(1).add()
        
    }

    
    /**
     * This function simply returns a random number
     * 
     * @return a random number between 0 and 6.
     * 
     * OPTIONNAL, WILL DO IF I HAVE SOME TIME LEFT : inclinaison plus prononcée pour jouer au centre, par exemple,  11% | 13% | 17% | 18% | 17% | 13% | 11%  au lieu de 14.3% partout.
     * DONE                                                     (augmente les probabilités de produire des alignements puisqu'ils peuvent se déployer à gauche et à droite)
     *             
     */
    public int randomMove(){
        int rdm100 = (int)(Math.random()*100);

        if (rdm100<11){
            return 0;
        }else if(rdm100<24){
            return 1;
        }else if(rdm100<41){
            return 2;
        }else if(rdm100<59){
            return 3;
        }else if(rdm100<76){
            return 4;
        }else if(rdm100<89){
            return 5;
        }else{
            return 6;
        }
        //return (int)(Math.random()*7);
    }

    /**
     * This function will search through the board. 
     * It will identify places where two Pawns from the computer are aligned, and check if it can align a third. 
     * If possible, it'll return the number of  row to pick to do so, as an int.
     * 
     * OPTIONNAL, WILL DO IF I HAVE SOME TIME LEFT : starts blocking 2-Pawns alignments from the player if no such configuration is found for his own Pawns.
     * DONE
     * 
     * OPTIONNAL, WILL TRY TO DO IF I HAVE SOME TIME LEFT : verifies if it is actually useful to play the move. For instance, avoid the move in these cases : [X] [O] [O] [ ] [X] 
     * 
     * 
     * @return the number of row to pick to do so, as an int.
     */
    public int buildingLineMove() throws ArrayIndexOutOfBoundsException{

        for (int i = 0; i < boardStateToAnalyse.length; i++) {
            for (int j = 0; j < boardStateToAnalyse[i].length; j++) {
                if(boardStateToAnalyse[i][j]!=null){
                    if(boardStateToAnalyse[i][j].player1==false){
                        if(buildPatternNorth(i, j, 3).equals("OON")) {
    
                            if(j>1){
                                if(!IsBelowEmpty(i, j-2)){
                                    return i; 
                                }
                            }
                        }
                        if(buildPatternNorthEast(i, j, 3).equals("OON")){
        
                            if(i<5&&j>1){
                                if(!IsBelowEmpty(i+2, j-2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 3).equals("OON")){
    
                            if(i<5){
                                if(!IsBelowEmpty(i+2, j)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 3).equals("OON")){
        
                            if(i<5&&j<4){
                                if(!IsBelowEmpty(i+2, j+2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 3).equals("OON")){
        
                            if(i>1&&j<4){
                                if(!IsBelowEmpty(i-2, j+2)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 3).equals("OON")){
    
                            if(i>1){
                                if(!IsBelowEmpty(i-2, j)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 3).equals("OON")){
        
                            if(i>1&&j>1){
                                if(!IsBelowEmpty(i-2, j-2)){
                                    return i-2; 
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < boardStateToAnalyse.length; i++) {
            for (int j = 0; j < boardStateToAnalyse[i].length; j++) {
                if(boardStateToAnalyse[i][j]!=null){
                    if(boardStateToAnalyse[i][j].player1==true){
                        if(buildPatternNorth(i, j, 3).equals("XXN")) {
    
                            if(j>1){
                                if(!IsBelowEmpty(i, j-2)){
                                    return i; 
                                }
                            }
                        }
                        if(buildPatternNorthEast(i, j, 3).equals("XXN")){
        
                            if(i<5&&j>1){
                                if(!IsBelowEmpty(i+2, j-2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 3).equals("XXN")){
    
                            if(i<5){
                                if(!IsBelowEmpty(i+2, j)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 3).equals("XXN")){
        
                            if(i<5&&j<4){
                                if(!IsBelowEmpty(i+2, j+2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 3).equals("XXN")){
        
                            if(i>1&&j<4){
                                if(!IsBelowEmpty(i-2, j+2)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 3).equals("XXN")){
    
                            if(i>1){
                                if(!IsBelowEmpty(i-2, j)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 3).equals("XXN")){
        
                            if(i>1&&j>1){
                                if(!IsBelowEmpty(i-2, j-2)){
                                    return i-2; 
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * This function will search through the board.  
     * It will identify patterns in the Pawns from the human player that are threatening an immediate win. 
     * If he finds any, it'll return the row to pick to prevent this, as an int.
     *                     
     * @return the row to pick to prevent this, as an int.
     */
    public int emergencyMove() {

        for (int i = 0; i < boardStateToAnalyse.length; i++) {
            for (int j = 0; j < boardStateToAnalyse[i].length; j++) {
                if(boardStateToAnalyse[i][j]!=null){
                    if(boardStateToAnalyse[i][j].player1==true){
                        if(buildPatternNorth(i, j, 4).equals("XXXN")) {
                            if(j>2){
                                if(!IsBelowEmpty(i, j-3)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i; 
                                }
                            }
                        }

                        if(buildPatternNorthEast(i, j, 4).equals("XXXN")){
                            if(i<4&&j>2){
                                if(!IsBelowEmpty(i+3, j-3)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternNorthEast(i, j, 4).equals("XXNX")){
                            if(i<4&&j>2){
                                if(!IsBelowEmpty(i+2, j-2)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 4).equals("XXXN")){
                            if(i<4){
                                if(!IsBelowEmpty(i+3, j)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 4).equals("XXNX")){
                            if(i<4){
                                if(!IsBelowEmpty(i+2, j)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 4).equals("XXXN")){
                            if(i<4&&j<3){
                                if(!IsBelowEmpty(i+3, j+3)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 4).equals("XXNX")){
                            if(i<4&&j<3){
                                if(!IsBelowEmpty(i+2, j+2)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 4).equals("XXXN")){
                            if(i>2&&j<3){
                                if(!IsBelowEmpty(i-3, j+3)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 4).equals("XXNX")){
                            if(i>2&&j<3){
                                if(!IsBelowEmpty(i-2, j+2)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 4).equals("XXXN")){
                            if(i>2){
                                if(!IsBelowEmpty(i-3, j)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 4).equals("XXNX")){
                            if(i>2){
                                if(!IsBelowEmpty(i-2, j)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 4).equals("XXXN")){
                            if(i>2&&j>2){
                                if(!IsBelowEmpty(i-3, j-3)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 4).equals("XXNX")){
                            if(i>2&&j>2){
                                if(!IsBelowEmpty(i-2, j-2)){
                                    System.out.println("NOPE ! WIN DENIED.");
                                    return i-2; 
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("emergencyMove returned -1");
        return -1;
    }

    public int shortEmergencyMove() {
        for (int i = 0; i < boardStateToAnalyse.length; i++) {
            for (int j = 0; j < boardStateToAnalyse[i].length; j++) {
                if(boardStateToAnalyse[i][j]!=null){
                    if(boardStateToAnalyse[i][j].player1==true){

                    }
                }
            }
        }
        return -1;
    }

    private int buildPatternEmergencyMove(int direction){
        

        return -1;
    }

    
    





    /**
     * This function will search through the board.  
     * It will identify patterns in the Pawns from the computer that are allowing an immediate win.
     * If he finds any, it'll return the row to pick to do so, as an int.
     * 
     * @return the row to pick to win the game.
     */
    public int winningMove() {

        for (int i = 0; i < boardStateToAnalyse.length; i++) {
            for (int j = 0; j < boardStateToAnalyse[i].length; j++) {
                if(boardStateToAnalyse[i][j]!=null){
                    if(boardStateToAnalyse[i][j].player1==false){
                        if(buildPatternNorth(i, j, 4).equals("OOON")) {
                            if(j>2){
                                if(!IsBelowEmpty(i, j-3)){
                                    return i; 
                                }
                            }
                        }
                        if(buildPatternNorthEast(i, j, 4).equals("OOON")){
                            if(i<4&&j>2){
                                if(!IsBelowEmpty(i+3, j-3)){
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternNorthEast(i, j, 4).equals("OONO")){
                            if(i<4&&j>2){
                                if(!IsBelowEmpty(i+2, j-2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 4).equals("OOON")){
                            if(i<4){
                                if(!IsBelowEmpty(i+3, j)){
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternEast(i, j, 4).equals("OONO")){
                            if(i<4){
                                if(!IsBelowEmpty(i+2, j)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 4).equals("OOON")){
                            if(i<4&&j<3){
                                if(!IsBelowEmpty(i+3, j+3)){
                                    return i+3; 
                                }
                            }
                        }
                        if(buildPatternSouthEast(i, j, 4).equals("OONO")){
                            if(i<4&&j<3){
                                if(!IsBelowEmpty(i+2, j+2)){
                                    return i+2; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 4).equals("OOON")){
                            if(i>2&&j<3){
                                if(!IsBelowEmpty(i-3, j+3)){
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternSouthWest(i, j, 4).equals("OONO")){
                            if(i>2&&j<3){
                                if(!IsBelowEmpty(i-2, j+2)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 4).equals("OOON")){
                            if(i>2){
                                if(!IsBelowEmpty(i-3, j)){
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternWest(i, j, 4).equals("OONO")){
                            if(i>2){
                                if(!IsBelowEmpty(i-2, j)){
                                    return i-2; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 4).equals("OOON")){
                            if(i>2&&j>2){
                                if(!IsBelowEmpty(i-3, j-3)){
                                    return i-3; 
                                }
                            }
                        }
                        if(buildPatternNorthWest(i, j, 4).equals("OONO")){
                            if(i>2&&j>2){
                                if(!IsBelowEmpty(i-2, j-2)){
                                    return i-2; 
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("winningMove returned -1");
        return -1;
    }

    /**
     * This function (boolean) will check if the spot right below another (whose
     * coordinates are given as arguments) is empty.
     * 
     * @param col            the column of the spot
     * @param row            the row of the spot
     * @return true if boardToAnalyse[row][col-1] = null; false otherwise.
     */
    private boolean IsBelowEmpty(int col, int row) {
        try {
            if(boardStateToAnalyse[col][row+1] == null){
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {    // Happens when we try to check below a case at the bottom of the board
                if(row+1 == 6){ 
                    return false;                       // we return false because technically the border acts as a pawn there (it allow the IA to play on the spot entered as argument)
                }else{System.out.println("weird... " + row);}
            }
        return false;
    }

    private boolean isMoveBad() {
        
        return false;
    }



    /**
     * Starting from boardToAnalyse[col][row], will store in a String of length patternLength the values stored in the following indexes of boardToAnalyse in this direction.
     * It'll help the different Move methods to identify pattern that offer opportunities, such as 3 IA Pawns in a row followed by an empty spot.
     * 
     * @param col
     * @param row
     * @param patternLength
     * @return
     */
    private String buildPatternNorth(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
                // return pattern;              we actually need to keep this, it can be useful when checking for the human player win condition.
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(row==0){
                return pattern;
            }else{
                row--;
            }
        }
        return pattern;
    }

    private String buildPatternNorthEast(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(row==0 || col==6){

                return pattern;
            }else{
                row--;
                col++;
            }
        }
        return pattern;
    }

    private String buildPatternEast(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(col==6){
                return pattern;
            }else{
                col++;
            }
        }  
        return pattern;
    }

    private String buildPatternSouthEast(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(row==5 ||col==6){
                return pattern;
            }else{
                row++;
                col++;
            }
        }
        return pattern;
    }

    private String buildPatternSouthWest(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(row==5 ||col==0){
                return pattern;
            }else{
                row++;
                col--;
            }
        }
        return pattern;
    }

    private String buildPatternWest(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }

            if(col==0){
                return pattern;
            }else{
                col--;
            }
        }
        return pattern;
    }

    private String buildPatternNorthWest(int col, int row, int patternLength) /*throws IndexOutOfBoundsException*/{
        String pattern = "";
        while(pattern.length()<patternLength){
            if(boardStateToAnalyse[col][row]==null){
                pattern += "N";
            }else if(boardStateToAnalyse[col][row].player1==true){
                pattern += "X";
            }else if(boardStateToAnalyse[col][row].player1==false){
                pattern += "O";
            }
            if(row==0 || col==0){
                return pattern;
            }else{
                row--;
                col--;
            }
        }
        return pattern;
    }

}