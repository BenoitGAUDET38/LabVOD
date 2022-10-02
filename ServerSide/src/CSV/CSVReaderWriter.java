package CSV;

import application.ClientInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReaderWriter {
    final String fileName = "Credentials.csv";

    String SPACE = " ";
    String LINE = "\n";

    Scanner readData(){
        try{
            return new Scanner(new File(fileName));
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return null;
    }

    public List<ClientInfo> getClientInfo(){
        List<ClientInfo> clientInfoList = new ArrayList<>();
        Scanner credentials = readData();

        while (credentials.hasNextLine()){
            String[] parts = credentials.nextLine().split(" ");
            clientInfoList.add(new ClientInfo(parts[0], parts[1]));
        }

        return clientInfoList;
    }


    public boolean addClientInfo(String mail, String pwd) {
        return addData(mail + SPACE + pwd + LINE);
    }

    boolean addData(String text){
        try {
            new FileWriter(fileName, true).append(text).close();
            return true;
        }catch (IOException io){
            io.printStackTrace();
        }
        return false;
    }
}
