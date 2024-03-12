package locations;

import player.*;
public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(1, player, "Güvenli Ev", "Sağlığınızı doldurur.");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz.");
        System.out.println("Sağlığınız yenilendi.");
        return true;
    }
}
