package locations;

import enemies.*;
import player.*;

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
            String selectCase = input.nextLine().toUpperCase();
            if (selectCase.equals("S")) {
                if (this.combat(obsNumber)) {
                    System.out.println(this.getName() + " bölgesindeki tüm düşmanları yendiniz");
                    return true;
                } else {
                    if (this.getPlayer().getHealth() == 0) {
                        System.out.println("ÖLDÜNÜZ");
                        return false;
                    } else {
                        return true;
                    }
                }
                // Savaşma işlemi
            } else if (selectCase.equals("K")) {
                System.out.println("\n" + this.getName() + " bölgesinden kaçtınız");
                return true;
                // Kaçma işlemi
            } else {
                System.out.println("Hatalı giriş yaptınız !");
                // Hatalı giriş
            }
        }
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getStartHealth());
            playerStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur  veya  <K>aç  : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz");
                    this.getObstacle().setHealth(Math.max(0, this.getObstacle().getHealth() - this.getPlayer().getTotalDamage()));
                    this.afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println(this.getObstacle().getName() + " vurdu");
                        this.getPlayer().setHealth(Math.max(0, this.getPlayer().getHealth() - Math.max(0, this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock())));
                        this.afterHit();
                    }
                } else if (selectCombat.equals("K")) {
                    System.out.println("\n" + this.getName() + " bölgesinden kaçtınız");
                    return false;
                } else {
                    System.out.println("Hatalı giriş yaptınız !");
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz.");
                System.out.println(this.getObstacle().getMoney() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoney());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(int i) {
        System.out.println("Oyuncu değerleri:");
        this.getPlayer().printInfo();
        System.out.println("----------------------------");
        System.out.println(i + ". " + this.getObstacle().getName() + " değerleri:");
        this.getObstacle().printInfo();
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
