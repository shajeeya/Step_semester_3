package inheritance_and_polymorphism.class_problems;

public class Problem4 {

    static class PatientProfile {

        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPinHash;

        // No-argument constructor
        public PatientProfile() {
            this(null, null);
        }

        // Name-only constructor
        public PatientProfile(String name) {
            this(null, name);
        }

        // ID + name constructor
        public PatientProfile(String patientId, String name) {
            this.patientId = patientId;
            this.name = name;
            this.discharged = false;
        }

        // Write-once patientId
        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String id) {
            if (this.patientId == null && id != null) {
                this.patientId = id;
            }
        }

        // JavaBean name property
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // JavaBean boolean property
        public boolean isDischarged() {
            return discharged;
        }

        public void setDischarged(boolean discharged) {
            this.discharged = discharged;
        }

        // Write-only locker PIN
        public void setLockerPin(String pin) {
            if (pin != null && pin.matches("\\d{4,6}")) {
                this.lockerPinHash = Integer.toHexString(pin.hashCode());
            }
        }
    }
}