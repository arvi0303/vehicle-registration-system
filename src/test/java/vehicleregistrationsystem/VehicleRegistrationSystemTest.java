package vehicleregistrationsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VehicleRegistrationSystemTest {

    @Test
    void loginReturnsTrueForValidCredentials() {
        LoginSignUp loginSignUp = new LoginSignUp("Arun", "arun@example.com", "password123");

        assertTrue(loginSignUp.Login("arun@example.com", "password123"));
    }

    @Test
    void loginReturnsFalseForInvalidCredentials() {
        LoginSignUp loginSignUp = new LoginSignUp("Arun", "arun@example.com", "password123");

        assertFalse(loginSignUp.Login("arun@example.com", "wrong-password"));
    }

    @Test
    void signupReturnsExpectedMessage() {
        LoginSignUp loginSignUp = new LoginSignUp("Arun", "arun@example.com", "password123");

        assertEquals("Sign up completed for Arun", loginSignUp.SignUp());
    }

    @Test
    void vehicleDetailsVerificationReturnsTrueWhenInputIsValid() {
        VehicleRegistration vehicleRegistration =
                new VehicleRegistration("Bike - TN09AB1234", "Arun");

        assertTrue(vehicleRegistration.GetDetailsAndVerifyDetails());
    }

    @Test
    void registerVehicleCreatesPermitAndCopiesFinanceReference() {
        VehicleRegistration vehicleRegistration =
                new VehicleRegistration("Bike - TN09AB1234", "Arun");
        Finance finance = new Finance(12000.0, "vehicle_registration_db");
        vehicleRegistration.setFinance(finance);

        VehiclePermit vehiclePermit = vehicleRegistration.RegisterVehicle();

        assertNotNull(vehiclePermit);
        assertSame(finance, vehiclePermit.getFinance());
    }

    @Test
    void registerVehicleReturnsNullForInvalidDetails() {
        VehicleRegistration vehicleRegistration =
                new VehicleRegistration("", "Arun");

        assertNull(vehicleRegistration.RegisterVehicle());
    }

    @Test
    void examinationReturnsTrueWhenCandidateDataIsAvailable() {
        Examination examination = new Examination("Road Test", "Approved");

        assertTrue(examination.FigureOutEligibleCandidates());
    }

    @Test
    void licenseIsProvidedForEligibleAdultCandidate() {
        VehicleLicensing vehicleLicensing =
                new VehicleLicensing(21, "Arun", "Chennai");
        vehicleLicensing.setExamination(new Examination("Road Test", "Approved"));

        assertTrue(vehicleLicensing.ProvideLicenseToDrive());
    }

    @Test
    void licenseIsRejectedForUnderAgeCandidate() {
        VehicleLicensing vehicleLicensing =
                new VehicleLicensing(17, "Arun", "Chennai");
        vehicleLicensing.setExamination(new Examination("Road Test", "Approved"));

        assertFalse(vehicleLicensing.ProvideLicenseToDrive());
    }
}
