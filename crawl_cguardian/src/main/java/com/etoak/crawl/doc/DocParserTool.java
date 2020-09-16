package com.etoak.crawl.doc;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DocParserTool {

	/* 通过选择器来选取页面的 */
	public static Elements select(Document doc, String cssSelector) {
		return doc.select(cssSelector);
	}

	/**
	 * @Title: select<br>
	 * @Description: 得到指定元素选择器的元素<br>
	 * @param page
	 * @param cssSelector
	 * @param index
	 * @return <br>
	 */
	public static Element select(Document doc, String cssSelector, int index) {
		Elements eles = select(doc, cssSelector);
		int realIndex = index;
		if (index < 0) {
			realIndex = eles.size() + index;
		}
		return eles.get(realIndex);
	}

	/**
	 * 获取满足选择器的元素中的链接 选择器cssSelector必须定位到具体的超链接 例如我们想抽取id为content的div中的所有超链接，这里
	 * 就要将cssSelector定义为div[id=content] a 放入set 中 防止重复；
	 * 
	 * @param cssSelector
	 * @return
	 */
	public static Set<String> getLinks(Document doc, String cssSelector) {
		Set<String> links = new HashSet<String>();
		Elements es = select(doc, cssSelector);
		Iterator<Element> iterator = es.iterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			if (element.hasAttr("href")) {
				links.add(element.attr("abs:href"));
			} else if (element.hasAttr("src")) {
				links.add(element.attr("abs:src"));
			}
		}
		return links;
	}

	/**
	 * 获取网页中满足指定css选择器的所有元素的指定属性的集合
	 * 例如通过getAttrs("img[src]","abs:src")可获取网页中所有图片的链接
	 * 
	 * @param cssSelector
	 * @param attrName
	 * @return
	 */
	public static ArrayList<String> getAttrs(Document doc, String cssSelector, String attrName) {
		ArrayList<String> result = new ArrayList<String>();
		Elements eles = select(doc, cssSelector);
		for (Element ele : eles) {
			if (ele.hasAttr(attrName)) {
				result.add(ele.attr(attrName));
			}
		}
		return result;
	}

	public static Elements getElementsByClass(Document doc, String cssSelector) {
		Elements es = doc.getElementsByClass(cssSelector);
		return es;
	}

	public static Element getElementsByid(Document doc, String cssSelector) {
		Element es = doc.getElementById(cssSelector);
		return es;
	}
}
