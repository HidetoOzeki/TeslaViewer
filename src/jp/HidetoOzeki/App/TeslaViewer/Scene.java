package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Graphics;

public class Scene {
	Main main;
	int WIDTH = 0;
	int HEIGHT = 0;
	int mouseWheelRotation;
	int mouseX, mouseY;
	public int xoffset, yoffset;

	public Scene(Main main) {
		this.main = main;
		WIDTH = main.WIDTH;
		HEIGHT = main.HEIGHT;
	}

	public void input(Key key) {

	}

	public void updateMouse(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public void updateMouseWheel(int mwr) {
		this.mouseWheelRotation += mwr;
	}

	public void update() {

	}

	public void render(Graphics g) {

	}
}
