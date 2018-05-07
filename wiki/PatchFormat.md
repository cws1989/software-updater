# Patch Format #

The following is the file format of the patch file.

| byte | Description |
| --- | --- |
| 5 | the header of the patch file, with ASCII characters "PATCH" |
| 1 | the compression method, 0 using GZIP, 1 using LZMA2 |
| | (the following is compressed using the above mentioned compression method) |
| 3 | the size of the [patch.xml](https://github.com/cws1989/software-updater/blob/master/wiki/PatchXml.md) file, maximum size is 16MB (before compression) |
| ~ | the [patch.xml](https://github.com/cws1989/software-updater/blob/master/wiki/PatchXml.md) file |
| to end | the files listed in patch.xml, one by one in order |
