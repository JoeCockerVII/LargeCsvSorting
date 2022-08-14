package com.example.csvsorting.domain.service;

import com.example.csvsorting.domain.entity.ClientDetails;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class PartFileSorted<T> implements Iterable<T> {

    private File file;
    public static int fileId = 0;
    public static String pathname = ".\\tmp";

    public PartFileSorted(List<T> details) throws IOException {
        File dir = new File(pathname);
        if(!dir.exists())
            dir.mkdirs();
//        file = new File((pathname + "\\file" + fileId++ + ".csv"));
        file = File.createTempFile("FileSort", ".csv", new File(pathname));
        saveObjectsToFile(details);
    }

    private void saveObjectsToFile(List<T> details) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (T det : details) {
                ClientDetails c1 = (ClientDetails) det;
                bw.write( c1.getCsvString());
                bw.newLine();
                bw.flush();
            }
        }

    }

    @Override
    public Iterator<T> iterator() {

        try {
            return new Iterator<T>() {
                private final BufferedReader br = new BufferedReader(new FileReader(file));
                T obj;

                public boolean hasNext() {
                    String currentLine;
                    if (obj == null) {
                        try {
                            if((currentLine = br.readLine()) !=null){
                                ClientDetails clientDetails = new ClientDetails(currentLine.split(";"));
                                obj = (T)clientDetails;
                            }
                        } catch (IllegalArgumentException | ClassCastException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            obj = null;
                        }
                    }
                    return obj != null;
                }

                public T next() {
                    hasNext();
                    T res = obj;
                    obj = null;
                    return res;
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
