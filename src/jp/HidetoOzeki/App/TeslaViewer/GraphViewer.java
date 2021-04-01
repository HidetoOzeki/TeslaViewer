package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import jp.HidetoOzeki.App.TeslaViewer.Data.TideLevel.TideLevel;

public class GraphViewer extends Scene {
	ArrayList<TideLevel> tidelevels = new ArrayList<>();
	static String[] log = new String[10];
	int logcount = 0;
	int imageid = 0;
	int datas = 89;
	public int loaded = 0;
	boolean doneloading = false;
	long datetime;

	public GraphViewer(Main main,int d) {
		super(main);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年M月d日");
		datetime = System.currentTimeMillis()-TimeUnit.DAYS.toMillis(d);
		File file = new File("data/"+dateformat.format(datetime));
		if (!file.mkdir()) {
			System.out.println("今日のデータファイルを検出");
		} else {
			System.out.println("今日のデータファイルを生成");
		}
		File tidalData_img = new File("data/" + dateformat.format(datetime) + "/tidalData_img");
		File tidalData_num = new File("data/" + dateformat.format(datetime) + "/tidalData_num");
		File tidalData_excel = new File("data/" + dateformat.format(datetime) + "/tidalData_excel");
		tidalData_img.mkdir();
		tidalData_num.mkdir();
		tidalData_excel.mkdir();
		// TODO Auto-generated constructor stub
	}

	public void log(String s) {
		if (logcount < 10) {
			log[logcount] = s;
			logcount++;
		} else {
			for (int i = 0; i < 10; i++) {
				if (i + 1 < 10) {
					log[i] = log[i + 1];
					log[i + 1] = s;
				}
			}
		}
	}
	/*
	 * public void init() { tidelevels.removeAll(tidelevels); loaded = 0;
	 * doneloading = false; for (int i = 0; i <
	 * main.data.getLatestTideLevel().length; i++) { tidelevels.add(new
	 * TideLevel(main.data.getLatestTideLevel()[i])); loaded++; main.render(); }
	 * doneloading = true; System.out.println(loaded + "data loaded"); }
	 */

	public void unload() {
		tidelevels.removeAll(tidelevels);
	}

	public void showLog(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		for (int i = 0; i < 10; i++) {
			if (log[i] != null) {
				g.setColor(Color.GREEN);
				g.drawString(log[i], WIDTH - 80, 48 + i * 24);
			}
		}
	}

	public void ScrollText(Graphics g, String s, int x, int y,Color c,int size) {
		x -= xoffset;
		y -= yoffset;
		g.setColor(c);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,size));
		g.drawString(s, x, y);
	}

	TideLevel tldetail = null;

	public void ShowTideLevels(Graphics g) {
		showLog(g);
		g.setColor(Color.GREEN);
		g.drawString("読み込み中", WIDTH - 90, HEIGHT - 84);
		g.drawString(loaded + "/" + datas, WIDTH - 90, HEIGHT - 64);
		int col = 0;
		int row = 0;
		tldetail = null;
		for (int i = 0; i < tidelevels.size(); i++) {
			if (col > 5) {
				col = 0;
				row++;
			}
			int xpos = col * 165;
			int ypos = row * 90;
			g.setColor(Color.BLACK);
			TideLevel tl = tidelevels.get(i);
			// g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			tl.render(xoffset, yoffset, g, 16 + xpos, 36 + ypos, 4, imageid);
			ScrollText(g, tl.getName(), 18 + xpos, 52 + ypos,Color.black,14);
			if(!tl.isDataAvailable())
				ScrollText(g, "[!]", 18 + xpos, 58 + ypos,Color.RED,24);
			g.setColor(Color.BLACK);
			col++;
			if (tl.onMouse(mouseX, mouseY)) {
				tldetail = tl;
			}
		}
		if (tldetail != null)
			tldetail.renderDetail(g, mouseX, mouseY, 1, imageid);
	}

	@Override
	public void input(Key key) {
		if (key.keys[KeyEvent.VK_ESCAPE]) {// exit to title
			unload();
			main.setScene(new Title(main));
		}
		int scrollspeed = 16;
		yoffset = mouseWheelRotation * scrollspeed;
		if (key.keys[KeyEvent.VK_DOWN])
			yoffset += scrollspeed;
		if (key.keys[KeyEvent.VK_UP])
			yoffset -= scrollspeed;
		if (key.keys[KeyEvent.VK_D])
			imageid++;// switch image
		if (imageid > 1)
			imageid = 0;
		key.keys[KeyEvent.VK_D] = false;
	}

	@Override
	public void update() {
		if (!doneloading) {
			TideLevel t = new TideLevel(main.data.getLatestTideLevel()[loaded],datetime);
			tidelevels.add(t);
			loaded++;
			if(t.isDataAvailable()) {
			//main.data.saveImage(t.getImage(), datetime, t.getName());
			//main.data.saveImage(t.getdiffImage(), datetime, t.getName() + "(偏差)");
			main.data.saveTidalData(t.getData(), datetime, t.getName());
			//main.data.saveExcelTidalData(t.getValueData(), datetime, t.getName());
			}
			main.render();
			if (loaded >= datas) {
				doneloading = true;
			}
		}
		super.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT * 2);
		ShowTideLevels(g);
	}

}
