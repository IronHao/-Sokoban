package 程序设计;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
public class Menu extends JFrame {
	//组件的创建
	JPanel panel1 = (JPanel)this.getContentPane();
	ImageIcon image=new ImageIcon("picture/推箱子.jpg");
	JButton btn1=new JButton("跳转");
	JLabel label=new JLabel("Sokoban",JLabel.CENTER);
	JLabel label2 = new JLabel(image);	
	Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public Menu() {
		label2.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		//将panel1自下而上转化，设置成透明
		panel1.setOpaque(false);
		panel1.setLayout(null);
		btn1.setBounds(500, image.getIconHeight()/2+200,200,75);
		btn1.setBackground(Color.white);
		btn1.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		label.setBounds(image.getIconWidth()/2-150, 200,300,200);
		label.setFont(new Font("Times NewRoman",Font.ITALIC,50));
		//使按钮透明
		btn1.setContentAreaFilled(false);
		//设置按钮字体颜色
		btn1.setForeground(Color.white);
		//另一层的面板，设置背景
		this.getLayeredPane().add(label2,new Integer(Integer.MIN_VALUE));
		this.setVisible(true);
		this.setTitle("欢迎");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(screenSize.width / 2 - image.getIconWidth() / 2, screenSize.height / 2 - image.getIconHeight() / 2, image.getIconWidth(),image.getIconHeight());
		this.setResizable(false);
		panel1.add(btn1);
		panel1.add(label);
		//是panel突起
		panel1.setBorder(BorderFactory.createRaisedBevelBorder());
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MenuBegin m=new MenuBegin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Menu.this.dispose();
				}
				catch(Exception l) {
					
				}
			}
		});
		//设置主题
		try {
			String IfClassName="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
	}
		catch(Exception e) {
			
		}
		//修改咖啡杯
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("picture/box.png"));
	
	}
	
	
	public static void main(String args[]) {
		Menu m=new Menu();
		
		}
	
	
}
