package org.simbiosis.systemui.client.branch;

import java.util.List;

import org.simbiosis.systemui.client.editor.CoaListEditor;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BranchEditor extends Composite implements Editor<BranchDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, BranchEditor> {
	}

	interface Driver extends SimpleBeanEditorDriver<BranchDv, BranchEditor> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	TextBox code;
	@UiField
	TextBox address;
	@UiField
	TextBox name;
	@UiField
	CoaListEditor coa1;
	@UiField
	CoaListEditor coa2;

	public BranchEditor(List<CoaDv> coas) {
		initWidget(uiBinder.createAndBindUi(this));
		//
		coa1.setList(coas);
		coa2.setList(coas);
		//
		driver.initialize(this);
	}

	public void setUser(BranchDv user) {
		driver.edit(user);
	}

	public BranchDv getBranch() {
		return driver.flush();
	}
}
