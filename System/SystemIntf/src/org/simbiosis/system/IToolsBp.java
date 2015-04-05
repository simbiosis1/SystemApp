package org.simbiosis.system;

import java.util.Date;

public interface IToolsBp {
	String generateCode(String prefix, String code, Date date, Integer number);
}
