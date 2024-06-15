package luisbank.Core.Controller;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BI implements Serializable{
    
    public String fullname;
    public String fathername;
    public String momname;
    private String number;
    public String localborn;
    public String dateborn;
    public String image;

    
    public void setNumber(String number){
        
        // Pattern pattern = Pattern.compile("[0-9]{9}[a-zAZ]{2}[0-9]{3}");
        // Matcher verificator = pattern.matcher(number);
        // if(!verificator.find())
        //     return false;

        this.number = number;
    
    }
    public String getNumber(){
        return this.number;
    }
    

}
