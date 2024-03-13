package locations;

import enemies.*;
import player.Player;

public class Forest extends BattleLocation{
    public Forest(Player player) {
        super(4, player, "Orman     ", "Vampir yuvası", new Vampire(), "Odun",3);
    }
}
