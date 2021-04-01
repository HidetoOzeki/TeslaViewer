package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {
	public boolean[] keys = new boolean[1024];

	public Key() {

	}

	public void toggle(int kc, boolean pressed) {
		if (kc > 0 && kc < 1024)
			keys[kc] = pressed;
	}

	@Override
	public void keyPressed(KeyEvent k) {
		toggle(k.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		toggle(k.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		super.keyTyped(arg0);
	}

}
