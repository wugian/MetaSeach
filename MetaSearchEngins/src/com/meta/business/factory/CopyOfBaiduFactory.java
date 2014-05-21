package com.meta.business.factory;

import com.meta.business.intertace.IFactory;
import com.meta.business.intertace.IParser;

public class CopyOfBaiduFactory implements IFactory {

	public IParser produce() {
		return new CopyOfBaiduParser();
	}

}
