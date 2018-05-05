// Copyright (c) 2012 Chan Wai Shing
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
package updater.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 * The titled panel for common use.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class JTitledPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  /**
   * The title label in {@link #headerPanel}.
   */
  protected JLabel headerTitle;
  /**
   * Panels.
   */
  protected JPanel headerPanel;
  protected JPanel contentPanel;
  protected JPanel footerPanel;
  /**
   * Panels with border.
   */
  protected JPanel headerBorderPanel;
  protected JPanel footerBorderPanel;

  public JTitledPanel() {
    super();
    titledPanelInitialize();
  }

  public JTitledPanel(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
    titledPanelInitialize();
  }

  public JTitledPanel(LayoutManager layout) {
    super(layout);
    titledPanelInitialize();
  }

  public JTitledPanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
    titledPanelInitialize();
  }

  /**
   * Initialize the titled panel.
   */
  protected final void titledPanelInitialize() {
    setLayout(new BorderLayout());

    //<editor-fold defaultstate="collapsed" desc="header">
    headerTitle = new JLabel();
    headerTitle.setIconTextGap(8);
    headerTitle.setFont(headerTitle.getFont().deriveFont(20F));

    headerPanel = new JPanel();
    headerPanel.setBorder(new EmptyBorder(10, 10, 7, 10));
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
    headerPanel.add(headerTitle);
    headerPanel.add(Box.createHorizontalGlue());

    headerBorderPanel = new JPanel();
    headerBorderPanel.setLayout(new BorderLayout());
    headerBorderPanel.add(headerPanel, BorderLayout.CENTER);
    headerBorderPanel.add(new JSeparator(), BorderLayout.SOUTH);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="content">
    contentPanel = new JPanel();
    contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="footer">
    footerPanel = new JPanel();
    footerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));

    footerBorderPanel = new JPanel();
    footerBorderPanel.setLayout(new BorderLayout());
    footerBorderPanel.add(new JSeparator(), BorderLayout.NORTH);
    footerBorderPanel.add(footerPanel, BorderLayout.CENTER);
    //</editor-fold>

    add(headerBorderPanel, BorderLayout.NORTH);
    add(contentPanel, BorderLayout.CENTER);
    add(footerBorderPanel, BorderLayout.SOUTH);
  }

  /**
   * Set the text of the header title label.
   * @param title the title
   * @param icon the icon
   */
  public void setTitle(String title, Icon icon) {
    headerTitle.setText(title);
    headerTitle.setIcon(icon);
  }

  /**
   * Set the visibility of the footer panel.
   * @param visible true to set visible, false to set invisible
   */
  public void setFooterPanelVisibility(boolean visible) {
    if (visible) {
      if (getComponents().length == 2) {
        add(footerBorderPanel, BorderLayout.SOUTH);
      }
    } else {
      remove(footerBorderPanel);
    }
  }

  /**
   * Get the header title label.
   * @return the label
   */
  public JLabel getHeaderTitle() {
    return headerTitle;
  }

  /**
   * Get the header panel.
   * @return the panel
   */
  public JPanel getHeaderPanel() {
    return headerPanel;
  }

  /**
   * Get the content panel.
   * @return the panel
   */
  public JPanel getContentPanel() {
    return contentPanel;
  }

  /**
   * Get the footer panel.
   * @return the panel
   */
  public JPanel getFooterPanel() {
    return footerPanel;
  }
}
