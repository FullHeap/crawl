/**  
 * @Title: ListUtil.java<br>
 * @Package com.coin.crawl.util<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年12月9日<br>
 * @version V1.0
 */
package com.coin.crawl.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: ListUtil<br>
 * @Description: List工具类<br>
 * @author lyzkk<br>
 * @date 2018年12月9日<br>
 *       <br>
 */
public class ListUtil {
	/**
	 * @Title: removeDuplicateWithOrder<br>
	 * @Description: 删除ArrayList中重复元素，保持顺序<br>
	 * @param list
	 *            <br>
	 */
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}

	/**
	 * @Title: removeDuplicate<br>
	 * @Description: 直接循环去重<br>
	 * @param list
	 * @return <br>
	 */
	public static List removeDuplicate(List list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		return list;
	}

	/**
	 * @Title: removeDuplicate<br>
	 * @Description: HashSet去重<br>
	 * @param list
	 * @return <br>
	 */
	public static List removeDuplicateHashSet(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/**
	 * @Title: removeDuplicateContains<br>
	 * @Description: Contains去重<br>
	 * @param list
	 * @return <br>
	 */
	public static List removeDuplicateContains(List list) {
		List listTemp = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if (!listTemp.contains(list.get(i))) {
				listTemp.add(list.get(i));
			}
		}
		return listTemp;
	}
}
