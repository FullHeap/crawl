/**   
 * @Title: MainFrame.java 
 * @Package com.agree.mainFrame 
 * @Description: 主面板
 * @author lyz liaoyinzhen@cfischina.com   
 * @date 2015-12-5 下午12:13:42 
 * @version V1.0   
 */
package com.etoak.crawl.main;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.etoak.crawl.page.Page;
import com.etoak.crawl.page.RequestAndResponseTool;

/**
 * @ClassName: MainFrame
 * @Description: 主面板
 * @author lyz liaoyinzhen@cfischina.com
 * @date 2015-12-5 下午12:13:42
 * 
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame jf = null;
	private JPanel jPanel;
	private JTextField text_excl = new JTextField("", 20);// 场次ID名称，以竖线分割
	private JTextField text_no = new JTextField("", 20);// 序号
	// 创建一个下拉列表框
	private JComboBox<String> comboBox = new JComboBox<String>();

	// 得到屏幕分辨率
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public MainFrame(List<String> list) {
		jf = new JFrame();
		jf.setVisible(true);
		jf.setTitle("爬虫工具");
		jf.setSize(400, 300);

		jf.setLocation((width - 400) / 2, (height - 300) / 2);
		jf.setResizable(false);// 窗体不可调整
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭时关闭应用程序

		// 创建按钮 选择excl文件的按钮
		jPanel = new JPanel();

		JLabel label1 = new JLabel("      场次ID|名称：");
		JLabel label2 = new JLabel("商品起始序号：");
		JLabel label3 = new JLabel("      场次ID|名称：");
		// JButton input = new JButton("运行");

		JButton submit = new JButton("运行");
		JButton exit = new JButton("退出");

		jPanel.add(label2);
		jPanel.add(text_no);

		// 需要选择的条目
		comboBox.addItem("        ");
		for (String string : list) {
			comboBox.addItem(string);
		}
		comboBox.setSize(100, 20);
		
		

		// // 添加条目选中状态改变的监听器
		// comboBox.addItemListener(new ItemListener() {
		//
		// public void itemStateChanged(ItemEvent e) {
		// // 只处理选中的状态
		// if (e.getStateChange() == ItemEvent.SELECTED) {
		// System.out.println("选中: " + comboBox.getSelectedIndex() + " = " +
		// comboBox.getSelectedItem());
		// }
		// }
		// });

		

		// 设置默认选中的条目
		comboBox.setSelectedIndex(0);

		jPanel.add(label1);
		jPanel.add(text_excl);
		// jPanel.add(input);
		// 添加到内容面板
		jPanel.add(label3);
		jPanel.add(comboBox);

		jPanel.add(submit, BorderLayout.SOUTH);
		jPanel.add(exit, BorderLayout.SOUTH);

		jf.add(jPanel);

		// input.addActionListener(new MouseAction("input"));
		submit.addActionListener(new MouseAction("submit"));
		exit.addActionListener(new MouseAction("exit"));
	}

	class MouseAction implements ActionListener {
		private String onclick = "";

		public MouseAction(String str) {
			onclick = str;
		}

		public void actionPerformed(ActionEvent e) {
			// 文件选择
			if ("input".equals(onclick)) {
			} else if ("output".equals(onclick)) {

			} else if ("submit".equals(onclick)) {
				comboBox.getSelectedItem().toString();
				MatchesCrawler crawler = new MatchesCrawler();
				// crawler.crawling(new String[] {
				// "http://www.huayicang.com/hxgq/xpai/goods_cc-18-1102-26.html"
				// });
				if(!text_excl.getText().equals("")){
					crawler.crawling(new String[] { "http://www.huayicang.com" },
							new String[] { text_excl.getText(), text_no.getText() });
				}else{
					System.out.println(">>>>>>>>>"+comboBox.getSelectedItem().toString());
					String gids = comboBox.getSelectedItem().toString();
					String[] gid = gids.split("\\|");
//					crawler.crawling(new String[] { "http://www.huayicang.com" },
//							new String[] { comboBox.getSelectedItem().toString(), text_no.getText() });
					crawler.crawling(new String[] { "http://www.huayicang.com" },
							new String[] { gid[0], text_no.getText() });
				}
			} else if ("exit".equals(onclick)) {
				jf.dispose();
			}
		}
	}

	public static void main(String[] args) {
		try {
			List<String> list = new ArrayList<String>();
			Page pageMatches = RequestAndResponseTool.sendRequstAndGetResponse("http://www.huayicang.com/hxgq/xpai/index.jsp");
//			Elements matchesList = pageMatches.getDoc().getElementsByTag("option");
			Elements qici = pageMatches.getDoc().getElementsByClass("qici");
			Elements matchesList = qici.get(0).getElementsByTag("option");
//			System.out.println(matchesList);
//			System.out.println(qici);
			
			for (Element element : matchesList) {
				System.out.println(">>>>>>>>>>"+element.attr("value")+"|"+element.text());
				list.add(element.attr("value")+"|"+element.text());
			}
			
			
			
//			Elements matches = matchesList.getElementsByTag("a");
//			for (int i=0;i<matchesList.size();i++) {
//				Element match = matchesList.get(i);
//				String herf = match.getElementsByAttribute("href").get(0).attr("href");
//				String gid = herf.substring(herf.indexOf("=") + 1);
//				String name = match.text();
//				String gidandname = gid + "|" + name;
//				comboBox.addItem(gidandname);
//				list.add(gidandname);
//				list.add(gidandname);
//			}
			new MainFrame(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
