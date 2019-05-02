package org.example.i18n;

import org.osgi.service.component.annotations.*;

@Component
public class SimpleLogServiceImpl implements SimpleLogService {

	@Override
	public void log(String message) {
		System.out.println(message);
	}
	
}
