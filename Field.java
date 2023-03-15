import java.awt.Graphics;
import java.awt.Color;

public class Field {
    static final int DRAWN_WIDTH = 20;

    double strength; // the GM in ag = GM/r^2
    int xPos;
    int yPos;

    Field(int strength, int xPos, int yPos) {
        this.strength = strength;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void paintField(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(xPos, yPos, DRAWN_WIDTH, DRAWN_WIDTH);
    }

}
