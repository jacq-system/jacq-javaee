/*
 * Copyright 2018 fhafner.
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
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fhafner
 */
@Entity
@Table(name = "view_protolog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewProtolog.findAll", query = "SELECT v FROM ViewProtolog v")
    , @NamedQuery(name = "ViewProtolog.findByCitationId", query = "SELECT v FROM ViewProtolog v WHERE v.citationId = :citationId")})
public class ViewProtolog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "citation_id")
    private Long citationId;
    @Lob
    @Size(max = 65535)
    @Column(name = "protolog")
    private String protolog;

    public ViewProtolog() {
    }

    public Long getCitationId() {
        return citationId;
    }

    public void setCitationId(Long citationId) {
        this.citationId = citationId;
    }

    public String getProtolog() {
        return protolog;
    }

    public void setProtolog(String protolog) {
        this.protolog = protolog;
    }

}
