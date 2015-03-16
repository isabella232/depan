/*
 * Copyright 2008 The Depan Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.devtools.depan.eclipse.utils;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * Reverse the collation order for an existing comparator.
 *
 * @author <a href="leeca@google.com">Lee Carver</a>
 */
public class InverseSorter extends ViewerSorter {

  private ViewerComparator sorter;

  public InverseSorter(ViewerComparator sorter) {
    this.sorter = sorter;
  }

  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {
    return - sorter.compare(viewer, e1, e2);
  }
}