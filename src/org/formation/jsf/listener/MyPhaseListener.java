package org.formation.jsf.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println("After " + arg0.getPhaseId().toString());
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		System.out.println("Before " + arg0.getPhaseId().toString());

	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.ANY_PHASE;
	}

}
