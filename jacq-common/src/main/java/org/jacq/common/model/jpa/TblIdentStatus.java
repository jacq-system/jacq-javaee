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
import java.util.List;
import jakarta.persistence.Basic;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_ident_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIdentStatus.findAll", query = "SELECT t FROM TblIdentStatus t")
    , @NamedQuery(name = "TblIdentStatus.findByIdentStatusId", query = "SELECT t FROM TblIdentStatus t WHERE t.identStatusId = :identStatusId")
    , @NamedQuery(name = "TblIdentStatus.findByStatus", query = "SELECT t FROM TblIdentStatus t WHERE t.status = :status")})
public class TblIdentStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ident_status_id")
    private Long identStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "identStatusId", fetch = FetchType.LAZY)
    private List<TblBotanicalObject> tblBotanicalObjectList;

    public TblIdentStatus() {
    }

    public TblIdentStatus(Long identStatusId) {
        this.identStatusId = identStatusId;
    }

    public TblIdentStatus(Long identStatusId, String status) {
        this.identStatusId = identStatusId;
        this.status = status;
    }

    public Long getIdentStatusId() {
        return identStatusId;
    }

    public void setIdentStatusId(Long identStatusId) {
        this.identStatusId = identStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<TblBotanicalObject> getTblBotanicalObjectList() {
        return tblBotanicalObjectList;
    }

    public void setTblBotanicalObjectList(List<TblBotanicalObject> tblBotanicalObjectList) {
        this.tblBotanicalObjectList = tblBotanicalObjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identStatusId != null ? identStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIdentStatus)) {
            return false;
        }
        TblIdentStatus other = (TblIdentStatus) object;
        if ((this.identStatusId == null && other.identStatusId != null) || (this.identStatusId != null && !this.identStatusId.equals(other.identStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblIdentStatus[ identStatusId=" + identStatusId + " ]";
    }

}
