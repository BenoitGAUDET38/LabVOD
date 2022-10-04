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

    /**
     * Get the scanner on the csv save file
     * @return
     */
    Scanner readData(){
        try{
            return new Scanner(new File(fileName));
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieve all ClientInfo from the csv file and return the corresponding list of ClientInfo
     * @return
     */
    public List<ClientInfo> getClientInfo(){
        List<ClientInfo> clientInfoList = new ArrayList<>();
        Scanner credentials = readData();

        while (credentials.hasNextLine()){
            String[] parts = credentials.nextLine().split(" ");
            clientInfoList.add(new ClientInfo(parts[0], parts[1]));
        }

        return clientInfoList;
    }


    /**
     * Add a client in the csv file with the correct syntax
     * @param mail
     * @param pwd
     * @return
     */
    public boolean addClientInfo(String mail, String pwd) {
        return addData(mail + SPACE + pwd + LINE);
    }

    /**
     * Add the given string in the csv file
     * @param text
     * @return
     */
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
