package com.test.calc;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements TCPConnectionListener {

    private final static int PORT = 8189;
    private final static String IP_ADDRESS = "localhost";


    public static void main(String[] args) {
        String address = args.length <= 0 ? IP_ADDRESS : args[0];
        int port = args.length <= 0 ? PORT : Integer.parseInt(args[1]);
        new Server(address,port);
    }

    private TCPConnection tcpConnection;

    public Server(String address, int port) {
        System.out.println("Server runner");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    tcpConnection = new TCPConnection(this, serverSocket.accept());
                } catch (IOException e){
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }

    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        tcpConnection.sendString("Client connected: "+ tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        tcpConnection.sendString(value);
    }

    @Override
    public synchronized void onException(TCPConnection connection, Exception ex) {
        System.out.println("TCPConnection exception: " + ex);
    }

}
