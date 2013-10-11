package sha3.MainClass;

public class PreProcessing {
        //changing the string revcieved into its binary representation
        public String toBinary(String line){
           
            String binary = "" ;
            byte[] infoBin = null;
            try {
             infoBin = line.getBytes( "UTF-8" );
             for (byte b : infoBin) {
                      binary = binary + Integer.toBinaryString(b);
             }
            } 
            catch (Exception e){
             System.out.println(e.toString());
            }
            
            return (binary);
            
        }
        // initial padding 10*1 form making it a multiple of BIT_RATE 
        public String toPad(String line) {
            
            line = line + "1";
            while ((line.length() + 1) % Sponge.BIT_RATE != 0) {
                line = line + "0";
            }
            line = line + "1";
            
            return line;
        }
    
}
