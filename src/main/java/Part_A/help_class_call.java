package Part_A;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class help_class_call implements Callable<Integer> {
    private String names_file;
    private int num_Of_Lines;

    public help_class_call(String fileName) {
        this.names_file = fileName;
    }

    public int getNum_Of_Lines() {
        return num_Of_Lines;
    }

    @Override
    public Integer call() {
        int count = 0;
        String line;
        try {
            FileReader reader = new FileReader(names_file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                count++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
