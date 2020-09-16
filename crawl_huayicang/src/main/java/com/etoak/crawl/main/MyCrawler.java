package com.etoak.crawl.main;

import java.util.List;
import java.util.Set;

import com.etoak.crawl.link.LinkFilter;
import com.etoak.crawl.link.Links;
import com.etoak.crawl.page.Page;
import com.etoak.crawl.page.PageElement;
import com.etoak.crawl.page.PageParserTool;
import com.etoak.crawl.page.RequestAndResponseTool;
import com.etoak.crawl.util.FileTool;

public class MyCrawler {

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
				if (url.startsWith("http://imgali.hxgqw.com/pic")) {
					return true;
				} else if (url.startsWith("http://www.huayicang.com/hxgq/xpai/index.jsp")) {
					return true;
				} else {
					return false;
				}
			}
		};

		// 循环条件：待抓取的链接不空且抓取的网页不多于 1000
		while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 1000) {

			// 先从待访问的序列中取出第一个；
			String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
			if (visitUrl == null) {
				continue;
			}

			System.out.println(visitUrl);
			// 根据URL得到page;
			Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);
			if (visitUrl.startsWith("http://imgali.hxgqw.com/pic")) {
				// 将保存文件
//				FileTool.saveImage(page, "");
			} else if (visitUrl.startsWith("http://www.huayicang.com/hxgq/xpai/index.jsp")){
				FileTool.saveToLocal(page);
			}

			// 将已经访问过的链接放入已访问的链接中；
			Links.addVisitedUrlSet(visitUrl);

			// 得到超链接
			Set<String> links = PageParserTool.getLinks(page, "img");
			for (String link : links) {
				if (filter.accept(link)) {
					Links.addUnvisitedUrlQueue(link);
					System.out.println("新增爬取图片路径: " + link);
				}
			}

			// 得到场次的对应链接
			List<String[]> changciList = PageElement.getChangci(page);
			for (String[] strings : changciList) {
				if (filter.accept(strings[1])) {
					Links.addUnvisitedUrlQueue(strings[1]);
					System.out.println("新增爬取路径: " + strings[1]);
				}
				System.out.println("场次:" + strings[0] + "\t链接：" + strings[1] + "\t数量：" + strings[2] + "\n");
			}

			// 得到商品对应链接
			if (page.getUrl().indexOf("http://www.huayicang.com/hxgq/xpai/index.jsp") != -1) {
//				List<String> goodList = PageElement.getGood(page);
//				for (String string : goodList) {
//					if (filter.accept(string)) {
//						Links.addUnvisitedUrlQueue(string);
//						System.out.println("新增爬取商品路径: " + string);
//					}
//				}
			}

		}
	}

	// main 方法入口
	public static void main(String[] args) {
		MyCrawler crawler = new MyCrawler();
		// crawler.crawling(new String[] {
		// "http://www.huayicang.com/hxgq/xpai/goods_cc-18-1102-26.html" });
		crawler.crawling(new String[] { "http://www.huayicang.com" });

	}
}
