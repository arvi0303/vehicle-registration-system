package vehicleregistrationsystem;

public class VehicleLicensing {
    public int age;
    public String name;
    public String address;
    private Finance finance;
    private Examination examination;

    public VehicleLicensing(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public boolean ProvideLicenseToDrive() {
        return age >= 18
                && examination != null
                && examination.FigureOutEligibleCandidates();
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }
}
