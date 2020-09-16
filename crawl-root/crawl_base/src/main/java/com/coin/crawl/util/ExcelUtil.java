package com.coin.crawl.util;

import java.io.File;
import java.io.IOException;

import com.coin.crawl.model.Good;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @ClassName: ExcelUtil<br>
 * @Description: excel处理相关操作<br>
 * @author lyzkk<br>
 * @date 2018年11月21日<br>
 *       <br>
 */
public class ExcelUtil {
	
	public static String createExcel(String fileName, String[] title) throws IOException, JXLException {
		// 1:创建excel文件
		File file = new File(fileName + ".xls");
		file.createNewFile();

		// 2:创建工作簿
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		// 3:创建sheet,设置第二三四..个sheet，依次类推即可
		WritableSheet sheet = workbook.createSheet("Result", 0);
		// 4：设置titles
		String[] titles = { "序号    ", "来源    ", "拍卖编号", "时期    ", "大类    ", "小类    ", "细类    ", "名称    ", "铸造局  ",
				"形制    ", "版式    ", "性质    ", "说明    ", "高MM    ", "直径MM  ", "厚度MM  ", "重量G   ", "材质    ", "铸地    ",
				"评级    ", "分数    ", "起拍价  ", "成交价  ", "成交人  ", "成交日期" };

		// 5:单元格
		Label label = null;
		// 6:给第一行设置列名
		for (int i = 0; i < titles.length; i++) {
			// x,y,第一行的列名
			label = new Label(i, 0, titles[i]);
			// 7：添加单元格
			sheet.addCell(label);
		}
		// 8：模拟数据库导入数据
		for (int i = 1; i < 10; i++) {
			// 添加编号，第二行第一列
			label = new Label(0, i, i + "");
			sheet.addCell(label);

			// 添加账号
			label = new Label(1, i, "10010" + i);
			sheet.addCell(label);

			// 添加密码
			label = new Label(2, i, "123456");
			sheet.addCell(label);
		}

		// 写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
		workbook.write();
		// 最后一步，关闭工作簿
		workbook.close();
		return null;
	}
	
	public static String createCrawlExcel(String fileName) throws IOException, JXLException {
		// 1:创建excel文件
		File file = new File(fileName + ".xls");
		file.createNewFile();

		// 2:创建工作簿
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		// 3:创建sheet,设置第二三四..个sheet，依次类推即可
		WritableSheet sheet = workbook.createSheet("Result", 0);
		// 4：设置titles
		String[] titles = { "序号    ", "来源    ", "拍卖编号", "时期    ", "大类    ", "小类    ", "细类    ", "名称    ", "铸造局  ",
				"形制    ", "版式    ", "性质    ", "说明    ", "高MM    ", "直径MM  ", "厚度MM  ", "重量G   ", "材质    ", "铸地    ",
				"评级    ", "分数    ", "起拍价  ", "成交价  ", "成交人  ", "成交日期" };

		// 5:单元格
		Label label = null;
		// 6:给第一行设置列名
		for (int i = 1; i <= titles.length; i++) {
			// x,y,第一行的列名
			label = new Label(i, 0, titles[i-1]);
			// 7：添加单元格
			sheet.addCell(label);
		}
		
		// 8：模拟数据库导入数据


		// 写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
		workbook.write();
		// 最后一步，关闭工作簿
		workbook.close();
		return null;
	}
	
	public static String insertDataHX(String fileName,Good goods,int index) throws Exception{
		File file = new File(fileName + ".xls");
		Workbook workbook = Workbook.getWorkbook(file);
		WritableWorkbook workbook1 = Workbook.createWorkbook(file,workbook);
		// 3:创建sheet,设置第二三四..个sheet，依次类推即可
		WritableSheet sheet = workbook1.getSheet(0);
		int i = index;
//				ExcelUtil.getExcelLastLine(fileName);
		Label label = null;
		label = new Label(0, i, i + "");
		sheet.addCell(label);

		// 添加序号
		label = new Label(1, i, goods.getGoodno());
		sheet.addCell(label);

		// 添加来源
		label = new Label(2, i, "华夏古泉");
		sheet.addCell(label);
		
		// 添加拍卖编号
		label = new Label(3, i, goods.getNetno());
		sheet.addCell(label);

		// 添加时期
		label = new Label(4, i, "");
		sheet.addCell(label);
		
		// 添加大类
		label = new Label(5, i, "");
		sheet.addCell(label);
		
		// 添加小类
		label = new Label(6, i, "");
		sheet.addCell(label);
		
		// 添加细类
		label = new Label(7, i, "");
		sheet.addCell(label);
		
		// 添加名称
		label = new Label(8, i, goods.getGname());
		sheet.addCell(label);
		
		// 添加铸造局
		label = new Label(9, i, goods.getFoundry());
		sheet.addCell(label);

		// 添加形制
		label = new Label(10, i, "");
		sheet.addCell(label);
		
		// 添加版式
		label = new Label(11, i, "");
		sheet.addCell(label);
		
		// 添加性质
		label = new Label(12, i, "");
		sheet.addCell(label);
		
		// 添加说明
		label = new Label(13, i, goods.getDescp());
		sheet.addCell(label);
		
		// 添加高MM
		label = new Label(14, i, "");
		sheet.addCell(label);
		
		// 添加直径MM
		label = new Label(15, i, "");
		sheet.addCell(label);
		
		// 添加厚度MM
		label = new Label(16, i, "");
		sheet.addCell(label);

		// 添加重量G
		label = new Label(17, i, "");
		sheet.addCell(label);

		// 添加材质
		label = new Label(18, i, "");
		sheet.addCell(label);

		// 添加铸地
		label = new Label(19, i, "");
		sheet.addCell(label);

		// 添加评级
		label = new Label(20, i, "");
		sheet.addCell(label);
		
		// 添加分数
		label = new Label(21, i, "");
		sheet.addCell(label);

		// 添加起拍价
		label = new Label(22, i, goods.getStprice());
		sheet.addCell(label);

		// 添加成交价
		label = new Label(23, i, goods.getPrice());
		sheet.addCell(label);

		// 添加成交人
		label = new Label(24, i, goods.getPayer());
		sheet.addCell(label);
		
		// 添加成交日期
		label = new Label(25, i, goods.getDealdate());
		sheet.addCell(label);	
		
		// 写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
		workbook1.write();
		// 最后一步，关闭工作簿
		workbook1.close();
		
		return null;
	}
	
	/**
	 * @Title: insertDataBL<br>
	 * @Description: 保利拍卖数据<br>
	 * @param fileName
	 * @param goods
	 * @param index
	 * @return
	 * @throws Exception <br>
	 */
	public static String insertDataBL(String fileName,Good goods,int index) throws Exception{
		File file = new File(fileName + ".xls");
		Workbook workbook = Workbook.getWorkbook(file);
		WritableWorkbook workbook1 = Workbook.createWorkbook(file,workbook);
		// 3:创建sheet,设置第二三四..个sheet，依次类推即可
		WritableSheet sheet = workbook1.getSheet(0);
		int i = index;
//				ExcelUtil.getExcelLastLine(fileName);
		Label label = null;
		label = new Label(0, i, i + "");
		sheet.addCell(label);

		// 添加序号
		label = new Label(1, i, goods.getGoodno());
		sheet.addCell(label);

		// 添加来源
		label = new Label(2, i, "保利拍卖");
		sheet.addCell(label);
		
		// 添加拍卖编号
		label = new Label(3, i, goods.getNetno());
		sheet.addCell(label);

		// 添加时期
		label = new Label(4, i, "");
		sheet.addCell(label);
		
		// 添加大类
		label = new Label(5, i, "");
		sheet.addCell(label);
		
		// 添加小类
		label = new Label(6, i, "");
		sheet.addCell(label);
		
		// 添加细类
		label = new Label(7, i, "");
		sheet.addCell(label);
		
		// 添加名称
		label = new Label(8, i, goods.getGname());
		sheet.addCell(label);
		
		// 添加铸造局
		label = new Label(9, i, goods.getFoundry());
		sheet.addCell(label);

		// 添加形制
		label = new Label(10, i, "");
		sheet.addCell(label);
		
		// 添加版式
		label = new Label(11, i, "");
		sheet.addCell(label);
		
		// 添加性质
		label = new Label(12, i, "");
		sheet.addCell(label);
		
		// 添加说明
		label = new Label(13, i, goods.getDescp());
		sheet.addCell(label);
		
		// 添加高MM
		label = new Label(14, i, "");
		sheet.addCell(label);
		
		// 添加直径MM
		label = new Label(15, i, "");
		sheet.addCell(label);
		
		// 添加厚度MM
		label = new Label(16, i, "");
		sheet.addCell(label);

		// 添加重量G
		label = new Label(17, i, "");
		sheet.addCell(label);

		// 添加材质
		label = new Label(18, i, "");
		sheet.addCell(label);

		// 添加铸地
		label = new Label(19, i, "");
		sheet.addCell(label);

		// 添加评级
		label = new Label(20, i, "");
		sheet.addCell(label);
		
		// 添加分数
		label = new Label(21, i, "");
		sheet.addCell(label);

		// 添加起拍价
		label = new Label(22, i, goods.getStprice());
		sheet.addCell(label);

		// 添加成交价
		label = new Label(23, i, goods.getPrice());
		sheet.addCell(label);

		// 添加成交人
		label = new Label(24, i, goods.getPayer());
		sheet.addCell(label);
		
		// 添加成交日期
		label = new Label(25, i, goods.getDealdate());
		sheet.addCell(label);	
		
		// 写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
		workbook1.write();
		// 最后一步，关闭工作簿
		workbook1.close();
		
		return null;
	}
	
	

	public static String readExcel() throws JXLException, IOException {
		// 1:创建workbook
		Workbook workbook = Workbook.getWorkbook(new File("test.xls"));
		// 2:获取第一个工作表sheet
		Sheet sheet = workbook.getSheet(0);
		// 3:获取数据
		System.out.println("行：" + sheet.getRows());
		System.out.println("列：" + sheet.getColumns());
		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				Cell cell = sheet.getCell(j, i);
				System.out.print(cell.getContents() + " ");
			}
			System.out.println();
		}

		// 最后一步：关闭资源
		workbook.close();
		return null;
	}
	
	public static int getExcelLastLine(String fileName) throws JXLException, IOException {
		// 1:创建workbook
		Workbook workbook = Workbook.getWorkbook(new File(fileName+".xls"));
		// 2:获取第一个工作表sheet
		Sheet sheet = workbook.getSheet(0);
		// 3:获取数据
		int i = sheet.getRows();
		// 最后一步：关闭资源
		workbook.close();
		return i;
	}


	public static void main(String[] args) {
		try {
			for(int i=0;i<10;i++){
				Good goods = new Good();
				goods.setFoundry("111");
				goods.setNetno("2222");
				if(i==9){
				goods.setPayer("3333");
				}
				// ExcelUtil.createExcel("测试xxx" + ".xls", args);
				ExcelUtil.createCrawlExcel("test");
				int x = ExcelUtil.getExcelLastLine("test");
				ExcelUtil.insertDataHX("test", goods, x);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JXLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
