package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class ReadFromFile {
    public String path;

    public ReadFromFile() {

    }

    public void setPath(String path){
        this.path = path;
    }

    // Citim fisierul linie cu linie. Apelem servicul si generem un fisier de out in acelas folder
    public String read() {
        String result = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path+".txt"));
            PrintWriter writer = new PrintWriter(path+"Out.txt");
            ExecutorService executor = Executors.newFixedThreadPool(4);
            Task t = new Task(path ,writer);
            while ((sCurrentLine = br.readLine()) != null) {
                t.setMsg(sCurrentLine);
                executor.submit(t);


            }
           // System.out.println("gata");
        } catch (IOException e) {
            System.out.println("Read line " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                System.out.println("Close Buffer" + ex.getMessage());
            }
        }
        return result;
    }
}
