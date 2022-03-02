package cert

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.ByteArrayInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*

fun parserToX509(bytes: ByteArray): X509Certificate? {
    val cf = CertificateFactory.getInstance("X509", BouncyCastleProvider())
    val certificate = cf.generateCertificate(ByteArrayInputStream(bytes))
    return if (certificate is X509Certificate) {
        certificate
    } else null
}

fun parser(bytes: ByteArray): Certificate? = parserToX509(bytes)?.let { Certificate(it) }

fun parserByBase64(base64: String): Certificate? {
    val decode = Base64.getDecoder().decode(base64)
    return parser(decode)
}

fun parserToMapByBase64(base64: String): MutableMap<String, Any> {
    val result = mutableMapOf<String, Any>()
    val certificate = parserByBase64(base64)
    val (version, serialNumber, signAlg, subject, issuer, notAtfer, notBefore) = certificate!!
    result["Version"] = version
    result["SerialNumber"] = serialNumber
    result["SignAlg"] = signAlg
    result["Subject"] = subject
    result["Issuer"] = issuer
    result["NotBefore"] = notBefore
    result["NotAfter"] = notAtfer
    return result
}