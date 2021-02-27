package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class Main {

    public static void main(String[] args) throws IOException {

        // create 100 files with 100 lines having 100 numbers each
        for(int i=0; i<100; i++)
        {
            String fileName = "D:\\Repos\\apache-file-ops\\filesDir\\" + "Doc" + i + ".txt";
            File file = new File(fileName);
            FileUtils.touch(file);
            List<String> lines = new ArrayList<>();
            Random rand = new Random();
            for(int j =0; j< 100; j++)
            {
                List<Integer> line = new ArrayList<>();
                for( int k =0; k< 100; k++)
                {
                    line.add(rand.nextInt(100));
                }
                String ln = line.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));
                lines.add(ln);
            }
            FileUtils.writeLines(file,lines);
        }
        //to read all files
        for( int i =0; i< 100; i++)
        {
            String sourceFile = "D:\\Repos\\apache-file-ops\\filesDir\\" + "Doc" + i + ".txt";

            List<String> fileLines = FileUtils.readLines(new File(sourceFile));
            List<String> lines = new ArrayList<>();
            for(String l : fileLines)
            {
                List<String> line = new ArrayList<>();
                String firstNumber = l.substring(0,l.indexOf(','));
                int number = Integer.parseInt(firstNumber);
                if (number %2 ==0)
                {
                    // store to a list
                    line.add(l);
                }
                //write the list to the dest file
                if(line.size()>0)
                {
                    String destFile = "D:\\Repos\\apache-file-ops\\filesDir\\" + "ResDoc" + i + ".txt";
                    File dest = new File(destFile);
                    FileUtils.touch(dest);
                    lines.add(line.toString());
                    FileUtils.writeLines(dest,lines);
                }

            }
        }
    }
}
