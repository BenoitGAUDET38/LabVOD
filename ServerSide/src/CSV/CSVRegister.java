package CSV;

import java.io.*;

public class CSVRegister extends CSVReader{
    public CSVRegister(){
        FileWriter file = null;

        try
        {
            file = new FileWriter("Credentials.csv");

            file.append("test");

            file.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
