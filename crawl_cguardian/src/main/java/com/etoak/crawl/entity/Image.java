/**  
 * @Title: Image.java<br>
 * @Package com.etoak.crawl.entity<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月5日<br>
 * @version V1.0
 */
package com.etoak.crawl.entity;

/**
 * @ClassName: Image<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月5日<br>
 *       <br>
 */
public class Image {
	private String goodno;
	private String matchesId;
	private String imageurl;

	public String getGoodno() {
		return goodno;
	}

	public void setGoodno(String goodno) {
		this.goodno = goodno;
	}

	public String getMatchesId() {
		return matchesId;
	}

	public void setMatchesId(String matchesId) {
		this.matchesId = matchesId;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

}
