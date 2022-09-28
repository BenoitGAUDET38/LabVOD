package CSV;

import java.io.FileWriter;
import java.util.Scanner;

public class CSVReader {
    FileWriter file = null;

    CSVReader(){
        try
        {
            file = new FileWriter("Credentials.csv");
            file.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    Scanner getCSVText(){
        return new Scanner((Readable) file);
    }

}
