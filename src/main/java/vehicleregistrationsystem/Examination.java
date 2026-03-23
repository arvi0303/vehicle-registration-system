package vehicleregistrationsystem;

public class Examination {
    public String name;
    protected String signature;

    public Examination(String name, String signature) {
        this.name = name;
        this.signature = signature;
    }

    public boolean FigureOutEligibleCandidates() {
        return name != null
                && !name.isBlank()
                && signature != null
                && !signature.isBlank();
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }
}
