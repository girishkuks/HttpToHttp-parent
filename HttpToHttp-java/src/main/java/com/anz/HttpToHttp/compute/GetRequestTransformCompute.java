/**
 * 
 */
package com.anz.HttpToHttp.compute;

import com.anz.HttpToHttp.transform.PreTransformBLSample;
import com.anz.common.compute.ComputeInfo;
import com.anz.common.compute.impl.CommonBlobTransformCompute;
import com.anz.common.compute.impl.ComputeUtils;
import com.anz.common.transform.ITransformer;
import com.ibm.broker.config.proxy.ConfigManagerProxyLoggedException;
import com.ibm.broker.config.proxy.ConfigManagerProxyPropertyNotInitializedException;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessageAssembly;

/**
 * @author sanketsw
 *
 */
public class GetRequestTransformCompute extends CommonBlobTransformCompute {

	/* (non-Javadoc)
	 * @see com.anz.common.compute.impl.CommonJsonJsonTransformCompute#getTransformer()
	 */
	@Override
	public ITransformer<String, String> getTransformer() {
		return new PreTransformBLSample();
	}

	@Override
	public void prepareForTransformation(ComputeInfo metadata,
			MbMessageAssembly inAssembly, MbMessageAssembly outAssembly) {
		
		try {
			
			String getURL = (String) ComputeUtils.getFlowProxy("TESTNODE_root", "default", "HttpToHttp-app", "Main")
					.getUserDefinedProperty("HTTP_GET_URL");
			
			logger.info("HTTP_GET_URL = {}", getURL);
			
			ComputeUtils.setElementInTree("GET", outAssembly.getLocalEnvironment() ,"Destination", "HTTP", "RequestLine", "Method");
			ComputeUtils.setElementInTree(getURL, outAssembly.getLocalEnvironment() ,"Destination", "HTTP", "RequestURL");
		
		} catch (Exception e) {
			
			logger.throwing(e);
			
		}
		
	}

}
