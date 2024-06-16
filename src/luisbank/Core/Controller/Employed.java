package luisbank.Core.Controller;

public class Employed extends Person {

    private String email;
    private String password;
    private String name;
       
    public Employed(String email, String password, String phone, String optionalphone, String name){
        super(phone, optionalphone);
        this.email = email;
        this.password = password;
        this.name = name;
      
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
    public String getName(){ return this.name; }
    public String getPhone(){ return this.phone;} 
    public String getPhone2(){ return this.optionalphone; }

   
    public String toString(){
        return this.email+"  "+this.password;
    }
    
}
