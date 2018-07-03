import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class resultFrame extends JFrame {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public resultFrame(String result) {
		initialize(result);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String result) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		
		JLabel label = new JLabel(result);
		label.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.BOLD, 31));
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 12));
		
		quitButton.setBackground(new Color(50, 205, 50));
		quitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);	
	        }
	    });
		
		JButton nextHandButton = new JButton("Next Hand");
		nextHandButton.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(nextHandButton, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(396)
							.addComponent(quitButton, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
					.addGap(396))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(nextHandButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		nextHandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
	}

	
}
