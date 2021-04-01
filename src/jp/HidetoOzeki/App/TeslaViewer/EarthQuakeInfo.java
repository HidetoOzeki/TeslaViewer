package jp.HidetoOzeki.App.TeslaViewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;

public class EarthQuakeInfo extends Scene {
	class EarthQuake {
		int intensity;
		int time_month = 0;
		int time_day = 0;
		int time_hour = 0;
		int time_min = 0;
		int totaltime = 0;
		String t,n,m,i;
		String url;
		String publishedTime;
		public EarthQuake(String time, String name, String magnitude, String Intensity, int intensity_num) {
			t = time;
			n = name;
			m = magnitude;
			i = Intensity;
			intensity = intensity_num;
		}
		public EarthQuake(String time, String name, String magnitude, String Intensity, int intensity_num,String timeadv,String pT) {
        this(time,name,magnitude,Intensity,intensity_num);
        time_month = Integer.parseInt(timeadv.substring(6,8));
        int dhmoffset = timeadv.lastIndexOf("-")+1;
        time_day = Integer.parseInt(timeadv.substring(dhmoffset,dhmoffset+2));
        time_hour = Integer.parseInt(timeadv.substring(dhmoffset+2,dhmoffset+4));
        time_min = Integer.parseInt(timeadv.substring(dhmoffset+4,dhmoffset+6));
        url = timeadv+".html";
        System.out.println(url);
        totaltime+=time_min;
        totaltime+=time_hour*60;
        totaltime+=(time_day*24*60);
        totaltime+=(time_month*30*24*60);
        publishedTime = pT;
		}

		public int getIntensity() {
			return intensity;
		}
		public int getTime_month() {
			return time_month;
		}
		public int getTime_day() {
			return time_day;
		}
		public int getTime_hour() {
			return time_hour;
		}
		public int getTime_min() {
			return time_min;
		}
		public int getTime() {
			return totaltime;
		}
		
	}

	ArrayList<EarthQuake> earthquakes = new ArrayList<EarthQuake>();
	int sort = 0;

	public EarthQuakeInfo(Main main) {
		super(main);
		load();
		// TODO Auto-generated constructor stub
	}

	public String[] extractInfo(String line) {
		String[] info = new String[5];
		int[] start = new int[6];
		int[] end = new int[6];
		int scount = 0;
		int ecount = 0;
		for (int i = 0; i < line.length(); i++) {
			if (i + 1 >= line.length())
				break;
			if (i - 1 <= 0)
				continue;
			if (line.charAt(i) == '>' && line.charAt(i + 1) != '<') {
				start[scount] = i+1;
				scount++;
			}
			if (line.charAt(i) == '<' && line.charAt(i - 1) != '>') {
				end[ecount] = i;
				ecount++;
			}
		}
		for(int i = 0;i < 5;i++) {
			info[i] = line.substring(start[i],end[i]);
		}
		return info;
	}

	public void load() {
		try {
			URL url = new URL("https://www.jma.go.jp/jp/quake/quake_local_index.html");
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			InputStream instream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if(line.contains(">êkìxÇP<")){
					String timeadv = line.substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],1,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇQ<")){
					String timeadv = line.substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],2,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇR<")){
					String timeadv = line.trim().substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],3,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇS<")){
					String timeadv = line.trim().substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],4,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇT<")){
					String timeadv = line.trim().substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],5,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇU<")){
					String timeadv = line.trim().substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],6,timeadv,s[4]));
				}
				if(line.contains(">êkìxÇV<")){
					String timeadv = line.trim().substring(line.lastIndexOf("./"),line.indexOf(".html"));
					line = line.trim();
					String[] s = extractInfo(line);
					earthquakes.add(new EarthQuake(s[0],s[1],s[2],s[3],7,timeadv,s[4]));
				}
				// EarthQuake(line.substring(57,57+17), line, line, line, HEIGHT)));
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void input(Key key) {
		if(key.keys[KeyEvent.VK_ESCAPE])main.setScene(new Title(main));
		int scrollspeed = 16;
		yoffset=this.mouseWheelRotation*scrollspeed;
		if(key.keys[KeyEvent.VK_1])earthquakes.sort(Comparator.comparing(EarthQuake::getIntensity).reversed());
		if(key.keys[KeyEvent.VK_2])earthquakes.sort(Comparator.comparing(EarthQuake::getIntensity));
		if(key.keys[KeyEvent.VK_3]) earthquakes.sort(Comparator.comparing(EarthQuake::getTime).reversed());
		if(key.keys[KeyEvent.VK_4]) earthquakes.sort(Comparator.comparing(EarthQuake::getTime));
		if (key.keys[KeyEvent.VK_DOWN])
			yoffset += scrollspeed;
		if (key.keys[KeyEvent.VK_UP])
			yoffset -= scrollspeed;
		key.keys[KeyEvent.VK_1]=false;
		key.keys[KeyEvent.VK_2]=false;
		key.keys[KeyEvent.VK_3]=false;
		key.keys[KeyEvent.VK_4]=false;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,14));
		for(int i = 0;i < earthquakes.size();i++) {
			int xx = 32;
			int yy = 64+i*16;
			yy-=yoffset;
			EarthQuake e = earthquakes.get(i);
			switch(e.intensity) {
			case 1:
				g.setColor(new Color(0x55B259));
				break;
			case 2:
				g.setColor(new Color(0x8D8E00));
				break;
			case 3:
				g.setColor(new Color(0xE08E5C));
				break;
			default:
				g.setColor(new Color(0xAD3737));
				break;
			}
		g.drawString("ì˙éûÅF"+e.t+"   èÍèäÅF"+e.n, xx, yy);
		g.drawString("É}ÉOÉjÉ`ÉÖÅ[ÉhÅF"+e.m+"  "+e.i, xx+500, yy);
		g.drawString("åˆï\ì˙éû ÅF"+e.publishedTime, xx+750, yy);
		}
		int xo = 200;
		g.setColor(Color.white);
		g.drawString("É\Å[Ég", WIDTH-xo, HEIGHT/2);
		g.drawString("1.êkìxç~èá", WIDTH-xo, 16+HEIGHT/2);
		g.drawString("2.êkìxè∏èá", WIDTH-xo, 16*2+HEIGHT/2);
		g.drawString("3.ì˙éûç~èá", WIDTH-xo+100, 16+HEIGHT/2);
		g.drawString("4.ì˙éûè∏èá", WIDTH-xo+100, 16*2+HEIGHT/2);	
		}

}
