import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {

    public int rows = 0;
    public int columns = 0;
    public int vehicles = 0;
    public int nbRides = 0;
    public int bonus = 0;
    public int steps = 0;
    public List<Ride> rides = new ArrayList<>();

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
                rides.add(new Ride(start, finish, cint(tokens[4]), cint(tokens[5]),i));
            }
            System.out.println("Read input");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void output(File file, List<Car> cars){
        try {
            FileWriter fw = new FileWriter(file);
            // code
            for(int i =0;i<cars.size();i++){
                fw.write(String.format("%d",cars.get(i).finishedRides.size()));
                for(Ride r:cars.get(i).finishedRides){
                    fw.write(String.format(" %d",r.index));
                }
                fw.write("\n");
            }
            fw.flush();
            fw.close();
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
