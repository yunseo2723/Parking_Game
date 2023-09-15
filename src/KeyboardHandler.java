import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class KeyboardHandler implements KeyListener {
    public CarMovementFrame parent;
    public Map map;
    
    public KeyboardHandler(CarMovementFrame parent, Map map) {
        this.parent = parent;
        this.map = map;
        // KeyListener 등록
        parent.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            parent.upKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            parent.downKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            parent.rightKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            parent.leftKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            parent.spaceBarClicked = true;
        }

        updateCarMovement(); // 차량 움직임 업데이트
        parent.repaint(); // 차량 이동 후 화면 다시 그리기
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        parent.playerCar.speed=0;
        if (keyCode == KeyEvent.VK_UP) {
            parent.upKeyPressed = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            parent.downKeyPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            parent.rightKeyPressed = false;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            parent.leftKeyPressed = false;
        }
        updateCarMovement(); // 차량 움직임 업데이트
        parent.repaint(); // 차량 이동 후 화면 다시 그리기
    }

    @Override
    public void keyTyped(KeyEvent e) {
        updateCarMovement(); // 차량 움직임 업데이트
        parent.repaint(); // 차량 이동 후 화면 다시 그리기
    }

    public void updateCarMovement() {
        int deltaX = 0;
        int deltaY = 0;
        int rotationAngle = parent.playerCar.rotationAngle; // 이전 회전 각도 유지
        double angle = Math.toRadians(rotationAngle);
        if (parent.upKeyPressed && parent.rightKeyPressed) {
            rotationAngle += 2;
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(-parent.playerCar.getSpeed() * Math.cos(angle));
        } else if (parent.upKeyPressed && parent.leftKeyPressed) {
            rotationAngle -= 2;
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(-parent.playerCar.getSpeed() * Math.cos(angle));
        } else if (parent.downKeyPressed && parent.rightKeyPressed) {
            rotationAngle -= 2;
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(-parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(parent.playerCar.getSpeed() * Math.cos(angle));
        } else if (parent.downKeyPressed && parent.leftKeyPressed) {
            rotationAngle += 2;
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(-parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(parent.playerCar.getSpeed() * Math.cos(angle));
        } else if (parent.upKeyPressed) {
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(-parent.playerCar.getSpeed() * Math.cos(angle));
        } else if (parent.downKeyPressed) {
            parent.playerCar.speedUp();
            deltaX = (int) Math.round(-parent.playerCar.getSpeed() * Math.sin(angle));
            deltaY = (int) Math.round(parent.playerCar.getSpeed() * Math.cos(angle));
        }

        // 차량과의 충돌 검사
        if (!checkCollisions(deltaX, deltaY)) {
            parent.playerCar.update(deltaX, deltaY, rotationAngle);
        } else {
            // 충돌이 발생한 경우 움직임을 멈춤
            parent.playerCar.speed = 0;
        }
        if (parent.playerCar.positionX < 50) {
        	parent.playerCar.positionX = 50;
        } else if (parent.playerCar.positionX > 1000 - parent.map.objects[0].width) {
        	parent.playerCar.positionX = 1000 - parent.map.objects[0].width;
        }
        
        // y 좌표 제한
        if (parent.playerCar.positionY < 0) {
        	parent.playerCar.positionY = 0;
        } else if (parent.playerCar.positionY > 1000 - parent.map.objects[0].height) {
        	parent.playerCar.positionY = 1000 - parent.map.objects[0].height;
        }
    }

    public boolean checkCollisions(int deltaX, int deltaY) {
        int playerX = parent.playerCar.positionX + deltaX;
        int playerY = parent.playerCar.positionY + deltaY;
        // 픽셀 기반 충돌 감지
        BufferedImage playerImage = parent.images[0]; // 플레이어 차량 이미지
        int playerWidth = playerImage.getWidth();
        int playerHeight = playerImage.getHeight();

        for (int i = 1; i < parent.map.objects.length; i++) {
            BufferedImage objectImage = parent.images[i];

            int objectX = parent.map.objects[i].x;
            int objectY = parent.map.objects[i].y;
            int objectWidth = objectImage.getWidth();
            int objectHeight = objectImage.getHeight();

            // 두 이미지 사이의 충돌 영역 계산
            int xMin = Math.max(playerX, objectX);
            int xMax = Math.min(playerX + playerWidth, objectX + objectWidth);
            int yMin = Math.max(playerY, objectY);
            int yMax = Math.min(playerY + playerHeight, objectY + objectHeight);

            // 충돌 영역 내의 픽셀 비교
            for (int x = xMin; x < xMax; x++) {
                for (int y = yMin; y < yMax; y++) {
                    int playerPixel = playerImage.getRGB(x - playerX, y - playerY);
                    int objectPixel = objectImage.getRGB(x - objectX, y - objectY);

                    // 투명하지 않은 픽셀이 겹치면 충돌로 간주
                    if ((playerPixel & 0xffffffff) == 0 && (objectPixel & 0xffffffff) == 0) {
                        return true; // 충돌이 발생한 경우
                    }
                }
            }
        }

        return false; // 충돌이 발생하지 않은 경우
    }
}