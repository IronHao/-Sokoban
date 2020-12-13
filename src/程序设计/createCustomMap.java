package 程序设计;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

/**
 * 这里实现生成一个窗口，用于设置自定义地图的大小
 * 异常处理已经完成，窗体大小也已经设定好了，不必再改
 * 限制了生成地图的大小，规定在3-20之间
 * @author 杨铁皓201906150323
 *
 */

public class createCustomMap extends variableSetting implements ActionListener{
	
	/* 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。--来自百度
	 * 大概理解是为了兼容性，加一加，还能把警告去掉，不是坏事*/
	private static final long serialVersionUID = 1L; 
	
	
	Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container c = getContentPane();
	
	private int width = 430;
	private int height = 180;
	
	/*两个静态变量，存储自定义地图的行列，便于在CustomMap里使用*/
	static int col;
	static int row;
	
	JTextField []text = {new JTextField(),
                         new JTextField()};

	JLabel []lab = {new JLabel("输入地图行数："),
                    new JLabel("输入地图列数：")};
	
	JButton but = new JButton("生成地图");

	public createCustomMap() throws IOException{
		setTitle("设置地图大小");
		setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width, height);
		setLayout(null);
		setBackground(Color.white);
	
		
		for (int i = 0; i<text.length;i++) {
			add(lab[i]);
			add(text[i]);
			lab[i].setBounds(10, i*50+10, 120, 40);
			text[i].setBounds(140, i*50+10, 120, 40);
		}
		
		add(but);
		
		
		but.setBounds(270, 30, 120, 60);
		
		but.setActionCommand("click");
		but.addActionListener(this);
		
		setVisible(true);
	}
	
	/*添加了异常处理，只有输入符合规范，才会生成并跳转到自定义地图上，否则弹框提示错误*/
	public void createBlankMap() {
		try{
			if ((Integer.valueOf(text[0].getText()) > 2 && Integer.valueOf(text[1].getText()) > 0) &&
			    (Integer.valueOf(text[0].getText()) <= 20 && Integer.valueOf(text[1].getText()) <= 32)){
				row = Integer.valueOf(text[0].getText());
				col = Integer.valueOf(text[1].getText());
				this.dispose();
				//提供弹窗提示，介绍如何使用自定义地图
				JOptionPane.showMessageDialog(null, "请注意，地图一定要确保编辑完成再保存，暂不支持再次编辑。\n" +
						                            "以及编辑用下拉框就在简单模式按钮旁边旁边，轻碰即可发现！\n" +
						                            "下拉框里内容按顺序分别为：\n" +
						                            "* 0-墙壁（不可移动，不可跨越）\r\n" + 
						                            "* 1-箱子（可移动，不可跨越）\r\n" + 
						                            "* 2-目标点（不可移动，不可跨越）\r\n" + 
						                            "* 3-空间（不可移动，可跨越）\r\n" + 
						                            "* 4-外部空间（不可移动，不可跨越）\r\n" + 
						                            "* 5-玩家（操作对象）\r\n" + 
						                            "* 6-到达目标点的箱子（箱子到达目标点后使用此图像）\r\n" + 
						                            "* 7-站在目标点的人（人到目标点位置时使用此图像）","使用须知",JOptionPane.PLAIN_MESSAGE);
				new CustomMap();
			}
			else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			//错误输入后提示错误
			JOptionPane.showMessageDialog(null, "输入错误，请重新输入(请输入3-20的整数)","错误",JOptionPane.PLAIN_MESSAGE);
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e)  {
		if(e.getActionCommand().equals("click")) {
			try{
				createBlankMap();
			}catch (Exception ee) {
				System.out.println(ee.toString());
			}
		}
	}

}
