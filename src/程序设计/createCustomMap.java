package �������;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

/**
 * ����ʵ������һ�����ڣ����������Զ����ͼ�Ĵ�С
 * �쳣�����Ѿ���ɣ������СҲ�Ѿ��趨���ˣ������ٸ�
 * ���������ɵ�ͼ�Ĵ�С���涨��3-20֮��
 * @author �����201906150323
 *
 */

public class createCustomMap extends variableSetting implements ActionListener{
	
	/* ���л�ʱΪ�˱��ְ汾�ļ����ԣ����ڰ汾����ʱ�����л��Ա��ֶ����Ψһ�ԡ�--���԰ٶ�
	 * ��������Ϊ�˼����ԣ���һ�ӣ����ܰѾ���ȥ�������ǻ���*/
	private static final long serialVersionUID = 1L; 
	
	
	Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Container c = getContentPane();
	
	private int width = 430;
	private int height = 180;
	
	/*������̬�������洢�Զ����ͼ�����У�������CustomMap��ʹ��*/
	static int col;
	static int row;
	
	JTextField []text = {new JTextField(),
                         new JTextField()};

	JLabel []lab = {new JLabel("�����ͼ������"),
                    new JLabel("�����ͼ������")};
	
	JButton but = new JButton("���ɵ�ͼ");

	public createCustomMap() throws IOException{
		setTitle("���õ�ͼ��С");
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
	
	/*������쳣����ֻ��������Ϲ淶���Ż����ɲ���ת���Զ����ͼ�ϣ����򵯿���ʾ����*/
	public void createBlankMap() {
		try{
			if ((Integer.valueOf(text[0].getText()) > 2 && Integer.valueOf(text[1].getText()) > 0) &&
			    (Integer.valueOf(text[0].getText()) <= 20 && Integer.valueOf(text[1].getText()) <= 32)){
				row = Integer.valueOf(text[0].getText());
				col = Integer.valueOf(text[1].getText());
				this.dispose();
				//�ṩ������ʾ���������ʹ���Զ����ͼ
				JOptionPane.showMessageDialog(null, "��ע�⣬��ͼһ��Ҫȷ���༭����ٱ��棬�ݲ�֧���ٴα༭��\n" +
						                            "�Լ��༭����������ڼ�ģʽ��ť�Ա��Աߣ��������ɷ��֣�\n" +
						                            "�����������ݰ�˳��ֱ�Ϊ��\n" +
						                            "* 0-ǽ�ڣ������ƶ������ɿ�Խ��\r\n" + 
						                            "* 1-���ӣ����ƶ������ɿ�Խ��\r\n" + 
						                            "* 2-Ŀ��㣨�����ƶ������ɿ�Խ��\r\n" + 
						                            "* 3-�ռ䣨�����ƶ����ɿ�Խ��\r\n" + 
						                            "* 4-�ⲿ�ռ䣨�����ƶ������ɿ�Խ��\r\n" + 
						                            "* 5-��ң���������\r\n" + 
						                            "* 6-����Ŀ�������ӣ����ӵ���Ŀ����ʹ�ô�ͼ��\r\n" + 
						                            "* 7-վ��Ŀ�����ˣ��˵�Ŀ���λ��ʱʹ�ô�ͼ��","ʹ����֪",JOptionPane.PLAIN_MESSAGE);
				new CustomMap();
			}
			else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			//�����������ʾ����
			JOptionPane.showMessageDialog(null, "�����������������(������3-20������)","����",JOptionPane.PLAIN_MESSAGE);
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
