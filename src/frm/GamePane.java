/**
  * @(#)frm.GamePane.java  2008-8-12  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-12 	小猪     		新建
  **/
package frm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import data.Block;
import data.BlockCreater;
import data.publicData;

 /**
 * 游戏面板类。<br>
 * 练习使用Swing组件、ActionListener、内部类的使用。
 */
public class GamePane extends JPanel implements ActionListener{
	
	/** 格子的宽度 */
	public static int gridw = 10;
	/** 格子的高度 */
	public static int gridh = 20;
	/** 每个格子占用的像素 */
	public static int pixelWidth = 20;
	
	//public static Color bgColor = getBackground();
	
	/** 存放各个格子的颜色 *///此处务必注意，宽10，是指后面维数，高20是指前面的维数.
	//此处创建的是20行10列的数组，即为宽10高20
	private Color[][] grids = new Color[gridh][gridw];
	
	/** 方块产生器 */
	private BlockCreater creater = null;
	/** 产生的方块 */
	private Block block = null;
	
	/** 方块下降的定时器 */
	private Timer timer ;
	
	private publicData data = new publicData();
	

	
	public GamePane() {
		this(10,20,20);
	}
	
	public GamePane(int gridw,int gridh,int pixelWidth) {
		GamePane.gridw = gridw;
		GamePane.gridh = gridh;
		GamePane.pixelWidth = pixelWidth;
		
		creater = BlockCreater.getBlockCreater();
		
		setSize(gridw*pixelWidth, gridh*pixelWidth);
		setPreferredSize(new Dimension(gridw*pixelWidth, gridh*pixelWidth));
		
		timer = new Timer(data.TimeScore.get(TetrisFrame.infoPan.getLevel()-1),this);
		
		//setBackground(publicData.bgColor);
	}
	
	/**
	 * 注册上、下、左、右方向键的方法。
	 * @param a1 上键的事件。
	 * @param a2 左键的事件。
	 * @param a3 右键的事件。
	 * @param a4 下键的事件。
	 */
	private void registerKey(AbstractAction a1,AbstractAction a2,AbstractAction a3,AbstractAction a4,AbstractAction a5){
		//注册向上键
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"UpPress");
		getActionMap().put("UpPress", a1);
		//注册向左键
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0),"LeftPress");
		getActionMap().put("LeftPress", a2);
		//注册向右键
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0),"RightPress");
		getActionMap().put("RightPress", a3);
		//注册向下建
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"DownPress");
		getActionMap().put("DownPress", a4);
		//注册向下建
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,0),"OneDownPress");
		getActionMap().put("OneDownPress", a5);
	}
	
	/**
	 * 移除注册的上、下、左、右方向键事件。
	 */
	public void removeKey(){
		registerKey(null, null, null, null,null);
	}
	/**
	 * 添加注册的上、下、左、右方向键事件。
	 */
	public void addKey(){
		registerKey(new ChangeListener(),new MoveLeftListener(),new MoveRightListener(),new MoveDownListener(),new OneMoveDownListener());
	}
	
	/**
	 * 游戏开始。
	 */
	public void gameStart(){
		clearGrids();
		block = creater.getNextBlock();
		addKey();
		timer.start();	
		repaint();
		TetrisFrame.infoPan.clearScore();
	}
	
	/**
	 * 游戏结束。
	 */
	public void gameStop(){
		timer.stop();
		removeKey();
		//clearGrids();
		repaint();
	}
	
	/**
	 * 暂停游戏。
	 */
	public void gamePause(){
		removeKey();
		timer.stop();
		repaint();
		
	}
	
	/**
	 * 继续游戏。
	 */
	public void gameContinue(){
		addKey();
		timer.start();
		repaint();
	}
	
	private void clearGrids(){
		for(int i=0;i<gridh;i++)
			for(int j=0;j<gridw;j++)
				grids[i][j] = null;
	}
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(publicData.bgColor2);
		g2.fillRect(0, 0, getWidth(), getHeight());
		//super.paint(g);
		
		if(!TetrisFrame.isFirst && TetrisFrame.isPause){
			g2.setColor(publicData.bgColor);
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setColor(Color.WHITE);
			g2.drawString("Waiting...", 20, 60);
		}
		
		if(TetrisFrame.isShowGrid){
			for(int i=1;i<gridh;i++){
				g2.setColor(Color.GRAY);
				g2.drawLine(0, i*pixelWidth, gridw*pixelWidth, i*pixelWidth);
			}
			for(int j=1;j<gridw;j++){
				g2.setColor(Color.GRAY);
				g2.drawLine(j*pixelWidth, 0,j*pixelWidth , gridh*pixelWidth);	
			}
		}
		
		if(block!=null && TetrisFrame.isRun && !TetrisFrame.isPause)
			block.panit(g2);
		Color c = g2.getColor();
		for(int i=0;i<gridh;i++)
			for(int j=0;j<gridw;j++)
				if(grids[i][j]!=null){
					g2.setColor(grids[i][j]);
					g2.fill3DRect(j*pixelWidth, i*pixelWidth, pixelWidth, pixelWidth, true);
				}
		g2.setColor(c);
		showNextBlock();
	}

	public void actionPerformed(ActionEvent e) {
		MoveFailed();
		repaint();
	}
	
	/**
	 * 当方块到底无法移动时的处理。
	 */
	private void MoveFailed(){
		//下移不成功
		if(!block.moveDown(grids)){
			//无法移动时，把方块的非0的格子赋给方框。
			changeGrids();
			
			//检测是否有满行的，消除满行的行
			String flashStr = hasFullRow();
			if(!flashStr.equals(""))
				moveDownRows(flashStr);
			
			//产生新的方块.
			block = creater.getNextBlock();
			//检测方块和方框是否有交集。即方块产生后是否可以移动
			//游戏结束.
			if(!block.collide(grids)){
				gameStop();
				TetrisFrame.gameStart_Stop.doClick();
				//System.out.println("stop");
			}
			//changeScore();
		}
	}
	
	/**
	 * 刷新显示下2个图形。
	 */
	private void showNextBlock(){
		TetrisFrame.infoPan.getBPane().repaint();
		TetrisFrame.infoPan.repaint();
	}
	
	
	/**
	 * 无法移动时，把方块的非0的格子赋给方框。<br>
	 * 此处的逻辑比较复杂，考虑清楚再写！<br>
	 * 思路：循环将4*4的方块对应非0的格子颜色赋给方框对应的格子。<br>
	 * 难度：方块和方框如何对应。
	 */
	private void changeGrids(){
		//此处的逻辑比较复杂，考虑清楚再写！
		for(int i=0;i<block.getNum();i++)
			for(int j=0;j<block.getNum();j++)
				if(Block.allshape[block.getType()][block.getDir()][i][j]==1)
					grids[block.getGridy()+i][block.getGridx()+j] = block.getColor();
	}
	
	/**
	 * 检测新增加方块后，是否有满行。
	 * @return 返回的字符串为空或""，表示无满行。否则以行数用逗号给开表示满行的。
	 */
	private String hasFullRow(){
		String str = "";	
		for(int i=0;i<gridh-block.getGridy();i++){
			boolean b = true;
			int row = block.getGridy()+i;
			//System.out.println("row:"+row);
			for(int j=0;j<gridw;j++)
				if(grids[row][j]==null){
					b = false;
					break;
				}
			if(b)
				str += row+",";
		}
		return str.length()>0?str.substring(0,str.length()-1):str;
	}
	
	/**
	 * 消除满行的方块。
	 * @param str 用逗号"."分隔的满行的行号组成的字符串。
	 */
	private void moveDownRows(String str){
		StringTokenizer tokenizer = new StringTokenizer(str,",");
		int rowNum = tokenizer.countTokens();
		while(tokenizer.hasMoreTokens()){
			int row = Integer.parseInt(tokenizer.nextToken());
			//把这行以后的都统统向前移动一行
			for(int i=row;i>0;i--)
				for(int j=0;j<gridw;j++)
					grids[i][j] = grids[i-1][j];
			//把第1行付为NULL。
			for(int i=0;i<gridw;i++)
				grids[0][i] = null;
		}
		
		changeScore(rowNum);
	}
	
	private void changeScore(int rowNum){
		TetrisFrame.infoPan.setLines(rowNum);
		
		int score = data.RowScore.get(rowNum-1);
		if(TetrisFrame.isShowGrid)
			score /= 3;
		score = TetrisFrame.infoPan.setScore(score);
		int i = 0;
		for(i = 0;i<data.UpgradeScore.size();i++)
			if(score<=data.UpgradeScore.get(i))
				break;
		int level = TetrisFrame.infoPan.getLevel();
		if(i+1!=level){
			TetrisFrame.infoPan.setLevel(i+1);
			if(i<data.TimeScore.size())
				timer.setDelay(data.TimeScore.get(i));
		}
	}
	
	/**
	 * 左移事件。
	 * 2008-8-12
	 * @author		达内科技[Tarena Training Group]
	 * @version	1.0
	 * @since		JDK1.6(建议) 
	 */
	private class MoveLeftListener extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			block.moveLeft(grids);
			repaint();
		}
	}
	
	/**
	 * 右移事件。
	 * 2008-8-12
	 * @author		达内科技[Tarena Training Group]
	 * @version	1.0
	 * @since		JDK1.6(建议) 
	 */
	private class MoveRightListener extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			block.moveRight(grids);
			repaint();
		}
	}
	
	/**
	 * 下移事件。
	 * 2008-8-12
	 * @author		达内科技[Tarena Training Group]
	 * @version	1.0
	 * @since		JDK1.6(建议) 
	 */
	private class MoveDownListener extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			MoveFailed();
			repaint();
		}
	}
	private class OneMoveDownListener extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			while(block.moveDown(grids))
				repaint();
			MoveFailed();
			repaint();
		}
	}
	
	/**
	 * 方块变换事件。
	 * 2008-8-12
	 * @author		达内科技[Tarena Training Group]
	 * @version	1.0
	 * @since		JDK1.6(建议) 
	 */
	private class ChangeListener extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			int dir = block.getDir();
			//++dir%4，这个是核心代码。不要忽略他。
			block.changeDir(++dir%4, grids);
			repaint();
		}
	}
}
