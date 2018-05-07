# Software Updater #

This project was actively developed in 2011, maintained in 2012~2014, stopped in 2014, and picked-up in 2018. Although it is in beta version, it is ready for use.

[Click here to view the detail wiki.](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md)

## Menu ##
  * [Overview](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md)
  * [Simple Example](https://github.com/cws1989/software-updater/blob/master/wiki/SimpleExample.md)
  * [Advanced Tutorial](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md)
  * [Client Settings](https://github.com/cws1989/software-updater/blob/master/wiki/ClientSettings.md)
  * [Patches Catalog](https://github.com/cws1989/software-updater/blob/master/wiki/PatchesCatalog.md)
  * [Patch](https://github.com/cws1989/software-updater/blob/master/wiki/Patch.md)
  * [Flow Details](https://github.com/cws1989/software-updater/blob/master/wiki/FlowDetails.md)
  * [Technical Details](https://github.com/cws1989/software-updater/blob/master/wiki/TechnicalDetails.md)

## Overview ##
Software Updater is a software/library that do software updating. It contains a library that do patch creating, downloading and patching. Besides, there is a GUI interface provided that utilize the library to do software updating.

This software is divided into four parts. They are:
 1. [Builder](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md#31-builder) - create patches, update [catalog](https://github.com/cws1989/software-updater/blob/master/wiki/PatchesCatalog.md) etc.
 1. [Launcher](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md#32-launcher) - do patching and launch the software
 1. [Downloader](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md#33-downloader) - check and download patches from the Internet
 1. [Self-Updater](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md#34-self-updater) - utility used to patch the launcher

Basically self-updater is part of the launcher so there actually contain only three parts. Launcher and downloader will be distributed accompany with your software and builder will reside in your computer. Because the downloader needs to download the patches through the Internet, so you have to prepare an Internet host space to put the patches.

## Features ##

Software Updater is not simple a 'download and replace files' updater, it do much more to make:
 1. Minimize download size
 1. Ensure integrity
 1. Rollback when update failed
 1. Resumable download and update
 1. Block unauthorized access
 1. Minimize garbage files
 1. Minimize the effort to create patches

[Click here for details.](https://github.com/cws1989/software-updater/blob/master/wiki/Overview.md#2-features)

## Simple Example ##
[Click here to read the generic text version.](https://github.com/cws1989/software-updater/blob/master/wiki/SimpleExample.md)

[https://www.youtube.com/watch?v=UfLkDaTIkQs](https://www.youtube.com/watch?v=UfLkDaTIkQs)

## Screenshots ##

### Launcher ###
![Launcher](https://raw.githubusercontent.com/cws1989/software-updater/master/wiki/Launcher.png)

Q&A: [How do I change the titles and icons of the launcher?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-change-the-titles-and-icons-of-the-launcher)

### Patch Downloader ###
![PatchDownloader](https://raw.githubusercontent.com/cws1989/software-updater/master/wiki/PatchDownloader.png)

Q&A: [How do I change the titles and icons of the downloader?](https://github.com/cws1989/software-updater/blob/master/wiki/AdvancedTutorial.md#how-do-i-change-the-titles-and-icons-of-the-downloader)

## Planning ##

### Plans to do in future ###
 1. Support different encryption & checksum method.
 1. Send back error report through web.
 1. Besides providing aes key and iv for patch in [catalog](https://github.com/cws1989/software-updater/blob/master/wiki/PatchesCatalog.md), provide another way to get from the invoker (command line or Java function).
 1. Provide command line interactive for downloader and launcher.
 1. Make GUI for builder.
 1. Execute script before/after upgrading.

## Support & Discussion ##
[Support & Discussion Group](http://groups.google.com/group/software-updater)

## Google Project ##
https://code.google.com/archive/p/software-updater/
