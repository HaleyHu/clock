package org.hbin.clock.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Calendar;

import javax.swing.JPanel;

/**
 * 
 * �ļ����� Clock.java
 * CopyRight (c) 2010
 * �ļ���ţ� 001
 * �����ˣ�  ���
 * ���ڣ� 2010.10.31
 * �޸��ˣ� 
 * ���ڣ� 
 * ������ һ��ʱ�ӳ��򣬿�����ʾϵͳ��ǰ�����ڡ�ʱ��
 * �汾�ţ� 1.0
 * ���䣺 335855364@qq.com
 *
 */
public class Clock extends JPanel
{
	private static final long serialVersionUID = -4408365836587843499L;

	/**
	 * Clock�����
	 */
	private final int WIDTH = 300;
	
	/**
	 * Clock���߶�
	 */
	private final int HEIGHT = 300;
	
	/**
	 * Clock��������
	 */
	private int centerX = WIDTH / 2;
	private int centerY = HEIGHT / 2;
	
	/**
	 * Clock�뾶
	 */
	private int radius = WIDTH/2 - 10;
	
	/**
	 * ʱ���֡���
	 */
	private int hour;
	private int minute;
	private int second;
	
	/**
	 * ������Ϣ
	 */
	private String dateInfo;
	
	/**
	 * ʱ��ĳ���
	 */
	private int hourHand = radius - 70;
	
	/**
	 * ����ĳ���
	 */
	private int minuteHand = radius - 50;
	
	/**
	 * ����ĳ���
	 */
	private int secondHand = radius - 30;
	
	public Clock()
	{
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
		this.dateInfo = "****��**��**��";

		this.setSize(WIDTH, HEIGHT);
	}
	
	public void start()
	{
		new Thread(new ClockDriver()).start();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		Color c = g2.getColor();
		Font f = g2.getFont();
		g2.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(new Color(0xafafaf));
		g2.fill3DRect(0, 0, getWidth(), getHeight(), true);
		
		/**
		 * ������
		 */
		g2.setColor(Color.BLACK);
		g2.fillOval(centerX-radius, centerY-radius, radius*2, radius*2);
		g2.setColor(new Color(0xcfcfcf));
		g2.fillOval(centerX-radius+5, centerY-radius+5, radius*2-10, radius*2-10);
		g2.setColor(Color.BLACK);
		
		/**
		 * ���̶�
		 */
		for(int i=1;i<=60;i++)
		{
			double markX1 = centerX + (radius-13) * Math.cos(Math.PI*(15-i)/30);
			double markY1 = centerY - (radius-13) * Math.sin(Math.PI*(15-i)/30);
			if(i%5==0)
			{
				markX1 = centerX + (radius-20) * Math.cos(Math.PI*(15-i)/30);
				markY1 = centerY - (radius-20) * Math.sin(Math.PI*(15-i)/30);
			}
			double markX2 = centerX + (radius-4) * Math.cos(Math.PI*(15-i)/30);
			double markY2 = centerY - (radius-4) * Math.sin(Math.PI*(15-i)/30);
			
			g2.drawLine((int)markX1, (int)markY1, (int)markX2, (int)markY2);
		}
		g2.setColor(Color.BLACK);
		
		
		/**
		 * ������ʱ��
		 */
		g2.setColor(Color.YELLOW);
		g2.setFont(new Font("����",Font.BOLD, 16));
		g2.drawString(dateInfo, centerX-dateInfo.length()*5, 80);
		
		String timeInfo = (hour>9?"":"0")+hour+":"+
			(minute>9?"":"0")+minute+":"+
			(second>9?"":"0")+second;
		g2.drawString(timeInfo, centerX-timeInfo.length()*4, 60);
		g2.setFont(f);
		
		g2.setColor(Color.RED);
		g2.setFont(new Font("����",Font.BOLD, 16));
		g2.drawString("���������", centerX-40, 230);
		g2.setFont(f);
		
		/**
		 * ��ʱ��
		 */
		double hourX = centerX + hourHand * Math.cos(Math.PI/180*((3-hour)*30-(double)minute/2));
		double hourY = centerY - hourHand * Math.sin(Math.PI/180*((3-hour)*30-(double)minute/2));
		g2.setColor(new Color(0x367517));//����ʱ����ɫ
		g2.setStroke(new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		g2.drawLine(centerX, centerY, (int)hourX, (int)hourY);
		
		/**
		 * ������
		 */
		double minuteX = centerX + minuteHand * Math.cos(Math.PI/180*((15-minute)*6-(double)second/10));
		double minuteY = centerY - minuteHand * Math.sin(Math.PI/180*((15-minute)*6-(double)second/10));
		g2.setColor(new Color(0x976D00));//���÷�����ɫ
		g2.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		g2.drawLine(centerX, centerY, (int)minuteX, (int)minuteY);
		
		/**
		 * ������
		 */
		double secondX = centerX + secondHand * Math.cos(Math.PI/180*(15-second)*6);
		double secondY = centerY - secondHand * Math.sin(Math.PI/180*(15-second)*6);
		g2.setColor(new Color(0xC0FF3E));//����������ɫ
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		g2.drawLine(centerX, centerY, (int)secondX, (int)secondY);

		// ����������
		g2.setColor(Color.BLACK);
		g2.fillOval(centerX-5, centerY-5, 10, 10);
		
		g2.setColor(c);
	}
	
	class ClockDriver implements Runnable
	{
		@Override
		public void run()
		{
			while(true)
			{
				Calendar cal = Calendar.getInstance();
				hour = cal.get(Calendar.HOUR_OF_DAY);
				minute = cal.get(Calendar.MINUTE);
				second = cal.get(Calendar.SECOND);
				
				dateInfo = cal.get(Calendar.YEAR) + "��" + 
					(cal.get(Calendar.MONTH)>9?"":"0")+cal.get(Calendar.MONTH) + "��" + 
					(cal.get(Calendar.DAY_OF_MONTH)>9?"":"0")+cal.get(Calendar.DAY_OF_MONTH) + "��";
				
				repaint();
				
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}