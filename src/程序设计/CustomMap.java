package �������;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

/**
 * �Զ����ͼ��ʵ�֡�ʵ���Զ����ͼ�ı༭
 * @author �����201906150323
 *
 */

public class CustomMap extends variableSetting {
	
	private static final long serialVersionUID = 1L;
	
	
	MyMapPanel pan=new MyMapPanel();
	Container c=getContentPane();
	JComboBox<ImageIcon> cbox = new JComboBox<ImageIcon>();  // �����򣬴洢ͼ�꣬�����û�ѡ�񷽿�
	PanelListenner plis = new PanelListenner();  // panel���¼�����
	Image tempPeople = ipeople.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);  // �޸�ԭ����ͼ��Ϊ32��32���أ���֤��Сһ�£�SCALE_DEFAULT��ʾĬ�ϵ�ͼ�������㷨
	
	
	static int[][]maps;  // �洢��ͼ���ڹ��캯�����ٸ���ʵ�����������������
	
	private int NumberInOneLine;
	private int LineNumber;
	
	private int width;
	private int height;
	
	private int peopleNumber = 0;  // ��¼��ǰ�༭״̬�м�����ұ������ȥ
	
	
	public CustomMap()  throws IOException {
		
		setTitle("Map");
		NumberInOneLine=createCustomMap.col;
		LineNumber=createCustomMap.row;
		maps = new int[LineNumber + 2][NumberInOneLine + 2];
		for(int i=0;i<LineNumber + 2;i++) {
			for(int j=0;j<NumberInOneLine + 2;j++) {
				maps[i][j]=4;    // ���ɵķ���Ĭ���� 4����outspace��������ͨ���ķ���
			}
		}
		
		width = NumberInOneLine * BLOCK_WIDTH;
		height = LineNumber * BLOCK_HEIGHT;
		
		setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width+100, height);
		
		
		/*��ͼ���õ�ͬһ��ͼƬ��һ���*/    
		iconList[5] = new ImageIcon(tempPeople);
		iconList[7] = new ImageIcon(tempPeople);
	
		for (int i = 0; i < iconList.length; i++) {
			cbox.addItem(iconList[i]);   // ���б���ﰴ˳����Ӷ���
		}
		cbox.setSelectedIndex(0);
		cbox.setBounds(MenuBegin.panel2.getWidth()-60, 0, 60, 50);
		
		
		MenuBegin.panel2.add(cbox);
		
		pan.setBounds(MenuBegin.panel2.getWidth() / 2 - width /2, MenuBegin.panel2.getHeight() / 2 - height / 2, width, height);
		pan.setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		MenuBegin.panel2.add(pan);
		pan.addMouseListener(plis);  // ��ӹ�����������panel�ϵ��¼�����
		
		pan.setVisible(true);
		pan.repaint();
	}
  	
	
 
	/*�����������paint������ʵ�ֻ��Ƶ�ͼ��Ч��*/
	class MyMapPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			Image image = null;
			super.paint(g);  // ����̳У���Ȼԭ�еķ�������ʵ�֣���Щ�Ѳ���Ĳ����Ͳ��ܻ��Ƴ����ˣ�Ӧ��
			for (int line = 0; line<LineNumber; line++) {
				for(int list = 0; list<NumberInOneLine; list++) {
					image = getImage(maps[line+1][list+1]);
				    g.drawImage(image, list * BLOCK_WIDTH, line * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT,null);
				}
			}
		}
	}
	
	/*������¼��������룬��ȡ�������λ�õ����꣬���Է���Ŀ�(��)����ȷ����maps���鼸�м��У�Ȼ�������
	 * �������쳣����*/
	class PanelListenner extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			try {
				int j = e.getX() / BLOCK_WIDTH + 1;
				int i = e.getY() / BLOCK_HEIGHT + 1;
				int index = cbox.getSelectedIndex();  // ��ȡ������ǰ��ѡ�ķ���
				// �·���if��Ϊ�˴�����ԭ����������
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
				pan.repaint();  // ���»���һ�飬�͵õ������
			}catch(Exception ee) {
				JOptionPane.showMessageDialog(null, "ֻ����һ�������ӵ����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				peopleNumber = peopleNumber - 1;
			}
			
			
		}
	}
}