/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.controller;

import org.jacq.input.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.faces.component.UIComponent;
import org.jacq.common.model.rest.CultivarResult;
import org.jacq.common.model.rest.LocationResult;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.model.rest.ScientificNameResult;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.GatheringService;
import org.jacq.common.rest.OrganisationService;
import org.jacq.common.rest.names.ScientificNameService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.input.listener.OrganisationSelectListener;
import org.jacq.input.view.DerivativeSearchModel;
import org.jacq.input.view.LazyDerivativeDataModel;
import org.jacq.input.view.LazyDerivativeDownloadDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.column.Column;
import org.primefaces.component.api.UIColumn;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author wkoller
 */
@Named
@ViewScoped
public class LivingPlantController implements Serializable, OrganisationSelectListener {

    @Inject
    protected SessionManager sessionManager;

    @Inject
    protected SessionController sessionController;

    public static final String TYPE_ALL = "all";
    public static final String TYPE_LIVING = "living";
    public static final String TYPE_VEGETATIVE = "vegetative";

    protected LazyDerivativeDataModel dataModel;
    protected LazyDerivativeDownloadDataModel downloadDataModel;
    protected ScientificNameService scientificNameService;
    protected OrganisationService organisationService;
    protected DerivativeService derivativeService;
    /**
     * Reference to gathering service
     */
    protected GatheringService gatheringService;

    protected Boolean downloadRender;

    @Inject
    protected OrganisationHierarchicSelectController organisationHierarchicSelectController;

    @PostConstruct
    public void init() {
        this.dataModel = new LazyDerivativeDataModel(ServicesUtil.getDerivativeService(), this.getDerivativeSearchModel());
        this.downloadDataModel = new LazyDerivativeDownloadDataModel(ServicesUtil.getDerivativeService(), this.dataModel);
        this.scientificNameService = ServicesUtil.getScientificNameService();
        this.organisationService = ServicesUtil.getOrganisationService();
        this.gatheringService = ServicesUtil.getGatheringService();
        this.derivativeService = ServicesUtil.getDerivativeService();

        this.downloadRender = false;
        if (sessionManager.getUser() != null && this.dataModel.getDerivativeSearchModel().getCallFlag() == 0) {
            this.dataModel.getDerivativeSearchModel().setOrganisationId(sessionManager.getUser().getOrganisationId() != null ? sessionManager.getUser().getOrganisationId() : null);
            this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(this.organisationService.load(this.dataModel.getDerivativeSearchModel().getOrganisationId()));
            this.dataModel.getDerivativeSearchModel().setHierarchic(true);
        }
        this.showorganisationHierarchicSelectController();
    }

    /**
     * Removes Markings of IndexSeminum
     */
    public void removeIndexSeminumMarking() {
        if (this.derivativeService.removeIndexSeminumMarking(this.dataModel.getDerivativeSearchModel().getType(), this.dataModel.getDerivativeSearchModel().getId(), this.dataModel.getDerivativeSearchModel().getPlaceNumber(), this.dataModel.getDerivativeSearchModel().getAccessionNumber(), this.dataModel.getDerivativeSearchModel().getSeparatedFilter(), this.dataModel.getDerivativeSearchModel().getScientificNameId(), this.dataModel.getDerivativeSearchModel().getOrganisationId(), this.dataModel.getDerivativeSearchModel().getHierarchic(), this.dataModel.getDerivativeSearchModel().getIndexSeminumFilter(), this.dataModel.getDerivativeSearchModel().getGatheringLocationName(), (this.dataModel.getDerivativeSearchModel().getExhibition() != null) ? this.dataModel.getDerivativeSearchModel().getExhibition() : null, (this.dataModel.getDerivativeSearchModel().getWorking() != null) ? this.dataModel.getDerivativeSearchModel().getWorking() : null, (this.dataModel.getDerivativeSearchModel().getSelectedCultivar() != null) ? this.dataModel.getDerivativeSearchModel().getSelectedCultivar().getCultivar() : null, this.dataModel.getDerivativeSearchModel().getClassification(), null, null) == null) {
            sessionController.setGrowlMessage(FacesMessage.SEVERITY_ERROR, "error", "not_allowed");
        }

    }

    public DerivativeSearchModel getDerivativeSearchModel() {
        return sessionManager.getDerivativeSearchModel();
    }

    public LazyDerivativeDataModel getDataModel() {
        return dataModel;
    }

    public LazyDerivativeDownloadDataModel getDownloadDataModel() {
        return downloadDataModel;
    }

    public String getTypeAll() {
        return TYPE_ALL;
    }

    public String getTypeLiving() {
        return TYPE_LIVING;
    }

    public String getTypeVegetative() {
        return TYPE_VEGETATIVE;
    }

    public List<ScientificNameResult> completeScientificName(String query) {
        return this.scientificNameService.find(query, Boolean.TRUE);
    }

    public List<LocationResult> completeLocation(String query) {
        return this.gatheringService.locationFind(query, 0, 10);
    }

    public List<CultivarResult> completeCultivar(String query) {
        return this.derivativeService.cultivarFind(query, 0, 10);
    }

    public Boolean getDownloadRender() {
        return downloadRender;
    }

    public void setDownloadRender(Boolean downloadRender) {
        this.downloadRender = downloadRender;
    }

    public List<OrganisationResult> completeOrganisation(String query) {
        return this.organisationService.search(null, query, null, null, null, null, null, 0, 10);
    }

    public void setRenderedTrue() {
        this.setDownloadRender(true);
    }

    public void organisationHierachicSelectEvent(SelectEvent event) {
        this.showorganisationHierarchicSelectController();
    }

    public void showorganisationHierarchicSelectController() {
        this.organisationHierarchicSelectController.show(this.getDerivativeSearchModel().getSelectedOrganisation(), this);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:hierachicSearch");
    }

    @Override
    public void setSelectedOrganisation(OrganisationResult organisationResult) {
        this.dataModel.getDerivativeSearchModel().setSelectedOrganisation(organisationResult);
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:organisation");
    }

    public OrganisationHierarchicSelectController getOrganisationHierarchicSelectController() {
        return organisationHierarchicSelectController;
    }

    public void setOrganisationHierarchicSelectController(OrganisationHierarchicSelectController organisationHierarchicSelectController) {
        this.organisationHierarchicSelectController = organisationHierarchicSelectController;
    }

    public void postProcessXlsx(Object document) {
        if (document == null) {
            return;
        }

        try {
            Workbook workbook = (Workbook) document;
            Sheet sheet = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : workbook.createSheet("Export");

            // Locate the export table to extract header labels
            FacesContext fc = FacesContext.getCurrentInstance();
            UIComponent comp = fc.getViewRoot().findComponent("jacq_form:downloadDerivativeTable");
            if (!(comp instanceof DataTable)) {
                return;
            }
            DataTable dataTable = (DataTable) comp;

            List<UIColumn> uiColumns = dataTable.getColumns();
            List<String> headers = new ArrayList<>();
            for (UIColumn uiCol : uiColumns) {
                String headerText = null;
                if (uiCol instanceof Column) {
                    Column col = (Column) uiCol;
                    Object exportHeader = col.getExportHeaderValue();
                    if (exportHeader != null) {
                        headerText = exportHeader.toString();
                    } else {
                        headerText = col.getHeaderText();
                    }
                }
                headers.add(headerText != null ? headerText : "");
            }

            // Shift existing rows down to insert header at row 0
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum >= 0 || sheet.getPhysicalNumberOfRows() > 0) {
                sheet.shiftRows(0, lastRowNum, 1, true, false);
            }

            Row headerRow = sheet.createRow(0);
            for (int c = 0; c < headers.size(); c++) {
                Cell cell = headerRow.createCell(c);
                cell.setCellValue(headers.get(c));
            }
        } catch (ClassCastException e) {
            // If document is not a Workbook (shouldn't happen for type=xlsx), ignore gracefully
        }
    }

}
