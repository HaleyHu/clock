package org.hbin.clock;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.hbin.clock.entities.Clock;

/**
 * 
 * 文件名： Clock.java CopyRight (c) 2010 
 * 文件编号： 002 
 * 创建人： 斌斌 
 * 日期： 2010.10.31 
 * 修改人： 
 * 日期：
 * 描述： 一个时钟程序，可以显示系统当前的日期、时间 版本号： 1.0
 */
public class MainFrame {

	public static void main(String[] args) {
		init();
	}

	public static void init() {
		Clock clock = new Clock();
		JFrame frame = new JFrame("钟表 -斌斌-1.0");

		frame.add(clock);
		Image image = null;
		try {
			URL url = MainFrame.class.getClassLoader().getResource("clock.png");
			image = ImageIO.read(url);
			frame.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setSize(clock.getWidth() + 10, clock.getHeight() + 37);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		center(frame);
		frame.setResizable(false);
		frame.setVisible(true);

		clock.start();
	}

	public static void center(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
	}
}
