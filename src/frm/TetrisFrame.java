/**
  * @(#)frm.TetrisFrame.java  2008-8-12  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-12 	小猪     		新建
  **/
package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import about.About;

import data.publicData;

 /**
 * 此处加入类详细说明
 */
public class TetrisFrame extends JFrame implements ActionListener{
	
	/** 游戏面板 */
	public static GamePane gamePan = null;
	/** 信息面板 */
	public static InfoPane infoPan = null; 

	/** 开始/停止游戏按钮 */
	public static JButton gameStart_Stop = new JButton("开始游戏");
	/** 暂停/继续游戏按钮 */
	private JButton gamePause_Con = new JButton("暂停游戏");
	/** 显示网格 */
	private JCheckBox boxShowGrid = new JCheckBox("显示网格");
	
	/** 游戏是否在运行 */
	public static boolean isRun = false;
	/** 游戏是否暂停 */
	public static boolean isPause = false;
	/** 是否第一次运行 */
	public static boolean isFirst = true;
	/** 是否显示网格 */
	public static boolean isShowGrid = false;
	
	/** 背景图像 */
	private BufferedImage image = null;
	
	private JMenuBar menuBar=new JMenuBar();
	private JMenu menuHelp=new JMenu("关于(H)");
	private JMenuItem menuItemAbout=new JMenuItem("关于俄罗斯方块",KeyEvent.VK_A);
	
	public TetrisFrame() {
//		try {
//			image = ImageIO.read(new File("F:\\tarena\\ws_corejava\\Tetris\\bin\\frm\\2.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		setSize(405, 480);
		Toolkit tk=Toolkit.getDefaultToolkit();
		setLocation((tk.getScreenSize().width-getSize().width)/2,(tk.getScreenSize().height-getSize().height)/2);
		
		setJMenuBar(menuBar);
		menuBar.add(menuHelp);
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuItemAbout);
		menuItemAbout.addActionListener(this);	
		
		gameStart_Stop.setSize(75,25);
		gameStart_Stop.setLocation(231, 230);
		gameStart_Stop.setMargin(new Insets(0,0,0,0));
		gameStart_Stop.setOpaque(true);
		gameStart_Stop.addActionListener(this);
		gamePause_Con.setSize(75,25);
		gamePause_Con.setEnabled(false);
		gamePause_Con.setLocation(316, 230);
		gamePause_Con.setOpaque(true);
		gamePause_Con.setMargin(new Insets(0,0,0,0));
		gamePause_Con.addActionListener(this);
		
		boxShowGrid.setSize(80,25);
		boxShowGrid.setLocation(231,265);
		boxShowGrid.setOpaque(true);
		boxShowGrid.addActionListener(this);
		
		
		infoPan = new InfoPane();
		infoPan.setLocation(231,10);
		
		gamePan = new GamePane();
		gamePan.setLocation(3, 3);
		
		JPanel bPan = new JPanel();
		bPan.setLocation(10, 10);
		bPan.setBackground(publicData.bgColor);
		bPan.setBorder(publicData.border);
		bPan.setSize(206,406);
		bPan.setLayout(null);
		bPan.add(gamePan);
		
/*		setLayout(new BorderLayout());
		ImagePane ip = new ImagePane();
		add(ip,BorderLayout.CENTER);*/
		setLayout(null);
		add(bPan);
		add(infoPan);
		add(gameStart_Stop);
		add(gamePause_Con);
		add(boxShowGrid);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		
	}
	
	//@Override
//	public void paint(Graphics g) {
//		
//		g.drawImage(image, 0, 28, this);
//		//super.paint(g);
//	}
	
//	@Override
//	public void paintComponents(Graphics g) {
//		super.paintComponents(g);
//		if(image == null) return;
//		g.drawImage(image, 0, 28, this);
//		int imageWidth = image.getWidth(this);
//		int imageHeight = image.getHeight(this);
//		
//		for(int i=0;i*imageWidth <= getWidth();i++)
//			for(int j=0;j*imageHeight <= getHeight();j++)
//				if(i+j>0)
//					g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
//	}
	
	/**
	 * 按钮事件。
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gameStart_Stop){
			//游戏正在运行，停止游戏
			if(isRun){
				gamePan.gameStop();
				isRun = false;
				gameStart_Stop.setText("开始游戏");
				if(isPause){
					isPause = false;
					gamePause_Con.setText("暂停游戏");
				}
				
				gamePause_Con.setEnabled(false);
				
				
			}else{//游戏停止运行，启动游戏
				gamePan.gameStart();
				isRun = true;
				gameStart_Stop.setText("结束游戏");
				gamePause_Con.setEnabled(true);
				isFirst = false;
			}
		}
		if(e.getSource()==gamePause_Con){
			//游戏已经暂停，继续游戏
			if(isPause && isRun){
				gamePan.gameContinue();
				isPause = false;
				gamePause_Con.setText("暂停游戏");
			}
			else if(!isPause && isRun){//暂停游戏，
				gamePan.gamePause();
				isPause = true;
				gamePause_Con.setText("继续游戏");
			}
		}
		if(e.getSource()==boxShowGrid){
			if(isShowGrid){
				gamePan.repaint();
				isShowGrid = false;
			}else{
				gamePan.repaint();
				isShowGrid = true;
			}
		}
		if(e.getSource()==menuItemAbout){
			//JOptionPane.showMessageDialog(null, "<html><body>程序参与人员 :<br>研发部总监 : 赵德奎<br>分 析 设 计&nbsp; :&nbsp;&nbsp;杨&nbsp;&nbsp;强<br>代 码 编 写&nbsp; :&nbsp;&nbsp;小&nbsp;&nbsp;猪<body></html>");
			new About(this,"关于俄罗斯方块",true);
		}
	}
	

}
