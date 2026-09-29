package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Problem3 {

    interface Capability {
        String getName();
        void apply(Object value);
    }

    static class PowerCapability implements Capability {

        private boolean on;

        @Override
        public String getName() {
            return "Power";
        }

        @Override
        public void apply(Object value) {

            if (!(value instanceof Boolean)) {
                throw new IllegalArgumentException(
                        "Power value must be true or false."
                );
            }

            on = (Boolean) value;
        }

        public boolean isOn() {
            return on;
        }
    }

    static class BrightnessCapability implements Capability {

        private int brightness;

        @Override
        public String getName() {
            return "Brightness";
        }

        @Override
        public void apply(Object value) {

            if (!(value instanceof Integer)) {
                throw new IllegalArgumentException(
                        "Brightness must be an integer."
                );
            }

            int valueToSet = (Integer) value;

            if (valueToSet < 0 || valueToSet > 100) {
                throw new IllegalArgumentException(
                        "Brightness must be between 0 and 100."
                );
            }

            brightness = valueToSet;
        }

        public int getBrightness() {
            return brightness;
        }
    }

    static class TemperatureCapability implements Capability {

        private int temperature;

        @Override
        public String getName() {
            return "Temperature";
        }

        @Override
        public void apply(Object value) {

            if (!(value instanceof Integer)) {
                throw new IllegalArgumentException(
                        "Temperature must be an integer."
                );
            }

            int valueToSet = (Integer) value;

            if (valueToSet < 16 || valueToSet > 30) {
                throw new IllegalArgumentException(
                        "Temperature must be between 16 and 30."
                );
            }

            temperature = valueToSet;
        }

        public int getTemperature() {
            return temperature;
        }
    }

    static class Device {

        private String name;

        private Map<String, Capability> capabilities =
                new LinkedHashMap<>();

        public Device(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addCapability(Capability capability) {

            capabilities.put(
                    capability.getName(),
                    capability
            );
        }

        public boolean hasCapability(String capabilityName) {
            return capabilities.containsKey(capabilityName);
        }

        public Capability getCapability(String capabilityName) {
            return capabilities.get(capabilityName);
        }

        public void applyCapability(
                String capabilityName,
                Object value) {

            Capability capability =
                    capabilities.get(capabilityName);

            if (capability == null) {
                return;
            }

            capability.apply(value);
        }
    }

    static class SceneStep {

        private String capabilityName;
        private Object value;

        public SceneStep(
                String capabilityName,
                Object value) {

            this.capabilityName = capabilityName;
            this.value = value;
        }

        public int applyTo(List<Device> devices) {

            int actionsApplied = 0;

            for (Device device : devices) {

                if (device.hasCapability(capabilityName)) {

                    try {
                        device.applyCapability(
                                capabilityName,
                                value
                        );

                        actionsApplied++;

                        printAction(device);

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                                "Rejected: " +
                                device.getName() +
                                " " +
                                e.getMessage()
                        );
                    }
                }
            }

            return actionsApplied;
        }

        private void printAction(Device device) {

            if (capabilityName.equals("Power")) {

                boolean state = (Boolean) value;

                System.out.println(
                        device.getName() +
                        ": " +
                        (state ? "ON" : "OFF")
                );

            } else if (capabilityName.equals("Brightness")) {

                System.out.println(
                        device.getName() +
                        ": brightness set to " +
                        value +
                        "%"
                );

            } else if (capabilityName.equals("Temperature")) {

                System.out.println(
                        device.getName() +
                        ": temperature set to " +
                        value +
                        "°C"
                );
            }
        }
    }

    static class Scene {

        private String name;
        private List<SceneStep> steps =
                new ArrayList<>();

        public Scene(String name) {
            this.name = name;
        }

        public void addStep(SceneStep step) {
            steps.add(step);
        }

        public void execute(List<Device> devices) {

            System.out.println(
                    "Scene '" +
                    name +
                    "' started."
            );

            int actionsApplied = 0;

            for (SceneStep step : steps) {

                actionsApplied +=
                        step.applyTo(devices);
            }

            System.out.println(
                    "Scene '" +
                    name +
                    "' completed: " +
                    actionsApplied +
                    " actions applied."
            );
        }
    }

    public static void main(String[] args) {

        Device labAC =
                new Device("Lab AC");

        labAC.addCapability(
                new PowerCapability()
        );

        labAC.addCapability(
                new TemperatureCapability()
        );

        Device ceilingLights =
                new Device("Ceiling Lights");

        ceilingLights.addCapability(
                new PowerCapability()
        );

        ceilingLights.addCapability(
                new BrightnessCapability()
        );

        Device projector =
                new Device("Projector");

        projector.addCapability(
                new PowerCapability()
        );

        List<Device> devices =
                new ArrayList<>();

        devices.add(labAC);
        devices.add(ceilingLights);
        devices.add(projector);

        Scene lectureMode =
                new Scene("Lecture Mode");

        lectureMode.addStep(
                new SceneStep(
                        "Power",
                        true
                )
        );

        lectureMode.addStep(
                new SceneStep(
                        "Brightness",
                        40
                )
        );

        lectureMode.addStep(
                new SceneStep(
                        "Temperature",
                        24
                )
        );

        lectureMode.execute(devices);

        // Invalid temperature
        try {
            labAC.applyCapability(
                    "Temperature",
                    12
            );
        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Rejected: Lab AC temperature must be between 16°C and 30°C."
            );
        }

        // Runtime capability addition
        projector.addCapability(
                new BrightnessCapability()
        );

        System.out.println(
                "Projector: Brightness capability added."
        );

        projector.applyCapability(
                "Brightness",
                70
        );

        System.out.println(
                "Projector: brightness set to 70%."
        );
    }
}