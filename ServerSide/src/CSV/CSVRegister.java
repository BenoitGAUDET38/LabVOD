package CSV;

import java.io.FileWriter;
import java.io.IOException;

public class CSVRegister extends CSVLogin {
    public CSVRegister(){
        super();
    }

    public boolean add(String mail, String pwd) {
        if(isCSVContain(mail)) return false;

        addData(mail + SPACE + pwd + LINE);
        return true;
    }

    void addData(String text){
        try {
            new FileWriter(fileName, true).append(text).close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
