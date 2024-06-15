package luisbank.Core.Controller;

import java.util.ArrayList;

public interface IClient {
    
    public String getName();
    public String getCode();
    public Account getAccount();
    public void setActived(boolean value);
    public boolean checkActived();

    
    

    
}
