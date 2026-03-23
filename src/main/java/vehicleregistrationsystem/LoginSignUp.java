package vehicleregistrationsystem;

public class LoginSignUp {
    private String name;
    private String emailId;
    private String password;
    private VehicleRegistration vehicleRegistration;
    private VehicleLicensing vehicleLicensing;

    public LoginSignUp(String name, String emailId, String password) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
    }

    public boolean Login(String enteredEmailId, String enteredPassword) {
        return emailId != null
                && password != null
                && emailId.equals(enteredEmailId)
                && password.equals(enteredPassword);
    }

    public String SignUp() {
        return "Sign up completed for " + name;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public VehicleRegistration getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(VehicleRegistration vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public VehicleLicensing getVehicleLicensing() {
        return vehicleLicensing;
    }

    public void setVehicleLicensing(VehicleLicensing vehicleLicensing) {
        this.vehicleLicensing = vehicleLicensing;
    }
}
