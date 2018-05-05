#!/bin/bash

WORKING_DIRECTORY=$(pwd)


linkFile() {
  rm $SOFTWARE_DIRECTORY"/$1"
  ln -s -f "$WORKING_DIRECTORY/SoftwareUpdaterCommon/$1" $SOFTWARE_DIRECTORY"/$1"
}

linkDir() {
  rm -r -f $SOFTWARE_DIRECTORY"/$1"
  ln -s -d "$WORKING_DIRECTORY/SoftwareUpdaterCommon/$1" $SOFTWARE_DIRECTORY"/$1"
}


SOFTWARE_DIRECTORY="SoftwareLauncher"

mkdir -p "$SOFTWARE_DIRECTORY/src/org/apache/commons"
linkDir src/org/apache/commons/codec
mkdir -p "$SOFTWARE_DIRECTORY/src"
linkDir src/watne
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/concurrent"
linkFile src/updater/concurrent/ConcurrentLock.java
linkFile src/updater/concurrent/LockType.java
linkFile src/updater/concurrent/LockUtil.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/crypto"
linkFile src/updater/crypto/AESKey.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/gui"
linkFile src/updater/gui/JTitledPanel.java
linkFile src/updater/gui/UpdaterWindow.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/script"
linkFile src/updater/script/Client.java
linkFile src/updater/script/InvalidFormatException.java
linkFile src/updater/script/Patch.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/patch"
linkFile src/updater/patch/Compression.java
linkFile src/updater/patch/OperationType.java
linkFile src/updater/patch/PatchReadUtil.java
linkFile src/updater/patch/PatchRecord.java
linkFile src/updater/patch/LogAction.java
linkFile src/updater/patch/LogReader.java
linkFile src/updater/patch/LogWriter.java
linkFile src/updater/patch/Patcher.java
linkFile src/updater/patch/PatcherListener.java
linkFile src/updater/patch/ReplacementRecord.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/util"
linkFile src/updater/util/CommonUtil.java
linkFile src/updater/util/GetClientScriptResult.java
linkFile src/updater/util/Interruptible.java
linkFile src/updater/util/InterruptibleInputStream.java
linkFile src/updater/util/InterruptibleOutputStream.java
linkFile src/updater/util/Pausable.java
linkFile src/updater/util/SeekableFile.java
linkFile src/updater/util/StreamRedirect.java
linkFile src/updater/util/XMLUtil.java
mkdir -p "$SOFTWARE_DIRECTORY/test/updater"
linkFile test/updater/TestCommon.java


SOFTWARE_DIRECTORY="SoftwarePatchBuilder"

mkdir -p "$SOFTWARE_DIRECTORY/src/org/apache/commons"
linkDir src/org/apache/commons/codec
mkdir -p "$SOFTWARE_DIRECTORY/src"
linkDir src/watne
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/concurrent"
linkFile src/updater/concurrent/ConcurrentLock.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/crypto"
linkFile src/updater/crypto/AESKey.java
linkFile src/updater/crypto/KeyGenerator.java
linkFile src/updater/crypto/RSAKey.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/script"
linkFile src/updater/script/Catalog.java
linkFile src/updater/script/Client.java
linkFile src/updater/script/InvalidFormatException.java
linkFile src/updater/script/Patch.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/patch"
linkFile src/updater/patch/Compression.java
linkFile src/updater/patch/OperationType.java
linkFile src/updater/patch/PatchCreator.java
linkFile src/updater/patch/PatchExtractor.java
linkFile src/updater/patch/PatchReadUtil.java
linkFile src/updater/patch/PatchRecord.java
linkFile src/updater/patch/PatchWriteUtil.java
linkFile src/updater/patch/LogAction.java
linkFile src/updater/patch/LogReader.java
linkFile src/updater/patch/LogWriter.java
linkFile src/updater/patch/PatchPacker.java
linkFile src/updater/patch/Patcher.java
linkFile src/updater/patch/PatcherListener.java
linkFile src/updater/patch/ReplacementRecord.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/util"
linkFile src/updater/util/CommonUtil.java
linkFile src/updater/util/GetClientScriptResult.java
linkFile src/updater/util/Interruptible.java
linkFile src/updater/util/InterruptibleInputStream.java
linkFile src/updater/util/InterruptibleOutputStream.java
linkFile src/updater/util/Pausable.java
linkFile src/updater/util/SeekableFile.java
linkFile src/updater/util/XMLUtil.java
mkdir -p "$SOFTWARE_DIRECTORY/test/updater"
linkFile test/updater/TestCommon.java


SOFTWARE_DIRECTORY="SoftwarePatchDownloader"

mkdir -p "$SOFTWARE_DIRECTORY/src/updater/concurrent"
linkFile src/updater/concurrent/ConcurrentLock.java
linkFile src/updater/concurrent/LockType.java
linkFile src/updater/concurrent/LockUtil.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/gui"
linkFile src/updater/gui/JTitledPanel.java
linkFile src/updater/gui/UpdaterWindow.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/script"
linkFile src/updater/script/Catalog.java
linkFile src/updater/script/Client.java
linkFile src/updater/script/InvalidFormatException.java
linkFile src/updater/script/Patch.java
mkdir -p "$SOFTWARE_DIRECTORY/src/updater/util"
linkFile src/updater/util/CommonUtil.java
linkFile src/updater/util/GetClientScriptResult.java
linkFile src/updater/util/DownloadProgressListener.java
linkFile src/updater/util/DownloadProgressUtil.java
linkFile src/updater/util/DownloadResult.java
linkFile src/updater/util/HTTPDownloader.java
linkFile src/updater/util/Interruptible.java
linkFile src/updater/util/Pausable.java
linkFile src/updater/util/XMLUtil.java
mkdir -p "$SOFTWARE_DIRECTORY/test/updater"
linkFile test/updater/TestCommon.java
