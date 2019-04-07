package com.mwq;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mwq.frame.IndexFrame;
import com.mwq.frame.LandFrame;
import com.mwq.hibernate.Dao;

public class PersonnelManage {

	boolean packFrame = false;

	public PersonnelManage() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				land();
			}
		});
	}

	public void land() {
		LandFrame frame = new LandFrame();
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}

		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new PersonnelManage();
	}

}
