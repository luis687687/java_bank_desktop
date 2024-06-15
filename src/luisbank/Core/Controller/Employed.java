package luisbank.Core.Controller;

public class Employed extends Person {

    private String email;
    private String password;
    
    public Employed(String email, String password, String phone, String optionalphone){
        super(phone, optionalphone);
        this.email = email;
        this.password = password;
      
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

   
    public String toString(){
        return this.email+"  "+this.password;
    }
    
}
