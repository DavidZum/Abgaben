package Sockets;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int noRequests = 1;

    public static void main(String[] args) {
        try {
            System.out.println("Server erstellen...");
            ServerSocket sock = new ServerSocket(6666);
            while (noRequests <= 1000) {
                System.out.println("Server wartet auf neue Verbindung....");
                Socket s = sock.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                String eingabe = empfangen(in);
                String ausgabe = eingabe(eingabe);
                senden(ausgabe, out);
                out.close();
                in.close();
                s.close();
                noRequests++;
            }
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String empfangen(DataInputStream in) {
        String response = "";

        try {
            response = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void senden(String output, PrintWriter out) {
        try {
            out.println(output);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String eingabe(String eingabe) throws IOException {
        if (eingabe.equals("LIST")) {
            return ServerFiles.listFiles(ServerFiles.getDir());
        } else {
            String[] strings = eingabe.split("#");
            File file = ServerFiles.createFile(strings[0]);
            ServerFiles.writeFile(strings[1], file);
            return ServerFiles.getFileGroesse(file);
        }
    }
}