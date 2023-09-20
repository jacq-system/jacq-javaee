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
@Table(name = "frmwrk_employment_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkEmploymentType.findAll", query = "SELECT f FROM FrmwrkEmploymentType f")
    , @NamedQuery(name = "FrmwrkEmploymentType.findByEmploymentTypeId", query = "SELECT f FROM FrmwrkEmploymentType f WHERE f.employmentTypeId = :employmentTypeId")
    , @NamedQuery(name = "FrmwrkEmploymentType.findByType", query = "SELECT f FROM FrmwrkEmploymentType f WHERE f.type = :type")})
public class FrmwrkEmploymentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employment_type_id")
    private Long employmentTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employmentTypeId", fetch = FetchType.LAZY)
    private List<FrmwrkUser> frmwrkUserList;

    public FrmwrkEmploymentType() {
    }

    public FrmwrkEmploymentType(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public FrmwrkEmploymentType(Long employmentTypeId, String type) {
        this.employmentTypeId = employmentTypeId;
        this.type = type;
    }

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<FrmwrkUser> getFrmwrkUserList() {
        return frmwrkUserList;
    }

    public void setFrmwrkUserList(List<FrmwrkUser> frmwrkUserList) {
        this.frmwrkUserList = frmwrkUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employmentTypeId != null ? employmentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrmwrkEmploymentType)) {
            return false;
        }
        FrmwrkEmploymentType other = (FrmwrkEmploymentType) object;
        if ((this.employmentTypeId == null && other.employmentTypeId != null) || (this.employmentTypeId != null && !this.employmentTypeId.equals(other.employmentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkEmploymentType[ employmentTypeId=" + employmentTypeId + " ]";
    }

}
