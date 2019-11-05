package Connect4_V3;

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
            if(this.row != 0 && this.column != 6){                  // On s'assure qu'on ne s'apprête pas à tenter de consulter une case inexistante dans le tableau
                if(manager.board[this.column+1][this.row-1]!=null){            // S'il y a bien une valeur, un pion, à cet indice du tableau
                    if(this.player1==manager.board[this.column+1][this.row-1].player1){    // Si ce pion appartient au même joueur que celui depuis lequel cette fonction est appelée
                        manager.currentPawnNorthEastNeighbours++;                                  // Alors on incrémente le compteur de voisins du dernier pion joué.        
                        manager.board[this.column+1][this.row-1].checkYourNeighbour(direction);    // Puis appelle cette fonction depuis ce pion pour voir un cran plus loin
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

        if(manager.currentTurn%2==1){                      // si le tour est impair, alors ce pion appartient au joueur 1. S'il est pair, au joueur 2.
            this.player1=true;                          
        }else{
            this.player1=false;                         // on attribue au boolean player1 la bonne valeur en conséquence.
        }
        this.column = columnPicked;                     // on sait déjà que la variable column du pion qu'on instancie sera celle que le joeur vient de choisir


        for(int i = 0 ; i < 6 ; i++){                   // on boucle autant de fois que la profondeur d'une colonne (i est incrémenté)

            if(manager.board[columnPicked][i]!=null){      // s'il y a déjà un pion à la profondeur i
                manager.board[columnPicked][i-1]=this;     // on place dans board le pion qu'on est en train d'instancier, une rangée au dessus.
                this.row=i-1;                           // on sait maintenant quelle valeur attribuer à la variable row de notre pion.                    
                break;
            }
            if(i==5){                                   // si on atteint le fond du plateau sans rencontrer de case occupée
                manager.board[columnPicked][i]=this;       // on place dans le board le pion qu'on est en train d'instancier, au plus bas de la colonne.
                this.row = i;                           // on sait maintenant quelle valeur attribuer à la variable row de notre pion.   
                break;
            }
        }
        System.out.println("Un pion vient d'être placé à la rangée " + this.row + " et à la colonne " + this.column);


        this.checkYourNeighbour("ne");                  // on va voir si ce pion a un voisin de sa couleur au nord-est. Si oui on incrémentera une variable de main puis
        this.checkYourNeighbour("e");                   // on ira voir un cran plus loin. Pour plus de détails voir le descriptif de la fonction checkYourNeighbour
        this.checkYourNeighbour("se");
        this.checkYourNeighbour("s");                   // pareil mais pour sud
        this.checkYourNeighbour("sw");                  // sud-ouest
        this.checkYourNeighbour("w");                   // etc.
        this.checkYourNeighbour("nw");
    }
}