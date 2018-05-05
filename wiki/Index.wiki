=Software Updater=

<wiki:toc max_depth="3" />

[http://code.google.com/p/java-syntax-highlighter/wiki/Overview Click here to view the detail wiki.]

==Overview==
Software Updater is a software/library that do software updating. It contains a library that do patch creating, downloading and patching. Besides, there is a GUI interface provided that utilize the library to do software updating.

This software is divided into four parts. They are:
 # [Overview#3.1._Builder Builder] - create patches, update [PatchesCatalog catalog] etc.
 # [Overview#3.2._Launcher Launcher] - do patching and launch the software
 # [Overview#3.3._Downloader Downloader] - check and download patches from the Internet
 # [Overview#3.4._Self-Updater Self-Updater] - utility used to patch the launcher

Basically self-updater is part of the launcher so there actually contain only three parts. Launcher and downloader will be distributed accompany with your software and builder will reside in your computer. Because the downloader needs to download the patches through the Internet, so you have to prepare an Internet host space to put the patches.

==Features==

Software Updater is not simple a 'download and replace files' updater, it do much more to make:
 # Minimize download size
 # Ensure integrity
 # Rollback when update failed
 # Resumable download and update
 # Block unauthorized access
 # Minimize garbage files
 # Minimize the effort to create patches

[Overview#2._Features Click here for details.]

==Simple Example==
[SimpleExample Click here to read the generic text version.]

<wiki:video url="http://www.youtube.com/watch?v=RAwjiZDN6rw"/>

==Screenshots==

===Launcher===
[http://software-updater.googlecode.com/svn/wiki/Launcher.png]

Q&A: [AdvancedTutorial#How_do_I_change_the_titles_and_icons_of_the_launcher? How do I change the titles and icons of the launcher?]

===Patch Downloader===
[http://software-updater.googlecode.com/svn/wiki/PatchDownloader.png]

Q&A: [AdvancedTutorial#How_do_I_change_the_titles_and_icons_of_the_downloader? How do I change the titles and icons of the downloader?]

==Planning==

===Plan to do in future===
 # Support different encryption & checksum method.
 # Send back error report through web.
 # Besides providing aes key and iv for patch in [PatchesCatalog catalog], provide another way to get from the invoker (command line or Java function).
 # Provide command line interactive for downloader and launcher.
 # Make GUI for builder.
 # Execute script before/after upgrading.

==Support & Discussion==
[http://groups.google.com/group/software-updater Support & Discussion Group]