package cn.edu.zufe.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.*;

import processing.core.PApplet;
import cn.edu.zufe.io.*;

public class FrameMain extends JFrame implements ActionListener {
	public LinkedList wellList;
	static JMenuBar menubar;
	JMenu menu;
	JMenuItem miOpen, miSave, miExport, miExit;
	PAppletWellView pWellView;
	PAppletSC psc;

	public FrameMain(String s, int x, int y, int width, int height) {
		super(s);
		this.setSize(width, height);
		this.setLocation(x, y);
		this.setVisible(true);

		// 添加菜单项
		menubar = new JMenuBar();
		menu = new JMenu("File");
		miOpen = new JMenuItem("Open");
		miSave = new JMenuItem("Save");
		miExport = new JMenuItem("Export");
		miExit = new JMenuItem("Exit");
		menu.add(miOpen);
		menu.add(miSave);
		menu.addSeparator();
		menu.add(miExport);
		menu.add(miExit);
		menubar.add(menu);
		miOpen.addActionListener(this);
		miSave.addActionListener(this);
		this.setJMenuBar(menubar);
	}
	
//	private void addPApplet(FrameMain f, PApplet p) {
//		PAppletWellView p1 = new PAppletWellView(100,100,new PAppletSC(100,100));
//		p1.setPreferredSize(new Dimension(575, 625));
//		f.add((Component)p1);
//	}

	public static void main(String[] args) {
		// 设置为 windows 的界面风格
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 实例化主窗体
		FrameMain fMain = new FrameMain("AutoCompare", 260, 80, 800, 620);
		fMain.setLayout(new FlowLayout());
		fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingUtilities.updateComponentTreeUI(fMain);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == miOpen) {
			try {
				openFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == miSave) {
			System.out.println("Save");
		}
	}

	private void openFile() throws IOException {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setCurrentDirectory(new File("."));
		if (JFileChooser.APPROVE_OPTION == jfc.showDialog(new JLabel(), "选择")) {
			File file = jfc.getSelectedFile();
			if (file != null && file.isFile()) {
				// 打开文件后的处理
				System.out.println("文件:" + file.getAbsolutePath());
				System.out.println(jfc.getSelectedFile().getName());
				wellList = Data.loadData(file.getAbsolutePath());
			}
		}
	}
}
