package com.muzhi.model.state;

import java.util.ArrayList;
import java.util.List;

import com.muzhi.model.vo.Build;

public class BuildMachine {
	private BuildState noBuildState;
    private BuildState noResourceState;
    private BuildState fillResourceState;
    private BuildState canUpgradeState;
    private BuildState upgradeState;
    private BuildState accelerateState;
    private BuildState canQuitState;
//    private BuildState accelerateUpgradeState;
//    private BuildState
    
    private BuildState state; //机器的当前状态
//    private List<BuildState> nextState;// 可以执行的下一状态 
    
    private Build build;

	public BuildMachine(Build build, Integer type) {
		super();
//		this.noBuildState = new NoBuildState(this);  
//        this.noResourceState = new NoResourceState(this);  
//        this.fillResourceState = new FillResourceState(this);  
//        this.canUpgradeState = new CanUpgradeState(this);  
//        this.upgradeState = new UpgradeState(this);
//      this.accelerateState = new accelerateState();
//        this.nextState = new ArrayList<BuildState>();
        this.build = build;
        
		switch (type) {
		// 餐厅
		case 1: 
//			if (build.getRestaurant() == null ) {
//				this.state = noBuildState;
//			} else if (build.getManorRestaurant().getLefttime() == 0) {
//				this.state = canUpgradeState;
//			} else if (build.getManorRestaurant().getLefttime() > 0) {
//				this.state = upgradeState;
//			} 
		}
	}

	public BuildState getNoBuildState() {
		return noBuildState;
	}

	public void setNoBuildState(BuildState noBuildState) {
		this.noBuildState = noBuildState;
	}

	public BuildState getNoResourceState() {
		return noResourceState;
	}

	public void setNoResourceState(BuildState noResourceState) {
		this.noResourceState = noResourceState;
	}

	public BuildState getFillResourceState() {
		return fillResourceState;
	}

	public void setFillResourceState(BuildState fillResourceState) {
		this.fillResourceState = fillResourceState;
	}

	public BuildState getUpgradeState() {
		return upgradeState;
	}

	public void setUpgradeState(BuildState upgradeState) {
		this.upgradeState = upgradeState;
	}

	public BuildState getState() {
		return state;
	}

	public void setState(BuildState state) {
		this.state = state;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}
    
}
