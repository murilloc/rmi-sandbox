package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServer extends Remote {

    String getMessage() throws RemoteException, Exception;
}
