import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static PriorityQueue<Car> pq = new PriorityQueue<>(Comparator.comparingLong(car -> car.timeFree));
    public static IO io = new IO();

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 2) {
            System.out.println("You didnt specify a file");
            return;
        }

        File file = new File(args[0]);
        io.readInput(file);

        List<Car> cars = new ArrayList<>(io.vehicles);

        for (int i = 0; i < io.vehicles; i++) {
            Car car = new Car();
            pq.add(car);
            cars.add(car);
        }

        while (pq.peek().timeFree < io.steps && !io.rides.isEmpty()) {
            Car car = pq.poll();
            List<Ride> r = new ArrayList<>(io.rides);
            Collections.sort(r, (firstRide, secondRide) -> {
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
            });

            for (int i = 0; i < r.size(); i++) {
                Ride ride = r.get(i);
                if (canComplete(car, ride)) {
                    car.scheduleRide(ride);
                    pq.add(car);
                }
            }
        }

        IO.output(new File(args[1]), cars);
    }

    static boolean canComplete(Car car, Ride ride) {
        int fDist = car.loc.distanceTo(ride.from);
        long idleA = Math.max(fDist, ride.earliestStart - car.timeFree);
        if (idleA + car.timeFree + ride.from.distanceTo(ride.to) >= ride.deadline) {
            return false;
        } else {
            return true;
        }
    }
}
