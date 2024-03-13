package locations;

import enemies.*;
import player.Player;

import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLocation(int id, Player player, String name, String explanation, Obstacle obstacle, String award, int maxObstacle) {
        super(id, player, name, explanation);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol burada " + this.getObstacle().getName() + " yaşıyor.");
        return true;
    }

    public int randomObstacleNumber() {
        Random random = new Random();
        return random.nextInt(this.maxObstacle) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
