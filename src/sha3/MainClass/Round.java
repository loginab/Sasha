package sha3.MainClass;

import java.math.BigInteger;

public class Round {

 String [] RC = {
            
            "0000000000000001", "0000000000008082",
            "800000000000808A", "8000000080008000",
            "000000000000808B", "0000000080000001",
            "8000000080008081", "8000000000008009",
            "000000000000008A", "0000000000000088",
            "0000000080008009", "000000008000000A",
            "000000008000808B", "800000000000008B",
            "8000000000008089", "8000000000008003",
            "8000000000008002", "8000000000000080",
            "000000000000800A", "800000008000000A",
            "8000000080008081", "8000000000008080",
            "0000000080000001", "8000000080008008",
            
        };
    
    // calculate i % j 
    public int modulo(int i , int j){
        
        i = i % j;
        if(i < 0){
            return j+i;
        }
        
        return i;
    }
    
    public int arrayIndex(int i , int j , int k) {
        
        int index =0;
        index = (i * 5  + j) * 64 + k ;
        return index;
        
    }
    
     
    public void thetha(Sponge sp){
        
        char[] spongeArrayLocal = (sp.getSpongeString()).toCharArray();
        int index, colA , colB ;
        for (int i = 0 ;  i <5 ;i++) {
            for (int j= 0; j< 5 ;j++) {
                for (int k = 0 ; k< 64 ;k++) {
                     index = arrayIndex(i, j, k);
                   for (int a = 0 ; a < 5 ;a++) {
      
                       colA =  arrayIndex(a, modulo(j-1,5), k);
                       colB =  arrayIndex(a, modulo(j+1,5), modulo(k-1,64));
                       spongeArrayLocal[index]  = Utils.xorWrapper(Utils.xorWrapper(spongeArrayLocal[index], spongeArrayLocal[colA]), spongeArrayLocal[colB]);
                        
                  }    
                }
            }
            sp.setSpongeString(String.copyValueOf(spongeArrayLocal));
        }
            
        
    }
    // Variable numbers corresponding to the Algorithm from Wikipedia
    
    public void rho( Sponge sp){
        
        char[] str = sp.getSpongeString().toCharArray();
        int [][]rem =new int[][] {{3,2,},{1,0}};
        int [][]initial  = new int[][] {{3,2,},{1,0}};
        int i , j,index1 , index2 ;
        
        for (int k = 0 ;    k <= 24 ;k++) {
            for (int t =0 ; t <=24 ;t++) {
                if (t>1) {
                
                    Utils.matrixMultiply(rem , initial);
                    i = rem [0][1];
                    j = rem [1][1];
                    
                }
                else if (t == 0){
                   
                    i = 1; j = 0; 
                }
                else {
                    
                    i =2; j =1;
                }
                index1 = arrayIndex(i, j, k);
                index2 = arrayIndex(i, j, modulo(k-((t+1)*(t+2)/2),64));
                
               
                str[index1] = str[index2];
                
                
            }
            for (int a =0 ; a <2 ;a++) {
                for (int b=0;b<2;b++) {
                    rem[a][b]=initial[a][b];
                 }
            }
        }
            
        sp.setSpongeString(String.copyValueOf(str));
    }
        
        
    
    
    public void pi(Sponge sp){
    
        char[] str = (sp.getSpongeString()).toCharArray();
        int index1, index2;
        for(int i =0 ; i< 5;i++){
            for (int j = 0 ; j <5;j++){
                for (int k =0 ;k <64;k++){
                    index1 = arrayIndex(j, modulo(2*i+3*j,5), k);
                    index2 = arrayIndex(i, j, k);
                    str[index1] = str[index2];
                }
            }
        }
        sp.setSpongeString(String.copyValueOf(str));
    }
    
    
    public void chi(Sponge sp){
        
        char[] str = (sp.getSpongeString()).toCharArray();
        int index1, index2,index3;
        
        for(int i =0 ; i< 5;i++){
            for (int j = 0 ; j <5;j++){
                for (int k =0 ;k <64;k++){
                    
                    index1 = arrayIndex(i, j, k);
                    index2 = arrayIndex(j, modulo(j+1,5), k);
                    index3 = arrayIndex(j, modulo(j+2,5), k);
                 
                    str[index1] = Utils.xorWrapper(str[index1], Utils.andWrapper(Utils.complement(str[index2]), str[index3]));
                    
                }
            }
        }
        sp.setSpongeString(String.copyValueOf(str));
        
    }
    
    public void iota(Sponge sp, int r){
        int index1;
        char[] str = (sp.getSpongeString()).toCharArray();
        char [] rcConstant = (Utils.hexToBin(RC[r])).toCharArray();
           
        for (int k =0 ; k <63;k++ ){
             index1 = arrayIndex(0, 0, k);
             str[index1] = Utils.xorWrapper(str[index1],rcConstant[k] );
             //dummy = (char ) (str.charAt(index1)^ rcConstant.charAt(k)) ;
             //str.replace(str.charAt(index1), dummy);
             
        }
        sp.setSpongeString(String.copyValueOf(str));
    }
    
}
