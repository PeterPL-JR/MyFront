package com.peterpl.myfront;

import java.io.*;

import com.peterpl.myfront.entity.player.*;
import com.peterpl.myfront.graphics.*;
import com.peterpl.myfront.input.Keyboards;
import com.peterpl.myfront.input.Mouses;

import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import static com.peterpl.myfront.Print.*;

public class Display {

	public static final int width = 1300;
	public static final int height = 700;
	public static final int frameLimit = 60;

	public static final String title = "MyFront";
	public static final String version = " (Pre-Classic 4.0-Alpha)";

	public static Coordinates coords;
	public static boolean coordsVisible = true;
	public static boolean inventoryVisible = true;
	public static boolean playerMoved = true;

	public static boolean day = true; // true
	public static int dayLength = 30; // 15
	public static int frames = 0;
	public static int time = 0;
	public static float zoom = 1;

	public static RenderWindow window;
	public static Keyboards key;
	public static Mouses mouse;

	public static View camera;
	public static Terrain terrain;
	public static Player player;

	public static void create() {
		window = new RenderWindow(new VideoMode(width, height), title + version);
		key = new Keyboards();
		mouse = new Mouses();

		// window.setMouseCursorVisible(false);
		window.setFramerateLimit(frameLimit);

		Image icon = new Image();
		try {
			icon.loadFromStream(Display.class.getResourceAsStream("/textures/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.setIcon(icon);

		camera = new View(new FloatRect(1400, 1700, width * zoom, height * zoom)); // 1400, 1700, width, height
		window.setView(camera);

		terrain = new Terrain();
		terrain.updateLight(3, false);

		player = new Player();

		coords = new Coordinates();
		
		player.update();
	}

	public static void render() {
		window.clear(new Color(0, 0, 0));

		for (int i = 0; i < Terrain.size * Terrain.size; i++) {
			window.draw(terrain.tiles[i]);
		}

		for (int i = 0; i < terrain.mobs.size(); i++) {
			window.draw(terrain.mobs.get(i));
		}

		window.draw(player);

		for (int i = 0; i < Terrain.size * Terrain.size; i++) {
			window.draw(terrain.light[i]);
		}

		if (coordsVisible) {
			window.draw(coords);
		}
		if (inventoryVisible) {
			player.inventory.render();
		}

		window.display();
	}

	public static void update() {
		window.setView(camera);
		if (frames % frameLimit == 0) {
			time++;
			//p(time);
			time();
			// p("Ruch psa!");
		}
		if (time >= dayLength) {
			time = 0;
			day = !day;
		}

		for (int i = 0; i < terrain.mobs.size(); i++) {
			terrain.mobs.get(i).update();
		}

		frames++;
	}

	public static void time() {

		if (day) {

			if (time == (dayLength - 2 * 5))
				terrain.updateLight(-1, true);
			else if (time == (dayLength - 1 * 5))
				terrain.updateLight(-1, true);
			else if (time == (dayLength))
				terrain.updateLight(-1, true);

		} else {

			if (time == (dayLength - 2 * 5))
				terrain.updateLight(1, true);
			else if (time == (dayLength - 1 * 5))
				terrain.updateLight(1, true);
			else if (time == (dayLength))
				terrain.updateLight(1, true);
		}
	}

	public static void main(String[] args) {

		Display.create();

		while (Display.window.isOpen()) {

			for (Event event : Display.window.pollEvents()) {
				if (event.type == Event.Type.CLOSED) {
					Display.window.close();
				}

				key.update(event);
				mouse.update(event);
			}
			Display.update();
			Display.render();
		}
	}
}
