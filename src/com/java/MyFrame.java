package com.java;




import java.awt.Color;

import java.awt.GridLayout;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class MyFrame{
	Vector<JCheckBox> vj = new Vector<JCheckBox>();
	Listener l;
	JFrame f ;
	JScrollPane jsp1; // 可以滚动的面板
	JTextField jt1;
	JTextField jcx1;
	JTextField jcx2;//文件名
	JTextField jcx3;//扩展名
	JTextField jcx4;//编号
	JTextField jcx5;//时期顺序改名
	JTextField jcx6;//时期顺序改名
	JPanel jp1;//左部分
	JPanel jp2;//右部分
	JPanel jp3;//列出查找出的文件
	JLabel jl1;
	JLabel jl2;
	JLabel jl3;
	JLabel jl4;
	JLabel jl5;
	JLabel jl6;
	JLabel jl7;
	JLabel jl8;
	JLabel jbz1;
	JLabel jbz2;
	JLabel jbz3;
	JCheckBox jc1;
	JCheckBox jlc1;
	JCheckBox jlc2;
	JCheckBox jlc3;
	JCheckBox jlc4;
	JButton jb1;
	JButton jb2;
	JButton jb3; // "改名"
	JButton jb4; // "去掉多余的字符串"
	JCheckBox jc9 ;
	public void addFils(JCheckBox file){
		vj.addElement(file);
		jp3.add(file);
		jp3.doLayout();
		jp3.setSize(300, 800);
		
	}
	public MyFrame() {
		l = new Listener(this);
		f = new JFrame(); // 整个框架
		jt1 = new JTextField();
		jcx1 = new JTextField(null);
		jcx2 = new JTextField();
		jcx3 = new JTextField();
		jcx4 = new JTextField();
		jcx5 = new JTextField();
		jcx6 = new JTextField();
		jp1 = new JPanel(); // 左侧的面板：直达路径、提示、文件列表显示
		jp2 = new JPanel(); // 右侧的面板：改名相关操作
		jp3 = new JPanel();
		jl1 = new JLabel("直达路径");
		jl2 = new JLabel("搜索");
		jl4 = new JLabel("改名");
		jl3 = new JLabel("要搜索的文件");
		jl5 = new JLabel();
		jl6 = new JLabel();
		jl7 = new JLabel();
		jl8 = new JLabel();
		jbz1 = new JLabel("提示：直达路径输入格式例如\"D:\\1\\\"");
		jbz2 = new JLabel("提示：扩展名格式例如\".txt\"");
		jbz3 = new JLabel("提示：编号请输入数字");
		jb1 = new JButton("当前文件夹查询   ");
		jb2 = new JButton("所有子文件夹查询");
		jb3 = new JButton("改名");
		jb4 = new JButton("去掉多余的字符串");
		jc1 = new JCheckBox();
		jlc1 = new JCheckBox("文件名");
		jlc2 = new JCheckBox("扩展名");
		jlc3 = new JCheckBox("编号");
		jlc4 = new JCheckBox("以当前日期顺序改名");
		jsp1 = new JScrollPane();
		//jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
		//jsp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//窗口关闭结束程序
		f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		f.setResizable(false);//窗口不可改变大小
		
		
		f.setLayout(new GridLayout(1,2));
		f.setSize(500, 600);
		
		f.setLocation(400, 100);
		f.setTitle("文件批量处理软件");
		f.add(jp1);
		f.add(jp2);
		jp1.setLayout(null);
		jp1.add(jl1);
		jp1.add(jt1);
		jp1.add(jbz1);
		jbz1.setBounds(10, 40, 260, 40);
		jl1.setBounds(10,5, 80, 40);
		jt1.setBounds(80,10, 160, 30);
		jp1.add(jsp1);
		//设置panel的外观和布局
		jp3.setBorder(BorderFactory.createLineBorder(Color.black));  
        jp3.setLayout(new BoxLayout(jp3,BoxLayout.Y_AXIS));

		jsp1.setBounds(10, 85, 230, 470);
		jsp1.setViewportView(jp3);
		
		jl1.setVisible(true);
		
		
		jp2.setLayout(null);
		jcx1.setBounds(100,40,120, 30);
		jp2.add(jcx1);
		jp2.add(jl2);
		jl2.setBounds(10,10, 30, 30);
		jp2.add(jl3);
		jl3.setBounds(10,40, 80, 30);
		jp2.add(jb1);
		jb1.setBounds(50,80,140, 30);
		jp2.add(jb2);
		jb2.setBounds(50,120,140, 30);
		jp2.add(jl4);
		jl4.setBounds(10,150, 30, 30);
		
		jp2.add(jlc1);
		jlc1.setBounds(10, 190, 80, 30);
		jp2.add(jlc2);
		jlc2.setBounds(10, 230, 80, 30);
		jlc2.setEnabled(false);
		jp2.add(jlc3);
		jp2.add(jbz2);
		jp2.add(jbz3);
		jbz2.setBounds(10, 390, 200, 40);
		jbz3.setBounds(10, 420, 200, 40);
		jlc3.setBounds(10, 270, 80, 30);
		jlc3.setEnabled(false);
		jp2.add(jlc4);
		jlc4.setBounds(10, 310, 150, 30);
		
		
		jp2.add(jcx2);
		jcx2.setBounds(100, 190,130, 30);
		jp2.add(jcx3);
		jcx3.setBounds(100, 230,130, 30);
		jp2.add(jcx4);
		jcx4.setBounds(100, 270,130, 30);
		
		jp2.add(jcx6); // 添加：输入去掉字符串
		jcx6.setBounds(10, 480,220, 30);
		
		jp2.add(jb3);
		jb3.setBounds(70, 350,70, 30);
		
		jp2.add(jb4);
		jb4.setBounds(50, 520,140, 30);  // 添加：去掉字符串按钮
		
		jb1.addActionListener(l);
		jb2.addActionListener(l);
		jb3.addActionListener(l);
		jb4.addActionListener(l);
		jlc1.addActionListener(l);
		jlc4.addActionListener(l);
		f.setVisible(true);
	}
	public static void main(String []args){
		MyFrame f = new MyFrame();
	}
}
