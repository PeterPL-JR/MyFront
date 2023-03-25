package com.peterpl.myfront.entity.player;

import java.io.*;

import org.jsfml.graphics.*;

import com.peterpl.myfront.*;
import com.peterpl.myfront.entity.*;
import com.peterpl.myfront.item.*;

public class Inventory extends Equipment {
	
	public Integer[] itemsPos;
	
	public Sprite menu;
	public Sprite point;
	public Texture menuTexture;
	public Texture pointTexture;
	
	public int selected = 0;
	public static int allSize = 7;
	
	public Inventory() {
		super(allSize);
		create();
		
		itemsPos = new Integer[allSize];
		for(int i = 0;i < allSize; i++) {
			itemsPos[i] = null;
		}
	}
	
	public void add(Item item, int index) {
		super.add(item, index);
	}
	
	public void create() {
		menuTexture = new Texture();
		pointTexture = new Texture();
		try {
			menuTexture.loadFromStream(Inventory.class.getResourceAsStream("/textures/gui/inventory.png"));
			pointTexture.loadFromStream(Inventory.class.getResourceAsStream("/textures/gui/point.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu = new Sprite();
		menu.setTexture(menuTexture);
		
		point = new Sprite();
		point.setTexture(pointTexture);
	}
	
	public void render() {	
		
	}
}
