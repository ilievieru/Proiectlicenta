package util;

import consume.Consume;

import java.io.PrintWriter;

/**
 * Created by v3790149 on 5/19/2016.
 */
// Un thread pentru apelarea serviciului
public class Task implements Runnable {
    private String msg;
    private String path;
    Consume consume = new Consume();

    PrintWriter writer = null;

    public Task( String path, PrintWriter writer) {
        this.path = path;
        this.writer = writer;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        String result = "Inceput";
        result = consume.Use(msg);
        writer.println(result);
    }
}
