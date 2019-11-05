# Connect4
A Training project


1. Description du jeu

  Le jeu est un simple Puissance 4 PvP (pas de joueur contrôlé par l'ordinateur).

2. Implémentation technique

  Clonez le répertoire Git dans un dossier de votre choix sur votre ordinateur. Appelez le terminal de votre ordinateur sur ce dossier et entrez la commande javac MainConnect4.java pour compiler le code. Compilez également GameManager.java, Pawn.java et IAscripts.java. Vous pouvez par la suite executer le programme avec la commannde java MainConnect4.
  
  Le fonctionnement du programme est décrit sur ces deux schémas :
  
  - ici GameManager et Pawn https://i.imgur.com/oaOktjZ.png
  
  - ici MainConnect4 et IAscripts https://i.imgur.com/raULFCr.png

3. Notice d'utilisation

  Le jeu va vous informer de quel joueur c'est le tour.
  
  Celui-ci doit entrer la colonne dans laquelle il compte lacher son pion, sous la forme d'un simple chiffre entre 1 et 7
  
  Si l'Input entré est bon, le jeu va afficher la grille mise à jour avec le coup joué, et passer le tour au joueur suivant.
  
  S'il ne correspond pas à un chiffre entre 1 et 7, le programme va informer les joueurs que l'Input n'est pas bon, puis demander
  au joueur d'entrer un nouvel input, sans passer le tour au joueur suivant.
  
  Le programme déterminera lui-même si la partie est finie.

4. Exemple d'utilisation :

  Vous trouverez ci-dessous un exemple de partie type, qui se terminera en dix coups par la victoire du joueur 2.
  
  Vous pouvez entrer les inputs enregistrés dans ce tableau au fur et à mesure qu'ils vous seront demandés par le programme.

  Tour  : 1 2 3 4 5 6 7 8 9 10
  
  J1___ : 5 3 3 7 6 4 5 3 1 4
  
  J2___ : 3 5 6 7 5 5 6 3 4 6
  
  NOTE : Plusieurs tests ont par ailleurs été effectués pour s'assurer que le programme ne plante pas lorsque le joueur tente d'entrer des inputs qui ne sont pas attendus.
  
