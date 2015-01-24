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
	static String filegs = null;//搜索文件的名称
	String filewj;
	MyFrame f;
	public Listener(MyFrame f){
		this.f = f;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String file1;//直达路径
		file1 = f.jt1.getText();//得到直达路径
		filewj = f.jcx1.getText();//得到按规则搜索文件的文件格式
		File file = new File(file1);
		if(e.getSource()==f.jb1){
			f.jp3.removeAll();
			f.vj.removeAllElements();
			if(filewj.equals("")){//如果是空 直接遍历  不为空则按照文件搜索要求搜索文件
				filegs = null;
				isexist = true;
			}else{
				filegs = filewj;
				isexist = false;
			}
			//javax.swing.JOptionPane.showMessageDialog(null, "请输出路径");
			fileFind(file);//遍历搜索文件
			if(!isexist){
				f.jp3.removeAll();
				f.vj.removeAllElements();
				f.jp3.repaint();
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "未找到要搜索的文件");	
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
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "未找到要搜索的文件");
				
			}
		}
		// 改名 按钮的事件
		if(e.getSource()==f.jb3){
			int num = 0;
			//如果顺序日期改名复选框被选中
			if(f.jlc4.isSelected()){
				//如果编号复选框被选中
				if(f.jlc3.isSelected()){
					//得到起始编号
					if(f.jcx4.getText().equals("")){
						javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件编号");
					}else{
						try{
							num = Integer.parseInt(f.jcx4.getText());
						
							for(int count=0;count<f.vj.size();count++){
								//判断文件列表内的文件是否被选中
								if(f.vj.get(count).isSelected()){
									//调用时间改名函数
									ReNameByTime(new File(f.vj.get(count).getText()), num);
									num++;
								}
							}
						}catch(Exception e1){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "文件编号请输入数字");
						}
					}
					//如果编号复选框没被选中
				}else{
						for(int count=0;count<f.vj.size();count++){
						if(f.vj.get(count).isSelected()){
							//num算为从零开始
							ReNameByTime(new File(f.vj.get(count).getText()), num);
							num++;
						}
					}
				}
			}
			//如果文件复选框被选中
			if(f.jlc1.isSelected()){
				String filer;
				//如果扩展名复选框没被选中
				if(!f.jlc2.isSelected()){
					//如果编号和文件复选框都被选中
					if(f.jlc1.isSelected()&&f.jlc3.isSelected()){
						if(f.jcx4.getText().equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件编号");
						}else{
							try{
								num = Integer.parseInt(f.jcx4.getText());
								for(int count=0;count<f.vj.size();count++){		
									if(f.vj.get(count).isSelected()){
										//取到要更改后的文件名
										filer = f.jcx2.getText();
										ReName(new File(f.vj.get(count).getText()),filer,""+num,"");
										num++;
									}
								}
							}catch(Exception e1){
								javax.swing.JOptionPane.showMessageDialog(f.jp3, "文件编号请输入数字");
							}
						}

					}
					//如果编号复选框没被选中和文件复选框被选中
					if(f.jlc1.isSelected()&&!f.jlc3.isSelected()){
						for(int count=0;count<f.vj.size();count++){
							
							if(f.vj.get(count).isSelected()){
								//取到要更改后的文件名称
								filer = f.jcx2.getText();
								if(filer.equals("")){
									javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件名称");
								}else{
								//调用改名函数
									ReName(new File(f.vj.get(count).getText()),filer,"","");
								}
							}
						}

					}
				//如果扩展名复选框被选中
				}else{
					String file4;
					////如果编号和文件复选框都被选中
					if(f.jlc1.isSelected()&&f.jlc3.isSelected()){
						//得到起始编号
						
						if(f.jcx4.getText().equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件编号");
						}else{
							try{
								num = Integer.parseInt(f.jcx4.getText());
								file4 = f.jcx3.getText();
								if(file4.equals("")){
									javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件扩展名");
								}else{
									for(int count=0;count<f.vj.size();count++){			
										if(f.vj.get(count).isSelected()){
											//filer得到要改名后的文件名
											filer = f.jcx2.getText();
											if(filer.equals("")){
												javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件名称");
											}else{
												//调用重命名函数
												ReName(new File(f.vj.get(count).getText()),filer,""+num,file4);
												num++;
											}
										}
									}
								}
							}catch(Exception e1){
								javax.swing.JOptionPane.showMessageDialog(f.jp3, "文件编号请输入数字");
							}
						
						//file4得到要改变后的扩展名
							
						}

					}
					//如果编号复选框没被选中和文件复选框被选中
					if(f.jlc1.isSelected()&&!f.jlc3.isSelected()){
						file4 = f.jcx3.getText();
						if(file4.equals("")){
							javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件扩展名");
						}else{
						
							for(int count=0;count<f.vj.size();count++){
								
								if(f.vj.get(count).isSelected()){
									filer = f.jcx2.getText();
									if(filer.equals("")){
										javax.swing.JOptionPane.showMessageDialog(f.jp3, "请输入文件名称");
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
		// 去掉不要的字符串：
		if(e.getSource()==f.jb4){
			String str = f.jcx6.getText();
			try{
				
			
				for(int count=0;count<f.vj.size();count++){
					//判断文件列表内的文件是否被选中
					//调用时间改名函数
//					ReNameByTime(new File(f.vj.get(count).getText()), 1);
//					ReName(new File(f.vj.get(count).getText()),filer,"",file4);
					ReNameDeleteStr(new File(f.vj.get(count).getText()),str);
				}
				
				//  重新刷新一下文件列表
				f.jp3.removeAll();
				f.vj.removeAllElements();
				if(filewj.equals("")){//如果是空 直接遍历  不为空则按照文件搜索要求搜索文件
					filegs = null;
					isexist = true;
				}else{
					filegs = filewj;
					isexist = false;
				}
				//javax.swing.JOptionPane.showMessageDialog(null, "请输出路径");
				fileFind(file);//遍历搜索文件
				if(!isexist){
					f.jp3.removeAll();
					f.vj.removeAllElements();
					f.jp3.repaint();
					javax.swing.JOptionPane.showMessageDialog(f.jp3, "未找到要搜索的文件");	
				}
				
			}catch(Exception e1){
				javax.swing.JOptionPane.showMessageDialog(f.jp3, "文件编号请输入数字");
			}
			
			
		}
		
		
		if(e.getSource()==f.jlc1){
			//处理按钮的亮灭
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
	//无规则遍历文件 找出文件信息
	public void filergodic(File file){
		
		File []nameList;
		JCheckBox jb11 = new JCheckBox();
		nameList = file.listFiles();//得到当前文件夹的所有文件和文件夹
		if(nameList!=null&&nameList.length>0) {
			for(int j=0;j<(nameList.length);j++){
				if(!nameList[j].isDirectory()){//判断是否是文件夹
					//f.vj.addElement();
					jb11 = new JCheckBox(""+nameList[j].toString());
					f.addFils(jb11);
				
				}
			}
		}
		
		
	}
	//按规则遍历文件 找出文件信息
	public void filergodics(File file,int di,String filegs){
		Myfilenamefileter mf = new Myfilenamefileter();//创建一个条件类
		File []nameList;
		int  num,lnum;//.的在字符串的位置     \\
		String newfile;
		JCheckBox jb11 = new JCheckBox();
		if(di==-1){//di等于-1时 表示 有前缀的文件的时候  
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
		}else{//只是查找后缀符合标准的文件
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
	//找到子文件夹所有文件
	public void fileFindAll(File file){
		
		int di = 0;//di是为了按规则搜索时 是否是有后缀的
		
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


	//找到文件夹所有文件
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
		fil = file.toString().lastIndexOf("\\");//存放\\的位置
		fil3 =file3.indexOf(".");//存放.的位置
		
		String fil2,fil4;
		if(file4.equals("")){
			if(file.toString().indexOf(".")==-1){
				fil2 = file3.substring(0,fil);
				fil4="";
			}else{
				fil4 = file3.substring(fil3);//截取到后缀名
				fil2 = file3.substring(0,fil);//截取到文件地址
			}
			
		
			if(bh.equals("")){
				newFile = new File(fil2+"\\"+filer+bh+fil4);
			}else{
				newFile = new File(fil2+"\\"+filer+"("+bh+")"+fil4);//bh是命名规则
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
			}else{
				file.renameTo(newFile);//重命名
			}
		}else{
			fil2 = file3.substring(0,fil);//截取到文件地址
			if(bh.equals("")){
				newFile = new File(fil2+"\\"+filer+bh+file4);
			}else{
				newFile = new File(fil2+"\\"+filer+"("+bh+")"+file4);//bh是命名规则
			}
			if(newFile.exists()){
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
			}else{
				file.renameTo(newFile);//重命名
			}
		}
		
	}
	
	public static void ReNameByTime(File file,int i ){
		String s;
		String file3;
		Calendar now = Calendar.getInstance();
		s = new SimpleDateFormat("yyyy年MM月dd日").format(now.getTime());//得到当前日期
		file3 = file.toString();//将传入的文件转为字符串
		int fil,fil3;
		String fil2,fil4;
		fil = file.toString().lastIndexOf("\\");//存放\\的位置
		fil3 =file3.indexOf(".");//存放.的位置
		if(file.toString().indexOf(".")==-1){
			//D:\1\1.txt
			fil2 = file3.substring(0,fil);//截取父目录地址
			fil4="";
		}else{
			fil4 = file3.substring(fil3);//截取后缀名
			fil2 = file3.substring(0,fil);//父目录地址
		}
		
		
		File newFile = new File(fil2+"\\"+s+"("+i+")"+fil4);
		if(!newFile.exists()){
			file.renameTo(newFile);//重命名
		}else{
			javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
		}
	}
	// 去掉不要的字符串
	public static void ReNameDeleteStr(File file,String deleteStr ){
		String s = null;
		String file3;
//		Calendar now = Calendar.getInstance();
//		s = new SimpleDateFormat("yyyy年MM月dd日").format(now.getTime());//得到当前日期
		file3 = file.toString();//将传入的文件转为字符串
		System.out.println("file3=" + file3);
		int fil,fil3;
		String fil2,fil4;
		fil = file3.lastIndexOf("\\");//存放\\的位置
		//fil3 =file3.indexOf(".");//存放.的位置
		fil3 =file3.lastIndexOf(".");//存放.的位置
		if(file.toString().indexOf(".")==-1){
			//D:\1\1.txt
			fil2 = file3.substring(0,fil);//截取父目录地址
			fil4="";
		}else{
			fil4 = file3.substring(fil3);//截取后缀名
			fil2 = file3.substring(0,fil);//父目录地址
			s = file3.substring(fil+1, file3.length());
			System.out.println("s=" + s);
		}
		// 字符串匹配：
		/*if(s.startsWith(deleteStr) && !"".equals(deleteStr))
		{
			s = s.replaceAll(deleteStr, "");
			System.out.println("替换后的：s=" + s);
			File newFile = new File(fil2+"\\"+s);
			System.out.println("newFile=" + newFile.getName());
			
			if(!newFile.exists()){
				file.renameTo(newFile);//重命名
			}else{
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
			}
		}else */
		if( s.contains(deleteStr) && !"".equals(deleteStr))
		{
			// 注意特殊字符:正则表达式里面的特殊字符串，进行处理
			deleteStr = deleteStr.replaceAll("\\[","\\\\[");
			deleteStr = deleteStr.replaceAll("\\]","\\\\]");   
			deleteStr = deleteStr.replaceAll("\\.","\\\\.");   
			System.out.println("2替换后的：deleteStr=" + deleteStr);    	
			
			s = s.replaceAll(deleteStr,"");
			
			System.out.println("2替换后的：s=" + s);
			File newFile = new File(fil2+"\\"+s);
			System.out.println("2newFile=" + newFile.getName());
			
			if(!newFile.exists()){
				file.renameTo(newFile);//重命名
			}else{
				javax.swing.JOptionPane.showMessageDialog(null,"文件已存在，不能重命名为该名称");
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
