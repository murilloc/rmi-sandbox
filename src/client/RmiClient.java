package client;

import server.RmiServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {

    public static void main(String[] args) {
        String host;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            RmiServer rmiServer = (RmiServer) registry.lookup("RmiServer");

            System.out.println(rmiServer.getTimeout(4000));

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
