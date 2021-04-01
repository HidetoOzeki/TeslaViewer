package jp.HidetoOzeki.App.TeslaViewer.Data.TideLevel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jp.HidetoOzeki.App.TeslaViewer.Main;

public class TideLevel {
	int diffw = 650;
	int diffh = 250;
	int imgw = 650;
	int imgh = 350;
	int id;
	int longitude = 0;
	int latitude = 0;
	boolean DataAvailable = true;
	String name;
	String LocationName;
	String DataUrl;
	BufferedImage image;
	BufferedImage image_diff;
	int renderx, rendery;
	String[] data;
	ArrayList<String> numdata = new ArrayList<String>();
	String[] numvalue;
	// GraphViewer gv;
	public String parseExcel(String s) {
		String res = "";
		res+=s.substring(0,4);
		res+="-";
		res+=s.substring(5,7);
		res+="-";
		res+=s.substring(8,10);
		res+=",";
		res+=s.substring(11,13);
		res+=":";
		res+=s.substring(14,16);
		res+=",";
		res+=Integer.parseInt(s.substring(17,21).trim());
		return res;
	}

	public void readFromURL(long time) {
		String strPostUrl = DataUrl;
		String strContentType = "application/x-www-form-urlencoded";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formParam = "dspymd="+sdf.format(time);
		HttpURLConnection con = null;
		try {    
		    URL url = new URL(strPostUrl);
		    con = (HttpURLConnection) url.openConnection();
		    con.setDoOutput(true);
		    con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type", strContentType);
		    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(formParam);
		    out.close();
		    con.connect();
		    final int status = con.getResponseCode();
		    if (status == HttpURLConnection.HTTP_OK) {
		        final InputStream in = con.getInputStream();
		        String encoding = con.getContentEncoding();
		        if(null == encoding){
		            encoding = "UTF-8";
		        }
		        final InputStreamReader inReader = new InputStreamReader(in, encoding);
		        final BufferedReader bufReader = new BufferedReader(inReader);
		        String line = "";
				boolean getnumdata = false;
				while ((line = bufReader.readLine()) != null) {
					if (line.contains("</pre>"))
						getnumdata = false;
					if (getnumdata)
						numdata.add(line);
					if (line.contains("<pre>"))
						getnumdata = true;
					if (line.contains("<title>"))
						name = line.substring(13, line.lastIndexOf('リ')); // remove "realtime"
					if (line.contains("Location")) {
						LocationName = line.substring(18).trim();
						if (line.contains("Longitude"))
							longitude = Integer.parseInt(line.substring(18).trim());
						if (line.contains("Latitude"))
							latitude = Integer.parseInt(line.substring(18).trim());
					}
				}
		        bufReader.close();
		        inReader.close();
		        in.close();
		    }else{
		        System.out.println(status);
		    }
		 
		}catch (Exception e1) {
		    e1.printStackTrace();
		} finally {
		    if (con != null) {
		        // コネクションを切断
		        con.disconnect();
		    }
		}
		if(!numdata.get(9).contains("<p>")) {
		 int l = numdata.size()-9;
		    numvalue = new String[l];
		    for(int i = 0;i < l;i++) {
		    	numvalue[i] = parseExcel(numdata.get(9+i));
		    }
		}else {
			DataAvailable = false;
		}
		//System.out.println("Location : " + LocationName);
		// System.out.println("Latitude : "+latitude+" Longitude : "+longitude);
		//System.out.println(name);
		// gv.log(name);

	}
	public boolean isDataAvailable() {
		return DataAvailable;
	}

	public void getImageFromURL(long time) {
		String strPostUrl = DataUrl;
		String strContentType = "application/x-www-form-urlencoded";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formParam = "dspymd="+sdf.format(time);
		HttpURLConnection con = null;
		StringBuffer result = new StringBuffer();
		 
		try {   
		     
		    URL url = new URL(strPostUrl);
		     
		    
		        con = (HttpURLConnection) url.openConnection();
		    
		     
		    con.setDoOutput(true);
		    con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type", strContentType);
		    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(formParam);
		    out.close();
		    con.connect();
 
		    // HTTPレスポンスコード
		    final int status = con.getResponseCode();
		    if (status == HttpURLConnection.HTTP_OK) {
		        // 通信に成功した
		        // テキストを取得する
		        final InputStream in = con.getInputStream();
		        String encoding = con.getContentEncoding();
		        if(null == encoding){
		            encoding = "UTF-8";
		        }
		        final InputStreamReader inReader = new InputStreamReader(in, encoding);
		        final BufferedReader bufReader = new BufferedReader(inReader);
		        URL img1 = new URL("https://www1.kaiho.mlit.go.jp/TIDE/gauge/temp/" + LocationName + ".png");
				URL img2 = new URL("https://www1.kaiho.mlit.go.jp/TIDE/gauge/temp/" + LocationName + "_diff.png");
		        image = ImageIO.read(img1);
				image_diff = ImageIO.read(img2);
		        bufReader.close();
		        inReader.close();
		        in.close();
		    }else{
		        System.out.println(status);
		    }
		 
		}catch (Exception e1) {
		    e1.printStackTrace();
		} finally {
		    if (con != null) {
		        // コネクションを切断
		        con.disconnect();
		    }
		}
	}

	public TideLevel(String DataUrl) {
		// this.id = id;
		this(DataUrl, System.currentTimeMillis());
	}
	public TideLevel(String DataUrl,long datadate) {
		// this.id = id;
		this.DataUrl = DataUrl;
		readFromURL(datadate);
		//getImageFromURL(datadate);
	}

	public void render(int xo, int yo, Graphics g, int x, int y, int div, int n) {
		x -= xo;
		y -= yo;
		renderx = x;
		rendery = y;
		switch (n) {
		case 0:
			g.drawImage(image, x, y, imgw / div, imgh / div, null);
			break;
		case 1:
			g.drawImage(image_diff, x, y, diffw / div, diffh / div, null);
			break;
		}
		// g.drawImage(image_diff, x,y+imgh/div,diffw/div,diffh/div,null);
	}

	public void renderDetail(Graphics g, int x, int y, int div, int n) {
		if ((x + imgw / div) > Main.WIDTH)
			x -= imgw / div;
		if ((y + imgh / div) > Main.HEIGHT)
			y -= imgh / div;
		switch (n) {
		case 0:
			g.drawImage(image, x, y, imgw / div, imgh / div, null);
			g.drawRect(x, y, imgw / div, imgh / div);
			break;
		case 1:
			g.drawImage(image_diff, x, y, diffw / div, diffh / div, null);
			g.drawRect(x, y, diffw / div, diffh / div);
			break;
		}
		g.drawString(name, x + 32, y + 32);
		// g.drawImage(image_diff, x,y+imgh/div,diffw/div,diffh/div,null);
	}

	public String getName() {
		return name;
	}

	public boolean onMouse(int mx, int my) {
		boolean xin = mx > renderx && mx < renderx + (imgw / 4);
		boolean yin = my > rendery && my < rendery + (imgh / 4);
		return (xin && yin);
	}

	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage getdiffImage() {
		return image_diff;
	}

	public String[] getData() {
		return numdata.toArray(new String[numdata.size()]);
	}
	public String[] getValueData() {
		return numvalue;
	}

}
