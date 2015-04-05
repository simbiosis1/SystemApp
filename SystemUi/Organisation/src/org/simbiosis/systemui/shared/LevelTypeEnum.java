package org.simbiosis.systemui.shared;

public enum LevelTypeEnum {
	SUPERADMIN, DIREKTUR, MANAGER_PUSAT, AUDIT_PUSAT, KACAB, SUPERVISOR, USER;
	
	public static String valueToString(int type) {
		switch (type) {
		case 1:
			return "SUPERADMIN";
		case 2:
			return "DIREKTUR";
		case 3:
			return "MANAGER PUSAT";
		case 4:
			return "AUDIT PUSAT";
		case 5:
			return "KACAB";
		case 6:
			return "SUPERVISOR";
		case 7:
			return "USER";
		}
		return "";
	}
}
