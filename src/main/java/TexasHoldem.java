import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class TexasHoldem{
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}
}