package com.test.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Client extends JFrame implements ActionListener, TCPConnectionListener {

    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8189;
    private static int WIDTH = 600;
    private static int HEIGHT = 400;

    private TCPConnection connection;
    private int port;
    private String host;

    public static void main(String[] args) {
        String address = args.length <= 0 ? IP_ADDRESS : args[0];
        int port = args.length <= 0 ? PORT : Integer.parseInt(args[1]);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client(address, port);
            }
        });
    }

    private final JTextArea log = new JTextArea();
    private final JTextField fieldInput = new JTextField();

    public Client(String host, int port) {

        this.port = port;
        this.host = host;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        log.setEditable(false);
        log.setLineWrap(true);
        fieldInput.addActionListener(this);

        add(log, BorderLayout.CENTER);
        add(fieldInput, BorderLayout.SOUTH);
        add(new JScrollPane(log), BorderLayout.CENTER);

        setVisible(true);

        try {
            connection = new TCPConnection(this, host, port);
        } catch (IOException e) {
            printMessage("Connection exception: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals("")) return;
        fieldInput.setText("");
        connection.sendString(msg + " = " + Calculator.calculate(msg));
    }


    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready! Address: " + host + ", Port: " + port);
        printMessage("Please enter a expression in style like this: 18*(20-12)+40/2*(2+6)");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMessage(value);
    }

    @Override
    public void onException(TCPConnection connection, Exception ex) {
        printMessage("Connection exception: " + ex);
    }

    private synchronized void printMessage(String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + "\r\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
