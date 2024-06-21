package luisbank.Core.Controller;

import java.io.Serializable;
import java.util.Date;

public class Moviment implements Serializable{
    public double value;
    public String type;
    public Date datestart;
    public Date dateend;
    public String transferedestination;
    public String transfereorigin;
    public double finalmoney;
    
    //normal moviments
    public Moviment(double value, String type, double finalmoney){
        
        this.value = value;
        this.type = type;
        this.datestart = new Date();
        this.dateend = this.datestart;
        this.finalmoney = finalmoney;
    }
    
    private String getNormalDate(Date date){
        String[] date1 = date.toString().split(" ");
        String day = date1[2];
        String time = date1[3];
        String month = date1[1];
        String year = date1[5];
        
        // Wed Jun 19 10:08:24 WAT 2024
        return time+ " " + day+" "+month+" "+year;
    }
    
    public String getNormalDateStart(){
        return this.getNormalDate(datestart);
    }
    public String getNormalDateEnd(){
        String r = this.getNormalDate(dateend);
        return r;
    }
    

    //transfering moviments
    public Moviment(double value,Date datestart, Date dateend, String originiban, String destinationiban, double finalmoney){
        this.dateend = dateend;
        this.datestart = datestart;
        this.value = value;
   
        this.type = Configurations.mov_type_transf;
        this.transferedestination = destinationiban;
        this.transfereorigin = originiban;
        this.finalmoney = finalmoney;
    }
    
    public String getType(){
        return this.type;
    }
    public String getTransfereOrigin(){
        return this.transfereorigin;
    }
    public String getTransfereDestin(){
        return this.transferedestination;
    }
    public double getValue(){
        return this.value;
    }
    public double getFinalMoney(){
        return this.finalmoney;
    }
    public Date getStarts(){
        return this.datestart;
    }
    public Date getEnds(){
        return this.dateend;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        sb.append("Valor: ");
        sb.append(value);
        sb.append(" final: ");
        sb.append(finalmoney);
        sb.append(" Tipo: ");
        sb.append(type);
        sb.append(" data de ínicio: ");
        sb.append(datestart);
        sb.append(" data de término: ");
        sb.append(dateend);
        sb.append(" origem: ");
        sb.append(transfereorigin);

        sb.append(" destino: ");
        sb.append(transferedestination);

        return sb.toString();
    }

    
}
