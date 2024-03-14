package locations;

import player.*;
public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(1, player, "Güvenli Ev", ":\tSağlığınızı doldurur.","");
    }

    @Override
    public boolean onLocation() {
        if (this.getPlayer().getInventory().isFood()&&this.getPlayer().getInventory().isFirewood()&&this.getPlayer().getInventory().isWater()){
            System.out.println("*** Yemek, Odun ve Su toplayarak Güvenli Eve döndünüz ***");
            return false;
        }else{
            System.out.println("\nGüvenli evdesiniz.");
            System.out.println("Sağlığınız yenilendi.");
            this.getPlayer().setHealth(this.getPlayer().getStartHealth());
            return true;
        }
    }
}
