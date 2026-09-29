package abstraction_and_interfaces.assignment_problems;

public class Problem1 {

    static abstract class Shape {

        private static int shapeCounter = 1000;
        private final String shapeId;

        protected Shape() {
            shapeId = "SHAPE-" + (++shapeCounter);
        }

        public String getShapeId() {
            return shapeId;
        }

        public abstract double calculateArea();

        public void scale(double factor) {
            scaleX(factor);
            scaleY(factor);
        }

        public void scale(double xFactor, double yFactor) {
            scaleX(xFactor);
            scaleY(yFactor);
        }

        protected abstract void scaleX(double factor);

        protected abstract void scaleY(double factor);
    }

    static class CircleShape extends Shape {

        private double radius;

        public CircleShape(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        protected void scaleX(double factor) {
            radius *= factor;
        }

        @Override
        protected void scaleY(double factor) {
            radius *= factor;
        }
    }

    static class SquareShape extends Shape {

        private double side;

        public SquareShape(double side) {
            this.side = side;
        }

        @Override
        public double calculateArea() {
            return side * side;
        }

        @Override
        protected void scaleX(double factor) {
            side *= factor;
        }

        @Override
        protected void scaleY(double factor) {
            side *= factor;
        }
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        System.out.println(c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);

        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());
    }
}