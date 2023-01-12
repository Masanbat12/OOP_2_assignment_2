
package Part_A;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class EX2_1 {


        public static String[] createTextFiles(int n, int seed, int bound){
            String [] file_names_arr  = new String[n];
            int i=0;
            try {
                Random rand = new Random(seed);
                while (i<n){
                    int lines = rand.nextInt(bound);
                    File file = new File("file_" + (i + 1) + ".txt");
                    file.createNewFile();
                    file_names_arr[i] = file.getName();
                    FileWriter stream_characters = new FileWriter(file);
                    for (int j = 0; j < lines; j++) {
                        stream_characters.write("Hello World\n");
                    }
                    stream_characters.close();
                    i++;
                }
            }catch (IOException ep){
                ep.printStackTrace();
            }
            return file_names_arr;
        }

    public static int getNumOfLines(String[] fileNames) {
        int count = 0;
        try {
            for (String fileName : fileNames) {
                Path path = Paths.get(fileName);
                count += Files.readAllLines(path).size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }


    public static int getNumOfLinesThreads(String[] fileNames)  {
        int counter = 0;
        help_class_run[] thread_file = new help_class_run[fileNames.length];
        for (int i = 0; i < thread_file.length; i++) {
            thread_file[i] = new help_class_run(fileNames[i]);
            thread_file[i].start();
        }
        for (int i = 0; i < thread_file.length; i++) {
            try {
                thread_file[i].join();
                counter += thread_file[i].getNum_Of_Lines();
            }
            catch (InterruptedException ep){
                ep.printStackTrace();
            }
        }
        return counter;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int countLines = 0;
        int len = fileNames.length;
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(len);

        for (int i = 0; i < len; i++) {
            help_class_call task = new help_class_call(fileNames[i]);
            futures.add(executor.submit(task));
        }
        for (Future<Integer> future : futures) {
            try {
                countLines += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return countLines;
    }


    public static void main(String [] args) throws IOException {
            String [] str = createTextFiles(10,1,100);

            double start = System.currentTimeMillis();
            getNumOfLines(str);
            double end = System.currentTimeMillis();
            System.out.println("Time of getNumOfLines: "+ (end-start)+ " millisecond\n");

            start = System.currentTimeMillis();
            getNumOfLinesThreads(str);
            end = System.currentTimeMillis();
            System.out.println("Time of getNumOfLinesThreads: "+ (end-start) + " millisecond\n");

            start = System.currentTimeMillis();
            getNumOfLinesThreadPool(str);
            end = System.currentTimeMillis();
            System.out.println("Time of getNumOfLinesThreadPool: "+ (end-start) + " millisecond\n");

        }


}
