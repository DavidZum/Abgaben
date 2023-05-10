package Sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", 6666);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        senden(getString(), dout);
        System.out.println(empfangen(in));
        in.close();
        dout.close();
        s.close();

    }

    public static String empfangen(BufferedReader in) {
        String str = "";
        try {
            String response;
            while ((response = in.readLine()) != null) {
                str += response + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void senden(String output, DataOutputStream dout) {
        try {
            dout.writeUTF(output);
            dout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString() {
        String output = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Senden (1) oder empfangen (2): ");
        int i = Integer.parseInt(scanner.nextLine());
        if (i == 1) {
            System.out.println("Dateiname eingeben: ");
            String filename = scanner.nextLine();
            System.out.println("Text eingeben: ");
            String text = scanner.nextLine();
            scanner.close();
            output = filename + "#" + text;
        } else if (i == 2) {
            output = "LIST";
        } else {
            System.out.println("Falsche Eingabe!");
            output = getString();
        }
        return output;
    }
}
