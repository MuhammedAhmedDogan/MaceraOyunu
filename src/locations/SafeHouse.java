package locations;

import player.*;
public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(1, player, "Güvenli Ev", ":\tSağlığınızı doldurur.","");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\nGüvenli evdesiniz.");
        System.out.println("Sağlığınız yenilendi.");
        this.getPlayer().setHealth(this.getPlayer().getStartHealth());
        return true;
    }
}
