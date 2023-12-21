import java.awt.*;

public class HalfReaction {
    public final static HalfReaction[] halfReactions = {
            new NoneReaction(),
            new HalfReaction("Au^3+(aq) + 3e^- -> Au(s)", 1.50, 136, 139, 141),
            new HalfReaction("Ag^+(aq) + e^- -> Ag(s)", 0.80, 255, 215, 0),
            new HalfReaction("Cu^+(aq) + e^- -> Cu(s)", 0.52, 184, 115, 51),
            new HalfReaction("Fe^3+(aq) + 3e^- -> Fe(s)", -0.036, 165, 156, 148),
            new HalfReaction("Pb^2+(aq) + 2e^- -> Pb(s)", -0.13, 95, 98, 106),
            new HalfReaction("Sn^2+(aq) + 2e^- -> Sn(s)", -0.14, 89, 78, 58),
            new HalfReaction("Ni^2+(aq) + 2e^- -> Ni(s)", -0.23, 114, 116, 114),
            new HalfReaction("Cd^2+(aq) + 2e^- -> Cd(s)", -0.40, 255, 246, 0),
            new HalfReaction("Fe^2+(aq) + 2e^- -> Fe(s)", -0.45, 165, 156, 148),
            new HalfReaction("Cr^3+(aq) + 3e^- -> Cr(s)", -0.73, 198, 200, 201),
            new HalfReaction("Zn^2+(aq) + 2e^- -> Zn(s)", -0.76, 186, 196, 200),
            new HalfReaction("Mn^2+(aq) + 2e^- -> Mn(s)", -1.18, 0, 38, 230),
            new HalfReaction("Al^3+(aq) + 3e^- -> Al(s)", -1.66, 136, 139, 141),
            new HalfReaction("Mg^2+(aq) + 2e^- -> Mg(s)", -2.37, 50, 100, 208),
            new HalfReaction("Na^+(aq) + e^- -> Na(s)", -2.71, 200, 200, 200),
            new HalfReaction("Ca^2+(aq) + 2e^- -> Ca(s)", -2.76, 220, 180, 200),
            new HalfReaction("Li^+(aq) + e^- -> Ba(s)", -3.04, 241, 235, 156)
    };
    private final String reaction;
    private final double E;
    private final int r, g, b;

    public HalfReaction(String reaction, double E, int r, int g, int b) {
        this.reaction = reaction;
        this.E = E;
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public double getECell() {
        return E;
    }

    public Color getSolutionColor() {
        return new Color(r, g, b, 127);
    }


    public Color getMetalColor() {
        return new Color(r, g, b, 230);
    }

    @Override
    public String toString() {
        return reaction;
    }
}
