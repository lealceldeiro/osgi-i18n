package org.example.i18n.messages;

import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.osgi.service.localization.BundleLocalization;
import org.eclipse.osgi.service.localization.LocaleProvider;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component
public class MessagesProviderImpl implements MessageProvider {
	private BundleLocalization bundleLocalization;
	private LocaleProvider localeProvider;
	private ResourceBundle resourceBundle;

	@Reference
	public void bindBundleLocalization(BundleLocalization bundleLocalization) {
		this.bundleLocalization = bundleLocalization;
	}

	public void unbindBundleLocalization(BundleLocalization bundleLocalization) {
		this.bundleLocalization = null;
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
	public void bindLocaleProvider(LocaleProvider localeProvider) {
		this.localeProvider = localeProvider;
	}
	
	public void unbindLocaleProvider(LocaleProvider localeProvider) {
		this.localeProvider = null;
	}
	

	@Activate
	public void activate() {
		resourceBundle = bundleLocalization.getLocalization(FrameworkUtil.getBundle(getClass()), getLocale());
	}

	@Override
	public String get(String key) {
		return resourceBundle.getString(key);
	}

	private String getLocale() {
		return localeProvider != null ? localeProvider.getLocale().toString() : Locale.getDefault().toString();
	}
}
