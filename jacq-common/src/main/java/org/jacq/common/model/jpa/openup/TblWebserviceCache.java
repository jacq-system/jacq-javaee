/*
 * Copyright 2016 wkoller.
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
package org.jacq.common.model.jpa.openup;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wkoller
 */
@Entity
@Table(name = "tbl_webservice_cache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWebserviceCache.findAll", query = "SELECT t FROM TblWebserviceCache t"),
    @NamedQuery(name = "TblWebserviceCache.findById", query = "SELECT t FROM TblWebserviceCache t WHERE t.id = :id"),
    @NamedQuery(name = "TblWebserviceCache.findByQuery", query = "SELECT t FROM TblWebserviceCache t WHERE t.query = :query"),
    @NamedQuery(name = "TblWebserviceCache.findByTimestamp", query = "SELECT t FROM TblWebserviceCache t WHERE t.timestamp = :timestamp")})
public class TblWebserviceCache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "query")
    private String query;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "response")
    private String response;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    private int timestamp;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblService serviceId;

    public TblWebserviceCache() {
    }

    public TblWebserviceCache(Integer id) {
        this.id = id;
    }

    public TblWebserviceCache(Integer id, String query, int timestamp) {
        this.id = id;
        this.query = query;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public TblService getServiceId() {
        return serviceId;
    }

    public void setServiceId(TblService serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof TblWebserviceCache)) {
            return false;
        }
        TblWebserviceCache other = (TblWebserviceCache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jacq.common.model.jpa.openup.TblWebserviceCache[ id=" + id + " ]";
    }

}
