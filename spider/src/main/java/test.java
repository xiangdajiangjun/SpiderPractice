import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args){
        String html =null;
        //发出请求，得到respond
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.bilibili.com/");
        //httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); // 设置请求头消息User-Agent
        httpGet.setHeader("User-Agent", "mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/79.0.3945.79 safari/537.36"); // 设置请求头消息User-Agent
        try {

            CloseableHttpResponse closeableHttpResponse =closeableHttpClient.execute(httpGet);
            if(closeableHttpResponse.getStatusLine().getStatusCode()!=200){
                System.out.println("失败，状态码："+closeableHttpResponse.getStatusLine().getStatusCode());
                throw new IOException();
            }
            html = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            closeableHttpResponse.close();
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //处理
        if(html==null){
            System.exit(1);
        }
        Document document =Jsoup.parse(html);
        if(document==null){
            System.out.println("失败");
            System.exit(1);}
        else {
            System.out.println("访问成功");
            System.out.println(document.toString());
        }
    }
}
