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

package com.google.devtools.depan.graph.basic;

import com.google.devtools.depan.graph.api.DirectedRelation;
import com.google.devtools.depan.graph.api.Relation;

/**
 * @author ycoppel@google.com (Yohann Coppel)
 *
 */
public class BasicDirectedRelation implements DirectedRelation {

  protected final Relation relation;
  protected boolean forward = false;
  protected boolean backward = false;

  public BasicDirectedRelation(final Relation relation) {
    this.relation = relation;
  }
  
  public BasicDirectedRelation(final Relation relation,
      final boolean forward, final boolean backward) {
    this.relation = relation;
    this.forward = forward;
    this.backward = backward;
  }

  @Override
  public Relation getRelation() {
    return relation;
  }

  @Override
  public boolean matchBackward() {
    return backward;
  }

  @Override
  public boolean matchForward() {
    return forward;
  }

  @Override
  public void setMatchBackward(boolean matches) {
    this.backward = matches;
  }

  @Override
  public void setMatchForward(boolean matches) {
    this.forward = matches;
  }
}
