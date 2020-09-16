/**  
 * @Title: ToolUtil.java<br>
 * @Package com.etoak.crawl.util<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月15日<br>
 * @version V1.0
 */ 
package com.etoak.crawl.util;

/**
 * @ClassName: ToolUtil<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月15日<br>
 *<br>
 */
public class ToolUtil {
	public static String getFoundry(String gname){
		String[] typestr = new String[]{
		"宝泉",    
		"宝源",    
		"宝苏",    
		"宝浙",    
		"宝福",    
		"宝武",    
		"宝川",    
		"宝陕",    
		"宝巩",    
		"宝河",    
		"宝直",    
		"宝蓟",    
		"宝德",    
		"宝济",    
		"宝晋",    
		"宝昌",    
		"宝安",    
		"宝台",    
		"宝南",    
		"宝广",    
		"宝桂",    
		"宝云",    
		"宝东",    
		"宝州",    
		"宝黔",    
		"宝迪",    
		"宝伊",    
		"阿克苏"  ,
		"喀什格尔",
		"叶尔羌",  
		"库车"};    
		for (String string : typestr) {
			if (gname.indexOf(string) != -1) {
				return string+"局";
			}
		}
		return "";

	}
}
