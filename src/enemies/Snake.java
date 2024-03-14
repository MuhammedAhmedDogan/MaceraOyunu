package enemies;

import java.util.Random;

public class Snake extends Obstacle {
    public Snake() {
        super(4, "Yılan", 0, 12, 0);
        this.setDamage(this.damage);
    }

    @Override
    public void setDamage(int damage) {
        Random random = new Random();
        this.damage = random.nextInt(4) + 3;
    }

    @Override
    public void printInfo() {
        System.out.println("Hasar: " + this.getDamage() + "\tSağlık: " + this.getHealth() + "\tGanimet: Para, Zırh veya Silah çıkabilir\n");
    }
}
