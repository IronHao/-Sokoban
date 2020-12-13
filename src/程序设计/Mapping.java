package 程序设计;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import 程序设计.Mapping.ButtonListener;

/**
 * 自定义地图的实现。这里点到地图外的话会有报错出现，暂时还没有处理，不影响使用，迟点我自己处理。
 * @author 杨铁皓201906150323
 *
 */

public class Mapping extends variableSetting {
	
	private static final long serialVersionUID = 1L;
	
	
	MyMapPanel pan=new MyMapPanel();
	Container c=getContentPane();
	
	static int[][]maps;  // 存储地图，在构造函数里再根据实际所需的行列来生成
	
	private int[][]easymaps = {{4,4,4,4,4,4,4,4,4,4},
                               {4,4,4,0,0,0,4,4,4,4},   // 存放地图数据
                               {4,4,4,0,2,0,4,4,4,4},
                               {4,4,4,0,3,0,0,0,0,4},
                               {4,0,0,0,1,3,1,2,0,4},
                               {4,0,2,3,1,5,0,0,0,4},
                               {4,0,0,0,0,1,0,4,4,4},
                               {4,4,4,4,0,2,0,4,4,4},
                               {4,4,4,4,0,0,0,4,4,4},
                               {4,4,4,4,4,4,4,4,4,4}};
	
	private int[][]hardmaps = {{4,4,4,4,4,4,4,4,4,4},
                               {4,0,0,0,0,0,0,0,0,4},
                               {4,0,3,3,0,3,3,3,0,4},
                               {4,0,3,1,2,2,1,3,0,4},
                               {4,0,5,1,2,6,3,0,0,4},
                               {4,0,3,1,2,2,1,3,0,4},
                               {4,0,3,3,0,3,3,3,0,4},
                               {4,0,0,0,0,0,0,0,0,4},
                               {4,4,4,4,4,4,4,4,4,4}};
	
	private int[][]middlemaps = {{4,4,4,4,4,4,4,4,4,4,4,4},
                                 {4,4,0,0,0,0,0,0,0,4,4,4},
                                 {4,4,0,3,3,3,3,3,0,0,0,4},
                                 {4,0,0,1,0,0,0,3,3,3,0,4},
                                 {4,0,3,5,3,1,3,3,1,3,0,4},
                                 {4,0,3,2,2,0,3,1,3,0,0,4},
                                 {4,0,0,2,2,0,3,3,3,0,4,4},
                                 {4,4,0,0,0,0,0,0,0,0,4,4},
                                 {4,4,4,4,4,4,4,4,4,4,4,4}};
	
	/*记录移动记录 用于10步撤销的实现*/
	public int record[][]=new int[100][2];  //记录了十步的数据 每步第一个数据是移动的方向 1234分别是上左下右 第二个数据是是否推动了箱子 1为推动0为没有
	public int step=0;

	private int NumberInOneLine;
	private int LineNumber;
	
	private int width;
	private int height;
	
	/*确定要读取的地图*/
	static int target; 
	
	/*找到玩家的坐标*/
	private int playerx;  
	private int playery;
	
	public Mapping()  throws IOException {
		
		
		setTitle("Map");
		// 文件中读取的数据和自有的简单、中等、困难地图的数据分开处理
		if(variableSetting.is_choose == false)
		{
			switch (target) {
			case 1:
				NumberInOneLine = easymaps[0].length - 2;
				LineNumber = easymaps.length - 2;
				break;
			case 2:
				NumberInOneLine = middlemaps[0].length - 2;
				LineNumber = middlemaps.length - 2;
				break;
			case 3:
				NumberInOneLine = hardmaps[0].length - 2;
				LineNumber = hardmaps.length - 2;
				break;
			}
				
			maps = new int[LineNumber + 2][NumberInOneLine + 2];
			for(int i=0;i<LineNumber + 2;i++) {
				for(int j=0;j<NumberInOneLine + 2;j++) {
					switch(target) {
					case 1:
					    maps[i][j] = easymaps[i][j];
					    break;
					case 2:
						maps[i][j] = middlemaps[i][j];
						break;
					case 3:
						maps[i][j] = hardmaps[i][j];
						break;
					}
				}
			}
			
			width = NumberInOneLine * BLOCK_WIDTH;
			height = LineNumber * BLOCK_HEIGHT;
		}
		else
		{	
			// 对于文件导入的数据，此时maps的数据在MenuBegin类已经赋值完成
			NumberInOneLine = maps[0].length - 2;
			LineNumber = maps.length - 2;
			
			width = NumberInOneLine * BLOCK_WIDTH;
			height = LineNumber * BLOCK_HEIGHT;
		}
		
		setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width+100, height);

		
		pan.setBounds(MenuBegin.panel2.getWidth() / 2 - width /2, MenuBegin.panel2.getHeight() / 2 - height / 2, width, height);
		pan.setLayout(null);

		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		MenuBegin.panel2.add(pan);
		JButton btn5=new JButton();  //撤销键  每次生成地图时更新
		btn5.setIcon(ii);
		btn5.setContentAreaFilled(false);
		btn5.setFocusPainted(false);
		btn5.setBounds(-10,530, 80, 80);
		MenuBegin.panel2.add(btn5);
		PanelListener plis=new PanelListener();
		pan.addKeyListener(plis);  //加入键盘监听者
		
		ButtonListener blis=new ButtonListener();
		btn5.addActionListener(blis);
		pan.setVisible(true);
		
		pan.requestFocus();  //因为监听者是在Panel上的，所以要把焦点从选中的按钮转换到Panel上
		pan.repaint();
		
		/*找到玩家位置坐标*/
		for(int i=1;i<maps.length-1;i++) {
			for(int j=1;j<maps[0].length-1;j++) {
				if(maps[i][j]==5||maps[i][j]==7) {
					playerx=i;
					playery=j;
				}
			}
		}
		
		/*初始化撤销记录数组*/
		for(int i=0;i<100;i++) {
			record[i][0]=-1;
			record[i][1]=0;
		}
	}
  	
	
 
	/*这个类重载了paint方法，实现绘制地图的效果*/
	class MyMapPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			Image image = null;
			super.paint(g);  // 必须继承，不然原有的方法不能实现，有些已部署的部件就不能绘制出来了（应该
			for (int line = 0; line<LineNumber; line++) {
				for(int list = 0; list<NumberInOneLine; list++) {
					image = getImage(maps[line + 1][list + 1]);
				    g.drawImage(image, list * BLOCK_WIDTH, line * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT,null);
				}
			}
		}
	}
	
	/*检查能否推动*/
	Boolean check(int position) {
		//不能推有三种情况：1.前面是地图边界 2.前面是墙 3.前面是箱子且前面的前面是墙或边界或箱子
		switch(position){
		case 1:
			if(maps[playerx-1][playery]==4||maps[playerx-1][playery]==0)
				return false;
			else if(maps[playerx-1][playery]==1||maps[playerx-1][playery]==6)
				if(maps[playerx-2][playery]==1||maps[playerx-2][playery]==4||maps[playerx-2][playery]==0||maps[playerx-2][playery]==6)
					return false;
			break;
		case 2:
			if(maps[playerx][playery-1]==4||maps[playerx][playery-1]==0)
				return false;
			else if(maps[playerx][playery-1]==1||maps[playerx][playery-1]==6)
				if(maps[playerx][playery-2]==1||maps[playerx][playery-2]==4||maps[playerx][playery-2]==0||maps[playerx][playery-2]==6)
					return false;
			break;
		case 3:
			if(maps[playerx+1][playery]==4||maps[playerx+1][playery]==0)
				return false;
			else if(maps[playerx+1][playery]==1||maps[playerx+1][playery]==6)
				if(maps[playerx+2][playery]==1||maps[playerx+2][playery]==4||maps[playerx+2][playery]==0||maps[playerx+2][playery]==6)
					return false;
			break;
		case 4:
			if(maps[playerx][playery+1]==4||maps[playerx][playery+1]==0)
				return false;
			else if(maps[playerx][playery+1]==1||maps[playerx][playery+1]==6)
				if(maps[playerx][playery+2]==1||maps[playerx][playery+2]==4||maps[playerx][playery+2]==0||maps[playerx][playery+2]==6)
					return false;
			break;
		default:
			break;
		}
		return true;
	}
	
	/*移动 修改移动后的数组 position为方向 1234分别为上左下右 不同的情况进行不同的地图数组修改*/
	void move(int position) {
		switch(position){
		case 1:
			if(maps[playerx][playery]==5) 
				maps[playerx][playery]=3;
			else
				maps[playerx][playery]=2;
			if(maps[playerx-1][playery]==1){
				record[step%100][1]=1;
				maps[playerx-1][playery]=5;
				if(maps[playerx-2][playery]==2) 
					maps[playerx-2][playery]=6;
				else if(maps[playerx-2][playery]==3)
					maps[playerx-2][playery]=1;
				}
			else if(maps[playerx-1][playery]==3){
				record[step%100][1]=0;
				maps[playerx-1][playery]=5;
				}
			else if(maps[playerx-1][playery]==2){
				record[step%100][1]=0;
				maps[playerx-1][playery]=7;
				}
			else if(maps[playerx-1][playery]==6){
				record[step%100][1]=1;
				maps[playerx-1][playery]=7;
				if(maps[playerx-2][playery]==2) 
					maps[playerx-2][playery]=6;
				else if(maps[playerx-2][playery]==3)
					maps[playerx-2][playery]=1;
				}
			playerx--;
			break;
		case 2:
			if(maps[playerx][playery]==5) 
				maps[playerx][playery]=3;
			else
				maps[playerx][playery]=2;
			if(maps[playerx][playery-1]==1){
				record[step%100][1]=1;
				maps[playerx][playery-1]=5;
				if(maps[playerx][playery-2]==2) 
					maps[playerx][playery-2]=6;
				else if(maps[playerx][playery-2]==3)
					maps[playerx][playery-2]=1;
				}
			else if(maps[playerx][playery-1]==3){
				record[step%100][1]=0;
				maps[playerx][playery-1]=5;
				}
			else if(maps[playerx][playery-1]==2){
				record[step%100][1]=0;
				maps[playerx][playery-1]=7;
				}
			else if(maps[playerx][playery-1]==6){
				record[step%100][1]=1;
				maps[playerx][playery-1]=7;
				if(maps[playerx][playery-2]==2) 
					maps[playerx][playery-2]=6;
				else if(maps[playerx][playery-2]==3)
					maps[playerx][playery-2]=1;
				}
			playery--;
			break;
		case 3:
			if(maps[playerx][playery]==5)
				maps[playerx][playery]=3;
			else
				maps[playerx][playery]=2;
			if(maps[playerx+1][playery]==1){
				record[step%100][1]=1;
				maps[playerx+1][playery]=5;
				if(maps[playerx+2][playery]==2)
					maps[playerx+2][playery]=6;
				else if(maps[playerx+2][playery]==3)
					maps[playerx+2][playery]=1;
				}
			else if(maps[playerx+1][playery]==3){
				record[step%100][1]=0;
				maps[playerx+1][playery]=5;
			}
			else if(maps[playerx+1][playery]==2){
				record[step%100][1]=0;
				maps[playerx+1][playery]=7;
			}
			else if(maps[playerx+1][playery]==6){
				record[step%100][1]=1;
				maps[playerx+1][playery]=7;
				if(maps[playerx+2][playery]==2)
					maps[playerx+2][playery]=6;
				else if(maps[playerx+2][playery]==3)
					maps[playerx+2][playery]=1;
			}
			playerx++;
			break;
		case 4:
			if(maps[playerx][playery]==5) 
				maps[playerx][playery]=3;
			else
				maps[playerx][playery]=2;
			if(maps[playerx][playery+1]==1){
				record[step%100][1]=1;
				maps[playerx][playery+1]=5;
				if(maps[playerx][playery+2]==2) 
					maps[playerx][playery+2]=6;
				else if(maps[playerx][playery+2]==3)
					maps[playerx][playery+2]=1;
				}
			else if(maps[playerx][playery+1]==3){
				record[step%100][1]=0;
				maps[playerx][playery+1]=5;
				}
			else if(maps[playerx][playery+1]==2){
				record[step%100][1]=0;
				maps[playerx][playery+1]=7;
				}
			else if(maps[playerx][playery+1]==6){
				record[step%100][1]=1;
				maps[playerx][playery+1]=7;
				if(maps[playerx][playery+2]==2) 
					maps[playerx][playery+2]=6;
				else if(maps[playerx][playery+2]==3)
					maps[playerx][playery+2]=1;
				}
			playery++;
			break;
		}
	}
	
	/*更改record数组*/
	public void record(int position) {
		switch(position) {
		case 1:
			record[step%100][0]=1;
			break;
		case 2:
			record[step%100][0]=2;
			break;
		case 3:
			record[step%100][0]=3;
			break;
		case 4:
			record[step%100][0]=4;
			break;
		}
	}
	
	/*回退一步 重新绘制地图 逻辑即为拉箱子*/
	public void back(int position) {
		switch(position){
		case 1:
			if(maps[playerx+1][playery]==2) 
				maps[playerx+1][playery]=7;
			else
				maps[playerx+1][playery]=5;
			if(record[step%100][1]==1) {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=1;
				else
					maps[playerx][playery]=6;
				if(maps[playerx-1][playery]==1)
					maps[playerx-1][playery]=3;
				else
					maps[playerx-1][playery]=2;
			}
			else {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=3;
				else
					maps[playerx][playery]=2;
			}
			playerx++;
			break;
		case 2:
			if(maps[playerx][playery+1]==2) 
				maps[playerx][playery+1]=7;
			else
				maps[playerx][playery+1]=5;
			if(record[step%100][1]==1) {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=1;
				else
					maps[playerx][playery]=6;
				if(maps[playerx][playery-1]==1)
					maps[playerx][playery-1]=3;
				else
					maps[playerx][playery-1]=2;
			}
			else {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=3;
				else
					maps[playerx][playery]=2;
			}
			playery++;
			break;
		case 3:
			if(maps[playerx-1][playery]==2) 
				maps[playerx-1][playery]=7;
			else
				maps[playerx-1][playery]=5;
			if(record[step%1000][1]==1) {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=1;
				else
					maps[playerx][playery]=6;
				if(maps[playerx+1][playery]==1)
					maps[playerx+1][playery]=3;
				else
					maps[playerx+1][playery]=2;
			}
			else {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=3;
				else
					maps[playerx][playery]=2;
			}
			playerx--;
			break;
		case 4:
			if(maps[playerx][playery-1]==2) 
				maps[playerx][playery-1]=7;
			else
				maps[playerx][playery-1]=5;
			if(record[step%100][1]==1) {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=1;
				else
					maps[playerx][playery]=6;
				if(maps[playerx][playery+1]==1)
					maps[playerx][playery+1]=3;
				else
					maps[playerx][playery+1]=2;
			}
			else {
				if(maps[playerx][playery]==5) 
					maps[playerx][playery]=3;
				else
					maps[playerx][playery]=2;
			}
			playery--;
			break;
		}
	}
	
	/*判断所有箱子是否都到位了*/
	boolean is_success() {
		for(int i=1;i<maps.length-1;i++) {
			for(int j=1;j<maps[0].length-1;j++) {
				if (maps[i][j]==1)
					return false;
				}
			}
		return true;
	}
	
	/*跳出恭喜完成本关的选择框*/
	void success() {
		JOptionPane.showMessageDialog(null,"恭喜完成本关，共进行了"+step+"次移动","恭喜",JOptionPane.PLAIN_MESSAGE);
	}
	
	/*监听用户键盘输入 并作出相应动作 awsd也可控制*/
	public class PanelListener extends KeyAdapter {
		
		// 当按键按下
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();  //获取键码常量
			switch (code) {
			case KeyEvent.VK_UP: case 87:  //如果是上键或w键则 判断能否向上移动 若能 则进行移动和移动记录
				if (check(1)) {
					step++;
					record(1);
					move(1);
				}			
				break;
			case KeyEvent.VK_DOWN: case 83:
				if (check(3)) {
					step++;
					record(3);
					move(3);
				}
				break;
			case KeyEvent.VK_LEFT: case 65:
				if (check(2)) {
					step++;
					record(2);
					move(2);
				}
				break;
			case KeyEvent.VK_RIGHT: case 68:
				if (check(4)) {
					step++;
					record(4);
					move(4);
				} 
				break;
			default:
				break;
			}
			pan.repaint();  //每次检测到键盘指令都要重新绘制地图
			if(is_success())
				success();   //每次检测到键盘指令都要判断是否已经过关 过关则弹出相应窗口
		}
	}
	
	/*撤销键监听者 点击撤销键则执行一步撤销*/
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			switch(record[step%100][0]){
				case 1:
					back(1);  //撤销
					record[step%100][0]=-1;	  //将最近的一步记录清空	
					step--;  //步数减少1
					break;
				case 2:
					back(2);
					record[step%100][0]=-1;			
					step--;
					break;
				case 3:
					back(3);
					record[step%100][0]=-1;			
					step--;
				    break;
				case 4:
					back(4);
					record[step%100][0]=-1;			
					step--;
					break;
				case -1:
					break;
			}
			pan.requestFocus();  //将焦点从按钮拉回到panel上
			pan.repaint();
		}
	}
}