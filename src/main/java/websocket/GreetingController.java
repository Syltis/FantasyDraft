package websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private PlayerList playerList = new PlayerList();

    @MessageMapping("/join")
    @SendTo("/fantasy/players/")
    public PlayerList join(HelloMessage message) throws Exception {
        playerList.addPlayer(new Player(message.getName()));
        return playerList;
    }

    @MessageMapping("/leave")
    @SendTo("/fantasy/players/")
    public PlayerList leave(HelloMessage message) throws Exception{
        playerList.removePlayer(message.getName());
        return playerList;
    }

    @MessageMapping("/pick")
    @SendTo("/fantasy/picks/")
    public Greeting greeting(PickMessage message) throws Exception{
        Thread.sleep(1000);
        return new Greeting("Player, "
                + HtmlUtils.htmlEscape(message.getName())+" picked: "+ HtmlUtils.htmlEscape(message.getPick()));

    }

    /*@Autowired
    public void setPlayerList(PlayerList playerList){
        this.playerList =playerList;
    }
*/
}


