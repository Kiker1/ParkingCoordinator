/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kiker
 */
@Entity
@Table(name = "passengerRequest")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PassengerRequest.findAll", query = "SELECT p FROM PassengerRequest p"),
    @NamedQuery(name = "PassengerRequest.findByIdpassengerRequest", query = "SELECT p FROM PassengerRequest p WHERE p.idpassengerRequest = :idpassengerRequest"),
    @NamedQuery(name = "PassengerRequest.findByCreationDate", query = "SELECT p FROM PassengerRequest p WHERE p.creationDate = :creationDate"),
    @NamedQuery(name = "PassengerRequest.findByUser", query = "SELECT p FROM PassengerRequest p WHERE p.userId = :userId")
})
public class PassengerRequest implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpassengerRequest")
    private Integer idpassengerRequest;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "driverRequest_id", referencedColumnName = "iddriverRequest")
    @ManyToOne(optional = false)
    private DriverRequest driverRequestid;

    public PassengerRequest()
    {
    }

    public PassengerRequest(Integer idpassengerRequest)
    {
        this.idpassengerRequest = idpassengerRequest;
    }

    public Integer getIdpassengerRequest()
    {
        return idpassengerRequest;
    }

    public void setIdpassengerRequest(Integer idpassengerRequest)
    {
        this.idpassengerRequest = idpassengerRequest;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }

    public DriverRequest getDriverRequestid()
    {
        return driverRequestid;
    }

    public void setDriverRequestid(DriverRequest driverRequestid)
    {
        this.driverRequestid = driverRequestid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idpassengerRequest != null ? idpassengerRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassengerRequest))
        {
            return false;
        }
        PassengerRequest other = (PassengerRequest) object;
        if ((this.idpassengerRequest == null && other.idpassengerRequest != null) || (this.idpassengerRequest != null && !this.idpassengerRequest.equals(other.idpassengerRequest)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.PassengerRequest[ idpassengerRequest=" + idpassengerRequest + " ]";
    }
    
}
