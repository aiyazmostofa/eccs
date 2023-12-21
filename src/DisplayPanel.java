import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {
    private final FullReaction fullReaction;
    public ArrayList<PixelCoord> electrons;

    public DisplayPanel(FullReaction fullReaction) {
        setPreferredSize(new Dimension(700, 400));
        this.fullReaction = fullReaction;
        electrons = new ArrayList<>();
        for (int x = 130; x <= 570; x += 20) {
            electrons.add(new PixelCoord(x, 80));
        } 

        for (int y = 80; y <= 130; y += 20) {
            electrons.add(new PixelCoord(130, y));
            electrons.add(new PixelCoord(570, y));
        }


        // 10 * M + 10 * (M - 1) == 530
        // 10M + 10M - 10
        // 20M = 520
        // M = 26

        Timer timer = new Timer(15, new PaintActionListener());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw beakers
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 700, 400);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 150, 200, 249, 10, 10);
        g.drawRoundRect(400, 150, 200, 249, 10, 10);

        g.setColor(Color.WHITE);
        g.fillRect(100, 150, 200, 10);
        g.fillRect(400, 150, 200, 10);

        // Draw salt bridge
        g.setColor(new Color(205, 180, 150));
        g.fillRect(290, 150, 10, 160);
        g.fillRect(401, 150, 10, 160);
        g.fillRect(290, 150, 120, 10);

        // Draw metals
        g.setColor(fullReaction.getOne().getMetalColor());
        g.fillRect(110, 130, 40, 220);

        g.setColor(fullReaction.getTwo().getMetalColor());
        g.fillRect(550, 130, 40, 220);

        // Draw solutions
        g.setColor(fullReaction.getOne().getSolutionColor());
        g.fillRoundRect(100, 200, 200, 199, 10, 10);

        g.setColor(fullReaction.getTwo().getSolutionColor());
        g.fillRoundRect(400, 200, 200, 199, 10, 10);

        // Draw wires
        g.setColor(Color.BLACK);
        g.drawLine(130, 130, 130, 80);
        g.drawLine(570, 130, 570, 80);
        g.drawLine(570, 80, 130, 80);

        // Draw name text
        g.drawString("Created by Aiyaz & Raiyan", 10,20);

        if (((ControlPanel)getParent().getComponent(1)).device.equals("LightBulb")) {
            if (fullReaction.isValid()) {
                g.setColor(Color.YELLOW);
                g.fillOval(350 - 20, 65 - 40, 40, 40);
            }
            g.setColor(Color.BLACK);
            g.drawOval(350 - 20, 65 - 40, 40, 40);
            g.setColor(Color.GRAY);
            g.fillRect(340, 65,20,30);
        } else if (((ControlPanel)getParent().getComponent(1)).device.equals("Voltmeter")){
            g.setColor(Color.BLACK);
            g.drawLine(130,80, 130, 40);
            g.drawLine(570, 80, 570, 40);
            g.drawLine(570, 40, 130, 40);

            g.setColor(Color.GRAY);
            g.fillRect(350 - 40, 25, 80, 30);

            g.setColor(Color.WHITE);
            g.drawString(String.format("%.3fV", fullReaction.direction() * fullReaction.voltage()), 330,45);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(130,80, 130, 40);
            g.drawLine(570, 80, 570, 40);
            g.drawLine(570, 40, 130, 40);

            g.setColor(Color.RED);
            g.fillRect(350 - 40, 25, 80, 30);
            g.fillRect(300,35,10,10);
        }

        if (fullReaction.isValid()) {
            for (PixelCoord pixelCoord : electrons) {
                g.setColor(Color.YELLOW);
                g.fillOval(pixelCoord.x - 5, pixelCoord.y - 5, 10, 10);
                g.setColor(Color.BLACK);
                g.drawLine(pixelCoord.x - 2, pixelCoord.y, pixelCoord.x + 2, pixelCoord.y);
            }
        }
    }

    private class PaintActionListener implements ActionListener {
        private boolean previous;

        @Override
        public void actionPerformed(ActionEvent e) {
            previous = fullReaction.isValid();
            if (isValid() || previous != isValid()) {
                for (PixelCoord pixelCoord : electrons) {
                    pixelCoord.increment();
                }
                repaint();
            }
            previous = isValid();
        }
    }

    class PixelCoord {
        private int x;
        private int y;
        private boolean reverse;

        public PixelCoord(int x, int y) {
            this.x = x;
            this.y = y;
            reverse = false;
        }

        public void setReverse(boolean reverse) {
            this.reverse = reverse;
        }

        public void increment() {
            int coef = reverse ? -1 : 1;
            if (y == 80 && (x < 570 && !reverse || x > 130 && reverse)) {
                x += coef;
            } else if (x == 130) {
                if (y == 130 && reverse) {
                    x = 570;
                    y += coef;
                }
                y -= coef;
            } else if (x == 570) {
                if (y == 130 && !reverse) {
                    x = 130;
                    y -= coef;
                } else {
                    y += coef;
                }
            }
        }
    }
}
