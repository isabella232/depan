/*
 * Copyright 2016 The Depan Project Authors
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

package com.google.devtools.depan.graph_doc.eclipse.ui.registry;

import com.google.devtools.depan.platform.PlatformLogger;
import com.google.devtools.depan.platform.plugin.ContributionEntry;
import com.google.devtools.depan.platform.plugin.ContributionRegistry;

import com.google.common.collect.Maps;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import java.util.Map;

/**
 * @author Lee Carver
 */
public class FromGraphDocRegistry extends
    ContributionRegistry<FromGraphDocContributor> {

  /**
   * Extension point name for persistence configuration
   */
  public final static String EXTENTION_POINT =
      "com.google.devtools.depan.graph_doc.eclipse.ui.registry.from_graph_doc";

  /**
   * Static instance. This class is a singleton.
   * It is created lazily on first access.
   */
  private static FromGraphDocRegistry INSTANCE = null;

  static class Entry extends ContributionEntry<FromGraphDocContributor>{

    public Entry(String bundleId, IConfigurationElement element) {
      super(bundleId, element);
    }

    @Override
    protected FromGraphDocContributor createInstance() throws CoreException {
      return (FromGraphDocContributor) buildInstance(ATTR_CLASS);
    }
  }

  /**
   * Singleton class: private constructor to prevent instantiation.
   */
  private FromGraphDocRegistry() {
  }

  @Override
  protected ContributionEntry<FromGraphDocContributor> buildEntry(
      String bundleId, IConfigurationElement element) {
    return new Entry(bundleId, element);
  }

  @Override
  protected void reportException(String entryId, Exception err) {
    PlatformLogger.logException(
        "Relation registry load failure for " + entryId, err);
  }

  /////////////////////////////////////
  // Project Relations

  public Map<String, FromGraphDocContributor> getContributionMap() {
    Map<String, FromGraphDocContributor> result = Maps.newHashMap();
    for (ContributionEntry<FromGraphDocContributor> contrib : getContributions()) {
      FromGraphDocContributor fromGraphDoc = contrib.getInstance();
      result.put(fromGraphDoc.getLabel(), fromGraphDoc);
    }

    return result ;
  }

  /////////////////////////////////////
  // Singleton access methods

  /**
   * Provide the {@code SourcePluginRegistry} singleton.
   * It is created lazily when needed.
   */
  public static synchronized FromGraphDocRegistry getInstance() {
    if (null == INSTANCE) {
      INSTANCE = new FromGraphDocRegistry();
      INSTANCE.load(EXTENTION_POINT);
    }
    return INSTANCE;
  }

  public static Map<String, FromGraphDocContributor> getRegistryContributionMap() {
    return getInstance().getContributionMap();
  }
}
