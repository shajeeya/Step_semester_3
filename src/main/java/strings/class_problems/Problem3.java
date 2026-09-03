package strings.class_problems;

public class Problem3 {

    static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        String filename = "Assignment1.PDF";

        System.out.println(validateFileExtension(filename));
    }
}