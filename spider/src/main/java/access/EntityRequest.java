package access;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EntityRequest {
    private EntityRequest(){}
    private EntityRequest(String url){

        }
    public static EntityRequest build(String uri){
        return new EntityRequest(uri);
    }
    public static EntityRequest build(){
        return new EntityRequest();
    }
    public EntityResponse get(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        return deal(httpGet);
    }
    public EntityResponse post(String url) throws IOException {
        HttpPost httpPost;
        httpPost=new HttpPost(url);
        return deal(httpPost);
    }
    private EntityResponse deal(HttpUriRequest httpUriRequest) throws IOException {
        httpUriRequest.setHeader(ConstantsRequest.UserAgent,ConstantsRequest.Chrome );
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse =closeableHttpClient.execute(httpUriRequest);
        EntityResponse response =new EntityResponse(closeableHttpResponse);
        closeableHttpResponse.close();
        closeableHttpClient.close();
        return response;
    }
}
