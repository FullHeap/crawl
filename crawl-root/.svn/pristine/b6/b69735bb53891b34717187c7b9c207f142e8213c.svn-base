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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.coin.crawl.link.LinkFilter;
import com.coin.crawl.link.Links;
import com.coin.crawl.model.Good;
import com.coin.crawl.model.Image;
import com.coin.crawl.model.Matches;
import com.coin.crawl.model.dao.Goodinfo;
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
				} else if (url.startsWith("http://www.polypm.com.cn/assest/special")) {
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
		Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

		
//		List<String[]> assestList = PageElement.getAssest(page);
		
		
		List<String[]> changciList = PageElement.getChangci(page);
		for (String[] strings : changciList) {
			if (filter.accept(strings[1])) {
				// Links.addUnvisitedUrlQueue(strings[1]);
				System.out.println("新增场次路径: " + strings[1]);
				System.out.println("场次编号为：" + strings[1].substring(strings[1].indexOf("=") + 1));
				System.out.println("场次名称为：" + strings[0]);
				System.out.println("场次数量为：" + strings[2]);

				String matchNO = strings[1].substring(strings[1].indexOf("=") + 1);
				Page pageMatches = RequestAndResponseTool.sendRequstAndGetResponse(strings[1]);
				FileTool.saveChangci(matchNO);

				Matches matches = new Matches();
				matches.setMid(matchNO);
				matches.setMname(strings[0]);
				matches.setMnum(strings[2]);
				matches.setMurl(strings[1]);
				Goodinfo.addMatchesinfo(matches);

				Elements elp = pageMatches.getDoc().getElementsByClass("pages");

				// 判断是否有分页
				String pages = elp.get(0).text();
				String pageno = pages.substring(pages.lastIndexOf("共") + 1, pages.lastIndexOf("页")).trim();
				System.out.println("pages:" + pageno);
				if (Integer.parseInt(pageno) >= 1) {
					// 循环遍历 每一页
					for (int pageindex = 1; pageindex <= Integer.parseInt(pageno); pageindex++) {
						// http://www.huayicang.com/hxgq/xpai/index.jsp?P=1&G=28601
						pageMatches = RequestAndResponseTool.sendRequstAndGetResponse(
								"http://www.huayicang.com/hxgq/xpai/index.jsp?p=" + pageindex + "&G=" + matchNO);

						// 取制定URL的场次
						if (pageMatches.getUrl().indexOf("http://www.huayicang.com/hxgq/xpai/index.jsp") != -1) {
							List<String> goodList = PageElement.getGood(pageMatches);
							for (String string : goodList) {
								if (filter.accept(string)) {

									// Links.addUnvisitedUrlQueue(string);
									Page pagegood = RequestAndResponseTool.sendRequstAndGetResponse(string);
									System.out.println("爬取商品路径: " + string);

									// 保存商品信息
									Good good = saveGoodInfo(matchNO, string, pagegood);

									// 保存图片信息
									Set<String> links = getBigImage(pagegood);
									int i = 1;
									for (String link : links) {
										// System.out.println("全部图片链接：" + link);
										if (filter.accept(link)) {
											Image image = new Image();
											if (i == 1) {
												good.setImgsrc(link);
											}
											// Links.addUnvisitedUrlQueue(link);
											System.out.println("商品图片：" + link);
											// System.out.println("filepath"+FileTool.dirPath+"\\"+matchNO+"\\"+string.substring(string.lastIndexOf("/")+1)+link.substring(link.lastIndexOf("/")+1));
											// FileTool.saveImage(link,
											// FileTool.dirPath+"\\"+matchNO+"\\"+link.substring(link.lastIndexOf("/")+1));
											image.setGoodno(goodNo);
											image.setImageurl(link);
											image.setMatchesId(matchNO);
											Goodinfo.addImageinfo(image);
											FileTool.saveImage(link, FileTool.dirPath + "\\" + matchNO + "\\" + goodNo
													+ "_" + i + ".jpg");
											i = i + 1;
											// goodNo =
											// String.valueOf((Integer.parseInt(goodNo)+1));
										}
										// 0 代表前面补充0
										// 4 代表长度为4
										// d 代表参数为正数型
									}
									goodNo = String.format("%08d", (Integer.parseInt(goodNo) + 1));
									Goodinfo.addGoodinfo(good);
								}
							}
						}

					}
				}

			}
			System.out.println("场次:" + strings[0] + "\t链接：" + strings[1] + "\t数量：" + strings[2] + "\n");
		}
	}

	/**
	 * @Title: saveGoodInfo<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br>
	 * @param matchNO
	 * @param url
	 * @param pagegood
	 * @return <br>
	 */
	public Good saveGoodInfo(String matchNO, String url, Page pagegood) {
		Good good = new Good();
		good.setGoodno(goodNo);
		good.setUrl(url);
		good.setMatchesno(matchNO);

		Element elsno = pagegood.getDoc().getElementsByClass("pu").get(0);
		// 商品名称
		Element els = pagegood.getDoc().getElementsByClass("name").get(0);
		// 所在地
		Element eladd = pagegood.getDoc().getElementsByClass("box_01_left").get(0);
		// 当前价格
		Element elcurPrice = pagegood.getDoc().getElementById("_CurPrice");
		// 成交价格
		Elements elcdelPrice = pagegood.getDoc().getElementsByClass("money");
		// 起拍价格，加价
		Element elcstartPrice = pagegood.getDoc().getElementsByClass("pu_price").get(0);
		Elements elcsprice = elcstartPrice.getElementsByTag("font");
		// 描述
		Elements eldesc = pagegood.getDoc().getElementsByClass("miaoshu_attention");

		// for (Element element : els) {
		good.setGname(els.text().substring(els.text().indexOf("：") + 2, els.text().indexOf("[") - 3));
		good.setPrice(elcurPrice.text());
		good.setAddress(eladd.getElementsByTag("div").get(0).text());
		good.setNetno(elsno.getElementsByTag("font").get(0).text());
		good.setPayer(elcdelPrice.get(0).text());
		good.setStprice(elcstartPrice.text().substring(0, elcstartPrice.text().indexOf("(") - 1));

		System.out.println("商品编号：" + elsno.getElementsByTag("font").get(0).text());
		// 名称 官方委托： 1-安阳折二（华夏评级-上美品(VF)75）     [收藏]
		System.out.println("商品名称：" + els.text().substring(els.text().indexOf("：") + 2, els.text().indexOf("[") - 3));
		System.out.println("当前价格：" + elcurPrice.text());
		System.out.println("成交人：" + elcdelPrice.get(0).text());
		System.out.println("成交价格：" + elcdelPrice.get(1).text());
		// 起始价 ￥100， 最小加价幅度:￥20   (请仔细阅读新规则！)
		System.out.println("起拍价格，加价：" + elcstartPrice.text().substring(0, elcstartPrice.text().indexOf("(") - 1));
		System.out.println("起拍价：" + elcsprice.get(0).text() + " 加价：" + elcsprice.get(1).text());
		// }

		for (Element element1 : eldesc) {
			good.setDescp(element1.text().substring(element1.text().indexOf("%") + 1));
			// 适用最新规则，邮寄费到付，点击浏览详情。 参拍请注意：本项拍品竞买方手续费率：13% 56mm 可视瑕疵
			System.out.println("商品描述：" + element1.text().substring(element1.text().indexOf("%") + 1));
		}

		return good;
	}

	/**
	 * @Title: getBigImage<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br>
	 * @param pagegood
	 * @return <br>
	 */
	public Set<String> getBigImage(Page pagegood) {
		// 示例图片链接
		Set<String> links = PageParserTool.getLinks(pagegood, "img");

		// 取得script中的大图链接
		Elements elScripts = pagegood.getDoc().getElementsByTag("script");
		for (Element element : elScripts) {
			String[] elScriptLists = element.data().toString().split("var");
			for (String string2 : elScriptLists) {
				if (string2.contains("Array")) {
					String x = string2.substring(string2.indexOf("\"") + 1, string2.lastIndexOf("\""));
					String[] srcArray = x.split("\",\"");
					for (String string3 : srcArray) {
						System.out.println(string3);
						links.add("http://imgali.hxgqw.com/pic/" + string3);
					}
				}
			}
		}
		return links;
	}

	// main 方法入口
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		// crawler.crawling(new String[] {
		// "http://www.huayicang.com/hxgq/xpai/goods_cc-18-1102-26.html" });
//		crawler.crawling(new String[] { "http://www.polypm.com.cn/assest/other/0/4" });
			crawler.crawling(new String[] { "http://www.polypm.com.cn/assest/other/3/37" });

	}

}
