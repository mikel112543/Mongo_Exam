import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        jsonread Json = new jsonread();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter file path of json Object");
        String jsonString = input.next();
        Json.serialize(jsonString);
    }
}
