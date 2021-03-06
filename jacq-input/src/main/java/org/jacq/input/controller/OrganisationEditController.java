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
package org.jacq.input.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jacq.common.model.rest.RoleResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.UserResult;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.UserService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.ApplicationManager;
import org.jacq.input.SessionManager;
import org.jacq.input.listener.OrganisationSelectListener;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author wkoller
 */
@ManagedBean
@ViewScoped
public class OrganisationEditController implements OrganisationSelectListener {

    @Inject
    protected SessionController sessionController;

    @Inject
    protected ApplicationManager applicationManager;

    @Inject
    protected SessionManager sessionManager;

    protected Long organisationId;

    protected OrganisationResult organisation;

    protected OrganisationService organisationService;

    protected UserService userService;

    protected List<OrganisationResult> organisations;

    protected List<UserResult> users;

    protected List<RoleResult> roles;

    @ManagedProperty(value = "#{organisationHierarchicSelectController}")
    protected OrganisationHierarchicSelectController organisationHierarchicSelectController;

    @PostConstruct
    public void init() {
        this.organisationService = ServicesUtil.getOrganisationService();

        this.userService = ServicesUtil.getUserService();

        this.organisations = this.organisationService.findAll();

        this.organisation = new OrganisationResult();
        if (sessionManager.getUser() != null && sessionManager.getUser().getOrganisationId() != null) {
            this.organisation.setParentOrganisationResult(this.organisationService.load(sessionManager.getUser().getOrganisationId()));
            this.organisation.setParentOrganisationDescription(this.organisation.getParentOrganisationResult().getDescription());
            this.organisation.setParentOrganisationId(this.organisation.getParentOrganisationResult().getOrganisationId());
        }

        this.users = this.userService.findAll();

        this.roles = this.userService.findAllRole();

        this.showorganisationHierarchicSelectController();
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;

        if (this.organisationId != null) {
            this.organisation = this.organisationService.load(this.organisationId);
        }
    }

    public OrganisationResult getOrganisation() {
        return organisation;
    }

    public String edit() {
        this.organisation = this.organisationService.save(this.organisation);
        this.applicationManager.clearOrganisationHierachy();
        return null;
    }

    public List<OrganisationResult> getOrganisations() {
        return organisations;
    }

    public List<UserResult> getUsers() {
        return users;
    }

    public List<RoleResult> getRoles() {
        return roles;
    }

    public void saveMessage() {
        sessionController.setGrowlMessage("successful", "entrysaved");
    }

    public void organisationHierachicSelectEvent(SelectEvent event) {
        this.showorganisationHierarchicSelectController();
    }

    public void showorganisationHierarchicSelectController() {
        this.organisationHierarchicSelectController.show(this.organisation.getParentOrganisationResult(), this);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:hierachicSearch");
    }

    public OrganisationHierarchicSelectController getOrganisationHierarchicSelectController() {
        return organisationHierarchicSelectController;
    }

    public void setOrganisationHierarchicSelectController(OrganisationHierarchicSelectController organisationHierarchicSelectController) {
        this.organisationHierarchicSelectController = organisationHierarchicSelectController;
    }

    public List<OrganisationResult> completeOrganisation(String query) {
        return this.organisationService.search(null, query, null, null, null, null, null, 0, 10);
    }

    /**
     * Listener to get the selceted Organisation from
     * OrganisationHierarchicSelect
     *
     * @param organisationResult
     */
    @Override
    public void setSelectedOrganisation(OrganisationResult organisationResult) {
        this.organisation.setParentOrganisationResult(organisationResult);
        this.organisation.setParentOrganisationId(organisationResult.getOrganisationId());
        this.organisation.setParentOrganisationDescription(organisationResult.getDescription());
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:organisation");
    }
}
