package server;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerImpl implements RmiServer {

    public static final String MESSAGE = "Hello RMI!";

    public RmiServerImpl() throws RemoteException {
    }

    public static void main(String[] args) {

        RmiServer server = null;

        try {
            server = new RmiServerImpl();
            System.out.println("Servidor iniciado....");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Registry registry;
        RmiServer stub = null;
        try {
            stub = (RmiServer) UnicastRemoteObject.exportObject(server, 0);
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("RmiServer", stub);
            System.out.println("Registry iniciado.....");

        } catch (RemoteException e) {
            System.out.println(e.getMessage());

        } catch (AlreadyBoundException e) {
            try {
                registry = LocateRegistry.getRegistry();
                registry.rebind("RmiServer", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getMessage() throws RemoteException, Exception {

        Thread.sleep(6000);
        return MESSAGE;
    }
}
