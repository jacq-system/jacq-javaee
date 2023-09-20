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
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "mig_nom_substantive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNomSubstantive.findAll", query = "SELECT t FROM TblNomSubstantive t")
    , @NamedQuery(name = "TblNomSubstantive.findBySubstantiveId", query = "SELECT t FROM TblNomSubstantive t WHERE t.substantiveId = :substantiveId")
    , @NamedQuery(name = "TblNomSubstantive.findBySubstantive", query = "SELECT t FROM TblNomSubstantive t WHERE t.substantive = :substantive")
    , @NamedQuery(name = "TblNomSubstantive.findLikeSubstantive", query = "SELECT t FROM TblNomSubstantive t WHERE t.substantive LIKE :substantive")})
public class TblNomSubstantive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "substantive_id")
    private Long substantiveId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "substantive")
    private String substantive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "substantiveId")
    private List<TblNomName> tblNomNameList;

    public TblNomSubstantive() {
    }

    public TblNomSubstantive(Long substantiveId) {
        this.substantiveId = substantiveId;
    }

    public TblNomSubstantive(Long substantiveId, String substantive) {
        this.substantiveId = substantiveId;
        this.substantive = substantive;
    }

    public Long getSubstantiveId() {
        return substantiveId;
    }

    public void setSubstantiveId(Long substantiveId) {
        this.substantiveId = substantiveId;
    }

    public String getSubstantive() {
        return substantive;
    }

    public void setSubstantive(String substantive) {
        this.substantive = substantive;
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
        hash += (substantiveId != null ? substantiveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNomSubstantive)) {
            return false;
        }
        TblNomSubstantive other = (TblNomSubstantive) object;
        if ((this.substantiveId == null && other.substantiveId != null) || (this.substantiveId != null && !this.substantiveId.equals(other.substantiveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblNomSubstantive[ substantiveId=" + substantiveId + " ]";
    }

}
