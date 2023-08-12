import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Graphics;

public class Particles {

    Random random = new Random();
    List<Particle> particles = new ArrayList<Particle>();

    public void createRandomParticles(int amount, int xSpread, int ySpread) {
        for (int i = 0; i <= amount; i++) {
            particles.add(new Particle(
                    random.nextInt(xSpread),
                    random.nextInt(ySpread),
                    random.nextInt(20) - 10,
                    random.nextInt(20) - 10));
        }
    }

    public void createParticleAtPoint(int xPos, int yPos) {
        particles.add(new Particle(
                xPos,
                yPos,
                random.nextInt(20) - 10,
                random.nextInt(20) - 10));
    }

    public void tickParticles(List<Field> fields, float deltaT) {
        System.out.println(deltaT);
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).tick(fields, deltaT);
            if (particles.get(i).checkOOB()) {
                particles.remove(i);
            }
        }
    }

    public void paintParticles(Graphics g) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).paintParticle(g);
        }
    }
}
