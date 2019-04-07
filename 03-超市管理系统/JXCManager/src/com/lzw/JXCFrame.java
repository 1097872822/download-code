package com.lzw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.lzw.login.Login;
public class JXCFrame {
	private JPanel sysManagePanel;
	private JDesktopPane desktopPane;
	private JFrame frame;
	private JLabel backLabel;
	// 创建窗体的Map类型集合对象
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	public JXCFrame() {
		frame = new JFrame("企业进销存管理系统");
		frame.getContentPane().setBackground(new Color(170, 188, 120));
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLabel = new JLabel();// 背景标签
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage(); // 更新或初始化背景图片
		desktopPane = new JDesktopPane();
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane);
		JTabbedPane navigationPanel = createNavigationPanel(); // 创建导航标签面板
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
	private JTabbedPane createNavigationPanel() { // 创建导航标签面板的方法
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(211, 230, 192));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

		JPanel baseManagePanel = new JPanel(); // 基础信息管理面板
		baseManagePanel.setBackground(new Color(215, 223, 194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,
				BoxLayout.X_AXIS));
		baseManagePanel.add(createFrameButton("客户信息管理", "KeHuGuanLi"));
		baseManagePanel.add(createFrameButton("商品信息管理", "ShangPinGuanLi"));
		baseManagePanel.add(createFrameButton("供应商信息管理", "GysGuanLi"));

		JPanel depotManagePanel = new JPanel(); // 库存管理面板
		depotManagePanel.setBackground(new Color(215, 223, 194));
		depotManagePanel.setLayout(new BoxLayout(depotManagePanel,
				BoxLayout.X_AXIS));
		depotManagePanel.add(createFrameButton("库存盘点", "KuCunPanDian"));
		depotManagePanel.add(createFrameButton("价格调整", "JiaGeTiaoZheng"));

		JPanel sellManagePanel = new JPanel();// 销售管理面板
		sellManagePanel.setBackground(new Color(215, 223, 194));
		sellManagePanel.setLayout(new BoxLayout(sellManagePanel,
				BoxLayout.X_AXIS));
		sellManagePanel.add(createFrameButton("销售单", "XiaoShouDan"));
		sellManagePanel.add(createFrameButton("销售退货", "XiaoShouTuiHuo"));

		JPanel searchStatisticPanel = new JPanel();// 查询统计面板
		searchStatisticPanel.setBounds(0, 0, 600, 41);
		searchStatisticPanel.setName("searchStatisticPanel");
		searchStatisticPanel.setBackground(new Color(215, 223, 194));
		searchStatisticPanel.setLayout(new BoxLayout(searchStatisticPanel,
				BoxLayout.X_AXIS));
		searchStatisticPanel.add(createFrameButton("客户信息查询", "KeHuChaXun"));
		searchStatisticPanel.add(createFrameButton("商品信息查询", "ShangPinChaXun"));
		searchStatisticPanel.add(createFrameButton("供应商信息查询",
				"GongYingShangChaXun"));
		searchStatisticPanel.add(createFrameButton("销售信息查询", "XiaoShouChaXun"));
		searchStatisticPanel.add(createFrameButton("销售退货查询",
				"XiaoShouTuiHuoChaXun"));
		searchStatisticPanel.add(createFrameButton("入库查询", "RuKuChaXun"));
		searchStatisticPanel
				.add(createFrameButton("入库退货查询", "RuKuTuiHuoChaXun"));
		searchStatisticPanel.add(createFrameButton("销售排行", "XiaoShouPaiHang"));

		JPanel stockManagePanel = new JPanel();// 进货管理面板
		stockManagePanel.setBackground(new Color(215, 223, 194));
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,
				BoxLayout.X_AXIS));
		stockManagePanel.add(createFrameButton("进货单", "JinHuoDan"));
		stockManagePanel.add(createFrameButton("进货退货", "JinHuoTuiHuo"));

		sysManagePanel = new JPanel();// 系统管理面板
		sysManagePanel.setBackground(new Color(215, 223, 194));
		sysManagePanel
				.setLayout(new BoxLayout(sysManagePanel, BoxLayout.X_AXIS));
		sysManagePanel.add(createFrameButton("操作员管理", "CzyGL"));
		sysManagePanel.add(createFrameButton("更改密码", "GengGaiMiMa"));
		sysManagePanel.add(createFrameButton("权限管理", "QuanManager"));

		tabbedPane.addTab("   基础信息管理   ", null, baseManagePanel, "基础信息管理");
		tabbedPane.addTab("   进货管理   ", null, stockManagePanel, "进货管理");
		tabbedPane.addTab("   销售管理   ", null, sellManagePanel, "销售管理");
		tabbedPane.addTab("   查询统计   ", null, searchStatisticPanel, "查询统计");
		tabbedPane.addTab("   库存管理   ", null, depotManagePanel, "库存管理");
		tabbedPane.addTab("   系统管理   ", null, sysManagePanel, "系统管理");

		return tabbedPane;
	}
	/** *********************辅助方法************************* */
	// 为内部窗体添加Action的方法
	private JButton createFrameButton(String fName, String cname) {
		String imgUrl = "res/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "res/ActionIcon/" + fName	+ "_roll.png";
		String imgUrl_down = "res/ActionIcon/" + fName	+ "_down.png";
		Icon icon = new ImageIcon(imgUrl);
		Icon icon_roll = null;
		if (imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll);
		Icon icon_down = null;
		if (imgUrl_down != null)
			icon_down = new ImageIcon(imgUrl_down);
		Action action = new openFrameAction(fName, cname, icon);
		JButton button = new JButton(action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setHideActionText(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if (icon_roll != null)
			button.setRolloverIcon(icon_roll);
		if (icon_down != null)
			button.setPressedIcon(icon_down);
		return button;
	}
	// 获取内部窗体的唯一实例对象
	private JInternalFrame getIFrame(String frameName) {
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName)) {
			try {
				Class fClass = Class.forName("internalFrame." + frameName);
				Constructor constructor = fClass.getConstructor(null);
				jf = (JInternalFrame) constructor.newInstance(null);
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			jf = ifs.get(frameName);
		return jf;
	}
	// 更新背景图片的方法
	private void updateBackImage() {
		if (backLabel != null) {
			int backw = JXCFrame.this.frame.getWidth();
			int backh = frame.getHeight();
			backLabel.setSize(backw, backh);
			backLabel.setText("<html><body><image width='" + backw
					+ "' height='" + (backh - 110) + "' src="
					+ JXCFrame.this.getClass().getResource("welcome.jpg")
					+ "'></img></body></html>");
		}
	}
	// 窗体监听器
	private final class FrameListener extends ComponentAdapter {
		public void componentResized(final ComponentEvent e) {
			updateBackImage();
		}
	}
	// 主窗体菜单项的单击事件监听器
	protected final class openFrameAction extends AbstractAction {
		private String frameName = null;
		private openFrameAction() {
		}
		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname);
			putValue(Action.SHORT_DESCRIPTION, cname);
			putValue(Action.SMALL_ICON, icon);
		}
		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName);
			// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}