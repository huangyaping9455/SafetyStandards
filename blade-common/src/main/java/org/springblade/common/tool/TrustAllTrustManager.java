/**
 * Copyright (C), 2015-2021
 * FileName: TrustAllTrustManager
 * Author:   呵呵哒
 * Date:     2021/4/25 12:22
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2021/4/25
 * @描述
 */
public class TrustAllTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
		throws java.security.cert.CertificateException {
		return;
	}

	@Override
	public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
		throws java.security.cert.CertificateException {
		return;
	}

}


