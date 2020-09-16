/**   
 * @Title: MainFrame.java 
 * @Package com.agree.mainFrame 
 * @Description: 主面板
 * @author lyz liaoyinzhen@cfischina.com   
 * @date 2015-12-5 下午12:13:42 
 * @version V1.0   
 */
package com.coin.crawl.main;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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

	
	// 得到屏幕分辨率
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public MainFrame() {
		jf = new JFrame();
		jf.setVisible(true);
		jf.setTitle("爬虫工具");
		jf.setSize(400, 180);

		jf.setLocation((width - 400) / 2, (height - 300) / 2);
		jf.setResizable(false);// 窗体不可调整
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭时关闭应用程序

		// 创建按钮 选择excl文件的按钮
		jPanel = new JPanel();

		JLabel label1 = new JLabel("      场次ID|名称：");
		JLabel label2 = new JLabel("商品起始序号：");
//		JButton input = new JButton("运行");

		JButton submit = new JButton("运行");
		JButton exit = new JButton("退出");

		jPanel.add(label2);
		jPanel.add(text_no);
		
		jPanel.add(label1);
		jPanel.add(text_excl);
//		jPanel.add(input);

		jPanel.add(submit, BorderLayout.SOUTH);
		jPanel.add(exit, BorderLayout.SOUTH);

		jf.add(jPanel);

//		input.addActionListener(new MouseAction("input"));
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
				MatchesCrawler crawler = new MatchesCrawler();
				// crawler.crawling(new String[] {
				// "http://www.huayicang.com/hxgq/xpai/goods_cc-18-1102-26.html" });
				crawler.crawling(new String[] { "http://www.huayicang.com" },new String[]{text_excl.getText(),text_no.getText()});
				
			} else if ("exit".equals(onclick)) {
				jf.dispose();
			}
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
