package luisbank.Core.Controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/****
 * 
 * As validacoes de insersao de employeds e clients devem estar na camada do arquivo software
 */

public class Agency implements Serializable{
    
    private HashMap<String, IClient> clients;
    private HashMap<String, Person> employeds;
    private IClient selected_client;
    private Employed selected_employed;

    private String code;

    
    public Agency(String code){
        this.clients = new HashMap<>();
        this.employeds = new HashMap<>();
        this.code = code;
    }

    public HashMap<String, IClient> getClients(){
        return this.clients;
    }
    public HashMap<String, Person> getEmplyeds(){
        return this.employeds;
    }
    
    public String getCode(){
        return this.code;
    }
    public IClient getSelectedClient(){
        return this.selected_client;
    }
    public void setSelectedEmployed(String email){
        this.selected_employed = (Employed)this.employeds.get(email);
    }
    public Employed getSelectEmployed(){
        return this.selected_employed;
    }
    public Employed getEmployed(String email){
        return (Employed)this.employeds.get(email);
    }

    // public boolean selectedClientDeposit(double money){
    //     return selected_client.getAccount().depositMoney(money);
    // }
    // public boolean selectedClientRemoveMoney(double money){
    //     return selected_client.getAccount().removeMoney(money);
    // }
    

    public boolean appendEmployed(Employed person){
        for(Person costumer : employeds.values()){
            Employed employed2 = (Employed) costumer;
            if(employed2.getEmail().equals(person.getEmail())){ // prevejo que isso nunca vai acontecer, uma vez que o configure controla tudo
                System.out.println("Não pode criar uma conta com email já catalogado!");
                return false;
            }
        }
        this.employeds.put(person.getEmail(), person); //Funcionário salvo
        return true;
    }


    public boolean appendClient(IClient client){
        clients.put(client.getCode(), client);
        return true;
    }

    public boolean setSelectedClient(String code){
        selected_client = this.hasClientWithSameCode(code);
        if((selected_client instanceof IClient))
            return false;
        return true;
    }

    public Employed checkEmployedEmail(String email){        
        for (Person employed2 : employeds.values()) {
            Employed costumer3 = (Employed) employed2;
            if(email.equals(costumer3.getEmail()))
                return costumer3;
        }
        return null;
    }

    public Employed removeEmployed(String email){
        Employed employed2 = checkEmployedEmail(email);
        if(!(employed2 instanceof Employed))
            return null;
        return (Employed) employeds.remove(employed2.getEmail());
    }

    public IClient hasClientWithSameCode(String code){
        for(IClient client2 : clients.values()){
            if(client2.getCode().equals(code))
                return client2;
        }

        return null;
    }

    
    public String toString(){
        StringBuffer st = new StringBuffer();
        st.append("Agency: ");
        st.append(code);

        if(employeds.size() > 0){
            st.append("\n");
            st.append("======== Empregados ========");
            st.append("\n");
            for (Person person : employeds.values()) {
                Employed costumer = ((Employed)person);
                st.append("\n");
                st.append(costumer);
               
            }
        }
        if(clients.size() > 0){
            st.append("\n");
            st.append("=========== Clientes ===========");
            st.append("\n");
            for(IClient client : clients.values()){
                st.append("\n");
                st.append((client));
                
            }
        }
        return st.toString();
    }
    
    public static boolean isLocalIban(String iban){
        String iban2 = iban.substring(0, 2);
        if(iban2.isEmpty() || iban.length() != 20)
            return false;
        return true;
    }

    public IClient getClientByIban(String iban){
        for(IClient client : clients.values())
            if(client.getAccount().getIban().equals(iban))
                return client;
        return null;
    }
    
    public boolean setSelectedEmployedCheck(double value){
        return this.selected_client.getAccount().setChek(value);
    }
    
    public boolean transfereMoney(double value, String iban, Date dateend){
        
        return getSelectedClient().getAccount().transfere(value, iban, dateend);
    }
    public void receiveMoney(IClient client2, double value, String iban, Date date){
        client2.getAccount().receive(value, iban, date);
    }
    
    public boolean removeMoney(double money){
        
        return this.getSelectedClient().getAccount().removeMoney(money);
    }
    
    public boolean depositMoney(double money){
        return this.getSelectedClient().getAccount().depositMoney(money);
    }
}
