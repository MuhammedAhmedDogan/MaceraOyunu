package locations;

import enemies.*;
import player.Player;

public class River extends BattleLocation{
    public River(Player player) {
        super(5, player, "Nehir", "     :\tAyı yuvası    ", new Bear(), "Ödül: Su",2);
    }
}
