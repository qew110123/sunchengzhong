package com.artsoft.demo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Notepad extends JFrame {

	private static final long serialVersionUID = 2618931623973154200L;
	// ϵͳ�������
	private JMenuBar menuBar = new JMenuBar();
	private JEditorPane content = new JEditorPane();
	private JScrollPane scroll = new JScrollPane(content);
	private JFileChooser filechooser = new JFileChooser();
	private BorderLayout bord = new BorderLayout();
	@SuppressWarnings("unused")
	private JLabel statusBar = new JLabel();
	private JPanel pane = new JPanel();
	private File file = null;

	// �����ļ��˵�
	private JMenu fileMenu = new JMenu();
	private JMenuItem newMenuItem = new JMenuItem();
	private JMenuItem openMenuItem = new JMenuItem();
	private JMenuItem saveMenuItem = new JMenuItem();
	private JMenuItem saveAsMenuItem = new JMenuItem();
	private JMenuItem pageSetupMenuItem = new JMenuItem();
	private JMenuItem printMenuItem = new JMenuItem();
	private JMenuItem exitMenuItem = new JMenuItem();
	// ������˵�
	private JMenu styleMenu = new JMenu();
	private ButtonGroup styleMenuGroup = new ButtonGroup();
	private JRadioButtonMenuItem javaStyleMenuItem = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem metalStyleMenuItem = new JRadioButtonMenuItem();
	private JRadioButtonMenuItem windowsStyleMenuItem = new JRadioButtonMenuItem();

	// ��������˵�
	private JMenuItem aboutMenuItem = new JMenuItem();
	private JMenuItem helpTopicMenuItem = new JMenuItem();
	private JMenu helpMenu = new JMenu();

	// ���캯��
	public Notepad() {
		initComponents();
	}

	private void initComponents() {

		// ����Ӳ˵���ļ��˵�
		fileMenu.setText("\u6587\u4ef6 (F)");
		newMenuItem.setText(" �½�(N)    Ctrl+N");
		openMenuItem.setText(" ��(O)...    Ctrl+O");
		saveMenuItem.setText(" ����(S)    Ctrl+S");
		saveAsMenuItem.setText(" ���Ϊ(A)...");
		pageSetupMenuItem.setText(" ҳ������(U)...");
		printMenuItem.setText(" ��ӡ(P)...    Ctrl+P");
		exitMenuItem.setText(" �˳�");
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(pageSetupMenuItem);
		fileMenu.add(printMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		// ����Ӳ˵�����˵�
		styleMenu.setText("���(S)");
		javaStyleMenuItem.setText("JavaĬ��");
		metalStyleMenuItem.setText("Metal���");
		windowsStyleMenuItem.setText("Windows���");
		styleMenuGroup.add(javaStyleMenuItem);
		styleMenuGroup.add(metalStyleMenuItem);
		styleMenuGroup.add(windowsStyleMenuItem);
		styleMenu.add(javaStyleMenuItem);
		styleMenu.add(metalStyleMenuItem);
		styleMenu.add(windowsStyleMenuItem);

		// ����Ӳ˵�������˵�
		helpMenu.setText("����(H)");
		helpTopicMenuItem.setText(" ��������(H)");
		aboutMenuItem.setText(" ���ڼ��±�(A)");
		helpMenu.add(helpTopicMenuItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutMenuItem);

		// �����ļ��˵��µ��¼�����
		newMenuItem.addActionListener(new newMenuItem_actionAdapter(this));
		openMenuItem.addActionListener(new openMenuItem_actionAdapter(this));
		saveMenuItem.addActionListener(new saveMenuItem_actionAdapter(this));
		saveAsMenuItem
				.addActionListener(new saveAsMenuItem_actionAdapter(this));
		pageSetupMenuItem
				.addActionListener(new pageSetupMenuItem_actionAdapter(this));
		printMenuItem.addActionListener(new printMenuItem_actionAdapter(this));
		exitMenuItem.addActionListener(new exitMenuItem_actionAdapter(this));

		// ������˵��µ��¼�����
		javaStyleMenuItem
				.addActionListener(new javaStyleMenuItem_actionAdapter(this));
		metalStyleMenuItem
				.addActionListener(new metalStyleMenuItem_actionAdapter(this));
		windowsStyleMenuItem
				.addActionListener(new windowsStyleMenuItem_actionAdapter(this));

		// ��������˵��µ��¼�����
		helpTopicMenuItem
				.addActionListener(new helpTopicMenuItem_actionAdapter(this));
		aboutMenuItem.addActionListener(new aboutMenuItem_actionAdapter(this));

		// ��Ӳ˵����˵���
		menuBar.add(fileMenu);
		menuBar.add(styleMenu);
		menuBar.add(helpMenu);

		// �������ڵ�һЩ����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("�ޱ��� - \u8bb0\u4e8b\u672c");
		this.setSize(640, 480);

		setJMenuBar(menuBar);
		pane.setLayout(bord);
		pane.add("Center", scroll);
		setContentPane(pane);
	}

	// �����½��˵����
	public void newMenuItemActionPerformed(ActionEvent evt) {
		file = null;
		if (!("".equals(content.getText()))) {
			Object[] options = { " ��(Y) ", " ��(N) ", " ȡ�� " };
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
				setTitle("�ޱ��� - \u8bb0\u4e8b\u672c");
			}
		}
	}

	// ����򿪲˵����
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

	// �����˳��˵����
	public void exitMenuItem_actionPerformed(ActionEvent e) {
		if (!("".equals(content.getText()))) {
			Object[] options = { " ��(Y) ", " ��(N) ", " ȡ�� " };
			int s = JOptionPane.showOptionDialog(null, "�ļ��������Ѿ��ı䡣/n�뱣���ļ���",
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

	// �����¼�
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

	// ���Ϊ�¼�
	public void saveAsMenuItemActionPerformed(ActionEvent evt) {
		filechooser.setDialogTitle("���Ϊ...");
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

	// ҳ�������¼�
	public void pageSetupMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null, "�˹������ڿ�����...");
	}

	// ��ӡ�¼�
	public void printMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null, "��ӡ��...");
	}

	// ���·����۷���
	void changeLookFeel(String className) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
			System.out.println(e);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	// Java����¼�
	public void javaStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	}

	// Motif����¼�
	public void metalStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	}

	// MAC����¼�
	public void windowsStyleMenuItemActionPerformed(ActionEvent evt) {
		changeLookFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	}

	// �����¼�
	public void helpTopicMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null,
				"\u9700\u8981\u5e2e\u52a9\u5417\uff1f");
	}

	// �����¼�
	public void aboutMenuItemActionPerformed(ActionEvent evt) {
		JOptionPane
				.showMessageDialog(
						null,
						"/n\u7a0b\u5e8f\u540d\u79f0\uff1aJava \u8bb0\u4e8b\u672c/n\u4ee3\u7801\u7f16\u5199\uff1aCavien/n\u4f5c\u8005\u7f51\u7ad9\uff1ahttp:/www.cavien.com/nE-mail/u3000/uff1aCavien@163.com");

	}

	// ������
	public static void main(String args[]) {
		Notepad notepad = new Notepad();
		notepad.setVisible(true);
	}

}

// �����½��¼���
class newMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	newMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.newMenuItemActionPerformed(evt);
	}
}

// ������¼���
class openMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	openMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.openMenuItemActionPerformed(evt);
	}
}

// ���屣���¼���
class saveMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	saveMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.saveMenuItemActionPerformed(evt);
	}
}

// �������Ϊ�¼���
class saveAsMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	saveAsMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.saveAsMenuItemActionPerformed(evt);
	}
}

// ����ҳ�������¼���
class pageSetupMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	pageSetupMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.pageSetupMenuItemActionPerformed(evt);
	}
}

// �����ӡ�¼���
class printMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	printMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.printMenuItemActionPerformed(evt);
	}
}

// ����Java����¼���
class javaStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	javaStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.javaStyleMenuItemActionPerformed(evt);

	}
}

// ����Java����¼���
class metalStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	metalStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.metalStyleMenuItemActionPerformed(evt);
	}
}

// ����Java����¼���
class windowsStyleMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	windowsStyleMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.windowsStyleMenuItemActionPerformed(evt);
	}
}

// ������������¼���
class helpTopicMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	helpTopicMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.helpTopicMenuItemActionPerformed(evt);
	}
}

// �����������¼���
class aboutMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	aboutMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.aboutMenuItemActionPerformed(evt);
	}
}

// �����˳��¼���
class exitMenuItem_actionAdapter implements ActionListener {
	Notepad adaptee;

	exitMenuItem_actionAdapter(Notepad adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent evt) {
		adaptee.exitMenuItem_actionPerformed(evt);
	}
}