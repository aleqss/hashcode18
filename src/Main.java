import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static PriorityQueue<Car> pq = new PriorityQueue<>(Comparator.comparingLong(car -> car.timeFree));

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length<1){
            System.out.println("You didnt specify a file");
            return;
        }

        IO io = new IO();
        File file = new File(args[0]);
        io.readInput(file);
    }
}
