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
import java.time.LocalDate;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_event_source")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionEventSource.findAll", query = "SELECT t FROM TblAcquisitionEventSource t")
    , @NamedQuery(name = "TblAcquisitionEventSource.findByAcquisitionEventSourceId", query = "SELECT t FROM TblAcquisitionEventSource t WHERE t.acquisitionEventSourceId = :acquisitionEventSourceId")
    , @NamedQuery(name = "TblAcquisitionEventSource.findBySourceDate", query = "SELECT t FROM TblAcquisitionEventSource t WHERE t.sourceDate = :sourceDate")})
public class TblAcquisitionEventSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acquisition_event_source_id")
    private Long acquisitionEventSourceId;
    @Column(name = "source_date")
    private LocalDate sourceDate;
    @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblAcquisitionEvent acquisitionEventId;
    @JoinColumn(name = "acquisition_source_id", referencedColumnName = "acquisition_source_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblAcquisitionSource acquisitionSourceId;

    public TblAcquisitionEventSource() {
    }

    public TblAcquisitionEventSource(Long acquisitionEventSourceId) {
        this.acquisitionEventSourceId = acquisitionEventSourceId;
    }

    public Long getAcquisitionEventSourceId() {
        return acquisitionEventSourceId;
    }

    public void setAcquisitionEventSourceId(Long acquisitionEventSourceId) {
        this.acquisitionEventSourceId = acquisitionEventSourceId;
    }

    public LocalDate getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(LocalDate sourceDate) {
        this.sourceDate = sourceDate;
    }

    public TblAcquisitionEvent getAcquisitionEventId() {
        return acquisitionEventId;
    }

    public void setAcquisitionEventId(TblAcquisitionEvent acquisitionEventId) {
        this.acquisitionEventId = acquisitionEventId;
    }

    public TblAcquisitionSource getAcquisitionSourceId() {
        return acquisitionSourceId;
    }

    public void setAcquisitionSourceId(TblAcquisitionSource acquisitionSourceId) {
        this.acquisitionSourceId = acquisitionSourceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acquisitionEventSourceId != null ? acquisitionEventSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAcquisitionEventSource)) {
            return false;
        }
        TblAcquisitionEventSource other = (TblAcquisitionEventSource) object;
        if ((this.acquisitionEventSourceId == null && other.acquisitionEventSourceId != null) || (this.acquisitionEventSourceId != null && !this.acquisitionEventSourceId.equals(other.acquisitionEventSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionEventSource[ acquisitionEventSourceId=" + acquisitionEventSourceId + " ]";
    }

}
