/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_curatorial_unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCuratorialUnit.findAll", query = "SELECT t FROM TblCuratorialUnit t"),
    @NamedQuery(name = "TblCuratorialUnit.findByCuratorialUnitId", query = "SELECT t FROM TblCuratorialUnit t WHERE t.curatorialUnitId = :curatorialUnitId"),
    @NamedQuery(name = "TblCuratorialUnit.findByTimestamp", query = "SELECT t FROM TblCuratorialUnit t WHERE t.timestamp = :timestamp")})
public class TblCuratorialUnit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "curatorial_unit_id")
    private Integer curatorialUnitId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinTable(name = "tbl_curatorial_unit_identifier", joinColumns = {
        @JoinColumn(name = "curatorial_unit_id", referencedColumnName = "curatorial_unit_id")}, inverseJoinColumns = {
        @JoinColumn(name = "identifier_id", referencedColumnName = "identifier_id")})
    @ManyToMany
    private Collection<TblIdentifier> tblIdentifierCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curatorialUnitId")
    private Collection<TblSpecimen> tblSpecimenCollection;
    @JoinColumn(name = "curatorial_unit_type_id", referencedColumnName = "curatorial_unit_type_id")
    @ManyToOne(optional = false)
    private TblCuratorialUnitType curatorialUnitTypeId;

    public TblCuratorialUnit() {
    }

    public TblCuratorialUnit(Integer curatorialUnitId) {
        this.curatorialUnitId = curatorialUnitId;
    }

    public TblCuratorialUnit(Integer curatorialUnitId, Date timestamp) {
        this.curatorialUnitId = curatorialUnitId;
        this.timestamp = timestamp;
    }

    public Integer getCuratorialUnitId() {
        return curatorialUnitId;
    }

    public void setCuratorialUnitId(Integer curatorialUnitId) {
        this.curatorialUnitId = curatorialUnitId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblIdentifier> getTblIdentifierCollection() {
        return tblIdentifierCollection;
    }

    public void setTblIdentifierCollection(Collection<TblIdentifier> tblIdentifierCollection) {
        this.tblIdentifierCollection = tblIdentifierCollection;
    }

    @XmlTransient
    public Collection<TblSpecimen> getTblSpecimenCollection() {
        return tblSpecimenCollection;
    }

    public void setTblSpecimenCollection(Collection<TblSpecimen> tblSpecimenCollection) {
        this.tblSpecimenCollection = tblSpecimenCollection;
    }

    public TblCuratorialUnitType getCuratorialUnitTypeId() {
        return curatorialUnitTypeId;
    }

    public void setCuratorialUnitTypeId(TblCuratorialUnitType curatorialUnitTypeId) {
        this.curatorialUnitTypeId = curatorialUnitTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curatorialUnitId != null ? curatorialUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCuratorialUnit)) {
            return false;
        }
        TblCuratorialUnit other = (TblCuratorialUnit) object;
        if ((this.curatorialUnitId == null && other.curatorialUnitId != null) || (this.curatorialUnitId != null && !this.curatorialUnitId.equals(other.curatorialUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblCuratorialUnit[ curatorialUnitId=" + curatorialUnitId + " ]";
    }

}