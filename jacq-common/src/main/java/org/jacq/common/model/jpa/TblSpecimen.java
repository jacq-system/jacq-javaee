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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_specimen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSpecimen.findAll", query = "SELECT t FROM TblSpecimen t")
    , @NamedQuery(name = "TblSpecimen.findBySpecimenId", query = "SELECT t FROM TblSpecimen t WHERE t.specimenId = :specimenId")
    , @NamedQuery(name = "TblSpecimen.findByHerbarNumber", query = "SELECT t FROM TblSpecimen t WHERE t.herbarNumber = :herbarNumber")
    , @NamedQuery(name = "TblSpecimen.findByBotanicalObjectId", query = "SELECT t FROM TblSpecimen t WHERE t.specimenId in (SELECT td.derivativeId FROM TblDerivative td WHERE td.botanicalObjectId = (:botanicalObjectId))")})
public class TblSpecimen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "specimen_id")
    private Long specimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "herbar_number")
    private String herbarNumber;
    @JoinColumn(name = "specimen_id", referencedColumnName = "derivative_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TblDerivative tblDerivative;

    public TblSpecimen() {
    }

    public TblSpecimen(Long specimenId) {
        this.specimenId = specimenId;
    }

    public TblSpecimen(Long specimenId, String herbarNumber) {
        this.specimenId = specimenId;
        this.herbarNumber = herbarNumber;
    }

    public Long getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(Long specimenId) {
        this.specimenId = specimenId;
    }

    public String getHerbarNumber() {
        return herbarNumber;
    }

    public void setHerbarNumber(String herbarNumber) {
        this.herbarNumber = herbarNumber;
    }

    public TblDerivative getTblDerivative() {
        return tblDerivative;
    }

    public void setTblDerivative(TblDerivative tblDerivative) {
        this.tblDerivative = tblDerivative;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specimenId != null ? specimenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSpecimen)) {
            return false;
        }
        TblSpecimen other = (TblSpecimen) object;
        if ((this.specimenId == null && other.specimenId != null) || (this.specimenId != null && !this.specimenId.equals(other.specimenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSpecimen[ specimenId=" + specimenId + " ]";
    }

}
