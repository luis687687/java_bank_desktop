package luisbank.Core.Controller;

import java.io.Serializable;

public class Comertial implements Serializable{
    
    public String comertialcode;
    public String local;
    public String phone;
    public String optionalphone;
    public String name;
    public String image;

    public Comertial(String comertialcode, String name){
        this.comertialcode = comertialcode;
        this.name = name;
    }

}
