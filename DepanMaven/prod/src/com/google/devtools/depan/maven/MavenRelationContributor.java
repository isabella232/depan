package com.google.devtools.depan.maven;

import com.google.devtools.depan.graph.api.Relation;
import com.google.devtools.depan.graph.registry.RelationContributor;
import com.google.devtools.depan.maven.graph.MavenRelation;

import java.util.Arrays;
import java.util.Collection;

public class MavenRelationContributor implements RelationContributor {

  public static final String LABEL = "Maven";

  public static final String ID =
      "com.google.devtools.depan.java.MavenRelationContributor";

  @Override
  public String getLabel() {
    return LABEL;
  }

  @Override
  public Collection<? extends Relation> getRelations() {
    return Arrays.asList(MavenRelation.values());
  }
}
