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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "frmwrk_accessOrganisation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrmwrkaccessOrganisation.findAll", query = "SELECT f FROM FrmwrkaccessOrganisation f")
    , @NamedQuery(name = "FrmwrkaccessOrganisation.findById", query = "SELECT f FROM FrmwrkaccessOrganisation f WHERE f.id = :id")
    , @NamedQuery(name = "FrmwrkaccessOrganisation.findByAllowDeny", query = "SELECT f FROM FrmwrkaccessOrganisation f WHERE f.allowDeny = :allowDeny")})
public class FrmwrkaccessOrganisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allowDeny")
    private boolean allowDeny;
    @JoinColumn(name = "AuthItem_name", referencedColumnName = "name")
    @ManyToOne(fetch = FetchType.LAZY)
    private FrmwrkAuthItem authItemname;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FrmwrkUser userId;
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblOrganisation organisationId;

    public FrmwrkaccessOrganisation() {
    }

    public FrmwrkaccessOrganisation(Long id) {
        this.id = id;
    }

    public FrmwrkaccessOrganisation(Long id, boolean allowDeny) {
        this.id = id;
        this.allowDeny = allowDeny;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAllowDeny() {
        return allowDeny;
    }

    public void setAllowDeny(boolean allowDeny) {
        this.allowDeny = allowDeny;
    }

    public FrmwrkAuthItem getAuthItemname() {
        return authItemname;
    }

    public void setAuthItemname(FrmwrkAuthItem authItemname) {
        this.authItemname = authItemname;
    }

    public FrmwrkUser getUserId() {
        return userId;
    }

    public void setUserId(FrmwrkUser userId) {
        this.userId = userId;
    }

    public TblOrganisation getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(TblOrganisation organisationId) {
        this.organisationId = organisationId;
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
        if (!(object instanceof FrmwrkaccessOrganisation)) {
            return false;
        }
        FrmwrkaccessOrganisation other = (FrmwrkaccessOrganisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.FrmwrkaccessOrganisation[ id=" + id + " ]";
    }

}
