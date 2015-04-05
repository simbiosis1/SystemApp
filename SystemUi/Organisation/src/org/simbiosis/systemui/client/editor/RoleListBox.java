package org.simbiosis.systemui.client.editor;

import org.kembang.editor.client.ListBoxListEditor;
import org.simbiosis.systemui.shared.RoleDv;

public class RoleListBox extends ListBoxListEditor<Long, RoleDv> {

	@Override
	public int compareData(Long value, RoleDv data) {
		return data.getId().compareTo(value);
	}

	@Override
	public Long convertData(int index, String value) {
		return index > -1 ? Long.parseLong(value) : 0L;
	}

	@Override
	public String convertItemId(RoleDv data) {
		return data.getId().toString();
	}

	@Override
	public String createItemString(RoleDv data) {
		return data.toString();
	}

}
