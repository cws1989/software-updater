# Client Settings #

The client settings is stored in client.xml.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<root>
  <version>1.0.0</version>
  <storage-path>update/</storage-path>
  <information>
    <software>
      <name>Language Files Tool</name>
      <icon>
        <location>folder</location>
        <path>software_icon.png</path>
      </icon>
    </software>
  </information>
  <launch>
    <type>command</type>
    <command>{java}</command>
    <command>-Dfile.encoding=UTF-8</command>
    <command>-jar</command>
    <command>./bin/shing.jar</command>
  </launch>
  <catalog>
    <url>http://localhost/catalog.xml</url>
    <public-key>
      <modulus>009ed8b8a799155ef393eb7be7e4129f1c0d20c7c...</modulus>
      <exponent>010001</exponent>
    </public-key>
  </catalog>
</root>
```

| Tag | Description |
| --- | --- |
| [&lt;version&gt;](#version) | the current version of the software |
| [&lt;storage-path&gt;](#storage-path) | the folder used to store the temporary files generated during patching etc.<br />this folder should only be used by this software updater |
| [&lt;information&gt;](#information) | the title and icon of the launcher/downloader<br />it is optional, if not specified, it will use the default title and icon |
| [&lt;launch&gt;](#launch) | the way to launch the software |
| [&lt;catalog&gt;](#catalog) | the information of the catalog |
| | Some data will be stored by the software updater, see [below](#data-stored-by-software-updater) for details |


## &lt;version&gt; ##

The **&lt;version&gt;** tag contains the version id of the current software. The version id currently accept only [0-9]+(.[0-9]+)*, that is numbers separated by dot(.). The software updater use it to determine which version is newer.


## &lt;storage-path&gt; ##

The downloaded patch(es), files generated during patching, and the file lock used by the launcher and downloader (to ensure only one instance running), will be stored in this folder.

This folder should only be used by this software updater.


## &lt;information&gt; ##

The **&lt;information&gt;** is an optional tag, if not specified, the default title and icon will be used for launcher and downloader.

The **&lt;information&gt;** tag can have 3 kinds of child tag, they are **&lt;software&gt;**, **&lt;launcher&gt;** and **&lt;downloader&gt;**. The title and icon information contained in **&lt;software&gt;** will be used for both the launcher and downloader, those in **&lt;launcher&gt;** will be used for launcher, and **&lt;downloader&gt;** for downloader.

If **&lt;software&gt;** tag exist with other two type of tags, the following priority is applied:
> (**&lt;launcher&gt;** == **&lt;downloader&gt;**) > **&lt;software&gt;**

For example, if **&lt;information&gt;** contains **&lt;software&gt;** and **&lt;launcher&gt;** tags, the launcher will use **&lt;launcher&gt;**, the downloader will use **&lt;software&gt;**.

### Title and icon ###
```xml
...
      <name>Language Files Tool</name>
      <icon>
        <location>folder</location>
        <path>software_icon.png</path>
      </icon>
...
```
The **&lt;name&gt;** tag contains the title, the **&lt;icon&gt;** tag contains both **&lt;location&gt;** and **&lt;path&gt;**.

The **&lt;location&gt;** tag can be either "**folder**" or "**jar**". If it is **folder**, the launcher/downloader will get the icon by  using the **&lt;path&gt;** as the file path. If it is **jar**, the launcher/downloader will use the **&lt;path&gt;** as the resource path, and get the icon directly from the jar (note you have to pack the icon file into the jar file of launcher/downloader).


## &lt;launch&gt; ##

The &lt;launch&gt; tag can have 2 type, one is **command**, one is **jar**.

If the type is **jar**, **&lt;jar-path&gt;** and **&lt;main-class&gt;** are used:
```xml
...
  <launch>
    <type>jar</type>
    <jar-path>./bin/shing.jar</command>
    <main-class>main.Main</command>
  </launch>
...
```
The launcher will use the [URLClassLoader](https://docs.oracle.com/javase/7/docs/api/java/net/URLClassLoader.html) to load the jar, and invoke the main method in the main class specified, all the arguments passed to the launcher will be passed directly to the main method.

If the type is **command**, list of **&lt;command&gt;** are used:
```xml
...
  <launch>
    <type>command</type>
    <command>{java}</command>
    <command>-Dfile.encoding=UTF-8</command>
    <command>-jar</command>
    <command>./bin/shing.jar</command>
  </launch>
...
```
The launcher will use [ProcessBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.html) to create a system process, all the **{java}** in the &lt;command&gt; will be replaced by the path of java binary that used to run the launcher.


## &lt;catalog&gt; ##

If you didn't use [encryption](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-do-authentication-on-catalogxml) on the catalog, you can just include the url of the catalog as:
```xml
...
  <catalog>
    <url>http://localhost/catalog.xml</url>
  </catalog>
...
```
If you have used [encryption](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-do-authentication-on-catalogxml) on the catalog, you have to include the cipher key information like:
```xml
...
  <catalog>
    <url>http://localhost/catalog.xml</url>
    <public-key>
      <modulus>009ed8b8a799155ef393eb7be7e4129f1c0d20c7c...</modulus>
      <exponent>010001</exponent>
    </public-key>
  </catalog>
...
```


## Data stored by Software Updater ##

The **&lt;last-updated&gt;** in **&lt;catalog&gt;** stores the last time the downloader downloaded the catalog, the downloader will use **If-Modified-Since** to check if there is any update.

The **&lt;full-pack-only&gt;** in **&lt;catalog&gt;** will only exist if a patching failed (and should have done a revert). It indicates normal patching on current software files will fail, so for subsequent update, only full-pack patch will be accepted (no diff patching so will not fail). After a full-pack patching succeed, &lt;full-pack-only&gt; will be removed and set to accept diff-patching again.

The **&lt;patches&gt;** in **&lt;root&gt;** stores the patches download by the patch downloader, the launcher will apply the patches listed.
