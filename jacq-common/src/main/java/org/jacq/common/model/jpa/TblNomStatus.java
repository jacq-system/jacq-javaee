/*
 * Copyright 2017 wkoller.
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "mig_nom_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNomStatus.findAll", query = "SELECT t FROM TblNomStatus t")
    , @NamedQuery(name = "TblNomStatus.findByStatusId", query = "SELECT t FROM TblNomStatus t WHERE t.statusId = :statusId")
    , @NamedQuery(name = "TblNomStatus.findByStatus", query = "SELECT t FROM TblNomStatus t WHERE t.status = :status")
    , @NamedQuery(name = "TblNomStatus.findByStatusSp2000", query = "SELECT t FROM TblNomStatus t WHERE t.statusSp2000 = :statusSp2000")})
public class TblNomStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Long statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "status_sp2000")
    private String statusSp2000;
    @ManyToMany(mappedBy = "tblNomStatusList")
    private List<TblNomName> tblNomNameList;

    public TblNomStatus() {
    }

    public TblNomStatus(Long statusId) {
        this.statusId = statusId;
    }

    public TblNomStatus(Long statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusSp2000() {
        return statusSp2000;
    }

    public void setStatusSp2000(String statusSp2000) {
        this.statusSp2000 = statusSp2000;
    }

    @XmlTransient
    public List<TblNomName> getTblNomNameList() {
        return tblNomNameList;
    }

    public void setTblNomNameList(List<TblNomName> tblNomNameList) {
        this.tblNomNameList = tblNomNameList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNomStatus)) {
            return false;
        }
        TblNomStatus other = (TblNomStatus) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblNomStatus[ statusId=" + statusId + " ]";
    }

}
