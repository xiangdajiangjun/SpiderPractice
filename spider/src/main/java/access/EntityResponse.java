package access;

import lombok.Data;
import lombok.ToString;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
@Data
@ToString
public class EntityResponse {
    //获取的实体,可获取内容、长度、类型、编码
    private String content;
    private  Long contentLength;
    private String contentType;//暂时不知道为何为空


    //状态列表，可获取状态码、http版本、是否ok
    private StatusLine statusLine;
    //地区标识
    private Locale locale;
    //头部信息
    Map<String, String> headerMap;

    public EntityResponse(CloseableHttpResponse closeableHttpResponse) throws IOException {
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        content = EntityUtils.toString(httpEntity);
        contentLength = httpEntity.getContentLength();
        contentType = httpEntity.getContentType().getValue();
        //还有一个httpEntity.getContentEncoding()好像可以获取编码，但测试时为null，先不管
        statusLine = closeableHttpResponse.getStatusLine();
        locale = closeableHttpResponse.getLocale();
        headerMap = Arrays.stream(closeableHttpResponse.getAllHeaders()).collect(Collectors.toMap(Header::getName,Header::getValue,(k1, k2)->k1));
    }
}
