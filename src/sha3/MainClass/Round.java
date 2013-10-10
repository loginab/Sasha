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
    
    // CONVERTING RC constant from a hex value to a string value
    
    public static String HexToBinary(char Hex) {
        int i = Integer.parseInt(Character.toString(Hex), 16);
        String Bin = Integer.toBinaryString(i);
        return Bin;
    }
    
    public String hexToBin(String s) {
        char []keyCharArray = s.toCharArray();
        String str = "";
        for (int i=0;i<s.length();i++)
            str = str + HexToBinary((keyCharArray[i]));
        
        return str;
      }
    
    public int[][] matrixMultiply(int a[][], int b[][]){
        
        a[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0] ; 
        a[0][1] = a[0][0] * b[0][1]  +  a[0][1] * b[1][1] ;
        
        a[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0] ; 
        a[1][1] = a[1][0] * b[0][1] +  a[1][1] * b[1][1] ;
        
        return a;
    }
    
     
    // calculate i % j 
    public int modulo(int i , int j){
        
        i = i % j;
        if(i < 0){
            return j-i;
        }
        
        return i;
    }
    public int arrayIndex(int i , int j , int k) {
        int index =0;
        index = (index * 5  + j) * 64 + k ;
        return index;
    }
    public String roundMain(String str, Sponge sp){
        
        thetha(sp);
        return str;
    }
    
    
    
       
    public void thetha( Sponge sp){
        
        String spongeArrayLocal = sp.getSpongeString();
        char dummy; 
        int index, colA , colB ;
       
        for (int i = 0 ;  i <5 ;i++) {
            for (int j= 0; j< 5 ;j++) {
                for (int k = 0 ; k< 64 ;k++) {
                     index = arrayIndex(i, j, k);
                   for (int a = 0 ; a < 5 ;a++) {
                       
                        // TODO DOES NOT TAKE INTO ACCOUNT THE VALUE OF K  in colB 
                       colA =  arrayIndex(a, modulo(j-1,5), k);
                       colB =  arrayIndex(a, modulo(j+1,5), modulo(k-1,64));
                       
                       dummy = (char ) (spongeArrayLocal.charAt(index)^ spongeArrayLocal.charAt(colA) ^ spongeArrayLocal.charAt(colB)) ;
                       spongeArrayLocal.replace(spongeArrayLocal.charAt(index),dummy);
                       
                   }    
                }
            }
            sp.setSpongeString(spongeArrayLocal);
        }
            
        
    }
    // Variable numbers corresponding to the Algorithm from Wikipedia
    
    public void rho( Sponge sp){
        
        String str = sp.getSpongeString();
        int [][]rem =new int[][] {{3,2,},{1,0}};
        int [][]initial  = new int[][] {{3,2,},{1,0}};
        int i , j,index1 , index2 ;
        
        for (int k = 0 ; k <= 24 ;k++) {
            for (int t =0 ; t <=24 ;t++) {
                if (t>1) {
                
                    matrixMultiply(rem , initial);
                    i = rem [0][1];
                    j = rem [1][1];
                    
                }
                else if (t == 0){
                   
                    i = 0; j = 0; 
                }
                else {
                    
                    i =2; j =1;
                }
                index1 = arrayIndex(i, j, k);
                index2 = arrayIndex(i, j, modulo(k-((t+1)*(t+2)/2),64));
                str.replace(str.charAt(index1), str.charAt(index2));
                
                
            }
            for (int a =0 ; a <2 ;a++) {
                for (int b=0;b<2;b++) {
                    rem[a][b]=initial[a][b];
                 }
            }
        }
            
          sp.setSpongeString(str);
        }
        
        
    
    
    public void pi(Sponge sp){
        String str = sp.getSpongeString();
        int index1, index2;
        for(int i =0 ; i< 5;i++){
            for (int j = 0 ; j <5;j++){
                for (int k =0 ;k <5;k++){
                    index1 = arrayIndex(j, modulo(2*i+3*j,5), k);
                    index2 = arrayIndex(i, j, k);
                    str.replace(str.charAt(index1), str.charAt(index2));
                }
            }
        }
          sp.setSpongeString(str);
    }
    
    
    public void fnX(Sponge sp){
        String str = sp.getSpongeString();
        char dummy;
        int index1, index2,index3;
        for(int i =0 ; i< 5;i++){
            for (int j = 0 ; j <5;j++){
                for (int k =0 ;k <5;k++){
                    
                    index1 = arrayIndex(i, j, k);
                    index2 = arrayIndex(j, modulo(j+1,5), k);
                    index3 = arrayIndex(j, modulo(j+2,5), k);
                                       
                    dummy = (char ) (str.charAt(index1)^ (~str.charAt(index2)) & str.charAt(index3)) ;
                    str.replace(str.charAt(index1), dummy);
                }
            }
        }
          sp.setSpongeString(str);
        
    }
    
    public void fifth(Sponge sp, int r){
        
        String temp = RC[r];
        String rcConstant = hexToBin(temp);
        
    }
    
}
