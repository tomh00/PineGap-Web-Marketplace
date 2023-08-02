package com.PineGap.Project;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// read in datapoints from a file
// then add it to an arraylist of datapoints
// this is then returned for use when the dataset is getting inserted.
public class ReadDataPoint {

    private static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = ReadDataPoint.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static ArrayList<String> readData(String fileName){

        ArrayList<String> listOfDataPoints = new ArrayList<>();
 
        try {
            
            InputStreamReader inputStream = new InputStreamReader(getResourceFileAsInputStream(fileName));
            BufferedReader br = new BufferedReader(inputStream);
            String line;
         
            while ((line = br.readLine()) != null) {
                
                // https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); 

                for(String value: values){
                    listOfDataPoints.add(value);
                }
            }
        } catch(Exception e) { }
        return listOfDataPoints;
    }
}
