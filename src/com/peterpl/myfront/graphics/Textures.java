package com.peterpl.myfront.graphics;

import java.io.IOException;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;

public class Textures {

	private Texture texture;
	private String path;
	
	public Textures(String path) {
		texture = new Texture();
		this.path = path;
		loadFromFile();
	}
	
	public Textures(String path, int x, int y) {
		texture = new Texture();
		this.path = path;
		loadFromFile(x, y);
	}
	
	private void loadFromFile() {
		try {
			texture.loadFromStream(Textures.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFromFile(int x, int y) {
		try {
			texture.loadFromStream(Textures.class.getResourceAsStream(path), new IntRect(x, y, 100, 100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture getTexture() {
		return texture;
	}
}
