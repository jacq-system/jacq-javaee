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
@Table(name = "tbl_phenology")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPhenology.findAll", query = "SELECT t FROM TblPhenology t")
    , @NamedQuery(name = "TblPhenology.findById", query = "SELECT t FROM TblPhenology t WHERE t.id = :id")
    , @NamedQuery(name = "TblPhenology.findByPhenology", query = "SELECT t FROM TblPhenology t WHERE t.phenology = :phenology")})
public class TblPhenology implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "phenology")
    private String phenology;
    @OneToMany(mappedBy = "phenologyId", fetch = FetchType.LAZY)
    private List<TblBotanicalObject> tblBotanicalObjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phenologyId", fetch = FetchType.LAZY)
    private List<TblVegetative> tblVegetativeList;

    public TblPhenology() {
    }

    public TblPhenology(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhenology() {
        return phenology;
    }

    public void setPhenology(String phenology) {
        this.phenology = phenology;
    }

    @XmlTransient
    public List<TblBotanicalObject> getTblBotanicalObjectList() {
        return tblBotanicalObjectList;
    }

    public void setTblBotanicalObjectList(List<TblBotanicalObject> tblBotanicalObjectList) {
        this.tblBotanicalObjectList = tblBotanicalObjectList;
    }

    @XmlTransient
    public List<TblVegetative> getTblVegetativeList() {
        return tblVegetativeList;
    }

    public void setTblVegetativeList(List<TblVegetative> tblVegetativeList) {
        this.tblVegetativeList = tblVegetativeList;
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
        if (!(object instanceof TblPhenology)) {
            return false;
        }
        TblPhenology other = (TblPhenology) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblPhenology[ id=" + id + " ]";
    }

}
