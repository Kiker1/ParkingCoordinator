/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kiker
 */
@Entity
@Table(name = "driverRequest")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "DriverRequest.findAll", query = "SELECT d FROM DriverRequest d"),
    @NamedQuery(name = "DriverRequest.findByIddriverRequest", query = "SELECT d FROM DriverRequest d WHERE d.iddriverRequest = :iddriverRequest"),
    @NamedQuery(name = "DriverRequest.findByCreationDate", query = "SELECT d FROM DriverRequest d WHERE d.creationDate = :creationDate"),
    @NamedQuery(name = "DriverRequest.findByEnterDate", query = "SELECT d FROM DriverRequest d WHERE d.enterDate = :enterDate"),
    @NamedQuery(name = "DriverRequest.findByLeaveDate", query = "SELECT d FROM DriverRequest d WHERE d.leaveDate = :leaveDate"),
    @NamedQuery(name = "DriverRequest.findByFreePlacesLeft", query = "SELECT d FROM DriverRequest d WHERE d.freePlacesLeft = :freePlacesLeft"),
    @NamedQuery(name = "DriverRequest.findByStatus", query = "SELECT d FROM DriverRequest d WHERE d.status = :status"),
    @NamedQuery(name = "DriverRequest.findByUser", query = "SELECT d FROM DriverRequest d WHERE d.userId = :userId"),
    @NamedQuery(name = "DriverRequest.findAllValid", query = "SELECT d FROM DriverRequest d WHERE d.freePlacesLeft > 0 and d.status LIKE 'OPEN' and d.userId != :userId")
})
public class DriverRequest implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddriverRequest")
    private Integer iddriverRequest;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "enter_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enterDate;
    @Column(name = "leave_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;
    @Column(name = "free_places_left")
    private Integer freePlacesLeft;
    @Size(max = 5)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driverRequestid")
    private List<PassengerRequest> passengerRequestList;
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "parking_id", referencedColumnName = "idparking")
    @ManyToOne(optional = false)
    private Parking parkingId;

    public DriverRequest()
    {
    }

    public DriverRequest(Integer iddriverRequest)
    {
        this.iddriverRequest = iddriverRequest;
    }

    public Integer getIddriverRequest()
    {
        return iddriverRequest;
    }

    public void setIddriverRequest(Integer iddriverRequest)
    {
        this.iddriverRequest = iddriverRequest;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getEnterDate()
    {
        return enterDate;
    }

    public void setEnterDate(Date enterDate)
    {
        this.enterDate = enterDate;
    }

    public Date getLeaveDate()
    {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    public Integer getFreePlacesLeft()
    {
        return freePlacesLeft;
    }

    public void setFreePlacesLeft(Integer freePlacesLeft)
    {
        this.freePlacesLeft = freePlacesLeft;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }

    public Parking getParkingId()
    {
        return parkingId;
    }

    public void setParkingId(Parking parkingId)
    {
        this.parkingId = parkingId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (iddriverRequest != null ? iddriverRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DriverRequest))
        {
            return false;
        }
        DriverRequest other = (DriverRequest) object;
        if ((this.iddriverRequest == null && other.iddriverRequest != null) || (this.iddriverRequest != null && !this.iddriverRequest.equals(other.iddriverRequest)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
//        return "entities.DriverRequest[ iddriverRequest=" + iddriverRequest + " ]";
        
        DateFormat df = new SimpleDateFormat();
        return String.format("ID = [%d] FreePlaces = [%d] EnterDate [%s] LeaveDate [%s] ParkingId [%d]",
                iddriverRequest,
                freePlacesLeft,
                enterDate != null ? df.format(enterDate) : "",
                leaveDate != null ? df.format(leaveDate) : "",
                parkingId.getIdparking());
    }
    
}
