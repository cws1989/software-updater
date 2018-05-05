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
package updater.downloader;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import updater.concurrent.ConcurrentLock;
import updater.concurrent.LockType;
import updater.concurrent.LockUtil;
import updater.gui.UpdaterWindow;
import updater.script.Catalog;
import updater.script.Client;
import updater.script.Client.Information;
import updater.script.InvalidFormatException;
import updater.script.Patch;
import updater.util.GetClientScriptResult;

/**
 * This is a software patch downloader.
 * It can be used separately with software launcher, although they share many 
 * things and can be combined in a single jar.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SoftwarePatchDownloader {

  private static final Logger LOG = Logger.getLogger(SoftwarePatchDownloader.class.getName());

  protected SoftwarePatchDownloader() {
  }

  /**
   * Dispose the window
   * @param window the window to dispose, accept null
   */
  protected static void disposeWindow(Window window) {
    if (window != null) {
      window.setVisible(false);
      window.dispose();
    }
  }

  /**
   * Main function of main class.
   * @param args the first argument (if any) indicate the location of the 
   * client script ({@link updater.script.Client})
   */
  public static void main(String[] args) {
    // set look & feel
    try {
      Util.setLookAndFeel();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, null, ex);
    }

    // get the client script
    GetClientScriptResult result = null;
    try {
      result = Util.getClientScript(args.length > 0 ? args[0] : null);
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(null, "Fail to load config file.");
      return;
    } catch (InvalidFormatException ex) {
      LOG.log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(null, "Config file format invalid.");
      return;
    }


    // check and download patches if any
    final Client clientScript = result.getClientScript();
    final File clientScriptFile = new File(result.getClientScriptPath());
    Information clientInfo = clientScript.getInformation();

    // check if there are patches downloaded and not be installed yet
    if (!clientScript.getPatches().isEmpty()) {
      // You have to restart the application to to install the update.
      JOptionPane.showMessageDialog(null, "There are patch(es) downloaded, you have to restart the application to install the update.");
      return;
    }

    // get the icon for GUI (if any specified)
    Image softwareIcon = null;
    Image updaterIcon = null;
    //<editor-fold defaultstate="collapsed" desc="icons">
    if (clientInfo != null) {
      try {
        if (clientInfo.getSoftwareIconLocation() != null) {
          if (clientInfo.getSoftwareIconLocation().equals("jar")) {
            URL resourceURL = PatchDownloader.class.getResource(clientInfo.getSoftwareIconPath());
            if (resourceURL != null) {
              softwareIcon = Toolkit.getDefaultToolkit().getImage(resourceURL);
            } else {
              throw new IOException("Resource not found: " + clientInfo.getSoftwareIconPath());
            }
          } else {
            softwareIcon = ImageIO.read(new File(clientInfo.getSoftwareIconPath()));
          }
        }
        if (clientInfo.getDownloaderIconLocation() != null) {
          if (clientInfo.getDownloaderIconLocation().equals("jar")) {
            URL resourceURL = PatchDownloader.class.getResource(clientInfo.getDownloaderIconPath());
            if (resourceURL != null) {
              updaterIcon = Toolkit.getDefaultToolkit().getImage(resourceURL);
            } else {
              throw new IOException("Resource not found: " + clientInfo.getDownloaderIconPath());
            }
          } else {
            updaterIcon = ImageIO.read(new File(clientInfo.getDownloaderIconPath()));
          }
        }
      } catch (Exception ex) {
        LOG.log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Fail to read images stated in the config file: root->information->software->icon or root->information->downloader->icon.");
        return;
      }
    }
    if (softwareIcon == null) {
      softwareIcon = Toolkit.getDefaultToolkit().getImage(SoftwarePatchDownloader.class.getResource("/software_icon.png"));
    }
    if (updaterIcon == null) {
      updaterIcon = Toolkit.getDefaultToolkit().getImage(SoftwarePatchDownloader.class.getResource("/updater_icon.png"));
    }
    //</editor-fold>

    String softwareName = clientInfo != null && clientInfo.getSoftwareName() != null ? clientInfo.getSoftwareName() : "Software Patches Downloader";
    String downloaderName = clientInfo != null && clientInfo.getDownloaderName() != null ? clientInfo.getDownloaderName() : "Patches Downloader";

    // GUI
    // record the current thread, for the use of following action listener triggered by swing dispatching to interrupt this thread
    final Thread currentThread = Thread.currentThread();
    final UpdaterWindow updaterGUI = new UpdaterWindow(softwareName, softwareIcon, downloaderName, updaterIcon);
    final JFrame updaterFrame = updaterGUI.getGUI();
    updaterGUI.addListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // user press cancel, ask for confirmation
        Object[] options = {"Yes", "No"};
        int result = JOptionPane.showOptionDialog(updaterFrame, "Are you sure to cancel download?", "Canel Download", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (result == 0) {
          updaterGUI.setCancelEnabled(false);
          currentThread.interrupt();
        }
      }
    });
    updaterGUI.setProgress(0);
    updaterGUI.setMessage("Getting patches catalog ...");
    updaterFrame.setVisible(true);

    DownloadPatchesListener downloadPatchesListener = new DownloadPatchesListener() {

      @Override
      public void downloadPatchesPatchDownloaded(Patch patch) throws IOException {
        List<Patch> existingPatches = clientScript.getPatches();
        existingPatches.add(patch);
        clientScript.setPatches(existingPatches);
        try {
          Util.saveClientScript(clientScriptFile, clientScript);
        } catch (Exception ex) {
          throw new IOException(ex);
        }
      }

      @Override
      public void downloadPatchesProgress(int progress) {
        updaterGUI.setProgress(progress);
      }

      @Override
      public void downloadPatchesMessage(String message) {
        updaterGUI.setMessage(message);
      }
    };

    // get catalog and check
    Catalog catalog = null;
    try {
      // check and download the catalog
      catalog = PatchDownloader.getUpdatedCatalog(clientScript);
      if (catalog == null) {
        JOptionPane.showMessageDialog(updaterFrame, "There are no updates available.");
        disposeWindow(updaterFrame);
        return;
      }
    } catch (InvalidFormatException ex) {
      LOG.log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(updaterFrame, "Patches catalog format invalid.");
      disposeWindow(updaterFrame);
      return;
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(updaterFrame, "Error occurred when getting the patches catalog.");
      disposeWindow(updaterFrame);
      return;
    }

    // determine suitable patches to download
    boolean isCatalogFullPackOnly = clientScript.isCatalogFullPackOnly() == null ? false : clientScript.isCatalogFullPackOnly();
    List<Patch> updatePatches = PatchDownloader.getSuitablePatches(catalog, clientScript.getVersion(), isCatalogFullPackOnly);
    if (updatePatches.isEmpty()) {
      // record the last updated time of patches catalog
      clientScript.setCatalogLastUpdated(System.currentTimeMillis());
      try {
        Util.saveClientScript(clientScriptFile, clientScript);
      } catch (Exception ex) {
        // should be a fatal problem but at this stage it is not
        LOG.log(Level.WARNING, null, ex);
      }
      JOptionPane.showMessageDialog(updaterFrame, "There are no updates available.");
      disposeWindow(updaterFrame);
      return;
    }

    try {
      // acquire lock
      ConcurrentLock lock = LockUtil.acquireLock(LockType.DOWNLOADER, new File(clientScript.getStoragePath()), 1000, 50);
      if (lock == null) {
        JOptionPane.showMessageDialog(updaterFrame, "There is another updater running.");
        disposeWindow(updaterFrame);
        return;
      }

      // download patches
      DownloadPatchesResult downloadResult = null;
      try {
        downloadResult = PatchDownloader.downloadPatches(downloadPatchesListener, clientScriptFile, clientScript, updatePatches, 10, 1000);
      } finally {
        lock.release();
      }

      switch (downloadResult) {
        case DOWNLOAD_INTERRUPTED:
          // user cancel
          disposeWindow(updaterFrame);
          break;
        case ERROR:
          JOptionPane.showMessageDialog(updaterFrame, "Error occurred when getting the patch.");
          disposeWindow(updaterFrame);
          break;
        case SAVE_TO_CLIENT_SCRIPT_FAIL:
          JOptionPane.showMessageDialog(updaterFrame, "Error occurred when getting the patch (save to client script).");
          disposeWindow(updaterFrame);
          break;
        case COMPLETED:
          JOptionPane.showMessageDialog(updaterFrame, "Download patches finished.");
          JOptionPane.showMessageDialog(updaterFrame, "You have to restart the application to install the update.");

          clientScript.setCatalogLastUpdated(System.currentTimeMillis());
          try {
            Util.saveClientScript(clientScriptFile, clientScript);
          } catch (Exception ex) {
            // should be a fatal problem but at this stage it is not
            LOG.log(Level.WARNING, null, ex);
          }

          disposeWindow(updaterFrame);
          break;
      }
    } catch (MalformedURLException ex) {
      LOG.log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(updaterFrame, "The URL of the patch is invalid.");
      disposeWindow(updaterFrame);
    }
  }
}
