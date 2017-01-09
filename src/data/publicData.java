/**
  * @(#)data.publicData.java  2008-8-13  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-13 	小猪     		新建
  **/
package data;

import java.awt.Color;
import java.util.Vector;

import javax.swing.border.LineBorder;

 /**
 * 此处加入类详细说明
 */
public class publicData {

	public static Color bgColor = new Color(255,255,255,125);
	public static Color bgColor2 = new Color(40,40,40,185);
	public static LineBorder border = new LineBorder(Color.GRAY,3);
	
	public  Vector<Integer> RowScore = null;
	public  Vector<Integer> UpgradeScore = null;
	public  Vector<Integer> TimeScore = null;
	
	public publicData() {
		RowScore = new Vector<Integer>();
		RowScore.add(58);
		RowScore.add(138);
		RowScore.add(238);
		RowScore.add(358);
		
		UpgradeScore = new Vector<Integer>();
		UpgradeScore.add(1300);
		UpgradeScore.add(2800);
		UpgradeScore.add(4500);
		UpgradeScore.add(6400);
		UpgradeScore.add(8500);
		UpgradeScore.add(10800);
		UpgradeScore.add(13300);
		UpgradeScore.add(16000);
		UpgradeScore.add(19000);
//		UpgradeScore.add(13);
//		UpgradeScore.add(28);
//		UpgradeScore.add(45);
//		UpgradeScore.add(64);
//		UpgradeScore.add(85);
//		UpgradeScore.add(108);
//		UpgradeScore.add(133);
//		UpgradeScore.add(160);
//		UpgradeScore.add(190);
		
		TimeScore = new Vector<Integer>();
		TimeScore.add(500);
		TimeScore.add(470);//30
		TimeScore.add(430);//40
		TimeScore.add(380);//50
		TimeScore.add(320);//60
		TimeScore.add(250);//70
		TimeScore.add(170);//80
		TimeScore.add(120);//50
		TimeScore.add(90);//30
	}
}
