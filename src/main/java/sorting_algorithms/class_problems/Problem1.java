package sorting_algorithms.class_problems;

import java.util.HashMap;
import java.util.Map;

public class Problem1 {

    static class SmartDevice {
        String id;
        String type;
        boolean isOn;
        boolean supportsDimming;
        boolean supportsScheduling;
        boolean supportsEnergy;
        int brightness;
        String schedule;
        double energy;

        SmartDevice(String id, String type, boolean dimming,
                    boolean scheduling, boolean energyMonitoring,
                    double energy) {
            this.id = id;
            this.type = type;
            this.supportsDimming = dimming;
            this.supportsScheduling = scheduling;
            this.supportsEnergy = energyMonitoring;
            this.energy = energy;
            this.isOn = false;
        }

        void turnOn() {
            isOn = true;
            System.out.println(id + " is ON");
        }

        void dim(int level) {
            if (!supportsDimming) {
                System.out.println(id + " rejected: DIM unsupported");
            } else if (level < 0 || level > 100) {
                System.out.println(id + " rejected: invalid dim level");
            } else {
                brightness = level;
                System.out.println(id + " dimmed to " + level);
            }
        }

        void schedule(String time) {
            if (!supportsScheduling) {
                System.out.println(id + " rejected: SCHEDULE unsupported");
            } else {
                schedule = time;
                System.out.println(id + " scheduled " + time);
            }
        }

        void showEnergy() {
            if (!supportsEnergy) {
                System.out.println(id + " rejected: ENERGY unsupported");
            } else {
                System.out.println(id + " energy " + energy + " kWh");
            }
        }
    }

    static class SmartHome {
        private Map<String, SmartDevice> devices = new HashMap<>();

        void addDevice(SmartDevice device) {
            devices.put(device.id, device);
        }

        SmartDevice findDevice(String id) {
            return devices.get(id);
        }

        void processCommand(String command) {
            String[] parts = command.split("\\\\s+");
            SmartDevice device = parts.length > 1
                    ? findDevice(parts[1]) : null;

            if (device == null) {
                System.out.println("Device not found");
                return;
            }

            switch (parts[0].toUpperCase()) {
                case "ON":
                    device.turnOn();
                    break;
                case "DIM":
                    if (parts.length >= 3) {
                        try {
                            device.dim(Integer.parseInt(parts[2]));
                        } catch (NumberFormatException e) {
                            System.out.println(device.id
                                    + " rejected: invalid dim level");
                        }
                    }
                    break;
                case "SCHEDULE":
                    if (parts.length >= 3) {
                        device.schedule(parts[2]);
                    }
                    break;
                case "ENERGY":
                    device.showEnergy();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    public static void main(String[] args) {
        SmartHome home = new SmartHome();

        home.addDevice(new SmartDevice(
                "L1", "light", true, true, false, 0));

        home.addDevice(new SmartDevice(
                "F1", "fan", false, true, false, 0));

        home.addDevice(new SmartDevice(
                "P1", "plug", false, false, true, 12));

        home.processCommand("ON L1");
        home.processCommand("DIM L1 40");
        home.processCommand("DIM F1 30");
        home.processCommand("SCHEDULE F1 22:00");
        home.processCommand("ENERGY P1");
        home.processCommand("ENERGY L1");
    }
}