package it.itisgalileiroma.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class CsvFileReader {

      public static void main(String[] args){

          String filePath = new File("graph-oop-master/resources/fileCSV.csv").getAbsolutePath();       // gets thte path
          boolean isFirstLine = true;                                   // flags the condition if its the first line getting scanned

          try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
              String line;

              while ((line = br.readLine()) != null) {
                  if (isFirstLine) {
                      isFirstLine = false;
                      continue;
                  }

                  String[] columns = line.split(",");

                  String source = columns[0];
                  String target = columns[1];
                  String weight = columns[2];

                  System.out.println("Source: " + source + ", Target: " + target + ", Weight: " + weight);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
}
