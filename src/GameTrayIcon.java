import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalIconFactory;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;



public class GameTrayIcon {

	protected static boolean displayMessage;
	protected static TrayIcon icon;
	private GameList gameList;
	
	public GameTrayIcon(GameList getList){
		gameList = getList;
	}
	
	public void setupIcon() throws AWTException, InterruptedException {
		Icon defaultIcon = MetalIconFactory.getTreeComputerIcon();
		String imgString = "C:\\Users\\t5gh5gg3\\Pictures\\TempFile.jpg";
		Image img = new BufferedImage(defaultIcon.getIconWidth(), defaultIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
 
        defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);
		Image iconImg = new ImageIcon(imgString).getImage();
		
		TrayIcon icon = new TrayIcon(img, "MMH Game Finder", setupPopup());
		icon.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						if(gameList.mainFrame.getExtendedState() == JFrame.ICONIFIED){
							int state = gameList.mainFrame.getExtendedState();
							state = state & JFrame.MAXIMIZED_BOTH;
							gameList.mainFrame.setExtendedState(state);
							gameList.mainFrame.toFront();
							gameList.mainFrame.requestFocus();
							gameList.mainFrame.repaint();
							gameList.mainFrame.setVisible(true);
							gameList.mainFrame.setAlwaysOnTop(true);
							gameList.mainFrame.setAlwaysOnTop(false);
						}
					}
				});
			}
		});

		setIcon(icon);
	}
	
	public void displayMessage(String gameName, String incomingGames){
		if(displayMessage){
			icon.displayMessage("Game Hosted", "Game Name(s): "+ gameName+
					"\nIncoming Games: "+incomingGames, TrayIcon.MessageType.INFO);
		}
	}
	
	public void setIcon(TrayIcon icon){
		this.icon = icon;
	}
	
	public PopupMenu setupPopup(){
		PopupMenu popup = new PopupMenu();
		displayMessage = true;
		
		MenuItem disableMessages = new MenuItem("Disable Messages");
		disableMessages.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				displayMessage = false;
				gameList.disableMessages.setSelected(true);
			}
		});

		MenuItem enableMessages = new MenuItem("Enable Messages");
		enableMessages.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				displayMessage = true;
				gameList.enableMessages.setSelected(true);
			}
		});
		
		MenuItem esc = new MenuItem("Exit");
		esc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		popup.add(enableMessages);
		popup.add(disableMessages);
		popup.add(esc);
		return popup;
	}
}
