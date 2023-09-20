/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jacq.common.manager.BaseGatheringManager;
import org.jacq.common.util.ServicesUtil;
import org.jacq.service.JacqServiceConfig;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class GatheringManager extends BaseGatheringManager {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected JacqServiceConfig jacqConfig;

    @PostConstruct
    public void init() {
        this.setEntityManager(em);
        this.setGeoNamesService(ServicesUtil.getGeoNamesService(jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_URL)));
        this.setGeoNamesUsername(jacqConfig.getString(JacqServiceConfig.SERVICE_GEONAMES_USERNAME));
    }
}
