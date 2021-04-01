package jp.HidetoOzeki.App.TeslaViewer.Data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
 
public class MyUtil {
     
    private static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("", 8080));
    private static String proxySwitch = "0";
 
     
    public static String callGet(String strGetUrl){
         
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
         
        try {
             
            URL url = new URL(strGetUrl);
             
            if(proxySwitch.equals("1")){
                con = (HttpURLConnection) url.openConnection(proxy);
            }else{
                con = (HttpURLConnection) url.openConnection();
            }
             
            con.setRequestMethod("GET");
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
                String line = null;
                // 1行ずつテキストを読み込む
                while((line = bufReader.readLine()) != null) {
                    result.append(line);
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
        System.out.println("result=" + result.toString());
         
        return result.toString();
         
         
    }
     
     
    public static String callPost(String strPostUrl, String strContentType, String formParam){
         
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
         
        try {   
             
            URL url = new URL(strPostUrl);
             
            if(proxySwitch.equals("1")){
                con = (HttpURLConnection) url.openConnection(proxy);
            }else{
                con = (HttpURLConnection) url.openConnection();
            }
             
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
                String line = null;
                // 1行ずつテキストを読み込む
                while((line = bufReader.readLine()) != null) {
                    result.append(line);
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
        System.out.println("result=" + result.toString());
         
        return result.toString();
         
         
    }
    
 
}