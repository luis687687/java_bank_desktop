package luisbank.Core.Controller;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.Date;
import java.util.HashMap;
/*****
 * 
 * OBS: métodos foram feitos com critério de responsabilidade única, quer dizer que tenho muitos
 * fragmentos de métodos, alguns são gerais, e têm suas seus métodos que os chama para casos específicos
 * os métodos gerais são posto com um //geral (indicando que tem um método que o consome para caso específico)
 * 
 * private static void updateAgencyMap(Agency agency)
 * 
 */
import luisbank.Core.Model.DataBase;

public class Software  {
    
    

    private static HashMap<String, Admin> admins = new HashMap<>();
    private static HashMap<String, Agency> agencies = new HashMap<>();
    private static DataBase<HashMap<String, Agency>> tableAgency = new DataBase<>(Configurations.FILE_AGENCIES);
    private static DataBase<HashMap<String, Admin>> tableAdmins = new DataBase<>(Configurations.FILE_ADMINS);
    private static Agency actual_Agency; //IMPORTANTE, quando seleccionar em abrir uma agencia especifica

    private static Employed logged_emplyed;
    
    public Software(){
        admins.put(Configurations.defaultadmin.getEmail(), Configurations.defaultadmin);
        Software.loadState();
        
      
    }

    public Employed getLoggedEmployed(){
        return logged_emplyed;
    }
    public static Agency getActualAgency(){
        return actual_Agency;
    }
   
   public static boolean appendAgency(Agency agency){
        if(!isAdmin())
            return false;
        for (Agency agency2 : agencies.values()) {   
            if(agency2.getCode().equals(agency.getCode())){
                return false;
            }
        }
        agencies.put(agency.getCode() , agency);
        return true;
   }

   public static boolean appendAdmin(Admin admin){
        if(!isAdmin())
            return false;
        if(checkEmailInSystem(admin.getEmail()) instanceof PairEmployedAgency)
            return false;
        admins.put(admin.getEmail() , admin);
        return true;
   }

   public static Agency getAgency(String code){
        for(Agency agency : agencies.values())
            if(agency.getCode().equals(code))
                return agency;
        return null;
   }


   public static void showAgencies(){
        StringBuffer st = new StringBuffer();
        for (Agency agency : agencies.values()) {
            st.append("\n\n ");
            st.append(agency);
        }
       System.out.println(st);
   }
   
   public static HashMap<String, Agency> getAgencies(){
       return agencies;
   }
   public static void showActualAgency(){
  
    System.out.println(actual_Agency);
    System.out.println("");
   }
   public static void showLoggedEmplyed(){
    System.out.println(logged_emplyed);
   }

   public static void showAdmins(){
    StringBuffer st = new StringBuffer();
    for (Admin admin : admins.values()) {
        st.append("\n\n ");
        st.append(admin);
    }
    System.out.println(st);
   }

   public static boolean setActualAgency(String code){
        actual_Agency = getAgency(code);
        return actual_Agency instanceof Agency;
   }

//    public static boolean actualAgencySelectClient(String clientcode){
//         return actual_Agency.setSelectedClient(clientcode);
//    }
//    public static boolean actualAgencySelectedClientDeposityMoney(double money){
//         return actual_Agency.getSelectedClient().getAccount().depositMoney(money);
//    }
//    public static boolean actualAgencySelectedClientRemoveMoney(double money){
//         return actual_Agency.getSelectedClient().getAccount().removeMoney(money);
//    }


    //retorna falso se o email do funcionário já existir
   //geral
   public static boolean agencyAppendEmployed(Agency agency, Employed employed){
    
    if(checkEmailInSystem(employed.getEmail()) instanceof PairEmployedAgency)
        return false;
    agency.appendEmployed(employed);
    saveAgencyState();
   
    return true;
   }

   //retorna falso se o numero de identificação do cliente já existir
   //geral
   public static boolean agencyAppendClient(Agency agency, IClient client){ 
        for(Agency agency2 : agencies.values())
            if(agency2.hasClientWithSameCode(client.getCode()) instanceof IClient)
                return false;
        agency.appendClient(client);
       
        return true;
   }

   //retorna falso se o email do funcionário já existir
   public static boolean actualAgencyAppendEmployed(Employed employed){
        return agencyAppendEmployed(actual_Agency, employed);
   }
   //retorna falso se o numero de identificação do cliente já existir
   public static boolean actualAgencyAppendClient(IClient client){
    return agencyAppendClient(actual_Agency, client);
   }

   public static HashMap<String, IClient> getClientsFromAgency(String agencycode){
        return getAgency(agencycode).getClients();
   }
   public static HashMap<String, Person> getEmployedsFromAgency(String agencycode){
        return getAgency(agencycode).getEmplyeds();
   }

   //selecciona um cliente, retornando par client-agencia
   public static PairClientAgency getOneClientById(String clientcode){
    for(Agency agency : agencies.values()){
        for(IClient client : agency.getClients().values()){
            if(client.getCode().equals(clientcode))
                return new PairClientAgency(client, agency);
        }
    }
    return null;
   }

   //selecciona um cliente, retornando par client-agencia
   public static PairClientAgency getOneClientByAccountNumber(String accountnumber){
    for(Agency agency : agencies.values()){
        for(IClient client : agency.getClients().values()){
            if(client.getAccount().getIban().equals(accountnumber))
                return new PairClientAgency(client, agency);
        }
    }
    return null;
   }


   //verifica se email está no sistema, para nao permitir duplicates no sistema
   //retorna um par, de agencia e empregado
   private static PairEmployedAgency checkEmailInSystem(String email){ 
    Employed employed2;
    for(Admin admin : admins.values())
        if(admin.getEmail().equals(email))
        //returno o object pois o email exist sim, 
        //mas o par de empregado e agencia, não condizem, pos é um admin, só é útil no acto de login
            return new PairEmployedAgency(admin, null); 
    for (Agency agency : agencies.values()) {
        employed2 = checkEmailOneAgency(agency, email);
       if(employed2 instanceof Employed)
            return new PairEmployedAgency(employed2, agency);
    }
    return null;
   }
   //verifica email presente em uma agencia para nao permitir duplicates na agencia
   public static Employed checkEmailOneAgency(Agency agency, String email){
        Employed employed2 = (agency.checkEmployedEmail(email));
        if(employed2 instanceof Employed)
            return employed2;
        return null;
   }

   //transferir funcionario com email de agencia from para agencia to
   public static boolean transfereEmployed(String email, Agency from, Agency to){
        if(!(to instanceof Agency) || !(from instanceof Agency))
            return false;

        if(to.checkEmployedEmail(email) instanceof Employed)
            return false; //já pertence neste distino
        if(!(from.checkEmployedEmail(email) instanceof Employed))
            return false; //nao pertence nesta origem
        to.appendEmployed(from.removeEmployed(email));
      
        return true;
   }

   public static void removeAgency(String code){
        agencies.remove(code);
   }

   //login de funcionários de agencias
   public static boolean login(String email, String pass){
        PairEmployedAgency pair;
        Employed employed2;
        pair = (checkEmailInSystem(email));
        if(pair instanceof PairEmployedAgency){
            employed2 = pair.employed;
            if(employed2.getPassword().equals(pass)){
                actual_Agency = pair.agency;
                logged_emplyed = employed2;
                return true;
            }
        }
        return false;
   }


   //Salvar tudo
   public static boolean saveAgencyState(){
        return tableAgency.save(agencies);
   }
   public static boolean saveAdminState(){
        return tableAdmins.save(admins);
   }

   public static boolean loadState(){
        HashMap<String, Agency> tb_agencies = tableAgency.read();
        HashMap<String, Admin> tb_admins = tableAdmins.read();
        if(tb_agencies instanceof HashMap && tb_admins instanceof HashMap){
            agencies = tb_agencies;
            admins = tb_admins;
            return true;
        }
        return false;    
   }

   public static void logout(){
        logged_emplyed = null;
        actual_Agency = null;
   }

 
    private static boolean isAdmin(){
        if(logged_emplyed instanceof Admin)
            return true;
        System.out.println("Faça login com admin Válido");
        return false;
    }

    public PairClientAgency getClientByIban(String iban){
        for(Agency agency : agencies.values()){
            IClient client = agency.getClientByIban(iban);
            if( client instanceof IClient)
                return new PairClientAgency(client, agency);
            }
        return null;
    }

    public static boolean transfereMoneyToExistentClient(IClient client2, double valor){
        if(client2 instanceof IClient){
            //data end será uma data normal, porque existe o cliente na nossa base de dados
            Date date = new Date((new Date()).getTime() + Configurations.milisseconds_time_transf_existent_client);
            if(actual_Agency.getSelectedClient().getAccount().transfere(valor, client2.getAccount().getIban(), date))
                client2.getAccount().receive(valor, actual_Agency.getSelectedClient().getAccount().getIban(), date); 
        }
        return true;
    }

    public static boolean transfereMoneyToAubsentClient(String iban, double valor){
        Date date = new Date((new Date()).getTime() + Configurations.milisseconds_time_transf_aubsent_client);
        actual_Agency.getSelectedClient().getAccount().transfere(valor, iban, date);
        return true;
    }
   
}
