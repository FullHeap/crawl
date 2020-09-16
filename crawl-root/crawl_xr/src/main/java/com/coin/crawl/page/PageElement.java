/**  
 * @Title: PageElement.java<br>
 * @Package com.etoak.crawl.page<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 * @version V1.0
 */
package com.coin.crawl.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName: PageElement<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 *       <br>
 */
public class PageElement {
	
	
	
	public static List<String> getPic(Page pagepic) {
		List<String> list = new ArrayList<String>();
		Set<String> links = PageParserTool.getLinks(pagepic, "img");
		for (String string : links) {
			if(string.indexOf("uploadfile")!=-1) {
//				System.out.println("2:"+string);
				list.add(string);
			}
		}
		return list;
	}

	public static List<String> getPageList(Page pagepic) {
		Elements page = PageParserTool.getElementsByClass(pagepic, "page");
		List<String> list = new ArrayList<String>();
		Elements elinks = page.get(0).getElementsByTag("a");
		if (elinks.hasAttr("href")) {
			for (Element element2 : elinks) {
				String x = element2.text();// 名称
				if ("前".equals(x) || "后".equals(x)) {
//					System.out.println("名称为：" + element2.text());
//					System.out.println("去除链接为：" + element2.attr("abs:href"));
				} else {
//					System.out.println("链接为：" + element2.attr("abs:href"));
					list.add(element2.attr("abs:href"));
				}
			}
		}
		return list;
	}

}
