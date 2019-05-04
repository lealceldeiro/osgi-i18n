package org.example.i18n;

import org.example.i18n.messages.MessageProvider;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component
public class SimpleLoggingComponent {

	private SimpleLogService simpleLogService;
	private MessageProvider messages;

	@Reference
	public void bindMessageProvider(MessageProvider messageProvider) {
		messages = messageProvider;
	}

	public void unbindMessageProvider(MessageProvider messageProvider) {
		messages = null;
	}

	@Reference
	public void bindLogger(SimpleLogService logService) {
		this.simpleLogService = logService;
	}

	public void unbindLogger(SimpleLogService logService) {
		this.simpleLogService = null;
	}

	@Activate
	public void activate() {
		simpleLogService.log(messages.get("startMessage"));
	}

	@Deactivate
	public void deactivate() {
		simpleLogService.log(messages.get("endMessage"));
	}
}
