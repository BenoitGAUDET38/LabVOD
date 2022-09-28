package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    final String fileName = "Credentials.csv";
    FileWriter fileWriter;

    CSVReader(){}

    void addData(String text){
        try {
            new FileWriter(fileName, true).append(text).close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    Scanner readData(){
        try{
            return new Scanner(new File(fileName));
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return null;
    }
}
