package websocket;

public class PickMessage {

    private String name;
    private String pick;

    public PickMessage() {
    }

    public PickMessage(String name, String pick) {
        this.name = name;
        this.pick = pick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }
}
