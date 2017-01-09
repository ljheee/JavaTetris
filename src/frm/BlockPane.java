/**
  * @(#)frm.BlockPane.java  2008-8-13  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 显示下两个块类。
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-13 	小猪     		新建
  **/
package frm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.Block;
import data.BlockCreater;
import data.publicData;

 /**
 * 显示下两个块类。
 */
public class BlockPane extends JPanel {

	/** 块产生器。 */
	private BlockCreater creater = null;
	private Block bigBlock = null;
	private Block smallBlock = null;
	
	public BlockPane() {
		creater = BlockCreater.getBlockCreater();
		setSize(160,100);
		setPreferredSize(new Dimension(160,100));
		setBackground(publicData.bgColor);
		setBorder(publicData.border);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		Block bigBlock = creater.getFirstBlock();
		Block smallBlock = creater.getLastBlock();
		
		Color c = g2.getColor();
		g2.setColor(bigBlock.getColor());
		for(int i=0;i<bigBlock.getNum();i++)
			for(int j=0;j<bigBlock.getNum();j++)
				if(Block.allshape[bigBlock.getType()][bigBlock.getDir()][i][j]!=0)
					g2.fill3DRect((1+j)*18, (1+i)*18, 18, 18, true);
		
		g2.setColor(smallBlock.getColor());
		for(int i=0;i<smallBlock.getNum();i++)
			for(int j=0;j<smallBlock.getNum();j++)
				if(Block.allshape[smallBlock.getType()][smallBlock.getDir()][i][j]!=0)
					g2.fill3DRect((7+j)*12, (3+i)*12, 12, 12, true);
		g2.setColor(c);
	}
}
