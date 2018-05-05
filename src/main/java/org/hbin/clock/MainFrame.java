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
 * �ļ����� Clock.java CopyRight (c) 2010 
 * �ļ���ţ� 002 
 * �����ˣ� ��� 
 * ���ڣ� 2010.10.31 
 * �޸��ˣ� 
 * ���ڣ�
 * ������ һ��ʱ�ӳ��򣬿�����ʾϵͳ��ǰ�����ڡ�ʱ�� �汾�ţ� 1.0
 */
public class MainFrame {

	public static void main(String[] args) {
		init();
	}

	public static void init() {
		Clock clock = new Clock();
		JFrame frame = new JFrame("�ӱ� -���-1.0");

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
