import java.util.Objects;
import java.util.Random;
import javax.swing.*;

public class Game {
    public int turnNumber = 0;
    public static int pileSize = 0;
    public static String firstPlayer;
    public int choice = 0;
    public String playerOne;
    public String playerTwo;
    public static JFrame frame = new GameFrameForm();
    public String response = "";
    public int maxTileChoice;
    public boolean invalidMove;
    public String winner;

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("The Game of Nim");


    }
    public void startGame(){
        Random randInt = new Random();
        while (pileSize < 10) {
            pileSize = randInt.nextInt(100);
        }
        double firstPlayerCheck = Math.random();
        if (firstPlayerCheck < 0.5) {
            firstPlayer = "p1";
        } else {
            firstPlayer = "p2";
        }
    }

    public boolean checkPlayer(){
        boolean humanPlayer;
        humanPlayer = !Objects.equals(Player.getName(turnNumber, firstPlayer), "Computer");
        return humanPlayer;
    }

    public void promptHumanMove(){
        response = ("");
        int maxTileChoice = Pile.getMaxLegalMove(pileSize);
        if (maxTileChoice > 1){
            response = (response + "\n Choose between 1 and " + maxTileChoice + " tiles.");
        }
    }

    public void doHumanMove(int choice){
        response = ("");
        invalidMove = true;
        while (invalidMove) {
            if (choice > maxTileChoice || choice == 0) {
                return;
            } else {
                invalidMove = false;
                response = (" " + Player.getName(turnNumber + 1, firstPlayer) + " chose to take " + choice + " tiles.");
                pileSize = Player.makeMove(choice, pileSize);
                response = (response + "\n There are currently " + pileSize + " tiles. \n Please press Change Turn.");
            }
        }
    }

    public void errorResult(){
        response = (" Invalid choice. Choose between 1 and " + maxTileChoice + " tiles.");
    }

    public void doComputerMove(){
        response = ("");
        Random randChoice = new Random();
        response = (response + "\n It is the computer's turn. They can take a maximum of " + Pile.getMaxLegalMove(pileSize) + " tiles.");
        boolean invalidCheck = true;
        choice = randChoice.nextInt(Pile.getMaxLegalMove(pileSize));
        while (invalidCheck) {
            if (choice == 0) {
                choice = randChoice.nextInt(Pile.getMaxLegalMove(pileSize));
            } else {
                invalidCheck = false;
            }
        }
        response = (response + "\n " + Player.getName(turnNumber, firstPlayer) + " chose to take " + choice + " tiles.");
        pileSize = Player.makeMove(choice, pileSize);
        response = (response + "\n There are currently " + pileSize + " tiles. \n Please press Change Turn.");
    }

    public void finishGame(){
        response = ("");
        while (pileSize > 0) {
            maxTileChoice = Pile.getMaxLegalMove(pileSize);
            if (maxTileChoice == 1) {
                turnNumber++;
                response = (response + "\n The only legal move is 1 tile, so I am choosing 1 for " + Player.getName(turnNumber + 1, firstPlayer) + " .");
                choice = 1;
                pileSize = Player.makeMove(choice, pileSize);
            } else if (pileSize == 1) {
                turnNumber++;
                choice = 1;
                pileSize = Player.makeMove(choice, pileSize);
                winner = Player.getName(turnNumber, firstPlayer);
                response = (response + "\n The only legal move is 1 tile, so I am choosing 1 for " + Player.getName(turnNumber + 1, firstPlayer) + ". \n Game over -- " + Player.getName(turnNumber, firstPlayer) + " wins!");

            }
        }
        turnNumber = 0;
        Player.playerList.remove(0);
        Player.playerList.remove(0);
        System.out.println(Player.playerList);
    }

    public void startGame21(){
        pileSize = 21;

        double firstPlayerCheck = Math.random();
        if (firstPlayerCheck < 0.5) {
            firstPlayer = "p1";
        } else {
            firstPlayer = "p2";
        }

    }




}




