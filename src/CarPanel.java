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
        	
        // 한글을 지원하는 폰트로 설정
        Font koreanFont = new Font("맑은 고딕", Font.PLAIN, 24);
        g.setFont(koreanFont);

        AffineTransform oldTransform = g2d.getTransform();

        if (isCarInParkingSpace() && parent.spaceBarClicked==true) {
        	  Dialog dialog = new Dialog((Frame) SwingUtilities.getWindowAncestor(map), parent, map, etc);
              parent.spaceBarClicked = false;
        	  dialog.showDialog();
        }
        
        // 주차 공간 그리기
        if (isCarInParkingSpace()) {
        	if(etc.showText) {
                Font originalFont = g.getFont(); // 원래 폰트 저장
                g.setFont(originalFont.deriveFont(Font.BOLD));
                g.drawString("스페이스바 클릭!", 800, 30);
                g.setFont(originalFont); // 원래 폰트로 되돌리기
        	}
            // 주차공간에 완전히 들어온 경우 초록색으로 표시
            g2d.setColor(Color.GREEN);
            // 실시간 업데이트를 위해 다시 그리기
        } else if (isCarPartiallyInParkingSpace()) {
            // 주차공간에 살짝 들어온 경우 노란색으로 표시
            g2d.setColor(Color.YELLOW);
            // 실시간 업데이트를 위해 다시 그리기
        } else {
            // 주차공간 밖에 있는 경우 흰색으로 표시
            g2d.setColor(Color.WHITE);
            // 실시간 업데이트를 위해 다시 그리기
        }
        
        g2d.setStroke(new BasicStroke(15)); // 선의 두께 설정
        g.drawRect(map.parkingSpace.x, map.parkingSpace.y, map.parkingSpace.width, map.parkingSpace.height);

        g2d.translate(parent.playerCar.positionX + parent.map.objects[0].width / 2, parent.playerCar.positionY + parent.map.objects[0].height / 2);
        g2d.rotate(Math.toRadians(parent.playerCar.rotationAngle));

        g2d.drawImage(parent.images[0], -parent.map.objects[0].width / 2, -parent.map.objects[0].height / 2, parent.map.objects[0].width, parent.map.objects[0].height, this);

        g2d.setTransform(oldTransform);
        etc.timer.start();
        g.setColor(Color.BLACK);
        g.drawString("경과 시간: " + etc.timeElapsed + "초", 10, 30);
        repaint();
        
        for (int i = 1; i < parent.map.objects.length; i++) {
            Rectangle object = map.objects[i];
            g2d.drawImage(parent.images[i], object.x, object.y, object.width, object.height, this);
        }
        
    }
    
    public boolean isCarInParkingSpace() {
        // 주차 공간과 차량의 위치 및 크기 정보 가져오기
        int carLeft = parent.playerCar.positionX;
        int carRight = parent.playerCar.positionX + parent.map.objects[0].width;
        int carTop = parent.playerCar.positionY;
        int carBottom = parent.playerCar.positionY + parent.map.objects[0].height;

        int spaceLeft = map.parkingSpace.x;
        int spaceRight = map.parkingSpace.x + map.parkingSpace.width;
        int spaceTop = map.parkingSpace.y;
        int spaceBottom = map.parkingSpace.y + map.parkingSpace.height;

        // 주차 공간과 차량의 위치 비교
        return carLeft >= spaceLeft && carRight <= spaceRight && carTop >= spaceTop && carBottom <= spaceBottom;
    }

    public boolean isCarPartiallyInParkingSpace() {
        // 주차 공간과 차량의 위치 및 크기 정보 가져오기
        int carLeft = parent.playerCar.positionX;
        int carRight = parent.playerCar.positionX + parent.map.objects[0].width;
        int carTop = parent.playerCar.positionY;
        int carBottom = parent.playerCar.positionY + parent.map.objects[0].height;

        int spaceLeft = map.parkingSpace.x;
        int spaceRight = map.parkingSpace.x + map.parkingSpace.width;
        int spaceTop = map.parkingSpace.y;
        int spaceBottom = map.parkingSpace.y + map.parkingSpace.height;

        // 주차 공간과 차량의 위치 비교
        return (carLeft < spaceRight && carRight > spaceLeft && carTop < spaceBottom && carBottom > spaceTop);

    }
	public void addRetryButton() {
    	JButton Retry = new JButton(new ImageIcon("Image/Retry.png"));
    	Retry.setSize(100, 100);
    	Retry.setLocation(70, 50);
    	Retry.setBorderPainted(false);
    	Retry.setFocusPainted(false);
    	Retry.setContentAreaFilled(false);//배경투명화
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
                // 타이머 초기화
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
    	Return.setContentAreaFilled(false);//배경투명화
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
    	Next.setContentAreaFilled(false);//배경투명화
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
