package �������;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import �������.Mapping.ButtonListener;

/**
 * �Զ����ͼ��ʵ�֡�����㵽��ͼ��Ļ����б�����֣���ʱ��û�д�����Ӱ��ʹ�ã��ٵ����Լ�����
 * @author �����201906150323
 *
 */

public class Mapping extends variableSetting {
	
	private static final long serialVersionUID = 1L;
	
	
	MyMapPanel pan=new MyMapPanel();
	Container c=getContentPane();
	
	static int[][]maps;  // �洢��ͼ���ڹ��캯�����ٸ���ʵ�����������������
	
	private int[][]easymaps = {{4,4,4,4,4,4,4,4,4,4},
                               {4,4,4,0,0,0,4,4,4,4},   // ��ŵ�ͼ����
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
	
	/*��¼�ƶ���¼ ����10��������ʵ��*/
	public int record[][]=new int[100][2];  //��¼��ʮ�������� ÿ����һ���������ƶ��ķ��� 1234�ֱ����������� �ڶ����������Ƿ��ƶ������� 1Ϊ�ƶ�0Ϊû��
	public int step=0;

	private int NumberInOneLine;
	private int LineNumber;
	
	private int width;
	private int height;
	
	/*ȷ��Ҫ��ȡ�ĵ�ͼ*/
	static int target; 
	
	/*�ҵ���ҵ�����*/
	private int playerx;  
	private int playery;
	
	public Mapping()  throws IOException {
		
		
		setTitle("Map");
		// �ļ��ж�ȡ�����ݺ����еļ򵥡��еȡ����ѵ�ͼ�����ݷֿ�����
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
			// �����ļ���������ݣ���ʱmaps��������MenuBegin���Ѿ���ֵ���
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
		JButton btn5=new JButton();  //������  ÿ�����ɵ�ͼʱ����
		btn5.setIcon(ii);
		btn5.setContentAreaFilled(false);
		btn5.setFocusPainted(false);
		btn5.setBounds(-10,530, 80, 80);
		MenuBegin.panel2.add(btn5);
		PanelListener plis=new PanelListener();
		pan.addKeyListener(plis);  //������̼�����
		
		ButtonListener blis=new ButtonListener();
		btn5.addActionListener(blis);
		pan.setVisible(true);
		
		pan.requestFocus();  //��Ϊ����������Panel�ϵģ�����Ҫ�ѽ����ѡ�еİ�ťת����Panel��
		pan.repaint();
		
		/*�ҵ����λ������*/
		for(int i=1;i<maps.length-1;i++) {
			for(int j=1;j<maps[0].length-1;j++) {
				if(maps[i][j]==5||maps[i][j]==7) {
					playerx=i;
					playery=j;
				}
			}
		}
		
		/*��ʼ��������¼����*/
		for(int i=0;i<100;i++) {
			record[i][0]=-1;
			record[i][1]=0;
		}
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
					image = getImage(maps[line + 1][list + 1]);
				    g.drawImage(image, list * BLOCK_WIDTH, line * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT,null);
				}
			}
		}
	}
	
	/*����ܷ��ƶ�*/
	Boolean check(int position) {
		//�����������������1.ǰ���ǵ�ͼ�߽� 2.ǰ����ǽ 3.ǰ����������ǰ���ǰ����ǽ��߽������
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
	
	/*�ƶ� �޸��ƶ�������� positionΪ���� 1234�ֱ�Ϊ�������� ��ͬ��������в�ͬ�ĵ�ͼ�����޸�*/
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
	
	/*����record����*/
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
	
	/*����һ�� ���»��Ƶ�ͼ �߼���Ϊ������*/
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
	
	/*�ж����������Ƿ񶼵�λ��*/
	boolean is_success() {
		for(int i=1;i<maps.length-1;i++) {
			for(int j=1;j<maps[0].length-1;j++) {
				if (maps[i][j]==1)
					return false;
				}
			}
		return true;
	}
	
	/*������ϲ��ɱ��ص�ѡ���*/
	void success() {
		JOptionPane.showMessageDialog(null,"��ϲ��ɱ��أ���������"+step+"���ƶ�","��ϲ",JOptionPane.PLAIN_MESSAGE);
	}
	
	/*�����û��������� ��������Ӧ���� awsdҲ�ɿ���*/
	public class PanelListener extends KeyAdapter {
		
		// ����������
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();  //��ȡ���볣��
			switch (code) {
			case KeyEvent.VK_UP: case 87:  //������ϼ���w���� �ж��ܷ������ƶ� ���� ������ƶ����ƶ���¼
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
			pan.repaint();  //ÿ�μ�⵽����ָ�Ҫ���»��Ƶ�ͼ
			if(is_success())
				success();   //ÿ�μ�⵽����ָ�Ҫ�ж��Ƿ��Ѿ����� �����򵯳���Ӧ����
		}
	}
	
	/*������������ �����������ִ��һ������*/
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			switch(record[step%100][0]){
				case 1:
					back(1);  //����
					record[step%100][0]=-1;	  //�������һ����¼���	
					step--;  //��������1
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
			pan.requestFocus();  //������Ӱ�ť���ص�panel��
			pan.repaint();
		}
	}
}