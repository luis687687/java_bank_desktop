package luisbank.Core.Controller;

import java.io.Serializable;

public class Configurations implements Serializable {
    public static  String FILE_ADMINS = "./admins.ser";
    public static  String FILE_AGENCIES = "./agencies.ser";
    public static  Admin defaultadmin = new Admin("luis", "1234", "929723051", "951110648", "Luis Domingos Marques");
    public static  String defaultibancode = "LM";
    ///
    public static final String mov_type_deposit = "Depósito";
    public static final String mov_type_remov = "Remoção";
    public static final String mov_type_transf = "Transferência";
    public static final String mov_type_credite_pay = "Dívida de ";
    public static final String mov_type_credite_take_money_on_deposit = "Cobrança de dívida";
    public static final String mov_type_credite_request = "Pedido de crédito";
    public static final String mov_type_maintanance = "Manutenção";
    public static final String mov_type_saving_account = "Lucro de Poupança";
    public static final String mov_type_mult = "Multa de dívida";
    public static final String mov_type_chek = "Chek";
    public static double percent_mult_credit = 0.15;
    public static double percent_mult_financy = 0.05;

    public static long milisseconds_time_transf_aubsent_client = 5000; //tempo de espera em transferencias interebancárias
    public static long milisseconds_time_transf_existent_client = 0; //tempo de espera em transferencias locais
    public static long milisseconds_time_pause_withdrow_account = 60000; //tempo para pausar as operações da conta poupança
    public static long second_time_waiting_to_pay_credite_account = 20; //tempo para esperar na combraça da divida
    public static long second_time_to_apply_policy = 10; //tempo aplicar politicas em contas


    public static double maximus_credite = 2000; //maximo de credito por conta
    public static double percent_credit_normal_account = 0.7; //taxa a cobrar em contas correntes
    public static double percent_credit_financy_account = 0.3; //taxa a cobrar em contas poupança
    public static double percent_financy_appendmoney = 0.1; //taxa a pagar em contas poupança
    public static double money_received_financy_account = 0.4; //valor a cobrar pela manutenção
    public static double money_demand_corrent_account = 1.25; //valor a cobrar pela manutenção

    public static double financy_account_limite_remove_money = 2000;
}

