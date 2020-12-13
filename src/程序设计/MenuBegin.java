package 程序设计;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import 程序设计.Mapping.ButtonListener;

public class MenuBegin extends JFrame  {
	ImageIcon image=new ImageIcon("picture/推箱子1.jpg");  
	JLabel labelback=new JLabel(image);
	JPanel panel1 = (JPanel)this.getContentPane();
	static JPanel panel2=new JPanel();
	JMenuBar jtb=new JMenuBar();
	JMenu file=new JMenu("文件(F)");
	JMenu other=new JMenu("其它(O)");
	JMenuItem savefile=new JMenuItem("保存");
	JMenuItem choosefile=new JMenuItem("读取");
	JMenuItem help=new JMenuItem("帮助");
	JButton btn1=new JButton("简单模式");
	JButton btn2=new JButton("中等模式");
	JButton btn3=new JButton("困难模式");
	JButton btn4=new JButton("自定义模式");
	
	JButton btn6=new JButton("存档一");
	JButton btn7=new JButton("存档二");
	JButton btn8=new JButton("存档三");
	JButton btn9=new JButton("存档一");
	JButton btn10=new JButton("存档二");
	JButton btn11=new JButton("存档三");
	
	int widthPanel2 = image.getIconWidth()-300;
	int heightPanel2 = image.getIconHeight();
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public MenuBegin() throws IOException{
		//JFrame的初始化
		super("推箱子");
		setBounds(screenSize.width / 2 - image.getIconWidth() / 2, screenSize.height / 2 - image.getIconHeight() / 2, image.getIconWidth(),image.getIconHeight());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(jtb);
		this.setResizable(true);
		this.setLayout(null);
		//按钮的初始化
		btn1.setBounds(900, 30, 200, 100);
		btn1.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn2.setBounds(900, 170, 200, 100);
		btn2.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn3.setBounds(900, 310, 200, 100);
		btn3.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn4.setBounds(900, 450, 200, 100);
		btn4.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		
		btn6.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn7.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn8.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn9.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn10.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		btn11.setFont(new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,30));
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					panel2.removeAll();
					panel2.setVisible(true);
					panel2.repaint();			
		
					// 用于告诉存储命令，当前存储的是否是自定义数据
					variableSetting.save_random = false;
					// 用于告诉Mapping类，当前使用的数据是否是文件中读取出的
					variableSetting.is_choose = false;
					Mapping.target = 1;
					Mapping map=new Mapping();
				}catch (IOException eI) {
					System.out.println(eI.toString());
				}
			}
			
		});
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					panel2.removeAll();
					panel2.setVisible(true);
					panel2.repaint();
		
					variableSetting.save_random = false;
					variableSetting.is_choose = false;
					Mapping.target = 2;
					Mapping map=new Mapping();
					}catch (IOException eI) {}
			}
			
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					panel2.removeAll();
					panel2.setVisible(true);
					panel2.repaint();

					Mapping.target = 3;
					variableSetting.save_random = false;
					variableSetting.is_choose = false;
					Mapping map=new Mapping();
				}catch (IOException eI) {}
			}
			
		});
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					panel2.removeAll();
					panel2.setVisible(true);
					panel2.repaint();
					
					variableSetting.save_random = true;
					variableSetting.is_choose = false;
					
					createCustomMap map=new createCustomMap(); 														
					
					
				}catch (IOException eI) {}
			}
			
		});
		
		// 存档1
		btn9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 分辨需要存储的是否是自定义数据
				if(variableSetting.save_random == false)
				{
					try {				
						// 确定存档1的存储地址
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/1.txt"));
					    // 明确存储的地图的长宽，便于读取后的数据转换
						bfw.write(Mapping.maps.length+"\n");							
						bfw.write(Mapping.maps[0].length+"\n");
                        // 在一行内逐个存入地图数据
						for(int aa =0; aa<Mapping.maps.length; aa++)
						{							
							for(int bb=0; bb<Mapping.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(Mapping.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档一保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
				// 需要存入的数据是自定义的情况
				else if(variableSetting.save_random == true)
				{
					try {							
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/1.txt"));
					
						bfw.write(CustomMap.maps.length+"\n");							
						bfw.write(CustomMap.maps[0].length+"\n");

						for(int aa =0; aa<CustomMap.maps.length; aa++)
						{							
							for(int bb=0; bb<CustomMap.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(CustomMap.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档一保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
								
			}
		});
		// 存档2
        btn10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(variableSetting.save_random == false)
				{
					try {							
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/2.txt"));
					
						bfw.write(Mapping.maps.length+"\n");							
						bfw.write(Mapping.maps[0].length+"\n");

						for(int aa =0; aa<Mapping.maps.length; aa++)
						{							
							for(int bb=0; bb<Mapping.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(Mapping.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档二保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
				else if(variableSetting.save_random == true)
				{
					try {							
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/2.txt"));
					
						bfw.write(CustomMap.maps.length+"\n");							
						bfw.write(CustomMap.maps[0].length+"\n");

						for(int aa =0; aa<CustomMap.maps.length; aa++)
						{							
							for(int bb=0; bb<CustomMap.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(CustomMap.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档二保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
								
			}
		});
       // 存档3
        btn11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(variableSetting.save_random == false)
				{
					try {							
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/3.txt"));
					
						bfw.write(Mapping.maps.length+"\n");							
						bfw.write(Mapping.maps[0].length+"\n");

						for(int aa =0; aa<Mapping.maps.length; aa++)
						{							
							for(int bb=0; bb<Mapping.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(Mapping.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档三保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
				else if(variableSetting.save_random == true)
				{
					try {							
						BufferedWriter bfw = new BufferedWriter(new FileWriter("map/3.txt"));
					
						bfw.write(CustomMap.maps.length+"\n");							
						bfw.write(CustomMap.maps[0].length+"\n");

						for(int aa =0; aa<CustomMap.maps.length; aa++)
						{							
							for(int bb=0; bb<CustomMap.maps[0].length; bb++)
							{							
								bfw.write(String.valueOf(CustomMap.maps[aa][bb]));
								bfw.flush();
							}
						}
						bfw.flush();
						bfw.close();
						JOptionPane.showMessageDialog(null, "存档三保存成功","提示",JOptionPane.PLAIN_MESSAGE);
						}catch(Exception e1)
						{}
				}
								
			}
		});
           
		
		choosefile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame m=new JFrame("存档");
				m.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 300 ,400,600);
				m.setVisible(true);
				m.setLayout(new GridLayout(3, 1));
				m.add(btn6);
				m.add(btn7);
				m.add(btn8);
				
				
				
			}
		});
		savefile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame m=new JFrame("保存");
				m.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 300 ,400,600);
				m.setVisible(true);
				m.setLayout(new GridLayout(3, 1));
				m.add(btn9);
				m.add(btn10);
				m.add(btn11);
				
				
			}
		});
		// 读取存档一按钮的相应方式     
        btn6.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try 
				{
					FileReader fr = new FileReader("map/1.txt");
					BufferedReader br = new BufferedReader(fr);
					
					String LineNumber;
					String NumberInOneLine;
	     			String strLine;
					while(br.ready())
					{
						LineNumber = br.readLine();
						NumberInOneLine = br.readLine();
						strLine = br.readLine();
	
						Mapping.maps = new int[Integer.parseInt(LineNumber)][Integer.parseInt(NumberInOneLine)];											
								
						for(int line_in =0; line_in<Integer.parseInt(LineNumber); line_in++)
						{
							for(int list_in =0; list_in<Integer.parseInt(NumberInOneLine); list_in++)
							{								
								Mapping.maps[line_in][list_in] =  (int)strLine.charAt(line_in*Integer.parseInt(NumberInOneLine) + list_in) - (int)('0');
							}							
						}
						variableSetting.is_choose = true;
						
						panel2.removeAll();
						
						Mapping map = new Mapping();
					
												
						panel2.setVisible(true);
						panel2.repaint();
					}	
					br.close();
					br.close();
					fr.close();				
				}catch(Exception e1)
				{}								
			}
		});
        // 读取存档2的响应方式
        btn7.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try 
				{
					FileReader fr = new FileReader("map/2.txt");
					BufferedReader br = new BufferedReader(fr);
					
					String LineNumber;
					String NumberInOneLine;
	     			String strLine;
					while(br.ready())
					{
						LineNumber = br.readLine();
						NumberInOneLine = br.readLine();
						strLine = br.readLine();
	
						Mapping.maps = new int[Integer.parseInt(LineNumber)][Integer.parseInt(NumberInOneLine)];											
								
						for(int line_in =0; line_in<Integer.parseInt(LineNumber); line_in++)
						{
							for(int list_in =0; list_in<Integer.parseInt(NumberInOneLine); list_in++)
							{								
								Mapping.maps[line_in][list_in] =  (int)strLine.charAt(line_in*Integer.parseInt(NumberInOneLine) + list_in) - (int)('0');
							}							
						}
						variableSetting.is_choose = true;
						
						panel2.removeAll();
						
						Mapping map = new Mapping();
					
												
						panel2.setVisible(true);
						panel2.repaint();
					}	
					br.close();
					br.close();
					fr.close();				
				}catch(Exception e1)
				{}								
			}
		});
        // 读取存档3的响应方式
        btn8.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try 
				{
					FileReader fr = new FileReader("map/3.txt");
					BufferedReader br = new BufferedReader(fr);
					
					String LineNumber;
					String NumberInOneLine;
	     			String strLine;
					while(br.ready())
					{
						LineNumber = br.readLine();
						NumberInOneLine = br.readLine();
						strLine = br.readLine();
	
						Mapping.maps = new int[Integer.parseInt(LineNumber)][Integer.parseInt(NumberInOneLine)];											
								
						for(int line_in =0; line_in<Integer.parseInt(LineNumber); line_in++)
						{
							for(int list_in =0; list_in<Integer.parseInt(NumberInOneLine); list_in++)
							{								
								Mapping.maps[line_in][list_in] =  (int)strLine.charAt(line_in*Integer.parseInt(NumberInOneLine) + list_in) - (int)('0');
							}							
						}
						variableSetting.is_choose = true;
						
						panel2.removeAll();
						
						Mapping map = new Mapping();
						
												
						panel2.setVisible(true);
						panel2.repaint();
					}	
					br.close();
					br.close();
					fr.close();				
				}catch(Exception e1)
				{}								
			}
		});
        
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "最经典的推箱子游戏，类似的游戏你一定早就玩过。要控制搬运工上下左右移动，来将箱子推到指定地点"+"\n" +
                        "-----------------" + "\n" +
                        "叶 飞   杨铁皓   徐声远   徐溶锴","帮助",-1);
				
			}
		});

		
		
		//添加背景
		this.getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));
		//JLabel大小设置
		labelback.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		//JPanel的初始化
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		//菜单栏的组件添加
		jtb.add(file);
		jtb.add(other);
		file.add(savefile);
		file.add(choosefile);
		other.add(help);
		//JPanel的组件添加
		panel1.add(btn1);
		panel1.add(btn2);
		panel1.add(btn3);
		panel1.add(btn4);
		
		panel1.add(panel2);
		
		//JPanel的初始化
		panel2.setBounds(0, 0, widthPanel2, heightPanel2);
		//快捷键
		file.setMnemonic('F');
		other.setMnemonic('O');
		//主题美化
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
	}
		catch(Exception e) {
			
		}
	//修改咖啡杯
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("picture/box.png"));
		
	}
	public static void main(String args[]) {
		try{
			new MenuBegin();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
