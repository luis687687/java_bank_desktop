package luisbank.Core.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/****
 * iban generator method i seted up as private
 */

public class Account implements Serializable {
    
    protected double money;
    private String iban;
    protected ArrayList<Moviment> moviments;
    private double creditemoney;
    private double totalcreditemoney;
    private double payedcredit;
    protected double percent;
    private Date datecredite_start;
    protected Date dateaccount_created;
    protected double money_demanded;
    protected double total_money_removed = 0;
    protected boolean card = false;
    private boolean active = true;
    protected double total_mult = 0;

    //auxiliares
    private long last_time_to_pay_credite; //ultima vez que pagou o credito
    private long last_time_to_pay_account; //ultima vez que pagou a manutenção
    private long time_to_end_transference ;
    
    

    public Account(){
        this.ibanGenerator();
        this.moviments = new ArrayList<>();
       
        this.dateaccount_created = new Date();
        this.money_demanded = Configurations.money_demand_corrent_account;
        
    }
    
    public void setMoney(double money){
        this.money = money;
    }
    public double getMoney(){
        demandMaintance();
        return this.money;
    }
    public void setActive(boolean status){
        this.active = status;
    }
    public boolean isActive(){
        return this.active;
    }
    
    public void setCard(boolean state){
        this.card = state;
    }
    public boolean hasCard(){
        return this.card;
    }


    //cobrar manutenção
    protected boolean demandMaintance(){
        long seconds = seconds(dateaccount_created);
        int cont = countMultiplesTime(seconds, last_time_to_pay_account, Configurations.second_time_to_apply_policy);
        
        System.out.println(cont+ " politicas");

        double debit = cont * this.money_demanded;
        if(debit == 0)
               return false;
        if(this.money > debit ){
            this.money -= debit;
            last_time_to_pay_account = seconds;
            this.moviments.add(new Moviment(-debit, Configurations.mov_type_maintanance, this.money));
        }
        
        return true;
    }
    
    public int timeToDemadMaintance(){
         long seconds = seconds(dateaccount_created);
         int cont = countMultiplesTime(seconds, last_time_to_pay_account, Configurations.second_time_to_apply_policy);
         return cont;
    }
    
 

    
    public String getLeftTimeTransference(){
        long actualtime = (new Date()).getTime();
        float diference_seconds = (time_to_end_transference - actualtime) / 1000f;
        if(actualtime >= time_to_end_transference)
            return "Sem pendentes";
        
        float hour = (diference_seconds/3600f);
        long hour_int = (int)hour;
        float min = (hour-hour_int)*60;
        long min_int = (int)min;
        int second = (int)((min - min_int)*60); 
        return  hour_int+"h:"+min_int+"min:"+second+"s";
        
    }
    public boolean setCredite(double value){
        if(hasCredite())
            return false;

        if(Configurations.maximus_credite >= value){
           
            this.datecredite_start = new Date();
            this.creditemoney = value;
            this.totalcreditemoney = value;
            moviments.add(new Moviment(value, Configurations.mov_type_credite_request, this.money));
            return true;
        }
        
        System.out.println("Limite de creditos");
        return false;
    }
    public boolean hasCredite(){
        //tem crédito não pago, então não pessa ,m
        return this.totalcreditemoney > 0 || this.money < 0;
    }

    public String getTimeToPermit(){ return "Autorizado!"; }
    /****
     * Essa função retorna o valor do tempo passado em segundos
     ***/
    protected long seconds(Date datestart){
        Date actualdate = new Date();
        long spendedtime = actualdate.getTime() - datestart.getTime();
        long seconds = spendedtime / 1000;
        return seconds;
    }
    protected int countMultiplesTime(long startsecond, long lastsecond, long divisor){
        long diference = startsecond - lastsecond;
        int cont = (int) (diference / divisor);
        return cont;
    }

    public double getCredite(){
        double percent = this instanceof AccountFinancy ? Configurations.percent_credit_financy_account : Configurations.percent_credit_normal_account;
        if(this.datecredite_start instanceof Date)
        {
            double totalpay = 0 , moneypercentvalue = percent*this.totalcreditemoney;
            long seconds = seconds(this.datecredite_start);
            int timespent = countMultiplesTime(seconds, last_time_to_pay_credite, 
                    Configurations.second_time_waiting_to_pay_credite_account);
            totalpay = timespent
                    *moneypercentvalue;
            System.out.println("");
            System.out.println("Total credit "+this.totalcreditemoney);
            System.out.println("Percent "+percent);
            System.out.println("Money Percent value "+moneypercentvalue);
            System.out.println(payedcredit+" Pago a pagar "+totalpay);
            System.out.println("====================================== "+timespent);
            double percent_mult = this instanceof AccountFinancy ? Configurations.percent_mult_financy : Configurations.percent_mult_credit;
            if(this.money == 0 ){
                this.total_mult += percent_mult * this.creditemoney;
                return this.totalcreditemoney;
            }
            if(totalpay != 0){   
                if(this.totalcreditemoney > payedcredit + totalpay ){ //pode remover o crédito
                    if(this.money - totalpay < 0)
                        totalpay = this.money;
                    payedcredit += totalpay;
                    this.money -= totalpay;
                    if(this.totalcreditemoney - totalpay <= 0){
                        this.totalcreditemoney = 0;
                        this.datecredite_start = null;
                    }
                    else{
                        this.totalcreditemoney -= totalpay;
                    }
                        
                    
                    last_time_to_pay_credite = seconds;
                    moviments.add(new Moviment(-totalpay, Configurations.mov_type_credite_pay+timespent+(timespent != 1 ? " meses " : " mes"), this.money));
                }
                else{
                    double valuetake = this.totalcreditemoney;
                    if(this.money - valuetake < 0)
                        valuetake = this.money;
                        
                    this.money -= valuetake;
                    this.payedcredit += valuetake;
                   
                    
                    if(this.totalcreditemoney - totalpay <= 0){
                         this.datecredite_start = null;
                         this.totalcreditemoney = 0;
                    }else{
                        this.totalcreditemoney -= valuetake;
                    }
                        
                    
                    moviments.add(new Moviment(-valuetake, Configurations.mov_type_credite_pay+timespent+(timespent != 1 ? " meses " : " mes"), this.money));
                }
            }
        }
        else{
            this.totalcreditemoney = 0;
            this.payedcredit = 0;
        }
        return this.totalcreditemoney;
    }

    public double getTotalCredite(){
        return this.totalcreditemoney;
    }

    
    public void setIban(String iban){
        this.iban = iban;
    }
    public String getIban(){
        return this.iban;
    }
    private void ibanGenerator(){

        StringBuffer intermediary = new StringBuffer();
        intermediary.append(Configurations.defaultibancode);
        intermediary.append(Math.random());

        
       this.iban = intermediary.toString();
       this.iban = this.iban.replace(".", "");

        
    }

    public boolean depositMoney(double money){
        double finalmoney = this.money + money;
        if(money > 0){
            this.money = finalmoney;
            this.moviments.add(new Moviment(money, Configurations.mov_type_deposit, finalmoney));
            return true;
        }
        return false;
    }

    public boolean removeMoney(double money){
        return this.internalRemoveMoney(money, Configurations.mov_type_remov);
    }
    protected boolean internalRemoveMoney(double money, String type){
        if(this.money - money >= 0){
            this.money -= money;
            this.total_money_removed += money;
            this.moviments.add(new Moviment(-money, type, this.money));
            return true;
        }
        return false;
}

    public boolean hasMoney(){
        return this.money > 0;
    }
    
    public boolean transfere(double money, String destinationiban, Date dateend){
       time_to_end_transference = dateend.getTime();
       return this.internalTransfere(money, destinationiban, dateend);
    }
    protected boolean internalTransfere(double money, String destinationiban, Date dateend){
        if(this.money < money)
            return false;
        if(destinationiban == this.iban)
            return false;
        this.total_money_removed += money;
        this.money -= money;
        this.moviments.add(new Moviment(-money, new Date(), dateend, this.iban, destinationiban, this.money));
        return true;
    }

    public boolean receive(double money, String originiban, Date dateend){
        if(money <= 0)
            return false;
        this.money += money;
        this.moviments.add(new Moviment(money, new Date(), dateend, originiban, this.iban, this.money));
        return true;
    }

    public ArrayList<Moviment> getMoviments(){
        demandMaintance();
        return this.moviments;
    }
    public boolean setChek(double value){
        return this.internalRemoveMoney(value, Configurations.mov_type_chek);
    }

}
