# Patch.xml #

The patch.xml is packed in the patch. It contains the information as mentioned below:
1. the type of the patch, full patch or diff patch
2. the **from**/**from-subsequent** and **to** version, telling what version of the software can apply this patch, and the software version after patching
3. the detail operations needed to do to patch the software (e.g. new file/folder, patch file, delete file/folder), file length and checksum of the old and new file will also be included here
4. a list of checksum of all files of the new version of software (including the files mentioned in 3)
