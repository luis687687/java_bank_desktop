import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import luisbank.Core.Controller.Account;
import luisbank.Core.Controller.AccountFinancy;
import luisbank.Core.Controller.Admin;
import luisbank.Core.Controller.Agency;
import luisbank.Core.Controller.BI;
import luisbank.Core.Controller.Comertial;
import luisbank.Core.Controller.Software;
import luisbank.Core.Controller.Employed;
import luisbank.Core.Controller.IClient;
import luisbank.Core.Controller.Moviment;
import luisbank.Core.Controller.PairClientAgency;
import luisbank.Core.Controller.Person;
import luisbank.Core.Controller.PersonColective;
import luisbank.Core.Controller.PersonSingular;
import luisbank.view.Manipulations;


public class Teste {
    
    public static void main(String ...args) throws Exception {

       // new Software();
//
//        Scanner sc = new Scanner(System.in);
//
//       
//        String response_str;
//        boolean response_logic;
//        Date d = new Date();
//
//
//        long tim = d.getTime();
//        long alter = tim + 1000;
//        Date d2 = new Date();
//        d2.setTime(alter);

        System.out.print(Manipulations.formatMoney("300000"));
//        
        
//        while (true) {
//            
//           System.out.println("1 -criar agencia ");
//           System.out.println("2 - ver lista de agencias");
//           System.out.println("3 - seleccionar agencia e adicionar funcionario na agencia ");
//           System.out.println("4- Transferir funcionario para outra agencia ");
//           System.out.println("5 - Eliminar agencia ");
//           System.out.println("6 - Mostrar dados de login");
//           System.out.println("7 - Login Emplyed");
//           System.out.println("8 - Add admin");
//           System.out.println("9 - show admin");
//           System.out.println("10 - Criar Cliente");
//           System.out.println("11 - Ver cliente");
//           System.out.println("12 - Depositar dinheiro na conta do cliente");
//           System.out.println("13 - Levantar dinheiro na conta do cliente");
//           System.out.println("14 - Transferencia");
//           System.out.println("15 - Pedir crédito");
//           System.out.println("x - terminar sessao");
//           System.out.println("* sair");
//
//            response_str = sc.next();
//            sc.nextLine();
//            if(response_str.equals("1")){
//                System.out.println("Code da agencia");
//                response_str = sc.next();
//                sc.nextLine();
//                Agency agency = new Agency(response_str);
//                response_logic = Software.appendAgency(agency);
//                if(response_logic){
//                    System.out.println("Well");
//                }
//                
//                
//            }
//            else{
//                if(response_str.equals("2"))
//                    Software.showAgencies();
//                else{
//                   
//                    if(response_str.equals("3"))
//                    {   
//                        System.out.println("Adicionar funcionário");
//                        System.out.println("Choose the agency code");
//                        response_str = sc.next();
//                        sc.nextLine();
//                        response_logic = Software.setActualAgency(response_str);
//                        if(response_logic){
//                            System.out.println("Informe o email: ");
//                            String email = sc.nextLine();
//                            System.out.println("A passe: ");
//                            String pass = sc.nextLine();
//
//                            Employed costumer = new Employed(email, pass, "922299", "", "Nome" );
//
//                            Software.actualAgencyAppendEmployed(costumer);
//                            
//                        }
//                    }
//                    else{
//                        if(response_str.equals("4")){
//                            
//                            System.out.println("Transferir funcionario");
//                            System.out.println("Code Origem");
//                            response_str = sc.next();
//                            sc.nextLine();
//                            Agency agency = Software.getAgency(response_str);
//                            if(agency instanceof Agency){
//                                boolean repeat = false;
//                                do{
//                                    System.out.println("Informe o email: ");
//                                    String email = sc.nextLine();
//                                    System.out.println("Informe a agencia destino (code): ");
//                                    String code = sc.nextLine();
//                                    Agency agency2 = Software.getAgency(code);
//
//                                    Software.transfereEmployed(email, agency, agency2);
//                                }while(repeat);
//                               
//                               
//                                
//                            }
//                        }
//                        else{
//
//                            if(response_str.equals("5")){
//
//                            }
//                            else{
//                                if(response_str.equals("6")){
//                                    Software.showLoggedEmplyed();
//                                    Software.showActualAgency();
//
//                                }
//                                else{
//                                    if(response_str.equals("7")){
//                                       System.out.println("Login de Email e Pass");
//                                       Software.login(
//                                        sc.nextLine(), sc.nextLine()
//                                       );
//                                       
//    
//                                    }
//                                    else{
//                                        if(response_str.equals("8")){
//                                            System.out.println("Add admin");
//                                            System.out.println("Email e senha");
//                                            Admin admin = new Admin(sc.nextLine(), sc.nextLine(), "", "", "Nome");
//                                            Software.appendAdmin(admin);
//                                         }
//                                         else{
//                                            if(response_str.equals("9")){
//                                                System.out.println("Show");
//                                                Software.showAdmins();
//                                                
//             
//                                             }
//                                             else{
//                                                if(response_str.equals("x")){
//                                                   Software.logout();
//                                                 }
//                                                 else{
//                                                    if(response_str.equals("10")){
//                                                        System.out.println("Informe a agencia");
//                                                        response_str = sc.nextLine();
//                                                        if(Software.setActualAgency(response_str)){
//                                                            System.out.println("Code do cliente");
//                                                            String codeclient = sc.nextLine();
//                                                            System.out.println("Informe o nome ");
//
//                                                            String name = sc.nextLine();
//                                                            BI bi = new BI(name, name, name, name);
//                                                            bi.setNumber(codeclient);
//                                                            bi.fullname = name;
//                                                            IClient client;
//                                                            System.out.println("O tipo de cliente (singular/s e colectivo/c)");
//                                                            String clitype = sc.nextLine();
//                                                            System.out.println("O tipo de conta (p/c)");
//                                                            String accountype = sc.nextLine();
//                                                            Account account = new Account();
//                                                            if(accountype.equals("p")){
//                                                                account = new AccountFinancy();
//                                                            }
//                                                                    
//
//                                                            if(clitype.equals("s")){
//                                                                client = new PersonSingular(account, bi, "", "");
//                                                            }else{
//                                                                client = new PersonColective(account, new Comertial(codeclient, name, "" , ""));
//                                                            }
//                                                                    
//                                                            Software.actualAgencyAppendClient(client);
//                                                        }
//
//                                                      }
//                                                      else{
//                                                        if(response_str.equals("11")){
//                                                            System.out.println("Informe a identificação do cliente");
//                                                            response_str = sc.nextLine();
//                                                            PairClientAgency pair = Software.getOneClientById(response_str);
//                                                          
//                                                            if(pair instanceof PairClientAgency){
//                                                                System.out.println(pair.client);
//                                                                for (Moviment moviment : pair.client.getAccount().getMoviments()) 
//                                                                    System.out.println(moviment);
//                                                            }
//                                                            
//                                                            System.out.println("");
//                                                          }
//                                                          else{
//                                                            if(response_str.equals("12")){
//                                                                System.out.println("Informe a identificação do cliente a depositar ");
//                                                                response_str = sc.nextLine();
//                                                                PairClientAgency pair = Software.getOneClientById(response_str);
//                                                                System.out.println(pair+ " parrr ");
//                                                                if(pair instanceof PairClientAgency)
//                                                                {
//                                                                    if(pair.agency instanceof Agency){
//                                                                        Software.setActualAgency(pair.agency.getCode());
//                                                                        Software.getActualAgency().setSelectedClient(pair.client.getCode());
//                                                                        System.out.println("Valor a depositar ");
//                                                                        Software.getActualAgency().getSelectedClient().getAccount().depositMoney(sc.nextDouble());
//
//                                                                    }
//                                                                
//                                                                }
//                                                            
//                                                              }
//                                                              else{
//                                                                if(response_str.equals("13")){
//                                                                    System.out.println("Informe a identificação do cliente a levantar ");
//                                                                    response_str = sc.nextLine();
//                                                                    PairClientAgency pair = Software.getOneClientById(response_str);
//                                                                    
//                                                                    if(pair instanceof PairClientAgency)
//                                                                    {
//                                                                        if(pair.agency instanceof Agency){
//                                                                            Software.setActualAgency(pair.agency.getCode());
//                                                                            Software.getActualAgency().setSelectedClient(pair.client.getCode());
//                                                                            Software.getActualAgency().getSelectedClient().getAccount().removeMoney(sc.nextDouble());
//    
//                                                                        }
//                                                                    
//                                                                    }
//                                                                
//                                                                  }
//                                                                  else{
//                                                                    if(response_str.equals("14")){
//                                                                        System.out.println("Informe a identificação ou iban do cliente a transferir");
//                                                                        response_str = sc.nextLine();
//                                                                        PairClientAgency pair = Software.getOneClientById(response_str);
//                                                                        
//                                                                        if(pair instanceof PairClientAgency)
//                                                                        {
//                                                                            if(pair.agency instanceof Agency){
//                                                                                Software.setActualAgency(pair.agency.getCode());
//                                                                                Software.getActualAgency().setSelectedClient(pair.client.getCode());
//                                                                                System.out.println("Conta destino: ");
//                                                                                String info = sc.nextLine();
//                                                                                PairClientAgency destin = Software.getOneClientByAccountNumber(info);
//                                                                                System.out.println("Valor a transferir ");
//                                                                                double valor = sc.nextDouble();
//                                                                                sc.nextLine();
//                                                                                if(! (destin instanceof PairClientAgency)){
//                                                                                    destin = Software.getOneClientById(info);
//                                                                                    if( ! (destin instanceof PairClientAgency))
//                                                                                    {
//                                                                                        System.out.println("Not found....");
//                                                                                        Software.transfereMoneyToAubsentClient(info, valor);
//                                                                                        continue;
//                                                                                    }
//                                                                                    
//                                                                                }
//                                                                                System.out.println("Found...");
//                                                                                Software.transfereMoneyToExistentClient(destin.client, valor);
//        
//                                                                            }
//                                                                        
//                                                                        }
//                                                                    
//                                                                      }
//                                                                      else{
//                                                                        if(response_str.equals("15")){
//                                                                           
//                                                                            System.out.println("Informe a identificação ou iban do cliente a transferir");
//                                                                            response_str = sc.nextLine();
//                                                                            PairClientAgency pair = Software.getOneClientById(response_str);
//                                                                            if(pair instanceof PairClientAgency){
//                                                                                System.out.println("Informe o montante ");
//                                                                                double v = sc.nextDouble();
//                                                                                sc.nextLine();
//                                                                                IClient client = pair.client;
//                                                                                if(!client.getAccount().setCredite(v))
//                                                                                    System.out.println("Não permitido");
//                                                                            }
//                                                                            
//                                                                          }
//                                                                          else{
//                                                                              System.out.println("Salvando...");
//                                                                              Software.saveAgencyState();
//                                                                              Software.saveAdminState();
//                                                                              Software.logout();
//                                                                              return;
//                                                                          }
//                                                                      }
//                                                                  }
//                                                              }
//                                                          }
//                                                     
//                                                      }
//                                                 }
//                                             }
//                                         }
//                                    }
//                                   
//                                }
//                                
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    
    }
}
