/**  
 * @Title: Crawler.java<br>
 * @Package com.etoak.crawl.main<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 * @version V1.0
 */
package com.etoak.crawl.main;

import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.etoak.crawl.entity.Good;
import com.etoak.crawl.entity.Image;
import com.etoak.crawl.entity.Matches;
import com.etoak.crawl.entity.dao.Goodinfo;
import com.etoak.crawl.link.LinkFilter;
import com.etoak.crawl.link.Links;
import com.etoak.crawl.page.Page;
import com.etoak.crawl.page.PageElement;
import com.etoak.crawl.page.PageParserTool;
import com.etoak.crawl.page.RequestAndResponseTool;
import com.etoak.crawl.util.FileTool;

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
	 * @param seeds
	 *            种子 URL
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

		// 定义过滤器，提取以 http://www.baidu.com 开头的链接
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith("http://www.cguardian.com")) {
					return true;
				} else if (url.startsWith("http://www.huayicang.com/hxgq/xpai/index.jsp")
						&& url.indexOf("27781") != -1) {
					return true;
				} else if (url.startsWith("http://www.huayicang.com/hxgq/xpai/goods")) {
					return true;
				} else {
					return false;
				}
			}
		};

		// 循环条件：待抓取的链接不空且抓取的网页不多于 1000
		// while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum()
		// <= 1000) {

		// 先从待访问的序列中取出第一个；
		String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
		System.out.println(visitUrl);
		// 根据URL得到page;
		Document doc = RequestAndResponseTool.sendRequstAndGetResponseACT(visitUrl);
		System.out.println(doc.html());

		
	}


	// main 方法入口
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		// crawler.crawling(new String[] {
		// "http://www.huayicang.com/hxgq/xpai/goods_cc-18-1102-26.html" });
		crawler.crawling(new String[] { "http://www.cguardian.com/AuctionQuery.html?categoryId=GD-2018-CN006-008-022&auctionName=&categoryName=%E7%BC%A4%E7%BA%B7%E9%9B%86%E2%80%94%E2%80%94%E8%81%9A%E7%84%A6%E7%A7%81%E4%BA%BA%E6%94%B6%E8%97%8F" });
	}

}
