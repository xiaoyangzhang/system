package com.yhyt.health;

import java.io.*;

public class DealSql {
    public static void main(String[] args) throws IOException {

//        FileInputStream fis = new FileInputStream(new File("/Users/localadmin/Desktop/toadd.txt"));
//        FileOutputStream fos = new FileOutputStream(new File("/Users/localadmin/Desktop/tomodify.txt"));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/localadmin/Desktop/toadd.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/localadmin/Desktop/tomodify.txt")));
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            String replace = line.replace("\t\\([1-9]+,", "\\(");
            bufferedWriter.write(replace);
        }
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
