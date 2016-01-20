package com.teamtrack.cache;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class CustomCache {

	private static final CustomCache customCache = new CustomCache();
	private Cache<Object, Object> cache;
	
	private CustomCache(){
		EmbeddedCacheManager manager = new DefaultCacheManager();
		manager.defineConfiguration("custom-cache", new ConfigurationBuilder().eviction().strategy(EvictionStrategy.LRU).maxEntries(10).build());
		cache = manager.getCache("custom-cache");
	}

	public Cache<Object, Object> getCache() {
		return cache;
	}
	
	public static CustomCache getCacheInstance(){
		return customCache;
	} 

}
