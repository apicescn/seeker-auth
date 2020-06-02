# 1 seeker-auth-client
提供auth相关的基础依赖包


# 2 核心内容

通过内置ScanResultResourceImpl接口的实现，暴露端点给xysy-seeker自动扫描自定义权限注解,Api注解入库

# 3 包引入说明

此客户端已包括Feign的功能，故而引入此client包使用feign是无须再引入seeker-auth-feign包。