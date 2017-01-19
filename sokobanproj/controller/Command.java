package controller;

import java.util.List;

public abstract class Command implements Commands{
	protected List<String> params;

	public void setParams(List<String> params) {
		this.params = params;	}

	public abstract void execute();
}
