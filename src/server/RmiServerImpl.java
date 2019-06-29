package server;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerImpl implements RmiServer {

    private static final String MESSAGE = "Hello RMI!";
    private static final String TIMEOUT_MESSAGE = "Timeout!";

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
            registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT); //Porta default do RMI
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
        return MESSAGE;
    }

    @Override
    public String getTimeout(Integer tempo) throws RemoteException, Exception {
        Thread.sleep(tempo);
        return TIMEOUT_MESSAGE;
    }
}
