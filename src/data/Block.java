/**
  * @(#)data.Block.java  2008-8-11  
  * Copy Right Information	: Tarena
  * Project					: Tetris
  * JDK version used		: jdk1.6.4
  * Comments				: 方块类。
  * Version					: 1.0
  * Sr	Date		Modified By		Why & What is modified
  * 1.	2008-8-11 	小猪     		新建
  **/
package data;

import java.awt.Color;
import java.awt.Graphics2D;

 /**
 * 方块类。<br>
 * 理解面向对象的思想。<br>
 */
public class Block {

	/** 七种图形，包括变换的图形 */
	public static int[][][][] allshape = {
		//z型
		{
			{{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}},
			{{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}
		},
		//反z型
		{
			{{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}},
			{{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}},
			{{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}},
			{{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}}
		},
		//1型
		{
			{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}},
			{{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}},
			{{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
		},
		//田型
		{
			{{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
		},
		//K型
		{
			{{0,1,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}},
			{{1,1,1,0},{0,1,0,0},{0,0,0,0},{0,0,0,0}},
			{{0,0,1,0},{0,1,1,0},{0,0,1,0},{0,0,0,0}},
		},
		//L型
		{
			{{1,1,1,0},{1,0,0,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}},
			{{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}},
		},
		//倒L型
		{
			{{1,1,1,0},{0,0,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,0,1,0},{0,0,1,0},{0,1,1,0},{0,0,0,0}},
			{{1,0,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}},
			{{0,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}},
		}
	};
	/** 方块的类型，范围：0-allshape.length */
	private int type = 0;
	/** 方块的方向，范围：0-4 */
	private int dir = 0;
	/** 方块的颜色 */
	private Color color = Color.PINK;
	/** 方块的x坐标 */
	private int gridx = 0;
	/** 方块的y坐标 */
	private int gridy = 0;
	/** 一个方框的占用的宽度和高度(缺省：是一个(n*n)4*4正方形) */
	private int num = 4;
	
	/** 一个格子的占有的像素宽度和高度(缺省：一个(n*n)20*20的正方形) */
	private int pixelWidth = 20;
	
	/**  */
	
	
	
	public Block(int type,int dir,Color color,int gridx,int gridy,int pixelWidth) {
		this.type = type;
		this.dir = dir;
		this.color = color;
		this.gridx = gridx;
		this.gridy = gridy;
		this.pixelWidth = pixelWidth;
		num = allshape[0].length;
	}
	
	/**
	 * 返回检测方块和格子的碰撞后的结果。<br>
	 * 碰撞有2个思路：<br>
	 * 1.分别检测左侧、右侧、下部的碰撞，底下注释的部分已实现。<br>
	 * 2.直接检测和方块交集的格子。<br>
	 * @param grids 方框的各个格子的颜色。
	 * @return 返回是否可以移动。ture:可以。false:不可以。
	 */
	public boolean collide(Color[][] grids){
		/** 方框的高度 */
		int height = grids.length;
		/** 方框的宽度 */
		int width = grids[0].length;
		if(gridx<=-1*num || gridx>=width || gridy>=height)
			return false;
		/** 碰撞方块的水平位置x的起始下标 */
		int posx1 = 0;
		/** 碰撞方块的水平位置x的结束下标 */
		int posx2 = num;
		/** 碰撞方块的垂直位置y的起始下标 */
		int posy1 = 0;
		/** 碰撞方块的垂直位置y的结束下标 */
		int posy2 = num;
		
		if(gridx<=0){
			posx1 = gridx*-1;
			//检测方块左侧移出去的部分是否有非0的格子
			for(int i=0;i<posx1;i++)
				for(int j=0;j<num;j++)
				if(allshape[type][dir][j][i]==1)
					return false;
		}
		if(gridx>=width-num){
			posx2 = width - gridx;
			//检测方块右侧移出去的部分是否有非0的格子
			for(int i=posx2;i<num;i++)
				for(int j=0;j<num;j++)
					if(allshape[type][dir][j][i]==1)
						return false;
		}
		if(gridy>=height-num){
			posy2 = height - gridy;
			//检测方块下侧移出去的部分是否有非0的格子
			for(int i=posy2;i<num;i++)
				for(int j=0;j<num;j++)
					if(allshape[type][dir][i][j]==1)
						return false;
		}
		
		//System.out.println("posx1:"+posx1+"\nposx2:"+posx2);
		//System.out.println("posy1:"+posy1+"\nposy2:"+posy2);
		
		//检测交集的部分，方块为1的地方，是否格子颜色为空		
		//此处的逻辑比较复杂，考虑清楚再写！
		for(int i=posy1;i<posy2;i++)
			for(int j=posx1;j<posx2;j++)
				if(allshape[type][dir][i][j]==1 && grids[gridy+i][gridx+j]!=null)
					return false;
		return true;
	}
	
	/**
	 * 方块的左移。
	 * @param grids 方框的各个格子的颜色。
	 * @return 返回是否左移成功。ture:成功。false:不成功。
	 */
	public boolean moveLeft(Color[][] grids){
		
		gridx--;
		boolean b = collide(grids);
		if(!b)
			gridx++;
		return b;
	}
	
	/**
	 * 方块的右移。
	 * @param grids 方框的各个格子的颜色。
	 * @return 返回是否右移成功。ture:成功。false:不成功。
	 */
	public boolean moveRight(Color[][] grids){
		gridx++;
		boolean b = collide(grids);
		if(!b)
			gridx--;
		return b;
	}
	
	/**
	 * 方块的下移。
	 * @param grids 方框的各个格子的颜色。
	 * @return 返回是否下移成功。ture:成功。false:不成功。
	 */
	public boolean moveDown(Color[][] grids){
		gridy++;
		boolean b = collide(grids);
		if(!b)
			gridy--;
		return b;
	}
	
	/**
	 * 变换方块。
	 * @param dir 变换的方向。
	 * @param grids 方框的各个格子的颜色。
	 * @return 返回变换成功否。true：成功。false：不成功。
	 */
	public boolean changeDir(int dir,Color[][] grids){
		int xdir = this.dir;
		this.dir = dir;
		boolean b = collide(grids);
		if(!b)
			this.dir = xdir;
		return b;
	}
	
	/**
	 * 方块的绘制。
	 * @param g 画笔。
	 */
	public void panit(Graphics2D g){
		//System.out.println("type:"+type+"\ndir:"+dir);
		Color c = g.getColor();
		g.setColor(color);
		//g.drawRect(gridx*pixelWidth, gridy*pixelWidth, pixelWidth*4, pixelWidth*4);
		for(int i=0;i<num;i++)
			for(int j=0;j<num;j++)
				if(allshape[type][dir][i][j]!=0)
					g.fill3DRect((gridx+j)*pixelWidth, (gridy+i)*pixelWidth, pixelWidth, pixelWidth, true);
		g.setColor(c);
	}
	
	/**
	 * 小方块的绘制。
	 * @param g 画笔。
	 * @param n 缩小的倍数。
	 */
	public void panitSmall(Graphics2D g,int n,int x,int y){
		Color c = g.getColor();
		//System.out.println("x:"+x);
		g.setColor(color);
		for(int i=0;i<num;i++)
			for(int j=0;j<num;j++)
				if(allshape[type][dir][i][j]!=0)
					g.fill3DRect((x+j)*pixelWidth*n, (y+i)*pixelWidth*n, pixelWidth*n, pixelWidth*n, true);
		g.setColor(c);
	}

	public Color getColor() {
		return color;
	}

	public int getDir() {
		return dir;
	}

	public int getGridx() {
		return gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public int getNum() {
		return num;
	}

	public int getType() {
		return type;
	}
	
	
	
//	/**
//	 * 判断和左边是否发生碰撞。简单地说即是否允许左移。
//	 * @param grids 方框颜色。
//	 * @return 返回是否可以左移。ture:可以。false:不可以。
//	 */
//	public boolean collideWithLeft(Color[][] grids){
//		/** 格子的宽度 */
//		int width = grids.length;
//		/** 格子的高度 */
//		int height = grids[0].length;
//		/** 要比较方块列的位置 */
//		int pos1 = 0;
//		/** 要比较格子列的位置 */
//		int pos2 = 0;
//		if(gridx > 0){
//			pos1 = 0;
//			pos2 = gridx - 1;
//		}else if(gridx + 2 >= 0){
//			pos1 = 1 - gridx;
//			pos2 = 0;
//		}else
//			return false;
//		//方块的某一列与其旁边即将发生碰撞。
//		for(int i=0;i<num;i++){
//			if(allshape[type][dir][i][pos1]==1 && grids[gridy+i][pos2]!=null)
//				return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 判断和右边是否发生碰撞。简单地说即是否允许右移。
//	 * @param grids 方框颜色。
//	 * @return 返回是否可以右移。ture:可以。false:不可以。
//	 */
//	public boolean collideWithRight(Color[][] grids){
//		/** 格子的宽度 */
//		int width = grids.length;
//		/** 格子的高度 */
//		int height = grids[0].length;
//		/** 要比较方块列的位置 */
//		int pos1 = 0;
//		/** 要比较格子列的位置 */
//		int pos2 = 0;
//		if(gridx + num < width){
//			pos1 = num - 1;
//			pos2 = gridx + num;
//		}else if(width - gridx - 2 >= 0){
//			pos1 = width - gridx -2;
//			pos2 = width - 1;
//		}else
//			return false;
//		//方块的某一列与其旁边即将发生碰撞。
//		for(int i=0;i<num;i++){
//			if(allshape[type][dir][i][pos1]==1 && grids[gridy+i][pos2]!=null)
//				return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 判断和下边是否发生碰撞。简单地说即是否允许下移。
//	 * @param grids 方框颜色。
//	 * @return 返回是否可以下移。ture:可以。false:不可以。
//	 */
//	public boolean collideWithBottom(Color[][] grids){
//		/** 格子的宽度 */
//		int width = grids.length;
//		/** 格子的高度 */
//		int height = grids[0].length;
//		/** 要比较方块行的位置 */
//		int pos1 = 0;
//		/** 要比较格子行的位置 */
//		int pos2 = 0;
//		if(gridy + num < height){
//			pos1 = num - 1;
//			pos2 = gridy + num;
//		}else if(height - gridy - 2 >= 0){
//			pos1 = width - gridy -2;
//			pos2 = height - 1;
//		}else
//			return false;
//		//方块的某一列与其旁边即将发生碰撞。
//		for(int i=0;i<num;i++){
//			if(allshape[type][dir][pos1][i]==1 && grids[pos2][gridx+i]!=null)
//				return false;
//		}
//		return true;
//	}
	
}


