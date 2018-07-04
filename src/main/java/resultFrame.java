import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class resultFrame extends JFrame {

	private JFrame frame;

	/**
	 * Create the application.
	 * @param mainFrame 
	 * @param players 
	 * @param user 
	 */
	public resultFrame(String result, Player user, ArrayList<Player> players, MainFrame mainFrame) {
		initialize(result, user, players, mainFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param mainFrame 
	 * @param players 
	 * @param user 
	 */
	private void initialize(String result, Player user, ArrayList<Player> players, MainFrame mainFrame) {
		frame = new JFrame();
        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel mainPanel = new JPanel(new BorderLayout());
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		
		JLabel label = new JLabel(result);
		label.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.BOLD, 15));
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel bustedOrWin = new JLabel();
		
		
		JButton quitButton = new JButton("Quit");
		JButton restart = new JButton("Restart");
		JButton nextHandButton = new JButton("Next Hand");

        btnPnl.add(restart);
        btnPnl.add(quitButton);
        btnPnl.add(nextHandButton);
        
		if (user.getStack() == 0) {
        	nextHandButton.setVisible(false);
        	bustedOrWin.setText("You busted out!");
        	btnPnl.add(bustedOrWin);
        	btnPnl.revalidate();
        }
		
        btnPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.setBackground(new Color(0, 128, 0));

        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(btnPnl, BorderLayout.SOUTH);
        
		quitButton.setBackground(new Color(50, 205, 50));
		quitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.exit(0);	
	        }
	    });
		
		restart.setBackground(new Color(50, 205, 50));
		restart.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				mainFrame.dispose();
				frame.setVisible(false);
	            MainMenu game = new MainMenu();
	            game.setVisible(true);	
	        }
	    });
		
        frame.add(mainPanel);
		frame.setBounds(100, 100, 1100, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		

		nextHandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				mainFrame.dispose();
				new MainFrame(user, players, frame);
				
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
	}

	
}
