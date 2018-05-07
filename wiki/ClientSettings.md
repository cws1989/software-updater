# Client Settings #

The client settings is stored in client.xml.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<root>
  <version>1.0.0</version>
  <storage-path>update/</storage-path>
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
| &lt;version&gt; | the current version of the software |
| &lt;storage-path&gt; | the folder used to store the temporary files generated during patching etc.. This folder should be empty and only be used by this software updater |
| [&lt;launch&gt;](#launch) | the way to launch the software |
| [&lt;catalog&gt;](#catalog) | the information of the catalog |

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
The launcher will use the [URLClassLoader](https://docs.oracle.com/javase/7/docs/api/java/net/URLClassLoader.html) to load the jar, and invoke the main method in the main class specified, all the arguments passed into the launcher will be passed directly into the main method.

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
If you have used [encryption](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-do-authentication-on-catalogxml) on the catalog, you have to include the information like:
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
