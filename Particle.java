import java.lang.Math;
import java.util.List;

import java.awt.Graphics;
import java.awt.Color;

public class Particle {
    static final int DRAWN_WIDTH = 5;
    static final int OOB_NUM = 1000; // if out of panel by this number of units -> kill particle

    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;
    private double xAcc;
    private double yAcc;

    Particle(int xPos, int yPos, float xVel, float yVel) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void tick(List<Field> fields) {
        updateAcc(fields);
        xVel += xAcc;
        yVel += yAcc;
        xPos += xVel;
        yPos += yVel;
    }

    public void paintParticle(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval(Math.round(xPos), Math.round(yPos), DRAWN_WIDTH, DRAWN_WIDTH);
    }

    public boolean checkOOB() {
        if (xPos > OOB_NUM + RenderPanel.PANEL_WIDTH || xPos < -OOB_NUM || yPos > OOB_NUM + RenderPanel.PANEL_HEIGHT
                || yPos < -OOB_NUM) {
            return true;
        }
        return false;
    }

    private void updateAcc(List<Field> fields) {
        xAcc = 0;
        yAcc = 0;

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            float deltax = xPos - field.xPos;
            float deltay = yPos - field.yPos;
            float deltad = (float) Math.sqrt(deltax * deltax + deltay * deltay);

            double acc = field.strength / Math.pow(deltad, 2);
            double angle = Math.atan(Math.abs(deltay / deltax));

            yAcc += Math.signum(deltay) * acc * Math.sin(angle);

            xAcc += Math.signum(deltax) * acc * Math.cos(angle);

            // don't know why, it just don't work
            // yAcc += acc * Math.sin(angle);
            // xAcc += acc * Math.cos(angle);

            // optimize the below code to not be so sus
            // commented out because this seems to generate rounding error
            // double ratio = deltay/deltax
            // if (deltay > 0) {
            // yAcc += ratio*acc/Math.sqrt(ratio*ratio + 1);
            // } else {
            // yAcc += -ratio*acc/Math.sqrt(ratio*ratio + 1);
            // }
            // if (deltax > 0) {
            // xAcc += acc/Math.sqrt(ratio*ratio + 1);
            // } else {
            // xAcc += -acc/Math.sqrt(ratio*ratio + 1);
            // }
        }
    }
}
