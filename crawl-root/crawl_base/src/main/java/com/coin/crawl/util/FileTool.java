package com.coin.crawl.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coin.crawl.page.Page;

/*  本类主要是 下载那些已经访问过的文件*/
public class FileTool {

	public static String dirPath = "d:/tmp/";

	/**
	 * getMethod.getResponseHeader("Content-Type").getValue() 根据 URL
	 * 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
	 */
	private static String getFileNameByUrl(String url, String contentType) {
		// 去除 http://
		url = url.substring(7);
		// text/html 类型
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		}
		// 图片类型
		else if (contentType.indexOf("jpg") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".jpg";
			return url;
		}
		// 如 application/pdf 类型
		else {
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}

	/*
	 * 生成目录
	 */
	private static void mkdir() {
		if (dirPath == null) {
			dirPath = Class.class.getClass().getResource("/").getPath() + "temp\\";
		}
		File fileDir = new File(dirPath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
	}

	/**
	 * 保存网页字节数组到本地文件，filePath 为要保存的文件的相对地址
	 */

	public static void saveToLocal(Page page) {
		mkdir();
		String fileName = getFileNameByUrl(page.getUrl(), page.getContentType());
		String filePath = dirPath + fileName;
		byte[] data = page.getContent();
		try {
			// Files.lines(Paths.get("D:\\jd.txt"),
			// StandardCharsets.UTF_8).forEach(System.out::println);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
			for (int i = 0; i < data.length; i++) {
				out.write(data[i]);
			}
			out.flush();
			out.close();
			System.out.println("文件：" + fileName + "已经被存储在" + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: saveImage<br>
	 * @Description: 保存图片<br>
	 * @param page
	 * @param fileName1
	 *            <br>
	 */
	public static void saveImage(String url, String filePath) {
		downloadPicture(url,filePath);
	}

	/**
	 * @Title: saveChangci<br>
	 * @Description: 场次则创建文件夹<br>
	 * @param page
	 * @param fileName <br>
	 */
	public static void saveChangci(String pathName) {
		File fileDir = new File(dirPath+pathName+"/");
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
	}
	
	/**
     * @Title: downloadPicture<br>
     * @Description: 链接url下载图片<br>
     * @param urlList
     * @param path <br>
     */
    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
 
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
 
            byte[] buffer = new byte[1024];
            int length;
 
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void downloadPicture1(String urlList,String path){
        try{
//            URL url = new URL(urlList);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(5 * 1000);
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36");
//            conn.setRequestProperty("Accept-Language", "zh-CN,zh-TW;q=0.9,zh;q=0.8,en-US;q=0.7,en;q=0.6");
//            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            conn.setRequestProperty("Connection", "keep-alive");
//            conn.setRequestProperty("Cookie", "UM_distinctid=174722f59854a9-05ae822b0ec398-c373667-144000-174722f598665a; __51cke__=; ASPSESSIONIDCWDDTASR=IKPJCLBAPMHGGKJIKCGCFIKE; ASPSESSIONIDAWCCTASQ=ADFJGDFAFFGJHNDELIKHDJKG; CNZZDATA1278618868=1426705223-1599640320-%7C1599658369; __tins__20641871=%7B%22sid%22%3A%201599661745956%2C%20%22vd%22%3A%2023%2C%20%22expires%22%3A%201599665272451%7D; __51laig__=47");
//            conn.setRequestProperty("Host", "www.xiurenji.com");
//            conn.setRequestProperty("Charset", "UTF-8");
//            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
//            InputStream inputStream = conn.getInputStream();
            byte[] x = HttpsUtil.HttpRequestReturnByte(urlList);
        	
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
    		fileOutputStream.write(x);
    		fileOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
        	
		}
	}
    
    public static void main(String[] args) {
    	downloadPicture1("https://www.xiurenji.com/uploadfile/202005/22/6120234446.jpg","d:\\tmp\\3.jpg");
    }
}
