/***
 * ASM: a very small and fast Java bytecode manipulation framework
 * Copyright (c) 2000,2002,2003 INRIA, France Telecom
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

package org.objectweb.asm.util;

import org.objectweb.asm.AnnotationVisitor;

/**
 * An {@link AnnotationVisitor} that prints a disassembled view of the
 * annotations it visits.
 *
 * @author Eric Bruneton
 */

public class TraceAnnotationVisitor extends AbstractVisitor
  implements AnnotationVisitor
{

  /**
   * Constructs a new {@link TraceAnnotationVisitor}.
   */

  public TraceAnnotationVisitor () {
  }

  // --------------------------------------------------------------------------
  // Implementation of the AnnotationVisitor interface
  // --------------------------------------------------------------------------

  public void visit (final String name, final Object value) {
    buf.setLength(0);
    if (name != null) {
      buf.append(name).append('=');
    }
    buf.append(value);
    text.add(buf.toString());
  }

  public void visitEnum (
    final String name,
    final String desc,
    final String value)
  {
    buf.setLength(0);
    if (name != null) {
      buf.append(name).append('=');
    }
    appendDescriptor(desc);
    buf.append('.').append(value);
    text.add(buf.toString());
  }

  public AnnotationVisitor visitAnnotation (
    final String name,
    final String desc)
  {
    buf.setLength(0);
    if (name != null) {
      buf.append(name).append('=');
    }
    buf.append('@');
    appendDescriptor(desc);
    buf.append('(');
    text.add(buf.toString());
    TraceAnnotationVisitor tav = new TraceAnnotationVisitor();
    text.add(tav.getText());
    text.add(")");
    return tav;
  }

  public AnnotationVisitor visitArray (final String name) {
    buf.setLength(0);
    if (name != null) {
      buf.append(name).append('=');
    }
    buf.append('{');
    text.add(buf.toString());
    TraceAnnotationVisitor tav = new TraceAnnotationVisitor();
    text.add(tav.getText());
    text.add("}");
    return tav;
  }

  public void visitEnd () {
  }

  /**
   * Appends an internal name, a type descriptor or a type signature to 
   * {@link #buf buf}.  
   * 
   * @param desc an internal name, type descriptor, or type signature. May be 
   *      <tt>null</tt>.
   */

  protected void appendDescriptor (final String desc) {
    buf.append(desc);
  }
}