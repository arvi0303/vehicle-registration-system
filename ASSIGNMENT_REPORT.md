# Vehicle Registration System Assignment Report

## Question 1: Prototype Code in Java

The prototype code has been developed in Java by following the given class diagram as closely as possible. The UML class names that contained spaces or `/` were converted into valid Java class names:

- `Login/Sign Up` -> `LoginSignUp`
- `Vehicle Registration` -> `VehicleRegistration`
- `Vehicle Licensing` -> `VehicleLicensing`
- `Vehicle Permit` -> `VehiclePermit`

The implemented classes are:

- `LoginSignUp`
- `VehicleRegistration`
- `VehicleLicensing`
- `VehiclePermit`
- `Finance`
- `Examination`

These classes are available in `src/main/java/vehicleregistrationsystem`.

## Question 2: Source Code Repository Management Using Git/GitHub

For team-based development, the following branching strategy is used:

- `main`: stable branch that contains the final working project
- `develop`: integration branch where completed feature branches are merged
- `feature/vehicle-registration`: work related to UML classes and prototype code
- `feature/testing`: work related to unit tests
- `feature/devops`: work related to Maven, Jenkins, Docker, and deployment files
- `release/v1.0.0`: branch created before final submission

Recommended team-wise ownership:

- Team Member 1: `LoginSignUp.java`, `VehicleRegistration.java`, `VehiclePermit.java`
- Team Member 2: `VehicleLicensing.java`, `Examination.java`, `Finance.java`
- Team Member 3: `VehicleRegistrationSystemApplication.java`, test files, Jenkins, Docker, and deployment files

Code tag version used:

- `v1.0.0`: first complete assignment submission

Useful Git commands:

```bash
git init -b main
git checkout -b develop
git checkout -b feature/vehicle-registration
git checkout -b feature/testing
git checkout -b feature/devops
git checkout -b release/v1.0.0
git tag v1.0.0
```

Why this structure is effective:

- It separates development work by feature
- It reduces merge conflicts among team members
- It keeps `main` stable for demonstration and submission
- It provides a clear tagged version for evaluation

## Question 3: Build Automation Using Maven and Jenkins

### Tools Used

- Maven: used for dependency management, compilation, testing, and packaging
- Jenkins: used for continuous integration and automated pipeline execution

### Maven Usage

The `pom.xml` file is used to:

- set Java version to 17
- include JUnit 5 dependency for testing
- compile source code
- run test cases
- package the application as a runnable JAR file

Main Maven command:

```bash
mvn clean test package
```

### Jenkins Usage

The `Jenkinsfile` automates the following:

- checkout source code
- run Maven build and test commands
- archive the generated JAR file
- build the Docker image

Pipeline stages:

1. Checkout
2. Build And Test
3. Archive Artifact
4. Build Docker Image

This provides continuous integration because each code push can automatically trigger build and test execution.

## Question 4: Unit Testing

At least 5 unit test cases were required, and 9 test cases have been developed in:

- `src/test/java/vehicleregistrationsystem/VehicleRegistrationSystemTest.java`

Test cases included:

1. Valid login returns true
2. Invalid login returns false
3. Sign up returns the expected message
4. Vehicle details verification returns true for valid input
5. Register vehicle returns a permit when data is valid
6. Register vehicle returns null when data is invalid
7. Examination eligibility returns true for valid candidate data
8. License is provided for an eligible adult candidate
9. License is rejected for an under-age candidate

These tests were written using JUnit 5.

## Question 5: Cloud Deployment Using Containers

### Containerization

The application has been containerized using Docker. The `Dockerfile` performs:

- Maven build in a build stage
- creation of a lightweight runtime image using Java 17 JRE
- execution of the application on port `8080`

### Cloud Choice

The preferred cloud deployment choice is **Azure Kubernetes Service (AKS)** because it supports container deployment, scaling, monitoring, and rolling updates.

### Deployment Files

The Kubernetes manifests are stored in:

- `deploy/kubernetes/deployment.yaml`
- `deploy/kubernetes/service.yaml`

### Deployment Process

1. Build the Docker image

```bash
docker build -t vehicle-registration-system:v1.0.0 .
```

2. Tag and push the image to a container registry

```bash
docker tag vehicle-registration-system:v1.0.0 your-registry/vehicle-registration-system:v1.0.0
docker push your-registry/vehicle-registration-system:v1.0.0
```

3. Apply Kubernetes deployment and service files

```bash
kubectl apply -f deploy/kubernetes/deployment.yaml
kubectl apply -f deploy/kubernetes/service.yaml
```

4. Check rollout status

```bash
kubectl rollout status deployment/vehicle-registration-system
```

### Rollout Explanation

When a new version is ready:

- update the Docker image tag in `deployment.yaml`
- push the new image to the registry
- apply the updated manifest
- Kubernetes performs a rolling update with minimum downtime

### Monitoring Tool

The preferred monitoring tools are:

- Prometheus for metrics collection
- Grafana for dashboards and visualization

In addition, `kubectl logs` and cloud-native monitoring such as Azure Monitor can be used for checking logs, CPU, memory, and application health.

## Question 6: Demonstration and Documentation

### Real-Time Demonstration

The application contains a simple runnable server:

- `/health`: verifies that the service is running
- `/demo`: demonstrates a sample workflow using the UML classes

### Steps to Demonstrate

1. Compile the source code

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object FullName)
```

2. Start the application

```powershell
java -cp out vehicleregistrationsystem.VehicleRegistrationSystemApplication
```

3. Open the following URLs in a browser or use PowerShell:

```powershell
Invoke-WebRequest http://localhost:8080/health
Invoke-WebRequest http://localhost:8080/demo
```

### Documentation Included

The documentation submitted with this project includes:

- source code
- unit test source code
- Maven configuration
- Jenkins pipeline
- Docker containerization file
- Kubernetes deployment files
- this assignment report

## Conclusion

The project now contains a simple end-to-end solution for the Vehicle Registration System assignment. It covers prototype development, version control strategy, build automation, testing, container deployment, and demonstration support.
