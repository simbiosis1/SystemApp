package org.simbiosis.systemui.client.role;

import org.simbiosis.systemui.client.editor.RoleMenuViewerTable;
import org.simbiosis.systemui.shared.RoleDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RoleViewer extends Composite implements Editor<RoleDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, RoleViewer> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoleDv, RoleViewer> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	Label name;
	@UiField
	Label description;
	@UiField
	RoleMenuViewerTable roleMenus;

	public RoleViewer() {
		initWidget(uiBinder.createAndBindUi(this));
		//
		driver.initialize(this);
		driver.edit(new RoleDv());
	}

	public void setRole(RoleDv role) {
		driver.edit(role);
	}
}
