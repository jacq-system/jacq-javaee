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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_acquisition_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAcquisitionDate.findAll", query = "SELECT t FROM TblAcquisitionDate t")
    , @NamedQuery(name = "TblAcquisitionDate.findById", query = "SELECT t FROM TblAcquisitionDate t WHERE t.id = :id")
    , @NamedQuery(name = "TblAcquisitionDate.findByYear", query = "SELECT t FROM TblAcquisitionDate t WHERE t.year = :year")
    , @NamedQuery(name = "TblAcquisitionDate.findByMonth", query = "SELECT t FROM TblAcquisitionDate t WHERE t.month = :month")
    , @NamedQuery(name = "TblAcquisitionDate.findByDay", query = "SELECT t FROM TblAcquisitionDate t WHERE t.day = :day")
    , @NamedQuery(name = "TblAcquisitionDate.findByCustom", query = "SELECT t FROM TblAcquisitionDate t WHERE t.custom = :custom")})
public class TblAcquisitionDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 4)
    @Column(name = "year")
    private String year;
    @Size(max = 2)
    @Column(name = "month")
    private String month;
    @Size(max = 2)
    @Column(name = "day")
    private String day;
    @Size(max = 20)
    @Column(name = "custom")
    private String custom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acquisitionDateId", fetch = FetchType.LAZY)
    private List<TblAcquisitionEvent> tblAcquisitionEventList;
    @OneToMany(mappedBy = "incomingDateId", fetch = FetchType.LAZY)
    private List<TblLivingPlant> tblLivingPlantList;

    public TblAcquisitionDate() {
    }

    public TblAcquisitionDate(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    @XmlTransient
    public List<TblAcquisitionEvent> getTblAcquisitionEventList() {
        return tblAcquisitionEventList;
    }

    public void setTblAcquisitionEventList(List<TblAcquisitionEvent> tblAcquisitionEventList) {
        this.tblAcquisitionEventList = tblAcquisitionEventList;
    }

    @XmlTransient
    public List<TblLivingPlant> getTblLivingPlantList() {
        return tblLivingPlantList;
    }

    public void setTblLivingPlantList(List<TblLivingPlant> tblLivingPlantList) {
        this.tblLivingPlantList = tblLivingPlantList;
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
        if (!(object instanceof TblAcquisitionDate)) {
            return false;
        }
        TblAcquisitionDate other = (TblAcquisitionDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblAcquisitionDate[ id=" + id + " ]";
    }

}
