import java.awt.*;

public class NoneReaction extends HalfReaction {
    public NoneReaction() {
        super("None", 0, 0, 0, 0);
    }

    @Override
    public Color getMetalColor() {
        return new Color(0,0,0,0);
    }

    @Override
    public Color getSolutionColor() {
        return new Color(0,0,0,0);
    }
}
