package inv;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class form extends JFrame {
	
	JButton btn = new JButton();
	
	ImageIcon bg = new ImageIcon (new ImageIcon("Inv.png").getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH));
	JLabel img = new JLabel();
	
	form(){
		
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Inventory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		img.setIcon(bg);
		img.setFocusable(false);
		img.setBounds(0,0,800,500);
		
		
		
		
		btn.setBounds(0,0,800,500);
		btn.setFocusable(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addActionListener(new ActionListener() 
		
		
		
		{
			
		//ADD BUTTON	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				new system();
				dispose();
				
			}
			
		});
		
		
		this.add(btn);
		this.add(img);
		setLayout(null);
		setVisible(true);
		
		
	}
	
		

}
