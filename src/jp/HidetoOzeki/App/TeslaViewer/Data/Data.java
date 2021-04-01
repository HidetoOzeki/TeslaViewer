package jp.HidetoOzeki.App.TeslaViewer.Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jp.HidetoOzeki.App.TeslaViewer.Main;
import jp.HidetoOzeki.App.TeslaViewer.Data.TideLevel.TideLevel;

public class Data {
	Main main;
	long now = System.currentTimeMillis();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年M月d日");
	SimpleDateFormat timeformat = new SimpleDateFormat("H時m分");
	String[] tld_latest = new String[] { "https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0001",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0004",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0006",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0008",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0016",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0023",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0029",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0032",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0033",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0035",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0036",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0037",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0038",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0042",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0052",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0053",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0054",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0062",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0063",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0065",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0055",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0056",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0057",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0058",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0059",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0068",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0070",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0071",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0073",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0074",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0075",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0079",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0081",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0082",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0083",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0087",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0089",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0091",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0095",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0113",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0101",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0103",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0104",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0105",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0106",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0107",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0108",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0109",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0111",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0115",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0117",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0118",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0120",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0121",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0122",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0136",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0123",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0127",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0128",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0129",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0130",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0132",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0133",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0134",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0149",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0152",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0154",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0156",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0157",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0158",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0159",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0161",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0163",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0164",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0168",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0171",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0173",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0174",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0175",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0177",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0178",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0179",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0180",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0181",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0184",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0185",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0187",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0188",
			"https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0060" };

	public Data(Main main) {
		this.main = main;
		File file = new File("data");
		if (!file.mkdir()) {
			System.out.println("データファイルを検出");
		} else {
			System.out.println("データファイルを生成");
		}
		// generate current day archive file
		File nowdata = new File("data/" + dateformat.format(now));
		if (!nowdata.mkdir()) {
			System.out.println("今日のデータファイルを検出");
		} else {
			System.out.println("今日のデータファイルを生成");
		}
		File tidalData_img = new File("data/" + dateformat.format(now) + "/tidalData_img");
		File tidalData_num = new File("data/" + dateformat.format(now) + "/tidalData_num");
		tidalData_img.mkdir();
		tidalData_num.mkdir();
	}

	public void saveImage(BufferedImage img, long saveday,String name) {
		try {
			ImageIO.write(img, "png", new File("data/" + dateformat.format(saveday) + "/tidalData_img/" + name + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveTidalData(String[] data, long saveday ,String name) {
		save(data, "data/" + dateformat.format(saveday) + "/tidalData_num/" + name,"txt");
	}
	public void saveExcelTidalData(String[] data, long saveday ,String name) {
		save(data, "data/" + dateformat.format(saveday) + "/tidalData_excel/" + name,"csv");
	}

	public void save(String[] s, String path,String type) {
		try {
			PrintWriter writer = new PrintWriter(path + "."+type, "UTF-8");
			for (int i = 0; i < s.length; i++) {
				writer.println(s[i]);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getLatestTideLevel() {
		return tld_latest;
	}

	public void loadTideLevels(ArrayList<TideLevel> tl) {// hokkaidou
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0001"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0004"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0006"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0008"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0016"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0023"));
		main.render();// touhoku
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0029"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0032"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0033"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0035"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0036"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0037"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0038"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0042"));
		main.render();// kanntou
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0052"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0053"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0054"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0062"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0063"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0065"));
		main.render();// izu
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0055"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0056"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0057"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0058"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0059"));
		main.render();// toukai
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0068"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0070"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0071"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0073"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0074"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0075"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0079"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0081"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0082"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0083"));
		main.render();// hokuriku
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0087"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0089"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0091"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0095"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0113"));
		main.render();// kinnki
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0101"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0103"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0104"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0105"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0106"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0107"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0108"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0109"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0111"));
		main.render();// chuugoku
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0115"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0117"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0118"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0120"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0121"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0122"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0136"));
		main.render();// shikoku
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0123"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0127"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0128"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0129"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0130"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0132"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0133"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0134"));
		main.render();// kyushu
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0149"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0152"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0154"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0156"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0157"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0158"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0159"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0161"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0163"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0164"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0168"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0171"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0173"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0174"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0175"));
		main.render();// nannsei
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0177"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0178"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0179"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0180"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0181"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0184"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0185"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0187"));
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0188"));
		main.render();
		tl.add(new TideLevel("https://www1.kaiho.mlit.go.jp/TIDE/gauge/gauge.php?s=0060"));
	}
}
