import CSV.CSVRegister;
import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IVODService;

import java.lang.reflect.Array;

public class Connection {
    Array clientList;

    boolean signIn(String mail, String pwd) throws SignInFailed {
        CSVRegister s = new CSVRegister();
        return false;
    }

    IVODService login(String mail, String pwd) throws InvalidCredentialsException {
        return null;
    }
}
