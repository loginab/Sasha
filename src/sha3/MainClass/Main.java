package sha3.MainClass;

import java.io.*;


public class Main {

    
    public static void main(String args[]){
     
        BufferedReader br;
        try {
            PreProcessing pP = new PreProcessing();
            br = new BufferedReader(new FileReader("test.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String binaryString = pP.toBinary(line);
                String paddedString = pP.toPad(binaryString);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }   
}
