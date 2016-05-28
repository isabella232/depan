package com.google.devtools.depan.view_doc.eclipse.ui.wizards;

import com.google.devtools.depan.graph_doc.eclipse.ui.registry.FromGraphDocWizard;
import com.google.devtools.depan.model.GraphNode;
import com.google.devtools.depan.platform.NewEditorHelper;
import com.google.devtools.depan.view_doc.eclipse.ui.editor.ViewEditor;
import com.google.devtools.depan.view_doc.model.GraphModelReference;
import com.google.devtools.depan.view_doc.model.ViewDocument;
import com.google.devtools.depan.view_doc.model.ViewPreferences;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;

public class ViewFromGraphDocWizard extends FromGraphDocWizard {

  private ViewFromGraphDocPage page;

  @Override
  public void addPages() {
    String filename = getName() + '.' + ViewDocument.EXTENSION;
    IContainer defaultContainer = null;
    page = new ViewFromGraphDocPage(defaultContainer, filename);
    addPage(page);
  }

  private String getName() {
    GraphNode node = getTopNode();
    if (null == node) {
      return "Empty Graph";
    }
    String baseName = node.friendlyString();
    int period = baseName.lastIndexOf('.');
    if (period > 0) {
      String segment = baseName.substring(period + 1);
      if (segment.length() > 3) {
        return segment;
      }
    }

    return baseName;
  }

  @Override
  public boolean performFinish() {

    String baseName = NewEditorHelper.newEditorLabel(
        page.getFilename() + " - New View");

    // Create ViewDocument elements
    GraphModelReference graphRef =
        new GraphModelReference(getGraphFile(), getGraphDoc());
    ViewPreferences userPrefs = buildViewPreferences();

    ViewDocument viewInfo = new ViewDocument(graphRef, getNodes(), userPrefs);
    ViewEditor.startViewEditor(viewInfo, baseName);
    return true;
  }

  private ViewPreferences buildViewPreferences() {
    ViewPreferences result = new ViewPreferences();

    // No locations, so initial layout occurs in ViewEditor once viewport
    // is constructed.

    return result;
  }
}
