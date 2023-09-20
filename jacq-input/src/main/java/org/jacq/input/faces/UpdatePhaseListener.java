/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.input.faces;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

/**
 *
 * @author fhafner
 */
public class UpdatePhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form:messages");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("jacq_form-topbar:timer");
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
