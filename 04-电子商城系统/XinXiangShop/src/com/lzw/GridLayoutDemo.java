package com.lzw;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class GridLayoutDemo extends JFrame {
	public GridLayoutDemo() {
		try {
			Desktop desktop;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
				File file = new File("res/a.doc");
				// file.createNewFile();
				desktop.open(file);
				// desktop.browse(new URI("http://cc163.com/ddk"));
				// desktop.print(file);
			}
		} catch (Exception e) {
			System.out.println("444");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		JTextField t = new JTextField(4);
		t.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tf = (JTextField) e.getSource();
				int len = tf.getText().length();
				System.out.println(len);
				if (len > 3) {
					e.consume();
				}
			}
		});
		JFrame f = new JFrame();
		f.setBounds(100, 100, 200, 200);
		f.setLayout(new FlowLayout());
		f.add(t);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
