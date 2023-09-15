import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BeginningPanel extends JPanel {
	
    public CarMovementFrame parent;

    public ImageIcon bgImageicon = new ImageIcon("Image/StartBack.png");
    public Image backgroundPanelImage = bgImageicon.getImage();
    public BeginningPanel(CarMovementFrame parent) {
        this.parent = parent;
        setLayout(null);
            
            JButton startButton = new JButton(new ImageIcon("Image/Start.png"));
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setSize(600, 150);
            startButton.setLocation(200, 570);
            startButton.addMouseListener(new MouseAdapter() {
            	public void mouseClicked(MouseEvent e) {
                    parent.swapPanel(CarMovementFrame.SELECT_PANEL);
            		startButton.setIcon(new ImageIcon("Image/Start.png"));
            	}
                public void mouseEntered(MouseEvent e) {
                    startButton.setIcon(new ImageIcon("Image/Start_focus.png"));
                }
                public void mouseExited(MouseEvent e) {
                    startButton.setIcon(new ImageIcon("Image/Start.png"));
                }
            });
            add(startButton);
            
            JButton settingButton = new JButton(new ImageIcon("Image/Setting.png"));
            settingButton.setBorderPainted(false);
            settingButton.setFocusPainted(false);
            settingButton.setSize(100, 100);
            settingButton.setLocation(840, 800);
            settingButton.addMouseListener(new MouseAdapter() {
            	public void mouseClicked(MouseEvent e) {
                    parent.swapPanel(CarMovementFrame.SETTING_PANEL);
                    settingButton.setIcon(new ImageIcon("Image/Setting.png"));
            	}
                public void mouseEntered(MouseEvent e) {
                	settingButton.setIcon(new ImageIcon("Image/Setting_focus.png"));
                }
                public void mouseExited(MouseEvent e) {
                	settingButton.setIcon(new ImageIcon("Image/Setting.png"));
                }
            });
            add(settingButton);
        
        JButton exitButton = new JButton(new ImageIcon("Image/Exit.png"));
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setSize(600, 150);
        exitButton.setLocation(200, 770);
        exitButton.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		exitButton.setIcon(new ImageIcon("Image/Exit_focus.png"));
        	}
    
        	@Override
        	public void mouseExited(MouseEvent e) {
        		exitButton.setIcon(new ImageIcon("Image/Exit.png"));
        	}
        });
        exitButton.addActionListener(e -> {
        	System.exit(0);
        });
        add(exitButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundPanelImage, 0, 0, bgImageicon.getIconWidth(), bgImageicon.getIconHeight(), null);
    }
}