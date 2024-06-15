package luisbank.Core.Controller;

import java.util.Date;

public class AccountFinancy extends Account{
    
    
    private long last_time_receive_paymentfinancy;
    
    private double mov_type_saving_account;
    private Date date_blocked_remove_operation;
   
    

    public AccountFinancy(){
        super();
        this.percent = Configurations.percent_financy_account;
        this.mov_type_saving_account = Configurations.money_received_financy_account;
    }
    public boolean setCredite(double value){
        return false; //nao pode setar credito
    }

    public double getMoney(){
        doesCanToRemoveMoney();
        payFinancy();
        return this.money;
    }

    public boolean removeMoney(double money){
        
        if(!hasPermitionToRemoveMoney(money))
            return false;
        if(this.total_money_removed + money == Configurations.financy_account_limite_remove_money) //se no final de tudo chegar ao limite de movimentação
            this.date_blocked_remove_operation = new Date(); //inicializa a data
        return this.internalRemoveMoney(money);
    }
     public boolean transfere(double money, String destinationiban, Date dateend){
        if(!hasPermitionToRemoveMoney(money))
            return false;
        if(this.total_money_removed + money == Configurations.financy_account_limite_remove_money) //se no final de tudo chegar ao limite de movimentação
            this.date_blocked_remove_operation = new Date(); //inicializa a data
       return this.internalTransfere(money, destinationiban, dateend);
    }

    //se ten permissao para retirar
    public boolean hasPermitionToRemoveMoney(double money){
        if (Configurations.financy_account_limite_remove_money >= total_money_removed+money)
            return true;

        System.out.println("Limite atingido !");
        return false;
    }



    //pagar poupança
    private boolean payFinancy(){
        long seconds = seconds(dateaccount_created);
        int cont = countMultiplesTime(seconds, last_time_receive_paymentfinancy, Configurations.second_time_to_apply_policy);
        System.out.println(cont+ " pagar");
        double financy = cont * this.mov_type_saving_account;
        if(financy > 0 ){
            this.money += financy;
            last_time_receive_paymentfinancy = seconds;
            this.moviments.add(new Moviment(financy, Configurations.mov_type_saving_account, this.money));
        }
        
        return true;
    }

    //pagar poupança
    private boolean doesCanToRemoveMoney(){
        if(( date_blocked_remove_operation instanceof Date))
         {  
            long seconds = seconds(date_blocked_remove_operation);
            int cont = countMultiplesTime(seconds, 0, Configurations.milisseconds_time_pause_withdrow_account/1000);
            System.out.println(cont + " espere....");
            if(cont > 0 ){
                this.setCanToRemove();
                this.date_blocked_remove_operation = null; //sem data de limite atingido
            }
            else
                return false;
        }
        return true;
    }
    
    //remove o bloqueio de levantamento da conta
    public void setCanToRemove(){
        this.total_money_removed = 0;
    }

    
 
}
