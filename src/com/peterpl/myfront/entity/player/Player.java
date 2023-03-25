package com.peterpl.myfront.entity.player;

import com.peterpl.myfront.*;
import com.peterpl.myfront.entity.*;
import com.peterpl.myfront.graphics.*;
import com.peterpl.myfront.item.*;
import com.peterpl.myfront.tile.*;

import org.jsfml.system.*;

public class Player extends Mob {

	public static Textures right = new Textures("/textures/entity/player.png", 0, 0);
	public static Textures down = new Textures("/textures/entity/player.png", 100, 0);
	public static Textures left = new Textures("/textures/entity/player.png", 100, 100);
	public static Textures up = new Textures("/textures/entity/player.png", 0, 100);

	public static Textures right_shot = new Textures("/textures/entity/player.png", 200, 0);
	public static Textures down_shot = new Textures("/textures/entity/player.png", 300, 0);
	public static Textures left_shot = new Textures("/textures/entity/player.png", 300, 100);
	public static Textures up_shot = new Textures("/textures/entity/player.png", 200, 100);

	public static Textures right_take = new Textures("/textures/entity/player.png", 400, 0);
	public static Textures down_take = new Textures("/textures/entity/player.png", 500, 0);
	public static Textures left_take = new Textures("/textures/entity/player.png", 500, 100);
	public static Textures up_take = new Textures("/textures/entity/player.png", 400, 100);

	public Inventory inventory;

	private boolean stand = true;
	private boolean shot = false;
	private boolean take = false;

	public Player() {
		inventory = new Inventory();

		updateTexture(right);
		updatePosition(0, 0);
	}

	public Player(int x, int y) {
		inventory = new Inventory();

		updateTexture(right);
		updatePosition(x, y);
	}

	public void update() {
		super.update();
		if (stand) {

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
		if (shot) {

			switch (rotation) {
			case RIGHT:
				updateTexture(right_shot);
				break;
			case DOWN:
				updateTexture(down_shot);
				break;
			case LEFT:
				updateTexture(left_shot);
				break;
			case UP:
				updateTexture(up_shot);
				break;
			}
		}
		if (take) {

			switch (rotation) {
			case RIGHT:
				updateTexture(right_take);
				break;
			case DOWN:
				updateTexture(down_take);
				break;
			case LEFT:
				updateTexture(left_take);
				break;
			case UP:
				updateTexture(up_take);
				break;
			}
		}

		if (Display.coords != null) {
			Display.coords.update();
			Display.coords.setPosition(getPosition().x + 625, getPosition().y - 290);
		}

		inventory.menu.setPosition(getPosition().x - 270, getPosition().y + 290);
		inventory.point.setPosition(getPosition().x - 270 + inventory.selected * Item.size, getPosition().y + 290);
	}

	public void move(int x, int y) {

		int size = Entity.size;

		int xPos = x * size;
		int yPos = y * size;

		if (!collision(x, y)) {
			if (x > 0)
				updateRotation(RIGHT);
			if (x < 0)
				updateRotation(LEFT);
			if (y > 0)
				updateRotation(DOWN);
			if (y < 0)
				updateRotation(UP);

			if (x > 0)
				Display.camera.move(new Vector2f(size, 0));
			;
			if (x < 0)
				Display.camera.move(new Vector2f(-size, 0));
			;
			if (y > 0)
				Display.camera.move(new Vector2f(0, size));
			;
			if (y < 0)
				Display.camera.move(new Vector2f(0, -size));
			;

			super.move(new Vector2f(xPos, yPos));
			update();
		}
	}

	public void updatePosition(int x, int y) {
		super.updatePosition(x, y);
		update();
	}

	public void stand() {
		stand = true;

		shot = false;
		take = false;

		update();
	}

	public void shot() {
		shot = true;

		stand = false;
		take = false;

		update();

		for (int i = 0; i < Display.terrain.tiles.length; i++) {
			int tileIndex = i;
			int dirX = 0;
			int dirY = 0;

			switch (rotation) {
			case RIGHT:
				dirX = 1;
				break;
			case DOWN:
				dirY = 1;
				break;
			case LEFT:
				dirX = -1;
				break;
			case UP:
				dirY = -1;
				break;
			}

			if (Display.terrain.tiles[tileIndex].x == x + dirX
					&& Display.terrain.tiles[tileIndex].y == y + dirY && !Display.terrain.tiles[tileIndex].spectre()) {

				String type = Display.terrain.tiles[tileIndex].type();
				Vector2f pos = Display.terrain.tiles[i].getPosition();

				if(inventory.items[inventory.selected] != null) return;
				
				if (type == "rock") {
					Display.terrain.tiles[tileIndex] = new GrassTile();
					inventory.add(new Rock(), inventory.selected);
				} else if (type == "flower") {
					Display.terrain.tiles[tileIndex] = new GrassTile();
					inventory.add(new Flower(), inventory.selected);
				}
				
				inventory.itemsPos[inventory.selected] = inventory.selected;
				
				Display.terrain.tiles[tileIndex].setPosition(pos);
				Display.terrain.tiles[tileIndex].update();

				return;
			}
		}
	}

	public void take() {
		take = true;

		stand = false;
		shot = false;

		update();

		for (int i = 0; i < Display.terrain.tiles.length; i++) {
			int tileIndex = i;
			int dirX = 0;
			int dirY = 0;

			switch (rotation) {
			case RIGHT:
				dirX = 1;
				break;
			case DOWN:
				dirY = 1;
				break;
			case LEFT:
				dirX = -1;
				break;
			case UP:
				dirY = -1;
				break;
			}
			
			Tile tile = Display.terrain.tiles[tileIndex];

			if (tile.x == x + dirX && tile.y == y + dirY && !tile.spectre()) {

				String type = tile.type();
				Vector2f pos = tile.getPosition();

				if (tile.solid() || tile.spectre() || !(tile instanceof GrassTile))
					return;

				Tile newTile;
				
				if(inventory.items.length < inventory.selected + 1) return;
				
				if(inventory.items[inventory.selected] == null) return;
				
				switch(inventory.items[inventory.selected].type) {
				case "rock":
					newTile = new RockTile();
					break;
				case "flower":
					newTile = new FlowerTile();
					break;
				default:
					newTile = null;
				}
				
				inventory.delete(inventory.selected);
				
				Display.terrain.tiles[tileIndex] = newTile;
				Display.terrain.tiles[tileIndex].setPosition(pos);
				Display.terrain.tiles[tileIndex].update();

				return;
			}

		}
	}
}
