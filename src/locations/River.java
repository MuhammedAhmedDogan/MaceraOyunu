package locations;

import enemies.*;
import player.Player;

public class River extends BattleLocation{
    public River(Player player) {
        super(5, player, "Nehir     ", "Ayı yuvası", new Bear(), "Su",2);
    }
}
