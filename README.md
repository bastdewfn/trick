# trick
本地型远程调用框架，可以在公司部门之前使用。可以少写一些基础类

使用： 接口前加注解，自动注入
@Autowired
private IProductFacade productFacade;




配置文件，自建
trick.properties

配置如下
trickbeans=productFacade,testInterface2,currencyService    #可多个， 隔开，  为注入时属性名称

#trick.productFacade(为上面的属性名称)

#className(为接口全类地址)
trick.productFacade.className=com.pfq.test.IProductFacade
#超时时间 毫秒
trick.productFacade.timeout=100
#对应远程地址
trick.productFacade.remoteUrl=http://pdtcfg.paifenlecorp.com/
#getProductList为方法名，remotePath 对应远程地址
trick.productFacade.method.getProductList.remotePath=api/getProductList
trick.productFacade.method.getProductList.httpType=POST
#api/getProduct?code={code}&version={version}   以索引顺序为  {code}为方法内第一个参数值,{version} 为第二个
trick.productFacade.method.getProduct.remotePath=api/getProduct?code={code}&version={version}
trick.productFacade.method.getProduct.httpType=POST


也可直接在接口前加 注解实现映射
@TrickServer("http://baidu.com")
public interface TestInterface1 {
    @TrickMethod(remotePath = "/fristdewfn?abc={abc}")
    void frist(String abc);
}
