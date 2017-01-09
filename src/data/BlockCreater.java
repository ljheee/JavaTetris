/**
  * @(#)data.BlockCreater.java  2008-8-12  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-12 	小猪     		新建
  **/
package data;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Random;

import javax.swing.JPanel;

import frm.GamePane;

 /**
 * 块产生器。<br>
 * 练习使用单子模式、工厂模式。
 */
public class BlockCreater {

	/** 存放块的队列。*/
	private ArrayDeque<Block> blocks = new ArrayDeque<Block>(2);
	/** 块产生器。 */
	private static BlockCreater blockCreater = null;
	/** 随机数产生器. */
	private static Random random = null;
	
	/** JPanel的背景颜色 */
	private Color panBGColor = null;
	
	/**
	 * 私有构造方法。
	 */
	private BlockCreater() {
		JPanel pan = new JPanel();
		panBGColor = pan.getBackground();
		random = new Random();
		addBlock();
		addBlock();
	}
	
	/**
	 * 块产生器，的静态方法，也是块产生工厂。
	 * @return 返回一个静态的实例块产生器。
	 */
	public static BlockCreater getBlockCreater(){
		if(blockCreater==null)
			blockCreater = new BlockCreater();
		return blockCreater;
	}
	
	/**
	 * 向队列中添加块。
	 */
	private void addBlock(){
		int type = random.nextInt(Block.allshape.length);
		int dir = random.nextInt(4);
		Color color = null;
		do{
			color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
		}while (color == panBGColor);
		
		int posx = (GamePane.gridw - 4)/2;
		int posy = 0;
		blocks.add(new Block(type,dir,color,posx,posy,GamePane.pixelWidth));
	}
	
	/**
	 * 得到队列中的第一个块。
	 * @return 返回队列中的第一个块，并删除。
	 */
	public Block getNextBlock(){
		Block block = blocks.poll();
		addBlock();
		return block;
	}
	
	/**
	 * 得到队列中的第一个块。
	 * @return 返回队列中的第一个块。
	 */
	public Block getFirstBlock(){
		return blocks.getFirst();
	}
	
	/**
	 * 得到队列中的最后一个块。
	 * @return 返回队列中的最后一个块。
	 */
	public Block getLastBlock(){
		return blocks.getLast();
	}
}
