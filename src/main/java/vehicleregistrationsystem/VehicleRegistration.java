package vehicleregistrationsystem;

public class VehicleRegistration {
    protected String vehicleDetails;
    public String owner;
    private VehiclePermit vehiclePermit;
    private Finance finance;

    public VehicleRegistration(String vehicleDetails, String owner) {
        this.vehicleDetails = vehicleDetails;
        this.owner = owner;
    }

    public VehiclePermit RegisterVehicle() {
        if (!GetDetailsAndVerifyDetails()) {
            return null;
        }

        vehiclePermit = new VehiclePermit();
        vehiclePermit.setFinance(finance);
        return vehiclePermit;
    }

    public boolean GetDetailsAndVerifyDetails() {
        return vehicleDetails != null
                && !vehicleDetails.isBlank()
                && owner != null
                && !owner.isBlank();
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public String getOwner() {
        return owner;
    }

    public VehiclePermit getVehiclePermit() {
        return vehiclePermit;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }
}
