package com.peterpl.myfront.entity;

import com.peterpl.myfront.graphics.*;

import org.jsfml.system.*;

import static com.peterpl.myfront.Print.*;

import java.util.*;

import com.peterpl.myfront.*;

public class Dog extends Mob {

	public static Textures right = new Textures("/textures/entity/dog.png", 0, 0);
	public static Textures down = new Textures("/textures/entity/dog.png", 100, 0);
	public static Textures left = new Textures("/textures/entity/dog.png", 100, 100);
	public static Textures up = new Textures("/textures/entity/dog.png", 0, 100);

	public Dog() {
		updateTexture(right);
		updatePosition(0, 0);
	}

	public Dog(int x, int y) {
		updateTexture(right);
		updatePosition(x, y);
	}

	int oldTime = 0;
	int newTime = 0;

	public void update() {
		super.update();

		oldTime = newTime;
		newTime = (int) clock.getElapsedTime().asSeconds();
		if (newTime != oldTime) {

			int action = random.nextInt(2);
			// 0 - stay
			// 1 - move
			// 2 - rotate
			
			if (action == 0) {

				int xPos = random.nextInt(2);
				int yPos = random.nextInt(2);

				move(xPos, yPos);
			} else if (action == 1) {
				
				int rot = random.nextInt(4);
				updateRotation(rot);
			}
		}
		
		switch (rotation) {
		case RIGHT:
			updateTexture(right);
			break;
		case DOWN:
			updateTexture(down);
			break;
		case LEFT:
			updateTexture(left);
			break;
		case UP:
			updateTexture(up);
			break;
		}
	}
}
