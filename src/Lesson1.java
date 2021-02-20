public class Lesson1 {

    public static void main(String[] args) {
        System.out.println("Hello World");

        args = new String[2];
        args[0] = "Hello";
        args[1] = "World";
        HelloWorldLib.printArray(args);
    }
}
