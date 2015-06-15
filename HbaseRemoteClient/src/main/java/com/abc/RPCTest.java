package com.abc;

import org.apache.hadoop.ipc.*;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/28/14
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RPCTest {
//    public static void main(String[] args) {
//        final RPC.Server server = new RPC.Builder(new Configuration()).
//                setInstance(new com.abc.PingRPCImpl()).
//                setProtocol(PingProtocol.class).
//                build();
//        server.start();
//    }
}
@ProtocolInfo(protocolName = "ping", protocolVersion = 1)
interface PingRPC {
    String ping(String msg);
}

class PingRPCImpl implements PingRPC {
    public String ping(String msg) { return "pong=" + msg; }
}