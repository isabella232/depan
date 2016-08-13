/*
 * Copyright 2009 The Depan Project Authors
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

package com.google.devtools.depan.matchers.persistence;

import com.google.devtools.depan.matchers.models.GraphEdgeMatcherDescriptor;
import com.google.devtools.depan.persistence.AbstractDocXmlPersist;
import com.google.devtools.depan.persistence.ObjectXmlPersist;
import com.google.devtools.depan.persistence.XStreamFactory;

import java.net.URI;

/**
 * Provide easy to use load and save methods for
 * {@link GraphEdgeMatcherDescriptor}s.
 * 
 * @author <a href="mailto:leeca@google.com">Lee Carver</a>
 */
public class EdgeMatcherDocXmlPersist
    extends AbstractDocXmlPersist<GraphEdgeMatcherDescriptor> {

  private final static EdgeMatcherDocXStreamConfig docConfig =
      new EdgeMatcherDocXStreamConfig();

  public EdgeMatcherDocXmlPersist(ObjectXmlPersist xmlPersist) {
    super(xmlPersist);
  }

  public static EdgeMatcherDocXmlPersist build(boolean readable) {
    ObjectXmlPersist persist = XStreamFactory.build(readable, docConfig);
    return new EdgeMatcherDocXmlPersist(persist);
  }

  /////////////////////////////////////
  // Hook method implementations for AbstractDocXmlPersist

  @Override
  protected GraphEdgeMatcherDescriptor coerceLoad(Object load) {
      return (GraphEdgeMatcherDescriptor) load;
  }

  @Override
  protected String logLoadException(URI uri, Exception err) {
    return logException("Unable to load EdgeMatcher from {0}", uri, err);
  }

  @Override
  public String logSaveException(URI uri, Exception err) {
    return logException("Unable to save EdgeMatcher to {0}", uri, err);
  }
}
