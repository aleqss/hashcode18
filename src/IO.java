import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public  void readInput(File file) throws FileNotFoundException {

        Scanner scanner =new Scanner(new FileReader(file));
        this.rows = scanner.nextInt();
        this.columns = scanner.nextInt();
        this.vehicles = scanner.nextInt();
        this.nbRides = scanner.nextInt();
        this.bonus = scanner.nextInt();
        this.steps = scanner.nextInt();

        for(int i =0 ; i<nbRides;i++){
            Coordinate start = new Coordinate(scanner.nextInt(), scanner.nextInt());
            Coordinate finish  = new Coordinate(scanner.nextInt(),scanner.nextInt());
            rides.add(new Ride(start,finish,scanner.nextInt(),scanner.nextInt()));
        }
        System.out.println("Read input");
    }

    public static void outPut(Object toOutput){

    }
}
