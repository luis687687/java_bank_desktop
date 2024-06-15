package luisbank.Core.Controller;

public class PairClientAgency {
    public IClient client;
    public Agency agency;
    public PairClientAgency(IClient client, Agency agency){
        this.client = client;
        this.agency = agency;
    }

}
