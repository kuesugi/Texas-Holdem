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

public class resultFrame extends JFrame implements ActionListener {

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
		label.setFont(new Font("Luminari", Font.PLAIN, 18));
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label, BorderLayout.CENTER);
		
		JButton close = new JButton("Close");
		
		close.setBackground(new Color(50, 205, 50));
		close.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);	
	        }
	    });
		frame.getContentPane().add(close, BorderLayout.SOUTH);
		frame.setBounds(100, 100, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
