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
@Table(name = "tbl_sex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblSex.findAll", query = "SELECT t FROM TblSex t")
    , @NamedQuery(name = "TblSex.findById", query = "SELECT t FROM TblSex t WHERE t.id = :id")
    , @NamedQuery(name = "TblSex.findBySex", query = "SELECT t FROM TblSex t WHERE t.sex = :sex")})
public class TblSex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sex")
    private String sex;
    @ManyToMany(mappedBy = "tblSexList", fetch = FetchType.LAZY)
    private List<TblBotanicalObject> tblBotanicalObjectList;

    public TblSex() {
    }

    public TblSex(Long id) {
        this.id = id;
    }

    public TblSex(Long id, String sex) {
        this.id = id;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSex)) {
            return false;
        }
        TblSex other = (TblSex) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblSex[ id=" + id + " ]";
    }

}
