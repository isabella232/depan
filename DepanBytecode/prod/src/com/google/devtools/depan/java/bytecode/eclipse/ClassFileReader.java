/*
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.depan.java.bytecode.eclipse;

import com.google.devtools.depan.filesystem.graph.FileElement;
import com.google.devtools.depan.java.bytecode.impl.ClassDepLister;
import com.google.devtools.depan.model.builder.DependenciesListener;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Connect an ASM {@code ClassVisitor} to the bytecode stream.
 * 
 * @author <a href="leeca@google.com">Lee Carver</a>
 */
public class ClassFileReader {

  private static final Logger logger =
      Logger.getLogger(ClassFileReader.class.getName());

  private final ClassAnalysisStats analysisStats;

  /**
   * Construct a reader, using a common stats counter.
   * 
   * @param packageBuilder
   */
  public ClassFileReader(ClassAnalysisStats analysisStats) {
    this.analysisStats = analysisStats;
  }

  /**
   * Process a single class file from either the file system or a jar.
   * 
   * @param builder destination of discovered dependencies
   * @param fileNode file node associated with the contents
   * @param content input stream from .class file.
   */
  public void readClassFile(
      DependenciesListener builder,
      FileElement fileNode,
      InputStream content) {

    ClassDepLister cd = new ClassDepLister(builder, fileNode);
    try {
      ClassReader cr = new ClassReader(content);
      cr.accept(cd, 0);
      analysisStats.incrClassesLoaded();
      return;
    } catch (IOException ioErr) {
      logger.warning(
          "Unable to load class information for " + fileNode.getId());
    } catch (ArrayIndexOutOfBoundsException bndException) {
      logger.info("Unreadable class file for " + fileNode.getId());
    }

    analysisStats.incrClassesFailed();
  }
}
