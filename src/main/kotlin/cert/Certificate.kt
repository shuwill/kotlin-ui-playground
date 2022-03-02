package cert

import java.security.cert.X509Certificate
import java.util.Date

data class Certificate(
    val version: Int,
    val serialNumber: String,
    val signAlg: String,
    val subject: String,
    val issuer: String,
    val notAfter: Date,
    val notBefore: Date
) {

    constructor(x509Certificate: X509Certificate) : this(
        version = x509Certificate.version,
        serialNumber = x509Certificate.serialNumber.toString(16),
        signAlg = x509Certificate.sigAlgName,
        subject = x509Certificate.subjectX500Principal.toString(),
        issuer = x509Certificate.issuerX500Principal.toString(),
        notAfter = x509Certificate.notAfter,
        notBefore = x509Certificate.notBefore
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Certificate

        if (serialNumber != other.serialNumber) return false

        return true
    }

    override fun hashCode(): Int {
        return serialNumber.hashCode()
    }

    override fun toString(): String {
        return "version=$version, serialNumber=$serialNumber, signAlg=$signAlg, subject=$subject, issuer=$issuer, notAfter=$notAfter, notBefore=$notBefore"
    }
}