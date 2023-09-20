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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionEvent.findAll", query = "SELECT t FROM TblAcquisitionEvent t")
    , @NamedQuery(name = "TblAcquisitionEvent.findById", query = "SELECT t FROM TblAcquisitionEvent t WHERE t.id = :id")})
public class TblAcquisitionEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Lob
    @Size(max = 65535)
    @Column(name = "number")
    private String number;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @JoinTable(name = "tbl_acquisition_event_person", joinColumns = {
        @JoinColumn(name = "acquisition_event_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "person_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TblPerson> tblPersonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionEventId", fetch = FetchType.LAZY)
    private List<TblBotanicalObject> tblBotanicalObjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionEventId", fetch = FetchType.LAZY)
    private List<TblAcquisitionEventSource> tblAcquisitionEventSourceList;
    @JoinColumn(name = "acquisition_date_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblAcquisitionDate acquisitionDateId;
    @JoinColumn(name = "acquisition_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblAcquisitionType acquisitionTypeId;
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblLocation locationId;
    @JoinColumn(name = "location_coordinates_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblLocationCoordinates locationCoordinatesId;

    public TblAcquisitionEvent() {
    }

    public TblAcquisitionEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @XmlTransient
    public List<TblPerson> getTblPersonList() {
        return tblPersonList;
    }

    public void setTblPersonList(List<TblPerson> tblPersonList) {
        this.tblPersonList = tblPersonList;
    }

    @XmlTransient
    public List<TblBotanicalObject> getTblBotanicalObjectList() {
        return tblBotanicalObjectList;
    }

    public void setTblBotanicalObjectList(List<TblBotanicalObject> tblBotanicalObjectList) {
        this.tblBotanicalObjectList = tblBotanicalObjectList;
    }

    @XmlTransient
    public List<TblAcquisitionEventSource> getTblAcquisitionEventSourceList() {
        return tblAcquisitionEventSourceList;
    }

    public void setTblAcquisitionEventSourceList(List<TblAcquisitionEventSource> tblAcquisitionEventSourceList) {
        this.tblAcquisitionEventSourceList = tblAcquisitionEventSourceList;
    }

    public TblAcquisitionDate getAcquisitionDateId() {
        return acquisitionDateId;
    }

    public void setAcquisitionDateId(TblAcquisitionDate acquisitionDateId) {
        this.acquisitionDateId = acquisitionDateId;
    }

    public TblAcquisitionType getAcquisitionTypeId() {
        return acquisitionTypeId;
    }

    public void setAcquisitionTypeId(TblAcquisitionType acquisitionTypeId) {
        this.acquisitionTypeId = acquisitionTypeId;
    }

    public TblLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(TblLocation locationId) {
        this.locationId = locationId;
    }

    public TblLocationCoordinates getLocationCoordinatesId() {
        return locationCoordinatesId;
    }

    public void setLocationCoordinatesId(TblLocationCoordinates locationCoordinatesId) {
        this.locationCoordinatesId = locationCoordinatesId;
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
        if (!(object instanceof TblAcquisitionEvent)) {
            return false;
        }
        TblAcquisitionEvent other = (TblAcquisitionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionEvent[ id=" + id + " ]";
    }

}
