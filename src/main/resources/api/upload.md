### 1. 文件上传
url: /upload
method: POST

### 2. 文件下载[小胡子：js多文件下载](https://github.com/barretlee/javascript-multiple-download)
js实现 已知目录下的文件下载
/views/html/files.html

### 3. Spring实现文件下载
前端上送文件名称，后台检查文件是否存在，存在的提供下载。

1. 文件list
url: /download

2. 文件下载
url: download/file?filename=${v.key}
${v.key} 包含了文件相对路径




