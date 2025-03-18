import java.util.*;

// Abstract class for Smart Devices
abstract class SmartDevice implements Comparable<SmartDevice> {
    protected String deviceID;
    protected String name;
    protected boolean status;

    public SmartDevice(String deviceID, String name) {
        this.deviceID = deviceID;
        this.name = name;
        this.status = false; // Default OFF
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public void toggleStatus() {
        status = !status;
    }
    
    @Override
    public int compareTo(SmartDevice other) {
        return Boolean.compare(other.status, this.status); // ON devices first
    }
    
    public abstract void display();
}

// Smart Light class
class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String deviceID, String name, int brightness) {
        super(deviceID, name);
        this.brightness = brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public void display() {
        System.out.println("SmartLight: " + name + " | Status: " + (status ? "ON" : "OFF") + " | Brightness: " + brightness);
    }
}

// Thermostat class
class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String deviceID, String name, int temperature) {
        super(deviceID, name);
        this.temperature = temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void display() {
        System.out.println("Thermostat: " + name + " | Status: " + (status ? "ON" : "OFF") + " | Temperature: " + temperature);
    }
}

// Security Camera class
class SecurityCamera extends SmartDevice {
    private boolean recording;

    public SecurityCamera(String deviceID, String name) {
        super(deviceID, name);
        this.recording = false;
    }

    public void toggleRecording() {
        recording = !recording;
    }

    @Override
    public void display() {
        System.out.println("SecurityCamera: " + name + " | Status: " + (status ? "ON" : "OFF") + " | Recording: " + (recording ? "Yes" : "No"));
    }
}

// House class
class House {
    private List<SmartDevice> devices;

    public House() {
        devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void toggleAllDevices() {
        boolean turnOn = devices.stream().anyMatch(d -> !d.getStatus());
        for (SmartDevice device : devices) {
            if (device.getStatus() != turnOn) {
                device.toggleStatus();
            }
        }
    }

    public void displayDevices() {
        Collections.sort(devices);
        for (SmartDevice device : devices) {
            device.display();
        }
    }
}

// Main class
public class smartHomeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        House house = new House();

        while (true) {
            System.out.println("\n1. Add Smart Light\n2. Add Thermostat\n3. Add Security Camera\n4. Toggle All Devices\n5. Display Devices\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Light ID: ");
                    String lightID = scanner.nextLine();
                    System.out.print("Enter Light Name: ");
                    String lightName = scanner.nextLine();
                    System.out.print("Enter Brightness: ");
                    int brightness = scanner.nextInt();
                    house.addDevice(new SmartLight(lightID, lightName, brightness));
                    break;
                case 2:
                    System.out.print("Enter Thermostat ID: ");
                    String thermostatID = scanner.nextLine();
                    System.out.print("Enter Thermostat Name: ");
                    String thermostatName = scanner.nextLine();
                    System.out.print("Enter Temperature: ");
                    int temperature = scanner.nextInt();
                    house.addDevice(new Thermostat(thermostatID, thermostatName, temperature));
                    break;
                case 3:
                    System.out.print("Enter Camera ID: ");
                    String cameraID = scanner.nextLine();
                    System.out.print("Enter Camera Name: ");
                    String cameraName = scanner.nextLine();
                    house.addDevice(new SecurityCamera(cameraID, cameraName));
                    break;
                case 4:
                    house.toggleAllDevices();
                    System.out.println("All devices toggled.");
                    break;
                case 5:
                    house.displayDevices();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

