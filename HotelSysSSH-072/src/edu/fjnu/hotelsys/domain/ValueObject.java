/**
 * 
 */
package edu.fjnu.hotelsys.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Administrator
 *
 */
public class ValueObject {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
