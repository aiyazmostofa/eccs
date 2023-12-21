import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public String device;
    private FullReaction fullReaction;
    public ControlPanel(FullReaction fullReaction) {
        this.fullReaction = fullReaction;
        setPreferredSize(new Dimension(700,60));
        setBackground(Color.WHITE);
        JComboBox<HalfReaction> comboBox = new JComboBox<>(HalfReaction.halfReactions);
        comboBox.addActionListener((e) -> {
            fullReaction.setOne((HalfReaction) ((JComboBox<?>)e.getSource()).getSelectedItem());
            if (device.equals("Power Source")) setPolarity(1);
            else setPolarity(-1);
        });
        add(comboBox);

        comboBox = new JComboBox<>(HalfReaction.halfReactions);
        comboBox.addActionListener((e) -> {
            fullReaction.setTwo((HalfReaction) ((JComboBox<?>)e.getSource()).getSelectedItem());
            if (device.equals("Power Source")) setPolarity(1);
            else setPolarity(-1);
        });
        add(comboBox);

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton button;

        buttonGroup.add(button = new JRadioButton() {{addActionListener(e -> {
            device = "LightBulb";
            setPolarity(-1);
        }); setText("LightBulb"); setSelected(true);}});
        device = "LightBulb";
        add(button);

        buttonGroup.add(button = new JRadioButton() {{addActionListener(e -> {
            device = "Voltmeter";
            setPolarity(-1);
        }); setText("Voltmeter");}});
        add(button);

        buttonGroup.add(button = new JRadioButton() {{addActionListener(e -> {
            device = "Power Source";
            setPolarity(1);
        }); setText("Power Source");}});
        add(button);
    }

    private void setPolarity(int dir) {
        if (fullReaction.isValid()) for (DisplayPanel.PixelCoord pc :((DisplayPanel)this.getParent().getComponent(0)).electrons) pc.setReverse(fullReaction.direction() == dir);
    }
}
