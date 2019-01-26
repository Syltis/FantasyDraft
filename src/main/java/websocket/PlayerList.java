package websocket;

import java.util.ArrayList;
import java.util.List;

//@Component
public class PlayerList {

    private List<Player> playerList = new ArrayList<>();

    public PlayerList() {
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player){

        this.playerList.add(player);
        System.out.println("Player added: " + player.getName());

    }

    public void removePlayer(String player) {
        for (Player p: playerList){
            if(p.name.equals(player)){
                playerList.remove(p);
            }
        }
    }
}
