package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class NowCast extends Scene {
	BufferedImage radarimg, thunderimg, tornadoimg;
	BufferedImage image;
	long now = 0;

	public NowCast(Main main) {
		super(main);
		LoadImage();
		image = radarimg;
	}

	public void LoadImage() {
		now = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = new Date(now);
		int min = Integer.parseInt(formatter.format(date).substring(10, 12));
		min = 5 * (min / 5);
		String ct = formatter.format(date).substring(0, 10);
		String TimeEvery5min = ct + String.format("%02d", min);
		min = Integer.parseInt(formatter.format(date).substring(10, 12)) - 5;
		min = 10 * (min / 10);
		String TimeEvery10min = ct + String.format("%02d", min);
		System.out.println(TimeEvery5min);
		System.out.println(TimeEvery10min);
		try {
			URL url0 = new URL("https://www.jma.go.jp/jp/radnowc/imgs/radar/000/" + TimeEvery5min + "-00.png");
			URL url1 = new URL("https://www.jma.go.jp/jp/radnowc/imgs/thunder/000/" + TimeEvery10min + "-00.png");
			URL url2 = new URL("https://www.jma.go.jp/jp/radnowc/imgs/tornado/000/" + TimeEvery10min + "-00.png");
			radarimg = ImageIO.read(url0);
			thunderimg = ImageIO.read(url1);
			tornadoimg = ImageIO.read(url2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void input(Key key) {
		if (key.keys[KeyEvent.VK_1])
			image = radarimg;
		if (key.keys[KeyEvent.VK_2])
			image = thunderimg;
		if (key.keys[KeyEvent.VK_3])
			image = tornadoimg;
		if (key.keys[KeyEvent.VK_ESCAPE])
			main.setScene(new Title(main));
		;
		key.keys[KeyEvent.VK_1] = false;
		key.keys[KeyEvent.VK_2] = false;
		key.keys[KeyEvent.VK_3] = false;
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (image != null)
			g.drawImage(image, 32, 32, image.getWidth(), image.getHeight(), null);
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		g.drawString("1.ç~êÖ", WIDTH / 2, HEIGHT / 2);
		g.drawString("2.óã", WIDTH / 2, 24 * 1 + HEIGHT / 2);
		g.drawString("3.ó≥ä™î≠ê∂ämìx", WIDTH / 2, 24 * 2 + HEIGHT / 2);
		g.drawString("ESC.èâä˙âÊñ ", WIDTH / 2, 24 * 3 + HEIGHT / 2);
	}

}
