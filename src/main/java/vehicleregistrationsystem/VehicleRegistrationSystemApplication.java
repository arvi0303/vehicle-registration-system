package vehicleregistrationsystem;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public final class VehicleRegistrationSystemApplication {
    private VehicleRegistrationSystemApplication() {
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", exchange ->
                handleGet(exchange, "Vehicle Registration System is running.\nUse /health or /demo.\n"));
        server.createContext("/health", exchange -> handleGet(exchange, "UP\n"));
        server.createContext("/demo", exchange -> handleGet(exchange, buildDemoResponse()));

        server.setExecutor(null);
        server.start();

        System.out.println("Vehicle Registration System started on port " + port);
    }

    static String buildDemoResponse() {
        LoginSignUp loginSignUp = new LoginSignUp("Arun", "arun@example.com", "password123");

        Finance finance = new Finance(25000.0, "vehicle_registration_db");

        VehicleRegistration vehicleRegistration =
                new VehicleRegistration("Bike - TN09AB1234", "Arun");
        vehicleRegistration.setFinance(finance);

        Examination examination = new Examination("Road Test", "Approved");

        VehicleLicensing vehicleLicensing =
                new VehicleLicensing(21, "Arun", "Chennai");
        vehicleLicensing.setFinance(finance);
        vehicleLicensing.setExamination(examination);

        loginSignUp.setVehicleRegistration(vehicleRegistration);
        loginSignUp.setVehicleLicensing(vehicleLicensing);

        VehiclePermit vehiclePermit = vehicleRegistration.RegisterVehicle();

        StringBuilder builder = new StringBuilder();
        builder.append("Vehicle Registration System Demo").append(System.lineSeparator());
        builder.append("Sign Up: ").append(loginSignUp.SignUp()).append(System.lineSeparator());
        builder.append("Login: ")
                .append(loginSignUp.Login("arun@example.com", "password123"))
                .append(System.lineSeparator());
        builder.append("Vehicle Details Verified: ")
                .append(vehicleRegistration.GetDetailsAndVerifyDetails())
                .append(System.lineSeparator());
        builder.append("Vehicle Permit Issued: ")
                .append(vehiclePermit != null)
                .append(System.lineSeparator());
        builder.append("License Eligible: ")
                .append(vehicleLicensing.ProvideLicenseToDrive())
                .append(System.lineSeparator());
        builder.append("Finance: ")
                .append(finance.PoolingOfFunds())
                .append(System.lineSeparator());
        builder.append("Decision Support: ")
                .append(finance.BetterDecisionMaking())
                .append(System.lineSeparator());
        return builder.toString();
    }

    private static void handleGet(HttpExchange exchange, String body) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            writeResponse(exchange, 405, "Only GET is supported.\n");
            return;
        }

        writeResponse(exchange, 200, body);
    }

    private static void writeResponse(HttpExchange exchange, int statusCode, String body)
            throws IOException {
        byte[] responseBytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(responseBytes);
        }
    }
}
