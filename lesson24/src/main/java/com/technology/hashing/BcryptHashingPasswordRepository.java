package com.technology.hashing;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHashingPasswordRepository implements HashingPasswordRepository {
  private final int logRounds;

  public BcryptHashingPasswordRepository(int logRounds) {
    this.logRounds = logRounds;
  }

  @Override
  public String generateHash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
  }

  @Override
  public boolean verifyHash(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }
}
