# Vehicle Registration System

This project is a simple Java implementation of the Vehicle Registration System class diagram given in the assignment. It includes the prototype code, unit tests, Maven build configuration, Jenkins pipeline, Docker containerization, Kubernetes deployment files, and written answers for all six questions.

## Project Structure

- `src/main/java/vehicleregistrationsystem`: Java source code based on the UML diagram
- `src/test/java/vehicleregistrationsystem`: Unit test cases
- `Jenkinsfile`: Jenkins pipeline for CI build and test automation
- `Dockerfile`: Container image build file
- `deploy/kubernetes`: Deployment and service manifests
- `ASSIGNMENT_REPORT.md`: Answers for Questions 1 to 6

## Run Locally

Compile the project:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object FullName)
```

Start the application:

```powershell
java -cp out vehicleregistrationsystem.VehicleRegistrationSystemApplication
```

Test the running application:

```powershell
Invoke-WebRequest http://localhost:8080/health
Invoke-WebRequest http://localhost:8080/demo
```
