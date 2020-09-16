/**  
 * @Title: Goodinfo.java<br>
 * @Package com.etoak.crawl.entity.dao<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月5日<br>
 * @version V1.0
 */ 
package com.etoak.crawl.entity.dao;

import java.sql.*;

import com.etoak.crawl.entity.Good;
import com.etoak.crawl.entity.Image;
import com.etoak.crawl.entity.Matches;
import com.etoak.crawl.util.DbUtil;

/**
 * @ClassName: Goodinfo<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月5日<br>
 *<br>
 */
public class Goodinfo {
	
	public static boolean addGoodinfo(Good good) {
		boolean result = true;
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO coin_goods VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,good.getGoodno   ());
			pstm.setString(2,good.getGname    ());
			pstm.setString(3,good.getPrice    ());
			pstm.setString(4,good.getTimes    ());
			pstm.setString(5,good.getDescp    ());
			pstm.setString(6,good.getPayer    ());
			pstm.setString(7,good.getImgsrc   ());
			pstm.setString(8,good.getMatchesno());
			pstm.setString(9,good.getUrl      ());
			pstm.setString(10,good.getAddress  ());
			pstm.setString(11,good.getNetno  ());
			pstm.setString(12,good.getStprice  ());
			pstm.setString(13,good.getBtype  ());
			pstm.setString(14,good.getStype  ());
			pstm.setString(15,good.getFoundry  ());

			pstm.executeQuery();

			conn.commit();
			conn.setAutoCommit(false);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}

		return result;
	}
	
	
	public static boolean addImageinfo(Image image) {
		boolean result = true;
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO coin_goods_image VALUES (?, ?, ?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,image.getGoodno   ());
			pstm.setString(2,image.getMatchesId    ());
			pstm.setString(3,image.getImageurl    ());

			pstm.executeQuery();

			conn.commit();
			conn.setAutoCommit(false);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}

		return result;
	}
	
	
	public static boolean addMatchesinfo(Matches Matche) {
		boolean result = true;
		Connection conn = DbUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO coin_matches VALUES (?, ?, ?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,Matche.getMid   ());
			pstm.setString(2,Matche.getMname    ());
			pstm.setString(3,Matche.getMnum    ());
			pstm.setString(4,Matche.getMurl    ());
			pstm.executeQuery();

			conn.commit();
			conn.setAutoCommit(false);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(conn, pstm, rs);
		}

		return result;
	}
}
