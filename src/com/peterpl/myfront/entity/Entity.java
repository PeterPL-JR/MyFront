package com.peterpl.myfront.entity;

import java.io.IOException;
import java.util.*;

import com.peterpl.myfront.graphics.Textures;
import com.peterpl.myfront.tile.Tile;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public abstract class Entity extends Sprite {
	
	public static final int size = 100;
	public int x, y;
	
	protected Random random = new Random();
	
	protected Texture texture = new Texture();

	public void update() {
		x = (int) getPosition().x / size - 20;
		y = (int) getPosition().y / size - 20;
	}
	
	public void updateTexture(String tilePath) {
		try {
			texture.loadFromStream(Tile.class.getResourceAsStream("/textures/entity/" + tilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTexture(texture);
	}
	
	public void updateTexture(Textures texture) {
		setTexture(texture.getTexture());
	}
}
