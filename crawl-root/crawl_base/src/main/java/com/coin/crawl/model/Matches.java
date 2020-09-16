/**  
 * @Title: Matches.java<br>
 * @Package com.etoak.crawl.entity<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 * @version V1.0
 */
package com.coin.crawl.model;

/**
 * @ClassName: Matches<br>
 * @Description: 场次<br>
 * @author lyzkk<br>
 * @date 2018年11月2日<br>
 *       <br>
 */
public class Matches {
	private String mid;
	private String mname;
	private String murl;
	private String mnum;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMurl() {
		return murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public String getMnum() {
		return mnum;
	}

	public void setMnum(String mnum) {
		this.mnum = mnum;
	}

}
