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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_label_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLabelType.findAll", query = "SELECT t FROM TblLabelType t")
    , @NamedQuery(name = "TblLabelType.findByLabelTypeId", query = "SELECT t FROM TblLabelType t WHERE t.labelTypeId = :labelTypeId")
    , @NamedQuery(name = "TblLabelType.findByType", query = "SELECT t FROM TblLabelType t WHERE t.type = :type")})
public class TblLabelType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "label_type_id")
    private Long labelTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "type")
    private String type;
    @ManyToMany(mappedBy = "tblLabelTypeList", fetch = FetchType.LAZY)
    private List<TblBotanicalObject> tblBotanicalObjectList;

    public TblLabelType() {
    }

    public TblLabelType(Long labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public TblLabelType(Long labelTypeId, String type) {
        this.labelTypeId = labelTypeId;
        this.type = type;
    }

    public Long getLabelTypeId() {
        return labelTypeId;
    }

    public void setLabelTypeId(Long labelTypeId) {
        this.labelTypeId = labelTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<TblBotanicalObject> getTblBotanicalObjectList() {
        return tblBotanicalObjectList;
    }

    public void setTblBotanicalObjectList(List<TblBotanicalObject> tblBotanicalObjectList) {
        this.tblBotanicalObjectList = tblBotanicalObjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labelTypeId != null ? labelTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblLabelType)) {
            return false;
        }
        TblLabelType other = (TblLabelType) object;
        if ((this.labelTypeId == null && other.labelTypeId != null) || (this.labelTypeId != null && !this.labelTypeId.equals(other.labelTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLabelType[ labelTypeId=" + labelTypeId + " ]";
    }

}
