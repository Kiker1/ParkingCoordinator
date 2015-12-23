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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kiker
 */
@Entity
@Table(name = "admin")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findByIdadmin", query = "SELECT a FROM Admin a WHERE a.idadmin = :idadmin")
})
public class Admin implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadmin")
    private Integer idadmin;
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userId;

    public Admin()
    {
    }

    public Admin(Integer idadmin)
    {
        this.idadmin = idadmin;
    }

    public Integer getIdadmin()
    {
        return idadmin;
    }

    public void setIdadmin(Integer idadmin)
    {
        this.idadmin = idadmin;
    }

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idadmin != null ? idadmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin))
        {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.idadmin == null && other.idadmin != null) || (this.idadmin != null && !this.idadmin.equals(other.idadmin)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Admin[ idadmin=" + idadmin + " ]";
    }
    
}
