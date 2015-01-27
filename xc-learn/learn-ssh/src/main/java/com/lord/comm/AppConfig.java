package com.lord.comm;


/**
 * 系统所有的配置
 * @author lord
 *
 */
public class AppConfig {
	public static CommConfig commConfig;

	public static CommConfig getCommConfig() {
		return commConfig;
	}

	public void setCommConfig(CommConfig commConfig) {
		AppConfig.commConfig = commConfig;
	}
	
}
