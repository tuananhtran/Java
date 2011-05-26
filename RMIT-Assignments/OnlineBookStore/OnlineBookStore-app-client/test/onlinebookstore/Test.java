package onlinebookstore;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import junit.framework.TestCase;
import vn.com.onlinebookstore.*;

public class Test extends TestCase {

    //declare book store manager object
    BookStoreManagerRemote bookStoreManager = null;

    public Test(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();

        //get initial context
        InitialContext context = new InitialContext();

        //get book store manager home object
        Object lookup = context.lookup("ejb/BookStoreManager");
        BookStoreManagerRemoteHome bookStoreManagerHome =
                (BookStoreManagerRemoteHome) PortableRemoteObject.narrow(lookup,
                BookStoreManagerRemoteHome.class);

        //create new session
        bookStoreManager = bookStoreManagerHome.create();
    }

    protected void tearDown() throws Exception {
        super.tearDown();

        //kill current session
        bookStoreManager.remove();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testSomeMethod() {
        assertTrue(true);
    }
}
