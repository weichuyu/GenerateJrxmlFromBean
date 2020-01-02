# GenerateJrxmlFromBean
一、根据bean中的注解，创建JasperReport可以读的报表模板文件jrxml  
（一）本工具是代码片段，在idea中直接run就好了，编译出的jar包没有什么用  
（二）ireport5.6测试可以打开生成的jrxml文件，更高版本的ireport没有测试过了  
（三）ireport5.6需要jdk1.7运行（jdk1.8运行ireport会报错），但本项目使用的是jdk1.8编译的
  
二、使用方法  
（一）模仿pojo22.BankFund写自己要生成的模板bean，如果想试试效果可以先跳过这步  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/pojo.PNG)  
（二）修改config.properties  
config.output.path 导出路径  
config.output.package 生成该包名下面所以的模板  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/config.PNG)  
（三）运行AutoGenerateJasperForMemberV1_6.AutoGenerateJasperLoop中的main方法  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/tree.PNG)  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/console.PNG)  
（四）用ireport5.6打开生成的jrxml，作另存为操作  
（直接将生成的jrxml在项目中使用可能会报错，稳妥起见强烈建议用ireport另存为一个新的jrxml）  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/output.PNG)  
 ![image](https://github.com/weichuyu/GenerateJrxmlFromBean/blob/master/sample/img/ireport.PNG)  
 
三、建立自己的模板bean  
（一）在类开头添加注解  
比如：@ExcelHeaderResources(title="清算行资金调拨表查询")  

（二）在字符串域开头添加注解  
比如：@ExcelFiledResources(order = 2, title = "银行名称")  

（三）在Bigdecimal域开头添加注解  
比如：@ExcelFiledResources(order = 4, title = "发生额付出",isNaN=true,mininum=0,maximum=3)  

（四）isNaN只要在域是BigDecimal格式时才有意义；mininum，maximum代表小数点最小最大保留位数  

（五）更多ExcelFiledResources的控制方法见代码本身，现在先不写了。 
