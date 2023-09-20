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
import java.time.LocalDate;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_living_plant_tree_record_file_page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findAll", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t")
    , @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findById", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.id = :id")
    , @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findByCorrectionsDone", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.correctionsDone = :correctionsDone")
    , @NamedQuery(name = "TblLivingPlantTreeRecordFilePage.findByCorrectionsDate", query = "SELECT t FROM TblLivingPlantTreeRecordFilePage t WHERE t.correctionsDate = :correctionsDate")})
public class TblLivingPlantTreeRecordFilePage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "corrections_done")
    private boolean correctionsDone;
    @Column(name = "corrections_date")
    private LocalDate correctionsDate;
    @JoinColumn(name = "living_plant_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblLivingPlant livingPlantId;
    @JoinColumn(name = "tree_record_file_page_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblTreeRecordFilePage treeRecordFilePageId;

    public TblLivingPlantTreeRecordFilePage() {
    }

    public TblLivingPlantTreeRecordFilePage(Long id) {
        this.id = id;
    }

    public TblLivingPlantTreeRecordFilePage(Long id, boolean correctionsDone) {
        this.id = id;
        this.correctionsDone = correctionsDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getCorrectionsDone() {
        return correctionsDone;
    }

    public void setCorrectionsDone(boolean correctionsDone) {
        this.correctionsDone = correctionsDone;
    }

    public LocalDate getCorrectionsDate() {
        return correctionsDate;
    }

    public void setCorrectionsDate(LocalDate correctionsDate) {
        this.correctionsDate = correctionsDate;
    }

    public TblLivingPlant getLivingPlantId() {
        return livingPlantId;
    }

    public void setLivingPlantId(TblLivingPlant livingPlantId) {
        this.livingPlantId = livingPlantId;
    }

    public TblTreeRecordFilePage getTreeRecordFilePageId() {
        return treeRecordFilePageId;
    }

    public void setTreeRecordFilePageId(TblTreeRecordFilePage treeRecordFilePageId) {
        this.treeRecordFilePageId = treeRecordFilePageId;
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
        if (!(object instanceof TblLivingPlantTreeRecordFilePage)) {
            return false;
        }
        TblLivingPlantTreeRecordFilePage other = (TblLivingPlantTreeRecordFilePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblLivingPlantTreeRecordFilePage[ id=" + id + " ]";
    }

}
