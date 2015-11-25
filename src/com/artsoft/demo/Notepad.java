package com.artsoft.demo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Notepad extends JFrame {

	private static final long serialVersionUID = 2618931623973154200L;
	// 系统组件声明
	private JMenuBar menuBar = new JMenuBar();
	private JEditorPane content = new JEditorPane();
	private JScrollPane scroll = new JScrollPane(content);
	private JFileChooser filechooser = new JFileChooser();
	private BorderLayout bord = new BorderLayout();
	@SuppressWarnings("unused")
	private JLabel statusBar = new JLabel();
	private JPanel pane = new JPanel();
	private File file = null;

	// 定义文件菜单
	private JMenu fileMenu = new JMenu();
	private JMenuItem newMenuItem = new JMenuItem();
	private JMenuItem openMenuItem = new JMenuItem();
	private JMenuItem saveMenuItem = new JMenuItem();
	private JMenuItem saveAsMenuItem = new JMenuItem();
	private JMenuItem pageSetupMenuItem = new JMenuItem();
	private JMenuItem printMenuItem = new JMenuItem();
	private JMenuItem exitMenuItem = new JMenuItem();
	// 定义风格菜单
	private JMenu styleMenu = new JMenu();
	private ButtonGroup styleMenuGroup = new ButtonGroup();
	private JRadioButtonMenuItem javaStyleMenuItem = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem metalStyleMenuItem = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem windowsStyleMenuItem = new JRadioButtonMenuItem();

	// 定义帮助菜单
	private JMenuItem aboutMenuItem = new JMenuItem();
	private JMenuItem helpTopicMenuItem = new JMenuItem();
	private JMenu helpMenu = new JMenu();

	// 构造函数
	public Notepad() {
		initComponents();
	}

	private void initComponents() {

		// 添加子菜单项到文件菜单
		fileMenu.setText("\u6587\u4ef6 (F)");
		newMenuItem.setText(" 新建(N)    Ctrl+N");
		openMenuItem.setText(" 打开(O)...    Ctrl+O");
		saveMenuItem.setText(" 保存(S)    Ctrl+S");
		saveAsMenuItem.setText(" 另存为(A)...");
		pageSetupMenuItem.setText(" 页面设置(U)...");
		printMenuItem.setText(" 打印(P)...    Ctrl+P");
		exitMenuItem.setText(" 退出");
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(pageSetupMenuItem);
		fileMenu.add(printMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		// 添加子菜单项到风格菜单
		styleMenu.setText("风格(S)");
		javaStyleMenuItem.setText("Java默认");
		metalStyleMenuItem.setText("Metal风格");
		windowsStyleMenuItem.setText("Windows风格");
		styleMenuGroup.add(javaStyleMenuItem);
		styleMenuGroup.add(metalStyleMenuItem);
		styleMenuGroup.add(windowsStyleMenuItem);
		styleMenu.add(javaStyleMenuItem);
		styleMenu.add(metalStyleMenuItem);
		styleMenu.add(windowsStyleMenuItem);

		// 添加子菜单项到帮助菜单
		helpMenu.setText("帮助(H)");
		helpTopicMenuItem.setText(" 帮助主题(H)");
		aboutMenuItem.setText(" 关于记事本(A)");
		helpMenu.add(helpTopicMenuItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutMenuItem);

		// 定义文件菜单下的事件监听
		newMenuItem.addActionListener(new newMenuItem_actionAdapter(this));
		openMenuItem.addActionListener(new openMenuItem_actionAdapter(this));
		saveMenuItem.addActionListener(new saveMenuItem_actionAdapter(this));
		saveAsMenuItem
				.addActionListener(new saveAsMenuItem_actionAdapter(this));
		pageSetupMenuItem
				.addActionListener(new pageSetupMenuItem_actionAdapter(this));
		printMenuItem.addActionListener(new printMenuItem_actionAdapter(this));
		exitMenuItem.addActionListener(new exitMenuItem_actionAdapter(this));

		// 定义风格菜单下的事件监听
		javaStyleMenuItem
				.addActionListener(new javaStyleMenuItem_actionAdapter(this));
		metalStyleMenuItem
				.addActionListener(new metalStyleMenuItem_actionAdapter(this));
		windowsStyleMenuItem
				.addActionListener(new windowsStyleMenuItem_actionAdapter(this));

		// 定义帮助菜单下的事件监听
		helpTopicMenuItem
				.addActionListener(new helpTopicMenuItem_actionAdapter(this));
		aboutMenuItem.addActionListener(new aboutMenuItem_actionAdapter(this));

		// 填加菜单到菜单栏
		menuBar.add(fileMenu);
		menuBar.add(styleMenu);
		menuBar.add(helpMenu);

		// 对主窗口的一些设置
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("无标题 - \u8bb0\u4e8b\u672c");
		this.setSize(640, 480);

		setJMenuBar(menuBar);
		pane.setLayout(bord);
		pane.add("Center", scroll);
		setContentPane(pane);
	}

	// 定义新建菜单项方法
	public void newMenuItemActionPerformed(ActionEvent evt) {
		file = null;
		if (!("".equals(content.getText()))) {
			Object[] options = { " 是(Y) ", " 否(N) ", " 取消 " };
			int s = JOptionPane
					.showOptionDialog(
							null,
							"\u6587\u4ef6 "
									+ getTitle()
									+ " \u7684\u6587\u5b57\u5df2\u7ecf\u6539\u53d8\u3002/n\u60f3\u4fdd\u5b58\u6587\u4ef6\u5417\uff1f",
							"\u8bb0\u4e8b\u672c", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
			switch (s) {
			case 0:
				int returnVal = filechooser.showSaveDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = filechooser.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(file);
						fw.write(content.getText());
						setTitle(filechooser.getSelectedFile().getName()
								+ " - \u8bb0\u4e8b\u672c");
						fw.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			case 1:
				content.setText("");
				setTitle("无标题 - \u8bb0\u4e8b\u672c");
			}
		}
	}

	// 定义打开菜单项方法
	public void openMenuItemActionPerformed(ActionEvent evt) {
		try {
			file = null;
			int returnVal = filechooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = filechooser.getSelectedFile();
				FileReader fr = new FileReader(file);
				int len = (int) file.length();
				char[] buffer = new char[len];
				fr.read(buffer, 0, len);
				fr.close();
				content.setText(new String(buffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 定义退出菜单项方法
	public void exitMenuItem_actionPerformed(ActionEvent e) {
		if (!("".equals(content.getText()))) {
			Object[] options = { " 是(Y) ", " 否(N) ", " 取消 " };
			int s = JOptionPane.showOptionDialog(null, "文件的文字已经改变。/n想保存文件吗？",
					"\u8bb0\u4e8b\u672c", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			switch (s) {
			case 0:
				int returnVal = filechooser.showSaveDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = filechooser.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(file);
						fw.write(content.getText());
						setTitle(filechooser.getSelectedFile().getName()
								+ " - \u8bb0\u4e8b\u672c");
						fw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
			case 1:
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}

	// 保存事件
	public void saveMenuItemActionPerformed(ActionEvent evt) {
		int returnVal = filechooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();

			try {
				FileWriter fw = new FileWriter(file);
				fw.write(content.getText());
				setTitle(filechooser.getSelectedFile().getName()
						+ " - \u8bb0\u4e8b\u672c");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 另存为事件
	public void saveAsMenuItemActionPerformed(ActionEvent evt) {
		filechooser.setDialogTitle("另存为...");
		int returnVal = filechooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(content.getText());
				setTitle(filechooser.getSelectedFile().getName()
						+ " - \u8bb0\u4e8b\u672c");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 页面设置事件
	public void pageSetupMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null, "此功能正在开发中...");
	}

	// 打印事件
	public void printMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null, "打印中...");
	}

	// 更新风格外观方法
	void changeLookFeel(String className) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
			System.out.println(e);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	// Java风格事件
	public void javaStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	}

	// Motif风格事件
	public void metalStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	}

	// MAC风格事件
	public void windowsStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	}

	// 帮助事件
	public void helpTopicMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null,
				"\u9700\u8981\u5e2e\u52a9\u5417\uff1f");
	}

	// 关于事件
	public void aboutMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane
				.showMessageDialog(
						null,
						"/n\u7a0b\u5e8f\u540d\u79f0\uff1aJava \u8bb0\u4e8b\u672c/n\u4ee3\u7801\u7f16\u5199\uff1aCavien/n\u4f5c\u8005\u7f51\u7ad9\uff1ahttp:/www.cavien.com/nE-mail/u3000/uff1aCavien@163.com");

	}

	// 主函数
	public static void main(String args[]) {
		Notepad notepad = new Notepad();
		notepad.setVisible(true);
	}

}

// 定义新建事件类
class newMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	newMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.newMenuItemActionPerformed(evt);
	}
}

// 定义打开事件类
class openMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	openMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.openMenuItemActionPerformed(evt);
	}
}

// 定义保存事件类
class saveMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	saveMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.saveMenuItemActionPerformed(evt);
	}
}

// 定义另存为事件类
class saveAsMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	saveAsMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.saveAsMenuItemActionPerformed(evt);
	}
}

// 定义页面设置事件类
class pageSetupMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	pageSetupMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.pageSetupMenuItemActionPerformed(evt);
	}
}

// 定义打印事件类
class printMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	printMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.printMenuItemActionPerformed(evt);
	}
}

// 定义Java风格事件类
class javaStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	javaStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.javaStyleMenuItemActionPerformed(evt);

	}
}

// 定义Java风格事件类
class metalStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	metalStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.metalStyleMenuItemActionPerformed(evt);
	}
}

// 定义Java风格事件类
class windowsStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	windowsStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.windowsStyleMenuItemActionPerformed(evt);
	}
}

// 定义帮助主题事件类
class helpTopicMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	helpTopicMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.helpTopicMenuItemActionPerformed(evt);
	}
}

// 定义关于软件事件类
class aboutMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	aboutMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.aboutMenuItemActionPerformed(evt);
	}
}

// 定义退出事件类
class exitMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	exitMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.exitMenuItem_actionPerformed(evt);
	}
}