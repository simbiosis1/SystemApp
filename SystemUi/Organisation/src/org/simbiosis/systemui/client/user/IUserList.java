package org.simbiosis.systemui.client.user;

import java.util.List;

import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormActivity;
import org.kembang.module.client.mvp.FormWidget;
import org.kembang.module.shared.SimpleBranchDv;
import org.kembang.module.shared.UserDv;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.SubBranchDv;

public interface IUserList {
	void setActivity(Activity activity, AppStatus appStatus);

	FormWidget getFormWidget();

	void setBranches(List<SimpleBranchDv> branches);

	void setSubBranches(List<SubBranchDv> subBranches);

	void setRoles(List<RoleDv> roles);

	void setUsers(List<UserDv> users);

	void editSelected();

	void newUser();

	void viewSelected();

	void clearViewer();

	UserDv getUser();

	public abstract class Activity extends FormActivity {
	}

}
