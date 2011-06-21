/*******************************************************************************
* Copyright (c) 2011 Stephan Schwiebert. All rights reserved. This program and
* the accompanying materials are made available under the terms of the Eclipse
* Public License v1.0 which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* <p/>
* Contributors: Stephan Schwiebert - initial API and implementation
*******************************************************************************/
package org.schwiebert.cloudio;

import java.util.List;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public interface IEditableCloudLabelProvider extends ICloudLabelProvider {

	public void setColors(List<RGB> colors);

	public void setFonts(List<FontData> fonts);

	public void setAngles(List<Float> list);
	
}
