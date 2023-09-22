package SelectionWheel;

import javax.swing.*;

import Controller.casinoController;
import Model.casinoMusic;

import java.awt.Color;
import java.awt.Font;
import java.util.*;


public class MainWheel {

	public int result = 0;
	
	public int getResult() {
		return result;
	}
	
	public MainWheel()  {

		int width = 1000, height = 1000;

		JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("10");
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("5");
		list.add("1");
		list.add("5");
		list.add("3");
		list.add("1");
		list.add("10");
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("5");
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("20");
		list.add("1");
		list.add("3");
		list.add("1");
		list.add("5");

		try {
			SelectionWheel wheel = new SelectionWheel(list);
			wheel.hasBorders(true);
			wheel.setBounds(10, 10, 700, 700);

			JLabel lbl1 = new JLabel("Selection: ");
			JLabel lbl2 = new JLabel("Angle: ");
			JLabel lbl3 = new JLabel("Speed: ");
			JLabel lblsel = new JLabel("(selection)");
			JLabel lblang = new JLabel("(angle)");
			JLabel lblsp = new JLabel("(speed)");
			lbl1.setBounds(720, 10, 100, 20);
			lblsel.setBounds(830, 10, 150, 20);
			lbl2.setBounds(720, 30, 100, 20);
			lblang.setBounds(830, 30, 150, 20);
			lbl3.setBounds(720, 50, 100, 20);
			lblsp.setBounds(830, 50, 150, 20);
			frame.add(wheel);
			frame.add(lbl1);
			frame.add(lblsel);
			frame.add(lbl2);
			frame.add(lblang);
			frame.add(lbl3);
			frame.add(lblsp);
			
			JLabel lbl4 = new JLabel("화면을 클릭하세요");
			
			lbl4.setBounds(150, 720, 700, 150);
			lbl4.setFont(new Font("Serif", Font.BOLD, 11));
			lbl4.setForeground(Color.BLACK);
			lbl4.setFont(lbl4.getFont().deriveFont(48.0f));

			frame.add(lbl4);
			
			frame.setSize(width, height);
			frame.setLayout(null);
			frame.setVisible(true);

			lblsel.setText(wheel.getSelectedString());
			lblang.setText(Double.toString(wheel.getRotationAngle()));
			lblsp.setText(Double.toString(wheel.getSpinSpeed()));

			// wheel.setShape(Wheel.Shape.UMBRELLA);

			int usable = 1;
			int usable2 = 1;
			
			while (true) {
				// wait for action
				while (true) {
					
					
					lblsel.setText(wheel.getSelectedString());
					lblang.setText(Double.toString(wheel.getRotationAngle()));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (wheel.isSpinning())
						break;
				}
				// while spinning
				while (wheel.isSpinning()) {
					lblsel.setText(wheel.getSelectedString());
					lblang.setText(Double.toString(wheel.getRotationAngle()));
					lblsp.setText(Double.toString(wheel.getSpinSpeed()));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lblsp.setText(Double.toString(wheel.getSpinSpeed()));
				// show selection
				JOptionPane.showMessageDialog(frame, "Selection: " + wheel.getSelectedString());
				
				if(usable == 1) {
					result = Integer.parseInt(wheel.getSelectedString());
					usable = 0;
				}

				break;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}