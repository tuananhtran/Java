package onlinebookstore.utils;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import vn.com.onlinebookstore.*;

public class Utils {

    //create new session
    public static BookStoreManagerRemote getSessionControl() throws Exception {

        //lookup remote home object
        InitialContext ic = new InitialContext();
        Object lookup = ic.lookup("ejb/BookStoreManager");
        BookStoreManagerRemoteHome home =
                (BookStoreManagerRemoteHome) PortableRemoteObject.narrow(
                lookup, BookStoreManagerRemoteHome.class);

        //create and return session control
        return home.create();
    }
}
