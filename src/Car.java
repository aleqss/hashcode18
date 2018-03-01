import java.util.LinkedList;
import java.util.List;

public class Car {
    Coordinate loc = new Coordinate(0, 0);
    List<Ride> finishedRides = new LinkedList<>();
    long timeFree = 0;


    void scheduleRide(Ride r) {
        finishedRides.add(r);

        timeFree += loc.distanceTo(r.from);
        if (timeFree < r.earliestStart) {
            timeFree = r.earliestStart;
        }
        timeFree += r.from.distanceTo(r.to);

        loc = r.to;

        Main.pq.add(this);
    }
}
