# Patch Format #

The following is the file format of the patch file.

| byte | Description |
| --- | --- |
| 5 | the header of the patch file, with ASCII characters "PATCH" |
| 1 | the compression method, 0 using GZIP, 1 using LZMA2 |
| | (the following is compressed using the above mentioned compression method) |
| 3 | the size of the patch.xml file, maximum size is 16MB |
| to end | the files listed in patch.xml, one by one in order |
