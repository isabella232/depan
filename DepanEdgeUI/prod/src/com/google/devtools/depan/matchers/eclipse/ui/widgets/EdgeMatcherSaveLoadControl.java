/*
 * Copyright 2007 The Depan Project Authors
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

package com.google.devtools.depan.matchers.eclipse.ui.widgets;

import com.google.devtools.depan.matchers.models.GraphEdgeMatcherDescriptor;
import com.google.devtools.depan.matchers.models.MatcherResources;
import com.google.devtools.depan.matchers.persistence.EdgeMatcherDocXmlPersist;
import com.google.devtools.depan.persistence.AbstractDocXmlPersist;
import com.google.devtools.depan.platform.eclipse.ui.widgets.GenericSaveLoadControl;
import com.google.devtools.depan.platform.eclipse.ui.widgets.SaveLoadConfig;
import com.google.devtools.depan.resources.ResourceContainer;

import org.eclipse.swt.widgets.Composite;

/**
 * Control for saving and loading {@link GraphEdgeMatcherDescriptor}s
 * (a.k.a. {@code EdgeMatcherDocument}).  The class defines the type-specific
 * strings and factories for the supplied generic type
 * {@link GraphEdgeMatcherDescriptor}.
 *
 * @author ycoppel@google.com (Yohann Coppel)
 */
public abstract class EdgeMatcherSaveLoadControl
    extends GenericSaveLoadControl<GraphEdgeMatcherDescriptor> {

  public EdgeMatcherSaveLoadControl(Composite parent) {
    super(parent, CONFIG);
  }

  // Only need one.
  private static SaveLoadConfig<GraphEdgeMatcherDescriptor> CONFIG =
      new ControlSaveLoadConfig();

  private static class ControlSaveLoadConfig
      extends SaveLoadConfig<GraphEdgeMatcherDescriptor> {

    @Override
    public ResourceContainer getContainer() {
      return MatcherResources.getContainer();
    }

    @Override
    public AbstractDocXmlPersist<GraphEdgeMatcherDescriptor> getDocXmlPersist(
        boolean readable) {
      return EdgeMatcherDocXmlPersist.build(readable);
    }

    @Override
    public String getSaveLabel() {
      return "Save as EdgeMatcher...";
    }

    @Override
    public String getLoadLabel() {
      return "Load from EdgeMatcher...";
    }

    @Override
    public String getExension() {
      return GraphEdgeMatcherDescriptor.EXTENSION;
    }
  }
}
