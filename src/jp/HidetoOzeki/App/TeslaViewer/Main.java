package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JFrame;

import jp.HidetoOzeki.App.TeslaViewer.Data.Data;

public class Main implements Runnable {
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	JFrame window;
	String ver = "";
	BufferStrategy str;
	Key key;
	int mouseX = 0;
	int mouseY = 0;
	int mouseWheelRotation = 0;
	Data data;
	Scene scene;

	public void setScene(Scene s) {
		scene = s;
	}

	public Main() {
		window = new JFrame("JeseaViewer" + " " + ver);
		window.setSize(WIDTH, HEIGHT);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.createBufferStrategy(2);
		window.addKeyListener(key = new Key());
		window.addMouseWheelListener(new Mouse());
		str = window.getBufferStrategy();
		data = new Data(this);
		scene = new Title(this);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}

	public void render() {
		Graphics2D g = (Graphics2D) str.getDrawGraphics();
		scene.render(g);
		g.setColor(Color.green);
		g.drawRect(mouseX - 5, mouseY - 5, 10, 10);
		g.dispose();
		str.show();
	}

	public void tick() {
		mouseX = (int) (MouseInfo.getPointerInfo().getLocation().getX() - window.getX());
		mouseY = (int) (MouseInfo.getPointerInfo().getLocation().getY() - window.getY());
		scene.updateMouse(mouseX, mouseY);
		scene.input(key);
		scene.update();
	}

	@Override
	public void run() {
		// init();
		int tps = 0;
		int fps = 0;
		double ns = 1000000000.0d / 60.0d;
		double last = System.nanoTime();
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (true) {
			double now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			if (delta >= 1) {
				tick();
				tps++;
				delta--;
			} else {
				render();
				fps++;
				try {
					Thread.sleep(12);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (System.currentTimeMillis() > timer + 1000) {
				timer += 1000;
				System.out.println("fps:" + fps + " tps:" + tps);
				fps = 0;
				tps = 0;
			}
		}
	}

	class Mouse extends MouseAdapter {

		@Override
		public void mouseWheelMoved(MouseWheelEvent m) {
			if (m.getWheelRotation() < 0) {
				scene.updateMouseWheel(m.getWheelRotation());
			} else {
				scene.updateMouseWheel(m.getWheelRotation());
			}
		}

	}

}
