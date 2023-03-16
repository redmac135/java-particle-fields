import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RenderPanel extends JPanel implements ActionListener, MouseListener  {

    static final int PANEL_WIDTH = 1000;
    static final int PANEL_HEIGHT = 1000;
    boolean mousePressed = false;

    Timer timer;
    Particles particles = new Particles();
    List<Field> fields = new ArrayList<Field> ();

    RenderPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.addMouseListener(this);

        timer = new Timer(10, this); // We can use "this" because it implements action performed
        timer.start();
        particles.createRandomParticles(1000, PANEL_WIDTH, PANEL_HEIGHT);
        fields.add(new Field(100000, 400, 400));
        fields.add(new Field(100000, 600, 600));
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

    @Override
    public void mouseClicked(MouseEvent e) {
        particles.createParticleAtPoint(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        System.out.println(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
