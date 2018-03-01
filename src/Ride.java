public class Ride {
    Coordinate from;
    Coordinate to;
    int earliestStart;
    int deadline;

    public Ride(Coordinate from, Coordinate to, int earliestStart, int deadline) {
        this.from = from;
        this.to = to;
        this.earliestStart = earliestStart;
        this.deadline = deadline;
    }
}
