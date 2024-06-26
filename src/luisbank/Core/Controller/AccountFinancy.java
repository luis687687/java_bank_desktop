package luisbank.Core.Controller;

import java.util.Date;

public class AccountFinancy extends Account{
    
    
    private long last_time_receive_paymentfinancy;
    
    private double mov_type_saving_account;
    private Date date_blocked_remove_operation;
   
    

    public AccountFinancy(){
        super();        
        
        
    }
    public boolean setCredite(double value){
        return false; //nao pode setar credito
    }

    public double getMoney(){
        doesCanToRemoveMoney();
        payFinancy();
        return this.money;
    }
     public boolean hasCard(){
        return false;
    }

    public boolean removeMoney(double money){
        System.out.println("Aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        if(!hasPermitionToRemoveMoney(money))
            return false;
        System.out.println(hasPermitionToRemoveMoney(money)+"  pppppppeeeeeeeeeeerrrrrrrrrrrrmmmmmmmmmi");
        if(this.total_money_removed + money == Configurations.financy_account_limite_remove_money) //se no final de tudo chegar ao limite de movimentação
            this.date_blocked_remove_operation = new Date(); //inicializa a data
        return this.internalRemoveMoney(money, Configurations.mov_type_remov);
    }
     public boolean transfere(double money, String destinationiban, Date dateend){
        if(!hasPermitionToRemoveMoney(money))
            return false;
        if(this.total_money_removed + money > Configurations.financy_account_limite_remove_money)
            return false; //não pode transferir, mais que o limite
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
    
    public String getTimeToPermit(){
        doesCanToRemoveMoney();
        if(date_blocked_remove_operation == null){
            System.out.println("Sem nenhuma data de bloqueio ..........");
            return "Permito!";
        }
            
        long time_blocked = date_blocked_remove_operation.getTime()+Configurations.milisseconds_time_pause_withdrow_account;
        Date actualdate = new Date();
        if(time_blocked > actualdate.getTime())
            System.out.println("Boas....");
        else{
            System.out.println("Não.............");
            return "Autorizado!";
        }
        float diference_seconds = (time_blocked - actualdate.getTime())/1000f;
        System.out.println(diference_seconds+" dddddddd");
        float hour = (diference_seconds/3600f);
        System.out.println(hour+ " horassss ");
        long hour_int = (int)hour;
        float min = (hour-hour_int)*60;
        System.out.println(min+ " minn ");
        long min_int = (int)min;
        int second = (int)((min - min_int)*60);
        
        System.out.println(hour_int+ "h : "+min_int+"min : "+second+"s ");
        
        return  hour_int+"h:"+min_int+"min:"+second+"s";
    }



    //pagar poupança
    private boolean payFinancy(){
        long seconds = seconds(dateaccount_created);
        int cont = countMultiplesTime(seconds, last_time_receive_paymentfinancy, Configurations.second_time_to_apply_policy);
        System.out.println(cont+ " pagar");
        double financy = cont * Configurations.money_received_financy_account;
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
