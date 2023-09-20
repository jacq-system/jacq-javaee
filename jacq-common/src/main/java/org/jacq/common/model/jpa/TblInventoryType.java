/*
 * Copyright 2019 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.common.model.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_inventory_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblInventoryType.findAll", query = "SELECT t FROM TblInventoryType t")
    , @NamedQuery(name = "TblInventoryType.findByInventoryTypeId", query = "SELECT t FROM TblInventoryType t WHERE t.inventoryTypeId = :inventoryTypeId")
    , @NamedQuery(name = "TblInventoryType.findByType", query = "SELECT t FROM TblInventoryType t WHERE t.type = :type")
    , @NamedQuery(name = "TblInventoryType.findByTimestamp", query = "SELECT t FROM TblInventoryType t WHERE t.timestamp = :timestamp")})
public class TblInventoryType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_type_id")
    private Long inventoryTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Column(name = "timestamp", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventoryTypeId", fetch = FetchType.LAZY)
    private List<TblInventory> tblInventoryList;

    public TblInventoryType() {
    }

    public TblInventoryType(Long inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public TblInventoryType(Long inventoryTypeId, String type) {
        this.inventoryTypeId = inventoryTypeId;
        this.type = type;
    }

    public Long getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(Long inventoryTypeId) {
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
    public List<TblInventory> getTblInventoryList() {
        return tblInventoryList;
    }

    public void setTblInventoryList(List<TblInventory> tblInventoryList) {
        this.tblInventoryList = tblInventoryList;
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
