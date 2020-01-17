package tool;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Request {
    private Request(){}
    private Request(String url){

        }
    public static Request build(String uri){
        return new Request(uri);
    }
    public static Request build(){
        return new Request();
    }
    public Response get(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        return deal(httpGet);
    }
    public Response post(String url) throws IOException {
        HttpPost httpPost;
        httpPost=new HttpPost(url);
        return deal(httpPost);
    }
    private Response deal(HttpUriRequest httpUriRequest) throws IOException {
        httpUriRequest.setHeader(ConstantsRequest.UserAgent,ConstantsRequest.Chrome );
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse =closeableHttpClient.execute(httpUriRequest);
        Response response =new Response(closeableHttpResponse);
        closeableHttpResponse.close();
        closeableHttpClient.close();
        return response;
    }
}
