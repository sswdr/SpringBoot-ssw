package com.ssw.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;

/**
 * @author ssw
 * @date 2023/12/12 14:12
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @Qualifier("noSSLAuthRestTemplate")
    public RestTemplate restTemplate() throws Exception {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);

        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

//    // 不推荐
//    private static final RestTemplate restTemplate;
//
//    static {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(15000);
//        factory.setReadTimeout(5000);
//
//        // ignore ssl
//        try {
//            TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
//            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//            SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//
//            HttpClientBuilder httpClientBuilder = HttpClients.custom();
//            httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
//            CloseableHttpClient httpClient = httpClientBuilder.build();
//            factory.setHttpClient(httpClient);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        restTemplate = new RestTemplate();
//    }
//
//    private static <T> ResponseEntity<T> get(String url,Class<T> type) {
//        return restTemplate.postForEntity(url, null, type);
//    }

//    // 不生效
//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        // 创建一个SSL上下文，并配置它以忽略SSL认证
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
//            public void checkClientTrusted(X509Certificate[] chain, String authType) {
//            }
//            public void checkServerTrusted(X509Certificate[] chain, String authType) {
//            }
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        }}, new java.security.SecureRandom());
//        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//        // 创建一个RestTemplate实例
//        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
//        return restTemplate;
//    }
}
