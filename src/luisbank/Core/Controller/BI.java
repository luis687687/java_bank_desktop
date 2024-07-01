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

    public BI(String name, String numberbi, String localborn, String dateborn){
        this.fullname = name;
        this.number = numberbi;
        this.localborn = localborn;
        this.dateborn = dateborn;
    }
    public String getLocal(){
        return this.localborn;
    }
    public void setNumber(String number){
        
        // Pattern pattern = Pattern.compile("[0-9]{9}[a-zAZ]{2}[0-9]{3}");
        // Matcher verificator = pattern.matcher(number);
        // if(!verificator.find())
        //     return false;

        this.number = number;
    
    }
    public boolean valide(){
        String pattern = "\\d{9}[A-Z]{2}\\d{3}";
        return validatePattern(this.number, pattern);
    }
    
     public static boolean validatePattern(String input, String pattern) {
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }
    public String getNumber(){
        return this.number;
    }
    

}
