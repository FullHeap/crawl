/**  
 * @Title: RedPackage.java<br>
 * @Package com.fxbank.cip.atv.utils<br>
 * @Description: TODO(用一句话描述该文件做什么)<br>
 * @author lyzkk<br>
 * @date 2018年11月5日<br>
 * @version V1.0
 */
package com.coin.crawl.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName: RedPackage<br>
 * @Description: 红包算法<br>
 * @date 2018年11月5日<br>
 *       <br>
 */
public class RedPackage {
	// 签约参数
	public static int bigNumQY = 0;
	public static int smlNumQY = 0;
	/**
	 * @Fields{签约大红包下标}
	 */
	public static List<Integer> bRedPackageIndexQY = null;
	public static List<String> sBigRedPakgDt = null;
	// 绑卡消费参数
	public static int bigNumXF = 0;
	public static int smlNumXF = 0;
	public static List<Integer> bRedPackageIndexXF = null;
	// 提现参数
	public static int bigNumTX = 0;
	public static int smlNumTX = 0;
	public static List<Integer> bRedPackageIndexTX = null;

	public RedPackage() {
	}

	/**
	 * @Title: getRedPackage<br>					
	 * @Description: 获得红包金额<br>
	 * @param modelFlg 活动类型
	 * @return
	 * @throws Exception
	 *             <br>
	 */
	public static String getRedPackage(Map<String, Object> param) throws Exception {
		RedPackage redPackage = new RedPackage();
		String redPackageAmt = "0.00";
		String modelFlg = param.get("modelFlg").toString();
		if ("QY".equals(modelFlg)) {
			redPackageAmt = redPackage.calculateRedPackageQY(param).toString();
		} else if ("XF".equals(modelFlg)) {
			redPackageAmt = redPackage.calculateRedPackageXF(param).toString();
		} else if ("TX".equals(modelFlg)) {
			redPackageAmt = redPackage.calculateRedPackageTX(param).toString();
		} else {
			throw new Exception("modelFlg：参数值非法！");
		}
		return redPackageAmt;
	}

	/**
	 * @Title: calculateRedPackageQY<br>
	 * @Description: 计算红包金额（签约）<br>
	 * @param param
	 * @return <br>
	 */
	private BigDecimal calculateRedPackageQY(Map<String, Object> param) {
		setRedPakgTpQY(param);
		String type = param.get("redPackageTp").toString();
		
		//获得浮动值k
		BigDecimal secOfk = new BigDecimal(param.get("secOfk").toString());
		BigDecimal k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		//当取得随机数大于k的上限时重新生成
		while (secOfk.compareTo(k) < 0) {
			k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
//		System.out.println("k:" + k);
		
		//获得红包金额
		BigDecimal redPackage = new BigDecimal(0);
		if (type.equals("B") || type.equals("SBG")) {
			redPackage = getBigAmtQY(param, k);
			++bigNumQY;
//			System.out.println("bigRedPakg");
		} else {
			redPackage = getSmlAmtQY(param, k);
			++smlNumQY;
//			System.out.println("smlRedPakg");
		}
//		System.out.println("已发大红包:" + bigNumQY);
//		System.out.println("已发小红包:" + smlNumQY);
		return redPackage;
	}

	/**
	 * @Title: getSmlAmtQY<br>
	 * @Description: 获得签约小红包金额<br>
	 * @param smRedPackage 小红包标准金额
	 * @param totalAmt 总金额
	 * @param bfSumAmt 已支付总金额 
	 * @param sParam1 小红包计算系数1
	 * @param sParam2 小红包计算系数2
	 * @param hours 当前时间
	 * @param bfNumOfPeople 已支付人数
	 * @return BigDecimal<br>
	 */
	private BigDecimal getSmlAmtQY(Map<String, Object> param, BigDecimal k) {
		BigDecimal smlRedPakg = new BigDecimal(param.get("smRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		
		BigDecimal smlAmt = smlRedPakg;

		/*暂不带时间系数改变*/
		/*String param1 = param.get("sParam1").toString();
		String param2 = param.get("sParam2").toString();
		int hours = Integer.parseInt(param.get("hours").toString());
		int bfNumOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		if (bfNumOfPeople != 0 && hours != 0) {
			BigDecimal amt1 = new BigDecimal(param2).multiply(totalAmt.subtract(bfSumAmt));
			BigDecimal amt2 = new BigDecimal((24 - hours) * bfNumOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param1));
			smlAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}*/

		if (smlAmt.compareTo(smlRedPakg) > 0) {
			smlAmt = smlRedPakg;
		}
		
		Random random = new Random();
		int flg = random.nextInt(2);
		if (flg == 0) {
			smlAmt = smlAmt.subtract(k);
		} else {
			smlAmt = smlAmt.add(k);
		}

		if (smlAmt.compareTo(new BigDecimal(0)) < 0) {
			smlAmt = smlAmt.add(k);
		}
		if (totalAmt.subtract(bfSumAmt).compareTo(smlAmt) < 0) {
			//smlAmt = totalAmt.subtract(bfSumAmt);
			return new BigDecimal(0);
		}
		return smlAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Title: getBigAmtQY<br>
	 * @Description: 获得签约大红包金额<br>
	 * @param bgRedPackage 大红包标准金额
	 * @param totalAmt 总金额
	 * @param bfSumAmt 已支付总金额 
	 * @param amtOfSbigRedPakg	超级大红包标准金额
	 * @param redPackageTp 红包类型 
	 * @param bParam1 大红包计算系数1
	 * @param bParam2 大红包计算系数2
	 * @param hours 当前时间
	 * @param bfNumOfPeople 已支付人数
	 * @return <br>
	 */
	private BigDecimal getBigAmtQY(Map<String, Object> param, BigDecimal k) {
		BigDecimal bigRedPakg = new BigDecimal(param.get("bgRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		BigDecimal amtOfSbigRedPakg = new BigDecimal(param.get("amtOfSbigRedPakg").toString());
		String sBigFlg = param.get("redPackageTp").toString();
		
		//判断大红包or超大红包
		BigDecimal bigAmt = "SBG".equals(sBigFlg) ? amtOfSbigRedPakg : bigRedPakg;

		/*String param1 = param.get("bParam1").toString();//0.2
		String param2 = param.get("bParam2").toString();//0.1
		int hours = Integer.parseInt(param.get("hours").toString());
		int bfNumOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		if (bfNumOfPeople != 0 && hours != 0 && !"SBG".equals(sBigFlg)) {
			//分子
			BigDecimal amt1 = new BigDecimal(param1).multiply(totalAmt.subtract(bfSumAmt));
			//分母
			BigDecimal amt2 = new BigDecimal((24 - hours) * bfNumOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param2));
			//分子/分母
			bigAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}*/

		//当红包金额大的情况则将其控制为最大红包金额
		if (bigAmt.compareTo(bigRedPakg) > 0 && !"SBG".equals(sBigFlg)) {
			bigAmt = bigRedPakg;
		}
		//随机生成浮动K正负
		Random random = new Random();
		int flg = random.nextInt(2);
		if (flg == 0) {
			bigAmt = bigAmt.subtract(k);
		} else {
			bigAmt = bigAmt.add(k);
		}

		//若减k的值红包金额小于0，则为原值
		if (bigAmt.compareTo(new BigDecimal(0)) < 0) {
			bigAmt = bigAmt.add(k);
		}
		
		//判断剩余金额是否大于大包金额
		if (totalAmt.subtract(bfSumAmt).compareTo(bigAmt) < 0) {
			//若不够发送，则返回 0
//			bigAmt = totalAmt.subtract(bfSumAmt);
			return new BigDecimal(0);
		}

		//返回四舍五入的金额数据
		return bigAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Title: setRedPakgTpQY<br>
	 * @Description: 设置签约红包类型：大红包/小红包<br>
	 * @param NumOfSRedPakg 小红包个数
	 * @param NumOfBRedPakg 大红包个数
	 * @param numOfSbig 超大红包个数
	 * @param dtSecOfSbig 出现日期范围
	 *            <br>
	 */
	private void setRedPakgTpQY(Map<String, Object> param) {
		int numOfSRedPakg = Integer.parseInt(param.get("NumOfSRedPakg").toString());// 小红包个数
		int numOfBRedPakg = Integer.parseInt(param.get("NumOfBRedPakg").toString());// 大红包个数
		int numOfSbig = Integer.parseInt(param.get("numOfSbig").toString());// 超大红包个数
		int dtSecOfSbig = Integer.parseInt(param.get("dtSecOfSbig").toString());// 超大红包出现日期范围
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		String sysDt = format.format(now);
		Random random = new Random();

		if (bRedPackageIndexQY == null) {
			bRedPackageIndexQY = new ArrayList<Integer>(0);
		}

		if (sBigRedPakgDt == null) {
			sBigRedPakgDt = new ArrayList<String>();
		}

		/* 当签约大小红包个数达到指定比例数目，则清零 */
		if ((bigNumQY + smlNumQY) == (numOfSRedPakg + numOfBRedPakg)) {
			bigNumQY = 0;
			smlNumQY = 0;
		}

		/* 清0后计算签约大红包下标位置 */
		if (bigNumQY == 0 && smlNumQY == 0) {
			bRedPackageIndexQY = new ArrayList<Integer>(0);
			int index = 0;
			boolean same = false;
			// 大红包如有多个，则一次性生成其下标位置（10取1）
			while (bRedPackageIndexQY.size() != numOfBRedPakg) {

				// 随机生成大红包所在下标。
				index = random.nextInt(numOfSRedPakg + numOfBRedPakg);
				for (int j = 0, length = bRedPackageIndexQY.size(); j < length; j++) {
					// 不存在下标则直接存入
					if (bRedPackageIndexQY.size() == 0) {
						break;
					}
					// 存在下标则比对是否一致 ，一致则跳过
					else if (index == bRedPackageIndexQY.get(j)) {
						same = true;
					}
				}

				// 下标不为0
				if (!same && index != 0) {
					bRedPackageIndexQY.add(index);
					same = false;
				}
			}
		}

		// 当没有超级大红包下标时生成
		if (sBigRedPakgDt.size() == 0) {
			String dt = "";
			int index = 0;
			boolean same = false;
			//获取日期对象
			Calendar calendar = Calendar.getInstance();
			
			//超级大包下标不够个数的时候生成对应个数下标
			while (sBigRedPakgDt.size() != numOfSbig) {
				calendar.setTime(now);
				//在大包范围内生成随机数
				index = random.nextInt(dtSecOfSbig);
				
				//获得当天起 index后的日期
				calendar.add(Calendar.DAY_OF_MONTH, index);
				dt = format.format(calendar.getTime());
				//超级大包日期
				for (int j = 0, length = sBigRedPakgDt.size(); j < length; j++) {
					if (sBigRedPakgDt.size() == 0) {
						break;
					} else if (dt.equals(sBigRedPakgDt.get(j))) {
						same = true;
					}
				}
				//跳过重复下标
				if (!same) {
					sBigRedPakgDt.add(dt);
				}
				same = false;
			}
		}

		boolean bigRegPakgFlg = false;
		boolean sbigRegPakgFlg = false;

		// 若下标中有值，则判断该红包是否为大红包
		for (Integer index : bRedPackageIndexQY) {
			if ((index - 1) == (bigNumQY + smlNumQY)) {
				bigRegPakgFlg = true;
				break;
			}
		}

		//若大包日期中有值，则判断系统日期是否为大包中的日期
		for (String dt : sBigRedPakgDt) {
			if (sysDt.equals(dt)) {
				sbigRegPakgFlg = true;
				break;
			}
		}

		
		if (bigRegPakgFlg || sbigRegPakgFlg) {
			//若该红包为大红包，则返回红包类型为大红包，将队列中的对应下标删除
			if (bigRegPakgFlg) {
				param.put("redPackageTp", "B");
				if (bRedPackageIndexQY.size() > 0) {
					bRedPackageIndexQY.remove(new Integer(bigNumQY + smlNumQY + 1));
				}
			}

			//将对应超级大包队列中当前日期给删除
			if (sbigRegPakgFlg) {
				param.put("redPackageTp", "SBG");
				if (sbigRegPakgFlg) {
					// bRedPackageIndexQY.remove(0);
					sBigRedPakgDt.remove(sysDt);
				}
				//超级大包占大包的第一个空位
				if (bRedPackageIndexQY.size() > 0) {
					bRedPackageIndexQY.remove(0);
				}
			}
		} else {
			param.put("redPackageTp", "S");
		}
	}

	/**
	 * 计算红包金额（消费）
	 * 
	 * @param param
	 * @return
	 */
	private BigDecimal calculateRedPackageXF(Map<String, Object> param) {
		setRedPakgTpXF(param);
		String type = param.get("redPackageTp").toString();
		BigDecimal secOfk = new BigDecimal(param.get("secOfk").toString());
		BigDecimal k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		while (secOfk.compareTo(k) < 0) {
			k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
//		System.out.println("k:" + k);

		BigDecimal redPackage = new BigDecimal(0);
		if (type.equals("B")) {
			redPackage = getBigAmtXF(param, k);
			++bigNumXF;
//			System.out.println("bigRedPakg");
		} else {
			redPackage = getSmlAmtXF(param, k);
			++smlNumXF;
//			System.out.println("smlRedPakg");
		}
		return redPackage;
	}

	/**
	 * @Title: getSmlAmtXF<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br>
	 * @param param
	 * @param k
	 * @return <br>
	 */
	private BigDecimal getSmlAmtXF(Map<String, Object> param, BigDecimal k) {
		BigDecimal smlRedPakg = new BigDecimal(param.get("smRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		String param1 = param.get("sParam1").toString();
		String param2 = param.get("sParam2").toString();
		int hours = Integer.parseInt(param.get("hours").toString());
		int numOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		Random random = new Random();
		int flg = random.nextInt(2);
		BigDecimal smlAmt = smlRedPakg;
		if (numOfPeople != 0 && hours != 0) {
			BigDecimal amt1 = new BigDecimal(param2).multiply(totalAmt.subtract(bfSumAmt));
			BigDecimal amt2 = new BigDecimal((24 - hours) * numOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param1));
			smlAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}

		if (smlAmt.compareTo(smlRedPakg) > 0) {
			smlAmt = smlRedPakg;
		}
		if (flg == 0) {
			smlAmt = smlAmt.subtract(k);
		} else {
			smlAmt = smlAmt.add(k);
		}

		if (smlAmt.compareTo(new BigDecimal(0)) < 0) {
			smlAmt = smlAmt.add(k);
		}
		if (totalAmt.subtract(bfSumAmt).compareTo(smlAmt) < 0) {
			smlAmt = totalAmt.subtract(bfSumAmt);
		}

		return smlAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Title: getBigAmtXF<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br>
	 * @param param
	 * @param k
	 * @return <br>
	 */
	private BigDecimal getBigAmtXF(Map<String, Object> param, BigDecimal k) {
		BigDecimal bigRedPakg = new BigDecimal(param.get("bgRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		String param1 = param.get("bParam1").toString();
		String param2 = param.get("bParam2").toString();
		int hours = Integer.parseInt(param.get("hours").toString());
		int numOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		Random random = new Random();
		int flg = random.nextInt(2);
		BigDecimal bigAmt = bigRedPakg;
		if (numOfPeople != 0 && hours != 0) {
			BigDecimal amt1 = new BigDecimal(param1).multiply(totalAmt.subtract(bfSumAmt));
			BigDecimal amt2 = new BigDecimal((24 - hours) * numOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param2));
			bigAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}

		if (bigAmt.compareTo(bigRedPakg) > 0) {
			bigAmt = bigRedPakg;
		}
		if (flg == 0) {
			bigAmt = bigAmt.subtract(k);
		} else {
			bigAmt = bigAmt.add(k);
		}

		if (bigAmt.compareTo(new BigDecimal(0)) < 0) {
			bigAmt = bigAmt.add(k);
		}
		if (totalAmt.subtract(bfSumAmt).compareTo(bigAmt) < 0) {
			bigAmt = totalAmt.subtract(bfSumAmt);
		}
		return bigAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Title: setRedPakgTpXF<br>
	 * @Description: TODO(这里用一句话描述这个方法的作用)<br>
	 * @param param <br>
	 */
	private void setRedPakgTpXF(Map<String, Object> param) {
		int numOfSRedPakg = Integer.parseInt(param.get("NumOfSRedPakg").toString());
		int numOfBRedPakg = Integer.parseInt(param.get("NumOfBRedPakg").toString());
		Random random = new Random();

		if (bRedPackageIndexXF == null) {
			bRedPackageIndexXF = new ArrayList<Integer>(0);
		}
		System.out.println("bigNum:" + bigNumXF);
		System.out.println("smlNum:" + smlNumXF);
		System.out.println("numOfSRedPakg + numOfBRedPakg:" + (numOfSRedPakg + numOfBRedPakg));
		if ((bigNumXF + smlNumXF) == (numOfSRedPakg + numOfBRedPakg)) {
			System.out.println("bigNum:" + bigNumXF);
			System.out.println("smlNum:" + smlNumXF);
			bigNumXF = 0;
			smlNumXF = 0;
		}
		if (bigNumXF == 0 && smlNumXF == 0) {
			bRedPackageIndexXF = new ArrayList<Integer>(0);
			int index = 0;
			boolean same = false;
			while (bRedPackageIndexXF.size() != numOfBRedPakg) {
				index = random.nextInt(numOfSRedPakg + numOfBRedPakg);
				for (int j = 0, length = bRedPackageIndexXF.size(); j < length; j++) {
					if (bRedPackageIndexXF.size() == 0) {
						break;
					} else if (index == bRedPackageIndexXF.get(j)) {
						same = true;
					}
				}
				if (!same && index != 0) {
					bRedPackageIndexXF.add(index);
				}
			}
		}

		boolean bigRegPakgFlg = false;

		for (Integer index : bRedPackageIndexXF) {
			if ((index - 1) == (bigNumXF + smlNumXF)) {
				bigRegPakgFlg = true;
				break;
			}
		}

		if (bigRegPakgFlg) {
			param.put("redPackageTp", "B");
			if (bRedPackageIndexXF.size() > 0) {
				bRedPackageIndexXF.remove(new Integer(bigNumXF + smlNumXF + 1));
			}
		} else {
			param.put("redPackageTp", "S");
		}
	}

	/**
	 * @Title: calculateRedPackageTX<br>
	 * @Description: 计算红包金额（提现）<br>
	 * @param param
	 * @return <br>
	 */
	private BigDecimal calculateRedPackageTX(Map<String, Object> param) {
		setRedPakgTpTX(param);
		String type = param.get("redPackageTp").toString();
		BigDecimal secOfk = new BigDecimal(param.get("secOfk").toString());
		BigDecimal k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		while (secOfk.compareTo(k) < 0) {
			k = new BigDecimal(Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		System.out.println("k:" + k);
		BigDecimal redPackage = new BigDecimal(0);
		if (type.equals("B")) {
			redPackage = getBigAmtTX(param, k);
			++bigNumTX;
			System.out.println("bigRedPakg");
		} else {
			redPackage = getSmlAmtTX(param, k);
			++smlNumTX;
			System.out.println("smlRedPakg");
		}
		return redPackage;
	}

	/**
	 * @Title: getSmlAmtTX<br>
	 * @Description: 获取小红包金额（提现）<br>
	 * @param param
	 * @param k
	 * @return <br>
	 */
	private BigDecimal getSmlAmtTX(Map<String, Object> param, BigDecimal k) {
		BigDecimal smlRedPakg = new BigDecimal(param.get("smRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		String param1 = param.get("sParam1").toString();
		String param2 = param.get("sParam2").toString();
		int hours = Integer.parseInt(param.get("hours").toString());
		int numOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		Random random = new Random();
		int flg = random.nextInt(2);
		BigDecimal smlAmt = smlRedPakg;
		if (numOfPeople != 0 && hours != 0) {
			BigDecimal amt1 = new BigDecimal(param2).multiply(totalAmt.subtract(bfSumAmt));
			BigDecimal amt2 = new BigDecimal((24 - hours) * numOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param1));
			smlAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}

		if (smlAmt.compareTo(smlRedPakg) > 0) {
			smlAmt = smlRedPakg;
		}
		if (flg == 0) {
			smlAmt = smlAmt.subtract(k);
		} else {
			smlAmt = smlAmt.add(k);
		}

		if (smlAmt.compareTo(new BigDecimal(0)) < 0) {
			smlAmt = smlAmt.add(k);
		}
		if (totalAmt.subtract(bfSumAmt).compareTo(smlAmt) < 0) {
			smlAmt = totalAmt.subtract(bfSumAmt);
		}
		return smlAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 获取大红包金额（提现）
	 * 
	 * @param totalAmt
	 * @param bfSumAmt
	 * @param numOfPeople
	 * @param k
	 * @param hours
	 * @param param1
	 * @param param2
	 * @param bigRedPakg
	 * @return
	 */
	private BigDecimal getBigAmtTX(Map<String, Object> param, BigDecimal k) {
		BigDecimal bigRedPakg = new BigDecimal(param.get("bgRedPackage").toString());
		BigDecimal totalAmt = new BigDecimal(param.get("totalAmt").toString());
		BigDecimal bfSumAmt = new BigDecimal(param.get("bfSumAmt").toString());
		String param1 = param.get("bParam1").toString();
		String param2 = param.get("bParam2").toString();
		int hours = Integer.parseInt(param.get("hours").toString());
		int numOfPeople = Integer.parseInt(param.get("bfNumOfPeople").toString());
		Random random = new Random();
		int flg = random.nextInt(2);
		BigDecimal bigAmt = bigRedPakg;
		if (numOfPeople != 0 && hours != 0) {
			BigDecimal amt1 = new BigDecimal(param1).multiply(totalAmt.subtract(bfSumAmt));
			BigDecimal amt2 = new BigDecimal((24 - hours) * numOfPeople)
					.divide(new BigDecimal(hours), 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(param2));
			bigAmt = amt1.divide(amt2, 6, BigDecimal.ROUND_DOWN);
		}

		if (bigAmt.compareTo(bigRedPakg) > 0) {
			bigAmt = bigRedPakg;
		}
		if (flg == 0) {
			bigAmt = bigAmt.subtract(k);
		} else {
			bigAmt = bigAmt.add(k);
		}

		if (bigAmt.compareTo(new BigDecimal(0)) < 0) {
			bigAmt = bigAmt.add(k);
		}
		if (totalAmt.subtract(bfSumAmt).compareTo(bigAmt) < 0) {
			bigAmt = totalAmt.subtract(bfSumAmt);
		}
		return bigAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 获取大小红包标识（提现）
	 * 
	 * @param param
	 */
	private void setRedPakgTpTX(Map<String, Object> param) {
		int numOfSRedPakg = Integer.parseInt(param.get("NumOfSRedPakg").toString());
		int numOfBRedPakg = Integer.parseInt(param.get("NumOfBRedPakg").toString());
		Random random = new Random();

		if (bRedPackageIndexTX == null) {
			bRedPackageIndexTX = new ArrayList<Integer>(0);
		}
		System.out.println("bigNum:" + bigNumTX);
		System.out.println("smlNum:" + smlNumTX);
		System.out.println("numOfSRedPakg + numOfBRedPakg:" + (numOfSRedPakg + numOfBRedPakg));
		if ((bigNumTX + smlNumTX) == (numOfSRedPakg + numOfBRedPakg)) {
			System.out.println("bigNum:" + bigNumTX);
			System.out.println("smlNum:" + smlNumTX);
			bigNumTX = 0;
			smlNumTX = 0;
		}
		if (bigNumTX == 0 && smlNumTX == 0) {
			bRedPackageIndexTX = new ArrayList<Integer>(0);
			int index = 0;
			boolean same = false;
			while (bRedPackageIndexTX.size() != numOfBRedPakg) {
				index = random.nextInt(numOfSRedPakg + numOfBRedPakg);
				for (int j = 0, length = bRedPackageIndexTX.size(); j < length; j++) {
					if (bRedPackageIndexTX.size() == 0) {
						break;
					} else if (index == bRedPackageIndexTX.get(j)) {
						same = true;
					}
				}
				if (!same && index != 0) {
					bRedPackageIndexTX.add(index);
				}
			}
		}

		boolean bigRegPakgFlg = false;

		for (Integer index : bRedPackageIndexTX) {
			if ((index - 1) == (bigNumTX + smlNumTX)) {
				bigRegPakgFlg = true;
				break;
			}
		}

		if (bigRegPakgFlg) {
			param.put("redPackageTp", "B");
			if (bRedPackageIndexTX.size() > 0) {
				bRedPackageIndexTX.remove(new Integer(bigNumTX + smlNumTX + 1));
			}
		} else {
			param.put("redPackageTp", "S");
		}
	}

	
}
