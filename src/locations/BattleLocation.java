package locations;

import enemies.*;
import player.Player;

import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private int maxObstacle;

    public BattleLocation(int id, Player player, String name, String explanation, Obstacle obstacle, String award, int maxObstacle) {
        super(id, player, name, explanation, award);
        this.obstacle = obstacle;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("\n----------- " + this.getName() + " -----------");
        System.out.println("Dikkatli ol burada şu an " + obsNumber + " tane " + this.getObstacle().getName() + " var.\n");

        for (; ; ) {
            System.out.print("<S>avaş\t<K>aç\t:");
            String selectCase = input.nextLine();
            selectCase = selectCase.toUpperCase();
            if (selectCase.equals("S")) {
                System.out.println("Savaş işlemleri olacak");
                break;
                // Savaşma işlemi
            } else if (selectCase.equals("K")) {
                System.out.println("Kaçış");
                break;
                // Kaçma işlemi
            } else {
                System.out.println("Hatalı giriş yaptınız !");
                // Hatalı giriş
            }
        }

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

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
