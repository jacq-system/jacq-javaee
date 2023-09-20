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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_tree_record_file_page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTreeRecordFilePage.findAll", query = "SELECT t FROM TblTreeRecordFilePage t")
    , @NamedQuery(name = "TblTreeRecordFilePage.findById", query = "SELECT t FROM TblTreeRecordFilePage t WHERE t.id = :id")
    , @NamedQuery(name = "TblTreeRecordFilePage.findByPage", query = "SELECT t FROM TblTreeRecordFilePage t WHERE t.page = :page")})
public class TblTreeRecordFilePage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "page")
    private long page;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treeRecordFilePageId", fetch = FetchType.LAZY)
    private List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageList;
    @JoinColumn(name = "tree_record_file_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblTreeRecordFile treeRecordFileId;

    public TblTreeRecordFilePage() {
    }

    public TblTreeRecordFilePage(Long id) {
        this.id = id;
    }

    public TblTreeRecordFilePage(Long id, long page) {
        this.id = id;
        this.page = page;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public List<TblLivingPlantTreeRecordFilePage> getTblLivingPlantTreeRecordFilePageList() {
        return tblLivingPlantTreeRecordFilePageList;
    }

    public void setTblLivingPlantTreeRecordFilePageList(List<TblLivingPlantTreeRecordFilePage> tblLivingPlantTreeRecordFilePageList) {
        this.tblLivingPlantTreeRecordFilePageList = tblLivingPlantTreeRecordFilePageList;
    }

    public TblTreeRecordFile getTreeRecordFileId() {
        return treeRecordFileId;
    }

    public void setTreeRecordFileId(TblTreeRecordFile treeRecordFileId) {
        this.treeRecordFileId = treeRecordFileId;
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
        if (!(object instanceof TblTreeRecordFilePage)) {
            return false;
        }
        TblTreeRecordFilePage other = (TblTreeRecordFilePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblTreeRecordFilePage[ id=" + id + " ]";
    }

}
