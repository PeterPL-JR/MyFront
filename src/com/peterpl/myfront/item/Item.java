package com.peterpl.myfront.item;

import org.jsfml.graphics.*;

import com.peterpl.myfront.graphics.*;

public abstract class Item extends Sprite {

	public static Textures rock = new Textures("/textures/item/rock.png");
	public static Textures flower = new Textures("/textures/item/flower.png");
	public static Textures NULL = new Textures("/textures/item/null.png");
	
	public static final int size = 90;
	
	public String type = null;
	private Texture texture = new Texture();
	
	public void updateTexture(Textures texture) {
		setTexture(texture.getTexture());
	}
	
	protected Item(String type) {
		this.type = type;
	}
	
	public String type() {
		return type;
	}
}
