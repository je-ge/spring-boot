package com.jege.spring.boot.xml;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
public class UserControllerTest {

  @Test
  public void testPost() throws Exception {
    // 直接字符串拼接
    StringBuilder builder = new StringBuilder();
    // xml数据存储
    builder.append("<user><id>1</id><Content>JE-GE</Content></user>");
    String data = builder.toString();
    System.out.println(data);
    String url = "http://localhost:8080/user";
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(new StringEntity(data, "text/xml", "utf-8"));
    CloseableHttpResponse response = httpClient.execute(httpPost);
    HttpEntity entity = response.getEntity();
    String content = EntityUtils.toString(entity);
    System.out.println("content:" + content);
  }
}
