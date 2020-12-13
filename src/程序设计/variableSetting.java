package �������;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * ������Ҫ�Ǵ��һЩ���õ��ı����ͷ�����
 * �̳�JFrame������������඼�̳д���
 * @author �����20190615323
 */
public class variableSetting extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	
	/*��ȡ�����ͼƬ�ز�*/
	Image wall = ImageIO.read(new File("picture/wall.png"));
	Image box = ImageIO.read(new File("picture/box.png"));
	Image star = ImageIO.read(new File("picture/star.png"));
	Image space = ImageIO.read(new File("picture/space.png"));
	Image outspace = ImageIO.read(new File("picture/outspace.png"));
	Image people = ImageIO.read(new File("picture/people.png"));
	Image end = ImageIO.read(new File("picture/end.png"));
	Image peopleInStar = ImageIO.read(new File("picture/people.png"));
	
	ImageIcon ii = new ImageIcon("picture/����.jpg");
	
	/*ͼ�꣬ר������������������ʾ*/
	ImageIcon iwall = new ImageIcon("picture/wall.png"); 
	ImageIcon ibox = new ImageIcon("picture/box.png"); 
	ImageIcon istar = new ImageIcon("picture/star.png"); 
	ImageIcon ispace = new ImageIcon("picture/space.png"); 
	ImageIcon ioutspace = new ImageIcon("picture/outspace.png"); 
    ImageIcon ipeople = new ImageIcon("picture/people.png");
    ImageIcon iend = new ImageIcon("picture/end.png"); 
    ImageIcon ipeopleInStar = new ImageIcon("picture/people.png");
    
    ImageIcon[] iconList = {iwall, ibox, istar, ispace, ioutspace, ipeople, iend, ipeopleInStar};  //������ڲ���
	
    // �����ж���Ҫ�������Ƶ������Ƿ����ļ��ж�ȡ������
    static boolean is_choose = false;
    // �����ж���Ҫ�洢�������Ƿ����Զ����ͼ������
    static boolean save_random = true;
    
	final int BLOCK_WIDTH = 32;  // һ�����ؿ�ĳ���

	final int BLOCK_HEIGHT =32;  // һ�����ؿ�Ŀ�ȣ��߶ȣ�
	
	public Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //��ȡ��Ļ�Ĵ�С������ʹ��������Ļ�������ʾ
	
	/*ʵ��ͼƬ�����ֵ�һһ��Ӧ��ʹmaps��ά������������ܳɹ�ת����ͼƬ
	 * 0-ǽ�ڣ������ƶ������ɿ�Խ��
	 * 1-���ӣ����ƶ������ɿ�Խ��
	 * 2-Ŀ��㣨�����ƶ������ɿ�Խ��
	 * 3-�ռ䣨�����ƶ����ɿ�Խ��
	 * 4-�ⲿ�ռ䣨�����ƶ������ɿ�Խ��
	 * 5-��ң���������
	 * 6-����Ŀ�������ӣ����ӵ���Ŀ����ʹ�ô�ͼ��
	 * 7-վ��Ŀ�����ˣ��˵�Ŀ���λ��ʱʹ�ô�ͼ��*/
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
