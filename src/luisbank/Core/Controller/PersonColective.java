package luisbank.Core.Controller;
public class PersonColective extends Person implements IClient {
    
    Comertial comertial;
    Account account;
    String phone1, phone2;
    public PersonColective(Account account, Comertial comertial){
        super(comertial.phone, comertial.optionalphone);
        this.comertial = comertial;
        this.account =account;

    }

    public String getName(){
        return this.comertial.name;
    }
    public String getCode(){
        return this.comertial.comertialcode;
    }
    public Account getAccount(){
        return this.account;
    }
    public String toString(){
        return "colective: "+getName()+" code: "+getCode()+" Money: "+getAccount().getMoney()+" iban: "+getAccount().getIban()+" actual credit: "+getAccount().getCredite();
    }
    

    
}
