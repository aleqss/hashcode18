public class Ride {
    Coordinate from;
    Coordinate to;
    int earliestStart;
    int deadline;
    int index;

    public Ride(Coordinate from, Coordinate to, int earliestStart, int deadline, int index) {
        this.from = from;
        this.to = to;
        this.earliestStart = earliestStart;
        this.deadline = deadline;
        this.index = index;
    }
}
