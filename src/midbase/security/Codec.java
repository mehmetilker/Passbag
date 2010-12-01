package midbase.security;

import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

// Utility class that encapsulates the calls to the SATSA API
class Codec
{
  // we can have a global instance of the algorithms
  // as long as we synchronize access
  private MessageDigest digest = null;
  private Cipher cipher = null;
  private boolean operative = true;
    
  // Builds the Codec object and initializes the
  // cipher and digest 
  Codec()
  {    
    try {
      digest = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      // Basically this should not happen since we have to know 
      // if SHA-1 is availabe otherwise the whole design 
      // cannot work
      operative = false;
    }
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    } catch (NoSuchAlgorithmException e) {
      // This should not happen since we know the target platform
      // but we set the operative flag to false just in case
      operative = false;
    } catch (NoSuchPaddingException e) {
      // This should not happen since we know the target platform
      // but we set the operative flag to false just in case
      operative = false;
    }
  }  

  // Encrypt text with given AES key. It encodes the message
  // including the length in two bytes and the plaintext
  synchronized byte[] encrypt (byte[] keyBits, byte[] plaintext)
    throws InvalidKeySpecException, InvalidKeyException,
      IllegalStateException, ShortBufferException,
      IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException
  {
    if (operative) {
      // Initialize the key from  the password
      Key key = new SecretKeySpec(keyBits, 0, keyBits.length, "AES");
      // add 2 bytes to encode the length of the plaintext
      // as a short value
      byte[] plaintextAndLength = new byte[plaintext.length + 2];
      plaintextAndLength[0] = (byte)(0xff & (plaintext.length >> 8));
      plaintextAndLength[1] = (byte)(0xff & plaintext.length);
      // build the new plaintext
      System.arraycopy(plaintext,
          0,
          plaintextAndLength,
          2,
          plaintext.length);   
    
      // calculate the size of the ciperthext considering
      // the padding
      int blocksize = 16;
      int ciphertextLength = 0;
      int remainder = plaintextAndLength.length % blocksize;
      if (remainder == 0) {
        ciphertextLength = plaintextAndLength.length;
      } else {
        ciphertextLength = plaintextAndLength.length - remainder
          + blocksize;
      }
      byte[] cipherText = new byte[ciphertextLength];
    
      // reinitialize the cipher in encryption mode with the given key
      cipher.init(Cipher.ENCRYPT_MODE, key);
      // do the encryption
      cipher.doFinal(plaintextAndLength,
          0,
          plaintextAndLength.length,
          cipherText,
          0);
      
      return cipherText;
    } else {
      throw new IllegalStateException("Codec not initialized");
    }
  }
  
  synchronized byte[] digest(byte message[]) throws DigestException {
    if (operative) {
      // Reset the digest and update with the data
      digest.reset();
      digest.update(message, 0, message.length);
      // SHA-1 produces 160-bit long digests
      byte[] output = new byte[20];
      digest.digest(output, 0, output.length);
      return output;
    } else {
      throw new IllegalStateException("Codec not initialized");
    }

  }
  
  synchronized boolean isDigestValid(byte message[], byte[] digest)
    throws DigestException {
    if (operative) {
      byte[] calculatedDigest = digest(message);
      if (calculatedDigest.length != digest.length) {
        return false;
      }
      // compare byte per byte
      for (int i=0;i<digest.length;i++) {
        if (calculatedDigest[i] != digest[i]) {
          return false;
        }
      }
      return true;
    } else {
      throw new IllegalStateException("Codec not initialized");
    }    
  }

  // Decrypt text with given AES key. It decodes the message
  // reading the message length and then the message itself
  synchronized byte[] decrypt (byte[] keyBits, byte[] cipherText)
    throws InvalidKeySpecException, InvalidKeyException,
      IllegalStateException, ShortBufferException,
      IllegalBlockSizeException, BadPaddingException,
      InvalidAlgorithmParameterException
  {
    if (operative) {
      // create a key from the keyBits
      Key key = new SecretKeySpec(keyBits, 0, keyBits.length, "AES");
    
      // Initialize the cipher in decrypt mode
      cipher.init(Cipher.DECRYPT_MODE, key);
  
      byte[] decrypted = new byte[cipherText.length];
      // Decrypt the cipher text
      cipher.doFinal(cipherText, 0, cipherText.length, decrypted, 0);
      // Calculate the length of the plaintext
      int plainTextLength = (decrypted[0] << 8)  |
        (decrypted[1] & 0xff);
      byte[] finalText = new byte[plainTextLength];
      // Decode the final text
      System.arraycopy(decrypted, 2, finalText, 0, plainTextLength);
  
      return finalText;
    } else {
      throw new IllegalStateException("Codec not initialized");
    }  
  }

  // Displays ecrypted data in hex
  String byteToHex(byte[] data)
  { 
    StringBuffer hexString = new StringBuffer();
    String hexCodes = "0123456789ABCDEF";

    for (int i=0; i < data.length; i++) 
    {
      hexString.append( hexCodes.charAt( (data[i] >> 4) & 0x0f) );
      hexString.append( hexCodes.charAt( data[i] & 0x0f) );
      if (i< data.length - 1)
      {
        hexString.append(":");
      }
      if ( ((i+1)%8) == 0)
        hexString.append("\n");
    }
    return hexString.toString();
  }
}
