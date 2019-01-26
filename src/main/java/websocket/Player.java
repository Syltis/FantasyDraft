package websocket;

public class Player {

    static int count = 0;

    private int id;
    String name;

    public Player(String name){
        count++;
        this.id = count;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
