/**  
 * @Title: Crawler.java<br>
 * @Package com.coin.crawl.main<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 * @version V1.0
 */
package com.coin.crawl.main;

import java.util.List;
import java.util.Set;

import org.jsoup.select.Elements;

import com.coin.crawl.link.Links;
import com.coin.crawl.page.Page;
import com.coin.crawl.page.PageElement;
import com.coin.crawl.page.PageParserTool;
import com.coin.crawl.page.RequestAndResponseTool;
import com.coin.crawl.util.FileTool;

/**
 * @ClassName: Crawler<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 *       <br>
 */
public class Crawler {

	public static String goodNo = "00010000";

	/**
	 * 使用种子初始化 URL 队列
	 *
	 * @param seeds 种子 URL
	 * @return
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			Links.addUnvisitedUrlQueue(seeds[i]);
		}
	}

	/**
	 * 抓取过程
	 *
	 * @param seeds
	 * @return
	 */
	public void crawling(String[] seeds) {

		// 初始化 URL 队列
		initCrawlerWithSeeds(seeds);

		// 先从待访问的序列中取出第一个；
		String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
//		System.out.println(visitUrl);
		// 根据URL得到page;
		Page page = RequestAndResponseTool.sendRequstAndGetHttpsResponse(visitUrl);
		
		Elements titles = PageParserTool.getElementsByClass(page, "title");
		System.out.println(titles.get(0).html());
		FileTool.saveChangci(titles.get(0).html());
		List<String> pageList = PageElement.getPageList(page);
		for (String string : pageList) {
//			System.out.println("x:"+string);
			Page page1 = RequestAndResponseTool.sendRequstAndGetHttpsResponse(string);
			List<String> picList = PageElement.getPic(page1);
			
			for (int i=0; i<picList.size(); i++) {
				String title = picList.get(i).substring(picList.get(i).lastIndexOf("/")+1);
				System.out.println(picList.get(i));
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
				FileTool.saveImage(picList.get(i), FileTool.dirPath + "\\" + titles.get(0).html() + "\\" + title);
				
			}
		}
		
		System.out.println("完成");
	}

	// main 方法入口
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		crawler.crawling(new String[] { "https://www.xiurenji.com/XiuRen/6161.html" });
	}

	
}
