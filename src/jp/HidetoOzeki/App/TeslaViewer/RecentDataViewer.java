package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;

import jp.HidetoOzeki.App.TeslaViewer.Data.MyUtil;
import jp.HidetoOzeki.App.TeslaViewer.Data.TideLevel.TideLevel;

public class RecentDataViewer extends Scene{
	int amount = 0;
	BufferedImage[] image = new BufferedImage[amount];
	BufferedImage[] image_diff = new BufferedImage[amount];
	TideLevel wakkanai;
	public RecentDataViewer(Main main) {
		super(main);
	}

	public RecentDataViewer(Main main,int d) {
		this(main);
		long day = 1000 * 60 * 60 * 24;
	}

	@Override
	public void input(Key key) {
		// TODO Auto-generated method stub
		super.input(key);
	}

	@Override
	public void updateMouse(int x, int y) {
		// TODO Auto-generated method stub
		super.updateMouse(x, y);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wakkanai.getImage(),0,0,null);
	}
	
}
