package CSV;

import java.io.*;
import java.util.Scanner;

public class CSVRegister extends CSVReader{

    private String SPACE = " ";
    private String LINE = "\n";

    public CSVRegister(){
        super();
    }

    public boolean add(String mail, String pwd) {
        Scanner credentials = readData();

        while (credentials.hasNextLine()){
            String scan = credentials.nextLine();
            if(scan.equals(mail + " " + pwd)) return false;
        }

        addData(mail + SPACE + pwd + LINE);
        return true;
    }
}
