package sha3.MainClass;

public class PreProcessing {

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
            //System.out.println("Initial length :"+ binary.length());
            return (binary);
            
        }
    
        public String toPad(String line) {
            
            line = line + "1";
            while ((line.length() + 1) % 1088 != 0) {
                line = line +"0";
            }
            line = line + "1";
            //System.out.println("Length after padding :"+ line.length());
            return line;
        }
    
}
