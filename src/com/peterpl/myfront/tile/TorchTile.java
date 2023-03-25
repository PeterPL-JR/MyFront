package com.peterpl.myfront.tile;

import com.peterpl.myfront.Display;
import com.peterpl.myfront.Terrain;

public class TorchTile extends Tile {

	int tileIndex;
	
	public TorchTile() {
		super(false, "torch", true);
		updateTexture(Tile.torch);
		if(glow) {
			light();
		}
	}
	
	public TorchTile(int tileIndex) {
		super(false, "torch", true);
		updateTexture(Tile.torch);
		this.tileIndex = tileIndex;
		if(glow) {
			light();
		}
	}
	
	private void light() {
		// Level 3
		Display.terrain.light[tileIndex].update(3, true);
		
		// Level 2
		Display.terrain.light[tileIndex - 1].update(2, true);
		Display.terrain.light[tileIndex + 1].update(2, true);
		Display.terrain.light[tileIndex - Terrain.size].update(2, true);
		Display.terrain.light[tileIndex + Terrain.size].update(2, true);
		
		// Level 1
		Display.terrain.light[tileIndex - 2].update(1, true);
		Display.terrain.light[tileIndex + 2].update(1, true);
		Display.terrain.light[tileIndex - 2 * Terrain.size].update(1, true);
		Display.terrain.light[tileIndex + 2 * Terrain.size].update(1, true);
		
		Display.terrain.light[tileIndex - Terrain.size + 1].update(1, true);
		Display.terrain.light[tileIndex + Terrain.size + 1].update(1, true);
		
		Display.terrain.light[tileIndex - Terrain.size - 1].update(1, true);
		Display.terrain.light[tileIndex + Terrain.size - 1].update(1, true);
	}
}
