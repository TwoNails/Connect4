// package Connect4_V3;

class Pawn {

    public GameManager manager;

    boolean player1;

    int row;                    
    int column;            // ces trois variables seront initialisées par le constructeur. Elles serviront à l'identification du pion par certaines méthodes.

    /** 
     * This function checks if the next Pawn, if any, in a given direction is owned by the same player than the one that called the function.
     * If so, it will increment a static variable on Main that we'll check later to see if the victory condition has been met.
     * Then, it will call itself from that Pawn it just found. This processus will repeat until no such Pawn can be found.
     * 
     * @param direction
     */
    public void checkYourNeighbour(String direction){

        switch(direction){                               

            case "ne" :
            if(this.row != 0 && this.column != 6){                  // We make sure that we're not about to try and check an inexistant index of the board
                if(manager.board[this.column+1][this.row-1]!=null){            // If there's actually a non NULL something at this index, a Pawn,
                    if(this.player1==manager.board[this.column+1][this.row-1].player1){    // If the Pawn we're checking is owned by the same player that the one Pawn from which this function is called
                        manager.currentPawnNorthEastNeighbours++;                                  // Then we increment the neighbour counter for this axis.      
                        manager.board[this.column+1][this.row-1].checkYourNeighbour(direction);    // Then we call this function from the Pawn we just checked to check for his own potential neighbours
                        break;
                    }
                }
            }
            break;

            case "e" :
            if(this.column != 6){
                if(manager.board[this.column+1][this.row]!=null){
                    if(this.player1==manager.board[this.column+1][this.row].player1){     
                        manager.currentPawnEastNeighbours++;
                        manager.board[this.column+1][this.row].checkYourNeighbour(direction);     
                        break;
                    }
                }
            }
            break;

            case "se" :
            if(this.row !=5 && this.column != 6){
                if(manager.board[this.column+1][this.row+1]!=null){
                    if(this.player1==manager.board[this.column+1][this.row+1].player1){
                        manager.currentPawnSouthEastNeighbours++;
                        manager.board[this.column+1][this.row+1].checkYourNeighbour(direction);
                        break;
                    }
                }
            }
            break;

            case "s" :
            if(this.row != 5){ 
                if(manager.board[this.column][this.row+1]!=null){
                    if(this.player1==manager.board[this.column][this.row+1].player1){
                        manager.currentPawnSouthNeighbours++;
                        manager.board[this.column][this.row+1].checkYourNeighbour(direction);
                        break;
                    }
                }
            }
            break;

            case "sw" :
            if(this.row != 5 && this.column != 0){ 
                if(manager.board[this.column-1][this.row+1]!=null){
                    if(this.player1==manager.board[this.column-1][this.row+1].player1){
                        manager.currentPawnSouthWestNeighbours++;
                        manager.board[this.column-1][this.row+1].checkYourNeighbour(direction);
                        break;
                    }
                }
            }
            break;

            case "w" :
            if(this.column != 0){
                if(manager.board[this.column-1][this.row]!=null){
                    if(this.player1==manager.board[this.column-1][this.row].player1){
                        manager.currentPawnWestNeighbours++;
                        manager.board[this.column-1][this.row].checkYourNeighbour(direction);
                        break;
                    }
                }
            }
            break;

            case "nw" : 
            if(this.row != 0 && this.column !=0){
                if(manager.board[this.column-1][this.row-1]!=null){
                    if(this.player1==manager.board[this.column-1][this.row-1].player1){
                        manager.currentPawnNorthWestNeighbours++;
                        manager.board[this.column-1][this.row-1].checkYourNeighbour(direction);
                        break;
                    }
                }
            }
            break;
        }    
    }

    public Pawn(int columnPicked, GameManager currentManager){
        this.manager=currentManager;

        if(manager.currentTurn%2==1){                      // if the turn is odd, then this Pawn is owned by the player 1. Otherwise, by the player 2.
            this.player1=true;                          
        }else{
            this.player1=false;                         // we set the boolean player 1 consequently
        }
        this.column = columnPicked;                     // we already know that the column attribute of the Pawn we're instanciating is the the one the player just chose.


        for(int i = 0 ; i < 6 ; i++){                   // we loop for as many times as the depth of a columun

            if(manager.board[columnPicked][i]!=null){      // if ther'es a Pawn at the depth i
                manager.board[columnPicked][i-1]=this;     // we place on the board the Pawn we're instanciating, one row above. IndexOutOfBoundsException are being dealt with by GamemManager
                this.row=i-1;                           // we now know which value to give to its row attribute.                    
                break;
            }
            if(i==5){                                   // if we rech the floor of the board without encountering any Pawn
                manager.board[columnPicked][i]=this;       // we place on the board the Pawn we are instanciating , on the last row.
                this.row = i;                           // we now know which value to give to its row attribute.   
                break;
            }
        }


        this.checkYourNeighbour("ne");                  // we check if the Pawn we're instanciating has a neighbour of the same player in the north-east direction. If so, we'll increment 
        this.checkYourNeighbour("e");                   // a certain value in GameManager then we'll check one step further. For more details see CheckYourNeighbour higher on this page
        this.checkYourNeighbour("se");
        this.checkYourNeighbour("s");                   // same but for the south
        this.checkYourNeighbour("sw");                  // south-west
        this.checkYourNeighbour("w");                   // etc.
        this.checkYourNeighbour("nw");
    }
}