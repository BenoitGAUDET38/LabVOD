package CSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVLogin {
    final String fileName = "Credentials.csv";

    String SPACE = " ";
    String LINE = "\n";

    public CSVLogin(){}

    Scanner readData(){
        try{
            return new Scanner(new File(fileName));
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return null;
    }

    public boolean isCSVContain(String mail){
        Scanner credentials = readData();

        while (credentials.hasNextLine()){
            String[] parts = credentials.nextLine().split(" ");
            if(parts[0].equals(mail)) return true;
        }

        return false;
    }
}
