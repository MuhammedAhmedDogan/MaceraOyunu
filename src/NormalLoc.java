public abstract class NormalLoc extends Location {

    public NormalLoc(int id, Player player, String name, String explanation) {
        super(id, player, name, explanation);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}
