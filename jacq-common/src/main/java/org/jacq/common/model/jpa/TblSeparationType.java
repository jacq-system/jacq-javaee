/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
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
 * @author wkoller
 */
@Entity
@Table(name = "tbl_separation_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSeparationType.findAll", query = "SELECT t FROM TblSeparationType t"),
    @NamedQuery(name = "TblSeparationType.findById", query = "SELECT t FROM TblSeparationType t WHERE t.id = :id"),
    @NamedQuery(name = "TblSeparationType.findByType", query = "SELECT t FROM TblSeparationType t WHERE t.type = :type")})
public class TblSeparationType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 25)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "separationTypeId")
    private Collection<TblSeparation> tblSeparationCollection;

    public TblSeparationType() {
    }

    public TblSeparationType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<TblSeparation> getTblSeparationCollection() {
        return tblSeparationCollection;
    }

    public void setTblSeparationCollection(Collection<TblSeparation> tblSeparationCollection) {
        this.tblSeparationCollection = tblSeparationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSeparationType)) {
            return false;
        }
        TblSeparationType other = (TblSeparationType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSeparationType[ id=" + id + " ]";
    }

}