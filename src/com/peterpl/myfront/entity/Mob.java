package com.peterpl.myfront.entity;

import java.util.*;

import com.peterpl.myfront.*;
import com.peterpl.myfront.tile.*;

import org.jsfml.system.*;

public abstract class Mob extends Entity {

	public static final int RIGHT = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;

	public int rotation = 0;
	protected String type;
	
	protected Random random;
	protected Clock clock;
	protected int time = 0;

	public Mob() {
		clock = new Clock();
		random = new Random();
	}
	
	public void update() {
		super.update();
	}

	public void move(int x, int y) {

		int xPos = x * size;
		int yPos = y * size;
		
		if (!collision(x, y)) {
			if (x > 0) updateRotation(RIGHT);
			if (x < 0) updateRotation(LEFT);
			if (y > 0) updateRotation(DOWN);
			if (y < 0) updateRotation(UP);

			super.move(new Vector2f(xPos, yPos));
			update();
		}
	}

	public void rotate(int i) {
		this.rotation += i;
	}

	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;

		int xPos = (x + 20) * Tile.size;
		int yPos = (y + 20) * Tile.size;

		setPosition(xPos, yPos);
	}

	public void updateRotation(int rotation) {
		this.rotation = rotation;
	}

	public boolean collision(int x, int y) {
		
		Tile tile = Display.terrain.getTile(this.x + x, this.y + y);

		if(tile.solid() || tile.spectre()) {
			
			if(this.x + size >= tile.x && this.y == tile.y) return true; 
			if(this.x <= tile.x + Tile.size && this.y == tile.y) return true;
			if(this.y + size >= tile.y && this.x == tile.x) return true;
			if(this.y <= tile.y + Tile.size && this.x == tile.x) return true;
			
			return true;
		}
		
		return false;
	}

	public String type() {
		return type;
	}
}
