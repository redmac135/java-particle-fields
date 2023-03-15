import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderPanel extends JPanel implements ActionListener {

    static final int PANEL_WIDTH = 1000;
    static final int PANEL_HEIGHT = 1000;

    Timer timer;
    Particles particles = new Particles(10000);
    List<Field> fields = new ArrayList<Field> ();

    RenderPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        timer = new Timer(10, this); // We can use "this" because it implements action performed
        timer.start();
        fields.add(new Field(100000, 100, 100));
        fields.add(new Field(100000, 400, 400));
    }

    public void paint(Graphics g) {
        super.paint(g);
        particles.paintParticles(g);
        for (int i=0; i < fields.size(); i++) {
            fields.get(i).paintField(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        particles.tickParticles(fields);
        repaint();
    }
}
