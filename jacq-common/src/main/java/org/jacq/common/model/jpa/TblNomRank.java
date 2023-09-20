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
@Table(name = "mig_nom_rank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNomRank.findAll", query = "SELECT t FROM TblNomRank t")
    , @NamedQuery(name = "TblNomRank.findByRankId", query = "SELECT t FROM TblNomRank t WHERE t.rankId = :rankId")
    , @NamedQuery(name = "TblNomRank.findByRank", query = "SELECT t FROM TblNomRank t WHERE t.rank = :rank")
    , @NamedQuery(name = "TblNomRank.findByRankLatin", query = "SELECT t FROM TblNomRank t WHERE t.rankLatin = :rankLatin")
    , @NamedQuery(name = "TblNomRank.findByRankAbbr", query = "SELECT t FROM TblNomRank t WHERE t.rankAbbr = :rankAbbr")
    , @NamedQuery(name = "TblNomRank.findByRankPlural", query = "SELECT t FROM TblNomRank t WHERE t.rankPlural = :rankPlural")})
public class TblNomRank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rank_id")
    private Long rankId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rank")
    private String rank;
    @Size(max = 45)
    @Column(name = "rank_latin")
    private String rankLatin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rank_abbr")
    private String rankAbbr;
    @Size(max = 45)
    @Column(name = "rank_plural")
    private String rankPlural;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rankId")
    private List<TblNomName> tblNomNameList;

    public TblNomRank() {
    }

    public TblNomRank(Long rankId) {
        this.rankId = rankId;
    }

    public TblNomRank(Long rankId, String rank, String rankAbbr) {
        this.rankId = rankId;
        this.rank = rank;
        this.rankAbbr = rankAbbr;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankLatin() {
        return rankLatin;
    }

    public void setRankLatin(String rankLatin) {
        this.rankLatin = rankLatin;
    }

    public String getRankAbbr() {
        return rankAbbr;
    }

    public void setRankAbbr(String rankAbbr) {
        this.rankAbbr = rankAbbr;
    }

    public String getRankPlural() {
        return rankPlural;
    }

    public void setRankPlural(String rankPlural) {
        this.rankPlural = rankPlural;
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
        hash += (rankId != null ? rankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNomRank)) {
            return false;
        }
        TblNomRank other = (TblNomRank) object;
        if ((this.rankId == null && other.rankId != null) || (this.rankId != null && !this.rankId.equals(other.rankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.TblNomRank[ rankId=" + rankId + " ]";
    }

}
