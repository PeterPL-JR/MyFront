package com.peterpl.myfront.tile;

public class WaterTile extends Tile {

	public WaterTile() {
		super(false, "water", false);
		updateTexture(Tile.water);
	}
}
