import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectPanel extends JPanel {
    public CarMovementFrame parent;

    public ImageIcon bgImageicon = new ImageIcon("Image/SelectBack.png");
    public Image backgroundPanelImage = bgImageicon.getImage();

    public SelectPanel(CarMovementFrame parent) {
    	this.parent = parent;

        setLayout(null);
        
        JButton Step1 = new JButton(new ImageIcon("Image/Step1.png"));
        Step1.setSize(600, 120);
        Step1.setLocation(200, 300);
        Step1.setBorderPainted(false);
        Step1.setFocusPainted(false);
        Step1.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                parent.swapPanel(CarMovementFrame.MAP_1);
                Step1.setIcon(new ImageIcon("Image/Step1.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Step1.setIcon(new ImageIcon("Image/Step1_focus.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Step1.setIcon(new ImageIcon("Image/Step1.png"));
        	}
        });
        add(Step1);
        
        JButton Step2 = new JButton(new ImageIcon("Image/Step2.png"));
        Step2.setSize(600, 120);
        Step2.setLocation(200, 440);
        Step2.setBorderPainted(false);
        Step2.setFocusPainted(false);
        Step2.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                parent.swapPanel(CarMovementFrame.MAP_2);
                Step2.setIcon(new ImageIcon("Image/Step2.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Step2.setIcon(new ImageIcon("Image/Step2_focus.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Step2.setIcon(new ImageIcon("Image/Step2.png"));
        	}
        });
        add(Step2);
        
        JButton Step3 = new JButton(new ImageIcon("Image/Step3.png"));
        Step3.setSize(600, 120);
        Step3.setLocation(200, 580);
        Step3.setBorderPainted(false);
        Step3.setFocusPainted(false);
        Step3.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                parent.swapPanel(CarMovementFrame.MAP_3);
                Step3.setIcon(new ImageIcon("Image/Step3.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Step3.setIcon(new ImageIcon("Image/Step3_focus.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Step3.setIcon(new ImageIcon("Image/Step3.png"));
        	}
        });
        add(Step3);
        
        JButton Step4 = new JButton(new ImageIcon("Image/Step4.png"));
        Step4.setSize(600, 120);
        Step4.setLocation(200, 720);
        Step4.setBorderPainted(false);
        Step4.setFocusPainted(false);
        Step4.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                parent.swapPanel(CarMovementFrame.MAP_4);
                Step4.setIcon(new ImageIcon("Image/Step4.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Step4.setIcon(new ImageIcon("Image/Step4_focus.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Step4.setIcon(new ImageIcon("Image/Step4.png"));
        	}
        });
        add(Step4);
    	JButton Back = new JButton(new ImageIcon("Image/Back.png"));
    	Back.setSize(600, 120);
    	Back.setLocation(200, 860);
    	Back.setBorderPainted(false);
    	Back.setFocusPainted(false);
    	Back.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
    			parent.swapPanel(CarMovementFrame.BEGINNING_PANEL);
    			Back.setIcon(new ImageIcon("Image/Back.png"));
    		}
    		public void mouseEntered(MouseEvent e) {
    			Back.setIcon(new ImageIcon("Image/Back_focus.png"));
    		}
    		public void mouseExited(MouseEvent e) {
    			Back.setIcon(new ImageIcon("Image/Back.png"));
    		}
    	});
    	add(Back);
	}
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundPanelImage, 0, 0, bgImageicon.getIconWidth(), bgImageicon.getIconHeight(), null);
    }
}