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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_inventory_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventoryType.findAll", query = "SELECT t FROM TblInventoryType t"),
    @NamedQuery(name = "TblInventoryType.findByInventoryTypeId", query = "SELECT t FROM TblInventoryType t WHERE t.inventoryTypeId = :inventoryTypeId"),
    @NamedQuery(name = "TblInventoryType.findByType", query = "SELECT t FROM TblInventoryType t WHERE t.type = :type"),
    @NamedQuery(name = "TblInventoryType.findByTimestamp", query = "SELECT t FROM TblInventoryType t WHERE t.timestamp = :timestamp")})
public class TblInventoryType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_type_id")
    private Integer inventoryTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryTypeId")
    private Collection<TblInventory> tblInventoryCollection;

    public TblInventoryType() {
    }

    public TblInventoryType(Integer inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public TblInventoryType(Integer inventoryTypeId, String type, Date timestamp) {
        this.inventoryTypeId = inventoryTypeId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Integer getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(Integer inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public Collection<TblInventory> getTblInventoryCollection() {
        return tblInventoryCollection;
    }

    public void setTblInventoryCollection(Collection<TblInventory> tblInventoryCollection) {
        this.tblInventoryCollection = tblInventoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryTypeId != null ? inventoryTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblInventoryType)) {
            return false;
        }
        TblInventoryType other = (TblInventoryType) object;
        if ((this.inventoryTypeId == null && other.inventoryTypeId != null) || (this.inventoryTypeId != null && !this.inventoryTypeId.equals(other.inventoryTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblInventoryType[ inventoryTypeId=" + inventoryTypeId + " ]";
    }

}