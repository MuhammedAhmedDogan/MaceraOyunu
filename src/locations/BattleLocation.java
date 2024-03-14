package locations;

import enemies.*;
import items.Armor;
import items.Weapon;
import player.*;

import java.util.Objects;
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
                    System.out.println("--------------------------------------");
                    System.out.println(this.getName() + " bölgesindeki tüm düşmanları yendiniz");
                    if (this.getId() == 3) {
                        System.out.println("*** Yemek kazandınız ***");
                        System.out.println("--------------------------------------");
                        this.getPlayer().getInventory().setFood(true);
                    }
                    if (this.getId() == 4) {
                        System.out.println("*** Odun kazandınız ***");
                        System.out.println("--------------------------------------");
                        this.getPlayer().getInventory().setFirewood(true);
                    }
                    if (this.getId() == 5) {
                        System.out.println("*** Su kazandınız ***");
                        System.out.println("--------------------------------------");
                        this.getPlayer().getInventory().setWater(true);
                    }
                    if (this.getId() == 6) {
                        System.out.println("*** Ganimeti topladınız ***");
                        System.out.println("--------------------------------------");
                        this.getPlayer().getInventory().setTrophy(true);
                    }
                    return true;
                } else {
                    if (this.getPlayer().getHealth() == 0) {
                        System.out.println("*** ÖLDÜNÜZ *** ");
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
            this.getObstacle().setDamage(this.getObstacle().getDamage());
            this.printStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur  veya  <K>aç  : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    if (this.isFirstAttack()) {
                        System.out.println("Siz vurdunuz");
                        this.getObstacle().setHealth(Math.max(0, this.getObstacle().getHealth() - this.getPlayer().getTotalDamage()));
                        this.afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println(this.getObstacle().getName() + " vurdu");
                            this.getPlayer().setHealth(Math.max(0, this.getPlayer().getHealth() - Math.max(0, this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock())));
                            this.afterHit();
                        }
                    } else {
                        System.out.println(this.getObstacle().getName() + " vurdu");
                        this.getPlayer().setHealth(Math.max(0, this.getPlayer().getHealth() - Math.max(0, this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock())));
                        this.afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println("Siz vurdunuz");
                            this.getObstacle().setHealth(Math.max(0, this.getObstacle().getHealth() - this.getPlayer().getTotalDamage()));
                            this.afterHit();
                        }
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
                if (this.getObstacle().getId() == 4) {
                    randomItemDrop();
                } else {
                    System.out.println(this.getObstacle().getMoney() + " para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoney());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                }

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

    public void printStats(int i) {
        System.out.println("\nOyuncu değerleri:");
        this.getPlayer().printInfo();
        System.out.println("----------------------------");
        System.out.println(i + ". " + this.getObstacle().getName() + " değerleri:");
        this.getObstacle().printInfo();
    }

    public void randomItemDrop() {
        Random random = new Random();
        double chanceDrop = random.nextDouble() * 100;
        if (chanceDrop < 15.0) {
            double chanceWeapon = random.nextDouble() * 100;
            if (chanceWeapon < 20) {
                System.out.println("---- Düşmandan Tüfek çıktı ----");
                Weapon droppedWeapon = Weapon.getWeaponObjID(3);
                if (this.getPlayer().getInventory().getWeapon().getDamage() >= Objects.requireNonNull(droppedWeapon).getDamage()) {
                    System.out.println("Kullandığınız silah bu silah ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Tüfek kuşanıldı.");
                    this.getPlayer().getInventory().setWeapon(droppedWeapon);
                }
            } else if (chanceWeapon < 50) {
                System.out.println("---- Düşmandan Kılıç çıktı ----");
                Weapon droppedWeapon = Weapon.getWeaponObjID(2);
                if (this.getPlayer().getInventory().getWeapon().getDamage() >= Objects.requireNonNull(droppedWeapon).getDamage()) {
                    System.out.println("Kullandığınız silah bu silah ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Kılıç kuşanıldı.");
                    this.getPlayer().getInventory().setWeapon(droppedWeapon);
                }
            } else {
                System.out.println("---- Düşmandan Tabanca çıktı ----");
                Weapon droppedWeapon = Weapon.getWeaponObjID(1);
                if (this.getPlayer().getInventory().getWeapon().getDamage() >= Objects.requireNonNull(droppedWeapon).getDamage()) {
                    System.out.println("Kullandığınız silah bu silah ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Tabanca kuşanıldı.");
                    this.getPlayer().getInventory().setWeapon(droppedWeapon);
                }
            }
        } else if (chanceDrop < 30.0) {
            double chanceArmor = random.nextDouble() * 100;
            if (chanceArmor < 20) {
                System.out.println("---- Düşmandan Ağır Zırh çıktı ----");
                Armor droppedArmor = Armor.getArmorObjID(3);
                if (this.getPlayer().getInventory().getArmor().getBlock() >= Objects.requireNonNull(droppedArmor).getBlock()) {
                    System.out.println("Kullandığınız zırh bu zırh ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Ağır Zırh kuşanıldı.");
                    this.getPlayer().getInventory().setArmor(droppedArmor);
                }
            } else if (chanceArmor < 50) {
                System.out.println("---- Düşmandan Orta Zırh çıktı ----");
                Armor droppedArmor = Armor.getArmorObjID(2);
                if (this.getPlayer().getInventory().getArmor().getBlock() >= Objects.requireNonNull(droppedArmor).getBlock()) {
                    System.out.println("Kullandığınız zırh bu zırh ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Orta Zırh kuşanıldı.");
                    this.getPlayer().getInventory().setArmor(droppedArmor);
                }
            } else {
                System.out.println("---- Düşmandan Hafif Zırh çıktı ----");
                Armor droppedArmor = Armor.getArmorObjID(1);
                if (this.getPlayer().getInventory().getArmor().getBlock() >= Objects.requireNonNull(droppedArmor).getBlock()) {
                    System.out.println("Kullandığınız zırh bu zırh ile aynı veya daha güçlü olduğu için alınmadı.");
                } else {
                    System.out.println("Hafif Zırh kuşanıldı.");
                    this.getPlayer().getInventory().setArmor(droppedArmor);
                }
            }
        } else if (chanceDrop < 55.0) {
            double chanceMoney = random.nextDouble() * 100;
            System.out.println("---- Düşmandan Para çıktı ----");
            if (chanceMoney < 20) {
                System.out.println(10 + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else if (chanceMoney < 50) {
                System.out.println(5 + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                System.out.println(1 + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            }
        } else {
            System.out.println("Bu düşmandan hiçbir eşya çıkmadı.");
        }
    }

    public int randomObstacleNumber() {
        Random random = new Random();
        return random.nextInt(this.maxObstacle) + 1;
    }

    public boolean isFirstAttack() {
        Random random = new Random();
        return random.nextBoolean();
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
