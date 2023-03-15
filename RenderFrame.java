import javax.swing.JFrame;

public class RenderFrame extends JFrame {

    RenderPanel panel;

    RenderFrame() {
        panel = new RenderPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
