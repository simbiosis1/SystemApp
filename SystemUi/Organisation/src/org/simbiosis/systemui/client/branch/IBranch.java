package org.simbiosis.systemui.client.branch;

import java.util.List;

import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormActivity;
import org.kembang.module.client.mvp.FormWidget;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;

public interface IBranch {
	void setActivity(Activity activity, AppStatus appStatus);

	FormWidget getFormWidget();

	void setCoa(List<CoaDv> coas);

	void setBranch(List<BranchDv> branchs);

	void editSelected();

	void newBranch();

	void viewSelected();

	void clearViewer();

	BranchDv getBranch();

	public abstract class Activity extends FormActivity {
	}

}
