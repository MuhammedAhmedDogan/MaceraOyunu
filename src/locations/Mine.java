package locations;

import enemies.*;
import player.*;

public class Mine extends BattleLocation {
    public Mine(Player player) {
        super(6, player, "Maden", "    :\tYılan yuvası", new Snake(), "Ödül: Her öldürülen yılandan Silah, Zırh veya Para düşebilir", 5);
    }

}
