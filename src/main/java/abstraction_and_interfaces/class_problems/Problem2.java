package abstraction_and_interfaces.class_problems;

public class Problem2 {

    interface Alertable {
        String sendAlert(String message);
    }

    static class SecuritySensor {
        private String zoneName;

        public SecuritySensor(String zoneName) {
            this.zoneName = zoneName;
        }

        public String getZoneName() {
            return zoneName;
        }
    }

    static class MotionSensor extends SecuritySensor implements Alertable {

        public MotionSensor(String zoneName) {
            super(zoneName);
        }

        @Override
        public String sendAlert(String message) {
            return "[" + getZoneName() + "] " + message;
        }
    }

    static class DualZoneMotionSensor extends MotionSensor {
        private String secondZoneName;

        public DualZoneMotionSensor(String zoneName, String secondZoneName) {
            super(zoneName);
            this.secondZoneName = secondZoneName;
        }

        @Override
        public String sendAlert(String message) {
            return super.sendAlert(message)
                    + " [also covering " + secondZoneName + "]";
        }
    }

    static class SmokeDetector implements Alertable {
        private String deviceId;

        public SmokeDetector(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public String sendAlert(String message) {
            return "[" + deviceId + "] " + message;
        }
    }

    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor sensor = (MotionSensor) a;
            return sensor.getZoneName();
        }

        return "Not a MotionSensor";
    }

    public static void main(String[] args) {

        MotionSensor m = new MotionSensor("Living Room");
        DualZoneMotionSensor d =
                new DualZoneMotionSensor("Hallway", "Stairwell");
        SmokeDetector s = new SmokeDetector("SD-01");

        System.out.println(m.sendAlert("Motion detected"));
        System.out.println(d.sendAlert("Motion detected"));
        System.out.println(s.sendAlert("Smoke detected"));

        Alertable[] devices = {m, d, s};

        broadcastAll(devices, "Emergency detected");

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));
    }
}