public class Test {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Test <name>");
            return;
        }
        String name = args[0];
        System.out.println("Hello " + name);
    }
}