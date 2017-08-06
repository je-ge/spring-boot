package com.jege.spring.boot;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:配置用户访问http自动跳转到https
 */
@Configuration
public class HttpsConfig {

  @Bean
  public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {// 1
      protected void postProcessContext(Context context) {
	SecurityConstraint securityConstraint = new SecurityConstraint();
	securityConstraint.setUserConstraint("CONFIDENTIAL");
	SecurityCollection collection = new SecurityCollection();
	collection.addPattern("/*");
	securityConstraint.addCollection(collection);
	context.addConstraint(securityConstraint);
      }
    };
    tomcat.addAdditionalTomcatConnectors(httpConnector());
    return tomcat;
  }

  @Bean
  public Connector httpConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    // 表示用8080端口来供http访问
    connector.setPort(8080);
    connector.setSecure(false);
    // 自动重定向到8443端口
    connector.setRedirectPort(8443);
    return connector;
  }
}
