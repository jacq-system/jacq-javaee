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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_source")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionSource.findAll", query = "SELECT t FROM TblAcquisitionSource t")
    , @NamedQuery(name = "TblAcquisitionSource.findByAcquisitionSourceId", query = "SELECT t FROM TblAcquisitionSource t WHERE t.acquisitionSourceId = :acquisitionSourceId")
    , @NamedQuery(name = "TblAcquisitionSource.findByName", query = "SELECT t FROM TblAcquisitionSource t WHERE t.name = :name")
    , @NamedQuery(name = "TblAcquisitionSource.findLikeName", query = "SELECT t FROM TblAcquisitionSource t WHERE t.name LIKE :name")})
public class TblAcquisitionSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acquisition_source_id")
    private Long acquisitionSourceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionSourceId", fetch = FetchType.LAZY)
    private List<TblAcquisitionEventSource> tblAcquisitionEventSourceList;

    public TblAcquisitionSource() {
    }

    public TblAcquisitionSource(Long acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    public TblAcquisitionSource(Long acquisitionSourceId, String name) {
        this.acquisitionSourceId = acquisitionSourceId;
        this.name = name;
    }

    public Long getAcquisitionSourceId() {
        return acquisitionSourceId;
    }

    public void setAcquisitionSourceId(Long acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<TblAcquisitionEventSource> getTblAcquisitionEventSourceList() {
        return tblAcquisitionEventSourceList;
    }

    public void setTblAcquisitionEventSourceList(List<TblAcquisitionEventSource> tblAcquisitionEventSourceList) {
        this.tblAcquisitionEventSourceList = tblAcquisitionEventSourceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acquisitionSourceId != null ? acquisitionSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcquisitionSource)) {
            return false;
        }
        TblAcquisitionSource other = (TblAcquisitionSource) object;
        if ((this.acquisitionSourceId == null && other.acquisitionSourceId != null) || (this.acquisitionSourceId != null && !this.acquisitionSourceId.equals(other.acquisitionSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionSource[ acquisitionSourceId=" + acquisitionSourceId + " ]";
    }

}
