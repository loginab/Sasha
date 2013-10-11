package sha3.MainClass;

import java.io.*;


public class Main {

    
    public static void main(String args[]){
     
        BufferedReader br;
        try {   
            
            br = new BufferedReader(new FileReader("test.txt"));
            String line;
            
            while ((line = br.readLine()) != null) {
                
                PreProcessing pP = new PreProcessing();
                Sponge sponge = new Sponge();
                String binaryString = pP.toBinary(line);
                String paddedString = pP.toPad(binaryString);
                System.out.println("Length of the padded String :"+ paddedString.length());
                String hash = sponge.mainSponge(paddedString);
                System.out.println("Generated hash :"+hash);
               // System.out.println("Length :"+hash.length());
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
