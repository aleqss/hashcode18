import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {

    int rows = 0;
    int columns = 0;
    int vehicles = 0;
    int nbRides = 0;
    int bonus = 0;
    int steps = 0;
    List<Ride> rides = new ArrayList<>();

    public void readInput(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] tokens = br.readLine().split("\\s+");
            this.rows = cint(tokens[0]);
            this.columns = cint(tokens[1]);
            this.vehicles = cint(tokens[2]);
            this.nbRides = cint(tokens[3]);
            this.bonus = cint(tokens[4]);
            this.steps = cint(tokens[5]);

            for (int i = 0; i < nbRides; i++) {
                tokens = br.readLine().split("\\s+");
                Coordinate start = new Coordinate(cint(tokens[0]), cint(tokens[1]));
                Coordinate finish = new Coordinate(cint(tokens[2]), cint(tokens[3]));
                rides.add(new Ride(start, finish, cint(tokens[4]), cint(tokens[5])));
            }
            System.out.println("Read input");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void output(File file, Object toOutput){
        try {
            FileWriter fw = new FileWriter(file);
            // code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int cint(String s) {
        return Integer.parseInt(s);
    }

    public static long clong(String s) {
        return Long.parseLong(s);
    }
}
