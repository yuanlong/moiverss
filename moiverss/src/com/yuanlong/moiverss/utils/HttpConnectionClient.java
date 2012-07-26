package com.yuanlong.moiverss.utils;

import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.yuanlong.moiverss.exception.NetException;

public class HttpConnectionClient {
	private static int DEFAULT_CONNECTION_TIMEOUT = 3000;

	private static int DEFAULT_CONNECTIONS_MAX_TOTAL = 200;

	private static int DEFAULT_CONNECTIONS_MAX_PERHOST = 50;

	private final ReentrantLock lock = new ReentrantLock();

	private static MultiThreadedHttpConnectionManager connectionManager = null;
	private static HttpClient httpClient = null;

	private int connectionTimeOut = DEFAULT_CONNECTION_TIMEOUT;
	private int connectionMaxTotal = DEFAULT_CONNECTIONS_MAX_TOTAL;
	private int connectionMaxPerHost = DEFAULT_CONNECTIONS_MAX_PERHOST;
	private String codeing = "GBK";

	private HttpClient getHttpClient() {
		this.lock.lock();
		try {
			if (connectionManager == null) {
				connectionManager = new MultiThreadedHttpConnectionManager();
				configure();
			}
			if (httpClient == null)
				httpClient = new HttpClient(connectionManager);
		} finally {
			this.lock.unlock();
		}
		return httpClient;
	}

	private void configure() {
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(this.connectionTimeOut);
		params.setMaxTotalConnections(this.connectionMaxTotal);
		params.setDefaultMaxConnectionsPerHost(this.connectionMaxPerHost);
	}

	public String getContextByGetMethod(String url) {
		HttpClient client = getHttpClient();

		client.getParams().setParameter("http.protocol.content-charset",
				this.codeing);

		GetMethod gm = new GetMethod(url);
		String result = "";
		try {
			client.executeMethod(gm);

			if (gm.getStatusCode() >= 400) {
				throw new HttpException("Connection Error!return Status :"
						+ gm.getStatusCode());
			}
			result = gm.getResponseBodyAsString();
		} catch (Exception e) {
			throw new NetException("HttpClient catch!", e);
		} finally {
			gm.releaseConnection();
		}
		return result;
	}

	public String getContextByPostMethod(String url, List<KeyValue> params) {
		HttpClient client = getHttpClient();

		client.getParams().setParameter("http.protocol.content-charset",
				this.codeing);

		PostMethod post = null;
		String result = "";
		try {
			URL u = new URL(url);
			client.getHostConfiguration().setHost(u.getHost(),
					u.getPort() == -1 ? u.getDefaultPort() : u.getPort(),
					u.getProtocol());

			post = new PostMethod(u.getPath());

			NameValuePair[] nvps = new NameValuePair[params.size()];
			int i = 0;
			for (KeyValue kv : params) {
				nvps[i] = new NameValuePair(kv.getKey(), kv.getValue());
				i++;
			}

			post.setRequestBody(nvps);

			client.executeMethod(post);
			result = post.getResponseBodyAsString();
		} catch (Exception e) {
			throw new NetException("HttpClient catch!", e);
		} finally {
			if (post != null)
				post.releaseConnection();
		}
		return result;
	}

	public int getConnectionTimeOut() {
		return this.connectionTimeOut;
	}

	public void setConnectionTimeOut(int connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public int getConnectionMaxTotal() {
		return this.connectionMaxTotal;
	}

	public void setConnectionMaxTotal(int connectionMaxTotal) {
		this.connectionMaxTotal = connectionMaxTotal;
	}

	public int getConnectionMaxPerHost() {
		return this.connectionMaxPerHost;
	}

	public void setConnectionMaxPerHost(int connectionMaxPerHost) {
		this.connectionMaxPerHost = connectionMaxPerHost;
	}

	public void setCodeing(String codeing) {
		this.codeing = codeing;
	}

	public String getCodeing() {
		return this.codeing;
	}
}
