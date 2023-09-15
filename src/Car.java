
public class Car {
    public int positionX;
    public int positionY;
    public int rotationAngle;
    public double speed = 0;
    public final double MAX_SPEED = 20.0;
    public final double ACCELERATION = 1;
    public final double DECELERATION = 0.1;
    public Map map;
    public CarMovementFrame parent;

    public Car(int x, int y, Map map, CarMovementFrame parent) {
        positionX = x;
        positionY = y;
        rotationAngle = 0;
        this.map = map;
        this.parent = parent;
    }
    public void update(int deltaX, int deltaY, int rotationAngle) {
        positionX += deltaX;
        positionY += deltaY;
        this.rotationAngle = rotationAngle;
        System.out.println(positionX);
        System.out.println(positionY);
    }

    public void speedUp() {
        if (speed < MAX_SPEED) {
            speed += ACCELERATION;
            if (speed > MAX_SPEED) {
                speed = MAX_SPEED;
            }
        }
    }

    public double getSpeed() {
        return speed;
    }
}
