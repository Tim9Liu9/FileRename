package com.java;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JFrame;








public class Listener implements ActionListener{
	boolean isexist = false;
	static String filegs = null;//�����ļ�������
	String filewj;
	MyFrame f;
	public Listener(MyFrame f){
		this.f = f;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String file1;//ֱ��·��
		file1 = f.jt1.getText();//�õ�ֱ��·��
		filewj = f.jcx1.getText();//�õ������������ļ����ļ���ʽ
		File file = new File(file1);
		if(e.getSource()==f.jb1){
			f.jp3.removeAll();
			f.vj.removeAllElements();
			if(filewj.equals("")){//����ǿ� ֱ�ӱ���  ��Ϊ�������ļ�����Ҫ�������ļ�
				filegs = null;
				isexist = true;
			}else{
				filegs = filewj;
				isexist = false;
			}
			//javax.swing.JOptionPane.showMessageDialog(null, "�����·��");
			fileFind(file);//���������ļ�
			if(!isexist){
				f.jp3.removeAll();
				f.vj.removeAllElements();
				f.jp3.repaint();
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "δ�ҵ�Ҫ�������ļ�");	
			}
		}
		if(e.getSource()==f.jb2){
			f.jp3.removeAll();
			f.vj.removeAllElements();
			if(filewj.equals("")){
				filegs = null;
				isexist = true;
			}else{
				filegs = filewj;
				isexist = false;
			}
			fileFindAll(file);
			if(!isexist){
				f.jp3.removeAll();
				f.vj.removeAllElements();
				f.jp3.repaint();
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "δ�ҵ�Ҫ�������ļ�");
				
			}
		}
		// ���� ��ť���¼�
		if(e.getSource()==f.jb3){
			int num = 0;
			//���˳�����ڸ�����ѡ��ѡ��
			if(f.jlc4.isSelected()){
				//�����Ÿ�ѡ��ѡ��
				if(f.jlc3.isSelected()){
					//�õ���ʼ���
					if(f.jcx4.getText().equals("")){
						javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ����");
					}else{
						try{
							num = Integer.parseInt(f.jcx4.getText());
						
							for(int count=0;count<f.vj.size();count++){
								//�ж��ļ��б��ڵ��ļ��Ƿ�ѡ��
								if(f.vj.get(count).isSelected()){
									//����ʱ���������
									ReNameByTime(new File(f.vj.get(count).getText()), num);
									num++;
								}
							}
						}catch(Exception e1){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "�ļ��������������");
						}
					}
					//�����Ÿ�ѡ��û��ѡ��
				}else{
						for(int count=0;count<f.vj.size();count++){
						if(f.vj.get(count).isSelected()){
							//num��Ϊ���㿪ʼ
							ReNameByTime(new File(f.vj.get(count).getText()), num);
							num++;
						}
					}
				}
			}
			//����ļ���ѡ��ѡ��
			if(f.jlc1.isSelected()){
				String filer;
				//�����չ����ѡ��û��ѡ��
				if(!f.jlc2.isSelected()){
					//�����ź��ļ���ѡ�򶼱�ѡ��
					if(f.jlc1.isSelected()&&f.jlc3.isSelected()){
						if(f.jcx4.getText().equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ����");
						}else{
							try{
								num = Integer.parseInt(f.jcx4.getText());
								for(int count=0;count<f.vj.size();count++){		
									if(f.vj.get(count).isSelected()){
										//ȡ��Ҫ���ĺ���ļ���
										filer = f.jcx2.getText();
										ReName(new File(f.vj.get(count).getText()),filer,""+num,"");
										num++;
									}
								}
							}catch(Exception e1){
								javax.swing.JOptionPane.showMessageDialog(f.jp3, "�ļ��������������");
							}
						}

					}
					//�����Ÿ�ѡ��û��ѡ�к��ļ���ѡ��ѡ��
					if(f.jlc1.isSelected()&&!f.jlc3.isSelected()){
						for(int count=0;count<f.vj.size();count++){
							
							if(f.vj.get(count).isSelected()){
								//ȡ��Ҫ���ĺ���ļ�����
								filer = f.jcx2.getText();
								if(filer.equals("")){
									javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ�����");
								}else{
								//���ø�������
									ReName(new File(f.vj.get(count).getText()),filer,"","");
								}
							}
						}

					}
				//�����չ����ѡ��ѡ��
				}else{
					String file4;
					////�����ź��ļ���ѡ�򶼱�ѡ��
					if(f.jlc1.isSelected()&&f.jlc3.isSelected()){
						//�õ���ʼ���
						
						if(f.jcx4.getText().equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ����");
						}else{
							try{
								num = Integer.parseInt(f.jcx4.getText());
								file4 = f.jcx3.getText();
								if(file4.equals("")){
									javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ���չ��");
								}else{
									for(int count=0;count<f.vj.size();count++){			
										if(f.vj.get(count).isSelected()){
											//filer�õ�Ҫ��������ļ���
											filer = f.jcx2.getText();
											if(filer.equals("")){
												javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ�����");
											}else{
												//��������������
												ReName(new File(f.vj.get(count).getText()),filer,""+num,file4);
												num++;
											}
										}
									}
								}
							}catch(Exception e1){
								javax.swing.JOptionPane.showMessageDialog(f.jp3, "�ļ��������������");
							}
						
						//file4�õ�Ҫ�ı�����չ��
							
						}

					}
					//�����Ÿ�ѡ��û��ѡ�к��ļ���ѡ��ѡ��
					if(f.jlc1.isSelected()&&!f.jlc3.isSelected()){
						file4 = f.jcx3.getText();
						if(file4.equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ���չ��");
						}else{
						
							for(int count=0;count<f.vj.size();count++){
								
								if(f.vj.get(count).isSelected()){
									filer = f.jcx2.getText();
									if(filer.equals("")){
										javax.swing.JOptionPane.showMessageDialog(f.jp3, "�������ļ�����");
									}else{
										ReName(new File(f.vj.get(count).getText()),filer,"",file4);
									}
								}
							}
						}

					}
				}
			}
		}
		// ȥ����Ҫ���ַ�����
		if(e.getSource()==f.jb4){
			String str = f.jcx6.getText();
			try{
				
			
				for(int count=0;count<f.vj.size();count++){
					//�ж��ļ��б��ڵ��ļ��Ƿ�ѡ��
					//����ʱ���������
//					ReNameByTime(new File(f.vj.get(count).getText()), 1);
//					ReName(new File(f.vj.get(count).getText()),filer,"",file4);
					ReNameDeleteStr(new File(f.vj.get(count).getText()),str);
				}
				
				//  ����ˢ��һ���ļ��б�
				f.jp3.removeAll();
				f.vj.removeAllElements();
				if(filewj.equals("")){//����ǿ� ֱ�ӱ���  ��Ϊ�������ļ�����Ҫ�������ļ�
					filegs = null;
					isexist = true;
				}else{
					filegs = filewj;
					isexist = false;
				}
				//javax.swing.JOptionPane.showMessageDialog(null, "�����·��");
				fileFind(file);//���������ļ�
				if(!isexist){
					f.jp3.removeAll();
					f.vj.removeAllElements();
					f.jp3.repaint();
					javax.swing.JOptionPane.showMessageDialog(f.jp3, "δ�ҵ�Ҫ�������ļ�");	
				}
				
			}catch(Exception e1){
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "�ļ��������������");
			}
			
			
		}
		
		
		if(e.getSource()==f.jlc1){
			//����ť������
			if(f.jlc1.isSelected()){
				//f.jlc2.setSelected(true);
				f.jlc2.setEnabled(true);
				f.jlc3.setEnabled(true);
				f.jlc4.setEnabled(false);
			}else{
				f.jlc2.setSelected(false);
				f.jlc2.setEnabled(false);
				f.jlc3.setEnabled(false);
				f.jlc3.setSelected(false);
				f.jlc4.setEnabled(true);
				f.jcx2.setText("");
				f.jcx3.setText("");
				f.jcx4.setText("");
			}
		}
		if(e.getSource()==f.jlc4){
			if(f.jlc4.isSelected()){
				f.jlc1.setEnabled(false);
				f.jlc3.setEnabled(true);
			}else{
				f.jlc1.setEnabled(true);
				f.jlc3.setEnabled(false);
				f.jlc3.setSelected(false);
				f.jcx2.setText("");
				f.jcx3.setText("");
				f.jcx4.setText("");
			}
		}
	}
	//�޹�������ļ� �ҳ��ļ���Ϣ
	public void filergodic(File file){
		
		File []nameList;
		JCheckBox jb11 = new JCheckBox();
		nameList = file.listFiles();//�õ���ǰ�ļ��е������ļ����ļ���
		if(nameList!=null&&nameList.length>0) {
			for(int j=0;j<(nameList.length);j++){
				if(!nameList[j].isDirectory()){//�ж��Ƿ����ļ���
					//f.vj.addElement();
					jb11 = new JCheckBox(""+nameList[j].toString());
					f.addFils(jb11);
				
				}
			}
		}
		
		
	}
	//����������ļ� �ҳ��ļ���Ϣ
	public void filergodics(File file,int di,String filegs){
		Myfilenamefileter mf = new Myfilenamefileter();//����һ��������
		File []nameList;
		int  num,lnum;//.�����ַ�����λ��     \\
		String newfile;
		JCheckBox jb11 = new JCheckBox();
		if(di==-1){//di����-1ʱ ��ʾ ��ǰ׺���ļ���ʱ��  
			nameList = file.listFiles();
			if(nameList!=null&&nameList.length>0) {
				for(int j=0;j<(nameList.length);j++){
					if(!nameList[j].isDirectory()){
						
							lnum = nameList[j].toString().lastIndexOf("\\");
							newfile= nameList[j].toString().substring(lnum+1);
							//System.out.println(newfile);
							if(newfile.indexOf(filegs)!=-1){
								jb11 = new JCheckBox(""+nameList[j].toString());
								f.addFils(jb11);
								isexist = true;
							}
					}
				}
			}
		}else{//ֻ�ǲ��Һ�׺���ϱ�׼���ļ�
			int wz;
			wz = filegs.indexOf(".");
			if(wz==0){
				mf.setFilegs(filegs);
				nameList = file.listFiles(mf);
				if(nameList!=null&&nameList.length>0) {
					for(int j=0;j<(nameList.length);j++){
						if(!nameList[j].isDirectory()){
							jb11 = new JCheckBox(""+nameList[j].toString());
							f.addFils(jb11);
							isexist = true;
						}
					}
				}
			}else{
				nameList = file.listFiles();
				if(nameList!=null&&nameList.length>0) {
					for(int j=0;j<(nameList.length);j++){
						if(!nameList[j].isDirectory()){
							
								lnum = nameList[j].toString().lastIndexOf("\\");
								newfile= nameList[j].toString().substring(lnum+1);
								//System.out.println(newfile);
								if(newfile.equals(filegs)){
									jb11 = new JCheckBox(""+nameList[j].toString());
									f.addFils(jb11);
									isexist = true;
									
								}
						}
					}
				}
			}
		}
	}
	//�ҵ����ļ��������ļ�
	public void fileFindAll(File file){
		
		int di = 0;//di��Ϊ�˰���������ʱ �Ƿ����к�׺��
		
		if(filegs==null){
			filergodic(file);
			if(file.isDirectory()) {
				File[] k = file.listFiles();
				if(k!=null&&k.length>0) {
					for(int i=0;i<k.length;i++) {
						File temp = k[i];
						fileFindAll(temp);
					}
				}
			}
		}else{
			di = filegs.indexOf(".");
			filergodics(file,di,filegs);
				if(file.isDirectory()) {
					File[] k = file.listFiles();
					if(k!=null&&k.length>0) {
						
						for(int i=0;i<k.length;i++) {
							
							File temp = k[i];
							fileFindAll(temp);
						}
					}
				}
			}
		}


	//�ҵ��ļ��������ļ�
	public void fileFind(File file){
		
		int di;
		
		if(filegs==null){
			filergodic(file);
		}else{
			di = filegs.indexOf(".");
			filergodics(file, di, filegs);
		}
	}
	
	
	public static void ReName(File file,String filer,String bh,String file4){
		
		String file3;
		file3 = file.toString();
		int fil,fil3;
		File newFile;
		//
		fil = file.toString().lastIndexOf("\\");//���\\��λ��
		fil3 =file3.indexOf(".");//���.��λ��
		
		String fil2,fil4;
		if(file4.equals("")){
			if(file.toString().indexOf(".")==-1){
				fil2 = file3.substring(0,fil);
				fil4="";
			}else{
				fil4 = file3.substring(fil3);//��ȡ����׺��
				fil2 = file3.substring(0,fil);//��ȡ���ļ���ַ
			}
			
		
			if(bh.equals("")){
				newFile = new File(fil2+"\\"+filer+bh+fil4);
			}else{
				newFile = new File(fil2+"\\"+filer+"("+bh+")"+fil4);//bh����������
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"�ļ��Ѵ��ڣ�����������Ϊ������");
			}else{
				file.renameTo(newFile);//������
			}
		}else{
			fil2 = file3.substring(0,fil);//��ȡ���ļ���ַ
			if(bh.equals("")){
				newFile = new File(fil2+"\\"+filer+bh+file4);
			}else{
				newFile = new File(fil2+"\\"+filer+"("+bh+")"+file4);//bh����������
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"�ļ��Ѵ��ڣ�����������Ϊ������");
			}else{
				file.renameTo(newFile);//������
			}
		}
		
	}
	
	public static void ReNameByTime(File file,int i ){
		String s;
		String file3;
		Calendar now = Calendar.getInstance();
		s = new SimpleDateFormat("yyyy��MM��dd��").format(now.getTime());//�õ���ǰ����
		file3 = file.toString();//��������ļ�תΪ�ַ���
		int fil,fil3;
		String fil2,fil4;
		fil = file.toString().lastIndexOf("\\");//���\\��λ��
		fil3 =file3.indexOf(".");//���.��λ��
		if(file.toString().indexOf(".")==-1){
			//D:\1\1.txt
			fil2 = file3.substring(0,fil);//��ȡ��Ŀ¼��ַ
			fil4="";
		}else{
			fil4 = file3.substring(fil3);//��ȡ��׺��
			fil2 = file3.substring(0,fil);//��Ŀ¼��ַ
		}
		
		
		File newFile = new File(fil2+"\\"+s+"("+i+")"+fil4);
		if(!newFile.exists()){
			file.renameTo(newFile);//������
		}else{
			javax.swing.JOptionPane.showMessageDialog(null,"�ļ��Ѵ��ڣ�����������Ϊ������");
		}
	}
	// ȥ����Ҫ���ַ���
	public static void ReNameDeleteStr(File file,String deleteStr ){
		String s = null;
		String file3;
//		Calendar now = Calendar.getInstance();
//		s = new SimpleDateFormat("yyyy��MM��dd��").format(now.getTime());//�õ���ǰ����
		file3 = file.toString();//��������ļ�תΪ�ַ���
		System.out.println("file3=" + file3);
		int fil,fil3;
		String fil2,fil4;
		fil = file3.lastIndexOf("\\");//���\\��λ��
		//fil3 =file3.indexOf(".");//���.��λ��
		fil3 =file3.lastIndexOf(".");//���.��λ��
		if(file.toString().indexOf(".")==-1){
			//D:\1\1.txt
			fil2 = file3.substring(0,fil);//��ȡ��Ŀ¼��ַ
			fil4="";
		}else{
			fil4 = file3.substring(fil3);//��ȡ��׺��
			fil2 = file3.substring(0,fil);//��Ŀ¼��ַ
			s = file3.substring(fil+1, file3.length());
			System.out.println("s=" + s);
		}
		// �ַ���ƥ�䣺
		/*if(s.startsWith(deleteStr) && !"".equals(deleteStr))
		{
			s = s.replaceAll(deleteStr, "");
			System.out.println("�滻��ģ�s=" + s);
			File newFile = new File(fil2+"\\"+s);
			System.out.println("newFile=" + newFile.getName());
			
			if(!newFile.exists()){
				file.renameTo(newFile);//������
			}else{
				javax.swing.JOptionPane.showMessageDialog(null,"�ļ��Ѵ��ڣ�����������Ϊ������");
			}
		}else */
		if( s.contains(deleteStr) && !"".equals(deleteStr))
		{
			// ע�������ַ�:������ʽ����������ַ��������д���
			deleteStr = deleteStr.replaceAll("\\[","\\\\[");
			deleteStr = deleteStr.replaceAll("\\]","\\\\]");   
			deleteStr = deleteStr.replaceAll("\\.","\\\\.");   
			System.out.println("2�滻��ģ�deleteStr=" + deleteStr);    	
			
			s = s.replaceAll(deleteStr,"");
			
			System.out.println("2�滻��ģ�s=" + s);
			File newFile = new File(fil2+"\\"+s);
			System.out.println("2newFile=" + newFile.getName());
			
			if(!newFile.exists()){
				file.renameTo(newFile);//������
			}else{
				javax.swing.JOptionPane.showMessageDialog(null,"�ļ��Ѵ��ڣ�����������Ϊ������");
			}
		}
		
		
		
	}
	
	
}
class Myfilenamefileter implements FilenameFilter{
	String filegs;
	
	public void setFilegs(String filegs) {
		this.filegs = filegs;
	}

	@Override
	public boolean accept(File dir, String name) {
		
		return name.endsWith(filegs);//||new File(name).isDirectory();
	}
	
}
