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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.coin.crawl.util.ListUtil;

/**
 * @ClassName: PageElement<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 *       <br>
 */
public class PageElement {
	/**
	 * @Title: getChangci<br>
	 * @Description: 取得所有场次相关信息<br>
	 *               0、名称 1、链接 2、数量
	 * @param page
	 * @return <br>
	 */
	public static List<String[]> getChangci(Page page) {
		List<String[]> changCiList = new ArrayList<String[]>();
		// 对DIV进行处理： 访问DOM的某个标签
		Elements ediv = PageParserTool.getElementsByClass(page, "ml15");
		if (!ediv.isEmpty()) {
			System.out.println("下面将打印所有ml15标签中的链接： ");
			// 取得场次相关信息
			for (Element element : ediv) {
				String[] changci = new String[4];
				// 场次链接相关
				Elements elink = element.getElementsByTag("a");
				if (elink.hasAttr("href")) {
					for (Element element2 : elink) {
						if (element2.attr("abs:href").indexOf("special") != -1) {
							changci[0] = element2.text();// 名称
							changci[1] = element2.attr("abs:href");// 链接
							System.out.println("链接为：" + element2.attr("abs:href"));
							System.out.println("名称为：" + element2.text());
							changCiList.add(changci);
						}
					}
				}

				Elements ep = element.getElementsByTag("p");
				changci[2] = "";
				for (Element element2 : ep) {
					System.out.println("数量为：" + element2.text());
					changci[2] = element2.text() + "|" + changci[2];
				}
				if (changci[1] != null) {
					changCiList.add(changci);
				}
			}
		}
		return changCiList;
	}

	/**
	 * @Title: getGood<br>
	 * @Description: 获得商品链接<br>
	 * @param page
	 * @return <br>
	 */
	public static List<String> getGood(Page page) {
		List<String> goodLinkList = new ArrayList<String>();
		Elements divfl = PageParserTool.getElementsByClass(page, "ml15");
		// Set<String> links = new HashSet<>();
		for (Element element : divfl) {
			Elements elink = element.getElementsByTag("a");
			for (Element element2 : elink) {
				// 若链接中含有goods 则代表是商品
				if (element2.attr("abs:href").indexOf("detail") != -1) {
					System.out.println("链接为：" + element2.attr("abs:href"));
					goodLinkList.add(element2.attr("abs:href"));
					break;
				}
			}
		}
		ListUtil.removeDuplicateWithOrder(goodLinkList);
		
		return goodLinkList;
	}

	/**
	 * @Title: getAssest<br>
	 * @Description: 获得拍卖会<br>
	 * @param page
	 * @return <br>
	 */
	public static List<String[]> getAssest(Page page) {
		List<String[]> assestLinkList = new ArrayList<String[]>();
		Elements ediv = PageParserTool.getElementsByClass(page, "nav_menu");
		if (!ediv.isEmpty()) {
			System.out.println("下面将打印所有nav_menu标签中的链接： ");
			for (Element element : ediv) {
				String[] assest = new String[2];
				Elements elink = element.getElementsByTag("a");
				for (Element element2 : elink) {
					if (element2.attr("abs:href").indexOf("assest") != -1) {
						assest[0] = element2.text();// 名称
						assest[1] = element2.attr("abs:href");// 链接
						System.out.println("链接为：" + element2.attr("abs:href"));
						System.out.println("名称为：" + element2.text());
						assestLinkList.add(assest);
					}
				}
			}
		}
		return assestLinkList;

	}

	public static List<String[]> getChangci() {
		// List<Map<String,String>> li = new ArrayList<Map<String,String>>();
		List<String> li = new ArrayList<String>();
		// li.add("29221|长春站一版币样票专场<br>11月30日(周五)20：00结标");
		// li.add("29142|印玺刻铜专场<br>11月28日(周三)20：00结标");
		// li.add("29101|成都站银锭钱币专场<br>11月27日(周二)20：00结标");
		// li.add("29081|个人古钱专场<br>11月22日(周四)21:00结标");
		// li.add("29181|回流古钱专场<br>11月22日(周四)20:00结标");
		// li.add("29041|徐州站纸币专场<br>11月21日(周三)21：00结标");
		// li.add("28862|香港站回流钱币场<br>11月21日(周三)20：00结标");
		// li.add("29061|古钱专场<br>11月20日(周二)20:00结标");
		// li.add("28921|长春站藏家铜镜专场<br>11月20日(周二)20：00结标");
		// li.add("29161|机制丝路现代币银锭专场<br>11月19日(周一)20：00结标");
		// li.add("28861|香港站回流古玩杂项专场<br>11月19日(周一)20：00结标");
		// li.add("29021|湖南站古钱专场<br>11月18日(周日)20：00结标");
		// li.add("29121|长春站机制币专场<br>11月18日(周日)20：00结标");
		// li.add("28881|广州站古钱机制币专场<br>11月16日(周五)20：00结标");
		// li.add("28841|古钱专场<br>11月15日(周四)21:00结标");
		// li.add("28521|杭州站铜元精品专场<br>11月15日(周四)21：00结标");
		// li.add("29002|古钱精品专场<br>11月15日(周四)20:00结标");
		// li.add("28381|徐州站古钱机制币专场<br>11月14日(周三)21：00结标");
		// li.add("29001|纸币专场<br>11月14日(周三)20：00结标");
		// li.add("28621|上海站机制古钱纸币专场<br>11月14日(周三)20：00结标");
		// li.add("28801|兰州站古钱机制币银锭场<br>11月13日(周二)21：00结标");
		// li.add("28761|古钱专场<br>11月13日(周二)20：00结标");
		// li.add("28661|河北站古钱专场<br>11月12日(周一)21：00结标");
		// li.add("28961|陈年名酒合作专场<br>11月12日(周一)20：00结标");
		// li.add("28901|机制丝路现代币银锭专场<br>11月12日(周一)20：00结标");
		// li.add("28821|藏家个人古钱专场<br>11月9日(周五)20：00结标");
		// li.add("28602|古钱专场<br>11月8日(周四)21：00结标");
		// li.add("28721|古钱精品专场<br>11月8日(周四)20：00结标");
		// li.add("28741|湖北站古钱机制币专场<br>11月7日(周三)21：00结标");
		// li.add("28701|湖南站古钱专场<br>11月07日(周三)20：00结标");
		// li.add("28601|古钱专场<br>11月6日(周二)20：00结标");
		// li.add("28501|陕西站古钱币专场<br>11月5日(周一)21：00结标");
		// li.add("28461|香港站回流钱币工艺品场<br>11月5日(周一)20：00结标");
		// li.add("28681|机制丝路现代币银锭专场<br>11月5日(周一)20：00结标");
		// li.add("28641|长春站中国铜元藏家专场<br>11月2日(周五)20：00结标");
		// li.add("28441|回流古钱专场<br>11月1日(周四)21：00结标");
		// li.add("28442|古钱精品专场<br>11月1日(周四)20：00结标");
		// li.add("28581|奢侈品合作专场<br>10月31日(周三)20：00结标");
		// li.add("28361|青岛站古钱专场<br>10月31日(周三)20：00结标");
		// li.add("28261|上海站机制币古钱专场<br>10月31日(周三)20：00结标");
		/*
		 * li.add("28341|古钱专场<br>10月30日(周二)20：00结标");
		 * li.add("28541|杭州站机制古钱现代币场<br>10月30日(周二)20：00结标");
		 * li.add("28321|广州站古钱机制币专场<br>10月29日(周一)21：00结标");
		 * li.add("28421|湖南站古钱专场<br>10月29日(周一)20：00结标");
		 * li.add("28481|机制丝路现代币银锭专场<br>10月29日(周一)20：00结标"); //
		 * //li.add("28401|陈年名酒合作专场<br>10月29日(周一)20：00结标");
		 * li.add("28181|上海藏家古钱专场<br>10月26日(周五)20：00结标");
		 * li.add("28281|古钱精品专场<br>10月25日(周四)20：00结标");
		 * li.add("28102|成都站机制币专场<br>10月23日(周二)20：00结标");
		 * li.add("28241|古钱专场<br>10月23日(周二)20：00结标");
		 * li.add("28161|河北站古钱专场<br>10月22日(周一)20：00结标");
		 * li.add("28221|香港站字画(工艺品)场<br>10月22日(周一)20：00结标");
		 * li.add("28301|机制丝路现代币银锭专场<br>10月22日(周一)20：00结标");
		 * li.add("28201|回流机制币专场<br>10月17日(周三)20：00结标");
		 * li.add("27961|古泉书馆书籍拓片专场<br>10月17日(周三)20：00结标");
		 * li.add("28141|湖南站银锭机制币专场<br>10月16日(周二)20：00结标");
		 * li.add("28063|古钱专场<br>10月16日(周二)20：00结标");
		 * li.add("27821|香港站回流钱币工艺品场<br>10月15日(周一)20：00结标");
		 * li.add("28062|机制丝路现代币银锭专场<br>10月15日(周一)20：00结标");
		 * li.add("28101|广州站机制币专场<br>10月14日(周日)20：00结标");
		 * li.add("28121|兰州站机制币银锭专场<br>10月12日(周五)20：00结标");
		 * li.add("27981|藏家个人古钱专场<br>10月11日(周四)21：00结标");
		 */
		// li.add("28061|古钱精品专场<br>10月11日(周四)20：00结标");
		// li.add("28081|奢侈品合作专场<br>10月11日(周四)20：00结标");
		// li.add("28001|湖南站古钱专场<br>10月10日(周三)21：00结标");
		// li.add("27901|藏家个人古钱机制币专场<br>10月10日(周三)20：00结标");
		// li.add("27861|上海站机制币古钱纸币场<br>10月10日(周三)20：00结标");
		// li.add("28041|纸币专场<br>10月9日(周二)20：00结标");
		// li.add("27881|古钱专场<br>10月9日(周二)20：00结标");
		// li.add("27941|机制丝路现代币银锭专场<br>10月8日(周一)20：00结标");
		// li.add("27921|湖北站古钱机制币专场<br>10月8日(周一)21：00结标");
		// li.add("27781|河北站古钱专场<br>10月8日(周一)20：00结标");

		List<String[]> changCiList = new ArrayList<String[]>();
		for (String string : li) {
			String[] x = string.split("\\|");
			String[] changci = new String[3];
			changci[0] = x[1];
			changci[1] = "http://www.huayicang.com/hxgq/xpai/index.jsp?G=" + x[0];
			changci[2] = "";
			changCiList.add(changci);
		}
		return changCiList;
	}

}
