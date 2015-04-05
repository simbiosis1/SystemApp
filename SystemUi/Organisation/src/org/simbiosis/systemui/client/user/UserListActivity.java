package org.simbiosis.systemui.client.user;

import java.util.List;

import org.kembang.module.client.mvp.FormActivityType;
import org.kembang.module.client.rpc.SystemService;
import org.kembang.module.client.rpc.SystemServiceAsync;
import org.kembang.module.shared.SimpleBranchDv;
import org.kembang.module.shared.UserDv;
import org.simbiosis.systemui.client.AppFactory;
import org.simbiosis.systemui.client.places.User;
import org.simbiosis.systemui.client.rpc.AppService;
import org.simbiosis.systemui.client.rpc.AppServiceAsync;
import org.simbiosis.systemui.client.user.IUserList.Activity;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

public class UserListActivity extends Activity {

	private final AppServiceAsync srv = GWT.create(AppService.class);
	private final SystemServiceAsync systemSrv = GWT
			.create(SystemService.class);

	User myPlace;
	AppFactory appFactory;

	public UserListActivity(User myPlace, AppFactory appFactory) {
		setMainFactory(appFactory);
		this.myPlace = myPlace;
		this.appFactory = appFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		IUserList myForm = appFactory.getUserEditor();
		myForm.setActivity(this, appFactory.getAppStatus());
		if (appFactory.getAppStatus().isLogin()) {
			loadCommonData();
		}
		appFactory.showApplication(panel, myForm.getFormWidget());
	}

	@Override
	public void dispatch(FormActivityType formActivityType) {
		switch (formActivityType) {
		case FA_NEW:
			onNew();
			break;
		case FA_EDIT:
			onEdit();
			break;
		case FA_SAVE:
			onSave();
			break;
		case FA_RELOAD:
			onReload();
			break;
		case FA_BACK:
			onBack();
			break;
		default:
			break;
		}
	}

	private void onNew() {
		IUserList myForm = appFactory.getUserEditor();
		myForm.newUser();
	}

	private void onReload() {
		loadUsers();
		IUserList myForm = appFactory.getUserEditor();
		myForm.clearViewer();
	}

	private void onSave() {
		boolean isSavingAllowed = false;
		IUserList myForm = appFactory.getUserEditor();
		UserDv dv = myForm.getUser();
		if (dv.isChangePassword()) {
			if (dv.getPassword().equals(dv.getConfirmPassword())) {
				isSavingAllowed = true;
			} else {
				Window.alert("Kata kunci konfirmasi tidak sama dengan kata kunci");
			}
		} else {
			isSavingAllowed = true;
		}
		if (isSavingAllowed) {
			showLoading();
			srv.saveUser(getKey(), dv, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					hideLoading();
					Window.alert("Data user sudah disimpan");
					onReload();
				}

				@Override
				public void onFailure(Throwable caught) {
					hideLoading();
					Window.alert("Error : saveUser");
				}
			});
		}
	}

	private void onBack() {
		IUserList myForm = appFactory.getUserEditor();
		myForm.viewSelected();
	}

	private void onEdit() {
		IUserList myForm = appFactory.getUserEditor();
		myForm.editSelected();
	}

	private void loadCommonData() {
		showLoading();
		systemSrv.listSimpleBranch(getKey(),
				new AsyncCallback<List<SimpleBranchDv>>() {

					@Override
					public void onSuccess(List<SimpleBranchDv> result) {
						IUserList myForm = appFactory.getUserEditor();
						myForm.setBranches(result);
						loadSubBranch();
					}

					@Override
					public void onFailure(Throwable caught) {
						hideLoading();
						Window.alert("Error : listBranches");
					}
				});
	}

	private void loadSubBranch() {
		srv.listSubBranch(getKey(), new AsyncCallback<List<SubBranchDv>>() {

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : listSubBranches");
			}

			@Override
			public void onSuccess(List<SubBranchDv> result) {
				IUserList myForm = appFactory.getUserEditor();
				// Tambahin untuk yang semua
				SubBranchDv all = new SubBranchDv();
				all.setId(0L);
				all.setName("SEMUA KAS");
				result.add(0, all);
				myForm.setSubBranches(result);
				loadRoles();
			}
		});
	}

	private void loadRoles() {
		srv.listRoles(getKey(), new AsyncCallback<List<RoleDv>>() {

			@Override
			public void onSuccess(List<RoleDv> result) {
				IUserList myForm = appFactory.getUserEditor();
				myForm.setRoles(result);
				loadUsers();
			}

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : listRoles");
			}
		});
	}

	private void loadUsers() {
		srv.listUsers(getKey(), new AsyncCallback<List<UserDv>>() {

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : listRoles");
			}

			@Override
			public void onSuccess(List<UserDv> result) {
				IUserList myForm = appFactory.getUserEditor();
				myForm.setUsers(result);
				hideLoading();
			}
		});
	}
}
