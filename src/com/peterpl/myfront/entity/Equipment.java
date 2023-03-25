package com.peterpl.myfront.entity;

import com.peterpl.myfront.item.*;

public class Equipment {

	public Item[] items;
	protected final int allSize;

	public Equipment(int size) {
		allSize = size;
		items = new Item[size];
		for(int i = 0; i < size; i++) {
			items[i] = null;
		}
	}

	public void add(Item item, int index) {
		items[index] = item;
		//Print.p("Dodano element: " + item.type);
	}

	public void delete(int index) {
		items[index] = null;
	}

	public int getAllSize() {
		return allSize;
	}

	public int getSize() {
		int itemCount = 0;
		for(Item it : items) {
			if(it != null) itemCount++;
		}
		return itemCount;
	}
}
