public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distanceTo(Coordinate other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    public static int distanceBetween(Coordinate o1, Coordinate o2) {
        return Math.abs(o1.x - o2.x) + Math.abs(o1.y - o2.y);
    }
}
