/*******************************************************************************
 * Copyright (c) 2011 Department of Computational Linguistics, University of Cologne, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Department of Computational Linguistics, University of Cologne, Germany - initial API and implementation
 ******************************************************************************/
package org.schwiebert.cloudio.application.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.schwiebert.cloudio.TagCloudViewer;
import org.schwiebert.cloudio.application.ui.TagCloudViewPart;

/**
 * 
 * @author sschwieb
 *
 */
public abstract class AbstractTagCloudAction implements IWorkbenchWindowActionDelegate {

	private Shell shell;
	private TagCloudViewPart tcViewPart;

	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {}

	@Override
	public void dispose() {}

	@Override
	public void init(IWorkbenchWindow window) {
		this.shell = window.getShell();
		tcViewPart = (TagCloudViewPart) window.getActivePage().getActivePart();
	}
	
	public Shell getShell() {
		return shell;
	}
	

	protected TagCloudViewer getViewer() {
		return tcViewPart.getViewer();
	}


}