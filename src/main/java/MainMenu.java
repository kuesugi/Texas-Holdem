import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainMenu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private String playerName = null;
	private int num;
	private JTextField nameField = new JTextField();
	private JTextField numberField = new JTextField();
	private JLabel startWarning = new JLabel("");// Give a base from which to write any error messages
	JLabel avatar;
	Player user;
	static int whichTheme = 0;

	/**
	 * Sets size of the frame
	 */
	public MainMenu() {
		super("Texas Holdem");
		initFrame();
		// set the size of the frame
		setSize(700, 450);
		// click X to exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Must be the last line of this constructor
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel MainTitle = new JLabel("Texas Hold'em");
		MainTitle.setForeground(new Color(255, 255, 255));
		MainTitle.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 40));
		MainTitle.setHorizontalAlignment(SwingConstants.CENTER);

		nameField.setToolTipText("");
		nameField.setColumns(10);
		nameField.setText("");

		numberField.setToolTipText("");
		numberField.setColumns(10);
		numberField.setText("");

		JLabel nameLabel = new JLabel("Player Name:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 15));

		JLabel numLabel = new JLabel("# of Players:");
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numLabel.setForeground(new Color(255, 255, 255));
		numLabel.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 15));

		JLabel theme = new JLabel("Themes:");
		theme.setHorizontalAlignment(SwingConstants.CENTER);
		theme.setForeground(new Color(255, 255, 255));
		theme.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 15));

		JButton greenTheme = new JButton("Traditional");
		greenTheme.setForeground(new Color(43, 151, 0));
		greenTheme.setFont(new Font("Futura", Font.ITALIC, 17));
		greenTheme.addActionListener(this);
		
		JButton navyTheme = new JButton("Navy");
		navyTheme.setForeground(new Color(31, 114, 205));
		navyTheme.setFont(new Font("Futura", Font.ITALIC, 17));
		navyTheme.addActionListener(this);

		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 13));
		btnStart.addActionListener(this);

		JButton btnBrowse = new JButton("Load Avatar");
		btnBrowse.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 13));
		btnBrowse.addActionListener(this);

		startWarning.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 14));
		startWarning.setForeground(new Color(255, 255, 255));
		startWarning.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(MainTitle, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(MainTitle, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(65)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(65)
						.addComponent(numLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(numberField, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(65)
						.addComponent(theme, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(greenTheme, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addComponent(navyTheme, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addGap(245)
								.addComponent(startWarning, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE).addGap(245))
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addGap(181)
								.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(181, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup().addGap(181)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(181, Short.MAX_VALUE)));

		avatar = new JLabel();
		avatar.setBounds(10, 10, 45, 45);
		add(avatar);

		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addComponent(MainTitle, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE).addGap(46)
								.addComponent(startWarning)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(nameLabel))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(numberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(numLabel))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(greenTheme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(navyTheme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(theme))
								.addGap(28)
								.addComponent(btnBrowse)
								.addGap(15)
								.addComponent(btnStart)
								.addGap(15)));

		contentPane.setLayout(gl_contentPane);

		btnBrowse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				// filter the files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showOpenDialog(null);
				// if the user click on save in JFileChooser
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					avatar.setIcon(ResizeImage(path));
				}
				// if the user click on save in JFileChooser
				else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No File Select");
				}
			}
		});

		greenTheme.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(new Color(0, 128, 0));

				whichTheme = 0;
			}
		});

		navyTheme.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				contentPane.setBackground(new Color(0,0,255));
				
				whichTheme = 1;
			}
		});

		btnStart.addActionListener(new ActionListener() {
			/**
			 * activates when start button is pushed
			 */
			@Override

			public void actionPerformed(ActionEvent e) {					
				playerName = nameField.getText();
				num = Integer.parseInt(numberField.getText());
				//if player name is null a warning message will appear on screen
				if(playerName == null || nameField.getText().isEmpty())
					startWarning.setText("Player must enter name before starting");
								
				else if(num == 0 || num > 7 || numberField.getText().isEmpty())
					startWarning.setText("Not a valid number!");
								
				else {
					String[] opponents = { "Leopold Bloom", "Stephen Dedalus", "Yelverton Barry", "Buck Mulligan",
						"Martin Cunningham", "Molly Bloom", "Josie Breen" };
					ArrayList<Player> players = new ArrayList<>();
					for (int j = 0; j < num; j++) {
					try {
						players.add(new Player(true, opponents[j], 1000, j+1));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					user = new Player(false, playerName, 1000, 1);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					new MainFrame(user, players, whichTheme, avatar);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //starts the game passing the player name parameter
					setVisible(false);
					dispose();
				}
			}
		});
	}

	// Method to resize imageIcon with the same size of a JLabel
	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(avatar.getWidth(), avatar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
