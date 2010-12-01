package midbase.security;

import java.security.GeneralSecurityException;

import javax.crypto.BadPaddingException;

public class ChriptoHelper {

	private Codec codec;	
	
	public ChriptoHelper(){
		this.codec = new Codec();
	}
	
	public byte[] Enc(byte[] password, byte[] message) {
		try {
			// Encrypt the message
			byte[] encryptedMessage = this.codec.encrypt(password, message);
			// Calculate the digest of the encrypted message
			byte[] digest = this.codec.digest(encryptedMessage);
			// Compose the overall message
			byte[] messageBytes = new byte[encryptedMessage.length
					+ digest.length];
			System.arraycopy(digest, 0, messageBytes, 0, digest.length);
			System.arraycopy(encryptedMessage, 0, messageBytes, digest.length,
					encryptedMessage.length);
			// Store the encoded message
			
			return messageBytes;

		} catch (GeneralSecurityException gse) {
			System.out.println(gse.toString());
		}
		
		return null;
	}
	
	public String Dec(byte[] message, byte[] password){
		try
        {
          // Get the cipher text and the digest
          // SHA-1 digest is 160 bits long
          byte[] digest = new byte[20];
          byte[] cipherText = new byte[message.length-digest.length];
          // Decompose the message
          System.arraycopy(message, 0, digest, 0, digest.length);
          System.arraycopy(message,
              digest.length,
              cipherText,
              0,
              cipherText.length);
          
          // Verify the cipher's text digest
          if (codec.isDigestValid(cipherText, digest)) {
            // If digest is ok, let's decrypt
            byte[] plainText = codec.decrypt(password, cipherText);        
             
            return new String(plainText);
            
            // Display the message on screen            
          } else {
            System.out.println("Digest of message is not valid");
          }            
        }
        catch (BadPaddingException bpe)
        {
          // This is a particular exception when the password is incorrect
        	System.out.println("Incorrect password.");
        }
        catch (GeneralSecurityException gse)
        {
          // Handles all other exceptions
        	System.out.println("General Security Exception while decrypting: "
              + gse.toString());
        }
        
        return null;
	}

}
