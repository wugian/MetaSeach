package com.meta.business.intertace;

import java.util.List;

import com.meta.model.Result;

public interface IParser {
	public List<Result> parsePage(String pageContent);
}
