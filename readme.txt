注意在java命令中指定-cp 或者-classpath 时候
如 
java -cp lib\beautyeye_lnf.jar exercise.MainFrame 会提示找不到类 因为此时找类的目录已经变成了lib
所以需要在-cp后面添加其他路径
java -cp lib\beautyeye_lnf.jar;. exercise.MainFrame
			      //.表示当前目录也是考虑范围。
