package org.simbiosis.systemui.client.role;

import java.util.List;

import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormActivity;
import org.kembang.module.client.mvp.FormWidget;
import org.simbiosis.systemui.shared.RoleDv;

public interface IRoleList {
	
	void setActivity(Activity activity, AppStatus appStatus);

	FormWidget getFormWidget();

	void setRoles(List<RoleDv> roles);

	void editSelected();
	
	void newRole(RoleDv roleDv);
	
	void viewSelected();
	
	void clearViewer();
	
	RoleDv getRole();

	public abstract class Activity extends FormActivity {
	}
}
