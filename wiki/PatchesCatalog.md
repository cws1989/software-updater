# Patches Catalog #

The patches catalog is the catalog file containing the patches information, this file should be host on the internet.

```xml
<patches>
  <patch id="1">
    <version>
      <from>1.0.0</from>
      <to>1.0.1</to>
    </version>
    <download>
      <url>http://localhost/1.0.0_1.0.1.patch</url>
      <checksum>84230577e7d95ce5307484f478b90a2f35a52c742800169aae137f6ab8c1956b</checksum>
      <length>269968</length>
      <encryption>
        <type>AES</type>
        <key>854c60b0d0d819f7f1d33adcce2cce0028db8cd6d85a5c599cb31b3bdb38eb41</key>
        <IV>10414b9876ff876f74ab0739a5a685d9</IV>
      </encryption>
    </download>
  </patch>
</patches>
```

| Tag | Description |
| --- | --- |
| &lt;patch&gt; | each patch should have one &lt;patch&gt;, note the **patch id** is manually set, and should be distinct across all patches, should not reuse the patch id |
| [&lt;version&gt;](#version) | telling what version of the software can apply this patch, and the software version after patching |
| [&lt;download&gt;](#download) | the information about the patch file, url to download, file length, check sum, encryption key |



## &lt;version&gt; ##

There is two way to set here. The first is to use **&lt;from&gt;** and **&lt;to&gt;**, the second is to use **&lt;from-subsequent&gt;** and **&lt;to&gt;**.

**&lt;from&gt;** and **&lt;to&gt;** can be used for full-pack and diff patching, specifing the patch is available only for specific version of software to upgrade to specific version of software.

**&lt;from-subsequent&gt;** and **&lt;to&gt;** can only be used for full-pack patching, specifing software version >= **&lt;from-subsequent&gt;** and version < **&lt;to&gt;** can apply the patch.

## &lt;download&gt; ##

If you didn't use encryption, you can just include like:
```xml
...
    <download>
      <url>http://file.lazysnake.com/u/32/1.0.0_1.0.1.patch</url>
      <checksum>84230577e7d95ce5307484f478b90a2f35a52c742800169aae137f6ab8c1956b</checksum>
      <length>269968</length>
    </download>
...
```

If you have used encryption on patch, you have to include the cipher key like:
```xml
...
    <download>
      <url>http://localhost/1.0.0_1.0.1.patch</url>
      <checksum>84230577e7d95ce5307484f478b90a2f35a52c742800169aae137f6ab8c1956b</checksum>
      <length>269968</length>
      <encryption>
        <type>AES</type>
        <key>854c60b0d0d819f7f1d33adcce2cce0028db8cd6d85a5c599cb31b3bdb38eb41</key>
        <IV>10414b9876ff876f74ab0739a5a685d9</IV>
      </encryption>
    </download>
...
```

[How do I encrypt the patch?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-encrypt-the-patch)

You should also consider encrypting the catalog, see [here](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-do-authentication-on-catalogxml)
