package qrameesh;

public class Checker {
   
    static boolean check_Password(String password){
        int lngth = password.length();
        if(!(lngth<8 || lngth >16)){
            return false;
        }else{
            return true;
        }
    }  
    
    
}
