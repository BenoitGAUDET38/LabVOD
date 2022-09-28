package CSV;

import java.io.*;
import java.util.Scanner;

public class CSVRegister extends CSVReader{


    public CSVRegister(){
        super();
    }

    public boolean add(String mail, String pwd) {
        Scanner credentials = getCSVText();
        while (credentials.hasNext()){}
        return true;
    }
}
