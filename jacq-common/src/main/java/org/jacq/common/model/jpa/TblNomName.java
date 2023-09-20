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
@Table(name = "mig_nom_name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNomName.findAll", query = "SELECT t FROM TblNomName t")
    , @NamedQuery(name = "TblNomName.findByNameId", query = "SELECT t FROM TblNomName t WHERE t.nameId = :nameId")
    , @NamedQuery(name = "TblNomName.findBySubstantive", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IS NULL AND t.secondEpithetId IS NULL")
    , @NamedQuery(name = "TblNomName.findBySubstantiveAndFirstEpithet", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IN (:firstEpithetIds) AND t.secondEpithetId IS NULL")
    , @NamedQuery(name = "TblNomName.findBySubstantiveAndFirstEpithetAndSecondEpithet", query = "SELECT t FROM TblNomName t WHERE t.substantiveId IN (:substantiveIds) AND t.firstEpithetId IN (:firstEpithetIds) AND t.secondEpithetId IN (:secondEpithetIds)")
    , @NamedQuery(name = "TblNomName.findBySubstantiveAndRank", query = "SELECT t FROM TblNomName t INNER JOIN t.substantiveId s INNER JOIN t.rankId r WHERE s.substantiveId = :substantiveId AND r.rankId = :rankId")
    , @NamedQuery(name = "TblNomName.findNameIdAndScientificName", query = "SELECT new org.jacq.common.model.jpa.ScientificName(t.nameId, v.scientificName) FROM TblNomName t JOIN t.viewScientificName v WHERE t.nameId = :scientificNameId")})
public class TblNomName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "name_id")
    private Long nameId;
    @Lob
    @Size(max = 65535)
    @Column(name = "annotation")
    private String annotation;
    @JoinTable(name = "tbl_nom_name_status", joinColumns = {
        @JoinColumn(name = "name_id", referencedColumnName = "name_id")}, inverseJoinColumns = {
        @JoinColumn(name = "status_id", referencedColumnName = "status_id")})
    @ManyToMany
    private List<TblNomStatus> tblNomStatusList;
    @JoinTable(name = "tbl_nom_name_person", joinColumns = {
        @JoinColumn(name = "name_id", referencedColumnName = "name_id")}, inverseJoinColumns = {
        @JoinColumn(name = "author_id", referencedColumnName = "id")})
    @ManyToMany
    private List<TblPerson> tblPersonList;
    @JoinColumn(name = "first_epithet_id", referencedColumnName = "epithet_id")
    @ManyToOne()
    private TblNomEpithet firstEpithetId;
    @JoinColumn(name = "second_epithet_id", referencedColumnName = "epithet_id")
    @ManyToOne()
    private TblNomEpithet secondEpithetId;
    @JoinColumn(name = "substantive_id", referencedColumnName = "substantive_id")
    @ManyToOne(optional = false)
    private TblNomSubstantive substantiveId;
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id")
    @ManyToOne(optional = false)
    private TblNomRank rankId;
    @OneToMany(mappedBy = "basId")
    private List<TblNomName> tblNomNameList;
    @JoinColumn(name = "bas_id", referencedColumnName = "name_id")
    @ManyToOne
    private TblNomName basId;

    public TblNomName() {
    }

    public TblNomName(Long nameId) {
        this.nameId = nameId;
    }

    public Long getNameId() {
        return nameId;
    }

    public void setNameId(Long nameId) {
        this.nameId = nameId;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @XmlTransient
    public List<TblNomStatus> getTblNomStatusList() {
        return tblNomStatusList;
    }

    public void setTblNomStatusList(List<TblNomStatus> tblNomStatusList) {
        this.tblNomStatusList = tblNomStatusList;
    }

    @XmlTransient
    public List<TblPerson> getTblPersonList() {
        return tblPersonList;
    }

    public void setTblPersonList(List<TblPerson> tblPersonList) {
        this.tblPersonList = tblPersonList;
    }

    public TblNomEpithet getFirstEpithetId() {
        return firstEpithetId;
    }

    public void setFirstEpithetId(TblNomEpithet firstEpithetId) {
        this.firstEpithetId = firstEpithetId;
    }

    public TblNomEpithet getSecondEpithetId() {
        return secondEpithetId;
    }

    public void setSecondEpithetId(TblNomEpithet secondEpithetId) {
        this.secondEpithetId = secondEpithetId;
    }

    public TblNomSubstantive getSubstantiveId() {
        return substantiveId;
    }

    public void setSubstantiveId(TblNomSubstantive substantiveId) {
        this.substantiveId = substantiveId;
    }

    public TblNomRank getRankId() {
        return rankId;
    }

    public void setRankId(TblNomRank rankId) {
        this.rankId = rankId;
    }

    @XmlTransient
    public List<TblNomName> getTblNomNameList() {
        return tblNomNameList;
    }

    public void setTblNomNameList(List<TblNomName> tblNomNameList) {
        this.tblNomNameList = tblNomNameList;
    }

    public TblNomName getBasId() {
        return basId;
    }

    public void setBasId(TblNomName basId) {
        this.basId = basId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameId != null ? nameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNomName)) {
            return false;
        }
        TblNomName other = (TblNomName) object;
        if ((this.nameId == null && other.nameId != null) || (this.nameId != null && !this.nameId.equals(other.nameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblNomName[ nameId=" + nameId + " ]";
    }

    /**
     * Custom Mappings
     */
    @JoinColumn(name = "name_id", referencedColumnName = "scientific_name_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ViewScientificName viewScientificName;

    public ViewScientificName getViewScientificName() {
        return viewScientificName;
    }

    public void setViewScientificName(ViewScientificName viewScientificName) {
        this.viewScientificName = viewScientificName;
    }
}
