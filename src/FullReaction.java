public class FullReaction {
    private HalfReaction one;
    private HalfReaction two;

    private boolean valid;

    public static final int LEFT = -1, STILL = 0, RIGHT = 1;

    public FullReaction() {
        one = two = HalfReaction.halfReactions[0];
        valid = false;
    }

    public void update() {
        valid = one != HalfReaction.halfReactions[0] && two != HalfReaction.halfReactions[0] && one != two;
    }

    public int direction() {
        if (!valid) return 0;
        if (one == two) return STILL;
        else {
            return one.getECell() < two.getECell() ? RIGHT : LEFT;
        }
    }

    public double voltage() {
        if (!valid) return 0;
        double a = one.getECell();
        double b = two.getECell();
        if (a < b) a = -a;
        else b = -b;
        return a + b;
    }

    public HalfReaction getOne() {
        return one;
    }

    public void setOne(HalfReaction one) {
        this.one = one;
        update();
    }

    public HalfReaction getTwo() {
        return two;
    }

    public void setTwo(HalfReaction two) {
        this.two = two;
        update();
    }

    public boolean isValid() {
        return valid;
    }
}
