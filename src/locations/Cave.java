package locations;

import enemies.*;
import player.Player;

public class Cave extends BattleLocation {
    public Cave(Player player) {
        super(3, player, "Mağara", "    :\tZombi yuvası", new Zombie(), "Ödül: Yemek",3);
    }


}

