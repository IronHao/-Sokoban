package 程序设计;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

/**
 * 自定义地图的实现。实现自定义地图的编辑
 * @author 杨铁皓201906150323
 *
 */

public class CustomMap extends variableSetting {
	
	private static final long serialVersionUID = 1L;
	
	
	MyMapPanel pan=new MyMapPanel();
	Container c=getContentPane();
	JComboBox<ImageIcon> cbox = new JComboBox<ImageIcon>();  // 下拉框，存储图标，便于用户选择方块
	PanelListenner plis = new PanelListenner();  // panel的事件监听
	Image tempPeople = ipeople.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);  // 修改原来的图标为32×32像素，保证大小一致，SCALE_DEFAULT表示默认的图像缩放算法
	
	
	static int[][]maps;  // 存储地图，在构造函数里再根据实际所需的行列来生成
	
	private int NumberInOneLine;
	private int LineNumber;
	
	private int width;
	private int height;
	
	private int peopleNumber = 0;  // 记录当前编辑状态有几个玩家被添加上去
	
	
	public CustomMap()  throws IOException {
		
		setTitle("Map");
		NumberInOneLine=createCustomMap.col;
		LineNumber=createCustomMap.row;
		maps = new int[LineNumber + 2][NumberInOneLine + 2];
		for(int i=0;i<LineNumber + 2;i++) {
			for(int j=0;j<NumberInOneLine + 2;j++) {
				maps[i][j]=4;    // 生成的方块默认是 4，即outspace，不可以通过的方块
			}
		}
		
		width = NumberInOneLine * BLOCK_WIDTH;
		height = LineNumber * BLOCK_HEIGHT;
		
		setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width+100, height);
		
		
		/*俩图标用的同一个图片，一起改*/    
		iconList[5] = new ImageIcon(tempPeople);
		iconList[7] = new ImageIcon(tempPeople);
	
		for (int i = 0; i < iconList.length; i++) {
			cbox.addItem(iconList[i]);   // 往列表框里按顺序添加东西
		}
		cbox.setSelectedIndex(0);
		cbox.setBounds(MenuBegin.panel2.getWidth()-60, 0, 60, 50);
		
		
		MenuBegin.panel2.add(cbox);
		
		pan.setBounds(MenuBegin.panel2.getWidth() / 2 - width /2, MenuBegin.panel2.getHeight() / 2 - height / 2, width, height);
		pan.setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		MenuBegin.panel2.add(pan);
		pan.addMouseListener(plis);  // 添加关于鼠标操作在panel上的事件监听
		
		pan.setVisible(true);
		pan.repaint();
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
					image = getImage(maps[line+1][list+1]);
				    g.drawImage(image, list * BLOCK_WIDTH, line * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT,null);
				}
			}
		}
	}
	
	/*具体的事件监听代码，获取鼠标所处位置的坐标，除以方块的宽(高)，来确定是maps数组几行几列，然后将其更换
	 * 加入了异常处理*/
	class PanelListenner extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			try {
				int j = e.getX() / BLOCK_WIDTH + 1;
				int i = e.getY() / BLOCK_HEIGHT + 1;
				int index = cbox.getSelectedIndex();  // 获取下拉框当前所选的方块
				// 下方的if是为了处理覆盖原有人物的情况
				if ((index != 5 && index != 7) && (maps[i][j] == 5 || maps[i][j] == 7)){
					peopleNumber = peopleNumber - 1;
				}
				if(index == 5 || index == 7) {
					peopleNumber = peopleNumber + 1; 
			    }
				if(peopleNumber > 1) {
					throw new Exception();
				}
				maps[i][j] = index;
				pan.repaint();  // 重新绘制一遍，就得到结果了
			}catch(Exception ee) {
				JOptionPane.showMessageDialog(null, "只能有一名推箱子的玩家", "提示", JOptionPane.PLAIN_MESSAGE);
				peopleNumber = peopleNumber - 1;
			}
			
			
		}
	}
}