import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;

//view
public class CarMovementFrame extends JFrame {
    public BeginningPanel beginningPanel; // 시작 메뉴 패널
    public SelectPanel selectPanel; // 단계선택 페이지
    public BackgroundMusic backgroundMusic;
    
    // 사용자의 버튼 클릭에 따라 패널을 갈아끼며 다양한 메뉴를 볼 수 있도록
    public static final int BEGINNING_PANEL = -1; // 시작메뉴 패널
    public static final int SETTING_PANEL = -0; // 설정 패널
    public static final int SELECT_PANEL = 1; // 단계선택 페이지
    public static final int MAP_1 = 2; // 단계선택 페이지
    public static final int MAP_2 = 3; // 단계선택 페이지
    public static final int MAP_3 = 4; // 단계선택 페이지
    public static final int MAP_4 = 5; // 단계선택 페이지

    // swapPanel함수를 통해 패널을 갈아낄 수 있도록 하기 위함
    public Car playerCar;
    public Map map;
    public etc etc;
	public Image background;
    public BufferedImage[] images;
    public boolean upKeyPressed;
    public boolean rightKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean spaceBarClicked;

    public CarMovementFrame(BackgroundMusic backgroundMusic) {
    	this.backgroundMusic = backgroundMusic;
        map = new Map();
        etc = new etc();
        images = new BufferedImage[6];
        add(map);
        ImageIcon[] imageIcons = {
        		new ImageIcon("Image/Black.png"),
                new ImageIcon("Image/Yellow.png"),
                new ImageIcon("Image/Blue.png"),
                new ImageIcon("Image/Red.png"),
                new ImageIcon("Image/White.png"),
                new ImageIcon("Image/Gray.png")
        };

        for (int i = 0; i < imageIcons.length; i++) {
            images[i] = loadImage(imageIcons[i].getImage());
        }

        setTitle("게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1015, 1045);

        upKeyPressed = false;
        rightKeyPressed = false;
        downKeyPressed = false;
        leftKeyPressed = false;
        spaceBarClicked = false;
        
        beginningPanel = new BeginningPanel(this);
        selectPanel = new SelectPanel(this);
        //settingPanel = new SettingPanel(backgroundMusic.getClip(), this);
        setContentPane(beginningPanel);
        //setContentPane(new CarPanel(this, map, etc));
        setVisible(true);
    }

    private BufferedImage loadImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }


    public void swapPanel(int panelID) {
        if (panelID == BEGINNING_PANEL) {
            setContentPane(beginningPanel);
        } else if (panelID == SELECT_PANEL) {
            setContentPane(selectPanel);
        } else if (panelID == MAP_1) {
            map.map1();
            playerCar = new Car(map.objects[0].x, map.objects[0].y, map, this); // 차의 좌표 초기화
            setContentPane(new CarPanel(this, map, etc));
        } else if (panelID == MAP_2) {
            map.map2();
            playerCar = new Car(map.objects[0].x, map.objects[0].y, map, this); // 차의 좌표 초기화
            setContentPane(new CarPanel(this, map, etc));
        } else if (panelID == MAP_3) {
            map.map3();
            playerCar = new Car(map.objects[0].x, map.objects[0].y, map, this); // 차의 좌표 초기화
            setContentPane(new CarPanel(this, map, etc));
        } else if (panelID == MAP_4) {
            map.map4();
            playerCar = new Car(map.objects[0].x, map.objects[0].y, map, this); // 차의 좌표 초기화
            setContentPane(new CarPanel(this, map, etc));
        }

        revalidate();
    }

}
