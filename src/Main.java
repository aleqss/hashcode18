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

        while (!pq.isEmpty() && pq.peek().timeFree < io.steps && !io.rides.isEmpty()) {
            List<Car> current = new ArrayList<>();
            current.add(pq.poll());
            while (!pq.isEmpty() && pq.peek().timeFree == current.get(0).timeFree)
                current.add(pq.poll());

            List<List<Ride>> decisions = new ArrayList<>(current.size());

            for (int i = 0; i < current.size(); i++) {
                decisions.set(i, new ArrayList<>(io.rides));
                Collections.sort(decisions.get(i), new Comparator<Ride>() {
                    @Override
                    public int compare (Ride firstRide, Ride secondRide) {
                        int fDist = current.get(i).loc.distanceTo(firstRide.from);
                        int sDist = current.get(i).loc.distanceTo(secondRide.from);

                        long idleA = Math.max(fDist, firstRide.earliestStart - current.get(i).timeFree);
                        long idleB = Math.max(sDist, secondRide.earliestStart - current.get(i).timeFree);

                        if (current.get(i).timeFree + idleA == firstRide.earliestStart) {
                            idleA -= io.bonus;
                        }

                        if (current.get(i).timeFree + idleB == secondRide.earliestStart) {
                            idleB -= io.bonus;
                        }

                        return Long.compare(idleA, idleB);
                    }
                });
            }

            List<Ride> bestPerCar = new ArrayList<>(current.size());

            for (int i = 0 ; i < decisions.size(); i++) {
                for (int j = 0; j < decisions.get(i).size(); j++) {
                    Ride ride = decisions.get(i).get(j);
                    if (canComplete(current.get(i), ride)) {
                        bestPerCar.set(i, ride);
                        break;
                    }
                }
            }

            while (current.size() > 0) {
                long bestIdle = Integer.MAX_VALUE;
                Ride bestRide = null;
                int carIndex = 0;

                for (int i = 0; i < bestPerCar.size(); i++) {
                    Ride ride = bestPerCar.get(i);
                    long idle = Math.max(current.get(i).loc.distanceTo(bestPerCar.get(i).from),
                        ride.earliestStart - current.get(i).timeFree);
                    if (idle + current.get(i).timeFree == ride.earliestStart)
                        idle -= io.bonus;

                    if (idle < bestIdle) {
                        bestIdle = idle;
                        bestRide = ride;
                        carIndex = i;
                    }
                }

                current.get(carIndex).scheduleRide(bestRide);
                for (int i = 0; i < decisions.size(); i++)
                    decisions.get(i).remove(bestRide);
                decisions.remove(carIndex);
                current.remove(carIndex);
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
