package enemies;

public class Obstacle {
    private int id, damage, health, startHealth, money;
    private String name;

    public Obstacle(int id, String name, int damage, int health, int money) {
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.startHealth = health;
        this.name = name;
        this.money = money;
    }

    public void printInfo() {
        System.out.println("\nHasar: " + this.getDamage() + "\tSağlık: " + this.getHealth() + "\tPara: " + this.getMoney());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getStartHealth() {
        return startHealth;
    }

    public void setStartHealth(int startHealth) {
        this.startHealth = startHealth;
    }
}
