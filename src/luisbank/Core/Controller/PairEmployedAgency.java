package luisbank.Core.Controller;

public class PairEmployedAgency {
    
    public Agency agency;
    public Employed employed;
    public PairEmployedAgency(Employed employed, Agency agency){
        this.agency = agency;
        this.employed = employed;
    }
}
