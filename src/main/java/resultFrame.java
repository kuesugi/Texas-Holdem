import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.BorderFactory;
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
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel mainPanel = new JPanel(new BorderLayout());

		JLabel label = new JLabel(result);
		label.setFont(new Font("Luminari", Font.PLAIN, 18));
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton restart = new JButton("Restart");
		JButton close = new JButton("Close");

        btnPnl.add(restart);
        btnPnl.add(close);
        
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.setBackground(new Color(0, 128, 0));

        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(btnPnl, BorderLayout.SOUTH);

        
		close.setBackground(new Color(50, 205, 50));
		close.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);	
	        }
	    });
		
		restart.setBackground(new Color(50, 205, 50));
		restart.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        	frame.setVisible(false);
	            MainMenu game = new MainMenu();
	            game.setVisible(true);	
	        }
	    });
	
        frame.add(mainPanel);
		frame.setBounds(100, 100, 1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
