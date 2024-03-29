package locations;

import player.*;
import java.util.Scanner;
public abstract class Location {
    private Player player;
    private String name, explanation,award;
    private int id;
    public static Scanner input = new Scanner(System.in);

    public Location(int id, Player player, String name, String explanation,String award) {
        this.id =id;
        this.explanation = explanation;
        this.player = player;
        this.name = name;
        this.award=award;
    }

    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}