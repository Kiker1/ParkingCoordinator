/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiker
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIduser", query = "SELECT u FROM User u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.name = :name and u.password = :password")
})
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<PassengerRequest> passengerRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Admin> adminList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<DriverRequest> driverRequestList;

    public User()
    {
    }

    public User(Integer iduser)
    {
        this.iduser = iduser;
    }

    public Integer getIduser()
    {
        return iduser;
    }

    public void setIduser(Integer iduser)
    {
        this.iduser = iduser;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @XmlTransient
    public List<PassengerRequest> getPassengerRequestList()
    {
        return passengerRequestList;
    }

    public void setPassengerRequestList(List<PassengerRequest> passengerRequestList)
    {
        this.passengerRequestList = passengerRequestList;
    }

    @XmlTransient
    public List<Admin> getAdminList()
    {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList)
    {
        this.adminList = adminList;
    }

    @XmlTransient
    public List<DriverRequest> getDriverRequestList()
    {
        return driverRequestList;
    }

    public void setDriverRequestList(List<DriverRequest> driverRequestList)
    {
        this.driverRequestList = driverRequestList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.User[ iduser=" + iduser + " ]";
    }
    
}
