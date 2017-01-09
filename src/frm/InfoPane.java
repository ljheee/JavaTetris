/**
  * @(#)frm.InfoPane.java  2008-8-13  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-13 	小猪     		新建
  **/
package frm;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.publicData;

 /**
 * 此处加入类详细说明
 */
public class InfoPane extends JPanel {

	/** 成绩 */
	private int score = 0;
	/** 等级 */
	private int level = 1;
	/** 行数 */
	private int lines = 0;
	/** 显示成绩 */
	private JLabel lblScore = new JLabel("积分："+score);
	/** 显示等级 */
	private JLabel lblLevel = new JLabel("级数："+level);
	/** 显示行数 */
	private JLabel lblLines = new JLabel("行数："+lines);
	/**  */
	private BlockPane bPane = new BlockPane();
	
	
	public InfoPane() {
		setSize(160,210);
		setPreferredSize(new Dimension(160,210));
		
		lblScore.setPreferredSize(new Dimension(100,25));
		lblLevel.setPreferredSize(new Dimension(100,25));
		lblLines.setPreferredSize(new Dimension(100,25));
		
		bPane = new BlockPane();
		bPane.setLocation(0, 0);
		bPane.setSize(160,100);
		
		JPanel pan = new JPanel();
		pan.setSize(160,100);
		pan.setLocation(0, 110);
		pan.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		pan.setBackground(publicData.bgColor);
		pan.setBorder(publicData.border);
		pan.add(lblScore);
		pan.add(lblLevel);
		pan.add(lblLines);
		
		setLayout(null);
		add(bPane);
		add(pan);
	}

	public void setLevel(int level) {
		this.level = level;
		lblLevel.setText("级数："+this.level);
	}

	public void setLines(int lines) {
		this.lines += lines;
		lblLines.setText("行数："+this.lines);
	}

	public int setScore(int score) {
		this.score += score;
		lblScore.setText("积分："+this.score);
		return this.score;
	}

	public BlockPane getBPane() {
		return bPane;
	}
	
	public void clearScore(){
		score = 0;
		level = 1;
		lines = 0;
		lblLevel.setText("级数："+this.level);
		lblLines.setText("行数："+this.lines);
		lblScore.setText("积分："+this.score);
	}

	public int getLevel() {
		return level;
	}
}
