package com.peterpl.myfront.tile;

import java.io.IOException;

import com.peterpl.myfront.graphics.Textures;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public abstract class Tile extends Sprite {
	
	public static Textures grass = new Textures("/textures/tile/grass.png");
	public static Textures stone = new Textures("/textures/tile/stone.png");
	public static Textures tree = new Textures("/textures/tile/tree.png");
	public static Textures water = new Textures("/textures/tile/water.png");
	public static Textures flower = new Textures("/textures/tile/flower.png");
	public static Textures rock = new Textures("/textures/tile/rock.png");
	public static Textures torch = new Textures("/textures/tile/torch.png");

	public static final int size = 100;
	public int x, y;
	
	protected boolean solid = false;
	protected boolean spectre = false;
	protected boolean glow = false;
	protected int rare = 0;
	
	protected String type = null;
	protected Texture texture = new Texture();

	protected Tile(boolean solid, String type, boolean glow) {
		this.solid = solid;
		this.type = type;
		this.glow = glow;
	}
	
	public void update() {
		x = (int) getPosition().x / size - 20;
		y = (int) getPosition().y / size - 20;
	}
	
	public void updateTexture(String tilePath) {
		try {
			texture.loadFromStream(Tile.class.getResourceAsStream("/textures/tile/" + tilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTexture(texture);
	}
	
	public void updateTexture(Textures texture) {
		setTexture(texture.getTexture());
	}
	
	public boolean solid() {
		return solid;
	}

	public boolean spectre() {
		return spectre;
	}
	
	public String type() {
		return type;
	}
	
	public void createSpectre() {
		this.spectre = true;
	}
	
	public void deleteSpectre() {
		this.spectre = false;
	}
	
	public void setRare(int rare) {
		this.rare = rare;
	}
	
	public int getRare() {
		return rare;
	}
}
