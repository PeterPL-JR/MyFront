package com.peterpl.myfront;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import com.peterpl.myfront.entity.*;
import com.peterpl.myfront.graphics.Light;
import com.peterpl.myfront.tile.FlowerTile;
import com.peterpl.myfront.tile.GrassTile;
import com.peterpl.myfront.tile.RockTile;
import com.peterpl.myfront.tile.StoneTile;
import com.peterpl.myfront.tile.Tile;
import com.peterpl.myfront.tile.TreeTile;
import com.peterpl.myfront.tile.WaterTile;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Terrain {

	public final static int size = 41;
	public Tile[] tiles;
	public Light[] light;
	public Vector<Mob> mobs;

	private Random random;

	public Terrain() {
		tiles = new Tile[size * size];
		light = new Light[size * size];
		mobs = new Vector<>();

		random = new Random();

		create();
	}

	private void create() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {

				int index = x + y * size;

				tiles[index] = loadMapFromFile(x, y);
				tiles[index].setPosition(new Vector2f(x * Tile.size, y * Tile.size));
				tiles[index].update();

				light[index] = new Light();
				light[index].setPosition(new Vector2f(x * Light.size, y * Light.size));
				light[index].update();
			}
		}

		createMobs();
	}

	private void createMobs() {
		int count = random.nextInt(10);
		for(int i = 0; i < count; i++) {
			
			int xPos, yPos;
			
			do {
				xPos = random.nextInt(24) - 14;
				yPos = random.nextInt(24) - 14;
				
			} while(getTile(xPos, yPos).solid());
			
			mobs.add(new Dog(xPos, yPos));
		}
	}

	private boolean pickColor(Color color, int r, int g, int b) {
		
		Color color2 = new Color(r, g, b);
		if(color.equals(color2)) return true;
		return false;
	}
	
	private Tile loadMapFromFile(int x, int y) {

		Texture texture = new Texture();

		try {
			texture.loadFromStream(Terrain.class.getResourceAsStream("/textures/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Color color = texture.copyToImage().getPixel(x, y);
		Tile tile;

		if (pickColor(color, 0, 0, 0)) {

			tile = new StoneTile();

		} else if (pickColor(color, 200, 200, 200)) {

			tile = generateTile();
			tile.createSpectre();

		} else {

			tile = generateTile();

		}

		return tile;
	}

	private Tile generateTile() {

		Vector<Tile> tiles = new Vector<Tile>();

		tiles.add(new GrassTile()); // 0 -> 60
		tiles.add(new StoneTile()); // 1 -> 10
		tiles.add(new FlowerTile()); // 4 -> 10
		tiles.add(new RockTile()); // 5 -> 10
		tiles.add(new TreeTile()); // 2 -> 5
		tiles.add(new WaterTile()); // 3 -> 5

		Tile tile = null;
		Random random = new Random();
		int type = random.nextInt(100);

		if (type < 60)
			tile = new GrassTile();
		else if (type >= 60 && type < 70)
			tile = new StoneTile();
		else if (type >= 70 && type < 80)
			tile = new TreeTile();
		else if (type >= 80 && type < 90)
			tile = new WaterTile();
		else if (type >= 90 && type < 95)
			tile = new FlowerTile();
		else if (type >= 95 && type < 100)
			tile = new RockTile();

		return tile;
	}

	public Tile getTile(int x, int y) {

		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].x == x && tiles[i].y == y) {
				return tiles[i];
			}
		}
		return null;
	}

	public void updateLight(int level, boolean plus) {
		for (int i = 0; i < light.length; i++) {
			light[i].update(level, plus);
		}
	}
}
