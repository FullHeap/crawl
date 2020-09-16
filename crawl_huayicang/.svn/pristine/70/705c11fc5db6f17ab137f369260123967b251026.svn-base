package com.etoak.crawl.util;

import com.etoak.crawl.page.Page;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/*  本类主要是 下载那些已经访问过的文件*/
public class FileTool {

	public static String dirPath = "C:/tmp/";

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
	 * @Title: mkdirPath<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br> <br>
	 */
	private static void mkdirPath(String pathName) {
		// TODO 自动生成的方法存根
		
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
}
