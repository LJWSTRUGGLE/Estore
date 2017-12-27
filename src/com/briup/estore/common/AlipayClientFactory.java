package com.briup.estore.common;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayClientFactory {
	/**
	 * 支付宝网关 (固定)
	 */
	private static final String serverUrl = "https://openapi.alipaydev.com/gateway.do"; 
	/**
	 * APPID 即创建应用后生成
	 */
	private static final String appId = "2016081600255419"; 
	/**
	 * 开发者私钥，由开发者自己生成
	 */
	private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfWrRi5U5V8tkyfR+3KDHTPT39iKIePepAdUYdbkQaGol8QVbyLadoL+8bG8iex2SqzHOp1HrJzcmwk79ncNdHl2+3dVqTGYtB5NLzkDULfxjGSbR8CAo4cut00QILRnNIeOfgr7SjyHV5xdR/j56GjfKRqKYK2fFM4fmWQYcinq5Ya4YrimPyACZ2asKDS64mXJY6PxBWXTM3MLB/UaZatJmHUfxv3XiKhA6wDxApNYjfGKZGcOd+tpfAhXLspTr/2m036lFPXhasapR/QjguiV2pI07/Iy4/DzPtCu+U6x/XHaSF5iNlk17W8slJ/+IMSJRYhM9oBrHygJaxUETrAgMBAAECggEAR8iCTbJh8VGuCZJXwRf4KeIClL0B2oGljlfFt+g+AUxG9pQwaHOsX8CIAzwmyW+kp1PmHnC6x0FOQ56qsBwivfs3tfrFTFJLaul7JYCmhIFCVcBJ7z1n7TnHWx3zv8SClfjw60P7KXRZhvtY2AWIMbCrvnWsJmM60ZlR5x+pkiwWFJmf6dxw4jNDlCsjgr5WCooRKs1A0g2LlGQZllOnlwYhkOCaw8LVulxnfijbj3cgzEUdU3j31izgjrnLR0y7ylqAxiuG683NMSPrL74qz9tpzgbxVIrgTYVmKGPU4ZbweIxCSMNYHh5smUARQNODL8tiXBT5p0rieIE0wEDVsQKBgQDfZM8xPtLp6i7u8r9ATx6lfUvdVxkIuHqyODjmecRy75wyN7ScqR6tcbbG4tLqKJriyl/6muR6nLwN86wvfwWI+DWq2I/1EiYop/UVFz+VJ2UTzHlkdV4dPwsyaBsY+n28PB/CRQmgZweWlF8/fOODYP4ABCkTuxtSX+bIcCqMHwKBgQC2nQl3fmUS2fp6TqD78+MB0dZ4OZ36KiJqgK3SSmR671r+3NrJ7f73hO8v8z9tv18A/4drg2WeIH51yjExPkb+bwXNCFctVQYucEDgTfd7AAMHD8Req91FGFDWlHEMyavSx0v43BoN6pjM9nyfspqk4SvLVCxfJfOYkSoE6C5ttQKBgCPrp805Vty6BTXy3oHOtxTRIuPCX4ohb8k8dRnKNqZslOcWQfp2zzIAeO1eB6ATS2RwOiKnyVlV3n830VYJzLALiUWhcPRtKKmc6DGt/VFUdlJKrLJUlP+K5ZgDWWgYzyL3X/vy8etS5ct2rCIfSM4VSIJkKN69VVVXilpsfsrtAoGBAJgkI4P7uSfIlceahMMFAWc9tQp7LmQDAKNsy+S8+BP4IP7fcCT2dQsxJLn1Vi4QV42UVvW7BkmE1vc52lwlf65xPwsAXOk2wiykd1ipfHtsmhkjCL67P2FWjdsI11sJFm9IfcI7VjWr81woK9I9Ulv2Aa+oVEN9cgL1RmXKLwxVAoGAOS6rPZk4uOcQ/gVMHYToQmJQhmbE1KY81enIt136mic75Vn/pqy2sLWAZkSkknHrdIXvCPb8KgreuTcuPuEUeUBX6sYkPhIe9CwIdAOeiixZBJ4/RuYUBblmvNPpWP/Fb94epGfwJ9h4wW8kU0qjVOMTYgS1JjylHupPLnZdiDw=";
	/**
	 * 	参数返回格式，只支持json json（固定）
	 */
	private static final String format = "json";
	/**
	 * 编码集，支持GBK/UTF-8
	 */
	public static final String charset = "utf-8";
	/**
	 * 支付宝公钥，由支付宝生成
	 */
	public static final String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA19tS+o4No+X+uBlM5gsRnCt8nyGKtcFsoIJkjpyuwP5Ach4racooXLX8LvyGK7rj8Wuwr/nP4PKKfZxdzXGtSSxqdWoCCAqh81gOy4zeH8juBlBQdRcLt6+MnrnfDaHxSPCq4UwTEk4KwWQ8fHKUg8gYteQksbnOsvMn0cUe/hukSrOS2fbQZRqz9GLe1bqSTxqcpt+4jI2V4UAEqMK3vJDJp4lzb9PTJdphdsOxFyW6eeZU0s2WA9DXyUajX+tSer8BDAYtwIgy6EeNi9PIfaOPIXibhifuZemPgmxTkwR+CO5F3g4TqOM114k4d+I4aMed/8RklYZPwqzyPnqOQQIDAQAB";
	/**
	 * 生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	public static final String signType = "RSA2";
	
	/**
	 * alipayClient只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象。 
	 * @return
	 */
	public static AlipayClient getAlipayClient() {
		return AlipatClientSingletonFactory.alipayClient;
	}
	
	private static class AlipatClientSingletonFactory{
		public static final AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format,
				charset, alipayPulicKey, signType);
	}
}
