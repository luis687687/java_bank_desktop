package luisbank.Core.Controller;

public class PersonSingular  extends Person implements IClient{
    
    BI bi;
    Account account;
    public PersonSingular(Account account, BI bi, String phone, String optionalphone){
        super(phone, optionalphone);
        this.phone = phone;
        this.bi = bi;
        this.account = account;
    }

  
    public String getName(){
        return this.bi.fullname;
    }
    public String getCode(){
        return this.bi.getNumber();
    }
    public Account getAccount(){
        return this.account;
    }

    public String toString(){
        return "singular: "+getName()+" code: "+getCode()+" Money: "+getAccount().getMoney()+" iban: "+getAccount().getIban()+" actual credit: "+getAccount().getCredite();

    }
}
