package locations;

import enemies.*;
import player.Player;

public class Cave extends BattleLocation {
    public Cave(Player player) {
        super(3, player, "Mağara    ", "Zombi yuvası", new Zombie(), "Yemek",3);
    }


}

