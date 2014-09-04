package com.jdkcn.baomu.checker;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The default health checker implement.
 * 
 * @author rory
 */
public class DefaultChecker implements Checker {

    private final Logger logger = LoggerFactory.getLogger(DefaultChecker.class);

    /**
     * {@inheritDoc}
     */
    public boolean health(String url) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        SSLContext sslContext = createTrustAnySslContext();
        clientBuilder.setSslcontext(sslContext);
        CloseableHttpClient httpClient = clientBuilder.build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(20000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        try {
            HttpClientContext context = HttpClientContext.create();
            HttpResponse httpResponse = httpClient.execute(httpGet, context);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            return statusCode == HttpStatus.SC_OK;
        } catch (Exception e) {
            logger.error("check health for url[{}] error:", url,  e);
        }
        return false;
    }

    private SSLContext createTrustAnySslContext() {
        SSLContext sslContext = SSLContexts.createDefault();
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }
}
