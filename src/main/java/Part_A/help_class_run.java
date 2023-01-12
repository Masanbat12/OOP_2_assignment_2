package Part_A;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class help_class_run extends Thread{

    private String names_file;
    private int num_Of_Lines;

    public help_class_run(String fileName){
        this.names_file = fileName;
    }

    public int getNum_Of_Lines() {
        return num_Of_Lines;
    }

    public void run(){
        int count = 0;
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.names_file))) {
            while ((str = reader.readLine()) != null) {
                count++;
            }
            this.num_Of_Lines = count;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




}
