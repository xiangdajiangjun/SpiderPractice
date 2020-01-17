package service.bilibili;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import tool.Request;
import tool.Response;

public  class BilibiliSpider {
     public static void main(String[] args) throws Exception {
         String uri ="http://www.bilibili.com/";
         Request request = Request.build();
         Response entityResponse = request.get(uri);
         if(!(entityResponse.getStatusLine().getStatusCode()==200)){
             throw new Exception("访问失败，状态码："+entityResponse.getStatusLine().getStatusCode()+"");
         }
         Document document = Jsoup.parse(entityResponse.getContent());
         System.out.println(document);
     }
}
