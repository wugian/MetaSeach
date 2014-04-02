package com.meta.business.factory;

import com.meta.business.intertace.IFactory;
import com.meta.business.intertace.IParser;

public class BaiduFactory implements IFactory {

	public IParser produce() {
		return new BaiduParser();
	}

}
