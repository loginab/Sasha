package sha3.MainClass;

public class Sponge {

    String  spongeString ; 
    public static int BIT_RATE = 1088;
    public static int CAPACITY = 512;
    public static int STATE = 1600;
    public static int SPONGE_ROUNDS = 24;
    public Sponge() {
    
        spongeString = "";
        for (int i = 0 ; i < STATE ;i++)
                    spongeString =  spongeString + "0"; 
        
    }


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
        //System.out.println("Number of blocks :" +numberOfBlocks );
        for (int i =0 ;i < numberOfBlocks;i++) {
            
            spongeRound(paddingBeforeSponge(str.substring(counter, counter+BIT_RATE)));
            // TODO call the round function for each
            for (int roundVariable = 0; roundVariable < SPONGE_ROUNDS;roundVariable++ ) {
                
                round.thetha(this);
                round.rho(this);
                round.pi(this);
                round.chi(this);
                round.iota(this, roundVariable);
                
            }
            
            counter += BIT_RATE;
        }
        
        return (this.getSpongeString()).substring(0, 256);
    }
   
   public String paddingBeforeSponge(String str){
        for (int i=0 ;i< CAPACITY;i++)
             str = str + "0";
       
        return(str);
    }
    
    
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
