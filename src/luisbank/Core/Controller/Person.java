package luisbank.Core.Controller;
import java.io.Serializable;
public class Person implements Serializable{
    String phone;
    String optionalphone;
    boolean actived;
    public Person(String phone, String optionalphone){
        this.phone = phone;
        this.optionalphone = optionalphone;
        this.actived = true;
    } 
    
    public void setActived(boolean value){
        this.actived = value;
    }
    public boolean checkActived(){
        return this.actived;
    }
    
}