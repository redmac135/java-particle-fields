import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Graphics;

public class Particles {

    Random random = new Random();
    List<Particle> particles = new ArrayList<Particle> ();

    Particles(int amount) {
        for (int i = 0; i <= amount; i++) {
            particles.add(new Particle(
                random.nextInt(500),
                random.nextInt(500),
                random.nextInt(20) - 10,
                random.nextInt(20) - 10
            ));
        }
    }

    public void tickParticles(List<Field> fields) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).tick(fields);
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
