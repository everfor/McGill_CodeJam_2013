package ghost;

import java.util.Random;

public class Ghost {
	int x, y;

	public Ghost() {
		x = 39;
		y = 29;
	}

	public void move() {
		int control = 0;
		while (control < 1) {
			Random generator = new Random();
			int randommove = generator.nextInt(4);

			if(randommove == 0) {
				x = x + 1;
			}

			else if(randommove == 1) {
				x = x - 1;

			} else if(randommove == 2) {
				y = y + 1;

			} else if(randommove == 3) {
				y = y - 1;

			}
			control = control + 1;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
