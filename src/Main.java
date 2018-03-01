import java.io.File;
import java.io.FileNotFoundException;

public class Main {

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
