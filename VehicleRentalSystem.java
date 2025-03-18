import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Rentable Interface
interface Rentable {
    String getRegnNumber();
    Date getRentalDate();
}

// Enumerations
enum FuelType {
    ELECTRIC, PETROL, DIESEL
}

enum TransmissionType {
    MANUAL, AUTOMATIC
}

enum CarType {
    HATCHBACK, SEDAN, SUV
}

// Abstract Vehicle Class
abstract class Vehicle implements Rentable {
    private String regNumber;
    private FuelType fuelType;
    private double perDayRent;
    private Date rentalDate;

    public Vehicle(String regNumber, FuelType fuelType, double perDayRent, Date rentalDate) {
        this.regNumber = regNumber;
        this.fuelType = fuelType;
        this.perDayRent = perDayRent;
        this.rentalDate = rentalDate;
    }

    @Override
    public String getRegnNumber() {
        return regNumber;
    }

    @Override
    public Date getRentalDate() {
        return rentalDate;
    }

    // getters for internal use
    protected FuelType getFuelType() {
        return fuelType;
    }

    protected double getPerDayRent() {
        return perDayRent;
    }

    // Abstract method for displaying details
    protected abstract void displayDetails();
}

// Car Class 
class Car extends Vehicle {
    private TransmissionType transmissionType;
    private CarType carType;
    private int numberOfSeats;

    public Car(String regNumber, FuelType fuelType, double perDayRent, Date rentalDate,
              TransmissionType transmissionType, CarType carType, int numberOfSeats) {
        super(regNumber, fuelType, perDayRent, rentalDate);
        this.transmissionType = transmissionType;
        this.carType = carType;
        this.numberOfSeats = numberOfSeats;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public CarType getCarType() {
        return carType;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    protected void displayDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Car Details:");
        System.out.println("Registration Number: " + getRegnNumber());
        System.out.println("Fuel Type: " + getFuelType());
        System.out.println("Per Day Rent: ₹" + getPerDayRent());
        System.out.println("Rental Date: " + dateFormat.format(getRentalDate()));
        System.out.println("Transmission Type: " + transmissionType);
        System.out.println("Car Type: " + carType);
        System.out.println("Number of Seats: " + numberOfSeats);
        System.out.println();
    }
}

// Motorcycle Class
class Motorcycle extends Vehicle {
    private int engineDisplacement;
    private double weight;

    public Motorcycle(String regNumber, FuelType fuelType, double perDayRent, Date rentalDate,
                     int engineDisplacement, double weight) {
        super(regNumber, fuelType, perDayRent, rentalDate);
        this.engineDisplacement = engineDisplacement;
        this.weight = weight;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    protected void displayDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Motorcycle Details:");
        System.out.println("Registration Number: " + getRegnNumber());
        System.out.println("Fuel Type: " + getFuelType());
        System.out.println("Per Day Rent: ₹" + getPerDayRent());
        System.out.println("Rental Date: " + dateFormat.format(getRentalDate()));
        System.out.println("Engine Displacement: " + engineDisplacement + " cc");
        System.out.println("Weight: " + weight + " kg");
        System.out.println();
    }
}

// User Class
class User {
    private String userId;
    private String name;
    private List<Vehicle> rentedVehicles;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.rentedVehicles = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void rentVehicle(Vehicle vehicle) {
        rentedVehicles.add(vehicle);
        System.out.println("Vehicle with registration number " + vehicle.getRegnNumber() + 
                          " rented successfully by " + name);
    }

    public void displayRentedVehicles() {
        if (rentedVehicles.isEmpty()) {
            System.out.println("No vehicles rented by " + name);
            return;
        }

        // Sort vehicles by rental date before displaying
        sortVehiclesByDate();
        
        System.out.println("\nVehicles rented by " + name + " (User ID: " + userId + "):");
        System.out.println("==================================================");
        
        for (Vehicle vehicle : rentedVehicles) {
            vehicle.displayDetails();
        }
    }

    public void sortVehiclesByDate() {
        Collections.sort(rentedVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v1.getRentalDate().compareTo(v2.getRentalDate());
            }
        });
    }
}

// RentalSystem class to manage the system
class RentalSystem {
    private List<User> users;
    private List<Vehicle> availableVehicles;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public RentalSystem() {
        users = new ArrayList<>();
        availableVehicles = new ArrayList<>();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    // Method to initialize some sample data
    public void initializeSampleData() {
        try {
            // Add some sample vehicles
            Car car1 = new Car("KA01AB1234", FuelType.PETROL, 2000.0, dateFormat.parse("15-01-2025"), 
                             TransmissionType.AUTOMATIC, CarType.SEDAN, 4);
            
            Car car2 = new Car("MH02CD5678", FuelType.DIESEL, 2500.0, dateFormat.parse("20-01-2025"), 
                             TransmissionType.MANUAL, CarType.SUV, 6);
            
            Motorcycle bike1 = new Motorcycle("DL03EF9012", FuelType.PETROL, 1000.0, dateFormat.parse("10-01-2025"), 
                                           150, 130.5);

            availableVehicles.add(car1);
            availableVehicles.add(car2);
            availableVehicles.add(bike1);

            // Add a sample user
            users.add(new User("U001", "John Doe"));
            
            System.out.println("Sample data initialized successfully!");
        } catch (ParseException e) {
            System.out.println("Error initializing sample data: " + e.getMessage());
        }
    }

    // Method to display the main menu
    public void showMainMenu() {
        int choice = 0;
        
        do {
            System.out.println("\n===== Vehicle Rental Management System =====");
            System.out.println("1. Register New User");
            System.out.println("2. Add New Vehicle");
            System.out.println("3. Rent a Vehicle");
            System.out.println("4. Display User's Rented Vehicles");
            System.out.println("5. Display All Available Vehicles");
            System.out.println("6. Initialize Sample Data");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        registerNewUser();
                        break;
                    case 2:
                        addNewVehicle();
                        break;
                    case 3:
                        rentVehicle();
                        break;
                    case 4:
                        displayUserRentedVehicles();
                        break;
                    case 5:
                        displayAvailableVehicles();
                        break;
                    case 6:
                        initializeSampleData();
                        break;
                    case 0:
                        System.out.println("Thank you for using Vehicle Rental System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = -1;
            }
        } while (choice != 0);
    }

    // Method to register a new user
    private void registerNewUser() {
        System.out.println("\n----- Register New User -----");
        
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        // Check if user ID already exists
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                System.out.println("User ID already exists. Please try a different ID.");
                return;
            }
        }
        
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        
        User newUser = new User(userId, userName);
        users.add(newUser);
        
        System.out.println("User registered successfully!");
    }

    // Method to add a new vehicle
    private void addNewVehicle() {
        System.out.println("\n----- Add New Vehicle -----");
        System.out.println("1. Add Car");
        System.out.println("2. Add Motorcycle");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please try again.");
                return;
            }
            
            // Common vehicle details
            System.out.print("Enter Registration Number: ");
            String regNumber = scanner.nextLine();
            
            // Check if registration number already exists
            for (Vehicle vehicle : availableVehicles) {
                if (vehicle.getRegnNumber().equals(regNumber)) {
                    System.out.println("Vehicle with this registration number already exists.");
                    return;
                }
            }
            
            // Get fuel type
            FuelType fuelType = getFuelTypeInput();
            
            // Get per day rent
            System.out.print("Enter Per Day Rent (in Rupees): ");
            double perDayRent = Double.parseDouble(scanner.nextLine());
            
            // Get rental date
            Date rentalDate = getDateInput("Enter Rental Date (dd-MM-yyyy): ");
            
            // Add specific vehicle based on choice
            if (choice == 1) {
                // Car specific details
                TransmissionType transmissionType = getTransmissionTypeInput();
                CarType carType = getCarTypeInput();
                
                System.out.print("Enter Number of Seats (2 or 4 or 6): ");
                int numberOfSeats = Integer.parseInt(scanner.nextLine());
                
                if (numberOfSeats != 2 && numberOfSeats != 4 && numberOfSeats != 6) {
                    System.out.println("Invalid number of seats. Only 2, 4, or 6 are allowed.");
                    return;
                }
                
                Car newCar = new Car(regNumber, fuelType, perDayRent, rentalDate, 
                                  transmissionType, carType, numberOfSeats);
                availableVehicles.add(newCar);
                System.out.println("Car added successfully!");
                
            } else {
                // Motorcycle specific details
                System.out.print("Enter Engine Displacement (in cc): ");
                int engineDisplacement = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Enter Weight (in kg): ");
                double weight = Double.parseDouble(scanner.nextLine());
                
                Motorcycle newMotorcycle = new Motorcycle(regNumber, fuelType, perDayRent, rentalDate,
                                                      engineDisplacement, weight);
                availableVehicles.add(newMotorcycle);
                System.out.println("Motorcycle added successfully!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to rent a vehicle
    private void rentVehicle() {
        System.out.println("\n----- Rent a Vehicle -----");
        
        if (users.isEmpty()) {
            System.out.println("No users registered. Please register a user first.");
            return;
        }
        
        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available for rent.");
            return;
        }
        
        // Select user
        User selectedUser = selectUser();
        if (selectedUser == null) return;
        
        // Select vehicle
        System.out.println("\nAvailable Vehicles:");
        for (int i = 0; i < availableVehicles.size(); i++) {
            Vehicle v = availableVehicles.get(i);
            System.out.println((i + 1) + ". " + v.getRegnNumber() + 
                              (v instanceof Car ? " (Car)" : " (Motorcycle)"));
        }
        
        System.out.print("Select a vehicle (1-" + availableVehicles.size() + "): ");
        try {
            int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (vehicleIndex < 0 || vehicleIndex >= availableVehicles.size()) {
                System.out.println("Invalid selection.");
                return;
            }
            
            Vehicle selectedVehicle = availableVehicles.get(vehicleIndex);
            selectedUser.rentVehicle(selectedVehicle);
            
            // Remove the vehicle from available vehicles
            availableVehicles.remove(vehicleIndex);
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to display a user's rented vehicles
    private void displayUserRentedVehicles() {
        System.out.println("\n----- Display User's Rented Vehicles -----");
        
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        
        User selectedUser = selectUser();
        if (selectedUser != null) {
            selectedUser.displayRentedVehicles();
        }
    }

    // Method to display all available vehicles
    private void displayAvailableVehicles() {
        System.out.println("\n----- Available Vehicles -----");
        
        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        
        for (Vehicle vehicle : availableVehicles) {
            vehicle.displayDetails();
        }
    }

    // Helper method to select a user
    private User selectUser() {
        System.out.println("Select a user:");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println((i + 1) + ". " + u.getName() + " (ID: " + u.getUserId() + ")");
        }
        
        System.out.print("Enter your choice (1-" + users.size() + "): ");
        try {
            int userIndex = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (userIndex < 0 || userIndex >= users.size()) {
                System.out.println("Invalid selection.");
                return null;
            }
            
            return users.get(userIndex);
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return null;
        }
    }

    // Helper method to get fuel type input
    private FuelType getFuelTypeInput() {
        FuelType fuelType = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Select Fuel Type:");
            System.out.println("1. ELECTRIC");
            System.out.println("2. PETROL");
            System.out.println("3. DIESEL");
            System.out.print("Enter your choice (1-3): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        fuelType = FuelType.ELECTRIC;
                        validInput = true;
                        break;
                    case 2:
                        fuelType = FuelType.PETROL;
                        validInput = true;
                        break;
                    case 3:
                        fuelType = FuelType.DIESEL;
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return fuelType;
    }

    // Helper method to get transmission type input
    private TransmissionType getTransmissionTypeInput() {
        TransmissionType transmissionType = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Select Transmission Type:");
            System.out.println("1. MANUAL");
            System.out.println("2. AUTOMATIC");
            System.out.print("Enter your choice (1-2): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        transmissionType = TransmissionType.MANUAL;
                        validInput = true;
                        break;
                    case 2:
                        transmissionType = TransmissionType.AUTOMATIC;
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return transmissionType;
    }

    // Helper method to get car type input
    private CarType getCarTypeInput() {
        CarType carType = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Select Car Type:");
            System.out.println("1. HATCHBACK");
            System.out.println("2. SEDAN");
            System.out.println("3. SUV");
            System.out.print("Enter your choice (1-3): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        carType = CarType.HATCHBACK;
                        validInput = true;
                        break;
                    case 2:
                        carType = CarType.SEDAN;
                        validInput = true;
                        break;
                    case 3:
                        carType = CarType.SUV;
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return carType;
    }

    // Helper method to get date input
    private Date getDateInput(String prompt) {
        Date date = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            String dateStr = scanner.nextLine();
            
            try {
                date = dateFormat.parse(dateStr);
                validInput = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd-MM-yyyy format.");
            }
        }
        
        return date;
    }
}

// Main Class
public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.showMainMenu();
    }
}