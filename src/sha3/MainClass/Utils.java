package sha3.MainClass;

public class Utils {
    
    
  public static String XOR(char a , char b){
        if (a == b )
            return "0";
        else
            return "1";
        
        
    }
    
    public static void matrixMultiply(int a[][], int b[][]){
        
        a[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % 5 ; 
        a[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % 5;
        
        a[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % 5 ; 
        a[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % 5 ;
        
        
    }
    
    
    public static char xorWrapper(char a , char b){
        if (a == b )
            return '0';
        else
            return '1';
        
        
    }
    
    public static char complement(char a){
        if (a == '0' )
            return '1';
        else
            return '0';
        
        
    }

    public static char andWrapper(char a,char b){
        if (a == '1' && b =='1' )
            return '1';
        else
            return '0';
        
        
    }
    
 public static String hexToBin(String s) {
        
        char[] dummy = s.toCharArray() ;
        String str  = "";
        for (int i = 0;i <s.length();i++){
            
            switch(dummy[i]) {
            
                case '0': 
                    str = str + "0000";
                    break;
                    
                case '1':
                    str = str + "0001";
                    break;
                
                case '2':
                    str = str + "0010";
                    break;
                
                case '3':
                    str = str + "0011";
                    break;
                
                case '4':
                    str = str + "0100";
                    break;
                    
                case '5':
                    str = str + "0101";
                    break;
                    
                case '6':
                    str = str + "0110";
                    break;
                    
                case '7':
                    str = str + "0111";
                    break;
                    
                case '8':
                    str = str + "1000";
                    break;
                    
                case '9':
                    str = str + "1001";
                    break;
                    
                case 'A':
                    str = str + "1010";
                    break;
                    
                case 'B':
                    str = str + "1011";
                    break;
                    
                case 'C':
                    str = str + "1100";
                    break;
                    
                case 'D':
                    str = str + "1101";
                    break;
                    
                case 'E':
                    str = str + "1110";
                    break;
                    
                case 'F':
                    str = str + "1111";
                    break;
                
            }
            
            
            
        }
        
        
        return str;
        
        
    }


 
}
