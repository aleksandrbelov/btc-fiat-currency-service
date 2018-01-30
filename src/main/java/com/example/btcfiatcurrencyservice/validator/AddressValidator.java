package com.example.btcfiatcurrencyservice.validator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Component;

@Component
public class AddressValidator {

    private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public void validateAddress(String address) throws NoSuchAlgorithmException {
        if (!sha256Validation(address))
            throw new BadRequestException("BTC address is not valid!");
    }

    private static boolean simpleValidation(String address){
        if (address == null) return false;
        char[] chars = address.toCharArray();
        Arrays.sort(chars);
        if (Arrays.binarySearch(chars, 0, chars.length/2, '0') > -1) {
            return false;
        }
        if (Arrays.binarySearch(chars, chars.length/2, chars.length, 'l') > -1) {
            return false;
        }
        if (Arrays.binarySearch(chars, 'O') > -1 || Arrays.binarySearch(chars, 'I') > -1) {
            return false;
        }
        return true;
    }

    private static boolean sha256Validation(String addr) throws NoSuchAlgorithmException {
        if (addr == null) {
            return false;
        }
        char[] chars = addr.toCharArray();
        BigInteger n = BigInteger.ZERO;
        for (char c: chars) {
            int indexOfChar = ALPHABET.indexOf(c);
            if (indexOfChar == -1) {
                return false;
            }
            n = n.multiply(BigInteger.valueOf(58L)).add(BigInteger.valueOf(indexOfChar));
        }
        byte[] nBytes = n.toByteArray();
        byte[] decoded25 = new byte[25];
        int dif = decoded25.length - nBytes.length;
        System.arraycopy(nBytes, 0, decoded25, dif, nBytes.length);
        return Arrays.equals(bytesRange(decoded25, 21, 25), first4BytesOfDoubleDigest(decoded25));
    }

    private static byte[] bytesRange(byte[] decoded25, int from, int to) {
        return Arrays.copyOfRange(decoded25, from, to);
    }

    private static byte[] first4BytesOfDoubleDigest(byte[] decoded25) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] firstDigest = sha256.digest(bytesRange(decoded25, 0, 21));
        byte[] secondDigest = sha256.digest(firstDigest);
        return bytesRange(secondDigest, 0, 4);
    }

}
