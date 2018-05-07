# Client Settings #

The client settings is stored in client.xml.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<root>
  <version>1.0.0</version  
  <storage-path> stores the directoryupdate/</storage-path>
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

The **&lt;version&gt;** tag is the current version of the software.  
The **&lt;storage-path&gt;** is the folder used to store the temporary files generated during patching etc.. This folder should be empty and only used by this software updater.  
The **&lt;launch&gt;** tag stores the way to launch the software.  
The **&lt;catalog&gt;** tag stores the url to get the catalog.  

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
The launcher will use the java.net.URLClassLoader to load the jar, and invoke the main method in the main class specified, all the arguments passed into the launcher will be passed directly into the main method.

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
The launcher will use java.lang.ProcessBuilder to create a system process, all the **{java}** in the &lt;command&gt; will be replaced by the path of java binary.

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
