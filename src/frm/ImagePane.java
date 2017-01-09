/**
  * @(#)frm.ImagePane.java  2008-8-14  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 此处输入简单类说明
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-14 	小猪     		新建
  **/
package frm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

 /**
 * 此处加入类详细说明
 */
public class ImagePane extends JPanel {

	private Image image = null;
	public ImagePane() {
		try {
			image = ImageIO.read(new File("F:\\tarena\\ws_corejava\\Tetris\\bin\\frm\\2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		if(image == null) return;
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		g.drawImage(image, 0, 0, null);
		
		
		for(int i=0;i*imageWidth <= getWidth();i++)
			for(int j=0;j*imageHeight <= getHeight();j++)
				if(i+j>0)
					g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
	}
}
