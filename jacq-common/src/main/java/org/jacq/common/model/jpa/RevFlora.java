/*
 * Copyright 2018 wkoller.
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "rev_flora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RevFlora.findAll", query = "SELECT r FROM RevFlora r")
    , @NamedQuery(name = "RevFlora.findByRevFloraId", query = "SELECT r FROM RevFlora r WHERE r.revFloraId = :revFloraId")})
public class RevFlora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rev_flora_id")
    private Long revFloraId;
    @JoinColumn(name = "uuid_minter_id", referencedColumnName = "uuid_minter_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SrvcUuidMinter uuidMinterId;

    public RevFlora() {
    }

    public RevFlora(Long revFloraId) {
        this.revFloraId = revFloraId;
    }

    public Long getRevFloraId() {
        return revFloraId;
    }

    public void setRevFloraId(Long revFloraId) {
        this.revFloraId = revFloraId;
    }

    public SrvcUuidMinter getUuidMinterId() {
        return uuidMinterId;
    }

    public void setUuidMinterId(SrvcUuidMinter uuidMinterId) {
        this.uuidMinterId = uuidMinterId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revFloraId != null ? revFloraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RevFlora)) {
            return false;
        }
        RevFlora other = (RevFlora) object;
        if ((this.revFloraId == null && other.revFloraId != null) || (this.revFloraId != null && !this.revFloraId.equals(other.revFloraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.RevFlora[ revFloraId=" + revFloraId + " ]";
    }

}
