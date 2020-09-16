/**  
* @Title: Test.java<br>
 * @Package com.coin.crawl.test<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月8日<br>
 * @version V1.0
 */ 
package com.coin.crawl.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.coin.crawl.util.RedPackage;

/**
 * @ClassName: Test<br>
 * @Description: TODO(这里用一句话描述这个类的作用)<br>
 * @author lyzkk<br>
 * @date 2018年11月8日<br>
 *<br>
 */
public class Test {
	/**
	 * 微信红包测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String hours = format.format(new Date()).substring(8, 10);

		// 签约红包测试
		int bfNumOfPeople = 0;
		int nowNumOfpeople = 0;
		BigDecimal bfSumAmt = new BigDecimal(0);
		param.put("modelFlg", "QY");
		param.put("bgRedPackage", "18.8");// 大红包金额标准
		param.put("smRedPackage", "4.5");// 小红包金额标准
		param.put("NumOfSRedPakg", "9");// 小红包个数占比(9/(9+1))
		param.put("NumOfBRedPakg", "1");// 大红包个数占比(1/(9+1))
		param.put("totalAmt", "1000.00");// 红包总金额预算
		param.put("numOfSbig", "10");// 超大红包个数
		param.put("dtSecOfSbig", "20");// 超大红包日期范围
		param.put("amtOfSbigRedPakg", "188.00");// 超大红包金额
		param.put("hours", hours);// 当前时段
		param.put("bfSumAmt", bfSumAmt); //已支付金额
		param.put("bfNumOfPeople", bfNumOfPeople);//已发放人数

		param.put("sParam1", "0.8");// 小红包金额计算系数
		param.put("sParam2", "0.9");// 小红包金额计算系数
		param.put("bParam1", "0.2");// 大红包金额计算系数
		param.put("bParam2", "0.1");// 大红包金额计算系数
		param.put("secOfk", "0.2");// 红包金额浮动系数

		// 消费红包测试
		// int bfNumOfPeople = 0;
		// int nowNumOfpeople = 0;
		// BigDecimal bfSumAmt = new BigDecimal(0);
		// param.put("modelFlg","XF");
		// param.put("bgRedPackage", "18.8");//大红包金额标准
		// param.put("smRedPackage", "4.5");//小红包金额标准
		// param.put("NumOfSRedPakg", "9");//小红包个数占比(9/(9+1))
		// param.put("NumOfBRedPakg", "1");//大红包个数占比(1/(9+1))
		// param.put("totalAmt", "1000.00");//红包总金额预算
		// param.put("hours", hours);//当前时段
		// param.put("bfSumAmt", bfSumAmt);
		// param.put("bfNumOfPeople", bfNumOfPeople);
		//
		// param.put("sParam1", "0.8");//小红包金额计算系数
		// param.put("sParam2", "0.9");//小红包金额计算系数
		// param.put("bParam1", "0.2");//大红包金额计算系数
		// param.put("bParam2", "0.1");//大红包金额计算系数
		// param.put("secOfk", "0.2");//红包金额浮动系数

		// 提现红包测试
		// int bfNumOfPeople = 0;
		// int nowNumOfpeople = 0;
		// BigDecimal bfSumAmt = new BigDecimal(0);
		// param.put("modelFlg","TX");
		// param.put("bgRedPackage", "18.8");//大红包金额标准
		// param.put("smRedPackage", "4.5");//小红包金额标准
		// param.put("NumOfSRedPakg", "9");//小红包个数占比(9/(9+1))
		// param.put("NumOfBRedPakg", "1");//大红包个数占比(1/(9+1))
		// param.put("totalAmt", "1000.00");//红包总金额预算
		// param.put("hours", hours);//当前时段
		// param.put("bfSumAmt", bfSumAmt);
		// param.put("bfNumOfPeople", bfNumOfPeople);
		//
		// param.put("sParam1", "0.8");//小红包金额计算系数
		// param.put("sParam2", "0.9");//小红包金额计算系数
		// param.put("bParam1", "0.2");//大红包金额计算系数
		// param.put("bParam2", "0.1");//大红包金额计算系数
		// param.put("secOfk", "0.2");//红包金额浮动系数

//		while (!param.get("totalAmt").equals(bfSumAmt.toString())) {
		
		
		while (!param.get("totalAmt").equals(bfSumAmt.toString())) {
			if((new BigDecimal(param.get("totalAmt").toString())).subtract(bfSumAmt).compareTo(new BigDecimal(4.5)) < 0){
				System.out.println(bfSumAmt);
				break;
			}
			String amt = "";
			try {
				amt = RedPackage.getRedPackage(param);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bfSumAmt = bfSumAmt.add(new BigDecimal(amt));
			++nowNumOfpeople;
			param.put("bfSumAmt", bfSumAmt);
//			System.out.println("红包总金额：" + param.get("totalAmt").toString());
//			System.out.println("已发金额：" + bfSumAmt);
//			System.out.println("前时段已发人数：" + bfNumOfPeople);
//			System.out.println("已发总人数：" + (bfNumOfPeople + nowNumOfpeople));
//			System.out.println("当前红包金额：" + amt);
//			System.out.println("当前时段：" + hours);
//			System.out.print("大红包位置：");
//			for (int i = 0, length = bRedPackageIndexQY.size(); i < length; i++) {
//				System.out.print(bRedPackageIndexQY.get(i) + " # ");
//			}
//			System.out.println("");
//			System.out.print("超大红包位置：");
//			for (int i = 0, length = sBigRedPakgDt.size(); i < length; i++) {
//				System.out.print(sBigRedPakgDt.get(i) + " # ");
//			}
//			System.out.println("");
//			System.out.println("---------------------------");
			System.out.println(""+(bfNumOfPeople + nowNumOfpeople)+"\t"+amt);
		}
	}
}
