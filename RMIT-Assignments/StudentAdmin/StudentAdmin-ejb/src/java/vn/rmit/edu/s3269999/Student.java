package vn.rmit.edu.s3269999;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class Student implements EntityBean {

    private EntityContext context;

    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">
    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }

    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
    }

    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }

    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
    }

    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
    }

    // </editor-fold>
    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Collection getCourses();

    public abstract void setCourses(Collection courses);

    public abstract Integer ejbSelectNumberOfCoursesEnrolled();

    public Integer ejbCreate(String name) throws CreateException {
        try {
            Integer nextID = null;

            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/cosc2465");

            Connection c = ds.getConnection();
            PreparedStatement s = c.prepareStatement("SELECT MAX(id) FROM Student");
            ResultSet rs = s.executeQuery();

            if (rs.next()) {
                nextID = new Integer(rs.getInt(1) + 1);
            } else {
                nextID = new Integer(1);
            }

            setId(nextID);
            setName(name);

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }

    public void ejbPostCreate(String name) {
        // TODO populate relationships here if appropriate
    }

    public int getNumberOfCoursesEnrolled() {
        return ejbSelectNumberOfCoursesEnrolled().intValue();
    }
}
