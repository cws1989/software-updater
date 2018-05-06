# Simple Example #

## Introduction ##
In this example, we are going integrate the Software Updater into [Sweet Home 3D](http://www.sweethome3d.com) and make a patch from version 3.2 to 3.3.

## Video ##
[https://www.youtube.com/watch?v=UfLkDaTIkQs](https://www.youtube.com/watch?v=UfLkDaTIkQs)

## Preparation ##
 1. Download the software updater from [here](https://github.com/cws1989/software-updater/releases)

## Integrate the updater to the software ##
 1. Open the software's root folder.
 1. Copy **Launcher.jar** and **PatchDownloader.jar** into it.
 1. Create a new folder named **update** and a file named [client.xml](https://github.com/cws1989/software-updater/blob/master/wiki/ClientSettings.md)
 1. Put the following content into [client.xml](https://github.com/cws1989/software-updater/blob/master/wiki/ClientSettings.md).
```xml
<root>
  <version>{software version}</version>
  <storage-path>update/</storage-path>
  <launch>
    <type>command</type>
    <command>{path to executable}</command>
  </launch>
  <catalog>
    <url>{HTTP URL to the catalog}</url>
  </catalog>
</root>
```
 1. Execute the **Launcher.jar** to see if it can launch your software correctly or not.

### Common Questions ###
 * [How do I launch jar instead of executable?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-launch-jar-instead-of-executable)
 * [How do I use it with Java executable wrapper launch4j, JSmooth etc.)?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-use-it-with-java-executable-wrapper-launch4j-jsmooth-etc)

## Create patches ##
 1. Move the [client.xml](https://github.com/cws1989/software-updater/blob/master/wiki/ClientSettings.md) out from the software folder first, see [here](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#what-kind-of-files-should-not-be-included-in-the-patch) for reason.
 1. Find the build.jar and execute 
```
java -jar build.jar -patch $oldVersionFolder $newVersionFolder --from $currentVersionNumber --to $newVersionNumber --output upgrade.patch
```
 1. Get the SHA256 checksum of the file: 
```
java -jar build.jar -sha256 upgrade.patch -o checksum.txt
```
 1. Create a file named [catalog.xml](https://github.com/cws1989/software-updater/blob/master/wiki/PatchesCatalog.md) and put the following content into it:
```xml
<patches>
  <patch id="1">
    <version>
      <from>{old version number}</from>
      <to>{new version number}</to>
    </version>
    <download>
      <url>{HTTP URL to the patch}</url>
      <checksum>{checksum of the patch from checksum.txt}</checksum>
      <length>{size of the patch (in byte)}</length>
    </download>
  </patch>
</patches>
```
 1. Upload the [catalog.xml](https://github.com/cws1989/software-updater/blob/master/wiki/PatchesCatalog.md) and **upgrade.patch** to your web hosting.

### Common Questions ###
 * [How do I add more patches for other versions?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-add-more-patches-for-other-versions)

## Client update the software ##
 1. Execute the **PatchDownloader.jar** to download the patch.
 1. After the downloads completed, execute the **Launcher.jar** to update and launch the software.

### Common Questions ###
 * [I don't want the client to launch the downloader manually, how do I integrate it into the software?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#i-dont-want-the-client-to-launch-the-downloader-manually-how-do-i-integrate-it-into-the-software)
