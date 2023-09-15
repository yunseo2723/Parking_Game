import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.*;

public class Map extends JPanel {
    public CarMovementFrame parent;
    public int currentMap;
    public Rectangle[] objects;
    public Rectangle parkingSpace;
    public Image[] background;
	public etc etc;

    public Map() {
        currentMap = 1;
        objects = new Rectangle[6];
        parkingSpace = null;

        ImageIcon[] imageIcons = {
        		new ImageIcon("Image/001.jpg"),
                new ImageIcon("Image/002.jpg"),
                new ImageIcon("Image/003.jpg"),
                new ImageIcon("Image/004.jpg")
        };
        
        background = new Image[imageIcons.length];
        for (int i = 0; i < imageIcons.length; i++) {
            background[i] = imageIcons[i].getImage();
        }
    }

    public void map1() {
        objects[0] = new Rectangle(330, 100, 100, 200); // Car
        objects[1] = new Rectangle(-200, 340, 100, 200); // Yellow
        objects[2] = new Rectangle(-200, 100, 100, 200); // Blue
        objects[3] = new Rectangle(-330, 800, 100, 200); // Red
        objects[4] = new Rectangle(-650, 340, 100, 200); // White
        objects[5] = new Rectangle(-640, 800, 100, 200); // Gray
        
        // 주차 공간 설정
        parkingSpace = new Rectangle(445, 700, 125, 225);
        currentMap = 1;
    }

    public void map2() {
        objects[0] = new Rectangle(330, 100, 100, 200); // Car
        objects[1] = new Rectangle(-330, 340, 100, 200); // Yellow
        objects[2] = new Rectangle(510, 30, 100, 200); // Blue
        objects[3] = new Rectangle(-330, 800, 100, 200); // Red
        objects[4] = new Rectangle(510, 270, 100, 200); // White
        objects[5] = new Rectangle(510, 770, 100, 200); // Gray
        
        // 주차 공간 설정
        parkingSpace = new Rectangle(485, 510, 170, 240);
        currentMap = 2;

    }

    public void map3() {
        objects[0] = new Rectangle(330, 100, 100, 200); // Car
        objects[1] = new Rectangle(330, 340, 100, 200); // Yellow
        objects[2] = new Rectangle(650, 100, 100, 200); // Blue
        objects[3] = new Rectangle(330, 800, 100, 200); // Red
        objects[4] = new Rectangle(650, 340, 100, 200); // White
        objects[5] = new Rectangle(640, 800, 100, 200); // Gray
        
        // 주차 공간 설정
        parkingSpace = new Rectangle(100, 100, 200, 100);
        currentMap = 3;

    }

    public void map4() {
        objects[0] = new Rectangle(330, 100, 100, 200); // Car
        objects[1] = new Rectangle(330, 340, 100, 200); // Yellow
        objects[2] = new Rectangle(650, 100, 100, 200); // Blue
        objects[3] = new Rectangle(330, 800, 100, 200); // Red
        objects[4] = new Rectangle(650, 340, 100, 200); // White
        objects[5] = new Rectangle(640, 800, 100, 200); // Gray
        
        // 주차 공간 설정
        parkingSpace = new Rectangle(100, 100, 200, 100);
        currentMap = 4;

    }

    
}
    

