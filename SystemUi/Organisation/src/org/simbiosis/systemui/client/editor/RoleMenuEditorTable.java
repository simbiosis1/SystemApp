package org.simbiosis.systemui.client.editor;

import org.kembang.grid.client.AdvancedGrid;
import org.kembang.grid.client.ColumnDef;
import org.kembang.grid.client.ColumnType;
import org.kembang.grid.client.ColumnValue;
import org.simbiosis.systemui.shared.RoleMenuDv;

public class RoleMenuEditorTable extends AdvancedGrid<RoleMenuDv> {
	ColumnDef<RoleMenuDv, String> colNr = new ColumnDef<RoleMenuDv, String>(
			ColumnType.LABEL, "No", "34px", "30px") {

		@Override
		public String getDataValue(RoleMenuDv data) {
			return data.getNr().toString();
		}
	};

	ColumnDef<RoleMenuDv, Boolean> colStatus = new ColumnDef<RoleMenuDv, Boolean>(
			ColumnType.CHECK, "Stat", "40px", "36px") {

		@Override
		public Boolean getDataValue(RoleMenuDv data) {
			return data.getStatus();
		}
	};

	ColumnDef<RoleMenuDv, String> colName = new ColumnDef<RoleMenuDv, String>(
			ColumnType.LABEL, "Nama") {

		@Override
		public String getDataValue(RoleMenuDv data) {
			return data.getName();
		}
	};

	public RoleMenuEditorTable() {
		addColumn(colNr);
		colStatus.setCheckBoxHandler(new ColumnValue<RoleMenuDv>() {

			@Override
			public void setDataValue(RoleMenuDv data) {
				data.setStatus(isChecked());
			}
		});
		addColumn(colStatus);
		addColumn(colName);
	}

}
