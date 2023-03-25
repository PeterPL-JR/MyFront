package com.peterpl.myfront.graphics;

import java.io.IOException;

import com.peterpl.myfront.Display;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class Coordinates extends Text {

	private Font font;
	private Color color;

	private String xText;
	private String yText;

	public Coordinates() {
		createFont();
		update();
	}

	public void update() {
		xText = "X: " + Display.player.x;
		yText = "Y: " + Display.player.y;
		setText();
	}

	private void createFont() {
		font = new Font();
		color = new Color(255, 255, 255);
		try {
			font.loadFromStream(Coordinates.class.getResourceAsStream("/fonts/default.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setFont(font);
		setColor(color);
		setCharacterSize(25);
		setPosition(new Vector2f(600, 600));
	}

	private void setText() {
		setString(xText + "\n" + yText);
	}
}
