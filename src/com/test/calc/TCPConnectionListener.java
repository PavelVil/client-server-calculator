package com.test.calc;

/**
 * Event handling in connection.
 * */
public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection tcpConnection);

    void onReceiveString(TCPConnection tcpConnection, String value);

    void onException(TCPConnection connection, Exception ex);

}
