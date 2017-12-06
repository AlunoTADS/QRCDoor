package br.ufpr.qrcdoor.crypt;

import java.math.BigInteger;

public class Rsa {

    private final static BigInteger ONE = new BigInteger("1");

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public Rsa() {
        BigInteger p = new BigInteger("a85124be030ed7ba94a0d192369bcaef", 16);
        BigInteger q = new BigInteger("8c6a6237c35f5479e7e6c0905a8b1b77", 16);
        BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");
        privateKey = publicKey.modInverse(phi);
    }

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    @Override
    public String toString() {
        String s = "";
        s += "public  = " + publicKey.toString(16) + "\n";
        s += "private = " + privateKey.toString(16) + "\n";
        s += "modulus = " + modulus.toString(16);
        return s;
    }

    public static void main(String[] args) {
        Rsa rsa = new Rsa();
        System.out.println(rsa);

        // create random message, encrypt and decrypt
        BigInteger message = new BigInteger("Message".getBytes());

        BigInteger encrypt = rsa.encrypt(message);
        BigInteger decrypt = rsa.decrypt(encrypt);
        System.out.println("message   = " + new String(message.toByteArray()));
        System.out.println("encrypted = " + encrypt.toString(16));
        System.out.println("decrypted = " + new String(decrypt.toByteArray()));
    }
}
