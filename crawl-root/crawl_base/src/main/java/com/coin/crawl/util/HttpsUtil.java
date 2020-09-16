package com.coin.crawl.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsUtil {

	private static final class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO 自动生成的方法存根

		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO 自动生成的方法存根

		}

		public X509Certificate[] getAcceptedIssuers() {
			// TODO 自动生成的方法存根
			return null;
		}

	}

	private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SSLSocketFactory ssf = ctx.getSocketFactory();

		URL url = new URL(uri);
		HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
		httpsConn.setSSLSocketFactory(ssf);
		httpsConn.setHostnameVerifier(new HostnameVerifier() {

			public boolean verify(String arg0, SSLSession arg1) {
				// TODO 自动生成的方法存根
				return true;
			}
		});
		httpsConn.setRequestMethod(method);
		httpsConn.setDoInput(true);
		httpsConn.setDoOutput(true);
		return httpsConn;
	}

	private static byte[] getBytesFromStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] kb = new byte[1024];
		int len;
		while ((len = is.read(kb)) != -1) {
			baos.write(kb, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		baos.close();
		is.close();
		return bytes;
	}

	private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		byte[] kb = new byte[1024];
		int len;
		while ((len = bais.read(kb)) != -1) {
			os.write(kb, 0, len);
		}
		os.flush();
		os.close();
		bais.close();
	}

	public static byte[] doGet(String uri) throws IOException {
		HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "GET");
		httpsConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");

		return getBytesFromStream(httpsConn.getInputStream());
	}

	public static byte[] doPost(String uri, String data) throws IOException {
		HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "POST");
		setBytesToStream(httpsConn.getOutputStream(), data.getBytes());
		return getBytesFromStream(httpsConn.getInputStream());
	}

	public static String HttpRequest(String requestUrl) {
		StringBuffer buffer = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		HttpsURLConnection httpUrlConn = null;
		// 建立并向网页发送请求
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			// 描述状态
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
			// 防止报403错误。
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 请求的类型
			httpUrlConn.setRequestMethod("GET");
			// 获取输入流
			inputStream = httpUrlConn.getInputStream();
//			getBytesFromStream(inputStream);
			inputStreamReader = new InputStreamReader(inputStream, "GB2312");
			bufferedReader = new BufferedReader(inputStreamReader);

			// 从输入流读取结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return buffer.toString();
	}

	
	public static byte[] HttpRequestReturnByte(String requestUrl) {
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		HttpsURLConnection httpUrlConn = null;
		// 建立并向网页发送请求
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			// 描述状态
			httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
			
			// 防止报403错误。
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 请求的类型
			httpUrlConn.setRequestMethod("GET");
			// 获取输入流
			inputStream = httpUrlConn.getInputStream();
			return getBytesFromStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放资源
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		
	}
	
	
	
}
