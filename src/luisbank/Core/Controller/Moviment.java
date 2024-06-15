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
        this.dateend = dateend;
        this.datestart = datestart;
        this.value = value;
        this.type = type;
        this.datestart = new Date();
        this.dateend = this.datestart;
        this.finalmoney = finalmoney;
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
