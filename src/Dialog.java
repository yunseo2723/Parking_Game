import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Dialog extends JDialog {
	private static final long serialVersionUID = 1L; 
	
    public CarMovementFrame parent;
    public Map map;
    public etc etc;
    public String nickname;
    public List<Record> ranking;

    public Dialog(Frame parentFrame, CarMovementFrame parent, Map map, etc etc) {
        super(parentFrame, true);
        setUndecorated(true);
        setLayout(new BorderLayout());
        
        JLabel messageLabel = new JLabel("Clear");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel additionalLabel = new JLabel();
        additionalLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel moveCountLabel = new JLabel("<html>걸린 시간: " + etc.timeElapsed + "초</html>");
        moveCountLabel.setHorizontalAlignment(JLabel.CENTER);

        this.parent = parent;
        this.map = map;
        this.etc = etc;
        this.ranking = new ArrayList<>();
        loadRanking();
        
        JButton backButton = new JButton("뒤로가기");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.swapPanel(CarMovementFrame.SELECT_PANEL);
                dispose();
            }
        });
        
        JButton retryButton = new JButton("다시하기");
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (parent.map.currentMap == 1) {
                    parent.swapPanel(CarMovementFrame.MAP_1);
                } else if (parent.map.currentMap == 2) {
                    parent.swapPanel(CarMovementFrame.MAP_2);
                } else if (parent.map.currentMap == 3) {
                    parent.swapPanel(CarMovementFrame.MAP_3);
                } else if (parent.map.currentMap == 4) {
                    parent.swapPanel(CarMovementFrame.MAP_4);
                }
                etc.timeElapsed = 0;
                etc.timer.restart();
                dispose();
            }
        });

        JButton nextButton = new JButton("다음단계");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parent.map.currentMap == 1) {
                    parent.swapPanel(CarMovementFrame.MAP_2);
                } else if (parent.map.currentMap == 2) {
                    parent.swapPanel(CarMovementFrame.MAP_3);
                } else if (parent.map.currentMap == 3) {
                    parent.swapPanel(CarMovementFrame.MAP_4);
                }
                etc.timeElapsed = 0;
                etc.timer.restart();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(retryButton);
        buttonPanel.add(nextButton);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        centerPanel.add(messageLabel);
        centerPanel.add(additionalLabel);
        centerPanel.add(moveCountLabel);
        
        int timeElapsed = etc.timeElapsed;
        
        JTextField nicknameField = new JTextField();
        nicknameField.setPreferredSize(new Dimension(200, 25));
        centerPanel.add(nicknameField);

        JPanel nicknamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nicknamePanel.add(new JLabel("닉네임: "));
        nicknamePanel.add(nicknameField);
        
        JButton confirmButton = new JButton("등록");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = nicknameField.getText();
                Record record = new Record(nickname, timeElapsed);
                boolean isRecordInserted = false;

                for (int i = 0; i < ranking.size(); i++) {
                    Record r = ranking.get(i);
                    if (record.compareTo(r) < 0) {
                        ranking.add(i, record);
                        isRecordInserted = true;
                        break;
                    }
                }

                if (!isRecordInserted && ranking.size() < 10) {
                    ranking.add(record);
                }

                if (ranking.size() > 10) {
                    ranking.remove(10);
                }

                // ��ŷ �˾� ����
                StringBuilder rankingMessage = new StringBuilder();
                String title = "---- 랭킹 ----";

                int padding = (50 - title.length()) / 2;
                String centeredTitle = String.format("%" + padding + "s%s%" + padding + "s", "", title, "");
                rankingMessage.append(centeredTitle).append("\n");
                
                for (int i = 0; i < ranking.size(); i++) {
                    Record r = ranking.get(i);
                    rankingMessage.append((i + 1) + ". " + r.getNickname() + ", 걸린시간: " + r.getElapsedTime() + "초\n");
                }
                JOptionPane.showMessageDialog(parent, rankingMessage.toString(), "랭킹", JOptionPane.INFORMATION_MESSAGE);
                saveRanking();
                confirmButton.setEnabled(false);
            }
        });
        nicknamePanel.add(confirmButton);
        centerPanel.add(nicknamePanel);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        ImageIcon star1Icon = new ImageIcon("Image/Star1.png");
        ImageIcon star2Icon = new ImageIcon("Image/Star2.png");
        ImageIcon star3Icon = new ImageIcon("Image/Star3.png");
        Image star1 = star1Icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image star2 = star2Icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
        Image star3 = star3Icon.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH);

        if (timeElapsed <= 20) {
            additionalLabel.setIcon(new ImageIcon(star3));
        } else if (timeElapsed <= 30) {
            additionalLabel.setIcon(new ImageIcon(star2));
        } else {
            additionalLabel.setIcon(new ImageIcon(star1));
        }

        setSize(500, 400);
        setLocationRelativeTo(parentFrame);
        setResizable(false);
    }
    
    public void showDialog() {
        
        setVisible(true);
    }
    
    public void saveRanking() {
        String[] fileNames = {"ranking1.txt", "ranking2.txt", "ranking3.txt", "ranking4.txt"};

        if (map.currentMap > 0 && map.currentMap <= 4) {
            String fileName = fileNames[map.currentMap - 1];

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                for (Record record : ranking) {
                    writer.println(record.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadRanking() {
        ranking.clear();

        String[] fileNames = {"ranking1.txt", "ranking2.txt", "ranking3.txt", "ranking4.txt"};

        if (map.currentMap >= 1 && map.currentMap <= 4) {
            String fileName = fileNames[map.currentMap - 1]; // 배열 인덱스는 0부터 시작하므로 맵 번호에 1을 빼줌

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String nickname = parts[0];
                        int timeElapsed = Integer.parseInt(parts[1]);
                        Record record = new Record(nickname, timeElapsed);
                        ranking.add(record);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 기록을 시간순으로 정렬
            Collections.sort(ranking, Comparator.comparingInt(Record::getElapsedTime));

            // 상위 10개 기록만 유지
            if (ranking.size() > 10) {
                ranking = ranking.subList(0, 10);
            }
        }
    }

}
