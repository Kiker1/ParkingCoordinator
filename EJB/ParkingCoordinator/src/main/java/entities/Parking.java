/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kiker
 */
@Entity
@Table(name = "parking")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Parking.findAll", query = "SELECT p FROM Parking p"),
    @NamedQuery(name = "Parking.findByIdparking", query = "SELECT p FROM Parking p WHERE p.idparking = :idparking"),
    @NamedQuery(name = "Parking.findByFreePlaces", query = "SELECT p FROM Parking p WHERE p.freePlaces = :freePlaces"),
    @NamedQuery(name = "Parking.findByAddress", query = "SELECT p FROM Parking p WHERE p.address = :address"),
    @NamedQuery(name = "Parking.findByComments", query = "SELECT p FROM Parking p WHERE p.comments = :comments")
})
public class Parking implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idparking")
    private Integer idparking;
    @Column(name = "free_places")
    private Integer freePlaces;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Size(max = 45)
    @Column(name = "comments")
    private String comments;

    public Parking()
    {
    }

    public Parking(Integer idparking)
    {
        this.idparking = idparking;
    }

    public Integer getIdparking()
    {
        return idparking;
    }

    public void setIdparking(Integer idparking)
    {
        this.idparking = idparking;
    }

    public Integer getFreePlaces()
    {
        return freePlaces;
    }

    public void setFreePlaces(Integer freePlaces)
    {
        this.freePlaces = freePlaces;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idparking != null ? idparking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parking))
        {
            return false;
        }
        Parking other = (Parking) object;
        if ((this.idparking == null && other.idparking != null) || (this.idparking != null && !this.idparking.equals(other.idparking)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("FreePlaces = [%d] Address = [%s]",
                freePlaces,
                address);
    }
    
}
