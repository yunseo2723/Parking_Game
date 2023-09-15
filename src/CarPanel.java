import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class CarPanel extends JPanel {
    public CarMovementFrame parent;
    public Map map;
    public etc etc;
    public CarPanel(CarMovementFrame parent, Map map, etc etc) {
        this.parent = parent;
        this.map = map;
        this.etc = etc;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if(map.currentMap == 1) {
        	g2d.drawImage(map.background[map.currentMap-1], 0, 0, 1000,1000, null);
        	addRetryButton();
        	addNextButton();
        }
        else if(map.currentMap == 2) {
        	g2d.drawImage(map.background[map.currentMap-1], 0, 0, 1000,1000, null);
    		addReturnButton();
    		addRetryButton();
    		addNextButton();
        }
        else if(map.currentMap == 3) {
        	g2d.drawImage(map.background[map.currentMap-1], 0, 0, 1000,1000, null);
			addReturnButton();
			addRetryButton();
			addNextButton();
        }
        else if(map.currentMap == 4) {
        	g2d.drawImage(map.background[map.currentMap-1], 0, 0, 1000,1000, null);
			addReturnButton();
			addRetryButton();
        }
        	
        // �ѱ��� �����ϴ� ��Ʈ�� ����
        Font koreanFont = new Font("���� ���", Font.PLAIN, 24);
        g.setFont(koreanFont);

        AffineTransform oldTransform = g2d.getTransform();

        if (isCarInParkingSpace() && parent.spaceBarClicked==true) {
        	  Dialog dialog = new Dialog((Frame) SwingUtilities.getWindowAncestor(map), parent, map, etc);
              parent.spaceBarClicked = false;
        	  dialog.showDialog();
        }
        
        // ���� ���� �׸���
        if (isCarInParkingSpace()) {
        	if(etc.showText) {
                Font originalFont = g.getFont(); // ���� ��Ʈ ����
                g.setFont(originalFont.deriveFont(Font.BOLD));
                g.drawString("�����̽��� Ŭ��!", 800, 30);
                g.setFont(originalFont); // ���� ��Ʈ�� �ǵ�����
        	}
            // ���������� ������ ���� ��� �ʷϻ����� ǥ��
            g2d.setColor(Color.GREEN);
            // �ǽð� ������Ʈ�� ���� �ٽ� �׸���
        } else if (isCarPartiallyInParkingSpace()) {
            // ���������� ��¦ ���� ��� ��������� ǥ��
            g2d.setColor(Color.YELLOW);
            // �ǽð� ������Ʈ�� ���� �ٽ� �׸���
        } else {
            // �������� �ۿ� �ִ� ��� ������� ǥ��
            g2d.setColor(Color.WHITE);
            // �ǽð� ������Ʈ�� ���� �ٽ� �׸���
        }
        
        g2d.setStroke(new BasicStroke(15)); // ���� �β� ����
        g.drawRect(map.parkingSpace.x, map.parkingSpace.y, map.parkingSpace.width, map.parkingSpace.height);

        g2d.translate(parent.playerCar.positionX + parent.map.objects[0].width / 2, parent.playerCar.positionY + parent.map.objects[0].height / 2);
        g2d.rotate(Math.toRadians(parent.playerCar.rotationAngle));

        g2d.drawImage(parent.images[0], -parent.map.objects[0].width / 2, -parent.map.objects[0].height / 2, parent.map.objects[0].width, parent.map.objects[0].height, this);

        g2d.setTransform(oldTransform);
        etc.timer.start();
        g.setColor(Color.BLACK);
        g.drawString("��� �ð�: " + etc.timeElapsed + "��", 10, 30);
        repaint();
        
        for (int i = 1; i < parent.map.objects.length; i++) {
            Rectangle object = map.objects[i];
            g2d.drawImage(parent.images[i], object.x, object.y, object.width, object.height, this);
        }
        
    }
    
    public boolean isCarInParkingSpace() {
        // ���� ������ ������ ��ġ �� ũ�� ���� ��������
        int carLeft = parent.playerCar.positionX;
        int carRight = parent.playerCar.positionX + parent.map.objects[0].width;
        int carTop = parent.playerCar.positionY;
        int carBottom = parent.playerCar.positionY + parent.map.objects[0].height;

        int spaceLeft = map.parkingSpace.x;
        int spaceRight = map.parkingSpace.x + map.parkingSpace.width;
        int spaceTop = map.parkingSpace.y;
        int spaceBottom = map.parkingSpace.y + map.parkingSpace.height;

        // ���� ������ ������ ��ġ ��
        return carLeft >= spaceLeft && carRight <= spaceRight && carTop >= spaceTop && carBottom <= spaceBottom;
    }

    public boolean isCarPartiallyInParkingSpace() {
        // ���� ������ ������ ��ġ �� ũ�� ���� ��������
        int carLeft = parent.playerCar.positionX;
        int carRight = parent.playerCar.positionX + parent.map.objects[0].width;
        int carTop = parent.playerCar.positionY;
        int carBottom = parent.playerCar.positionY + parent.map.objects[0].height;

        int spaceLeft = map.parkingSpace.x;
        int spaceRight = map.parkingSpace.x + map.parkingSpace.width;
        int spaceTop = map.parkingSpace.y;
        int spaceBottom = map.parkingSpace.y + map.parkingSpace.height;

        // ���� ������ ������ ��ġ ��
        return (carLeft < spaceRight && carRight > spaceLeft && carTop < spaceBottom && carBottom > spaceTop);

    }
	public void addRetryButton() {
    	JButton Retry = new JButton(new ImageIcon("Image/Retry.png"));
    	Retry.setSize(100, 100);
    	Retry.setLocation(70, 50);
    	Retry.setBorderPainted(false);
    	Retry.setFocusPainted(false);
    	Retry.setContentAreaFilled(false);//�������ȭ
    	Retry.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
                if (parent.map.currentMap == 1) {
                    parent.swapPanel(CarMovementFrame.MAP_1);
                } else if (parent.map.currentMap == 2) {
                    parent.swapPanel(CarMovementFrame.MAP_2);
                } else if (parent.map.currentMap == 3) {
                    parent.swapPanel(CarMovementFrame.MAP_3);
                } else if (parent.map.currentMap == 4) {
                    parent.swapPanel(CarMovementFrame.MAP_4);
                }
                Retry.setIcon(new ImageIcon("Image/Retry.png"));
                // Ÿ�̸� �ʱ�ȭ
                etc.timeElapsed = 0;
                etc.timer.restart();
            }
        	public void mouseEntered(MouseEvent e) {
        		Retry.setIcon(new ImageIcon("Image/Retry.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Retry.setIcon(new ImageIcon("Image/Retry.png"));
        	}
        });
        add(Retry);
    }
	public void addReturnButton() {
    	JButton Return = new JButton(new ImageIcon("Image/Return.png"));
    	Return.setSize(100, 100);
    	Return.setLocation(0, 50);
    	Return.setBorderPainted(false);
    	Return.setFocusPainted(false);
    	Return.setContentAreaFilled(false);//�������ȭ
    	Return.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                if (parent.map.currentMap == 2) {
                    parent.swapPanel(CarMovementFrame.MAP_1);
                } else if (parent.map.currentMap == 3) {
                    parent.swapPanel(CarMovementFrame.MAP_2);
                } else if (parent.map.currentMap == 4) {
                    parent.swapPanel(CarMovementFrame.MAP_3);
                }
                etc.timeElapsed = 0;
                etc.timer.restart();
                Return.setIcon(new ImageIcon("Image/Return.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Return.setIcon(new ImageIcon("Image/Return.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Return.setIcon(new ImageIcon("Image/Return.png"));
        	}
        });
        add(Return);
    }
	public void addNextButton() {
    	JButton Next = new JButton(new ImageIcon("Image/Next.png"));
    	Next.setSize(100, 100);
    	Next.setLocation(140, 50);
    	Next.setBorderPainted(false);
    	Next.setFocusPainted(false);
    	Next.setContentAreaFilled(false);//�������ȭ
    	Next.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
                if (parent.map.currentMap == 1) {
                    parent.swapPanel(CarMovementFrame.MAP_2);
                } else if (parent.map.currentMap == 2) {
                    parent.swapPanel(CarMovementFrame.MAP_3);
                } else if (parent.map.currentMap == 3) {
                    parent.swapPanel(CarMovementFrame.MAP_4);
                }
                etc.timeElapsed = 0;
                etc.timer.restart();
                Next.setIcon(new ImageIcon("Image/Next.png"));
        	}
        	public void mouseEntered(MouseEvent e) {
        		Next.setIcon(new ImageIcon("Image/Next.png"));
        	}
        	public void mouseExited(MouseEvent e) {
        		Next.setIcon(new ImageIcon("Image/Next.png"));
        	}
        });
        add(Next);
    }
}
