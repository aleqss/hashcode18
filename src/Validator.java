import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Validator {
    public static IO io = new IO();

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Did not specify files");
        }

        System.out.println(calcScore(args[0], args[1]));
    }

    public static long calcScore(String inputPath, String outputPath) throws IOException {
        File input = new File(inputPath);
        File output = new File(outputPath);

        BufferedReader br = new BufferedReader(new FileReader(output));

        io.readInput(input);

        HashSet<Ride> completed = new HashSet<>();

        long scoreSum = 0;
        for (int i = 0; i < io.vehicles; i++) {
            String[] tokens = br.readLine().split("\\s+");
            int M = IO.cint(tokens[0]);
            long nextFree = 0;
            Coordinate c = new Coordinate(0, 0);

            for (int j = 1; j <= M; j++) {
                int rideNr = IO.cint(tokens[j]);
                Ride r = io.rides.get(rideNr);

                if (completed.contains(r)) {
                    throw new IllegalStateException(String.format("Ride %d was already completed!\n", rideNr));
                }

                nextFree += c.distanceTo(r.from);
                if (nextFree <= r.earliestStart) {
                    scoreSum += io.bonus;
                    nextFree = r.earliestStart;
                }

                long score = r.from.distanceTo(r.to);
                nextFree += score;

                if (nextFree > r.deadline) {
                    throw new IllegalStateException(String.format("Car %d is trying to drive a ride whose window has closed: %d(%d, %d) at %d\n", i, rideNr, r.earliestStart, r.deadline, nextFree));
                }

                scoreSum += score;
                completed.add(r);
                c = r.to;
            }
        }

        return scoreSum;
    }
}
