import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	BackgroundMusic backgroundMusic = new BackgroundMusic("Music/BackgroundMusic.wav");
            Map map = new Map();
            CarMovementFrame main = new CarMovementFrame(backgroundMusic);
            KeyboardHandler key = new KeyboardHandler(main, map);
            
            // 프레임을 보이도록 설정
            main.setVisible(true);
            backgroundMusic.play();
            // 키 입력을 받을 수 있도록 포커스 설정
            main.setFocusable(true);
            main.requestFocus();
        });
    }
}
