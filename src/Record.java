
public class Record implements Comparable<Record> {
    public String nickname;
    public int timeElapsed;

    public Record(String nickname, int timeElapsed) {
        this.nickname = nickname;
        this.timeElapsed = timeElapsed;
    }

    public String getNickname() {
        return nickname;
    }

    public int getElapsedTime() {
        return timeElapsed;
    }
    
    @Override
    public String toString() {
        return nickname + "," + timeElapsed;
    }
    
    @Override
    public int compareTo(Record other) {
            // 
            if (this.timeElapsed < other.timeElapsed) {
                return -1;
            } else if (this.timeElapsed > other.timeElapsed) {
                return 1;
            } else {
                return 0;
            }
        }
    }