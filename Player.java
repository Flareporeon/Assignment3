import java.util.ArrayList;
import java.util.Objects;

public class Player {
    public static ArrayList<String> playerList = new ArrayList(2);



    public static ArrayList<String> nameInputs(String playerOne, String playerTwo){
        playerList.add(playerOne);
        playerList.add(playerTwo);

        return playerList;
    }

    public static int makeMove(int removal, int pileSize){


        pileSize -= removal;
        return pileSize;
    }

    public static String getName(int turnNumber, String randTurn){
        String name = "";
        if (turnNumber%2 == 1 && Objects.equals(randTurn, "p1")){
            name = playerList.get(0);
        } else if (turnNumber%2 == 0 && Objects.equals(randTurn, "p2")){
            name = playerList.get(0);
        } else if (turnNumber%2 == 1 && Objects.equals(randTurn, "p2")){
            name = playerList.get(1);
        } else if (turnNumber%2 == 0 && Objects.equals(randTurn, "p1")){
            name = playerList.get(1);
        }


        return name;

    }
}
