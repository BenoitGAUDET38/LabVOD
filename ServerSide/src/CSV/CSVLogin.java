package CSV;

public class CSVLogin extends CSVReader{
    public CSVLogin(){}

    public boolean canLogin(String mail, String pwd){
        return isCSVContain(mail,pwd);
    }
}
