package com.peterpl.myfront.graphics;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Light extends RectangleShape {

	private Color level0 = new Color(0, 0, 0, 210);
	private Color level1 = new Color(0, 0, 0, 140);
	private Color level2 = new Color(0, 0, 0, 70);
	private Color level3 = new Color(0, 0, 0, 0);

	public static final int size = 100;
	public int x, y;

	private int lightLevel = 3;

	public Light() {
		super(new Vector2f(size, size));
		setFillColor(level3);
	}

	public void update() {
		x = (int) getPosition().x / size - 20;
		y = (int) getPosition().y / size - 20;
	}

	public void update(int level, boolean plus) {

		if (plus) {
			
			lightLevel += level;
			
		} else {
			
			lightLevel = level;
		}
		
		switch (lightLevel) {
		case 0:
			setFillColor(level0);
			break;
		case 1:
			setFillColor(level1);
			break;
		case 2:
			setFillColor(level2);
			break;
		case 3:
			setFillColor(level3);
			break;
		default:
			setFillColor(level3);
		}
	}

	public int getLight() {
		return lightLevel;
	}
}
