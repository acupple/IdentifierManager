package org.mokey.mapping.identifiermanager.utils;

import java.util.concurrent.TimeUnit;

import jersey.repackaged.com.google.common.cache.Cache;
import jersey.repackaged.com.google.common.cache.CacheBuilder;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class UAParser {
	private static UserAgentStringParser parser = null;
	private static Cache<String, ReadableUserAgent> cache = null;
	
	static{
		parser = UADetectorServiceFactory
				.getCachingAndUpdatingParser();
		
		cache = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterWrite(2, TimeUnit.HOURS)
				.build();
	}
	
	public static ReadableUserAgent parse(final String userAgentString){
		ReadableUserAgent result = cache.getIfPresent(userAgentString);
		if (result == null) {
			result = parser.parse(userAgentString);
			cache.put(userAgentString, result);
		}
		return result;
	}
	
	public static void shutdown(){
		cache.cleanUp();
		parser.shutdown();
	}
}
