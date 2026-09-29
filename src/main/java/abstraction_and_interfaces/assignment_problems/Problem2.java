package abstraction_and_interfaces.assignment_problems;

public class Problem2 {

    private static int totalExports = 0;

    interface Exportable {
        String exportData();
    }

    static class ReportGenerator implements Exportable {

        private String reportName;

        public ReportGenerator(String reportName) {
            this.reportName = reportName;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported report: " + reportName;
        }
    }

    static class UserProfile implements Exportable {

        private String username;

        public UserProfile(String username) {
            this.username = username;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported profile: " + username;
        }
    }

    static int getTotalExports() {
        return totalExports;
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {

        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println(getTotalExports());
    }
}