import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class etc extends JPanel {
    public Timer timer; // 타이머 변수
    public int timeElapsed; // 경과 시간 변수
    public CarMovementFrame parent;
    public boolean showText = true;
    
	public etc() {
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showText = !showText; // 상태를 반전시킴
                repaint(); // 다시 그리기 요청
            	timeElapsed++;
            }
        });
	}

}