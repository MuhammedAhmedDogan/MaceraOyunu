package locations;

import player.*;
public abstract class NormalLoc extends Location {

    public NormalLoc(int id, Player player, String name, String explanation,String award) {
        super(id, player, name, explanation,award);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
