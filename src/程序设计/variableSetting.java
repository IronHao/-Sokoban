package 程序设计;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 这里主要是存放一些常用到的变量和方法，
 * 继承JFrame，方便后续的类都继承此类
 * @author 杨铁皓20190615323
 */
public class variableSetting extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	/*获取所需的图片素材*/
	Image wall = ImageIO.read(new File("picture/wall.png"));
	Image box = ImageIO.read(new File("picture/box.png"));
	Image star = ImageIO.read(new File("picture/star.png"));
	Image space = ImageIO.read(new File("picture/space.png"));
	Image outspace = ImageIO.read(new File("picture/outspace.png"));
	Image people = ImageIO.read(new File("picture/people.png"));
	Image end = ImageIO.read(new File("picture/end.png"));
	Image peopleInStar = ImageIO.read(new File("picture/people.png"));
	
	ImageIcon ii = new ImageIcon("picture/返回.jpg");
	
	/*图标，专门用来在下拉框里显示*/
	ImageIcon iwall = new ImageIcon("picture/wall.png"); 
	ImageIcon ibox = new ImageIcon("picture/box.png"); 
	ImageIcon istar = new ImageIcon("picture/star.png"); 
	ImageIcon ispace = new ImageIcon("picture/space.png"); 
	ImageIcon ioutspace = new ImageIcon("picture/outspace.png"); 
    ImageIcon ipeople = new ImageIcon("picture/people.png");
    ImageIcon iend = new ImageIcon("picture/end.png"); 
    ImageIcon ipeopleInStar = new ImageIcon("picture/people.png");
    
    ImageIcon[] iconList = {iwall, ibox, istar, ispace, ioutspace, ipeople, iend, ipeopleInStar};  //数组便于操作
	
    // 用于判断需要处理并绘制的数据是否是文件中读取而来的
    static boolean is_choose = false;
    // 用于判断需要存储的数据是否是自定义地图的数据
    static boolean save_random = true;
    
	final int BLOCK_WIDTH = 32;  // 一个像素块的长度

	final int BLOCK_HEIGHT =32;  // 一个像素块的宽度（高度）
	
	public Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //获取屏幕的大小，用于使程序在屏幕里居中显示
	
	/*实现图片和数字的一一对应，使maps二维数组里的数字能成功转换成图片
	 * 0-墙壁（不可移动，不可跨越）
	 * 1-箱子（可移动，不可跨越）
	 * 2-目标点（不可移动，不可跨越）
	 * 3-空间（不可移动，可跨越）
	 * 4-外部空间（不可移动，不可跨越）
	 * 5-玩家（操作对象）
	 * 6-到达目标点的箱子（箱子到达目标点后使用此图像）
	 * 7-站在目标点的人（人到目标点位置时使用此图像）*/
	Image getImage(Integer i) {
		switch (i) {
		case 0:
			return wall;
		case 1:
			return box;
		case 2:
			return star;
		case 3:
			return space;
		case 4:
			return outspace;
		case 5:
			return people;
		case 6:
			return end;
		case 7:
			return peopleInStar;
		default:
			return null;


		}
	}
	
	
	public variableSetting() throws IOException{
		// TODO Auto-generated constructor stub
	}

}
