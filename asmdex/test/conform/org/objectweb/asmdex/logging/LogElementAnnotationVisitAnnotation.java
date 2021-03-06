/* Software Name : AsmDex
 * Version : 1.0
 *
 * Copyright © 2012 France Télécom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.objectweb.asmdex.logging;

/**
 * LogElement for the Visit of a nested Annotation.
 * 
 * @author Julien Névo
 */
public class LogElementAnnotationVisitAnnotation extends LogElement {

	/** The name. */
	protected String name;
	
	/** The desc. */
	protected String desc;
	
	/**
	 * Constructor
	 * @param name
	 * @param desc
	 */
	public LogElementAnnotationVisitAnnotation(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	@Override
	public ElementType getType() {
		return ElementType.TYPE_ANNOTATION_VISIT_ANNOTATION;
	}
	
	@Override
	public boolean isElementEqual(LogElement e) {
		LogElementAnnotationVisitAnnotation a = (LogElementAnnotationVisitAnnotation)e;
		
		// Name can be Null.
		if (name == null) {
			if (a.name != null) {
				return false;
			}
		} else if (!name.equals(a.name)) {
			return false;
		}
		
		return desc.equals(a.desc);
	}

}
