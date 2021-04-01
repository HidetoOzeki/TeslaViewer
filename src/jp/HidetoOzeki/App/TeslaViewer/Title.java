package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Title extends Scene {
	ArrayList<String> log = new ArrayList<>();
	BufferedImage image;
	public Title(Main main) {
		super(main);
		try {
			image = ImageIO.read(Main.class.getResourceAsStream("/title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//log.add("マウスホイールでスクロール可能");
		//log.add("ナウキャスト：降水、雷、竜巻：追加　＊雷、竜巻まれに読み込みエラーあり");
		//log.add("最新地震情報追加");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void input(Key key) {
		if (key.keys[KeyEvent.VK_1])
			main.setScene(new GraphViewer(main,0));
		if (key.keys[KeyEvent.VK_2])
			main.setScene(new GraphViewer(main,1));
		if (key.keys[KeyEvent.VK_3])
			main.setScene(new GraphViewer(main,2));
		if (key.keys[KeyEvent.VK_4])
			main.setScene(new GraphViewer(main,3));
		if (key.keys[KeyEvent.VK_5])
			main.setScene(new NowCast(main));
		if (key.keys[KeyEvent.VK_6])
			main.setScene(new EarthQuakeInfo(main));
		key.keys[KeyEvent.VK_1] = false;
		key.keys[KeyEvent.VK_5] = false;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public void render(Graphics g) {
		int h = 210;
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT * 2);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		g.drawString("日本全国験潮データ", 100, h+48);
		g.drawString("データ読み込み", 100, h + 48 * 2);
		g.drawString("1. 最新データ", 100, h + 48 * 3);

			g.drawString("2. 昨日のデータ", 100, h + 48 * 4);
			g.drawString("3. 二日前のデータ", 100, h + 48 * 5);
			g.drawString("4. 三日前のデータ", 100, h + 48 * 6);

		g.drawString("5. レーダーナウキャスト", 100, h + 48 * (3 + 4));
		g.drawString("6. 最新地震情報", 100, h + 48 * (3 + 5));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
		g.drawString("Dキーで偏差グラフ表示", WIDTH / 3, HEIGHT / 2 + 24 * 1);
		g.drawString("ESCキーで初期画面に戻る", WIDTH / 3, HEIGHT / 2 + 24 * 2);
		g.drawString("1.2.3...キーで選択", WIDTH / 3, HEIGHT / 2 + 24 * 3);
		g.setColor(Color.GRAY);
		//g.drawString("変更点", WIDTH - 370, -32 + HEIGHT / 3);
		for (int i = 0; i < log.size(); i++) {
			g.drawString(">" + log.get(i), WIDTH - 520, i * 16 + HEIGHT / 3);
		}
		g.drawImage(image,100,24,null);
		// TODO Auto-generated method stub
		super.render(g);
	}

}
