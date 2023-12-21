import javax.swing.*;
import java.awt.*;

public class MasterPanel extends JPanel {
    private final DisplayPanel displayPanel;
    private final ControlPanel controlPanel;
    private final FullReaction fullReaction;

    public MasterPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(700,460));
        setBackground(Color.WHITE);
        fullReaction = new FullReaction();
        displayPanel = new DisplayPanel(fullReaction);
        controlPanel = new ControlPanel(fullReaction);
        add(displayPanel);
        add(controlPanel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Electrochemical Cell Simulation");
        MasterPanel masterPanel = new MasterPanel();
        frame.add(masterPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
