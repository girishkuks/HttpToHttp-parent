/**
 * 
 */
package com.anz.HttpToHttp.transform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anz.HttpToHttp.transform.pojo.NumbersInput;
import com.anz.common.compute.ComputeInfo;
import com.anz.common.transform.ITransformer;
import com.anz.common.transform.TransformUtils;


/**
 * @author sanketsw
 * 
 */
public class TransformBLSample implements ITransformer<String, String> {
	
	private static final Logger logger = LogManager.getLogger();

	
	/* (non-Javadoc)
	 * @see com.anz.common.transform.IJsonJsonTransformer#execute(java.lang.String)
	 */
	public String execute(String inputJson, Logger appLogger, ComputeInfo metadata) throws Exception {
		
		logger.info("{}: Request: {}", this.getClass().getName(), inputJson);
		
		NumbersInput json = (NumbersInput) TransformUtils.convertFromXml(inputJson,
				NumbersInput.class);
		
		logger.info("numbersinput = {}", json.toString());
		
		json.setLeft(json.getLeft() + 100);
		json.setRight(json.getRight() + 100);
		
		String out = TransformUtils.toJSON(json);
		return out;
		
	}


}
