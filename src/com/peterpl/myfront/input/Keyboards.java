package com.peterpl.myfront.input;

import com.peterpl.myfront.*;
import com.peterpl.myfront.entity.*;

import org.jsfml.window.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.window.event.*;

public class Keyboards {

	public void update(Event e) {
		if (e.type == Event.Type.KEY_PRESSED) {
			KeyEvent keyEvent = e.asKeyEvent();

			if (Display.playerMoved) {

				switch (keyEvent.key) {
				case W:
					Display.player.move(0, -1);
					Display.player.updateRotation(Mob.UP);
					break;
				case S:
					Display.player.move(0, 1);
					Display.player.updateRotation(Mob.DOWN);
					break;
				case A:
					Display.player.move(-1, 0);
					Display.player.updateRotation(Mob.LEFT);
					break;
				case D:
					Display.player.move(1, 0);
					Display.player.updateRotation(Mob.RIGHT);
					break;
				default:
					break;
				}

			} else {

				switch (keyEvent.key) {
				case W:
					Display.player.updateRotation(Mob.UP);
					break;
				case S:
					Display.player.updateRotation(Mob.DOWN);
					break;
				case A:
					Display.player.updateRotation(Mob.LEFT);
					break;
				case D:
					Display.player.updateRotation(Mob.RIGHT);
					break;
				default:
					break;
				}
			}

			if (keyEvent.key == Key.E) {
				Display.inventoryVisible = !Display.inventoryVisible;
				// int x = (int) Display.player.x;
				// int y = (int) Display.player.x;
				// Display.terrain.mobs.add(new Dog(x, y));
			}

			if (keyEvent.key == Key.F1) {
				Display.coordsVisible = !Display.coordsVisible;
			}
			
			if(keyEvent.key == Key.F2) {
				Print.p("------------------------");
				for(int i = 0; i < Display.player.inventory.getAllSize(); i++) {
					Print.p(Display.player.inventory.items[i] + "");
				}
			}

			Display.player.update();
		}
	}
}
