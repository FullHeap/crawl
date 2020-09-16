/**  
 * @Title: Test.java<br>
 * @Package crawl_huayicang<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年12月20日<br>
 * @version V1.0
 */ 
package crawl_huayicang;

import com.etoak.crawl.page.Page;
import com.etoak.crawl.page.RequestAndResponseTool;

/**
 * @ClassName: Test<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年12月20日<br>
 *<br>
 */
public class Test {
	
	public static void main(String[] args) {
		Page pageMatches = RequestAndResponseTool.sendRequstAndGetResponse(
				"http://www.huayicang.com/hxgq/xpai/index.jsp?P=2&G=29801");
		System.out.println("场次URL："+pageMatches.getUrl());
		System.out.println(pageMatches.getHtml());
	}


}
