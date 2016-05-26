package com.chat.socnet;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by Каролина on 26.05.2016.
 */
public class Srv {

        static BufferedReader in = null;
        static PrintWriter    out= null;

        static ServerSocket servers = null;
        static Socket  fromclient = null;
        static Integer port = 4561;
        public static void main(String[] args){
            System.out.println("Добро пожаловать на сервер");
            try {
                servers = new ServerSocket(port,10);
            } catch (IOException e) {
                System.out.println("Невозможно подключиться к порту "+port);
                System.exit(-1);
            }
            try {
                System.out.print("Ожидание клиента...");
                fromclient= servers.accept();
                System.out.println("Клиент присоеденился");
            } catch (IOException e) {
                System.out.println("Клиент не може присоедениться");
                System.exit(-1);
            }

            try {
                in  = new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
                out = new PrintWriter(fromclient.getOutputStream(),true);
                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("Клиент прислал "+input);
                    out.println("Обработанное сообщение от сервера: "+input);
                }
                out.close();
                in.close();
                fromclient.close();
                servers.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("You got an IOException ");
                e.printStackTrace();
            }




        }
}
