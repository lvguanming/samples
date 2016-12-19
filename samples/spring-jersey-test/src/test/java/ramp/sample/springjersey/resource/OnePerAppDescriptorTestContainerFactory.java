package ramp.sample.springjersey.resource;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

public class OnePerAppDescriptorTestContainerFactory implements TestContainerFactory {
	private static final ConcurrentMap<AppDescriptor, TestContainer> cache = new ConcurrentHashMap<AppDescriptor, TestContainer>();

	private final TestContainerFactory tcf;

	public OnePerAppDescriptorTestContainerFactory(TestContainerFactory tcf) {
		this.tcf = tcf;
	}

	@Override
	public Class<? extends AppDescriptor> supports() {
		return tcf.supports();
	}

	@Override
	public TestContainer create(URI baseUri, AppDescriptor ad) {
		if (cache.get(ad) == null) {
			cache.putIfAbsent(ad, tcf.create(baseUri, ad));
		}
		return cache.get(ad);
	}
}