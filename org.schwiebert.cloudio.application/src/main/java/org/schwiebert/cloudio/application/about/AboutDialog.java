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
package org.schwiebert.cloudio.application.about;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.schwiebert.cloudio.TagCloud;
import org.schwiebert.cloudio.layout.DefaultLayouter;
import org.schwiebert.cloudio.util.Word;

/**
 * 
 * @author sschwieb
 *
 */
public class AboutDialog extends Dialog {
	
	
	private TagCloud tc;

	public AboutDialog(Shell parentShell) {
		super(parentShell);
		setBlockOnOpen(false);
	}
	

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout());
		tc = new TagCloud(parent, SWT.NONE) {
			
			public Rectangle getClientArea() {
				return new Rectangle(0, 0, 400, 330);
			};
			
		};
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint=330;
		data.widthHint=400;
		tc.setLayoutData(data);
		tc.setMaxFontHeight(50);
		tc.setMinFontSize(15);
		tc.setLayouter(new DefaultLayouter(5, 0));
		List<Word> values = new ArrayList<Word>();
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		Color[] colors = new Color[5];
		colors[0] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_CYAN);
		colors[1] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		colors[2] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		colors[3] = Display.getDefault().getSystemColor(SWT.COLOR_DARK_YELLOW);
		colors[4] = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		Word w = getWord("Cloudio", fontNames, colors);
		w.weight=0.95;
		w.angle=-35;
		w.color = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		values.add(w);
		w = getWord("Inspired by Wordle", fontNames, colors);
		w.color = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		w.angle = (float) (Math.random() * 90);
		if(Math.random() < 0.5) w.angle = -w.angle;
		w.weight=0.2;
		if(Math.random() < 0.5) w.angle = -w.angle;
		values.add(w);
		for(int i = 0; i < 20; i++) {
			w = getWord("Cloudio", fontNames, colors);
			values.add(w);
			w = getWord("Tag Cloud", fontNames, colors);
			values.add(w);
		}
		tc.setWords(values, null);
		// FIXME Should not be required to re-calc extents!
		tc.layoutCloud(null, true);
		Label l = new Label(parent, SWT.NONE);
		l.setText("Written by Stephan Schwiebert, 2011");
		return tc;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}


	private Word getWord(String string, String[] fontNames, Color[] colors) {
		Word w = new Word(string);
		w.color = colors[(int) (Math.random()*colors.length-1)];
		w.weight = Math.random()/2;
		w.fontData = getShell().getFont().getFontData().clone();
		w.angle = (float) (Math.random() * 20);
		if(Math.random() < 0.5) w.angle = -w.angle;
		String name = fontNames[(int) (Math.random()*(fontNames.length-1))];
		for (FontData fd : w.fontData) {
			fd.setName(name);
		}
		return w;
	}

	@Override
	public boolean close() {
		tc.dispose();
		return super.close();
	}

}