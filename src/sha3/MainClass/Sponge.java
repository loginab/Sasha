package sha3.MainClass;

public class Sponge {

    String  spongeString ; 
    /* Definitions of constants in SHA-3*/
    public static int BIT_RATE = 1088;
    public static int CAPACITY = 512;
    public static int STATE = 1600;
    public static int SPONGE_ROUNDS = 24;
    public Sponge() {
    
        spongeString = "";
        for (int i = 0 ; i < STATE ;i++)
                    spongeString =  spongeString + "0"; 
        
    }

    // Getter and setter for class
    public String getSpongeString() {
        return spongeString;
    }


    public void setSpongeString(String spongeString) {
        this.spongeString = spongeString;
    }

    
    public String mainSponge(String str){
    
        int numberOfBlocks = str.length() / BIT_RATE;
        int counter = 0 ;
        Round round = new Round();
       
        for (int i =0 ;i < numberOfBlocks;i++) {
            // calculate the sponge
            spongeRound(paddingBeforeSponge(str.substring(counter, counter+BIT_RATE)));
            // for each block call each of the sponge functions SPONGE_ROUNDS number of times
            for (int roundVariable = 0; roundVariable < SPONGE_ROUNDS;roundVariable++ ) {
                round.thetha(this);
                round.rho(this);
                round.pi(this);
                round.chi(this);
                round.iota(this, roundVariable);
            }
            counter += BIT_RATE;
        }
        // returning a 256 bit hash value
        return (this.getSpongeString()).substring(0, 256);
    }
   
    
   // Making the message block equal to the state for XORing
   public String paddingBeforeSponge(String str){
        for (int i=0 ;i< CAPACITY;i++)
             str = str + "0";
       
        return(str);
    }
    
   // Making the message block equal to the state for XORing
    public void spongeRound(String str){
    
        StringBuilder sb = new StringBuilder();
        String dummyString = "";
        for(int i = 0; i < str.length(); i++) {
            String dummy = Utils.XOR(str.charAt(i),spongeString.charAt(i));
            dummyString = dummyString + dummy ; 
            
        }
        setSpongeString(dummyString);
    
    }
    
}
