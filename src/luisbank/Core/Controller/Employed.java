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
    
    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}
    public void setPhone(String phone){this.phone = phone;}
    public void setPhone2(String phone2){this.optionalphone = phone2;}
    public void setPassword(String pass){this.password = pass;}
   
    public String toString(){
        return this.email+"  "+this.password;
    }
    
}
