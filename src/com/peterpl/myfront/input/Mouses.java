package com.peterpl.myfront.input;

import org.jsfml.window.*;
import org.jsfml.window.Mouse.*;
import org.jsfml.window.event.*;

import com.peterpl.myfront.*;
import com.peterpl.myfront.entity.player.*;

public class Mouses {

	public void update(Event e) {
		if (e.type == Event.Type.MOUSE_BUTTON_PRESSED) {
			MouseButtonEvent mouseButtonEvent = e.asMouseButtonEvent();

			if (mouseButtonEvent.button == Button.LEFT) {
				Display.player.shot();
			}
			if (mouseButtonEvent.button == Button.RIGHT) {
				Display.player.take();
			}
		}
		if (e.type == Event.Type.MOUSE_BUTTON_RELEASED) {
			Display.player.stand();
		}
		if (e.type == Event.Type.MOUSE_WHEEL_MOVED) {
			MouseWheelEvent mouseWheelEvent = e.asMouseWheelEvent();
			Inventory inv = Display.player.inventory;

			if(mouseWheelEvent.delta > 0) {
				
				if(!(inv.selected == 0)) inv.selected += -1;
				
			} else {
				
				if(!(inv.selected == 6)) inv.selected += 1;
			}
			
			Display.player.update();
		}

		Display.playerMoved = (Mouse.isButtonPressed(Button.MIDDLE)) ? false : true;
	}
}
