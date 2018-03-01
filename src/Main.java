import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static PriorityQueue<Car> pq = new PriorityQueue<>(Comparator.comparingLong(car -> car.timeFree));
    public static IO io = new IO();

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length<1){
            System.out.println("You didnt specify a file");
            return;
        }

        File file = new File(args[0]);
        io.readInput(file);

        for (int i = 0; i < io.vehicles; i++) {
            pq.add(new Car());
        }

        while (pq.peek().timeFree < io.steps && !io.rides.isEmpty()) {
            Car car = pq.poll();
            List<Ride> r = new ArrayList<>(io.rides);
            Collections.sort(r, new Comparator<Ride>() {
                @Override
                public int compare(Ride firstRide, Ride secondRide) {
                    int fDist = car.loc.distanceTo(firstRide.from);
                    int sDist = car.loc.distanceTo(secondRide.from);

                    long idleA = Math.max(fDist, firstRide.earliestStart - car.timeFree);
                    long idleB = Math.max(sDist, secondRide.earliestStart - car.timeFree);

                    if (car.timeFree + idleA == firstRide.earliestStart) {
                        idleA -= io.bonus;
                    }

                    if (car.timeFree + idleB == secondRide.earliestStart) {
                        idleB -= io.bonus;
                    }

                    return Long.compare(idleA, idleB);
                }
            });
            
        }
    }
}
