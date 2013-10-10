package sha3.MainClass;

public class Sponge {

    String  spongeString ; 
    public static int BIT_RATE = 1088;
    public static int CAPACITY = 512;
    public static int STATE = 1600;
    public Sponge() {
    
        spongeString = "";
        for (int i = 0 ; i < STATE ;i++)
                    spongeString =  spongeString + ""; 
        
    }


    public String getSpongeString() {
        return spongeString;
    }


    public void setSpongeString(String spongeString) {
        this.spongeString = spongeString;
    }

    public void mainSponge(String str){
    
        int numberOfBlocks = str.length() / BIT_RATE;
        int counter = 0 ;
        for (int i =0 ;i < numberOfBlocks;i++) {
            spongeRound(paddingBeforeSponge(str.substring(counter, counter+BIT_RATE)));
            // TODO call the round function for each 
            
            counter += BIT_RATE;
        }
        
    }
   
   public String paddingBeforeSponge(String str){
        for (int i=0 ;i< CAPACITY;i++)
             str = str + "0";
        return(str);
    }
    
    
    public void spongeRound(String str){
    
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
            sb.append((char)(str.charAt(i) ^ spongeString.charAt(i)));
        String result = sb.toString();
        setSpongeString(result);
        
    }
    
}
